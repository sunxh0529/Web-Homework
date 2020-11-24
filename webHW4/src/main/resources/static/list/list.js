function add(elem) {
    var temp = document.createElement("form")
    temp.action = "/addNew"
    temp.method = "post"
    temp.style.display = "none"
    document.body.appendChild(temp)

    temp.submit()
    return temp
}

function edit(elem) {
    var row = elem.parentNode.parentNode.rowIndex - 1

    var temp = document.createElement("form")
    temp.action = "/edit"
    temp.method = "post"
    temp.style.display = "none"

    var opt = document.createElement("textarea")
    opt.name = "row"
    opt.value = row.toString()
    temp.appendChild(opt)
    document.body.appendChild(temp)
    temp.submit()
    return temp
}

function del(elem) {
    var row = elem.parentNode.parentNode.rowIndex - 1
    var tr = elem.parentNode.parentNode
    var tbody = tr.parentNode
    tbody.removeChild(tr)

    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = callBack
    xmlHttpRequest.open("post", "/del", true)
    xmlHttpRequest.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded")
    xmlHttpRequest.send("row=" + row)
}

function callBack() {
}
