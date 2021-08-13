package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.AccountActivity;
import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;

import java.util.Collection;
import java.util.Date;

public interface PortfolioManagerService {

    void deposit(Date date, Double cash, Integer userId);


    Double getNetWorth(Date date);

    void deleteAccountActivity(AccountActivity accountAct);

    void withdraw(Date date, Double cash, Integer userId);

    void updateNetWorth(Integer id, Date date);

    Double getCashAccountByDate(Date date);

    Collection<Investments> getAllInvestments();

    Double getInvestmentValue(String ticker);

    void buyInvestment(String ticker, Double quantity);

    void sellInvestment(String ticker, Double quantity);

    void deleteInvestment(String ticker);
}




