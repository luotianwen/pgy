<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><script type="text/javascript" src="<%=path%>/js/util.js"></script>
<script type="text/javascript" src="<%=path%>/pages/user/js/res.js"></script>
<script type="text/javascript">
    $(function(){
	    $("#resName").focus();
	});
</script>
<s:hidden id="resId" name="resVO.id"></s:hidden>
<s:hidden id="parentId" name="resVO.parentId"></s:hidden>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="resVO.id>0">修改资源</s:if><s:else >新建资源</s:else></h2>
</div>

<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="resName">资源名称</label>
	<div class="controls">
            <input id="resName" type="text" class="input-medium"  name="resVO.resName"  value="${resVO.resName}" />
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="resDesc">资源描述</label>
	<div class="controls">
       <input id="resDesc" type="text" class="input-medium"  name="resVO.resDesc"  value="${resVO.resDesc}" />
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateRes('<%=path %>/user');" class="btn btn-primary"><s:if test="resVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>