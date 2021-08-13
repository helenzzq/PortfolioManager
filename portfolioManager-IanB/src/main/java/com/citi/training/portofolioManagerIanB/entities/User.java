package com.citi.training.portofolioManagerIanB.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // add attributes for all the remaining properties
    @Column(name = "NAME")
    private String name;
    @Column(name = "CASH")
    private Double cash;
    @Column(name = "MARKET_VALUE")
    private Double marketValue;
    @Column(name = "TOTAL_EQUITY")
    private Double totalEquity;

    public User() {
    }

    //Constructor
    public User(String name) {
        this.name = name;
        this.cash = 0.0;
        this.marketValue = 0.0;
        this.totalEquity = 0.0;

    }

    //Setter and getter for methods
    //Id is fixed and cannot be reset.

    public void setName(String name) {
        this.name = name;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getCash() {
        return cash;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
