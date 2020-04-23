//用于动态创建分页对象的一系列函数,样式参考下方注释
// <ul class="pagination pagination-lg id="mypage">
// <li class="disabled"><a href="#"><i class="fa fa-angle-left">«</i></a></li>
// <li class="active"><a href="#">1</a></li>
// <li><a href="#">2</a></li>
// <li><a href="#">3</a></li>
// <li><a href="#">4</a></li>
// <li><a href="#">5</a></li>
// <li><a href="#"><i class="fa fa-angle-right">»</i></a></li>
// </ul>
//需引入jquery
function creat(total,now,max,id,funcname) {//创建分页对象,total:总页数,now:当前在第几页，max:显示多少页，id:对象id funcname:渲染页面的函数名（string）,可自行实现，需包括now,max参数
    $("#"+id).empty();
    var start,end;//显示的起始，终止页
    //计算显示的起始页
    if (total<=max){
        start = 1;
        end = total;
    } else {
        if (now <= parseInt(max/2)){
            start = 1;
            end = max;
        }
        else if (now>=total-parseInt(max/2)){
            start = total - max + 1;
            end = total;
        } else {
            start = now - parseInt(max/2) +1;
            end = start + max -1;
        }
    }
    //填充html


    if(start==1)//创建前一页箭头内容
        $("#"+id).append('<li class="disabled"><a href="#"><i class="fa fa-angle-left">«</i></a></li>');
    else
        $("#"+id).append('<li><a href="#"'+get_string(funcname,now-1,max)+'><i class="fa fa-angle-left">«</i></a></li>');

    //创建各分页
    var i;
    for (i=0;start+i<=end;i++){
        if (start+i==now)
            $("#"+id).append('<li class="active"><a href="#"'+get_string(funcname,start+i,max)+'>'+(start+i)+'</a></li>');
        else
            $("#"+id).append('<li><a href="#"'+get_string(funcname,start+i,max)+'>'+(start+i)+'</a></li>');
    }
    if(now==total)//创建后一页箭头内容
        $("#"+id).append('<li class="disabled"><a href="#"><i class="fa fa-angle-right">«</i></a></li>');
    else
        $("#"+id).append('<li><a href="#"'+get_string(funcname,now+1,max)+'><i class="fa fa-angle-right">«</i></a></li>');
}

function get_string(funcname,aim,max) {
    return 'onclick="'+funcname+'('+aim+','+max+')"'
}
