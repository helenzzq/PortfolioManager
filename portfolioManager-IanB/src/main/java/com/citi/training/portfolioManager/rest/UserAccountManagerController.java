package com.citi.training.portfolioManager.rest;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import com.citi.training.portfolioManager.services.PortfolioManagerService;
import com.citi.training.portfolioManager.services.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@RestController
@RequestMapping("/user")
@CrossOrigin // allows requests from all domains
public class UserAccountManagerController {


    @Autowired
    private UserManagerService userManagerService;
    @Autowired
    private PortfolioManagerService portfolioManagerService;

    /**
     * GET Requests
     **/

    @GetMapping()
    public Collection<User> getAllUser() {
        return userManagerService.getUsers();
    }


    @GetMapping("/account")
    public Collection<AccountActivity> getAllActivity() {
        return userManagerService.getAccountActivity();
    }


//    @PostMapping("/update-networth/{date}")
//    public void deposit(@PathVariable("date") long date) {
//        portfolioManagerService.updateNetWorth(1, new Date(date));
//    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter]
     * @Return A list of all account Activity within the time range
     * */
    @GetMapping("/account/range={range}")
    public Collection<AccountActivity> getAccountActivityByRange(@PathVariable("range") String range) {
        return portfolioManagerService.getAccountActivityByRange(range);
    }

    /*
     * Path Variable date is in dd-mm-yyyy format
     * */
    @PostMapping("/delete-account-activity/{date}")
    public void deleteAccountActivity(@PathVariable("date") String date) throws ParseException {
        Date dates = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        userManagerService.deleteAccountActivity(dates);
    }

}
