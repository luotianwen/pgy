<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="mstatDate" name="projectIncomeVO.statDate"/>
<s:hidden id="mprojectId" name="projectIncomeVO.projectId"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>修改配置</h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="loginId">项目名称</label>
	<div class="controls">
		<s:property value="projectIncomeVO.projectName"/><s:property value="projectIncomeVO.projectId"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="rateHigh">高</label>
	<div class="controls">
		<input id="rateHigh" type="text" class="input-medium" name="projectIncomeVO.rateHigh" size="30" value="<s:property value="projectIncomeVO.rateHigh"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="rateMid">中</label>
	<div class="controls">
		<input id="rateMid" type="text" class="input-medium" name="projectIncomeVO.rateMid" size="30" value="<s:property value="projectIncomeVO.rateMid"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="rateLow">低</label>
	<div class="controls">
		<input id="rateLow" type="text" class="input-medium" name="projectIncomeVO.rateLow" size="30" value="<s:property value="projectIncomeVO.rateLow"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="expectPrice">安装单价</label>
	<div class="controls">
		<input id="expectPrice" type="text" class="input-medium" name="projectIncomeVO.expectPrice" size="30" value="<s:property value="projectIncomeVO.expectPrice"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="minIncomeRate">最小收益</label>
	<div class="controls">
		<input id="minIncomeRate" type="text" class="input-medium" name="projectIncomeVO.minIncomeRate" size="30" value="<s:property value="projectIncomeVO.minIncomeRate"/>"/>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateConfig('<%=path %>','<s:property value="projectIncomeVO.statDate"/>','<s:property value="projectIncomeVO.projectId"/>');" class="btn btn-primary">确 定</button>
 <button type="reset" class="btn">重设</button>
</div>