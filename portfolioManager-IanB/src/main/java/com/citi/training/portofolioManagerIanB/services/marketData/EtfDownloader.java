package com.citi.training.portofolioManagerIanB.services.marketData;


import java.io.IOException;


public class EtfDownloader extends MarketDownloader {
    private Double price;

    EtfDownloader(String symbol) {
        super(symbol);
        try {
            retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void retrieveData() throws IOException {
        price = super.downloadTimeSeriesFromTwelveData("1min");
    }

    public Double getPrice() {
        return price;
    }

    public static void main(String[] args) {
        EtfDownloader etfDownloader = new EtfDownloader("VTI");
        System.out.println(etfDownloader.getPrice());

    }

}
