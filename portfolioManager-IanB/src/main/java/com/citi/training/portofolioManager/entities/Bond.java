package com.citi.training.portofolioManager.entities;

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
    @Column(name="costPerShare")  Double costPerShare;
    @Column(name="marketPrice")  Double marketPrice;
    @Column(name="marketValue")  Double marketValue;
    @Column(name="profitAndLoss")  Double profitNLoss;
    @Column(name="percentRetained")  Double percentRetained;
    @Column(name="percentPort")  Double percentInPort;
    @Column(name="portfolioId")  Integer portfolioId;
    public Bond(String ticker, Double quantity, Double buyInPrice, Double marketPrice){
        super(ticker,quantity,buyInPrice,marketPrice);
    }

}
