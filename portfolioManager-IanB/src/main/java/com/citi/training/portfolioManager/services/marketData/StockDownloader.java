package com.citi.training.portfolioManager.services.marketData;

import com.google.gson.JsonParser;
import com.mashape.unirest.http.exceptions.UnirestException;

/*
 * A class that use YahooFinance to download a single stock data
 * */
public class StockDownloader extends MarketDownloader {
    private Double price;

    public StockDownloader(String symbol) {
        super("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary", symbol);
        this.symbol = "symbol=" + symbol + "&region=US";
        this.price = 0.0;

    }

    public static void main(String[] args) throws Exception {

        String host = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary";
        StockDownloader s = new StockDownloader( "AMRN");
        s.downloadFromYahoo();
        System.out.println(s.getPrice());

    }

    //Retrieve the stock data by quote ticker
    @Override
    public void retrieveData() throws UnirestException {


        super.downloadFromYahoo();

        price = JsonParser.parseString(data)
                .getAsJsonObject().get("price").getAsJsonObject()
                .get("regularMarketPrice").getAsJsonObject()
                .get("raw").getAsDouble();

    }

    public Double getPrice() {
        return price;
    }


}