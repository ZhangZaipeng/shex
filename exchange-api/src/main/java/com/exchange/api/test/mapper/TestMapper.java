package com.exchange.api.test.mapper;

import com.exchange.api.test.domain.Test;
import com.exchange.platform.mybatis.DefaultMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/10/7.
 */
public interface TestMapper extends DefaultMapper{
    Test selectById(@Param("id") int id);
}
