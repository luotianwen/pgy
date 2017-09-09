<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<s:hidden id="id" name="agentVO.id"/>
<s:hidden id="uid" name="userVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="agentVO.id>0">修改代理商</s:if><s:else >新建代理商</s:else></h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cname">代理商名称<font color="red">*</font></label>
	<div class="col-sm-8">
		<input id="cname" type="text" class="form-control" name="agentVO.name" size="30" value="<s:property value="agentVO.name"/>" onblur="javascript:checkExistAgentName('<%=path%>',this.value);"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="ccontact">联系人<font color="red">*</font></label>
	<div class="col-sm-8">
		<input id="ccontact" type="text" class="form-control" name="agentVO.contact" size="30" value="<s:property value="agentVO.contact"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cmobile">手机号<font color="red">*</font></label>
	<div class="col-sm-8">
		<input id="cmobile" type="text" class="form-control" name="agentVO.mobile" size="30" value="<s:property value="agentVO.mobile"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cqq">QQ</label>
	<div class="col-sm-8">
		<input id="cqq" type="text" class="form-control" name="agentVO.qq" size="30" value="<s:property value="agentVO.qq"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cmail">mail</label>
	<div class="col-sm-8">
		<input id="cmail" type="text" class="form-control" name="agentVO.mail" size="30" value="<s:property value="agentVO.mail"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="caddress">地址</label>
	<div class="col-sm-8">
		<input id="caddress" type="text" class="form-control" name="agentVO.address" size="30" value="<s:property value="agentVO.address"/>"/>
		
	</div>
</div>

<div class="control-group">
	<label class="col-sm-3 control-label" for="loginId">登录帐号<font color="red">*</font></label>
	<div class="col-sm-8">
		<s:if test="userVO.id==0">
		<input type="text" class="form-control" id="loginId" name="userVO.loginId" size="30" value="${userVO.loginId}" onblur="javascript:checkExisitPU(this.value);"/>
		</s:if>
		<s:if test="userVO.id>0">
			<s:hidden id="loginId" name="userVO.loginId"></s:hidden>
			<p class="form-control-static">${userVO.loginId }</p>
		</s:if>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="passwd">登录密码<font color="red">*</font></label>
	<div class="col-sm-8">
		<input id="passwd" type="text" class="form-control" name="userVO.passwd" size="30" value="<s:property value="userVO.passwd"/>"/>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateAgent('<%=path %>');" class="btn btn-primary"><s:if test="agentVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>

<script type="text/javascript">
	
</script>