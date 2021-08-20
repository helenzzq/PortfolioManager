package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.entities.Investments;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;

public interface PortfolioManagerService {

    Double deposit(Double cash, Integer userId);

    Double getNetWorth(Date date);

    void deleteAccountActivity(Date date);

    Double withdraw(Double cash, Integer userId);

    void updateNetWorth(Integer id, Date date);

    Double getCashAccountByDate(Date date);

    Collection<Investments> getAllInvestments();

    Double getInvestmentValue(String ticker);

    /* buy and sell investment return its current quantity after buying/selling
    * returns null if failed */
    Double buyInvestment(String ticker, Double quantity) throws UnirestException;

    Double sellInvestment(String ticker, Double quantity) throws UnirestException;

    Dictionary<String, Double> getIndices();
}




