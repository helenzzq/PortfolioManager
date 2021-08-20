package com.citi.training.portofolioManager.repo;

import com.citi.training.portofolioManager.entities.Investments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioManagerRepository extends JpaRepository<Investments, String> {
	int findInvestmentByTicker(String ticker);
}
