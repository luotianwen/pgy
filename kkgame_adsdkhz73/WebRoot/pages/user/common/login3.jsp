<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>系统升级维护中</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
</HEAD>
<BODY>
系统升级维护中
<DIV id=div2 style="DISPLAY: none"></DIV>
<!--  </CONTENTTEMPLATE>-->
</BODY>
<script language="javascript">
var myForm = document.getElementById("portalUserForm");
<s:if test="errorMsg!=null && errorMsg!=''">
alert('<s:property value="errorMsg"/>');
</s:if>

	function VerifyData(){
		var loginId = document.getElementById("loginId").value;
		if(loginId==""){
			alert("请输入姓名");
			return false;
		}
		var passwd = document.getElementById("passwd").value;
		
		if(passwd==""){
			alert("请输入密码");
			return false;
		}
		return true;
	}

	function remove(){
		document.getElementById("loginId").value = "";
		document.getElementById("passwd").value = "";
		
	}
</script>
</HTML>
