package com.movieprj.services;


import com.alibaba.fastjson.JSON;
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
    public String upload_image(MultipartFile image, String basePath, String author_name, String article_id) {
        System.out.println(basePath);
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
        //获取作者个人图片存储文件夹路径
        String direPath = basePath + "\\" + author_id;

        File direFile = new File(direPath);
        //文件夹如果不存在，新建文件夹
        if (direFile.exists() == false || direFile.isDirectory() == false) {
            direFile.mkdir();
        }

        //得到文章图片文件夹路径
        String imagedir = direFile + "\\" + article_id;
        File direimage = new File(imagedir);
        //文件夹如果不存在，新建文件夹
        if (direimage.exists() == false || direimage.isDirectory() == false) {
            direimage.mkdir();
        }

        //图片绝对地址
        String path = direimage + "\\" + imageName;
        try {
            image.transferTo(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "images\\" + author_id + "\\" + article_id + "\\" + imageName;
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
    public String get_all_article_data() {
        List<Article> articles = articleMapper.getArticle();

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < articles.size(); i++) {
            Article a = articles.get(i);
            int id = a.getAuthor_id();
            String name = userPasswordMapper.findNameById(id);
            JSONObject json = new JSONObject();
            json.put("article_id", a.getArticle_id());
            json.put("author_name", name);
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

        String url = articleMapper.getUrlById(article_id);

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
//            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int upload_article(Map<String, String> data) {
        int article_id = Integer.valueOf(data.get("article_id"));
        String content = data.get("content");//要保存的内容

        String article_url = articleMapper.getUrlById(article_id);//文章相对地址

        try {
            String path = System.getProperty("user.dir");
            String article_path = path + "\\src\\main\\resources\\static\\article";

            String abs_article_url = article_path + "\\" + article_url;//文章绝对地址
            File file = new File(abs_article_url);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fwriter = new FileWriter(abs_article_url, false);
            fwriter.write(content);
            articleMapper.updateCheckById(article_id,0);//修改审核状态为未审核
            articleMapper.updateTimeById(article_id);
            fwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public int del_article(String[] ids) {//删除文章
        for (String id : ids) {
            if (!this.del_article_by_id(Integer.valueOf(id)))
                return -1;
        }
        return 0;
    }

    public boolean del_article_by_id(int article_id) {
        try {
            String article_url = articleMapper.getUrlById(article_id);
            int author_id = articleMapper.getAuthorIdById(article_id);
            String path = System.getProperty("user.dir");
            String article_dir_path = path + "\\src\\main\\resources\\static\\article";
            String image_dir_path = path + "\\src\\main\\resources\\static\\images";
            String article_to_del = article_dir_path + "\\" + article_url;
            String image_dir_to_del = image_dir_path + "\\" + author_id + "\\" + article_id;

            File article_file = new File(article_to_del);
            if (article_file.exists())
                if (article_file.isFile())
                    article_file.delete();

            File image_file = new File(image_dir_to_del);
            if (image_file.exists())
                if (image_file.isDirectory()) {
                    this.deleteDir(image_file);
                }
            articleMapper.deleateById(article_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    @Override
    public int update_check(JSONArray ja) {
        for (int i = 0;i<ja.size();i++){
            JSONObject jsonObject = ja.getJSONObject(i);
            int article_id = (int)jsonObject.get("article_id");
            int check_status = (int)jsonObject.get("check");
            articleMapper.updateCheckById(article_id,check_status);
        }
        return 0;
    }
}