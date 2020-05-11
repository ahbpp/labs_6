package sbt.predict.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sbt.predict.utils.MSR;
import sbt.predict.utils.Utils;

import java.time.format.DateTimeFormatter;

@Component
public class PredictService {

    @Autowired
    Utils utils;
    @Autowired
    MSR msr;

    public String getPredictDollarToday(int days) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://rbc:8110/rbc?days=" + days;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String rates = response.getBody();
        System.out.println(rates);
        String url2 = "http://weather:8111/weather?days=" + days;

        ResponseEntity<String> response2 = restTemplate.getForEntity(url2, String.class);
        String weather = response2.getBody();
        return rates+weather;
    }
}
