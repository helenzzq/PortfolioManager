package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;

import java.util.Collection;
import java.util.Date;

public interface UserManagerService {

    Collection<User> getUsers();

    Double deposit(Double cash, Integer userId);

    Double withdraw(Double cash, Integer userId);

    Collection<AccountActivity> getAccountActivity();
    void deleteAccountActivity(Date date);

}
