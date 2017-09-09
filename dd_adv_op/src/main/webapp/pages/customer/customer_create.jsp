<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserVO userVO = (UserVO)request.getSession().getAttribute("SESSION_USER");
%>

<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<s:hidden id="id" name="customerVO.id"/>
<s:hidden id="uid" name="userVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="customerVO.id>0">修改客户</s:if><s:else >新建客户</s:else></h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<%
if(userVO.getCustomerId()==0&&userVO.getAgentId()==0) {	
%>
<div class="control-group">
	<label class="col-sm-3 control-label" for="customer">代理商</label>
	<div class="col-sm-8">
		<s:select name="customerVO.agentId" id="agentId" list="#Option.agentList" listKey="id" listValue="name" cssStyle="width:150px" cssClass="form-control"></s:select>
	</div>
</div>
<%
}
%>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cname">客户名称</label>
	<div class="col-sm-8">
		<input id="cname" type="text" class="form-control" name="customerVO.name" size="30" value="<s:property value="customerVO.name"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="ccontact">联系人</label>
	<div class="col-sm-8">
		<input id="ccontact" type="text" class="form-control" name="customerVO.contact" size="30" value="<s:property value="customerVO.contact"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cmobile">手机号</label>
	<div class="col-sm-8">
		<input id="cmobile" type="text" class="form-control" name="customerVO.mobile" size="30" value="<s:property value="customerVO.mobile"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cqq">QQ</label>
	<div class="col-sm-8">
		<input id="cqq" type="text" class="form-control" name="customerVO.qq" size="30" value="<s:property value="customerVO.qq"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cmail">mail</label>
	<div class="col-sm-8">
		<input id="cmail" type="text" class="form-control" name="customerVO.mail" size="30" value="<s:property value="customerVO.mail"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="caddress">地址</label>
	<div class="col-sm-8">
		<input id="caddress" type="text" class="form-control" name="customerVO.address" size="30" value="<s:property value="customerVO.address"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cbalanceType">结算方式</label>
	<div class="col-sm-8">
		<input id="cbalanceType" type="text" class="form-control" name="customerVO.balanceType" size="30" value="<s:property value="customerVO.balanceType"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="cbalancePercent">结算比例</label>
	<div class="col-sm-8">
		<input id="cbalancePercent" type="text" class="form-control" name="customerVO.balanceType" size="30" value="<s:property value="customerVO.balanceType"/>"/>
	</div>
</div>

<div class="control-group">
	<label class="col-sm-3 control-label" for="loginId">登录帐号</label>
	<div class="col-sm-8">
		<s:if test="userVO.id==0">
		<input type="text" class="form-control" id="loginId" name="userVO.loginId" value="${userVO.loginId}" onblur="javascript:checkExisitPU(this.value);"/>
		</s:if>
		<s:if test="userVO.id>0">
			<s:hidden id="loginId" name="userVO.loginId"></s:hidden>
			<p class="form-control-static">${userVO.loginId }</p>
		</s:if>
	</div>
</div>
<div class="control-group">
	<label class="col-sm-3 control-label" for="passwd">登录密码</label>
	<div class="col-sm-8">
		<input id="passwd" type="text" class="form-control" name="userVO.passwd" size="30" value="<s:property value="userVO.passwd"/>"/>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateCustomer('<%=path %>');" class="btn btn-primary"><s:if test="customerVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>

<script type="text/javascript">
	
</script>