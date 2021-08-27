package com.citi.training.portfolioManager.entities;

import com.citi.training.portfolioManager.entities.investments.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An Entity Class that is mapped to the "user" table in database
 */
@Entity
@Table(name = "user")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "accountActivityId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = AccountActivity.class)
    private List<AccountActivity> accountActivity = new ArrayList<>();


    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Stock.class)
    private List<Investment> stocks = new ArrayList<>();

    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Bond.class)
    private List<Investment> bonds = new ArrayList<>();

    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Etf.class)
    private List<Investment> etf = new ArrayList<>();

    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Future.class)
    private List<Investment> future = new ArrayList<>();
    @Transient
    private HashMap<String, List<Investment>> investment = new HashMap<>();

    //Constructor
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {


    }

    public HashMap<String, List<Investment>> getInvestment() {
        investment.put("Stock", stocks);
        investment.put("Bond", bonds);
        investment.put("ETF", etf);
        investment.put("Future", future);

        return investment;
    }

    public AccountActivity getTodayAccountActivity() {
        if (accountActivity.size() != 0) {
            return accountActivity.get(accountActivity.size() - 1);
        }
        return null;
    }


    /**
     * Getter And Setter For attributes
     */
    public List<Investment> getStocks() {
        return stocks;
    }

    public List<Investment> getBonds() {
        return bonds;
    }

    public List<Investment> getEtf() {
        return etf;
    }

    public List<Investment> getFuture() {
        return future;
    }


    public List<AccountActivity> getAccountActivity() {
        return accountActivity;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
