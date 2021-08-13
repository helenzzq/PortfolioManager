package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.AccountActivity;
import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;
import com.citi.training.portofolioManagerIanB.repo.AccountRepository;
import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerRepository;
import com.citi.training.portofolioManagerIanB.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PortfolioManagerServiceImpl implements PortfolioManagerService {
    @Autowired
    private PortfolioManagerRepository portfolioManagerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountActivityDao;

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
    public void deposit(Date date, Double cash, Integer userId) {
        User user = userRepository.getById(userId);
        AccountActivity accountActivity = user.getTodayAccountActivity();
        accountActivity.deposit(cash);

    }

    @Override
    public void withdraw(Date date, Double cash, Integer userId) {
        User user = userRepository.getById(userId);
        AccountActivity accountActivity = user.getTodayAccountActivity();
        accountActivity.withdraw(cash);

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
    public void deleteAccountActivity(AccountActivity accountAct) {
        accountActivityDao.delete(accountAct);
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
    public void buyInvestment(String ticker, Double quantity) {
        Double currentQuantity = 0.0;
        Investments investment = portfolioManagerRepository.getById(ticker);
        currentQuantity = investment.getQuantity();
        investment.setQuantity(currentQuantity + quantity);


    }

    @Override
    public void sellInvestment(String ticker, Double quantity) {

    }

    @Override
    public void deleteInvestment(String ticker) {
        portfolioManagerRepository.deleteById(ticker);
    }
}
