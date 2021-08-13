package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import org.springframework.beans.factory.annotation.Autowired;

import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerReposoitory;

import java.util.Collection;

public class PortfolioManagerServiceImpl implements PortfolioManagerService {
	@Autowired
    private PortfolioManagerReposoitory portfolioManagerRepository;

    @Override
    public Collection<Investments> getAllInvestments() {
//    return PortfolioManagerReposoitory.findAll();
        return null;
    }

}
