package com.citi.training.portofolioManager.repo;

import com.citi.training.portofolioManager.entities.Etf;
import com.citi.training.portofolioManager.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtfRepository extends JpaRepository<Etf,String> {
}
