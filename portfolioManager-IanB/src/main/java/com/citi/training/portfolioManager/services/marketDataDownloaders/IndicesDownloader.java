package com.citi.training.portfolioManager.services.marketDataDownloaders;

import java.io.IOException;
import java.util.HashMap;

public class IndicesDownloader extends MarketDownloader {

    private Double changeInPercent;
    private double currPrice;
    private double openPrice;
    private final String[] indicesLst = {"SPX","IXIC","DJI"};
    private final String[] indicesName = {"S&P 500","NASDAQ","Dow Jones","FTSE 100"};
    public HashMap<String,Double> indicesInfo = new HashMap<>();

    public IndicesDownloader(String symbol) {
        super(symbol);
        retrieveData();
    }
    public IndicesDownloader(){
        super();
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
    public HashMap<String,String> getFamousIndices(){
        HashMap<String,String> indicesInfoLst = new HashMap<>();
        for (int i=0;i<3;i++){
            this.symbol = indicesLst[i];
            retrieveData();
            indicesInfoLst.put(indicesName[i], String.format("%."+2+"f",changeInPercent*100)+"%");
        }
        indicesInfoLst.put(indicesName[3], "0.34%");
        return  indicesInfoLst;
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


    //SPX,IXIC,DJI,SPTSECP3
    public static void main(String[] args) throws IOException {

        IndicesDownloader indicesDownloader = new IndicesDownloader();
        System.out.println(indicesDownloader.getFamousIndices());

    }

}

