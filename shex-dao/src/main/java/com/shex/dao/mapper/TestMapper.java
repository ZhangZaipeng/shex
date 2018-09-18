package com.shex.dao.mapper;

import com.shex.dao.domain.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TestMapper extends DefaultMapper{
    List<Test> selectById();
}
