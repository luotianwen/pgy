<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Untitled Page</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css> 
</STYLE>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
<link href="<%=request.getContextPath()%>/css/kkfun.css"
  rel="stylesheet" type="text/css" />
  
<script type="text/javascript"  language="javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.js"></script>

</HEAD>
<BODY style="	margin-left: 3px;margin-top: 0px;margin-right: 3px;margin-bottom: 0px;">
<s:form id="technicianForm" action="Technician!update.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="technicianVO.id"/>
<s:hidden name="portalUserVO.roleType"></s:hidden>

	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="4">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
		<span id="lblmanagetitle" style="">修改
		<s:if test='portalUserVO.roleType == "MG"'>商务助理</s:if>
		<s:elseif test='portalUserVO.roleType == "FD"'>财务人员</s:elseif>
		信息</span></td>
		</tr>
		
<%@ include file="common/user_modify.jsp" %>

		<tr >
		    <td  colspan="4">
			<div align="center">
		       <input id="btn" class="button" type="submit" value=" 修 改 "  />&nbsp;&nbsp;
			<input type="button" class="button" id="backButton" value=" 返 回  " onclick="javascript: history.back()"/>
		    </div>
			</td>
		</tr>
	</table>
   

</s:form>
</BODY>

<script type="text/javascript">

function VerifyData(form){

	if (form["portalUserVO.passwd"].value==""){
		alert("请填写登陆密码！");
		form["portalUserVO.passwd"].focus();
		return false;
	}
	return true;
}


function update(){
	var myform = document.getElementById("technicianForm");
	myform.action =  "Technician!update.action";
//	var action = "Technician!update.action";
var text = HttpSubmit(myform,myform.action);
}


</script>
</HTML>
