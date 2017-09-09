<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="sdkdomainVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="sdkdomainVO.id>0">修改sdk域名配置</s:if><s:else >新建sdk域名配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	<div class="control-group">
		<label class="control-label" for="downloadKey">下载域名（http://apk.google8cn.com）</label>
		<div class="controls">
			<input id="download" type="text" class="input-medium" name="sdkdomainVO.download"  size="50" value="<s:property value="sdkdomainVO.download"/>"/>
		</div>
	</div>

	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatesdkdomain('<%=path %>');" class="btn btn-primary"><s:if test="sdkdomainVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
