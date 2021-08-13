package com.citi.training.portofolioManagerIanB.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    @JoinColumn(name = "account_activity_id", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<AccountActivity> accountActivity;


    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Investments> investments = new ArrayList<>();

    public List<Investments> getInvestments() {
        return investments;
    }

    //Constructor
    public User(String name) {
        this.name = name;

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
