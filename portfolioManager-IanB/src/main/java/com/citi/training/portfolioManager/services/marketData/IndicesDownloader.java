package com.citi.training.portfolioManager.services.marketData;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.HashMap;

public class IndicesDownloader extends MarketDownloader {

    private Double changeInPercent;
    private double currPrice;
    private double openPrice;
    public HashMap<String,Double > indicesInfo = new HashMap<>();

    public IndicesDownloader(String symbol) {
        super(symbol);
        retrieveData();
    }

    @Override
    public void retrieveData() {

        try {
            currPrice = super.downloadTimeSeriesFromTwelveData("1min");
            openPrice = super.downloadTimeSeriesFromTwelveData("1day");
        } catch (IOException e) {
            e.printStackTrace();
        }

        changeInPercent = (currPrice - openPrice) / openPrice;

        indicesInfo.put("currPrice",currPrice);
        indicesInfo.put("openPrice",openPrice);
        indicesInfo.put("changeInPercent",changeInPercent);
    }

    public HashMap<String, Double> getIndicesInfo() {
        return indicesInfo;
    }

    public Double getChangeInPercent() {
        return changeInPercent;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public static void main(String[] args) throws IOException {

        IndicesDownloader indicesDownloader = new IndicesDownloader("SPX");
        indicesDownloader.retrieveData();
        System.out.println("Open Price for SPX is:" + indicesDownloader.getOpenPrice());
        System.out.println("Curr Price for SPX is:" + indicesDownloader.getCurrPrice());
        System.out.println("Change in Percentage for SPX is:" + indicesDownloader.getChangeInPercent());
    }

}

