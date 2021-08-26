package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import com.citi.training.portfolioManager.repo.AccountRepository;
import com.citi.training.portfolioManager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.threeten.extra.YearQuarter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountActivityRepo;
    @Override
    public Collection<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public Double deposit(Double cash, Integer userId) {
        User user = userRepository.getById(userId);
        AccountActivity accountActivity = user.getTodayAccountActivity();
        accountActivity.deposit(cash);
        return accountActivity.getCashValue();
    }

    @Override
    public Double withdraw(Double cash, Integer userId) {
        User user = userRepository.getById(userId);
        AccountActivity accountActivity = user.getTodayAccountActivity();
        // If there's no enough cash in account, withdraw fails.
        if(cash > accountActivity.getCashValue()) return null;
        accountActivity.withdraw(cash);
        return accountActivity.getCashValue();
    }

    @Override
    public Collection<AccountActivity> getAccountActivity(){
        return accountActivityRepo.findAll();
    }

    @Override
    public void deleteAccountActivity(Date date) {
        accountActivityRepo.deleteById(date);
    }

    @Override
    public Collection<AccountActivity> getAccountActivityByRange(String range) {
        LocalDate today = LocalDate.now();
        Collection<AccountActivity> accountActivities = null;
        int month = today.getMonthValue();
        switch (range) {
            case "lastWeek":
                LocalDate date = today.with(DayOfWeek.MONDAY).minusDays(7);
                Date lastMonday = java.sql.Date.valueOf(date);
                Date lastFriday = java.sql.Date.valueOf(date.plusDays(4));
                accountActivities = accountActivityRepo.getAccountActivitiesByInterval(lastMonday, lastFriday);
                break;
            case "lastMonth":
                accountActivities = accountActivityRepo.getAccountActivitiesByYearAndMonth(month - 1, 2021);
                break;
            case "lastQuarter":
                YearQuarter lastQuarter = YearQuarter.now(ZoneId.systemDefault()).minusQuarters(1);
                Date firstDay = java.sql.Date.valueOf(lastQuarter.atDay(1));
                Date lastDayOfLastQuarter = java.sql.Date.valueOf(lastQuarter.atEndOfQuarter());
                accountActivities = accountActivityRepo.getAccountActivitiesByInterval(firstDay, lastDayOfLastQuarter);
                break;
        }
        return accountActivities;

    }
    private HashMap<String, List<Double>> getAccountInfoByRange(String range){
        HashMap<String, List<Double>> accountInfo = new HashMap<>();
        Collection<AccountActivity> accountActivities = getAccountActivityByRange(range);
        List<Double> netWorthLst = new ArrayList<>();
        List<Double> cashLst = new ArrayList<>();
        List<Double> equityLst = new ArrayList<>();
        List<Double> totalEquityLst = new ArrayList<>();
        for (AccountActivity ac : accountActivities){
            netWorthLst.add(ac.getNetWorth());
            cashLst.add(ac.getCashValue());
            equityLst.add(ac.getInvestmentValue());
            totalEquityLst.add(ac.getTotalEquity());
        }
        accountInfo.put("netWorth",netWorthLst);
        accountInfo.put("investmentValue",equityLst);
        accountInfo.put("cashValue",cashLst);
        accountInfo.put("totalEquity",totalEquityLst);
        return accountInfo;
    }
    @Override
    public List<Double> getNetWorthByRange(String range) {
        return getAccountInfoByRange(range).get("netWorth");
    }

    @Override
    public List<Double> getCashValueByRange(String range) {
        return getAccountInfoByRange(range).get("cashValue");
    }

    @Override
    public List<Double> getInvestmentValueByRange(String range) {
        return getAccountInfoByRange(range).get("investmentValue");
    }

    @Override
    public List<Double> getTotalEquityByRange(String range) {
        return getAccountInfoByRange(range).get("totalEquity");
    }

}
