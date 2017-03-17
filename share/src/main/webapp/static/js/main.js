/**
 * Created by licheng on 2016/7/29 0029.
 */

/**
 * 提交确认
 * @type {{comfirm: $.do.comfirm}}
 */
$.do = {
    /**
     * 提交前确认，返回后弹框
     * @param url
     * @param json
     * @param comfirmText
     * @param successText
     */
    comfirm: function (url, json, comfirmText, successText) {
        swal({
                title: comfirmText,
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false
            },
            function () {
                $.post(url, json, function (data, status, xhr) {
                    if (status == "success") {
                        swal(successText, "", "success")
                    }
                });
            });
    }
}


/**
 * load  加载方法   越连接    form表单提交
 * @type {{form: $.load.form, a: $.load.a, a: $.load.a}}
 */
$.load = {
    /**
     * form 表单提交
     * @param formid
     */
    form: function (formid) {
        var url = $('#' + formid).attr("action");
        var result = $('#' + formid).serializeArray();
        $('#page-wrapper').load(url, result);
    },
    /**
     * form 表单提交,带结果弹框
     * @param formid
     * @param successtext
     */
    form: function (formid, successtext) {
        var url = $('#' + formid).attr("action");
        var result = $('#' + formid).serializeArray();
        $.load.aShowAlert(url, result, successtext);
    },
    /**
     * 表单重置
     * @param formId
     */
    reset: function (formId) {
        $(':input', '#' + formId)
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
    },
    /**
     * popForm 弹窗表单提交
     * @param formid
     */
    popForm: function (formid) {
        var url = $('#' + formid).attr("action");
        var result = $('#' + formid).serializeArray();
        $('#popupContent').load(url, result);
    },
    /**
     * 越连接
     * @param url
     */
    a: function (url) {
        $('#page-wrapper').load(url);
    },
    /**
     * 带参数的越连接
     * @param url
     * @param json
     */
    a: function (url, json) {
        $('#page-wrapper').load(url, json);
    },
    /**
     * 带成功弹框的越连接
     * @param url
     * @param successtext
     */
    aShowAlert: function (url, successtext) {
        $('#page-wrapper').load(url, function (data, status, xhr) {
            if (status == "success") {
                swal(successtext, "", "success")
            }
        });
    },
    /**
     * 带成功弹框的带参数的越连接
     * @param url
     * @param json
     * @param successtext
     */
    aShowAlert: function (url, json, successtext) {
        $('#page-wrapper').load(url, json, function (data, status, xhr) {
            if (status == "success") {
                swal(successtext, "", "success");
            }
        });
    }
}
