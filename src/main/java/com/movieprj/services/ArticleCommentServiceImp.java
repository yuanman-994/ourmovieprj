package com.movieprj.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieprj.beans.Article;
import com.movieprj.beans.ArticleComment;
import com.movieprj.mapper.ArticleCommentMapper;
import com.movieprj.mapper.UserPasswordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleCommentServiceImp implements ArticleCommentService {
    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Override
    public int getCommentNum(int article_id) {
        return articleCommentMapper.getCommentNumById(article_id);
    }

    @Override
    public int getPageNum(int article_id, int pageSize) {
        int num = articleCommentMapper.getCommentNumById(article_id);
        double result = (double)num/pageSize;
        return (int)Math.ceil(result);
    }

    @Override
    public String getCommentsByPage(int article_id, int aimPage,int page_size) {
        List<ArticleComment> list = articleCommentMapper.getCommentsByPage((aimPage-1)*page_size,page_size,article_id);
        JSONArray jsonArray = new JSONArray();
        for (ArticleComment articleComment : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time",articleComment.getTime());
            jsonObject.put("content",articleComment.getContent());
            jsonObject.put("name",userPasswordMapper.findNameById(articleComment.getUser_id()));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
