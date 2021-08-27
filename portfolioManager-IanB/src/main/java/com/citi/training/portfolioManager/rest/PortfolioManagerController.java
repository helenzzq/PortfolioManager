package com.citi.training.portfolioManager.rest;

import com.citi.training.portfolioManager.entities.*;
import com.citi.training.portfolioManager.services.MarketUpdaterServices;
import com.citi.training.portfolioManager.services.PortfolioManagerService;
import com.citi.training.portfolioManager.services.UserAccountManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/portfolio-manager")
@CrossOrigin // allows requests from all domains
public class PortfolioManagerController {

    @Autowired
    private PortfolioManagerService portfolioManagerService ;

    @Autowired
    private MarketUpdaterServices marketUpdaterServices;
    @Autowired
    private UserAccountManagerService userAccountManagerService;

    /**
     * GET Requests
     **/

    @GetMapping("/investments/userId={id}")
    public HashMap<String, List<Investment>> getAllInvestments(@PathVariable("id") Integer userId) {
        return portfolioManagerService.getAllInvestment(userId);
    }

    @GetMapping("/exchange-rate/USD-CAD")
    public Double getUSDToCADExchangeRate(){
       return  marketUpdaterServices.getExchangeRateBySymbol("USD","CAD");

    }

    @GetMapping("/net-worth/today")
    public Double getTodayNetWorth() {
        return portfolioManagerService.getTodayNetWorth();
    }

    @GetMapping("/investment/type={type}&ticker={ticker}")
    public Double getInvestmentValue(@PathVariable("type") String type, @PathVariable("ticker") String ticker) {
        return portfolioManagerService.getInvestmentValue(type, ticker);
    }
    @GetMapping("/investments/portfolio-percentage/userId={id}")
    public List<HashMap<String, Object>> getInvestmentPercentage(@PathVariable("id") Integer id) {
        return portfolioManagerService.getInvestmentPercentage(id);
    }
    @GetMapping("/stocks")
    public Collection<Stock> getAllStocks(){
        return portfolioManagerService.getStocks();
    }
    @GetMapping("/etfs")
    public Collection<Etf> getAllEtfs(){
        return portfolioManagerService.getEtf();
    }
    @GetMapping("/future")
    public Collection<Future> getAllFuture(){
        return portfolioManagerService.getFuture();
    }
    @GetMapping("/bonds")
    public Collection<Bond> getAllBonds(){
        return portfolioManagerService.getBonds();
    }
    @GetMapping("/gainers")
    public HashMap<Integer, String> getGainers() {
        return marketUpdaterServices.getDailyGainers();
    }

    @GetMapping("/losers")
    public HashMap<Integer, String> getLosers() {
        return marketUpdaterServices.getDailyLosers();
    }

    @GetMapping("/indices/famous-indices")
    public HashMap<String, String> getFamousIndices() {
        return marketUpdaterServices.getFamousIndicesInfo();
    }


    /*****************/
    /**POST Requests**/
    /*****************/

    @PostMapping("/deposit/{cash}")
    public void deposit(@PathVariable("cash") double cash) {
        userAccountManagerService.deposit(cash, 1);
    }

    @PostMapping("/withdraw/{cash}")
    public void withdraw(@PathVariable("cash") double cash) {
        Double response = userAccountManagerService.withdraw(cash, 1);
        if (response == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Sorry, there is no enough money in your cash account.");
        }
    }


//    @PostMapping("/buy/{ticker}/{quantity}")
//    public void buyInvestment(@PathVariable("type") String type, @PathVariable("ticker") String ticker, @PathVariable("quantity") Double quantity) throws UnirestException {
//        Double response = portfolioManagerService.buyInvestment(type, ticker, quantity);
//        if (response == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Sorry, there is no enough money in your cash account.");
//        }
//    }
//
//    @PostMapping("/sell/{ticker}/{quantity}")
//    public void sellInvestment(@PathVariable("type") String type, @PathVariable("ticker") String ticker, @PathVariable("quantity") Double quantity) throws UnirestException {
//        Integer response = portfolioManagerService.sellInvestment(type, ticker, quantity);
//        if (response == 404) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Sorry, you don't have this investment.");
//        }
//        else if (response == 406){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_ACCEPTABLE, "Sorry, you don't have enough quantity to sell.");
//        }
//    }

}
