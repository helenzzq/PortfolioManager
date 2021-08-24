package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,String> {
}
