package sbt.rbc.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sbt.rbc.DbWorker;
import sbt.rbc.responsers.ResponserToRBC;
import sbt.rbc.utils.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import sbt.rbc.RbcDB;


@Component
public class RBCService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final DateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");


    @Autowired
    private DbWorker dbWorker;


    @Autowired
    Utils utils;

    public void saveNewLine(Double dollar) {
        // here we save no time because we need only days (time will be 00:00:00).
        String currentDate = getDateNoTime();
        RbcDB rbk = new RbcDB(currentDate, dollar);

        dbWorker.save(rbk);
    }

    public static String getDateNoTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().toString();
    }

    public double getMaxRateForPeriod(int lastdays, ResponserToRBC responserToRBC) {
        String currentDate = getDateNoTime();
        Optional<RbcDB> base_result = dbWorker.findByDate(currentDate);
        Optional<Double>  currency = base_result.map(RbcDB::getCurrency);
        System.out.println(currency);
        if (currency.isPresent()) {
            return currency.get();
        }
        double result = utils.getMaxFromArray(getRateForPeriod(lastdays, responserToRBC));
        saveNewLine(result);
        return result;
    }

    public ArrayList<Double> getRateForPeriod(int lastdays, ResponserToRBC responserToRBC) {
        return parseResponse(responserToRBC.getResponse(lastdays));
    }


    public ArrayList<Double> parseResponse(String responseString) {
        String[] lines = responseString.split("\n");



        ArrayList<Double> ans = new ArrayList<>();
        for (String line : lines) {
            System.out.println("Line:=" + line);
            String[] elements = line.split(",");
            ans.add(Double.parseDouble(elements[elements.length - 1]));
        }
        return ans;
    }

}