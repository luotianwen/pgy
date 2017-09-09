<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="sdkConfigVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
		<s:if test="sdkConfigVO.id==0">新建sdk配置</s:if>
		<s:if test="sdkConfigVO.id>0">修改sdk配置</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
		<table class="table">
			<tr>
				<td class="table_td_title"  style="width: 80px; color:red">拦截频率</td>
				<td>
					<input id="intercepterRate" type="number"  class="input-medium" name="sdkConfigVO.intercepterRate"
						   value="${sdkConfigVO.intercepterRate}"/>

				</td>
			</tr>
			<tr>
				<td class="table_td_title">push通知栏频率</td>
				<td>
					<input id="pushNoticeRate" type="number"  class="input-medium" name="sdkConfigVO.pushNoticeRate"
						   value="${sdkConfigVO.pushNoticeRate}"/>
				</td>
			</tr>
			<tr>
				<td class="table_td_title"  style="color:red">版本号</td>
				<td colspan="3">
					<input id="version" type="text" size="80" class="input-medium" name="sdkConfigVO.version"
						   value="${sdkConfigVO.version}"/>
				</td>
			</tr>

			<tr>

			<tr>
				<td colspan="4">
					<input id="createBtn" type="button" class="btn btn-primary" value="确 定"
						   onclick="javascript:updatesdkconfig('<%=path %>');">
				</td>
			</tr>
		</table>
	</form>
</div>
<div class="modal-footer">
</div>