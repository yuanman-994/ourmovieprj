package com.movieprj.services;

import com.movieprj.beans.ShowType;

import java.util.List;
import java.util.Map;

public interface ShowTypeService {

    public List<ShowType> findAll();
    public int addShowType(ShowType show_type);
    public int deleteShowType(Map<String,List> types_names);
    public int updateShowType(String new_show_type,String old_show_type);
}
