<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<s:hidden id="id" name="projectVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>项目转化率修改</h2>
</div>

<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	<div class="control-group">
		<label class="control-label" for="sdateKey">name</label>
		<div class="controls">
			 <s:property value="projectVO.id"/>/<s:property value="projectVO.name"/>
		</div>
	</div>
	 
	<div class="control-group">
		<label class="control-label" for="rateHighKey">结算转化率-高</label>
		<div class="controls">
			<input id="rateHigh" type="text" class="input-medium" name="projectVO.rateHigh" onkeypress="" size="11" value="<s:property value="projectVO.rateHigh"/>"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="apkidKey">结算转化率-中</label>
		<div class="controls">
			<input id="rateMid" type="text" class="input-medium" name="projectVO.rateMid" onkeypress=""  size="11" value="<s:property value="projectVO.rateMid"/>"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="apkidKey">结算转化率-低</label>
		<div class="controls">
			<input id="rateLow" type="text" class="input-medium" name="projectVO.rateLow" onkeypress=""  size="11" value="<s:property value="projectVO.rateLow"/>"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="apkidKey">最低利润率</label>
		<div class="controls">
			<input id="minIncomeRate" type="text" class="input-medium" name="projectVO.minIncomeRate" onkeypress=""  size="11" value="<s:property value="projectVO.minIncomeRate"/>"/>
		</div>
	</div>
	 
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateProduct('<%=path %>');" class="btn btn-primary"><s:if test="projectVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 