package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;

public class PortfolioManagerServiceImpl<PortfolioManagerRepository> implements PortfolioManagerService {
	@Autowired
    private PortfolioManagerRepository portfolioManagerRepository;

    @Override
    public Collection<Investments> getAllInvestments() {
//    return PortfolioManagerReposoitory.findAll();
        return null;
    }

}
