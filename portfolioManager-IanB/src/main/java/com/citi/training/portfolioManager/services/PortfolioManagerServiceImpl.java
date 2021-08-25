package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.*;
import com.citi.training.portfolioManager.repo.*;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.threeten.extra.YearQuarter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
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

    @Override
    public Collection<AccountActivity> getAccountActivityByRange(String range) {
        LocalDate today = LocalDate.now();
        Collection<AccountActivity> accountActivities = null;
        int month = today.getMonthValue();
        switch (range) {
            case "lastWeek":
                LocalDate date = today.with(DayOfWeek.MONDAY).minusDays(7);
                Date lastMonday = java.sql.Date.valueOf(date);
                Date lastFriday = java.sql.Date.valueOf(date.plusDays(4));
                accountActivities = accountActivityRepo.getAccountActivitiesByInterval(lastMonday, lastFriday);
                break;
            case "lastMonth":
                accountActivities = accountActivityRepo.getAccountActivitiesByYearAndMonth(month - 1, 2021);
                break;
            case "lastQuarter":
                YearQuarter lastQuarter = YearQuarter.now(ZoneId.systemDefault()).minusQuarters(1);
                Date firstDay = java.sql.Date.valueOf(lastQuarter.atDay(1));
                Date lastDayOfLastQuarter = java.sql.Date.valueOf(lastQuarter.atEndOfQuarter());
                accountActivities = accountActivityRepo.getAccountActivitiesByInterval(firstDay, lastDayOfLastQuarter);
                break;
        }
        return accountActivities;

    }
    private HashMap<String,List<Double>> getAccountInfoByRange(String range){
        HashMap<String, List<Double>> accountInfo = new HashMap<>();
        Collection<AccountActivity> accountActivities = getAccountActivityByRange(range);
        List<Double> netWorthLst = new ArrayList<>();
        List<Double> cashLst = new ArrayList<>();
        List<Double> equityLst = new ArrayList<>();
        List<Double> totalEquityLst = new ArrayList<>();
        for (AccountActivity ac : accountActivities){
            netWorthLst.add(ac.getNetWorth());
            cashLst.add(ac.getCashValue());
            equityLst.add(ac.getInvestmentValue());
            totalEquityLst.add(ac.getTotalEquity());
        }
        accountInfo.put("netWorth",netWorthLst);
        accountInfo.put("investmentValue",equityLst);
        accountInfo.put("cashValue",cashLst);
        accountInfo.put("totalEquity",totalEquityLst);
        return accountInfo;
    }
    @Override
    public List<Double> getNetWorthByRange(String range) {
        return getAccountInfoByRange(range).get("netWorth");
    }

    @Override
    public List<Double> getCashValueByRange(String range) {
        return getAccountInfoByRange(range).get("cashValue");
    }

    @Override
    public List<Double> getInvestmentValueByRange(String range) {
        return getAccountInfoByRange(range).get("investmentValue");
    }

    @Override
    public List<Double> getTotalEquityByRange(String range) {
        return getAccountInfoByRange(range).get("totalEquity");
    }


    @Override
    public Double getTodayNetWorth() {
        Double netWorth = 0.0;
        List<String> typeLst = Arrays.asList("Stock", "Etf", "Bond", "Future");
        for (int i = 0; i < 4; i++) {
            String types = typeLst.get(i);
            updateInvestmentRepo();
            Collection<Investment> investments = investmentsRepos.get(types).findAll();
            for (Investment investment : investments) {
                netWorth += investment.getProfitNLoss();
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
