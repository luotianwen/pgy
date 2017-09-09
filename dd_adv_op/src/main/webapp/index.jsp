<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>云视传媒广告SDK平台</title>
    <meta charset="UTF-8"/>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>


    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <link href="<%=request.getContextPath()%>/css/toastr.css" rel="stylesheet"/>
    <meta name="author" content="">
    <link rel='stylesheet' href='<%=request.getContextPath()%>/css/gsrc.css'>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/supersized.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

<div class="page-container">
    <h1>Cloudmob</h1>
    <form id="loginform" method="post" class="form-vertical" action="<%=request.getContextPath()%>/user/Login!login.action">
        <input type="text" name="userVo.loginId" id="loginId" class="username" placeholder="用户名">
        <input type="password" name="userVo.passwd" id="password" class="password" placeholder="密码">
        <button onclick="return login()">Sign me in</button>
        <div class="error"><span>+</span></div>
    </form>
    <div class="connect">
        <p>Or connect with:</p>
        <p>
            <a class="facebook" href=""></a>
            <a class="twitter" href=""></a>
        </p>
    </div>
</div>
<div align="center">Copyright © 2016.Cloudmob Company name All rights reserved.</div>

<!-- Javascript -->
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/js/supersized.3.2.7.min.js"></script>
<script src="<%=request.getContextPath()%>/js/supersized-init.js"></script>
<script src="<%=request.getContextPath()%>/js/scripts.js"></script>
<script src="<%=request.getContextPath()%>/js/kkpay.js"></script>
<%--<!-- jQuery 2.1.3 -->--%>
<script src="<%=request.getContextPath()%>/css/jQuery/jQuery-2.1.3.min.js"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js" type="text/javascript"></script>
<!-- iCheck -->
<script src="<%=request.getContextPath()%>/css/iCheck/icheck.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/toastr.js"></script>
<script>
$(function () {
$('input').iCheck({
checkboxClass: 'icheckbox_square-blue',
radioClass: 'iradio_square-blue',
increaseArea: '20%' // optional
});
});
</script>
<script>
    var myForm = document.getElementById("loginform");
    $("#loginId").focus();
    <s:if test="errorMsg != null && !errorMsg.equals('')">
    var message = '<s:property value="errorMsg"/>';
    showErrorToastMiddle("",message);
    </s:if>
    function login() {
        var username = $('#username').val().trim();
        var password = $('#password').val().trim();
        var errorMessage = "";
        if (username.length == 0) {
            errorMessage = "请输入用户名！";
            $('#username').focus();
        } else if (password.length == 0) {
            errorMessage = "请输入密码！";
            $('#password').focus();
        }
        myForm.submit();

    }
</script>
</body>
</html>