package com.citi.training.portfolioManager.model.marketDataDownloaders;

import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ExchangeRateDownloader extends MarketDownloader{
    private Double exchangeRate = 0.0;
    public ExchangeRateDownloader(String symbol){
        super(symbol);

    }
    @Override
    public void retrieveData() throws IOException {
        this.host = "https://api.twelvedata.com/exchange_rate";


        String apiKey = "5e0210e890b64501b5fadda2756fc2c0";
        URL url = new URL(host + "?symbol=" + symbol + "&apikey=" + apiKey);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            data = reader.readLine();

            exchangeRate = JsonParser.parseString(data)
                    .getAsJsonObject().get("rate").getAsDouble();

        }

    }

    public Double getExchangeRate() {
        return exchangeRate;
    }
    public static void main(String[] args) throws IOException {
        ExchangeRateDownloader  ex =new ExchangeRateDownloader("CAD/USD");
        ex.retrieveData();
        System.out.println(ex.getExchangeRate());
    }
}
