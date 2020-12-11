function onInput(self, target) {
    if ('' === self.val()) {
        target.text('请填写此栏')
        target.css('color', 'rgb(240, 120, 0)')
    }
    else if (true === isValidPattern(self[0])) {
        target.text('格式正确')
        target.css('color', 'rgb(15, 75, 221)')
    }
    else {
        target.text('格式错误')
        target.css('color', 'rgb(255, 62, 62)')
    }
}

function isValidPattern(self) {
    let pattern = self.pattern
    let info = self.value
    let regex = new RegExp('^' + pattern + '$')
    let re = regex.exec(info)

    if (null != re)
        return true
    else
        return false
}

// 绑定
$('#u-name').on('input', function () {
    onInput($('#u-name'), $('#format-name'))
})

$('#u-phone').on('input', function () {
    onInput($('#u-phone'), $('#format-phone'))
})

$('#u-email').on('input', function () {
    onInput($('#u-email'), $('#format-email'))
})

$('#u-address').on('input', function () {
    onInput($('#u-address'), $('#format-address'))
})

$('#u-qq').on('input', function () {
    onInput($('#u-qq'), $('#format-qq'))
})

$("#u-phone").change(function () {
    if (true === isValidPattern($("#u-phone")[0])) {
        let tel = $("#u-phone").val()
        $.ajax({
            type: 'POST',
            url: '/checktel',
            data: {'tel': tel},
            success: function (data) {
                let elem = $('#format-phone')
                if (1 === data) {
                    elem.text('该电话号码已存在')
                    elem.css('color', 'rgb(255, 0, 0)')
                } else {
                    elem.text('该电话号码可用')
                    elem.css('color', 'rgb(0, 255, 0)')
                }
            }
        })
    }
})
