package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.services.marketData.EtfDownloader;
import com.citi.training.portfolioManager.services.marketData.MarketMovers;
import com.citi.training.portfolioManager.services.marketData.StockDownloader;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class MarketUpdaterServiceImpl implements MarketUpdaterServices {

    private MarketMovers marketMovers = new MarketMovers();
    private StockDownloader stockDownloader;


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
}
