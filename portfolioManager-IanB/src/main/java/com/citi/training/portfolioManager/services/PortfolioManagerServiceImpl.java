package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.*;
import com.citi.training.portfolioManager.repo.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class PortfolioManagerServiceImpl implements PortfolioManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountActivityRepo;
    @Autowired
    private MarketUpdaterServices marketUpdaterServices;

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EtfRepository etfRepository;
    @Autowired
    private BondRepository bondRepository;
    @Autowired
    private FutureRepository futureRepository;

    private HashMap<String, JpaRepository> investmentsRepos = new HashMap<>();

    private void updateInvestmentRepo() {
        investmentsRepos.put("Stock", stockRepository);
        investmentsRepos.put("Bond", bondRepository);
        investmentsRepos.put("Etf", etfRepository);
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

    //It should be called everytime when updatingMarketPrice and at the time

    public Double getTodayNetWorth(){
        Double netWorth = 0.0;
        List<String> typeLst = Arrays.asList("Stock", "Etf","Bond","Future");
        for (int i =0; i<4;i++){
            String types = typeLst.get(i);
            Collection<Investment> investments = investmentsRepos.get(types).findAll();
            for (Investment investment : investments){
                netWorth +=investment.getProfitNLoss();
            }
        }
        return netWorth;

    }

    @Override
    public void updateNetWorth(Integer id, Date date) {
    }

    @Override
    public Double getCashAccountByDate(Date date) {
        return null;
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
    public Double buyInvestment(String type, String ticker, Double quantity) {
        Double currentQuantity = 0.0;
        //Check if current investment exist
        Investment investment = getInvestmentFromRepo(type, ticker);
        //Step 1: Identify investment type
        //Case 1: Buy Stock
        Double price = marketUpdaterServices.getInvestmentPrice(type, ticker);
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
    public Integer sellInvestment(String type, String ticker, Double quantity) throws UnirestException {
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


        Double price = marketUpdaterServices.getStockPrice(ticker);
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


    @Override
    public Double getNetWorth(Date date) {
        AccountActivity accountActivity = accountActivityRepo.getById(date);
        return accountActivity.getNetWorth();

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

}