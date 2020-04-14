package com.movieprj.mapper;

import com.movieprj.beans.Article;
import com.movieprj.beans.GroupBuyBeans;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("INSERT INTO article(author_id,headline,click_num,release_time,type,check_status)" +
            "VALUES (#{author_id},#{headline},#{click_num},CURRENT_TIMESTAMP(),#{type},#{check_status})")
    @Options(useGeneratedKeys = true, keyProperty = "article_id", keyColumn = "article_id")
    public void saveArticle(Article article);//得到自动生成的主键值同步保存在article中。article_url置空

    @Select("SELECT * FROM article")
    public List<Article> getArticle();//查询所有文章

    @Update("UPDATE article SET article_url=#{url} WHERE article_id=#{id}")
    public void saveArticleUrlById(int id, String url);//保存article_url

    @Update("UPDATE article SET release_time=CURRENT_TIMESTAMP() WHERE article_id=#{id}")
    public void updateTimeById(int id);//保存article_url

    @Update("UPDATE article SET check_status=#{check} WHERE article_id=#{id}")
    public void updateCheckById(int id, int check);//保存article_url

    @Select("SELECT article_url FROM article WHERE article_id=#{id}")
    public String getUrlById(int id);

    @Select("SELECT author_id FROM article WHERE article_id=#{id}")
    public int getAuthorIdById(int id);

    @Select("SELECT * FROM article WHERE author_id=#{id}")
    public List<Article> getArticleById(int id);//查询个人文章

    @Update("UPDATE article SET headline=#{headline},click_num=#{click_num},type=#{article_type} WHERE article_id=#{article_id}")
    public void updata_row(int article_id, String headline, int click_num, int article_type);

    @Delete("DELETE FROM article WHERE article_id=#{id}")
    public void deleateById(int id);
}
