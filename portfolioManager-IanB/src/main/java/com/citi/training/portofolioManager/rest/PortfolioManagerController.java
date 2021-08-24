package com.citi.training.portofolioManager.rest;

import com.citi.training.portofolioManager.entities.Investment;
import com.citi.training.portofolioManager.services.MarketUpdaterServices;
import com.citi.training.portofolioManager.services.PortfolioManagerService;
import com.citi.training.portofolioManager.services.PortfolioManagerServiceImpl;
import com.citi.training.portofolioManager.services.UserManagerService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/portfoliomanager")
@CrossOrigin // allows requests from all domains
public class PortfolioManagerController {

    @Autowired
    private PortfolioManagerService portfolioManagerService = new PortfolioManagerServiceImpl();

    @Autowired
    private MarketUpdaterServices marketUpdaterServices;

    @Autowired
    private UserManagerService userManagerService;

    /**
     * GET Requests
     **/


    @GetMapping("/investments")
    public HashMap<String, List<Investment>> getAllInvestments(Integer userId) {
        return portfolioManagerService.getAllInvestment(userId);
    }
//
//    @GetMapping("/networth/{date}")
//    public Double getNetWorth(@PathVariable("date") long date) {
//        return portfolioManagerService.getNetWorth(new Date(date));
//    }
//
//    @GetMapping("/cash/{date}")
//    public Double getCashAccountByDate(@PathVariable("date") long date) {
//        return portfolioManagerService.getCashAccountByDate(new Date(date));
//    }

    @GetMapping("/investment/{ticker}")
    public Double getInvestmentValue(@PathVariable("type") String type, @PathVariable("id") Integer userId, @PathVariable("ticker") String ticker) {
        return portfolioManagerService.getInvestmentValue(type, userId, ticker);
    }

    @GetMapping("/gainers")
    public HashMap<Integer, String> getGainers() {
        return marketUpdaterServices.getDailyGainers();
    }

    @GetMapping("/losers")
    public HashMap<Integer, String> getLosers() {
        return marketUpdaterServices.getDailyLosers();
    }

    @GetMapping("/indices")
    public Dictionary<String, Double> getIndices() {
        return portfolioManagerService.getIndices();
    }

    /*****************/
    /**POST Requests**/
    /*****************/

    @PostMapping("/deposit/{cash}")
    public void deposit(@PathVariable("cash") double cash) {
        userManagerService.deposit(cash, 1);
    }

    @PostMapping("/withdraw/{cash}")
    public void withdraw(@PathVariable("cash") double cash) {
        Double response = userManagerService.withdraw(cash, 1);
        if (response == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sorry, there is no enough money in your cash account.");
        }
    }


    @PostMapping("/buy/{ticker}/{quantity}")
    public void buyInvestment(@PathVariable("type") String type, @PathVariable("ticker") String ticker, @PathVariable("quantity") Double quantity) throws UnirestException {
        Double response = portfolioManagerService.buyInvestment(type, ticker, quantity);
        if (response == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sorry, there is no enough money in your cash account.");
        }
    }

    @PostMapping("/sell/{ticker}/{quantity}")
    public void sellInvestment(@PathVariable("type") String type, @PathVariable("ticker") String ticker, @PathVariable("quantity") Double quantity) throws UnirestException {
        Double response = portfolioManagerService.sellInvestment(type, ticker, quantity);
        if (response == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sorry, selling failed.");
        }
    }

}
