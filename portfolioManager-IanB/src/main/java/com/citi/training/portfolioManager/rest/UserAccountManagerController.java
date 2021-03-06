package com.citi.training.portfolioManager.rest;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import com.citi.training.portfolioManager.services.PortfolioManagerService;
import com.citi.training.portfolioManager.services.UserAccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin // allows requests from all domains
public class UserAccountManagerController {


    @Autowired
    private UserAccountManagerService userAccountManagerService;
    @Autowired
    private PortfolioManagerService portfolioManagerService;

    /**
     * GET Requests
     **/

    @GetMapping()
    public Collection<User> getAllUser() {
        return userAccountManagerService.getUsers();
    }


    @GetMapping("/account")
    public Collection<AccountActivity> getAllActivity() {
        return userAccountManagerService.getAccountActivity();
    }


    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter,yearToDate]
     * @Return A list of all account Activity within the time range
     * */
    @GetMapping("/account/range={range}")
    public Collection<AccountActivity> getAccountActivityByRange(@PathVariable("range") String range) {
        return userAccountManagerService.getAccountActivityByRange(range);
    }

    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter,yearToDate]
     * @Return A list of all net Worth within the range
     * */
    @GetMapping("/account/net-worth/range={range}")
    public List<HashMap<String, Object>> getNetWorthByRange(@PathVariable("range") String range) {
        return userAccountManagerService.getNetWorthByRange(range);
    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter,yearToDate]
     * @Return  A list of all cash Value balance within the time range
     * */
    @GetMapping("/account/cash/range={range}")
    public List<HashMap<String, Object>> getCashValueByRange(@PathVariable("range") String range) {
        return userAccountManagerService.getCashValueByRange(range);
    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter,yearToDate]
     * @Return A list of all market Value balance within the time range
     * */
    @GetMapping("/account/market-value/range={range}")
    public List<HashMap<String, Object>> getMarketValueByRange(@PathVariable("range") String range) {
        return userAccountManagerService.getInvestmentValueByRange(range);
    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter,yearToDate]
     * @Return A list of all total Equity balance within the time range
     * */
    @GetMapping("/account/total-equity/range={range}")
    public List<HashMap<String, Object>> getTotalEquityByRange(@PathVariable("range") String range) {
        return userAccountManagerService.getTotalEquityByRange(range);
    }

    @GetMapping("/account/balance/userid={id}")
    public List<HashMap<String, Object>> getTodayAccountBalance(@PathVariable("id") int id) {
        return userAccountManagerService.getTodayAccountBalance(id);
    }
    /*
     * Path Variable date is in dd-mm-yyyy format
     * */
    @PostMapping("/delete-account-activity/{date}")
    public void deleteAccountActivity(@PathVariable("date") String date) throws ParseException {
        Date dates = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        userAccountManagerService.deleteAccountActivity(dates);
    }

}