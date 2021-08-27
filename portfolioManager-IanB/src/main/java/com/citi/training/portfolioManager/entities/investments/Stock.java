package com.citi.training.portfolioManager.entities.investments;

import javax.persistence.*;
import java.io.Serializable;

/**
 * An Entity Class that is mapped to the "Stock" table in database
 *
 * */
@Entity
@Table(name = "stock")
public class Stock implements Serializable, Investment {
    @Id
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "currency")
    private String currency;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "costPerShare")
    private Double costPerShare;
    @Column(name = "marketPrice")
    private Double marketPrice;
    @Column(name = "marketValue")
    private Double marketValue;
    @Column(name = "profitAndLoss")
    private Double profitNLoss;
    @Column(name = "percentRetained")
    private Double percentRetained;
    @Column(name = "percentPort")
    private Double percentInPort;
    @Column(name = "portfolioId")
    private Integer portfolioId;

    public Stock(String ticker, Double quantity, Double buyInPrice, Double marketPrice) {
        this.ticker = ticker;
        this.marketPrice = marketPrice;
        this.quantity = quantity;
        this.costPerShare = buyInPrice;
        this.marketValue = marketPrice * quantity;
        this.profitNLoss = 0.0;
        this.percentRetained = 0.0;
        this.percentInPort = 0.0;
    }

    public Stock() {

    }

    @Override
    public void updateInvestment(Double quantity, Double price, Double marketPrice) {
        this.quantity = quantity;
        this.costPerShare = price;
        this.marketPrice = marketPrice;
        this.profitNLoss = marketPrice - price;
        this.percentRetained = profitNLoss / (quantity * costPerShare);

    }

    /**
     * Getter And Setter For attributes
     */
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getCostPerShare() {
        return costPerShare;
    }

    public void setCostPerShare(Double costPerShare) {
        this.costPerShare = costPerShare;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getProfitNLoss() {
        return profitNLoss;
    }

    public void setProfitNLoss(Double profitNLoss) {
        this.profitNLoss = profitNLoss;
    }

    public Double getPercentRetained() {
        return percentRetained;
    }

    public void setPercentRetained(Double percentRetained) {
        this.percentRetained = percentRetained;
    }

    public Double getPercentInPort() {
        return percentInPort;
    }

    public void setPercentInPort(Double percentInPort) {
        this.percentInPort = percentInPort;
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
    }
}
