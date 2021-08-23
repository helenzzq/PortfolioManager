package com.citi.training.portofolioManager.rest;

import com.citi.training.portofolioManager.entities.AccountActivity;
import com.citi.training.portofolioManager.entities.User;
import com.citi.training.portofolioManager.services.InvestmentsUpdaterServices;
import com.citi.training.portofolioManager.services.PortfolioManagerService;
import com.citi.training.portofolioManager.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserAccountManagerController {
    @Autowired
    private PortfolioManagerService portfolioManagerService;


    @Autowired
    private UserManagerService userManagerService;
    /**
     * GET Requests
     **/

    @GetMapping("/user")
    public Collection<User> getAllUser() {
        return userManagerService.getUsers();
    }


}
