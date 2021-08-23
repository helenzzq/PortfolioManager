package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.entities.AccountActivity;
import com.citi.training.portofolioManager.entities.User;

import java.util.Collection;
import java.util.Date;

public interface UserManagerService {

    Collection<User> getUsers();

    Double deposit(Double cash, Integer userId);

    Double withdraw(Double cash, Integer userId);

    Collection<AccountActivity> getAccountActivity();
    void deleteAccountActivity(Date date);

}
