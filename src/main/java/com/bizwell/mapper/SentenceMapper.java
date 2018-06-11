package com.bizwell.mapper;

import com.bizwell.entity.Sentence;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by charles on 2017/11/21.
 */
@Mapper
public interface SentenceMapper {
    Long save(Sentence sentence);

    void update(Sentence sentence);
}
