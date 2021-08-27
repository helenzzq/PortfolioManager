package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.investments.Bond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondRepository extends JpaRepository<Bond,String> {
}
