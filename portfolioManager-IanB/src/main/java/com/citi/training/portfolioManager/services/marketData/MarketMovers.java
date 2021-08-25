package com.citi.training.portfolioManager.services.marketData;

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
    public void retrieveData() {

        try {
            super.downloadFromYahoo();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        JsonArray k = JsonParser.parseString(data)
                .getAsJsonObject().get("finance").getAsJsonObject()
                .get("result").getAsJsonArray();
        ArrayList<HashMap<Integer, String>> marketMovers = new ArrayList<>();
        marketMovers.add(gainers);
        marketMovers.add(losers);
        for (int i = 0; i < 2; i++) {
            JsonArray movers = k.get(i).getAsJsonObject().get("quotes").getAsJsonArray();
            for (int j = 0; j < 5; j++) {
                String symbol = movers.get(j).getAsJsonObject().get("symbol").toString();
                marketMovers.get(i).put(j, symbol);
            }
        }

    }

    public HashMap<Integer, String> getGainers() {
        return gainers;
    }

    public HashMap<Integer, String> getLosers() {
        return losers;
    }

    public static void main(String[] args) {

        MarketMovers s = new MarketMovers();
        s.retrieveData();
        System.out.println(s.getGainers());
        System.out.println(s.getLosers());
    }


}