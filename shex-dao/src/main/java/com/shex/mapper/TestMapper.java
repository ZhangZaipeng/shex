package com.shex.mapper;

import com.shex.domain.Test;
import com.shex.platform.mybatis.DefaultMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TestMapper{
    Test selectById();
}
