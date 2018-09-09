package com.shex.api.controller;

import com.shex.apiservice.test.TestService;
import com.shex.domain.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/19.
 */
@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @GetMapping(value = "/ok.json")
    public String ok() {
        return testService.test();
    }

    @GetMapping(value = "/ok1.json")
    public Test ok1() {
        return testService.test1();
    }



}
