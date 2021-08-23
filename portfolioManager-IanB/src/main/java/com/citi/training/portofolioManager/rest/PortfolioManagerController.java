package com.citi.training.portofolioManager.rest;

import com.citi.training.portofolioManager.entities.AccountActivity;
import com.citi.training.portofolioManager.entities.Investment;
import com.citi.training.portofolioManager.entities.User;
import com.citi.training.portofolioManager.services.InvestmentsUpdaterServices;
import com.citi.training.portofolioManager.services.PortfolioManagerService;
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
	private PortfolioManagerService portfolioManagerService;

	@Autowired
	private InvestmentsUpdaterServices investmentsUpdaterServices;
	

	/****************/
	/**GET Requests**/
	/****************/

	@GetMapping("/user")
	public Collection<User> getAllUser() {
		return portfolioManagerService.getUsers();
	}
	@GetMapping("/account")
	public Collection<AccountActivity> getAllActivity() {
		return portfolioManagerService.getAccountActivity();
	}

	@GetMapping("/investments")
	public HashMap<String, List<Investment>> getAllInvestments() {
		return portfolioManagerService.getAllInvestment();
	}

	@GetMapping("/networth/{date}")
	public Double getNetWorth(@PathVariable("date") long date) {
		return portfolioManagerService.getNetWorth(new Date(date));
	}

	@GetMapping("/cash/{date}")
	public Double getCashAccountByDate(@PathVariable("date") long date) {
		return portfolioManagerService.getCashAccountByDate(new Date(date));
	}

	@GetMapping("/investment/{ticker}")
	public Double getInvestmentValue(@PathVariable("ticker") String ticker) {
		return portfolioManagerService.getInvestmentValue(ticker);
	}

	@GetMapping("/gainers")
	public HashMap<Integer, String> getGainers() {
		return investmentsUpdaterServices.getDailyGainers();
	}

	@GetMapping("/losers")
	public HashMap<Integer, String> getLosers() {
		return investmentsUpdaterServices.getDailyLosers();
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
		portfolioManagerService.deposit(cash, 1);
	}

	@PostMapping("/withdraw/{cash}")
	public void withdraw(@PathVariable("cash") double cash) {
		Double response = portfolioManagerService.withdraw(cash, 1);
		if(response == null) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Sorry, there is no enough money in your cash account.");
		}
	}


	@PostMapping("/buy/{ticker}/{quantity}")
	public void buyInvestment(@PathVariable("ticker") String ticker, @PathVariable("ticker") Double quantity) throws UnirestException {
		Double response = portfolioManagerService.buyInvestment(ticker, quantity);
		if(response == null) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Sorry, there is no enough money in your cash account.");
		}
	}

	@PostMapping("/sell/{ticker}/{quantity}")
	public void sellInvestment(@PathVariable("ticker") String ticker, @PathVariable("ticker") Double quantity) throws UnirestException {
		Double response = portfolioManagerService.sellInvestment(ticker, quantity);
		if(response == null) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Sorry, selling failed.");
		}
	}


	@PostMapping("/update-networth/{date}")
	public void deposit(@PathVariable("date") long date) {
		portfolioManagerService.updateNetWorth(1, new Date(date));
	}

	@PostMapping("/delete-account-activity/{date}")
	public void deleteAccountActivity(@PathVariable("date") long date) {
		portfolioManagerService.deleteAccountActivity(new Date(date));
	}

}
