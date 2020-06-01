function try_login() {
    var name = $(" input[ name='user_name' ] ").val()
    var password = $(" input[ name='password' ] ").val()
    var url = "/login?" + "user_name=" + name + "&" + "password=" + password
    if (name.length == 0 || password.length == 0)
        return
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            var data = JSON.parse(result);
            if (data.status == 0){
                $("#login-button").empty()
                $("#login-button").append(name)
                $("a[name=tips]").empty()
                $("a[name=tips]").append(data.msg)
                get_user_inf();
            } else {
                $("a[name=tips]").empty()
                $("a[name=tips]").append(data.msg)
            }
        }
    })
}

function logout() {
    $("#login-button").empty()
    $("#login-button").append("Login")
}
function get_user_inf() {//请求获取用户名,并修改部分前端逻辑
    $.ajax({
        url: "/get_user_inf",
        type: "GET",
        success: function (result) {
            if (result=="#no-login")
                return
            else{
                $("#login-button").empty()
                $("#login-button").append(result)
            }
        }
    })
}

function loginForAdminPage() {
    var name = $("#user-name").val()
    var password = $("#user-password").val()
    var url = "/admin_login?" + "user_name=" + name + "&" + "password=" + password
    if (name.length == 0 || password.length == 0)
        return;
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            var data = JSON.parse(result);
            if (data.status == 0){
                location.replace("/admin_index");
            } else {
               alert(data.msg);
            }
        }
    })
}