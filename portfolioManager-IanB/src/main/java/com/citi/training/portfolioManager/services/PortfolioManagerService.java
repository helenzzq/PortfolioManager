package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.Investment;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;

public interface PortfolioManagerService {


    Double getNetWorth(Date date);


    void updateNetWorth(Integer id, Date date);

    Double getCashAccountByDate(Date date);

    Double getInvestmentValue(String type,String ticker);

    /* buy and sell investment return its current quantity after buying/selling
    * returns null if failed */
    Double buyInvestment(String type, String ticker, Double quantity) throws UnirestException;

    Double sellInvestment(String type,String ticker, Double quantity) throws UnirestException;

    HashMap<String, List<Investment>> getAllInvestment(Integer userId);


}




