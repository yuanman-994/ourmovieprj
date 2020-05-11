package com.movieprj.mapper;

import com.movieprj.beans.Keyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SensitiveWordMapper {

    @Select("SELECT * FROM sensitive_word")
    public List<Keyword> getKeyword();
}
