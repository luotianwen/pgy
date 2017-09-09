<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="<%=request.getContextPath() %>/css/pkig.css" rel="stylesheet" type="text/css">
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_updateStatus.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <s:form  theme="simple" id="updateStruts" action="user/User!struts2.action">
    <s:hidden id="id" name="userVo.id" />
    <table align="center" cellpadding="0" cellspacing="0" id="tb_content">
			<tbody>
				<tr>
					<td width="56%" nowrap>
					<div class="div_subtitle">当前位置<span class="arrow_subtitle">&gt;</span>用户列表
						<span class="arrow_subtitle">&gt;</span>注销用户
					</div>
					</td>
					<td width="44%" align="right" valign="bottom" nowrap="nowrap">&nbsp;</td>
				</tr>
			</tbody>			
		</table>
		<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
			<tr>
			<td class="td_head" colspan="8">
			<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
			</td>
			</tr>
			<s:if test="errorMsg != null&& !errorMsg.equals('') ">
				<tr >		 
		            <td width="85%" nowrap="nowrap" colspan="4">提示：<font color="red">${errorMsg }</font></td>
				</tr>	
			</s:if>	
		  <tr>
    		<td nowrap="nowrap" width="15%" class="td_title">姓名</td>
    		<td nowrap="nowrap" width="15%" class="td_title">用户名</td>
  		</tr>
		  <tr>
		    <td nowrap="nowrap" width="15%" class="td_title"><s:property value="userVo.realName"/></td>
		    <td nowrap="nowrap" width="15%" class="td_title"><s:property value="userVo.loginId"/> </td>
		   </tr>
		  <tr>
	    <td nowrap="nowrap" width="15%" class="td_title"><input type="submit" value="确定注销" id="btn" class="button" /></td>
	    <td nowrap="nowrap" width="15%" class="td_title">&nbsp;</td>
	    <td nowrap="nowrap" width="15%" class="td_title"><input type="button" value="返回" id="btn" class="button" onclick="javascript: history.back();"/></td>
	  </tr>
		</table>
		</s:form>		
  </body>
</html>
