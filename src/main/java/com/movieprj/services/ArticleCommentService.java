package com.movieprj.services;

import org.springframework.stereotype.Service;


@Service
public interface ArticleCommentService {
    public int getCommentNum(int article_id);//得到总评论数
    public int getPageNum(int article_id,int pageSize);//得到页数
    public String getCommentsByPage(int article_id,int aimPage,int page_size);//分页查找,指定文章
    public String getCommentsByPageAll(int limit,int offset,String searchArticle,String searchUser);//分页查找，全部
    public void deleteComment(int comment_id);
}
