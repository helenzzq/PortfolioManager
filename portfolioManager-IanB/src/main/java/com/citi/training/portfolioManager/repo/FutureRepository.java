package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.investments.Future;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutureRepository extends JpaRepository<Future,String> {
}
