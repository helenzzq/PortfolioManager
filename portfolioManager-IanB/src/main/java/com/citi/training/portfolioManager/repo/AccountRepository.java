package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AccountRepository extends JpaRepository<AccountActivity, Date> {

}