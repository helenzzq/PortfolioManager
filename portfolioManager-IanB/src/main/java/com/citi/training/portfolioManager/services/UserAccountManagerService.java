package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface UserAccountManagerService {

    Collection<User> getUsers();

    Double deposit(Double cash, Integer userId);

    Double withdraw(Double cash, Integer userId);

    Collection<AccountActivity> getAccountActivity();


    void deleteAccountActivity(Date date);

    Collection<AccountActivity> getAccountActivityByRange(String range);

    List<HashMap<String, Object>> getNetWorthByRange(String range);

    List<HashMap<String, Object>> getCashValueByRange(String range);

    List<HashMap<String, Object>> getInvestmentValueByRange(String range);

    List<HashMap<String, Object>> getTotalEquityByRange(String range);

    List<HashMap<String,Object>> getTodayAccountBalance(Integer userId);
}
