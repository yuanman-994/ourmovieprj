package com.movieprj.services;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieprj.beans.Article;
import com.movieprj.mapper.ArticleMapper;
import com.movieprj.mapper.UserPasswordMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
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
            article.setCheck_status(0);
            article.setClick_num(0);
            article.setHeadline(data.get("headline"));
            article.setType(Integer.valueOf(data.get("type")));
            articleMapper.saveArticle(article);
            int article_id = article.getArticle_id();

            String path = System.getProperty("user.dir");
            String article_path = path + "\\src\\main\\resources\\static\\article";
            String author_dic = article_path + "\\" + author_id;//作者个人文件夹

            System.out.println(author_dic);
            File direFile = new File(author_dic);
            if (direFile.exists() == false || direFile.isDirectory() == false) {//文件夹不存在，则新建
                direFile.mkdir();
            }
            String abs_article_url = author_dic + "\\" + article_id + ".txt";//文章绝对地址
            String article_url = author_id + "\\" + article_id + ".txt";
            File file = new File(abs_article_url);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fwriter = new FileWriter(abs_article_url, false);
            System.out.println(data.get("content"));
            fwriter.write(data.get("content"));
            fwriter.close();
            articleMapper.saveArticleUrlById(article_id, article_url);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String upload_image(MultipartFile image, String basePath, String author_name) {
        String ret = "";

        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件类型，如果不是图片，禁止上传
        String contentType = image.getContentType();
        //获得文件的后缀名
        String suffixName = contentType.substring(contentType.indexOf("/") + 1);
        //得到文件名
        String imageName = uuid + "." + suffixName;
        int author_id = userPasswordMapper.findIdByName(author_name);
        //获取文件夹路径
        String direPath = basePath + "\\" + author_id;
        File direFile = new File(direPath);
        //文件夹如果不存在，新建文件夹
        if (direFile.exists() == false || direFile.isDirectory() == false) {
            direFile.mkdir();
        }
        //得到文件路径
        String path = direPath + "\\" + imageName;
        try {
            image.transferTo(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "images\\" + author_id + "\\" + imageName;
    }

    @Override
    public String get_self_article_data(String author_name) {
        int id = userPasswordMapper.findIdByName(author_name);
        List<Article> articles = articleMapper.getArticleById(id);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < articles.size(); i++) {
            Article a = articles.get(i);
            JSONObject json = new JSONObject();
            json.put("article_id", a.getArticle_id());
            json.put("author_name", author_name);
            json.put("headline", a.getHeadline());
            json.put("click_num", a.getClick_num());
            json.put("release_time", a.getRelease_time());
            String type;
            if (a.getType() == 1)
                type = "新闻";
            else
                type = "影片看点";
            json.put("type", type);
            String check = "未通过";
            switch (a.getCheck_status()) {
                case 0:
                    check = "未审核";
                    break;
                case 1:
                    check = "已通过";
                    break;
                case -1:
                    check = "未通过";
                    break;
            }
            json.put("check_status", check);
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

    @Override
    public int update_row(Map<String, String> row) {
        try {
//            System.out.println(row);
            int article_id, click_num, article_type = 1;
            String headline = row.get("headline");
            article_id = Integer.valueOf(row.get("article_id"));
            click_num = Integer.valueOf(row.get("click_num"));
            switch (row.get("type")) {
                case "新闻":
                    article_type = 1;
                    break;
                case "影片看点":
                    article_type = 2;
                    break;
            }
            articleMapper.updata_row(article_id, headline, click_num, article_type);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public String get_text_by_id(int article_id) {

        String url = articleMapper.selectUrlById(article_id);

        String path = System.getProperty("user.dir");
        String article_path = path + "\\src\\main\\resources\\static\\article";
        String abs_article_url = article_path + "\\" + url;//文章绝对地址

        File file = new File(abs_article_url);

        String result = null;
        try {
            StringBuffer buffer = new StringBuffer();
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = bf.readLine()) != null) {//使用readLine方法，一次读一行
                buffer.append(s.trim());
            }
            result = buffer.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
