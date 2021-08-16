package com.citi.training.portofolioManagerIanB.services.yahooFinanceDownloader;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public abstract class Downloader {
    String host;
    String api_host;
    String api_key;
    String data;


    Downloader(String host, String api_host, String api_key) {
        this.host = host;
        this.api_host = api_host;
        this.api_key = api_key;

    }

    public String downloadData(String query) throws UnirestException{
        data = Unirest.get(host + "?" + query)
                .header("x-rapidapi-key", api_key)
                .header("x-rapidapi-host", api_host)
                .asString().getBody();
        return data;
    }
    public abstract void retrieveData(String symbol) throws UnirestException;
}
