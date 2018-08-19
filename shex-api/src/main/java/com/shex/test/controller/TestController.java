package com.shex.test.controller;

import com.shex.conf.bean.LocaleMessageSource;
import com.shex.test.mapper.SequenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/8/19.
 */
@RestController
// @MonitoredWithSpring
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private LocaleMessageSource messageSource;

    @GetMapping(value = "/user/ok1.json")
    public String ok1(){
        logger.info("/user/ok1.json");
        return "ok 2345 \n" + messageSource.getMessage("mess.user.password",null);
    }

    @GetMapping(value = "/user/ha/ok2.json")
    public String ok3(){
        logger.info("/asset/ok2.json");
        return "ok 2345";
    }

    @GetMapping(value = "/asset/ok2.json")
    public String ok2(){
        logger.info("/asset/ok2.json");
        return "ok 2345";
    }

    @GetMapping(value = "/ok4.json")
    public String ok4(){
        return sequenceMapper.selectById().getSeqName();
    }

}
