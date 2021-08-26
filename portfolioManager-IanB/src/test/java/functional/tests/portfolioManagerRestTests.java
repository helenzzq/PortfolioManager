package functional.tests;

import com.citi.training.portfolioManager.entities.Bond;
import com.citi.training.portfolioManager.entities.Etf;
import com.citi.training.portfolioManager.entities.Investment;
import com.citi.training.portfolioManager.entities.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import strategy.CollectionCast;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class portfolioManagerRestTests {

    private RestTemplate portfolioTestAgent = new RestTemplate();
    ArrayList<Stock> expectedStocks = new ArrayList<>();
    public void setUpSampleStock() {
        Stock biliStock = new Stock("BILI",100.0,80.0,78.0);
        Stock gmeStock = new Stock("GME",10.0,180.0,210.29);
        expectedStocks.add(biliStock);
        expectedStocks.add(gmeStock);
    }


    @Test
    public void testGetAllStocks() throws IOException {
        Collection<Stock> output = portfolioTestAgent.getForObject("http://localhost:8080/portfoliomanager/stocks", Collection.class);
        List<Stock> stocks = CollectionCast.toObjectList(output,Stock.class);
        assert stocks != null;
        setUpSampleStock();
        assertThat(stocks.size(),equalTo(expectedStocks.size()));
        for (int i=0;i<expectedStocks.size();i++){
            assertThat(stocks.get(i).getTicker(),equalTo(expectedStocks.get(i).getTicker()));
            assertThat(stocks.get(i).getCostPerShare(),equalTo(expectedStocks.get(i).getCostPerShare()));
            assertThat(stocks.get(i).getQuantity(),equalTo(expectedStocks.get(i).getQuantity()));
        }
    }

    @Test
    public void testGetAllEtfs() {
        Collection<Etf> output = portfolioTestAgent.getForObject("http://localhost:8080/portfoliomanager/etfs", Collection.class);
        assertThat(output.isEmpty(),equalTo(true));
        }
    @Test
    public void testGetAllBonds() {
        Collection<Bond> output = portfolioTestAgent.getForObject("http://localhost:8080/portfoliomanager/bonds", Collection.class);
        assertThat(output.isEmpty(),equalTo(true));
    }
    @Test
    public void testGetAllFuture() {
        Collection<Bond> output = portfolioTestAgent.getForObject("http://localhost:8080/portfoliomanager/future", Collection.class);
        assertThat(output.isEmpty(),equalTo(true));
    }
    @Test
    public void testGetAllInvestment(){
        HashMap<String,List<Investment>> investments = portfolioTestAgent.getForObject("http://localhost:8080/portfoliomanager/investments/userId=1", HashMap.class);
        assertThat(investments.size(),equalTo(4));
        String[] types = {"ETF","Bond","Stock","Future"};
        assertThat(investments.keySet().containsAll(Arrays.asList(types)),equalTo(true));
        List<Investment> outputStocks = investments.get("Stock");
        for (int i=0;i<expectedStocks.size();i++){
            assertThat(outputStocks.get(i).getTicker(),equalTo(expectedStocks.get(i).getTicker()));
            assertThat(outputStocks.get(i).getCostPerShare(),equalTo(expectedStocks.get(i).getCostPerShare()));
            assertThat(outputStocks.get(i).getQuantity(),equalTo(expectedStocks.get(i).getQuantity()));
        }
    }


}
