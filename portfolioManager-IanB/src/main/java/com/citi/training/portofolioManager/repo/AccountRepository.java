package com.citi.training.portofolioManager.repo;

import com.citi.training.portofolioManager.entities.AccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AccountRepository extends JpaRepository<AccountActivity, Date> {

}