package com.citi.training.portfolioManager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


// add an annotations specifying the table that this will map to
@Entity
@Table(name = "account_activity")
public class AccountActivity implements Serializable {
    @Id
    @Column(name = "date")
    private Date date;
    @Column(name = "accountActivityId")
    private Integer accountActivityId;
    @Column(name = "netWorth")
    private Double netWorth;
    @Column(name = "cashValue")
    private Double cashValue;
    @Column(name = "investmentValue")
    private Double investmentValue;
    @Column(name = "totalEquity")
    private Double totalEquity;


    public AccountActivity(Date date, Double netWorth, Double cashValue, Double investmentValue, Double totalEquity) {
        this.date = date;
        this.netWorth = netWorth;
        this.cashValue = cashValue;
        this.investmentValue = investmentValue;
        this.totalEquity = totalEquity;
    }

    public AccountActivity() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    public Double getCashValue() {
        return cashValue;
    }

    public void setCashValue(Double cashValue) {
        this.cashValue = cashValue;
    }

    public void deposit(Double cashValue) {
        this.cashValue += cashValue;
    }

    public void withdraw(Double cashValue) {
        this.cashValue += cashValue;
    }

    public Double getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(Double investmentValue) {
        this.investmentValue = investmentValue;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }
}



