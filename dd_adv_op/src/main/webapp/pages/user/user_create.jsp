<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<script type="text/javascript" src="<%=path%>/pages/user/js/user.js?version=1.1"></script>
<s:hidden id="id" name="userVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="userVO.id>0">修改用户</s:if><s:else >新建用户</s:else></h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="loginId">登录帐号</label>
	<div class="controls">
		<s:if test="userVO.id==0">
		<input type="text" class="input-medium" id="loginId" name="userVO.loginId" value="${userVO.loginId}" onblur="javascript:checkExisitPU(this.value);"/>
		</s:if>
		<s:if test="userVO.id>0">
			<s:hidden id="loginId" name="userVO.loginId"></s:hidden>
			${userVO.loginId }
		</s:if>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="passwd">登录密码</label>
	<div class="controls">
		<input id="passwd" type="text" class="input-medium" name="userVO.passwd" size="30" value="<s:property value="userVO.passwd"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="passwd">姓名</label>
	<div class="controls">
		<input id="realName" type="text" class="input-medium" name="userVO.realName" value="<s:property value="userVO.realName"/>"/>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateUser('<%=path %>');" class="btn btn-primary"><s:if test="userVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>