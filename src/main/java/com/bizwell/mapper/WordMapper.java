package com.bizwell.mapper;

import com.bizwell.entity.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by charles on 2017/11/21.
 */
@Mapper
public interface WordMapper {
    Long save(Word word);

    void batchSave(List<Word> words);
}
