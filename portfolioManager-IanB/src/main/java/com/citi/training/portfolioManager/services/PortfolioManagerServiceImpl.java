package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.*;
import com.citi.training.portfolioManager.entities.investments.*;
import com.citi.training.portfolioManager.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.lang.Math.round;

@Service
public class PortfolioManagerServiceImpl implements PortfolioManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountActivityRepo;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EtfRepository etfRepository;
    @Autowired
    private BondRepository bondRepository;
    @Autowired
    private FutureRepository futureRepository;

    private HashMap<String, JpaRepository> investmentsRepos = new HashMap<>();

    List<String> INVESTMENTS = Arrays.asList("Stock", "Bond", "Future", "ETF");

    private void updateInvestmentRepo() {
        investmentsRepos.put("Stock", stockRepository);
        investmentsRepos.put("Bond", bondRepository);
        investmentsRepos.put("ETF", etfRepository);
        investmentsRepos.put("Future", futureRepository);
    }

    private Investment getInvestmentFromRepo(String type, String ticker) {
        updateInvestmentRepo();
        Investment investment;
        try {
            investment = (Investment) investmentsRepos.get(type).getById(ticker);
        } catch (Exception e) {
            investment = null;
        }
        return investment;
    }

    @Override
    public List<HashMap<String, Object>> getInvestmentPercentage(Integer userId) {
        List<HashMap<String, Object>> investmentMap = new ArrayList<>();
        AccountActivity ac = userRepository.getById(1).getTodayAccountActivity();
        HashMap<String, Object> investmentEntry = new HashMap<>();
        double cash = ac.getCashValue();
        double total = ac.getTotalEquity();
        investmentEntry.put("investmentType", "Cash");
        investmentEntry.put("marketValue", (float)cash);
        float p = (float)cash*100/(float)total;
        investmentEntry.put("percentage", round(p*100.0)/100.0);
        investmentMap.add(investmentEntry);
        for (int i = 0; i < 4; i++) {
            investmentEntry = new HashMap<>();

            String types = INVESTMENTS.get(i);
            updateInvestmentRepo();

            Collection<Investment> investments = investmentsRepos.get(types).findAll();
            Double marketValue = 0.0;
            Double percentage = 0.0;
            for (Investment investment : investments) {
                marketValue += investment.getMarketValue();
                percentage += investment.getPercentInPort();
            }
            investmentEntry.put("investmentType", types);
            investmentEntry.put("marketValue", marketValue);
            investmentEntry.put("percentage", percentage);
            investmentMap.add(investmentEntry);
        }

        return investmentMap;
    }

    @Override
    public Double getTodayNetWorth() {
        Double netWorth = 0.0;

        for (int i = 0; i < 4; i++) {
            String types = INVESTMENTS.get(i);
            updateInvestmentRepo();
            Collection<Investment> investments = investmentsRepos.get(types).findAll();
            for (Investment investment : investments) {
                netWorth += investment.getProfitNLoss();
            }
        }
        return netWorth;

    }

    @Override
    public Double getInvestmentValue(String type, String ticker) {

        return getInvestmentFromRepo(type, ticker).getMarketValue();
    }

    private Investment initInvestmentByName(String name, String ticker, Double quantity, Double price) throws ClassNotFoundException, NoSuchMethodException {
        String className = "com.citi.training.portfolioManager.entities." + name;
        Investment investment = null;
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor(String.class, Double.class, Double.class, Double.class);
        try {
            investment = (Investment) constructor.newInstance(ticker, quantity, price, price);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return investment;

    }

    @Override
    public Collection<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Collection<Bond> getBonds() {
        return bondRepository.findAll();
    }

    @Override
    public Collection<Etf> getEtf() {
        return etfRepository.findAll();
    }

    @Override
    public Collection<Future> getFuture() {
        return futureRepository.findAll();
    }


    @Override
    public HashMap<String, List<Investment>> getAllInvestment(Integer userId) {
        return userRepository.getById(1).getInvestment();
    }

    @Override
    public Double buyInvestment(String type, String ticker, Double quantity) {
        Double currentQuantity = 0.0;
        //Check if current investment exist
        Investment investment = getInvestmentFromRepo(type, ticker);
        //Step 1: Identify investment type
        //Case 1: Buy Stock
        Double price = 0.0;
        String className = "com.citi.training.portfolioManager.entities." + type;
        Investment investment1 = null;
        try {
            investment1 = initInvestmentByName(type, ticker, quantity, price);
            //Store it into the corresponding repo
            investmentsRepos.get(type).save(Class.forName(className).cast(investment1));

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        Date today = new Date();
        AccountActivity accountActivity = accountActivityRepo.getById(today);

        // If no enough cash value:
        if (quantity * price > accountActivity.getCashValue()) return null;

        // buy it successfully
        currentQuantity = investment.getQuantity();
        Double resultQuantity = currentQuantity + quantity;
        accountActivity.withdraw(quantity * price);
        investment.setQuantity(resultQuantity);
        return resultQuantity;
    }

    @Override
    public Integer sellInvestment(String type, String ticker, Double quantity) {
        Double currentQuantity = 0.0;
        // check if this investment ticker exist
        Investment investment = getInvestmentFromRepo(type, ticker);
        if (investment == null) {
            return 404;
        } else {
            currentQuantity = investment.getQuantity();
            if (currentQuantity < quantity) return 406;
        }

        AccountActivity accountActivity = accountActivityRepo.getById(new Date());


        Double price = 0.0;
        // If there is no that many investment to sell, return null.
        JpaRepository targetRepo = investmentsRepos.get(type);

        // If selling all of the given investment, delete it from the table.
        if (currentQuantity.equals(quantity)) {
            targetRepo.deleteById(ticker);
        } else {
            // Sell at marketPrice
            Double resultQuantity = currentQuantity - quantity;
            targetRepo.save(investment);
            accountActivity.deposit(quantity * price);
        }

        return 200;
    }


}
