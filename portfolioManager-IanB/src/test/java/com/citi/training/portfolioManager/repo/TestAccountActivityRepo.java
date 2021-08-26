package com.citi.training.portfolioManager.repo;


import com.citi.training.portfolioManager.AppConfig;
import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.rest.UserAccountManagerController;
import com.citi.training.portfolioManager.services.UserAccountManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest() // use an in memory database
@ContextConfiguration(classes = {com.citi.training.portfolioManager.AppConfig.class})
public class TestAccountActivityRepo {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    private AccountRepository repo;
    @Autowired
    private UserAccountManagerService userAccountManagerService;
    @Autowired
    UserAccountManagerController controller;

    private Date accountDate;

    @BeforeEach
    public void setupDatabaseEntryForReadOnlyTests() throws ParseException {
        Date dates = new SimpleDateFormat("dd-MM-yyyy").parse("16-07-2021");
        AccountActivity ac = new AccountActivity(dates,750.0,1000.0,9000.0,10750.0);
        AccountActivity result = manager.persist(ac);
       accountDate = result.getDate();

    }
    // unit test the repo using a mock database
    @Test
    public void canRetrieveAccountActivityByRange() {
        Iterable<AccountActivity> accounts = repo.getAccountActivitiesByYearAndMonth(7,2021);
        Stream<AccountActivity> stream = StreamSupport.stream(accounts.spliterator(), false);
        assertThat(stream.count(), equalTo(1L));
    }


}
