package com.citi.training.portfolioManager.services;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.HashMap;

public interface MarketUpdaterServices {
    public HashMap<Integer, String> getDailyGainers();
    public HashMap<Integer, String> getDailyLosers();
    public Double getStockPrice(String symbol) throws UnirestException;

    Double getInvestmentPrice(String type, String symbol) ;
}
