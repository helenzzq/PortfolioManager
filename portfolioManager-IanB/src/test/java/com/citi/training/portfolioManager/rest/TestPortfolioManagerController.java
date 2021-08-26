package com.citi.training.portfolioManager.rest;

import com.citi.training.portfolioManager.entities.Bond;
import com.citi.training.portfolioManager.entities.Etf;
import com.citi.training.portfolioManager.entities.Stock;
import com.citi.training.portfolioManager.rest.PortfolioManagerController;

import com.citi.training.portfolioManager.services.MarketUpdaterServices;
import com.citi.training.portfolioManager.services.PortfolioManagerService;
import com.citi.training.portfolioManager.services.UserManagerService;
import com.citi.training.portfolioManager.services.UserManagerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PortfolioManagerController.class)
@ContextConfiguration(classes = {com.citi.training.portfolioManager.AppConfig.class})
public class TestPortfolioManagerController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PortfolioManagerService portfolioManagerService;
    @MockBean
    private MarketUpdaterServices marketUpdaterServices;
    @MockBean
    private UserManagerService userManagerService;
    @Autowired
    private
    ObjectMapper mapper;

    private void mockMvcPerform(String api, String expression, Object expectedOutput) throws Exception {
        mockMvc.perform(get(api)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(expression, is(expectedOutput)));
    }

    @Test
    public void testCanRetrieveAllStocks() throws Exception {

        Stock stocks = new Stock("K.TO", 80.0, 7.8, 8.0);
        List<Stock> allStock = Arrays.asList(stocks);

        given(portfolioManagerService.getStocks()).willReturn(allStock);
        mockMvcPerform("/portfolio-manager/stocks", "$[0].ticker", "K.TO");
        mockMvcPerform("/portfolio-manager/stocks", "$[0].quantity", 80.0);
        mockMvcPerform("/portfolio-manager/stocks", "$[0].costPerShare", 7.8);

    }

    @Test
    public void testCanRetrieveAllEtf() throws Exception {

        Etf etf = new Etf("QQQ", 60.0, 300.0, 374.8);
        List<Etf> allEtf = Arrays.asList(etf);

        given(portfolioManagerService.getEtf()).willReturn(allEtf);
        mockMvcPerform("/portfolio-manager/etfs", "$[0].ticker", "QQQ");
        mockMvcPerform("/portfolio-manager/etfs", "$[0].quantity", 60.0);
        mockMvcPerform("/portfolio-manager/etfs", "$[0].costPerShare", 300.0);

    }

    @Test
    public void testCanRetrieveAllFuture() throws Exception {

        Bond bond = new Bond("AGG", 30.0, 150.0, 228.0);
        List<Bond> allBond = Arrays.asList(bond);
        given(portfolioManagerService.getBonds()).willReturn(allBond);
        mockMvcPerform("/portfolio-manager/bonds", "$[0].ticker", "AGG");
        mockMvcPerform("/portfolio-manager/bonds", "$[0].quantity", 30.0);
        mockMvcPerform("/portfolio-manager/bonds", "$[0].costPerShare", 150.0);
    }

    @Test
    public void testRetrieveAllIndices() throws Exception {

        HashMap<String, String> indices = new HashMap<>();
        String[] indicesName = {"S&P 500", "NASDAQ", "Dow Jones", "FTSE 100"};
        String[] output = {"0.21%", "0.2%", "0.25%", "0.34%"};
        for (int i = 0; i < 4; i++) {
            indices.put(indicesName[i], output[i]);
        }

        given(marketUpdaterServices.getFamousIndicesInfo()).willReturn(indices);
        mockMvcPerform("/portfolio-manager/indices/famous-indices", "$.NASDAQ", "0.2%");
    }

    @Test
    public void testRetrieveAllLosers() throws Exception {

        HashMap<Integer, String> gainers = new HashMap<>();
        String[] gainer = {"Apple", "TSLA", "CLEU", "AMC"};

        for (int i = 0; i < 4; i++) {
            gainers.put(i, gainer[i]);
        }

        given(marketUpdaterServices.getDailyGainers()).willReturn(gainers);
        mockMvcPerform("/portfolio-manager/gainers", "$.3", "AMC");
    }

    @Test
    public void testRetrieveAllGainers() throws Exception {

        HashMap<Integer, String> gainers = new HashMap<>();
        String[] gainer = {"BILI", "GME", "K.TO", "SU"};

        for (int i = 0; i < 4; i++) {
            gainers.put(i, gainer[i]);
        }

        given(marketUpdaterServices.getDailyGainers()).willReturn(gainers);
        mockMvcPerform("/portfolio-manager/gainers", "$.0", "BILI");
    }

}
