toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

function search_music() {
    // 声明变量
    let input, filter, table, tr, td, i;
    input = document.getElementById("input_music");
    filter = input.value.toUpperCase();
    table = document.getElementById("music_table");
    tr = table.getElementsByTagName("tr");

    // 循环表格每一行，查找匹配项
    for (i = 1; i < tr.length; i++) {
        let flag = false;
        for (let x = 0; x < tr[i].getElementsByTagName("td").length; x++) {
            if (x == 1) continue;
            td = tr[i].getElementsByTagName("td")[x];
            if (td) {
                if (x == 4) {
                    let str = tr[i].getElementsByTagName("td")[x].getElementsByTagName("a")[0].text;
                    if (str.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                        flag = true;
                    }
                    else tr[i].style.display = "none";
                }
                else if (td.textContent.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    flag = true;
                } else {
                    tr[i].style.display = "none";
                }
            }
            if (flag) break;
        }
    }
}

function check(p) {
    if (p != null && p !== "") return true;
    return false;
}

function add_music(a = "addMusic") {
    let title = document.getElementById("title").value;
    let time = document.getElementById("time").value;
    let author = document.getElementById("author").value;
    let album = document.getElementById("album_name").value;
    let wid = document.getElementById("wid").value;
    if (check(title) && check(author) && check(album) && check(wid)) {
        $("#addButton")[0].disabled = true;
        $("#updateButton")[0].disabled = true;
        $("#delButton")[0].disabled = true;
        $.ajax({
            url:  a,
            data: {'title': title, 'time': time, 'author': author, 'album_name': album, 'wid':wid},
            type: 'post',
            dataType: 'json',
            timeout: 5000,
            cache: false,
            success:function (data) {
                console.log(data);
                let json = JSON.parse(data);
                if (json.status) {
                    if (a == "addMusic") {
                        toastr["success"](json.msg, "成功");
                    }
                    else {
                        toastr["success"]("数据修改成功！", "成功");
                    }
                    setTimeout('document.location.reload()',5000)
                }
                else {
                    if (a == "addMusic") toastr["warning"](json.msg, "失败");
                    else toastr["warning"]("数据修改失败！检查该数据是否已经存在", "失败");
                    $("#addButton")[0].disabled = false;
                    $("#updateButton")[0].disabled = false;
                    $("#delButton")[0].disabled = false;
                }
            },
            error: function(result) {
                toastr["warning"]("未知原因的错误，请稍后尝试", "失败");
                $("#addButton")[0].disabled = false;
                $("#updateButton")[0].disabled = false;
                $("#delButton")[0].disabled = false;
            },
        })
    }
    else {
        document.getElementById("alert").style = "";
        document.getElementById("alert-span").textContent = "数据不能为空！"
    }
}
// 转给 add_music 干活
function update_music() {
    add_music("update_music");
}
// 只需要wid删除
function del_music() {
    let wid = document.getElementById("wid").value;
    $("#addButton")[0].disabled = true;
    $("#updateButton")[0].disabled = true;
    $("#delButton")[0].disabled = true;
    $.ajax({
        url:  "delMusic",
        data: {'wid':wid},
        type: 'post',
        dataType: 'json',
        timeout: 5000,
        cache: false,
        success:function (data) {
            console.log(data);
            let json = JSON.parse(data);
            if (json.status) {
                toastr["success"](json.msg, "成功");
                setTimeout('document.location.reload()',5000)
            }
            else {
                toastr["warning"]("数据删除失败！", "失败");
                $("#addButton")[0].disabled = false;
                $("#updateButton")[0].disabled = false;
                $("#delButton")[0].disabled = false;
            }
        },
        error: function() {
            toastr["warning"]("未知原因的错误，请稍后尝试", "失败");
            $("#addButton")[0].disabled = false;
            $("#updateButton")[0].disabled = false;
            $("#delButton")[0].disabled = false;
        },
    })
}


$("#exampleModal").on('show.bs.modal',function (e) {
    /*
        1.如果是 a标签，则显示修改和删除，隐藏添加，并把标题修改为 修改/添加数据 同时把数据那一行拷贝到form表单中
        2.如果是 button id=addButtonTrigger 则显示添加，隐藏修改和删除，把标题修改为 添加数据
     */
    let t = e.relatedTarget;
    if (t == undefined) return;
    if (t.tagName == 'A') {
        let tr = t.parentElement.parentElement;
        let td = tr.getElementsByTagName("td");
        let title = td[0].textContent;
        let time = td[1].textContent;
        let author = td[2].textContent;
        let album = td[3].textContent;
        let wid = td[4].children[0].textContent;
        $("#wid")[0].disabled = true;
        $("#title")[0].value = title;
        $("#time")[0].value = time;
        $("#author")[0].value = author;
        $("#album_name")[0].value = album;
        $("#wid")[0].value = wid;
        $("#exampleModalLabel")[0].textContent = "修改/删除 数据"
        $("#addButton")[0].style = "display:none"
        $("#updateButton")[0].style = "";
        $("#delButton")[0].style = "";
    }
    else {
        $("#wid")[0].disabled = false;
        $("#title")[0].value = "";
        $("#time")[0].value = "";
        $("#author")[0].value = "";
        $("#album_name")[0].value = "";
        $("#wid")[0].value = "";
        $("#exampleModalLabel")[0].textContent = "添加 数据"
        $("#addButton")[0].style = ""
        $("#updateButton")[0].style = "display:none";
        $("#delButton")[0].style = "display:none";
    }
    console.log(e.relatedTarget);
    // if ($(".modal-backdrop").length > 1) {
    //     $(".modal-backdrop").not(':first').remove();
    // }
    // $(this).off('show.bs.modal');  // 由于该方法会被执行两次，需要解绑以防止重复触发
})