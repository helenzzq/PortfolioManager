package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.entities.AccountActivity;
import com.citi.training.portofolioManager.entities.Investment;
import com.citi.training.portofolioManager.repo.AccountRepository;
import com.citi.training.portofolioManager.repo.UserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
@Service
public class PortfolioManagerServiceImpl implements PortfolioManagerService {
//    @Autowired
//    private PortfolioManagerRepository portfolioManagerRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AccountRepository accountActivityDao;
    @Autowired
    private MarketUpdaterServices InvestmentUpdaterServices;

    public List<AccountActivity> getAccountHistory() {

        return accountActivityDao.findAll();

    }


//    public AccountActivity getAccountActivityByDate(Date date) {
////        Optional<AccountActivity> accountOptional = accountActivityDao.findById(date);
//        if (accountOptional.isPresent()) {
//            return accountOptional.get();
//        }
//        return null;
//    }

    //It should be called everytime when updatingMarketPrice and at the time

    @Override
    public void updateNetWorth(Integer id, Date date) {
//        AccountActivity accountActivity = accountActivityDao.getById(date);
        HashMap<String, List<Investment>> Investment = userRepository.getById(id).getInvestment();
        Double sum = 0.0;
//        for (Investment securities : Investment.values()) {
//            sum += securities.getProfitNLoss();
//        }
//        accountActivity.setNetWorth(sum);
    }

    @Override
    public Double getCashAccountByDate(Date date) {
        return null;
    }


    @Override
    public Double getNetWorth(Date date) {
        AccountActivity accountActivity = accountActivityDao.getById(date);
        return accountActivity.getNetWorth();

    }

    @Override
    public Double buyInvestment(String ticker, Double quantity) throws UnirestException {
        return null;
    }

    @Override
    public Double sellInvestment(String ticker, Double quantity) throws UnirestException {
        return null;
    }



    @Override
    public Double getInvestmentValue(Integer userId,String ticker) {
        HashMap<String, List<Investment>> investments = getAllInvestment(userId);
        investments.values();

    }

//
//    @Override
//    public Double buyInvestment(String ticker, Double quantity) throws UnirestException {
//        Double currentQuantity = 0.0;
//        Investment investment = portfolioManagerRepository.getById(ticker);
//        Double price = InvestmentUpdaterServices.getStockPrice(ticker);
//        Date today = new Date();
//        AccountActivity accountActivity = accountActivityDao.getById(today);
//
//        // If no enough cash value:
//        if(quantity * price > accountActivity.getCashValue()) return null;
//
//        // If no such investment in investment table:
////        if(investment == null) {
////            investment = new Investment(ticker,0.0, price, price);
////        }
//
//        // buy it successfully
//        currentQuantity = investment.getQuantity();
//        Double resultQuantity = currentQuantity + quantity;
//        accountActivity.withdraw(quantity * price);
//        investment.setQuantity(resultQuantity);
//        return resultQuantity;
//    }
//
//    @Override
//    public Double sellInvestment(String ticker, Double quantity) throws UnirestException {
//        Double currentQuantity = 0.0;
//        Investment investment = portfolioManagerRepository.getById(ticker);
//        Date today = new Date();
//        AccountActivity accountActivity = accountActivityDao.getById(today);
//
//        // If no such investment bought before.
//        if(investment == null) return null;
//
//        currentQuantity = investment.getQuantity();
//
//        Double price = InvestmentUpdaterServices.getStockPrice(ticker);
//
//        // If there is no that many investment to sell, return null.
//        if(currentQuantity < quantity) return null;
//
//        // If selling all of the given investment, delete it from the table.
//        if(currentQuantity == quantity) {
//            portfolioManagerRepository.deleteById(ticker);
//            return 0.0;
//        }
//
//        // Sell
//        Double resultQuantity = currentQuantity - quantity;
//        investment.setQuantity(resultQuantity);
//        accountActivity.deposit(quantity * price);
//        return resultQuantity;
//    }

    @Override
    public HashMap<String, List<Investment>> getAllInvestment(Integer userId) {
       return  userRepository.findById(userId).get().getInvestment();
    }

    @Override
    public Dictionary<String, Double> getIndices() {
        return null;
    }
}
