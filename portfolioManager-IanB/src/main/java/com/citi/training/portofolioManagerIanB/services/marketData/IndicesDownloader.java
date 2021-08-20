package com.citi.training.portofolioManagerIanB.services.marketData;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IndicesDownloader extends MarketDownloader {

    private Double changeInPercent;
    private double currPrice;
    private double openPrice;

    public IndicesDownloader(String symbol) {
        super(symbol);
        try {
            retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void retrieveData() throws IOException {

        currPrice = super.downloadTimeSeriesFromTwelveData("1min");
        openPrice = super.downloadTimeSeriesFromTwelveData("1day");
        changeInPercent = (currPrice - openPrice) / openPrice;


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

    public static void main(String[] args) throws IOException, UnirestException {

        IndicesDownloader indicesDownloader = new IndicesDownloader("SPX");
        indicesDownloader.retrieveData();
        System.out.println("Open Price for SPX is:" + indicesDownloader.getOpenPrice());
        System.out.println("Curr Price for SPX is:" + indicesDownloader.getCurrPrice());
        System.out.println("Change in Percentage for SPX is:" + indicesDownloader.getChangeInPercent());
    }

}

