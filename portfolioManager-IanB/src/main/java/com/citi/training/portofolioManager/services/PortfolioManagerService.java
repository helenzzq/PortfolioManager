package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.entities.AccountActivity;
import com.citi.training.portofolioManager.entities.Investment;
import com.citi.training.portofolioManager.entities.User;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;

public interface PortfolioManagerService {


    Double getNetWorth(Date date);


    void updateNetWorth(Integer id, Date date);

    Double getCashAccountByDate(Date date);

    Double getInvestmentValue(String type,Integer userId,String ticker);

    /* buy and sell investment return its current quantity after buying/selling
    * returns null if failed */
    Double buyInvestment(String type, String ticker, Double quantity) throws UnirestException;

    Double sellInvestment(String type,String ticker, Double quantity) throws UnirestException;

    HashMap<String, List<Investment>> getAllInvestment(Integer userId);

    Dictionary<String, Double> getIndices();


}




