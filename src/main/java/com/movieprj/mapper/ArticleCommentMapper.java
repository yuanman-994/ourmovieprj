package com.movieprj.mapper;

import com.movieprj.beans.Article;
import com.movieprj.beans.ArticleComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
}
