<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="spromotionVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="pcustomerVO.id>0">修改渠道链接配置</s:if><s:else >新建渠道链接配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>



	<div class="control-group">
		<label class="control-label" for="customerIdKey">渠道ID</label>
		<div class="controls">
			<s:select id="customerId" list="#Option.pcustomerList" listKey="id" listValue="name" name="spromotionVO.cooId"
					  headerKey="0" headerValue="请选择渠道" cssClass="selectWidth"></s:select>
		</div>
	</div>

		<div class="control-group">
			<label class="control-label" for="customerIdKey">类型</label>
			<div class="controls">
				<s:select id="type" list="#Option.subType" listKey="key" listValue="value"
						  name="spromotionVO.type" cssClass="selectWidth" headerKey="0"
						  headerValue="请选择广告类型"></s:select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="statusKey">状态</label>
			<div class="controls">
				<s:select id="status" list="#Option.selectList" listKey="key" listValue="value"
						  name="spromotionVO.status" cssClass="selectWidth"></s:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="linkUrlKey">推广链接(不能修改)</label>
			<div class="controls">
				<input id="linkUrl" type="text" class="input-medium" readonly name="pcustomerVO.linkUrl" size="70" value="<s:property value="spromotionVO.linkUrl"/>"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="notesKey">说明</label>
			<div class="controls">
				<textarea rows="2" cols="50" id="notes">${spromotionVO.notes}</textarea>
			</div>
		</div>



	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatespromotion('<%=path %>');" class="btn btn-primary"><s:if test="spromotionVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
 <script>
	 $("#customerId").select2();
	 $("#type").select2();
 </script>