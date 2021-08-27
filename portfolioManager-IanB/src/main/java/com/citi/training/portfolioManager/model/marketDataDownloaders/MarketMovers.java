package com.citi.training.portfolioManager.model.marketDataDownloaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * A class that use YahooFinance to download a Daily Market Movers
 * */
public class MarketMovers extends MarketDownloader {
    private HashMap<Integer, String> gainers = new HashMap<>();
    private HashMap<Integer, String> losers = new HashMap<>();

    public MarketMovers() {
        super("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-movers", "region=US&lang=en-US&count=5&start=0");

    }


    @Override
    public void retrieveData(){

        try {
            super.downloadFromYahoo();



        JsonArray k = JsonParser.parseString(data)
                .getAsJsonObject().get("finance").getAsJsonObject()
                .get("result").getAsJsonArray();
        ArrayList<HashMap<Integer, String>> marketMovers = new ArrayList<>();
        marketMovers.add(gainers);
        marketMovers.add(losers);
        for (int i = 0; i < 2; i++) {
            JsonArray movers = k.get(i).getAsJsonObject().get("quotes").getAsJsonArray();
            for (int j = 0; j < 5; j++) {
                String symbol = movers.get(j).getAsJsonObject().get("symbol").getAsString();
                StockDownloader stockDownloader = new StockDownloader(symbol);
                stockDownloader.downloadFromYahoo();
                 String name= JsonParser.parseString(stockDownloader.data)
                        .getAsJsonObject().get("quoteType").getAsJsonObject()
                        .get("longName").getAsString();
                marketMovers.get(i).put(j, name);
            }
        }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, String> getGainers() {
        return gainers;
    }

    public HashMap<Integer, String> getLosers() {
        return losers;
    }

    public static void main(String[] args) throws UnirestException {

        MarketMovers s = new MarketMovers();
        s.retrieveData();
        System.out.println(s.getGainers());
        System.out.println(s.getLosers());
    }


}
