package com.citi.training.portfolioManager.rest;

import com.citi.training.portfolioManager.entities.*;
import com.citi.training.portfolioManager.services.MarketUpdaterServices;
import com.citi.training.portfolioManager.services.PortfolioManagerService;
import com.citi.training.portfolioManager.services.UserManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private UserManagerService userManagerService;

    /**
     * GET Requests
     **/

    @GetMapping("/investments/userId={id}")
    public HashMap<String, List<Investment>> getAllInvestments(@PathVariable("id") Integer userId) {
        return portfolioManagerService.getAllInvestment(userId);
    }

    @GetMapping("/net-worth/today")
    public Double getTodayNetWorth() {
        return portfolioManagerService.getTodayNetWorth();
    }

    /**
    * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter]
     * @Return A list of all net Worth within the range
    * */
    @GetMapping("/net-worth/range={range}")
    public List<Double> getNetWorthByRange(@PathVariable("range") String range) {
        return portfolioManagerService.getNetWorthByRange(range);
    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter]
     * @Return  A list of all cash Value balance within the time range
     * */
    @GetMapping("/cash/range={range}")
    public List<Double> getCashValueByRange(@PathVariable("range") String range) {
        return portfolioManagerService.getCashValueByRange(range);
    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter]
     * @Return A list of all market Value balance within the time range
     * */
    @GetMapping("/market-value/range={range}")
    public List<Double> getMarketValueByRange(@PathVariable("range") String range) {
        return portfolioManagerService.getInvestmentValueByRange(range);
    }
    /**
     * @Param range: the range that user want to filter, should be in [lastMonth,lastWeek,lastQuarter]
     * @Return A list of all total Equity balance within the time range
     * */
    @GetMapping("/total-equity/range={range}")
    public List<Double> getTotalEquityByRange(@PathVariable("range") String range) {
        return portfolioManagerService.getTotalEquityByRange(range);
    }


    @GetMapping("/investment/type={type}&ticker={ticker}")
    public Double getInvestmentValue(@PathVariable("type") String type, @PathVariable("ticker") String ticker) {
        return portfolioManagerService.getInvestmentValue(type, ticker);
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

    @GetMapping("/indices/indices={symbol}")
    public HashMap<String, Double> getSingleIndices(@PathVariable("symbol") String symbol) {
        return marketUpdaterServices.getIndicesInfo(symbol);
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
