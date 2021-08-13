package com.citi.training.portofolioManagerIanB.rest;

import com.citi.training.portofolioManagerIanB.services.PortfolioManagerService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/portfoliomanager")
@CrossOrigin // allows requests from all domains
public class PortfolioManagerController {
	
	@Autowired
	private PortfolioManagerService portfolioManagerService;
	
	
//	@GetMapping
//	public Collection<Investment> getInvestments() {
//		return null;
//	}


}
