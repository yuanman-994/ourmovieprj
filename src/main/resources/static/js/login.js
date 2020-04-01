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
        }
    })
}