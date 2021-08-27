package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.investments.*;

import java.util.*;

public interface PortfolioManagerService {

    List<HashMap<String, Object>> getInvestmentPercentage(Integer userId);

    Double getTodayNetWorth();

    Double getInvestmentValue(String type, String ticker);

    /* buy and sell investment return its current quantity after buying/selling
     * returns null if failed */
    Double buyInvestment(String type, String ticker, Double quantity);

    Integer sellInvestment(String type, String ticker, Double quantity);

    Collection<Stock> getStocks();

    Collection<Bond> getBonds();

    Collection<Etf> getEtf();

    Collection<Future> getFuture();

    HashMap<String, List<Investment>> getAllInvestment(Integer userId);


}




