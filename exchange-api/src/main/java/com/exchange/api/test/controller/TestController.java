package com.exchange.api.test.controller;

import com.exchange.api.test.domain.Test;
import com.exchange.api.test.mapper.TestMapper;
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
    private TestMapper testMapper;

    @GetMapping(value = "/ok.json")
    public String ok() {
        return "23123";
    }

    @GetMapping(value = "/ok1.json")
    public Test ok1() {
        return testMapper.selectById(1);
    }

}
