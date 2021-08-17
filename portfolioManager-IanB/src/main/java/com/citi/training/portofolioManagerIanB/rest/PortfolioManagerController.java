package com.citi.training.portofolioManagerIanB.rest;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.services.PortfolioManagerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

@RestController
@RequestMapping("/portfoliomanager")
@CrossOrigin // allows requests from all domains
public class PortfolioManagerController {
	
	@Autowired
	private PortfolioManagerService portfolioManagerService;
	
	
	@GetMapping("/investments")
	public Collection<Investments> getAllInvestments() {
		return portfolioManagerService.getAllInvestments();
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
	public List<Investments> getGainers() {
		return portfolioManagerService.calculateTopFiveGainers();
	}

	@GetMapping("/losers")
	public List<Investments> getLosers() {
		return portfolioManagerService.calculateTopFiveLosers();
	}

	@GetMapping("/indices")
	public Dictionary<String, Double> getIndices() {
		return portfolioManagerService.getIndices();
	}



}
