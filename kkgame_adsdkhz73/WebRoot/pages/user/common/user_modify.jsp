<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:hidden id="userId" name="portalUserVO.id" />
<div class="control-group">
	<label class="control-label" for="loginId">登录帐号</label>
	<div class="controls">
	<s:property value="portalUserVO.loginId"/><s:hidden id ="loginId" name ="portalUserVO.loginId" />
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="passwd">登录密码</label>
	<div class="controls">
	<input id="passwd" type="text" class="input-medium" name="portalUserVO.passwd" size="30"  value="<s:property value="portalUserVO.passwd"/>"/>	
</div>
</div>
<div class="control-group">
	<label class="control-label" for="status">状态</label>
	<div class="controls">
		<s:select id="status" list="#{'0':'正常','1':'受限','2':'锁定'}"  name="portalUserVO.status" value="0" cssStyle="width:165px"></s:select>
	</div>
</div>