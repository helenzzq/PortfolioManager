package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.AccountActivity;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface AccountRepository extends JpaRepository<AccountActivity, Date> {

    @Query("SELECT p FROM AccountActivity p where function('Month',p.date)=:month and  function('Year',p.date) =:year ")
    List<AccountActivity> getAccountActivitiesByYearAndMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT p FROM AccountActivity p where p.date <=:end AND p.date >=:start ")
    Collection<AccountActivity> getAccountActivitiesByInterval(@Param("start")  Date start, @Param("end") Date end);


}
