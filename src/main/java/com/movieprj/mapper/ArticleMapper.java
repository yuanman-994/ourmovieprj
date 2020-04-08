package com.movieprj.mapper;

import com.movieprj.beans.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(author_id,headline,click_num,release_time,type,check_status)"+
    "VALUES (#{author_id},#{headline},#{click_num},CURRENT_TIMESTAMP(),#{type},#{check})")
    @Options(useGeneratedKeys=true, keyProperty="article_id", keyColumn="article_id")
    public void saveArticle(Article article);//得到自动生成的主键值同步保存在article中。article_url置空

    @Update("Update article SET article_url=#{url} WHERE article_id=#{id}")
    public void saveArticleUrlById(int id,String url);//保存article_url
}
