package com.citi.training.portofolioManager.services;

import com.citi.training.portofolioManager.entities.User;

import java.util.Collection;

public interface UserManagerService {

    Collection<User> getUsers();

    Double deposit(Double cash, Integer userId);

    Double withdraw(Double cash, Integer userId);
}
