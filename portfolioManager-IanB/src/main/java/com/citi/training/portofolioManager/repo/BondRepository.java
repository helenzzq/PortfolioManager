package com.citi.training.portofolioManager.repo;

import com.citi.training.portofolioManager.entities.Bond;
import com.citi.training.portofolioManager.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondRepository extends JpaRepository<Bond,String> {
}
