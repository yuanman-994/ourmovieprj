//用于动态创建影城动态页面部分内容的一些函数

//文章样式
var line = '<div class="wthree-news-top-left">{0}{1}{2}</div>'
var leftOrRight = '<div class="col-md-6 w3-agileits-news-left">\n' +
    '                                        <div class="col-sm-5 wthree-news-img">\n' +
    '                                            <a href="/news_single?article_id={5}" onclick="addClick({5})"><img src="{0}" alt="" /></a>\n' +
    '                                        </div>\n' +
    '                                        <div class="col-sm-7 wthree-news-info">\n' +
    '                                            <h5><a href="/news_single?article_id={5}" onclick="addClick({5})">{1}</a></h5>\n' +
    '                                            <h4>{2}</h4>\n' +
    '                                            <ul>\n' +
    '                                                <li><i class="fa fa-clock-o" aria-hidden="true"></i>{3}</li>\n' +
    '                                                <li><i class="fa fa-eye" aria-hidden="true"></i>{4}</li>\n' +
    '                                            </ul>\n' +
    '                                        </div>\n' +
    '                                        <div class="clearfix"> </div>\n' +
    '                                    </div>';
var end = '<div class="clearfix"> </div>';

//最新更新文章的样式
var updated_news = '<div class="date-text">\n' +
    '                                        <a href="/news_single?article_id={0}" onclick="addClick({5})">{1}<span class="blinking"><img\n' +
    '                                                src="images/new.png" alt=""/></span></a>\n' +
    '                                        <p>{2}</p>\n' +
    '                                    </div>';

String.format = function (src) {

    if (arguments.length == 0) return null;

    var args = Array.prototype.slice.call(arguments, 1);

    return src.replace(/\{(\d+)\}/g, function (m, i) {

        return args[i];

    });

};

function loadArticles(data, id) {//data:数据，对象数组 id:字符串，上层容器id
    var i;
    var len = data.length;
    $("#" + id).empty();//移除之前的内容
    for (i = 0; i < len; i += 2) {
        if (i + 1 < len) {//还剩超过两个
            var article_left = data[i];
            var article_right = data[i + 1];
            var left = getString(article_left);
            var right = getString(article_right);
            var temp = String.format(line, left, right, end);
            $("#" + id).append(temp);
        } else {//只有一个了
            var article = data[i];
            var s = getString(article);
            var temp = String.format(line, s, "", end);
            $("#" + id).append(temp);
        }
    }
}

function loadUpdated(data, id) {//需加载的数据，容器id
    var i;
    var len = data.length;
    $("#" + id).empty();//移除之前的内容

    for (i = 0; i < len; i++) {
        var article = data[i];
        var str = String.format(updated_news, article.article_id, article.release_time, article.headline)
        $("#" + id).append(str);
    }
}

function addClick(article_id) {
    $.post("/article/addClick?article_id=" + article_id);
}

function getString(article) {
    return String.format(leftOrRight, article.cover, article.headline, article.author_name, article.release_time, article.click_num, article.article_id);
}

var hotArticle = '<div class="top-news-grid">\n' +
    '                                <div class="top-news-grid-heading">\n' +
    '                                    <a href="/news_single?article_id={0}">{1}</a>\n' +
    '                                </div>\n' +
    '                                <div class="w3ls-news-t-grid top-t-grid">\n' +
    '                                    <ul>\n' +
    '                                        <li><i class="fa fa-clock-o"></i> {2}</a></li>\n' +
    '                                        <li><i class="fa fa-user"></i> {3}</a></li>\n' +
    '                                    </ul>\n' +
    '                                </div>\n' +
    '                            </div>'

function loadHotArticle(id) {
    $.get("/article/get_hot_articles", function (data) {
        var i;
        var articles = JSON.parse(data);
        var len = articles.length;
        $("#" + id).empty();//移除之前的内容
        for (i = 0; i < len; i++) {
            var article = articles[i];
            if (article.article_id == 0)//未设置热门
                continue;
            var str = String.format(hotArticle, article.article_id, article.headline, article.release_time, article.author_name);
            $("#" + id).append(str);
        }
    })
}