package com.citi.training.portfolioManager.model.marketDataDownloaders;


import java.io.IOException;


public class EtfDownloader extends MarketDownloader {
    private Double price;

    public EtfDownloader(String symbol) {
        super(symbol);

    }

    @Override
    public void retrieveData() throws IOException {
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
