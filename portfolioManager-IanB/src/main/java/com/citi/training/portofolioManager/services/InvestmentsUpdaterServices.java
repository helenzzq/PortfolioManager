package com.citi.training.portofolioManager.services;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;

public interface InvestmentsUpdaterServices {
    public HashMap<Integer, String> getDailyGainers();
    public HashMap<Integer, String> getDailyLosers();
    public Double getStockPrice(String symbol) throws UnirestException;
}
