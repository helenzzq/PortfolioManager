package com.citi.training.portofolioManagerIanB.entities;

import javax.persistence.*;
import java.io.Serializable;

// add an annotations specifying the table that this will map to
@Entity
@Table(name="stock")
public class Stock extends Investments implements Serializable {
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

    public Stock(String ticker,Double quantity,Double buyInPrice, Double marketPrice){
        super(ticker,quantity,buyInPrice,marketPrice);
    }

}
