package sbt.weather.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sbt.weather.responsers.ResponserToWheather;
import sbt.weather.services.WeatherService;
import sbt.weather.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@RestController
@Component
public class WeatherController {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    Utils utils;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ResponserToWheather responserToWheather;


    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @ResponseBody
    public Viewer item(@RequestParam("days") int days) {
        // "yyyy-MM-dd"
        String today_date = dateTimeFormatter.format(LocalDateTime.now());
        long today_seconds = utils.parseDate(today_date);
        long inp_seconds = utils.prevDay(today_seconds);
        Viewer viewer = new Viewer();
        ArrayList<Double> value = weatherService.getTempretureForPeriod(inp_seconds, days, responserToWheather);
        viewer.setValue(utils.getMaxFromArray(value));
        return viewer;
    }
}