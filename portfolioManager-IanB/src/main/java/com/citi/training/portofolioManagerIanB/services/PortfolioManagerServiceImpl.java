package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.AccountActivity;
import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;
import com.citi.training.portofolioManagerIanB.repo.AccountRepository;
import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerRepository;
import com.citi.training.portofolioManagerIanB.repo.UserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
@Service
public class PortfolioManagerServiceImpl implements PortfolioManagerService {
    @Autowired
    private PortfolioManagerRepository portfolioManagerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountActivityDao;
    @Autowired
    private InvestmentsUpdaterServices investmentsUpdaterServices;

    public List<AccountActivity> getAccountHistory() {

        return accountActivityDao.findAll();

    }


    public AccountActivity getAccountActivityByDate(Date date) {
        Optional<AccountActivity> accountOptional = accountActivityDao.findById(date);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        }
        return null;
    }

    @Override
    public Double deposit(Double cash, Integer userId) {
        User user = userRepository.getById(userId);
        AccountActivity accountActivity = user.getTodayAccountActivity();
        accountActivity.deposit(cash);
        return accountActivity.getCashValue();
    }

    @Override
    public Double withdraw(Double cash, Integer userId) {
        User user = userRepository.getById(userId);
        AccountActivity accountActivity = user.getTodayAccountActivity();
        // If there's no enough cash in account, withdraw fails.
        if(cash > accountActivity.getCashValue()) return null;
        accountActivity.withdraw(cash);
        return accountActivity.getCashValue();
    }

    //It should be called everytime when updatingMarketPrice and at the time

    @Override
    public void updateNetWorth(Integer id, Date date) {
        AccountActivity accountActivity = accountActivityDao.getById(date);
        List<Investments> investments = userRepository.getById(id).getInvestments();
        Double sum = 0.0;
        for (Investments securities : investments) {
            sum += securities.getProfitNLoss();
        }
        accountActivity.setNetWorth(sum);
    }


    @Override
    public Double getNetWorth(Date date) {
        AccountActivity accountActivity = accountActivityDao.getById(date);
        return accountActivity.getNetWorth();

    }

    @Override
    public void deleteAccountActivity(Date date) {
        accountActivityDao.deleteById(date);
    }

    @Override
    public Double getCashAccountByDate(Date date) {
        return accountActivityDao.getById(date).getCashValue();
    }

    @Override
    public Collection<Investments> getAllInvestments() {
        return portfolioManagerRepository.findAll();
    }

    @Override
    public Double getInvestmentValue(String ticker) {
        return portfolioManagerRepository.findById(ticker).get().getMarketValue();
    }


    @Override
    public Double buyInvestment(String ticker, Double quantity) throws UnirestException {
        Double currentQuantity = 0.0;
        Investments investment = portfolioManagerRepository.getById(ticker);
        Double price = investmentsUpdaterServices.getStockPrice(ticker);
        Date today = new Date();
        AccountActivity accountActivity = accountActivityDao.getById(today);

        // If no enough cash value:
        if(quantity * price > accountActivity.getCashValue()) return null;

        // If no such investment in investment table:
        if(investment == null) {
            investment = new Investments(ticker,0.0, price, price);
        }

        // buy it successfully
        currentQuantity = investment.getQuantity();
        Double resultQuantity = currentQuantity + quantity;
        accountActivity.withdraw(quantity * price);
        investment.setQuantity(resultQuantity);
        return resultQuantity;
    }

    @Override
    public Double sellInvestment(String ticker, Double quantity) throws UnirestException {
        Double currentQuantity = 0.0;
        Investments investment = portfolioManagerRepository.getById(ticker);
        Date today = new Date();
        AccountActivity accountActivity = accountActivityDao.getById(today);

        // If no such investment bought before.
        if(investment == null) return null;

        currentQuantity = investment.getQuantity();

        Double price = investmentsUpdaterServices.getStockPrice(ticker);

        // If there is no that many investment to sell, return null.
        if(currentQuantity < quantity) return null;

        // If selling all of the given investment, delete it from the table.
        if(currentQuantity == quantity) {
            portfolioManagerRepository.deleteById(ticker);
            return 0.0;
        }

        // Sell
        Double resultQuantity = currentQuantity - quantity;
        investment.setQuantity(resultQuantity);
        accountActivity.deposit(quantity * price);
        return resultQuantity;
    }


    @Override
    public Dictionary<String, Double> getIndices() {
        return null;
    }
}
