package functional.tests;

import com.citi.training.portfolioManager.entities.Investment;
import com.citi.training.portfolioManager.entities.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class portfolioManagerRestTests {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGetAllStocks() {
        Collection<Stock> stocks = restTemplate.getForObject("http://localhost:8080/stocks", Collection.class);
        assert stocks != null;
        Collection<Stock> expectedStockCollection = new ArrayList<>();
        Stock biliStock = new Stock("BILI",100.0,80.0,78.0);
        Stock gmeStock = new Stock("GME",100.0,180.0,210.29);
        expectedStockCollection.add(biliStock);
        expectedStockCollection.add(gmeStock);
        assertThat(stocks,equalTo(expectedStockCollection));
    }
    @Test
    public void testGetInvestment() {

    }

}
