package com.citi.training.portofolioManagerIanB.repo;

import com.citi.training.portofolioManagerIanB.entities.AccountActivity;
import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AccountRepository extends JpaRepository<AccountActivity, Date> {
    public Iterable<AccountActivity> findAccountRecordByDate(Date date);
}