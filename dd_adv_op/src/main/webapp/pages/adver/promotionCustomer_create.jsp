<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="promotionCustomerVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="promotionCustomerVO.id>0">修改渠道配置</s:if><s:else >新建渠道配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	
 
	
	<div class="control-group">
		<label class="control-label" for="nameKey">名称</label>
		<div class="controls">
			<input id="name" type="text" class="input-medium" name="promotionCustomerVO.name" size="50"  value="<s:property value="promotionCustomerVO.name"/>"/>
		</div>
	</div>

		<div class="control-group">
			<label class="control-label" for="nameKey">联系人</label>
			<div class="controls">
				<input id="contact" type="text" class="input-medium" name="promotionCustomerVO.contact" size="50"  value="<s:property value="promotionCustomerVO.contact"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="nameKey">邮箱</label>
			<div class="controls">
				<input id="email" type="text" class="input-medium" name="promotionCustomerVO.email" size="50"  value="<s:property value="promotionCustomerVO.email"/>"/>
			</div>
		</div>





		<div class="control-group">
			<label class="control-label" for="notesKey">说明</label>
			<div class="controls">
				<textarea rows="2" cols="50" id="notes">${promotionCustomerVO.notes}</textarea>
			</div>
		</div>





	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatepromotionCustomer('<%=path %>');" class="btn btn-primary"><s:if test="promotionCustomerVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
