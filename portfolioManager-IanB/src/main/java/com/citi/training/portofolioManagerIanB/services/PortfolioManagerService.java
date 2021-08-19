package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.AccountActivity;
import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public interface PortfolioManagerService {

    void deposit(Double cash, Integer userId);

    Double getNetWorth(Date date);

    void deleteAccountActivity(Date date);

    void withdraw(Double cash, Integer userId);

    void updateNetWorth(Integer id, Date date);

    Double getCashAccountByDate(Date date);

    Collection<Investments> getAllInvestments();

    Double getInvestmentValue(String ticker);

    /* buy and sell investment return its current quantity after buying/selling
    * returns null if failed */
    Double buyInvestment(String ticker, Double quantity) throws UnirestException;

    Double sellInvestment(String ticker, Double quantity);

//    void deleteInvestment(String ticker);

    List<Investments> calculateTopFiveGainers();

    List<Investments> calculateTopFiveLosers();

    Dictionary<String, Double> getIndices();
}




