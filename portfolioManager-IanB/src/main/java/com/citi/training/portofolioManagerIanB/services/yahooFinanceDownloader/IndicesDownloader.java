package com.citi.training.portofolioManagerIanB.services.yahooFinanceDownloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IndicesDownloader extends Downloader {

    private Double changeInPercent;
    private double currPrice;
    private double openPrice;

    public IndicesDownloader(String host, String api_host, String api_key) {
        super(host, api_host, api_key);
    }

    @Override
    public void retrieveData(String symbol) throws UnirestException {
        try {
            URL url = new URL(host + "?symbol=" + symbol + "&interval=1min&apikey=" + api_key);
            URL url2 = new URL(host + "?symbol=" + symbol + "&interval=1day&apikey=" + api_key);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));

                JsonArray indicesArr = JsonParser.parseString(reader.readLine())
                        .getAsJsonObject().get("values").getAsJsonArray();
                currPrice = indicesArr.get(0).getAsJsonObject().get("open").getAsDouble();
                openPrice = JsonParser.parseString(reader2.readLine())
                        .getAsJsonObject().get("values").getAsJsonArray().get(0).getAsJsonObject().get("open").getAsDouble();
                changeInPercent = (currPrice - openPrice) / openPrice;


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


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

    public static void main(String[] args) throws Exception {
        String host = "https://api.twelvedata.com/time_series";
        String apiKey = "aadf3ea56e98461492e8c3fba24b7830";
        IndicesDownloader indicesDownloader = new IndicesDownloader(host,"",apiKey);
        indicesDownloader.retrieveData("SPX");
        System.out.println("Open Price for SPX is:" + indicesDownloader.getOpenPrice());
        System.out.println("Curr Price for SPX is:" + indicesDownloader.getCurrPrice());
        System.out.println("Change in Percentage for SPX is:" + indicesDownloader.getChangeInPercent());
    }

}

