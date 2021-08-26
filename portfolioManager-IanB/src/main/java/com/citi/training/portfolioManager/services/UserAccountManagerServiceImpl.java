package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import com.citi.training.portfolioManager.repo.AccountRepository;
import com.citi.training.portfolioManager.repo.UserRepository;
import com.citi.training.portfolioManager.services.marketData.ExchangeRateDownloader;
import com.citi.training.portfolioManager.strategy.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static org.apache.http.client.utils.DateUtils.parseDate;

@Service
public class UserAccountManagerServiceImpl implements UserAccountManagerService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountActivityRepo;

    @Override
    public Collection<User> getUsers() {
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
        if (cash > accountActivity.getCashValue()) return null;
        accountActivity.withdraw(cash);
        return accountActivity.getCashValue();
    }

    @Override
    public Collection<AccountActivity> getAccountActivity() {
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
            default:
                int startMonth = DateTimeFormatter.getLastQuarterStartMonth();
                accountActivities = new ArrayList<>();
                int timeLength = 3;
                if (range.equals("yearToDate")) {
                    startMonth = 1;
                    timeLength = LocalDate.now().getMonthValue();
                }

                for (int i = 0; i < timeLength; i++) {

                    List<AccountActivity> temp = accountActivityRepo.getAccountActivitiesByYearAndMonth(startMonth + i, 2021);
                    accountActivities.add(temp.get(temp.size() - 1));
                }
                break;
        }
        System.out.println(accountActivities);
        return accountActivities;

    }


    private List<HashMap<String, Object>> getAccountInfoByRange(
            String type, String range) {
        Collection<AccountActivity> accountActivities = getAccountActivityByRange(range);
        List<HashMap<String, Object>> accountInfo = new ArrayList<>();
        for (AccountActivity ac : accountActivities) {
            HashMap<String, Object> info = new HashMap<>();
            String dates = DateTimeFormatter.convertDateToLocalDate(ac.getDate()).toString();
            info.put("name", DateTimeFormatter.formatLocalDate(dates));
            switch (type) {
                case "cashValue":
                    info.put("value", ac.getCashValue());
                    break;
                case "totalEquity":
                    info.put("value", ac.getTotalEquity());
                    break;
                case "investmentValue":
                    info.put("value", ac.getInvestmentValue());
                    break;
                case "netWorth":
                    info.put("value", ac.getNetWorth());
                    break;
            }
            accountInfo.add(info);

        }
        return accountInfo;


    }

    @Override
    public List<HashMap<String, Object>> getNetWorthByRange(String range) {
        return getAccountInfoByRange("netWorth", range);
    }

    @Override
    public List<HashMap<String, Object>> getCashValueByRange(String range) {
        return getAccountInfoByRange("cashValue", range);
    }

    @Override
    public List<HashMap<String, Object>> getInvestmentValueByRange(String range) {
        return getAccountInfoByRange("investmentValue", range);
    }

    @Override
    public List<HashMap<String, Object>> getTotalEquityByRange(String range) {
        return getAccountInfoByRange("totalEquity", range);
    }


    @Override
    public List<HashMap<String, Object>> getTodayAccountBalance(Integer userId) {
        User user = userRepository.getById(1);
        ExchangeRateDownloader ex = new ExchangeRateDownloader("CAD/USD");
        try {
            ex.retrieveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Double rate = ex.getExchangeRate();
        AccountActivity ac = user.getTodayAccountActivity();
        List<HashMap<String, Object>> balances = new ArrayList<>();
        HashMap<String, Object> balanceEntry = new HashMap<>();
        String[] strings = {"USD only", "Combined in CAD"};
        balanceEntry.put("currency", "CAD only");
        balanceEntry.put("cash", 0.0);
        balanceEntry.put("marketValue", 0.0);
        balanceEntry.put("totalEquity", 0.0);

        balances.add(balanceEntry);
        for (String st : strings) {
            balanceEntry = new HashMap<>();
           double cash = st.equals("USD only") ? ac.getCashValue() : (ac.getCashValue() * rate);
            double marketValue = st.equals("USD only") ? ac.getInvestmentValue() : (ac.getInvestmentValue() * rate);
            double totalEquity =  st.equals("USD only") ? ac.getTotalEquity() : (ac.getTotalEquity() * rate);
            balanceEntry.put("currency", st);
            balanceEntry.put("cash", (float)(cash));
            balanceEntry.put("marketValue", (float)marketValue);
            balanceEntry.put("totalEquity", (float)totalEquity);
            balances.add(balanceEntry);
        }

        return balances;
    }


}
