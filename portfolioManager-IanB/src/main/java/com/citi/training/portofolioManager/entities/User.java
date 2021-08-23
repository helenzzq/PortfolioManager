package com.citi.training.portofolioManager.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "user")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // add attributes for all the remaining properties
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "accountActivityId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<AccountActivity> accountActivity;


    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},targetEntity = Stock.class)
    private List<Investment> stocks = new ArrayList<>();

//    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private List<Investment> bonds = new ArrayList<>();
//
//    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private List<Investment> etf = new ArrayList<>();
//
//    @JoinColumn(name = "portfolioId", referencedColumnName = "id")
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private List<Investment> future = new ArrayList<>();
//

    public HashMap<String, List<Investment>> getInvestment() {
        HashMap<String, List<Investment>> Investment = new HashMap<>();
        Investment.put("Stock",  stocks);
//        Investment.put("Bond",  bonds);
//        Investment.put("ETF",  etf);
//        Investment.put("Future",future);


        return Investment;
    }

    //Constructor
    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public AccountActivity getTodayAccountActivity() {
        return accountActivity.get(accountActivity.size() - 1);
    }

    public void setAccountActivity(List<AccountActivity> accountActivity) {
        this.accountActivity = accountActivity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AccountActivity> getAccountActivity() {
        return accountActivity;
    }
    //Setter and getter for methods
    //Id is fixed and cannot be reset.

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
