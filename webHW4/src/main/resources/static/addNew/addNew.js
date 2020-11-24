function check(id) {
    var elem = document.getElementById(id);
    var pattern = elem.pattern;
    var infor = elem.value;
    var regex = new RegExp('^' + pattern + '$');
    var re = regex.exec(infor);
    var temp = null;

    if ('input-name' == id)
        temp = document.getElementById('name-valid');
    else if ('input-tel' == id)
        temp = document.getElementById('tel-valid');
    else if ('input-email' == id)
        temp = document.getElementById('email-valid');
    else if ('input-address' == id)
        temp = document.getElementById('address-valid');
    else if ('input-qq' == id)
        temp = document.getElementById('qq-valid');

    console.log("id = ", id);

    if ('' == infor)
    {
        temp.innerHTML = "请填写";
        temp.style.color = "rgb(15, 75, 221)";
    }
    else if (null != re)
    {
        temp.innerHTML = "通过";
        temp.style.color = "rgb(94,221,15)";
    }
    else if (null == re)
    {
        temp.innerHTML = "格式错误";
        temp.style.color = "rgb(255, 62, 62)";
    }
}