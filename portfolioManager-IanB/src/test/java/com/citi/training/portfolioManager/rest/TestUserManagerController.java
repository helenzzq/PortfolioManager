package com.citi.training.portfolioManager.rest;


import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import com.citi.training.portfolioManager.services.PortfolioManagerService;
import com.citi.training.portfolioManager.services.UserAccountManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import strategy.MockMvcOperation;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserAccountManagerController.class)
@ContextConfiguration(classes = {com.citi.training.portfolioManager.AppConfig.class})
public class TestUserManagerController {
    @MockBean
    private UserAccountManagerService userAccountManagerService;
    @MockBean
    private PortfolioManagerService portfolioManagerService;
    @Autowired
    private
    ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCanRetrieveAllUsers() throws Exception {
        User user = new User(1,"Helen");
        Collection<User> users = new ArrayList<>();
        users.add(user);
        given(userAccountManagerService.getUsers()).willReturn(users);
        MockMvcOperation.perform(mockMvc,"/user","$[0].name","Helen");
    }
    @Test
    public void testCanRetrieveAllAccountActivity() throws Exception {
        Date dates = new SimpleDateFormat("dd-MM-yyyy").parse("16-07-2021");
        AccountActivity ac = new AccountActivity(dates,750.0,1000.0,9000.0,10750.0);
        Collection<AccountActivity> accountActivities = new ArrayList<>();
        accountActivities.add(ac);
        given(userAccountManagerService.getAccountActivity()).willReturn(accountActivities);
        MockMvcOperation.perform(mockMvc,"/user/account","$[0].netWorth",750.0);
        MockMvcOperation.perform(mockMvc,"/user/account","$[0].cashValue",1000.0);
        MockMvcOperation.perform(mockMvc,"/user/account","$[0].investmentValue",9000.0);
    }
    @Test
    public void testCanGetAccountActivityByRange() throws Exception {
        Date dates = new SimpleDateFormat("dd-MM-yyyy").parse("16-07-2021");
        AccountActivity ac = new AccountActivity(dates,750.0,1000.0,9000.0,10750.0);
        Collection<AccountActivity> accountActivities = new ArrayList<>();
        accountActivities.add(ac);
        given(userAccountManagerService.getAccountActivityByRange("lastWeek")).willReturn(accountActivities);
        MockMvcOperation.perform(mockMvc,"/user/account/range=lastWeek","$[0].netWorth",750.0);

    }
    @Test
    public void testCanGetCashValueByRange() throws Exception {
        List<Double> cash = new ArrayList<>();
        cash.add(100.0);
        given(userAccountManagerService.getCashValueByRange("lastMonth")).willReturn(cash);
        MockMvcOperation.perform(mockMvc,"/user/account/cash/range=lastMonth","$[0]",100.0);
    }
    @Test
    public void testCanGetNetWorthByRange() throws Exception {
        List<Double> netWorth = new ArrayList<>();
        netWorth.add(7500.0);
        given(userAccountManagerService.getNetWorthByRange("lastQuarter")).willReturn(netWorth);
        MockMvcOperation.perform(mockMvc,"/user/account/net-worth/range=lastQuarter","$[0]",7500.0);
    }
    @Test
    public void testCanGetTotalEquityByRange() throws Exception {
        List<Double> totalEquity = new ArrayList<>();
        totalEquity.add(17500.0);
        given(userAccountManagerService.getTotalEquityByRange("lastQuarter")).willReturn(totalEquity);
        MockMvcOperation.perform(mockMvc,"/user/account/total-equity/range=lastQuarter","$[0]",17500.0);
    }
}
