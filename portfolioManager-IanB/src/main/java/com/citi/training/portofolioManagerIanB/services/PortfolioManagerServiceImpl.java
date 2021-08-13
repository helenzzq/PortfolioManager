package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.repo.PortfolioManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collection;
import java.util.Optional;

public class PortfolioManagerServiceImpl implements PortfolioManagerService {
	@Autowired
    private PortfolioManagerRepository portfolioManagerRepository;

    @Override
    public Collection<Investments> getAllInvestments() {
        return portfolioManagerRepository.findAll();
    }

    @Override
    public Double getInvestmentValue(String ticker) {
        return portfolioManagerRepository.findById(ticker).get().getMarketValue();
    }

    @Override
    public void buyInvestment(String ticker, Double quantity) {
        Double currentQuantity = 0.0;
        Investments investment = portfolioManagerRepository.getById(ticker);
        if(investment != null) {
            currentQuantity = investment.getQuantity();
            investment.setQuantity(currentQuantity + quantity);
        }

    }

    @Override
    public void sellInvestment(String ticker, Double quantity) {

    }

    @Override
    public void deleteInvestment(String ticker) {
        portfolioManagerRepository.deleteById(ticker);
    }
}
