<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="linkadsconfigVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="linkadsconfigVO.id>0">修改配置</s:if><s:else >新建配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>

	
	<div class="control-group">
		<label class="control-label" for="nameKey">名称</label>
		<div class="controls">
			<input id="name" type="text" class="input-medium" name="linkadsconfigVO.name"     value="<s:property value="linkadsconfigVO.name"/>"/>
		</div>
	</div>
	 
	
	
	<div class="control-group">
		<label class="control-label" for="linkTypeKey">类型</label>
		<div class="controls">
			<s:select list="#Option.linkType" listKey="key" listValue="value" id="linkType"
					  name="linkadsconfigVO.linkType"></s:select>
		</div>
	</div>
	 
	
	
	<%--<div class="control-group">
		<label class="control-label" for="linkUrlKey">链接路径</label>
		<div class="controls">
			<input id="linkUrl" type="text" class="input-medium" name="linkadsconfigVO.linkUrl"  size="60" value="<s:property value="linkadsconfigVO.linkUrl"/>"/>
		</div>
	</div>--%>

		<input id="linkUrl" type="hidden">
	
	<div class="control-group">
		<label class="control-label" for="deskIconNameKey">桌面图标名称</label>
		<div class="controls">
			<input id="deskIconName" type="text" class="input-medium" name="linkadsconfigVO.deskIconName"  value="<s:property value="linkadsconfigVO.deskIconName"/>"/>
		</div>
	</div>
	 
	
	
	<div class="control-group">
		<label class="control-label" for="deskIconUrlKey">桌面图标</label>
		<div class="controls">
			<input id="deskIconUrl" type="text" class="input-medium" name="linkadsconfigVO.deskIconUrl"  size="60" value="<s:property value="linkadsconfigVO.deskIconUrl"/>"/>
		</div>
	</div>
	 
	
	
	<div class="control-group">
		<label class="control-label" for="statusBarTitleKey">状态栏标题</label>
		<div class="controls">
			<input id="statusBarTitle" type="text" class="input-medium" name="linkadsconfigVO.statusBarTitle"  value="<s:property value="linkadsconfigVO.statusBarTitle"/>"/>
		</div>
	</div>
	 
	
	
	<div class="control-group">
		<label class="control-label" for="statusBarContentKey">状态栏内容</label>
		<div class="controls">
			<input id="statusBarContent" type="text" class="input-medium" name="linkadsconfigVO.statusBarContent" size="60"  value="<s:property value="linkadsconfigVO.statusBarContent"/>"/>
		</div>
	</div>
	 
	
	

	
	
	<div class="control-group">
		<label class="control-label" for="statusKey">状态</label>
		<div class="controls">
			<s:select list="#{'0':'关闭','1':'打开'}" listKey="key" listValue="value" id="status"
					  name="linkadsconfigVO.status"></s:select>
		</div>
	</div>
	 
	
	
	   
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatelinkadsconfig('<%=path %>');" class="btn btn-primary"><s:if test="linkadsconfigVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
