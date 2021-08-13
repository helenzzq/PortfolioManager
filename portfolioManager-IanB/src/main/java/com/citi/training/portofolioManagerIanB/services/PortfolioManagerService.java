package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import java.util.Collection;

public interface PortfolioManagerService {
    Collection<Investments> getAllInvestments();
    Double getInvestmentValue(String ticker);
    void buyInvestment(String ticker, Double quantity);
    void sellInvestment(String ticker, Double quantity);
    void deleteInvestment(String ticker);
}




