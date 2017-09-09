<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Cloudmob</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
    	<link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet">
    	<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
   		<link href="<%=request.getContextPath()%>/css/toastr.css" rel="stylesheet">
   	<script src="<%=request.getContextPath()%>/js/toastr.js"></script>
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      		<script src="js/html5shiv.js"></script>
    	<![endif]-->
    	<style type="text/css">
    	.wrap {
    		background-image: url('<%=request.getContextPath()%>/images/login_bg.png');
    		background-repeat: repeat-x;
    		height:706px;
    	}
    	.login_middle {
   			background-image: url('<%=request.getContextPath()%>/images/login_form.png');
    		background-repeat:no-repeat;
    		height: 388px;
    	}
    	.login_button {
    		background-image: url('<%=request.getContextPath()%>/images/login_button.png');
    		background-repeat:no-repeat;
    		margin-left:390px;
    		height:55px;
    		width:152px;
    		border:solid 1px #000;
    	}
    	.container .credit {
	        margin: 20px 0;
	        text-align: center;
	      }
	      </style>
	</head>
	<body>
		<div id="wrap">
		<div class="container wrap">
		  <div>
		  	<img src="<%=request.getContextPath()%>/images/login_top.png"></img>
		  </div>
	      <div>
		        <s:form id="loginForm" action="/Login!login.action" method="post" theme="simple">
		        <div class="login_middle">
		        	<table width="100%">
		        		<tr>
		        			<td>
		        				<input id="loginId" name="portalUserVO.loginId" style="margin-top: 105px;margin-left: 390px;height: 30px;font-size: 20px;" type="text" class="input-large" >
		        			</td>
		        		</tr>
		        		<tr>
		        			<td>
		        				<input id="passwd" name="portalUserVO.passwd" style="margin-top: 7px;margin-left: 390px;height: 30px;font-size: 20px;vertical-align: middle" type="password" class="input-large">
		        				</td>
		        		</tr>
		        		<tr>
		        			<td>
		        				<img onclick="javascript:login();" style="margin-left: 390px" src="<%=request.getContextPath()%>/images/login_button.png"/>
		        			</td>
		        		</tr>
		        	 </table>
		       	</div>
		        </s:form>
	     	</div>
	      </div>
		</div>
		<div id="footer">
	      <div class="container">
	        <p class="muted credit">Copyright © 2011-2013. KOK All rights reserved.</p>
	      </div>
	    </div>
   	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
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