package com.citi.training.portofolioManagerIanB.services;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import java.util.Collection;

public interface PortfolioManagerService {
    Collection<Investments> getAllInvestments();
}

