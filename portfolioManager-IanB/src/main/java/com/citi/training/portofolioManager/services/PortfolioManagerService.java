package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.entities.AccountActivity;
import com.citi.training.portofolioManager.entities.Investment;
import com.citi.training.portofolioManager.entities.User;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;

public interface PortfolioManagerService {

    Double deposit(Double cash, Integer userId);

    Double getNetWorth(Date date);

    void deleteAccountActivity(Date date);

    Double withdraw(Double cash, Integer userId);

    void updateNetWorth(Integer id, Date date);

    Double getCashAccountByDate(Date date);



    Double getInvestmentValue(String ticker);

    /* buy and sell investment return its current quantity after buying/selling
    * returns null if failed */
    Double buyInvestment(String ticker, Double quantity) throws UnirestException;

    Double sellInvestment(String ticker, Double quantity) throws UnirestException;

    Dictionary<String, Double> getIndices();

    Collection<User> getUsers();
    Collection<AccountActivity> getAccountActivity();

    HashMap<String, List<Investment>> getAllInvestment();
}




