<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="promotionVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="promotionVO.id>0">修改推广链接配置</s:if><s:else >新建推广链接配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	
 
	
	<div class="control-group">
		<label class="control-label" for="nameKey">名称</label>
		<div class="controls">
			<input id="name" type="text" class="input-medium" name="promotionVO.name"  value="<s:property value="promotionVO.name"/>"/>
		</div>
	</div>


		<div class="control-group">
			<label class="control-label" for="advLinkmanIdKey">接入人员</label>
			<div class="controls">
				<s:select id="advLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name" name="promotionVO.advLinkmanId"
						  headerKey="0" headerValue="请选择接入人员" cssClass="selectWidth"></s:select>
			</div>
		</div>



	
	<div class="control-group">
		<label class="control-label" for="adverIdKey">广告主</label>
		<div class="controls">
			<s:select id="adverId" list="#Option.adverList" listKey="id" listValue="name" name="promotionVO.adverId"
					  cssClass="selectWidth"></s:select>
		</div>
	</div>


	
	<div class="control-group">
		<label class="control-label" for="redirectUrlKey">跳转URL</label>
		<div class="controls">
			<input id="redirectUrl" type="text" class="input-medium" name="promotionVO.redirectUrl" size="70" value="<s:property value="promotionVO.redirectUrl"/>"/>
		</div>
	</div>
	 
	
	

	 
	

	<div class="control-group">
		<label class="control-label" for="statusKey">状态</label>
		<div class="controls">
			<s:select id="status" list="#Option.selectList" listKey="key" listValue="value"
					  name="promotionVO.status" cssClass="selectWidth"></s:select>
		</div>
	</div>
	 
	
	
	<div class="control-group">
		<label class="control-label" for="notesKey">说明</label>
		<div class="controls">
			<textarea rows="2" cols="50" id="notes">${promotionVO.notes}</textarea>
		</div>
	</div>
	 
	

	
	   
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatepromotion('<%=path %>');" class="btn btn-primary"><s:if test="promotionVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
