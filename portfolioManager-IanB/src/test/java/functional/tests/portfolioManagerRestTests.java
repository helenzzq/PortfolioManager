package functional.tests;

import com.citi.training.portfolioManager.entities.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import strategy.CollectionCast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class portfolioManagerRestTests {

    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testGetAllStocks() throws IOException {
        Collection<Stock> output = restTemplate.getForObject("http://localhost:8080/portfoliomanager/stocks", Collection.class);
        List<Stock> stocks = CollectionCast.toObjectList(output,Stock.class);
        assert stocks != null;
        Stock biliStock = new Stock("BILI",100.0,80.0,78.0);
        Stock gmeStock = new Stock("GME",10.0,180.0,210.29);
        ArrayList<Stock> expectedStocks = new ArrayList<>();
        expectedStocks.add(biliStock);
        expectedStocks.add(gmeStock);
        assertThat(stocks.size(),equalTo(expectedStocks.size()));
        for (int i=0;i<expectedStocks.size();i++){
            assertThat(stocks.get(i).getTicker(),equalTo(expectedStocks.get(i).getTicker()));
            assertThat(stocks.get(i).getCostPerShare(),equalTo(expectedStocks.get(i).getCostPerShare()));
            assertThat(stocks.get(i).getQuantity(),equalTo(expectedStocks.get(i).getQuantity()));
        }
    }


}
