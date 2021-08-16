package com.citi.training.portofolioManagerIanB.services.yahooFinanceDownloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * A class that use YahooFinance to download a Daily Market Movers
 * */
public class MarkerMovers extends Downloader {
    private HashMap<Integer, String> gainers;
    private HashMap<Integer, String> losers;
    public MarkerMovers(String host, String api_host, String api_key){
        super(host,api_host,api_key);
        gainers=new HashMap<>();
        losers = new HashMap<>();
    }

    @Override
    public void retrieveData(String query) throws UnirestException {
        super.downloadData(query);
        JsonArray k = JsonParser.parseString(data)
                .getAsJsonObject().get("finance").getAsJsonObject()
                .get("result").getAsJsonArray();
        ArrayList<HashMap<Integer, String>> marketMovers = new ArrayList<>();
        marketMovers.add(gainers);
        marketMovers.add(losers);
        for (int i =0;i<2;i++){
            JsonArray movers =  k.get(i).getAsJsonObject().get("quotes").getAsJsonArray();
            for (int j=0;j<5;j++){
                String symbol = movers.get(j).getAsJsonObject().get("symbol").toString();
                marketMovers.get(i).put(j,symbol);
            }
        }

    }

    public HashMap<Integer, String> getGainers() {
        return gainers;
    }

    public HashMap<Integer, String> getLosers() {
        return losers;
    }

    public static void main(String[] args) throws Exception {

        String host = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-movers";

        String x_rapidapi_key = "bdd16c27d4mshd5948dee3978ee3p1ea11fjsncc40f6d4e8ed";
        String x_rapidapi_host = "apidojo-yahoo-finance-v1.p.rapidapi.com";
        MarkerMovers s = new MarkerMovers(host, x_rapidapi_host, x_rapidapi_key);
        s.retrieveData("region=US&lang=en-US&count=5&start=0");
        System.out.println(s.getGainers());
        System.out.println(s.getLosers());

    }



}
