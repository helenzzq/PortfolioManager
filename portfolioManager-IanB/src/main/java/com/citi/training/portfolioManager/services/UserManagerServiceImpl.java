package com.citi.training.portfolioManager.services;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import com.citi.training.portfolioManager.repo.AccountRepository;
import com.citi.training.portfolioManager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

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

}
