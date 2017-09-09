<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="appNameVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
		<s:if test="appNameVO.id==0">新建包名</s:if>
		<s:if test="appNameVO.id>0">修改包名</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
		<table class="table">
			<tr>
				<td class="table_td_title"  style="width: 80px; color:red">APP名称</td>
				<td>
					<input id="appName" type="text"  class="input-medium" name="appNameVO.appName"
						   value="${appNameVO.appName}"/>

				</td>
			</tr>
			<tr>
				<td class="table_td_title">APP包名</td>
				<td>
					<input id="packageName" type="text"  class="input-medium" name="appNameVO.packageName"
						   value="${appNameVO.packageName}"/>
				</td>
			</tr>

			<tr>

			<tr>
				<td colspan="4">
					<input id="createBtn" type="button" class="btn btn-primary" value="确 定"
						   onclick="javascript:updateappname('<%=path %>');">
				</td>
			</tr>
		</table>
	</form>
</div>
<div class="modal-footer">
</div>