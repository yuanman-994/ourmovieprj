<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript"
            src="../static/js/jquery/jquery.min.js"></script>
</head>
<body>
<form>
    <input type="button" value="测试JSON交互" onclick="Movies.testJson();"/>
</form>
<div id="datatable"></div>
<script>
    $(function () {
        // User.testJson();
    });

    var Movies = {
        testJson: function () {
            //url:表示后台的url，就是 RequestMapping配置的内容
            // type：http请求的类型
            var id;
            $.ajax({
                url: "${pageContext.request.contextPath }/movies/test",
                data:{id:id},
                type: "post",
                async:false,
                // 定义发送请求的数据格式为JSON字符串
                contentType: "application/json;charset=UTF-8",
                //定义回调响应的数据格式为 JSON 字符串,该属性可以省略
                dataType: "json",
                //成功响应的结果
                success: function (data) {
                    var arr = data.list;
                    for(var i=0;i<data.list.length;i++){
                        var movieid=arr[i].id;
                        var name=arr[i].name;
                        console.log(movieid);
                        console.log(name);
                        var m= [];
                        m[i]="<ul>";
                        m[i]+="<li>"+"电影id："+movieid+"</li>";
                        m[i]+="<li>"+"电影名："+name+"</li>";
                        m[i]+="</ul>";
                        $('#datatable').append(m[i]);
                    }

            }});

        }
    };
</script>

</body>
</html>
