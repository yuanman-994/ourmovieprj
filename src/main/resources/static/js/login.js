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
            $("a[name=tips]").empty()
            $("a[name=tips]").append(result)
            $("#login-button").empty()
            $("#login-button").append(name)
        }
    })
}

function logout() {
    $("#login-button").empty()
    $("#login-button").append("Login")
}
function get_user_inf() {//请求获取用户详细信息，目前只能获取用户名
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