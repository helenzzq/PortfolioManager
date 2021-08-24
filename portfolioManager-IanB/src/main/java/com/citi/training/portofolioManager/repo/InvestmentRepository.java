package com.citi.training.portofolioManager.repo;

import com.citi.training.portofolioManager.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment,String> {
}
