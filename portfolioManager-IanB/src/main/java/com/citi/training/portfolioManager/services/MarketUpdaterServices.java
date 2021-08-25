package com.citi.training.portfolioManager.services;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.HashMap;

public interface MarketUpdaterServices {
    public HashMap<Integer, String> getDailyGainers();
    public HashMap<Integer, String> getDailyLosers();
    public Double getStockPrice(String symbol) throws UnirestException;


    HashMap<String, Double> getIndicesInfo(String symbol);

    Double getIndicesOpenPrice(String symbol);

    Double getIndicesCurrPrice(String symbol);

    Double getIndicesChangeInPercent(String symbol);

    HashMap<String,String> getFamousIndicesInfo();

    Double getInvestmentPrice(String type, String symbol) ;
}
