package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.AccountActivity;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface AccountRepository extends JpaRepository<AccountActivity, Date> {

    @Query("SELECT p FROM AccountActivity p where function('Month',p.date)=:month and  function('Year',p.date) =:year ")
    Collection<AccountActivity> getAccountActivitiesByYearAndMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT p FROM AccountActivity p where p.date <=:end AND p.date >=:start ")
    Collection<AccountActivity> getAccountActivitiesByInterval(@Param("start")  Date start, @Param("end") Date end);

}
//
//    @Query("SELECT p FROM AccountActivity p where function('Month',p.date)=:month and  function('Day',p.date) <: end AND function('Day',p.date) >: start ")
//    Collection<AccountActivity> getAccountActivitiesByInterval(@Param("month")  Integer month, @Param("year") Integer year,
//                                                               @Param("start")  Integer start, @Param("end") Integer end);
//    @Query("SELECT p FROM AccountActivity p where function('Month',p.date)=:month and  function('Day',p.date) <: end AND function('Day',p.date) >: start ")
//    Collection<AccountActivity> getAccountActivitiesByQuarter(@Param("month")  Integer month, @Param("year") Integer year,
//                                                               @Param("start")  Integer start, @Param("end") Integer end);
