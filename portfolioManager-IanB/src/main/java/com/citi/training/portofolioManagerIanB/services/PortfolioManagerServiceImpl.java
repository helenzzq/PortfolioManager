package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;

public class PortfolioManagerServiceImpl implements PortfolioManagerService {
	@Autowired
    private PortfolioManagerRepository portfolioManagerRepository;

    @Override
    public Collection<Investments> getAllInvestments() {
    return portfolioManagerRepository.findAll();
    }

}
