package com.citi.training.portofolioManagerIanB.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

// add an annotations specifying the table that this will map to
@Entity
@Table(name="bond")
public class Bond extends Investments implements Serializable {
    @Id
    @Column(name="ticker") String ticker;
    @Column(name="currency")  String currency;
    @Column(name="quantity")  Double quantity;
    @Column(name="cost_per_share")  Double costPerShare;
    @Column(name="market_price")  Double marketPrice;
    @Column(name="market_value")  Double marketValue;
    @Column(name="profit_and_loss")  Double profitNLoss;
    @Column(name="percent_retained")  Double percentRetained;
    @Column(name="percent_port")  Double percentInPort;
    @Column(name="portfolio_id")  Integer portfolioId;
    public Bond(String ticker, Double quantity, Double buyInPrice, Double marketPrice){
        super(ticker,quantity,buyInPrice,marketPrice);
    }

}
