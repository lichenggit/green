/**
 * Created by Administrator on 2017/2/18 0018.
 */
function reloadCode() {
    var time = new Date().getTime();
    document.getElementById("captcha_img").src = "${pageContext.request.contextPath}/user/verifyCode.pic?time=" + time;

}

function submitAfterCheckCode() {
    var verifyCode = $('#verifyCode').val();
    $.post("${pageContext.request.contextPath}/user/checkVerifyCode.json", {"verifyCode": verifyCode}, function (result) {
        if (result.code == 1) {
            alert(result.desc);
            //reloadCode();
        } else {
            $('#login_form').submit();
        }
    });
}
