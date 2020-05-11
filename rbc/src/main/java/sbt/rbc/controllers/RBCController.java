package sbt.rbc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sbt.rbc.services.RBCService;
import sbt.rbc.responsers.ResponserToRBC;


@RestController
@Component
public class RBCController {

    @Autowired
    private RBCService service;
    @Autowired
    private ResponserToRBC responserToRBC;


    @RequestMapping(value = "/rbc", method = RequestMethod.GET)
    @ResponseBody
    public Viewer index(@RequestParam("days") int days) {
        Viewer viewer = new Viewer();
        viewer.setValue(service.getMaxRateForPeriod(days, responserToRBC));
        return viewer;
    }

}