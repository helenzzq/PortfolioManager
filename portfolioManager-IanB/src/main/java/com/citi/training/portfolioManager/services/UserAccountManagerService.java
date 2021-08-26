package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface UserAccountManagerService {

    Collection<User> getUsers();

    Double deposit(Double cash, Integer userId);

    Double withdraw(Double cash, Integer userId);

    Collection<AccountActivity> getAccountActivity();

    void deleteAccountActivity(Date date);

    Collection<AccountActivity> getAccountActivityByRange(String range);

    List<Double> getNetWorthByRange(String range);

    List<Double> getCashValueByRange(String range);

    List<Double> getInvestmentValueByRange(String range);

    List<Double> getTotalEquityByRange(String range);
}
