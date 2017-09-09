<%@ page isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	int roleId = userVO.getRoleId();
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>商务拓展人员详情</h2>
</div>
<div class="modal-body">
<table class="table table-bordered" >
<tr >	
	<td class="table_td_title">姓名</td>			 
    <td colspan="3"><s:property value="bdVO.name"/></td>
</tr>
<tr >	
	<td class="table_td_title">所属代理商</td>	
	<td colspan="3"><s:property value="bdVO.agentName"/></td>
</tr>
<tr >
	<td class="table_td_title">拓展级别</td>			 
	<td colspan="3"><s:property value="bdVO.level"/></td>			
</tr>
<tr >
	<td class="table_td_title">电话号码</td>			 
    <td colspan="3"><s:property value="bdVO.telephone"/></td>			
</tr>
<tr >
	<td class="table_td_title">邮箱地址</td>			 
    <td colspan="3"><s:property value="bdVO.mail"/></td>			
</tr>
<%@ include file="../common/user_detail.jsp" %>

<tr >
	<td class="table_td_title">备注</td>
	<td colspan="3"><textarea id="description" name="bdVO.description" cols="80" rows="5" readonly="readonly"><s:property value="bdVO.description"/></textarea></td>
</tr>
</table>
</div>
<div class="modal-footer">
</div>