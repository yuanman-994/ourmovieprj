package com.movieprj.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieprj.beans.ArticleComment;
import com.movieprj.mapper.ArticleCommentMapper;
import com.movieprj.mapper.ArticleMapper;
import com.movieprj.mapper.UserPasswordMapper;
import com.movieprj.services.dfa.SensitiveWordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleCommentServiceImp implements ArticleCommentService {
    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Resource
    private ArticleMapper articleMapper;

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

            String content = articleComment.getContent();
//            System.out.println(content);
//            System.out.println(SensitiveWordUtils.sensitiveWordMap);
//            System.out.println(SensitiveWordUtils.isContaintSensitiveWord(content,2));
//            System.out.println(SensitiveWordUtils.getSensitiveWord(content,2));
//            System.out.println(SensitiveWordUtils.getSensitiveWordSum(content,2));
//            System.out.println(SensitiveWordUtils.replaceSensitiveWord(content,2,"*"));
//            System.out.println("\n");
            content = SensitiveWordUtils.replaceSensitiveWord(content,2,"*");

            jsonObject.put("content",content);
            jsonObject.put("name",userPasswordMapper.findNameById(articleComment.getUser_id()));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public String getCommentsByPageAll(int limit,int offset,String searchArticle,String searchUser) {
        List<ArticleComment> articleComments;
        int total;
        if (searchArticle == "" && searchUser == ""){
            articleComments = articleCommentMapper.getComments(limit,offset);
            total = articleCommentMapper.getCommentsCount();
        } else if (searchArticle != "" && searchUser =="") {
            articleComments=articleCommentMapper.getCommentsSearchArticle(limit,offset,searchArticle);
            total = articleCommentMapper.getCommentsSearchArticleCount(searchArticle);
        } else if (searchArticle == "" && searchUser !=""){
            articleComments=articleCommentMapper.getCommentsSearchUser(limit,offset,searchUser);
            total = articleCommentMapper.getCommentsSearchUserCount(searchUser);
        } else {
            articleComments=articleCommentMapper.getCommentsSearchBothArticleAndUser(limit, offset, searchArticle, searchUser);
            total = articleCommentMapper.getCommentsSearchBothArticleAndUserCount(searchArticle,searchUser);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rows",articleComments);
        jsonObject.put("total",total);
        return jsonObject.toString();
    }

    @Override
    public void deleteComment(int comment_id) {
        articleCommentMapper.deleteCommentById(comment_id);
    }
}
