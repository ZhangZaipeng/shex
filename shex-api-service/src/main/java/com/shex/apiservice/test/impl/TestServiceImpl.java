package com.shex.apiservice.test.impl;

import com.shex.apiservice.test.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by ZhangZp on 2018/8/27.
 */
@Service
public class TestServiceImpl implements TestService{

    @Override
    public String test() {
        return "122321 test";
    }

    /*@Override
    public Test test1() {
        return testMapper.selectById();
    }*/
}
