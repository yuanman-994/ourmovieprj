function getParam(url, name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"),
        r = url.substr(url.indexOf("?") + 1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return '';
}