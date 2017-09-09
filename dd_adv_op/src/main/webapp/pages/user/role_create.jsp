<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<script type="text/javascript" src="<%=path%>/pages/user/js/role.js"></script>

<script type="text/javascript">
    $(function(){
	    $("#roleName").focus();
	});
</script>
<s:hidden id="roleId" name="roleVO.id"></s:hidden>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="roleVO.id>0">修改角色</s:if><s:else >新建角色</s:else></h2>
</div>

<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="roleName">角色名称</label>
	<div class="controls">
            <input id="roleName" type="text" class="input-medium"  name="roleVO.roleName"  value="${ roleVO.roleName}" />
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="roleDesc">角色描述</label>
	<div class="controls">
       <input id="roleDesc" type="text" class="input-medium"  name="roleVO.roleDesc"  value="${ roleVO.roleDesc}" />
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateRole('<%=path %>');" class="btn btn-primary"><s:if test="roleVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>