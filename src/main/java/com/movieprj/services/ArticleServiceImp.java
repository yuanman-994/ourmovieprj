package com.movieprj.services;


import com.movieprj.beans.Article;
import com.movieprj.mapper.ArticleMapper;
import com.movieprj.mapper.UserPasswordMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.UUID;

@Service
public class ArticleServiceImp implements ArticleService {
    @Resource
    private UserPasswordMapper userPasswordMapper;
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Boolean save_article(String author_name, Map<String, String> data) {
        try {
            int author_id = userPasswordMapper.findIdByName(author_name);
            Article article = new Article();
            article.setAuthor_id(author_id);
            article.setCheck(0);
            article.setClick_num(0);
            article.setHeadline(data.get("headline"));
            article.setType(Integer.valueOf(data.get("type")));
            articleMapper.saveArticle(article);
            int article_id = article.getArticle_id();

            String path = System.getProperty("user.dir");
            String article_url = "article\\" + author_id + "_" + article_id + ".txt";
            String abs_url = path + "\\src\\main\\resources\\static\\" + article_url;

            File file = new File(abs_url);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fwriter = new FileWriter(abs_url, false);
            System.out.println(data.get("content"));
            fwriter.write(data.get("content"));
            fwriter.close();
            articleMapper.saveArticleUrlById(article_id,article_url);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String upload_image(MultipartFile image, String basePath, String author_name){
        String ret = "";

        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件类型，如果不是图片，禁止上传
        String contentType = image.getContentType();
        //获得文件的后缀名
        String suffixName = contentType.substring(contentType.indexOf("/")+1);
        //得到文件名
        String imageName = uuid+"."+suffixName;
        int author_id = userPasswordMapper.findIdByName(author_name);
        //获取文件夹路径
        String direPath = basePath+"\\"+author_id;
        File direFile = new File(direPath);
        //文件夹如果不存在，新建文件夹
        if(direFile.exists() == false || direFile.isDirectory() == false){
            direFile.mkdir();
        }
        //得到文件路径
        String path = direPath+"\\"+imageName;
        try {
            image.transferTo(new File(path));
        } catch (Exception e){
            e.printStackTrace();
        }
        return "images\\"+author_id+"\\"+imageName;
//        return "images\\6\\1a7121bb3827431e8dca0a3ab6ee856e.jpeg";
    }
}
