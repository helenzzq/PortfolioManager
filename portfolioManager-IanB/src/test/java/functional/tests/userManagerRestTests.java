package functional.tests;

import com.citi.training.portfolioManager.entities.AccountActivity;
import com.citi.training.portfolioManager.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import strategy.CollectionCast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class userManagerRestTests {
    private RestTemplate userTestAgent = new RestTemplate();
    @Test
    public void testGetAllUser() throws IOException {
        Collection<User> users = userTestAgent.getForObject("http://localhost:8080/user", Collection.class);
        assertThat(users.size(),equalTo(1));
    }

    @Test
    public void testGetAllAccountActivity(){
        Collection<AccountActivity> accounts = userTestAgent.getForObject("http://localhost:8080/user/account", Collection.class);
        assertThat(accounts.size(),equalTo(10));
    }
    @Test
    public void testGetAccountActivityByRange() throws ParseException, IOException {
        Collection<AccountActivity> accounts = userTestAgent.getForObject("http://localhost:8080/user/account/range=lastMonth", Collection.class);
        List<AccountActivity> accountActivities = CollectionCast.toObjectList(accounts,AccountActivity.class);
        AccountActivity outputAccount =accountActivities.get(0);
        Date dates = new SimpleDateFormat("dd-MM-yyyy").parse("16-07-2021");
        AccountActivity ac = new AccountActivity(dates,750.0,1000.0,9000.0,10750.0);
        assertThat(accounts.size(),equalTo(1));

        assertThat(outputAccount.getCashValue(),equalTo(ac.getCashValue()));
        assertThat(outputAccount.getInvestmentValue(),equalTo(ac.getInvestmentValue()));
        assertThat(outputAccount.getTotalEquity(),equalTo(ac.getTotalEquity()));

    }
}
