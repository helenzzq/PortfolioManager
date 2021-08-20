package com.citi.training.portofolioManager.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Investments {
    @Id
    String ticker;
    String currency;
    Double quantity;
    Double costPerShare;
    Double marketPrice;
    Double marketValue;
    Double profitNLoss;
    Double percentRetained;
    Double percentInPort;
    Integer portfolioId;

    public Investments(String ticker, Double quantity, Double buyInPrice, Double marketPrice) {
        this.ticker = ticker;
        this.marketPrice = marketPrice;
        this.quantity = quantity;
        this.costPerShare = buyInPrice;
        this.marketValue = marketPrice * quantity;
        this.profitNLoss = 0.0;
        this.percentRetained = 0.0;
        this.percentInPort = 0.0;
    }

    public Investments() {

    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public Double getMarketValue() {
        return marketValue;
    }


    public Double getCostPerShare() {
        return costPerShare;
    }


    public Double getMarketPrice() {
        return marketPrice;
    }


    public Double getPercentInPort() {
        return percentInPort;
    }


    public Double getPercentRetained() {
        return percentRetained;
    }


    public Double getProfitNLoss() {
        return profitNLoss;
    }


    public Double getQuantity() {
        return quantity;
    }


    public String getTicker() {
        return ticker;
    }


    public String getCurrency() {
        return currency;
    }


    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }


    public void setCostPerShare(Double costPerShare) {
        this.costPerShare = costPerShare;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
        this.marketValue = marketPrice * quantity;
        this.profitNLoss = (marketPrice - costPerShare) * quantity;
        this.percentRetained = costPerShare/marketPrice -1;
    }
    public void buyInvestment(Double quantity){
        this.quantity += quantity;
    }
    public void sellInvestment(Double quantity){
        this.quantity -= quantity;
    }


    public void setPercentInPort(Double percentInPort) {
        this.percentInPort = percentInPort;
    }


    public void setPercentRetained(Double percentRetained) {
        this.percentRetained = percentRetained;
    }


    public void setProfitNLoss(Double profitNLoss) {
        this.profitNLoss = profitNLoss;
    }


    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
