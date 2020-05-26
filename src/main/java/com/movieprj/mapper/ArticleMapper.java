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

    @Select("SELECT headline FROM article WHERE article_id = #{id}")
    public String getHeadlineById(int id);//查询所有文章

    @Update("UPDATE article SET article_url=#{url} WHERE article_id=#{id}")
    public void saveArticleUrlById(int id, String url);//保存article_url

    @Update("UPDATE article SET article_cover_image=#{url} WHERE article_id=#{id}")
    public void saveArticleCoverById(int id, String url);

    @Select("SELECT article_cover_image FROM article WHERE article_id=#{id}")
    public String getArticleCoverById(int id);

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
    public void deleteById(int id);

    @Select("SELECT COUNT(*) FROM article WHERE type=#{type}")
    public int getTotalWithType(int type);

    @Select("SELECT COUNT(*) FROM article")
    public int getTotal();

    @Select("SELECT * FROM article WHERE type=#{type} AND check_status=1 ORDER BY release_time DESC limit #{currIndex} , #{pageSize} ")
    public List<Article> getByPageOnlyPassCheck(int currIndex, int pageSize, int type);//分页查询

    @Select("SELECT * FROM article WHERE article_id=#{article_id}")
    public Article getSingle(int article_id);

    @Select("SELECT * FROM article WHERE check_status=1 ORDER BY release_time DESC limit 0 , 10")
    public List<Article> getUpdatedNews();

    @Select("SELECT click_num FROM article WHERE article_id=#{article_id}")
    public int getClickNum(int article_id);

    @Update("UPDATE article SET click_num=#{click_num} WHERE article_id=#{article_id}")
    public void updateClickNum(int article_id,int click_num);

    @Select("SELECT * FROM article WHERE article_id in\n" +
            "(\n" +
            "SELECT article_id FROM\n" +
            "(\n" +
            "\tSELECT a.article_id , a.headline, u.user_name ,a.release_time\n" +
            "\tFROM article a, user_password u\n" +
            "\tWHERE a.author_id = u.user_id\n" +
            ") r\n" +
            "WHERE r.headline like CONCAT('%',#{search},'%')\n" +
            "OR r.user_name like CONCAT('%',#{search},'%')\n" +
            "ORDER BY r.release_time DESC\n" +
            ") \n" +
            "LIMIT #{offset} , #{limit}")
    public List<Article> getByPageWithSearch(int limit,int offset,String search);//分页查询,带有模糊搜索

    @Select("SELECT COUNT(*) FROM\n" +
            "(\n" +
            "\tSELECT a.article_id , a.headline, u.user_name ,a.release_time\n" +
            "\tFROM article a, user_password u\n" +
            "\tWHERE a.author_id = u.user_id\n" +
            ") r\n" +
            "WHERE r.headline like CONCAT('%',#{search},'%')\n" +
            "OR r.user_name like CONCAT('%',#{search},'%')\n" +
            "ORDER BY r.release_time DESC")
    public int getSearchCount(String search);

    @Select("SELECT * FROM article ORDER BY release_time DESC limit #{offset} , #{limit}")
    public List<Article> getByPage(int limit,int offset);

    @Select("SELECT article_id FROM article_hot")
    public List<Integer> getHot();

    @Update("UPDATE article_hot SET article_id=#{article_id} WHERE id=#{id}")
    public void setHot(int id, int article_id);//保存article_url
}
