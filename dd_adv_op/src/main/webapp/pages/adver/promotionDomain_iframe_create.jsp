<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="promotionDomainVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="promotionDomainVO.id>0">修改链接域名配置</s:if><s:else >新建链接域名配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	
 
	
	<div class="control-group">
		<label class="control-label" for="download">域名</label>
		<div class="controls">
			<input id="download" type="text" class="input-medium" name="promotionDomainVO.download"    size="50" value="<s:property value="promotionDomainVO.download"/>"/>
		</div>
	</div>
	 
	
	

	
	
	   
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatepromotionDomain('<%=path %>');" class="btn btn-primary"><s:if test="promotionDomainVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
