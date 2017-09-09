<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
</head>
<title>Cloudmob</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=request.getContextPath()%>/css/style1.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<%--<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>--%>
<%--<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>--%>
<!--//webfonts-->
<link href="<%=request.getContextPath()%>/css/toastr.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/js/toastr.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});
</script>
<h1>Cloudmob</h1>
<div class="login-form">
	<div class="close"> </div>
	<div class="head-info">
		<label class="lbl-1"> </label>
		<label class="lbl-2"> </label>
		<label class="lbl-3"> </label>
	</div>
	<div class="clear"> </div>
	<div class="avtar">
		<img src="<%=request.getContextPath()%>/images/avtar.png" />
	</div>
	<s:form id="loginForm" action="/Login!login.action" method="post" theme="simple">
		<input type="text" id="loginId" class="text"name="portalUserVO.loginId" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}" >
		<div class="key">
			<input type="password" id="passwd" name="portalUserVO.passwd" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
		</div>
	</s:form>
	<div class="signin">
		<input type="submit"id="btn" value="Login" onclick="javascript:login();" >
	</div>
</div>
<div class="copy-rights">
	<p>Copyright &copy; 2016.Cloudmob Company name All rights reserved.</p>
</div>

<script src="<%=request.getContextPath()%>/js/kkgame-hz.js"></script>
<script src="<%=request.getContextPath()%>/js/toastr.js"></script>
<script language="javascript">
    var myForm = document.getElementById("loginForm");
    $("#loginId").focus();
    <s:if test="errorMsg != null && !errorMsg.equals('')">
    var message='<s:property value="errorMsg"/>';
    showErrorToastMiddle("",message);
    </s:if>
    function login() {
        var loginId = $("#loginId").val();
        if(loginId==""){
            showInfoToastMiddle("请输入用户名");
            $("#loginId").focus();
            return false;
        }
        var passwd = $("#passwd").val();
        if(passwd==""){
            showInfoToastMiddle("请输入密码");
            $("#passwd").focus();
            return false;
        }
        myForm.submit();
    }

    function VerifyData(){
        var loginId = $("#loginId").val();
        if(loginId==""){
            showInfoToastMiddle("请输入用户名");
            $("#loginId").focus();
            return false;
        }
        var passwd = $("#passwd").val();
        if(passwd==""){
            showInfoToastMiddle("请输入密码");
            $("#passwd").focus();
            return false;
        }
        return true;
    }
    if (document.addEventListener){//如果是Firefox
        document.addEventListener("keypress", fireFoxHandler, true);
    } else {
        document.attachEvent("onkeypress", ieHandler);
    }
    function fireFoxHandler(evt){
        //alert("firefox");    
        if (evt.which== 13){
            if(VerifyData()) {
                document.forms[0].submit();
            }
        }
    }
    function ieHandler(evt){
        if (evt.keyCode == 13) {
            if(VerifyData()) {
                document.forms[0].submit();
            }
        }
    }
</script>
</body>
</html>