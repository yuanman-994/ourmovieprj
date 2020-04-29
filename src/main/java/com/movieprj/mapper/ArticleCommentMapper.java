package com.movieprj.mapper;

import com.movieprj.beans.Article;
import com.movieprj.beans.ArticleComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleCommentMapper {

    @Insert("INSERT INTO article_comment(article_id,user_id,content,time) " +
            "VALUES(#{article_id},#{user_id},#{content},CURRENT_TIMESTAMP())")
    @Options(useGeneratedKeys = true, keyProperty = "comment_id", keyColumn = "comment_id")
    public void addComment(ArticleComment articleComment);

    @Select("SELECT COUNT(*) FROM article_comment WHERE article_id=#{article_id}")
    public int getCommentNumById(int article_id);

    @Select("SELECT * FROM article_comment WHERE article_id=#{article_id} ORDER BY time DESC limit #{currIndex} , #{pageSize} ")
    public List<ArticleComment> getCommentsByPage(int currIndex, int pageSize, int article_id);//分页查询


    @Select("SELECT ac.article_id,ac.comment_id,ac.content,ac.time,a.headline,u.user_name " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "ORDER BY ac.time DESC " +
            "LIMIT #{offset},#{limit}")
    public List<ArticleComment> getComments(int limit,int offset);

    @Select("SELECT ac.article_id,ac.comment_id,ac.content,ac.time,a.headline,u.user_name " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "AND a.headline LIKE CONCAT('%',#{article},'%') " +
            "ORDER BY ac.time DESC " +
            "LIMIT #{offset},#{limit}")
    public List<ArticleComment> getCommentsSearchArticle(int limit,int offset,String article);

    @Select("SELECT ac.article_id,ac.comment_id,ac.content,ac.time,a.headline,u.user_name " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "AND u.user_name LIKE CONCAT('%',#{user},'%') " +
            "ORDER BY ac.time DESC " +
            "LIMIT #{offset},#{limit}")
    public List<ArticleComment> getCommentsSearchUser(int limit,int offset,String user);

    @Select("SELECT ac.article_id,ac.comment_id,ac.content,ac.time,a.headline,u.user_name " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "AND a.headline LIKE CONCAT('%',#{article},'%') " +
            "AND u.user_name LIKE CONCAT('%',#{user},'%') " +
            "ORDER BY ac.time DESC " +
            "LIMIT #{offset},#{limit}")
    public List<ArticleComment> getCommentsSearchBothArticleAndUser(int limit,int offset,String article,String user);

    @Select("SELECT COUNT(*) " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "ORDER BY ac.time DESC")
    public int getCommentsCount();

    @Select("SELECT COUNT(*) " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "AND a.headline LIKE CONCAT('%',#{article},'%') " +
            "ORDER BY ac.time DESC")
    public int getCommentsSearchArticleCount(String article);

    @Select("SELECT COUNT(*) " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "AND u.user_name LIKE CONCAT('%',#{user},'%') " +
            "ORDER BY ac.time DESC")
    public int getCommentsSearchUserCount(String user);

    @Select("SELECT COUNT(*) " +
            "FROM article a,article_comment ac,user_password u " +
            "WHERE ac.article_id = a.article_id " +
            "AND ac.user_id = u.user_id " +
            "AND a.headline LIKE CONCAT('%',#{article},'%') " +
            "AND u.user_name LIKE CONCAT('%',#{user},'%') " +
            "ORDER BY ac.time DESC")
    public int getCommentsSearchBothArticleAndUserCount(String article,String user);

    @Delete("DELETE FROM article_comment WHERE comment_id = #{comment_id}")
    public void deleteCommentById(int comment_id);

    @Delete("DELETE FROM article_comment WHERE article_id = #{article_id}")
    public void deleteCommentByArticle(int article_id);
}
