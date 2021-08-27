package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.investments.Etf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtfRepository extends JpaRepository<Etf,String> {
}
