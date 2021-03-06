package com.movieprj.services;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieprj.beans.Article;
import com.movieprj.beans.ArticleComment;
import com.movieprj.mapper.ArticleCommentMapper;
import com.movieprj.mapper.ArticleMapper;
import com.movieprj.mapper.UserPasswordMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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
    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public Boolean saveArticle(String author_name, Map<String, String> data) {
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
    public String uploadImage(MultipartFile image, String basePath, String author_name, String article_id) {
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
        return "images\\articleImages\\" + author_id + "\\" + article_id + "\\" + imageName;
    }

    @Override
    public String getSelfArticleData(String author_name) {
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
    public String getAllArticleData() {
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
    public int updateRow(Map<String, String> row) {
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
    public String getTextById(int article_id) {

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
    public int uploadArticle(Map<String, String> data) {
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

            articleMapper.updateCheckById(article_id, 0);//修改审核状态为未审核

            articleMapper.updateTimeById(article_id);

            //文章内容修改后检测是否已被设为热门文章，如果是则从热门撤下
            List<Integer> Hot = articleMapper.getHot();
            for (int i=1;i<=6;i++)//热门文章固定有6个位置
                if (Hot.get(i-1)==article_id){
                    articleMapper.setHot(i,0);//
                }

            fwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public int delArticle(String[] ids) {//删除文章
        for (String id : ids) {
            //删除前先检测是否已被设为热门文章，如果是则先从热门撤下
            int article_id = Integer.valueOf(id);
            List<Integer> Hot = articleMapper.getHot();
            for (int i=1;i<=6;i++)//热门文章固定有6个位置
                if (Hot.get(i-1)==article_id){
                    articleMapper.setHot(i,0);//
                }
            this.delArticleById(article_id);
        }
        return 0;
    }

    public boolean delArticleById(int article_id) {
        try {
            String article_url = articleMapper.getUrlById(article_id);
            int author_id = articleMapper.getAuthorIdById(article_id);
            String path = System.getProperty("user.dir");
            String article_dir_path = path + "\\src\\main\\resources\\static\\article";
            String image_dir_path = path + "\\src\\main\\resources\\static\\images\\articleImages";
            String article_to_del = article_dir_path + "\\" + article_url;
            String image_dir_to_del = image_dir_path + "\\" + author_id + "\\" + article_id;
            String cover_to_del = path + "\\src\\main\\resources\\static\\images\\articleCoverImages\\" + article_id + ".jpeg";


            System.out.println(article_to_del);
            System.out.println(image_dir_to_del);
            System.out.println(cover_to_del);

            File article_file = new File(article_to_del);
            if (article_file.exists())
                if (article_file.isFile())
                    article_file.delete();

            File image_file = new File(image_dir_to_del);
            if (image_file.exists())
                if (image_file.isDirectory()) {
                    this.deleteDir(image_file);
                }

            File article_cover = new File(cover_to_del);
            if (article_cover.exists())
                if (article_cover.isFile())
                    article_cover.delete();

            articleCommentMapper.deleteCommentByArticle(article_id);
            articleMapper.deleteById(article_id);

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
    public int updateCheck(JSONArray ja) {
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jsonObject = ja.getJSONObject(i);
            int article_id = (int) jsonObject.get("article_id");
            int check_status = (int) jsonObject.get("check");
            articleMapper.updateCheckById(article_id, check_status);
        }
        return 0;
    }

    @Override
    public int uploadCover(MultipartFile image, String basePath, int article_id) {
        try {
            //获得文件类型，如果不是图片，禁止上传
            String contentType = image.getContentType();
            //获得文件的后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            //得到文件名
            String imageName = article_id + "." + suffixName;

            //图片绝对地址
            String path = basePath + "\\" + imageName;
            image.transferTo(new File(path));

            BufferedImage Bimage = (BufferedImage) ImageIO.read(new File(path));//图片放缩
            Bimage = this.Thumb(Bimage, 182, 268, false);
            ImageIO.write(Bimage, "jpeg", new File(path));

            String url = "images\\articleCoverImages\\" + imageName;
            articleMapper.saveArticleCoverById(article_id, url);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public String getCover(int article_id) {
        String url = articleMapper.getArticleCoverById(article_id);
        if (url == null)
            return "images\\articleCoverImages\\default.jpeg";
        else
            return url;
    }

    @Override
    public int getTotalPage(int type, int total_per_page) {
        int total = articleMapper.getTotalWithType(type);
        double result = (double) total / total_per_page;
        return (int) Math.ceil(result);
    }

    @Override
    public String getData(int type, int page_size, int currIndex) {
        List<Article> list = articleMapper.getByPageOnlyPassCheck((currIndex - 1) * page_size, page_size, type);
        JSONArray jsonArray = new JSONArray();
        for (Article a : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("article_id", a.getArticle_id());
            jsonObject.put("author_name", userPasswordMapper.findNameById(a.getAuthor_id()));
            jsonObject.put("headline", a.getHeadline());
            jsonObject.put("click_num", a.getClick_num());
            jsonObject.put("release_time", a.getRelease_time());
            String cover = a.getArticle_cover_image();
            if (cover == null)
                cover = "images\\articleCoverImages\\default.jpeg";
            jsonObject.put("cover", cover);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public String getSingle(int article_id) {
        Article a = articleMapper.getSingle(article_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("article_id", a.getArticle_id());
        jsonObject.put("author_name", userPasswordMapper.findNameById(a.getAuthor_id()));
        jsonObject.put("headline", a.getHeadline());
        jsonObject.put("click_num", a.getClick_num());
        jsonObject.put("release_time", a.getRelease_time());
        jsonObject.put("comment_num", articleCommentMapper.getCommentNumById(article_id));
        String cover = a.getArticle_cover_image();
        if (cover == null)
            cover = "images\\articleCoverImages\\default.jpeg";
        jsonObject.put("cover", cover);
        return jsonObject.toString();
    }

    @Override
    public String getUpdatedNews() {
        List<Article> list = articleMapper.getUpdatedNews();
        JSONArray jsonArray = new JSONArray();
        for (Article a : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("article_id", a.getArticle_id());
            jsonObject.put("author_name", userPasswordMapper.findNameById(a.getAuthor_id()));
            jsonObject.put("headline", a.getHeadline());
            jsonObject.put("click_num", a.getClick_num());
            jsonObject.put("release_time", a.getRelease_time());
            String cover = a.getArticle_cover_image();
            if (cover == null)
                cover = "images\\articleCoverImages\\default.jpeg";
            jsonObject.put("cover", cover);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    @Override
    public void submitComment(int article_id, int user_id, String content) {
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticle_id(article_id);
        articleComment.setContent(content);
        articleComment.setUser_id(user_id);
        articleCommentMapper.addComment(articleComment);
    }

    @Override
    public void addClick(int article_id) {
        int num = articleMapper.getClickNum(article_id);
        articleMapper.updateClickNum(article_id, num + 1);
    }

    public BufferedImage Thumb(BufferedImage source, int width, int height, boolean b) {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) width / source.getWidth();
        double sy = (double) height / source.getHeight();

        if (b) {
            if (sx > sy) {
                sx = sy;
                width = (int) (sx * source.getWidth());
            } else {
                sy = sx;
                height = (int) (sy * source.getHeight());
            }
        }

        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(width, height, type);
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    @Override
    public String getArticleWithLimit(int limit, int offset, String search) {
        List<Article> articles;
        int total;
        if (search == "") {
            articles = articleMapper.getByPage(limit, offset);
            total = articleMapper.getTotal();
        } else {
            articles = articleMapper.getByPageWithSearch(limit, offset, search);
            total = articleMapper.getSearchCount(search);
        }
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
            String cover = a.getArticle_cover_image();
            if (cover == null)
                cover = "images\\articleCoverImages\\default.jpeg";
            json.put("cover", cover);
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
        JSONObject json = new JSONObject();
        json.put("total", total);
        json.put("rows", jsonArray);
        return json.toString();
    }

    @Override
    public String getHotArticle() {
        List<Integer> idl = articleMapper.getHot();
        JSONArray jsonArray = new JSONArray();
        int t = 1;
        for (int i : idl) {
            if (i==0){//id为-1表示未设置热门文章
                JSONObject json = new JSONObject();
                json.put("article_id", 0);
                json.put("author_name", "未设置");
                json.put("headline", "未设置");
                json.put("release_time", "未设置");
                json.put("id", t++);
                jsonArray.add(json);
            }else {
                Article a = articleMapper.getSingle(i);
                int id = a.getAuthor_id();
                String name = userPasswordMapper.findNameById(id);
                JSONObject json = new JSONObject();
                if (a.getCheck_status()!=1){
                    //发现被设为热门的文章未过审时，视为未设置热门
                    json.put("article_id", 0);
                    json.put("author_name", "未设置");
                    json.put("headline", "未设置");
                    json.put("release_time", "未设置");
                    json.put("id", t++);
                    jsonArray.add(json);
                } else {
                    json.put("article_id", a.getArticle_id());
                    json.put("author_name", name);
                    json.put("headline", a.getHeadline());
                    json.put("release_time", a.getRelease_time());
                    json.put("id", t++);
                    jsonArray.add(json);
                }
            }
        }
        return jsonArray.toString();
    }

    @Override
    public int setHot(int id, int article_id) {
        Article article = articleMapper.getSingle(article_id);
        if (article==null&&article_id!=0)
           return -1;
        else if (article.getCheck_status()!=1){
            return -2;
        } else
            articleMapper.setHot(id,article_id);
        return 0;
    }
}

