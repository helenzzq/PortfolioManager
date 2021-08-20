package com.citi.training.portofolioManagerIanB.services.marketData;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class MarketDownloader {
    String host;
    String data;
    String symbol;

    MarketDownloader(String host, String symbol) {
        this.host = host;
        this.data = "";
        this.symbol = symbol;
    }

    MarketDownloader(String symbol) {
        this.symbol = symbol;
        this.data = "";
    }

    Double downloadTimeSeriesFromTwelveData(String interval) throws IOException {
        this.host = "https://api.twelvedata.com/time_series";
        String apiKey = "aadf3ea56e98461492e8c3fba24b7830";
        URL url = new URL(host + "?symbol=" + symbol + "&interval=" + interval + "&apikey=" + apiKey);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            data = reader.readLine();
            JsonArray arr = JsonParser.parseString(data)
                    .getAsJsonObject().get("values").getAsJsonArray();
            return arr.get(0).getAsJsonObject().get("open").getAsDouble();
        }


    }

    void downloadFromYahoo() throws UnirestException {
        String API_HOST = "apidojo-yahoo-finance-v1.p.rapidapi.com";
        String API_KEY = "bdd16c27d4mshd5948dee3978ee3p1ea11fjsncc40f6d4e8ed";
        data = Unirest.get(host + "?" + symbol)
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .asString().getBody();

    }

    abstract void retrieveData() throws IOException, UnirestException;
}
