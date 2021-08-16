package com.citi.training.portofolioManagerIanB.services.yahooFinanceDownloader;

import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

/*
 * A class that use YahooFinance to download a single stock data
 * */
public class StockDownloader extends Downloader {
    private Double price;

    public StockDownloader(String host, String api_host, String api_key) {
        super(host, api_host, api_key);
        this.price = 0.0;
    }

    public static void main(String[] args) throws Exception {

        String host = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary";

        String x_rapidapi_key = "bdd16c27d4mshd5948dee3978ee3p1ea11fjsncc40f6d4e8ed";
        // Headers for a request
        String x_rapidapi_host = "apidojo-yahoo-finance-v1.p.rapidapi.com";
        StockDownloader s = new StockDownloader(host, x_rapidapi_host, x_rapidapi_key);
        s.downloadData("AMRN");
        System.out.println(s.getPrice());

    }

    //Retrieve the stock data by quote ticker
    @Override
    public void retrieveData(String symbol) throws UnirestException {
        String query = "symbol=" + symbol + "&region=US";
        super.downloadData(query);
        price = JsonParser.parseString(data)
                .getAsJsonObject().get("price").getAsJsonObject()
                .get("regularMarketPrice").getAsJsonObject()
                .get("raw").getAsDouble();

    }

    public Double getPrice() {
        return price;
    }


}