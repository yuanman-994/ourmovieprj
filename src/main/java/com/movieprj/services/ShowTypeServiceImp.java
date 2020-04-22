package com.movieprj.services;

import com.movieprj.beans.ShowType;
import com.movieprj.mapper.ShowTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ShowTypeServiceImp implements ShowTypeService {

    @Resource
    private ShowTypeMapper showTypeMapper;

    @Override
    public List<ShowType> findAll() {
        return showTypeMapper.findAll();
    }

    @Override
    public int addShowType(ShowType show_type) {
        showTypeMapper.addShowType(show_type);
        return 1;
    }

    @Override
    public int deleteShowType(Map<String,List> show_types) {
        List types = show_types.get("showType");
        for(int i=0 ;i<types.size();i++) {
            ShowType showType = new ShowType();
            showType.setShow_type(types.get(i).toString());
            showTypeMapper.deleteShowType(showType);
        }
        return 1;
    }

    @Override
    public int updateShowType(String new_show_type,String old_show_type) {
        showTypeMapper.updateShowType(new_show_type, old_show_type);
        return 1;
    }
}
