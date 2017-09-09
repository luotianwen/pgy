<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserVO userVO = (UserVO)request.getSession().getAttribute("SESSION_USER");
%>
<s:hidden id="id" name="productVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="productVO.id>0">修改应用</s:if><s:else >新建应用</s:else></h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>

<s:if test="ProductVO.id>0">
<div class="control-group">
	<label class="col-sm-3 control-label" for="id">APP ID</label>
	<div class="col-sm-8">
		<p class="form-control-static"><s:property value="productVO.id"/></p>
	</div>
</div>
</s:if>
<div class="control-group">
	<label class="col-sm-3 control-label" for="appname">应用名称</label>
	<div class="col-sm-8">
		<input id="appname" type="text" class="form-control" name="productVO.name" size="30" value="<s:property value="productVO.name"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="highPrice">高</label>
	<div class="col-sm-8">
		<input id="highPrice" type="text" class="form-control" name="productVO.highPrice" size="30" value="<s:property value="productVO.highPrice"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="midPrice">中</label>
	<div class="col-sm-8">
		<input id="midPrice" type="text" class="form-control" name="productVO.midPrice" size="30" value="<s:property value="productVO.midPrice"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="lowPrice">低</label>
	<div class="col-sm-8">
		<input id="lowPrice" type="text" class="form-control" name="productVO.lowPrice" size="30" value="<s:property value="productVO.lowPrice"/>"/>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateProduct('<%=path %>');" class="btn btn-primary"><s:if test="productVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>