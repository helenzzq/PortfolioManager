package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.AccountActivity;
import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;
import com.citi.training.portofolioManagerIanB.repo.AccountRepository;
import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PortfolioManagerServiceImpl implements PortfolioManagerService {
    @Autowired
    private PortfolioManagerRepository portfolioManagerRepository;

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
    public void deposit(Date date, Double cash, User user) {
        AccountActivity accountActivity = user.getTodayAccountActivity();
        accountActivity.deposit(cash);

    }

    @Override
    public void withdraw(Date date, Double cash, User user) {
        AccountActivity accountActivity = user.getTodayAccountActivity();
        accountActivity.withdraw(cash);

    }

    @Override
    public void deleteAccountActivity(AccountActivity accountAct) {
        accountActivityDao.delete(accountAct);
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
        if (investment != null) {
            currentQuantity = investment.getQuantity();
            investment.setQuantity(currentQuantity + quantity);
        }

    }

    @Override
    public void sellInvestment(String ticker, Double quantity) {

    }

    @Override
    public void deleteInvestment(String ticker) {
        portfolioManagerRepository.deleteById(ticker);
    }
}
