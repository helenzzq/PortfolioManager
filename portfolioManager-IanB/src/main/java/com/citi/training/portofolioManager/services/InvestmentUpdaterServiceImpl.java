package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.repo.PortfolioManagerRepository;
import com.citi.training.portofolioManager.services.marketData.MarkerMovers;
import com.citi.training.portofolioManager.services.marketData.StockDownloader;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InvestmentUpdaterServiceImpl implements InvestmentsUpdaterServices {
    @Autowired
    private PortfolioManagerRepository portfolioManagerRepository;

    private MarkerMovers marketMovers;
    private StockDownloader stockDownloader;

//    public void initDownloader() throws Exception {

//
//
//        String MKT_MOVER_QUERY = "region=US&lang=en-US&count=5&start=0";
//
//        marketMovers = new MarkerMovers(HOST,MKT_MOVER_QUERY);
//        marketMovers.retrieveData();
//    }

    public HashMap<Integer, String> getDailyGainers() {
        return marketMovers.getGainers();
    }

    public HashMap<Integer, String> getDailyLosers() {
        return marketMovers.getLosers();
    }

    public Double getStockPrice(String symbol){
        String HOST = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary";
        StockDownloader stockDownloader = new StockDownloader(HOST, symbol);
        return stockDownloader.getPrice();
    }
}
