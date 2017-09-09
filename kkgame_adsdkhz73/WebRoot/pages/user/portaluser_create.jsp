<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>新建技术人员</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css> 
</STYLE>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
<link href="<%=request.getContextPath()%>/css/kkfun.css"
  rel="stylesheet" type="text/css" />
  <script type="text/javascript"  language="javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.js"></script>
  
</HEAD>
<BODY style="	margin-left: 3px;margin-top: 0px;margin-right: 3px;margin-bottom: 0px;">
<s:form id="technicianForm" action="PortalUser!save.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden name="portalUserVO.roleType"></s:hidden>
	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="4">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
		<span id="lblmanagetitle" style="">新建
		<s:if test='portalUserVO.roleType == "MG"'>商务助理</s:if>
		<s:elseif test='portalUserVO.roleType == "FD"'>财务人员</s:elseif>
		</span></td>
		</tr>
	 <tr >
	<td height="25" width="15%"  class="td_title">姓名<font color="red">*</font></td>
    <td height="25" width="35%" colspan="3"><input id="name" name="portalUserVO.name" size="30"  /></td>
 </tr>
 <tr >	
<%@ include file="common/user_create.jsp" %>
		<tr >
		    <td  colspan="4">
			<div align="center">
		       <input id="btn" class="button" type="submit" value=" 创  建  " />&nbsp;&nbsp;
			<input type="button" class="button" id="backButton" value=" 返 回  " onclick="javascript: history.back()"/>
		    </div>
			</td>
		</tr>
	</table>
</s:form>
</BODY>

<script type="text/javascript">

function VerifyData(form){

	if (form["portalUserVO.loginId"].value==""){
		alert("请填写登陆账号！");
		form["portalUserVO.loginId"].focus();
		return false;
	}

	if (form["portalUserVO.passwd"].value==""){
		alert("请填写登陆密码！");
		form["portalUserVO.passwd"].focus();
		return false;
	}
	return true;
}






</script>
</HTML>
