//用于动态创建影城动态页面部分内容的一些函数
// <div role="tabpanel" class="tab-pane fade in active" id="home1" aria-labelledby="home1-tab">
//    <div class="wthree-news-top-left">
//      <div class="col-md-6 w3-agileits-news-left">
//          <div class="col-sm-5 wthree-news-img">
//              <a th:href="@{/news_single}"><img src="images/m1.jpg" alt="" /></a>
//          </div>
//          <div class="col-sm-7 wthree-news-info">
//              <div>
//                  <h5><a th:href="@{/news_single}">文章1</a></h5>
//                  <ul>
//                      <li><i class="fa fa-clock-o" aria-hidden="true"></i> 24/09/2016</li>
//                      <li><i class="fa fa-eye" aria-hidden="true"></i> 2642</li>
//                  </ul>
//              </div>
//          </div>
//          <div class="clearfix"> </div>
//     </div>
//     <div class="col-md-6 w3-agileits-news-left">
//          <div class="col-sm-5 wthree-news-img">
//              <a th:href="@{/news_single}"><img src="images/m1.jpg" alt="" /></a>
//          </div>
//          <div class="col-sm-7 wthree-news-info">
//              <div>
//                  <h5><a th:href="@{/news_single}">文章2</a></h5>
//                  <ul>
//                      <li><i class="fa fa-clock-o" aria-hidden="true"></i> 24/09/2016</li>
//                      <li><i class="fa fa-eye" aria-hidden="true"></i> 2642</li>
//                  </ul>
//              </div>
//          </div>
//          <div class="clearfix"> </div>
//     </div>
//     <div class="clearfix"> </div>
//   </div>
// </div>
var line = '<div class="wthree-news-top-left">{0}{1}{2}</div>'
var leftOrRight = '<div class="col-md-6 w3-agileits-news-left">\n' +
    '                                        <div class="col-sm-5 wthree-news-img">\n' +
    '                                            <a th:href="@{/news_single}"><img src="{0}" alt="" /></a>\n' +
    '                                        </div>\n' +
    '                                        <div class="col-sm-7 wthree-news-info">\n' +
    '                                            <h5><a th:href="@{/news_single}">{1}</a></h5>\n' +
    '                                            <h4>{2}</h4>\n' +
    '                                            <ul>\n' +
    '                                                <li><i class="fa fa-clock-o" aria-hidden="true"></i>{3}</li>\n' +
    '                                                <li><i class="fa fa-eye" aria-hidden="true"></i>{4}</li>\n' +
    '                                            </ul>\n' +
    '                                        </div>\n' +
    '                                        <div class="clearfix"> </div>\n' +
    '                                    </div>';
var end = '<div class="clearfix"> </div>';

String.format = function (src) {

    if (arguments.length == 0) return null;

    var args = Array.prototype.slice.call(arguments, 1);

    return src.replace(/\{(\d+)\}/g, function (m, i) {

        return args[i];

    });

};

function loadArticles(data, id, lineclass) {//data:数据，对象数组 id:字符串，上层容器id lineclass:行容器类型
    var i;
    var len = data.length;
    $("#" + id).empty();//移除之前的内容
    for (i = 0; i < len; i += 2) {
        if (i+1 < len) {//还剩超过两个
            var article_left = data[i];
            var article_right = data[i+1];
            var left = getString(article_left);
            var right = getString(article_right);
            var temp = String.format(line, left, right, end);
            $("#" + id).append(temp);
        } else {//只有一个了
            var article = data[i];
            var s = getString(article);
            var temp = String.format(line, s, "",end);
            $("#" + id).append(temp);
        }
    }
}

function getString(article) {
    return String.format(leftOrRight, article.cover,article.headline,article.author_name,article.release_time,article.click_num);
}