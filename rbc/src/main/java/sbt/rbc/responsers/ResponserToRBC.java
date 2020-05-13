package sbt.rbc.responsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponserToRBC {

    @Autowired
    private RestTemplate restTemplate;

    public String getResponse(int lastdays) {
        try {
            String url = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers=USD000000TOD&separator=,&data_format=BROWSER&lastdays=";
            ResponseEntity<String> response = restTemplate.getForEntity(url + lastdays, String.class);
            System.out.println("Result net = " + response.getBody());
            return response.getBody();
        } catch (Exception e){
            String data =
                    "USD000000TOD,2019-09-13,70.2225,64.7175,64.125,64.2725,725046000,64.3306\n" +
                            "USD000000TOD,2019-09-16,63.835,64.1675,63.57,64,1367191000,63.9049\n" +
                            "USD000000TOD,2019-09-17,64.03,64.44,63.97,64.3575,725278000,64.184\n";
            return data;
        }
    }
}
