package com.shex.apiservice.test.impl;

import com.shex.apiservice.test.TestService;
import com.shex.domain.Test;
import com.shex.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZhangZp on 2018/8/27.
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public String test() {
        return "122321 test";
    }

    @Override
    public Test test1() {
        return testMapper.selectById();
    }
}
