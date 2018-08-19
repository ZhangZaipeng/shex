package com.shex.test.mapper;

import com.shex.test.domain.Sequence;
import org.springframework.stereotype.Component;

@Component
public interface SequenceMapper {
    Sequence selectById();
}
