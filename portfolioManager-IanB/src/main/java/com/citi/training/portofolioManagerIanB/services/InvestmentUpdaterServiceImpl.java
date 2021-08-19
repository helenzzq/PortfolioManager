package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerRepository;
import com.citi.training.portofolioManagerIanB.services.yahooFinanceDownloader.MarkerMovers;
import com.citi.training.portofolioManagerIanB.services.yahooFinanceDownloader.StockDownloader;
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

    public void initDownloader() throws Exception {
        String HOST = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary";
        String API_KEY = "bdd16c27d4mshd5948dee3978ee3p1ea11fjsncc40f6d4e8ed";
        String API_HOST = "apidojo-yahoo-finance-v1.p.rapidapi.com";
        String MKT_MOVER_QUERY = "region=US&lang=en-US&count=5&start=0";
        stockDownloader = new StockDownloader(HOST, API_HOST, API_KEY);
        marketMovers = new MarkerMovers(HOST, API_HOST, API_KEY);
        marketMovers.retrieveData(MKT_MOVER_QUERY);
    }

    public HashMap<Integer, String> getDailyGainers() {
        return marketMovers.getGainers();
    }

    public HashMap<Integer, String> getDailyLosers() {
        return marketMovers.getLosers();
    }

    public Double getStockPrice(String symbol) throws UnirestException {
        stockDownloader.retrieveData(symbol);
        return stockDownloader.getPrice();
    }
}
