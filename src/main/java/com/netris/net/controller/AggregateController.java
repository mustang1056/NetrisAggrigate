package com.netris.net.controller;


import com.netris.net.service.AggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
public class AggregateController {

    @RequestMapping(value = "/callable", method = RequestMethod.GET)
    @ResponseBody
    public String getAggregate() {

        AtomicBoolean processed = new AtomicBoolean(true) ;
        AggregateService service = new AggregateService(processed);

        Thread thread = new Thread(){
            public void run(){
                service.start();
            }
        };
        thread.start();
        synchronized(processed) {
            try {
                processed.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return service.getResult();
    }


}
