package com.citi.training.portofolioManagerIanB.repo;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioManagerRepository extends JpaRepository<Investments, Integer> {
	Iterable<Investments> findInvestmentByTicker(String ticker);
}
