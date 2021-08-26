package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.services.marketData.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class MarketUpdaterServiceImpl implements MarketUpdaterServices {

    private final MarketMovers marketMovers = new MarketMovers();
    private  IndicesDownloader indicesDownloader ;

    public HashMap<Integer, String> getDailyGainers() {

        marketMovers.retrieveData();

        return marketMovers.getGainers();
    }

    public HashMap<Integer, String> getDailyLosers() {

        marketMovers.retrieveData();
        return marketMovers.getLosers();
    }

    public Double getStockPrice(String symbol) throws UnirestException {

        StockDownloader stockDownloader = new StockDownloader(symbol);
        stockDownloader.retrieveData();
        return stockDownloader.getPrice();
    }

    public Double getEtfPrice(String symbol) throws IOException {
        EtfDownloader etfDownloader = new EtfDownloader(symbol);
        etfDownloader.retrieveData();
        return etfDownloader.getPrice();
    }
    private void setUpIndiceDownloader(String symbol){
        if (indicesDownloader == null){
            indicesDownloader = new IndicesDownloader(symbol);
        }
        else {
            indicesDownloader.retrieveData();
        }
    }
    @Override
    public HashMap<String, Double> getIndicesInfo(String symbol){
       indicesDownloader = new IndicesDownloader(symbol);
        return indicesDownloader.getIndicesInfo();
    }
    @Override
    public Double getIndicesOpenPrice(String symbol){
        setUpIndiceDownloader(symbol);
        return indicesDownloader.getOpenPrice();

    }

    @Override
    public Double getIndicesCurrPrice(String symbol) {
        setUpIndiceDownloader(symbol);
        return indicesDownloader.getCurrPrice();
    }


    @Override
    public Double getIndicesChangeInPercent(String symbol) {
        setUpIndiceDownloader(symbol);
        return indicesDownloader.getCurrPrice();

    }

    @Override
    public HashMap<String,String> getFamousIndicesInfo(){
        IndicesDownloader indicesDownloader = new IndicesDownloader();
        return indicesDownloader.getFamousIndices();
    }

    @Override
    public Double getInvestmentPrice(String type, String symbol) {
        try {
            if (type.equals("Stock")) {
                return getStockPrice(symbol);

            } else if (type.equals("Etf")) {
                return getEtfPrice(symbol);
            }
        } catch (UnirestException | IOException e) {
            return 0.0;
        }
        return 0.0;
    }
    @Override
    public Double getExchangeRateBySymbol(String currency, String toCurrency){
        ExchangeRateDownloader ex = new ExchangeRateDownloader(currency+"/"+toCurrency);
        try {
            ex.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ex.getExchangeRate();
    }
}
