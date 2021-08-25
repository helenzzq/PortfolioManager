package functional.tests;

import com.citi.training.portfolioManager.entities.Investment;
import com.citi.training.portfolioManager.entities.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class portfolioManagerRestTests {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testFindAllStocks() {
        Collection<Stock> stocks = restTemplate.getForObject("http://localhost:8080/stocks", Collection.class);
        assert stocks != null;
        Collection<Stock> expectedStockCollection = new ArrayList<>();

        assertThat(stocks.size(),  greaterThan(1));
//        assertThat(stocks.containsAll());
    }


}
