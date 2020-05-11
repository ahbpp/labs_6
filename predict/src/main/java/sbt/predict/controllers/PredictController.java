package sbt.predict.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sbt.predict.services.PredictService;


@RestController
@Component
public class PredictController {


    @Autowired
    private PredictService predictService;


    @RequestMapping(value = "/predict", method = RequestMethod.GET)
    @ResponseBody
    public String predict(@RequestParam("days") int days) {
        Viewer viewer = new Viewer();
//        viewer.setValue(predictService.getPredictDollarToday(days));
        return predictService.getPredictDollarToday(days);
    }
}
