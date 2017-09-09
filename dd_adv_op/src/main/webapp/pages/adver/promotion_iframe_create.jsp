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
<div style="width: auto" class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	
 
	
	<div class="control-group">
		<label class="control-label" for="name">名称</label>
		<div class="controls">
			<input id="name" type="text" class="input-medium" name="promotionVO.name"  value="<s:property value="promotionVO.name"/>"/>
		</div>
	</div>


		<div class="control-group">
			<label class="control-label" for="advLinkmanId">接入人员</label>
			<div class="controls">
				<s:select id="advLinkmanId" list="#Option.advLinkmanList" listKey="id" listValue="name" name="promotionVO.advLinkmanId"
						  headerKey="0" headerValue="请选择接入人员" cssClass="selectWidth"></s:select>
			</div>
		</div>



	
	<div class="control-group">
		<label class="control-label" for="adverId">广告主</label>
		<div class="controls">
			<s:select id="adverId" list="#Option.adverList" listKey="id" listValue="name" name="promotionVO.adverId"
					  cssClass="selectWidth"></s:select>
		</div>
	</div>


	
	<div class="control-group">
		<label class="control-label" for="redirectUrl">top跳转URL(格式:<font color="#b22222">http(s)://</font> www.baidu.com?a=<font color="#b22222">{tid} </font>,下同)</label>
		<div class="controls">
			<input id="redirectUrl" type="text" class="input-medium" name="promotionVO.redirectUrl" size="70" value="<s:property value="promotionVO.redirectUrl"/>"/>
		</div>
	</div>

		<div class="control-group">
			<label class="control-label" for="iframe1">iframe1</label>
			<div class="controls">
				<input id="iframe1" type="text" class="input-medium" name="promotionVO.iframe1" size="70" value="<s:property value="promotionVO.iframe1"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="iframe2">iframe2</label>
			<div class="controls">
				<input id="iframe2" type="text" class="input-medium" name="promotionVO.iframe2" size="70" value="<s:property value="promotionVO.iframe2"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="iframe3">iframe3)</label>
			<div class="controls">
				<input id="iframe3" type="text" class="input-medium" name="promotionVO.iframe3" size="70" value="<s:property value="promotionVO.iframe3"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="iframe4">iframe4</label>
			<div class="controls">
				<input id="iframe4" type="text" class="input-medium" name="promotionVO.iframe4" size="70" value="<s:property value="promotionVO.iframe4"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="iframe5">iframe5</label>
			<div class="controls">
				<input id="iframe5" type="text" class="input-medium" name="promotionVO.iframe5" size="70" value="<s:property value="promotionVO.iframe5"/>"/>
			</div>
		</div>
		<tr >
			<td>国家</td>
			<td >
				<s:select list="#Option.countryList" listKey="id" listValue="name" id="cou" name="promotionVO.cou" cssClass="selectWidth" headerKey="0" headerValue="--所有国家--"></s:select>
			</td>
		</tr>
		<tr>
			<td class="table_td_title">运营商</td>
			<td>
				<s:select id="operatorId" list="#Option.operatorsList" listKey="key" listValue="value"
						  name="promotionVO.operatorId" multiple="true" cssClass="selectWidth" > </s:select>
			</td>
		</tr>


		<div class="control-group">
		<label class="control-label" for="status">状态</label>
		<div class="controls">
			<s:select id="status" list="#Option.selectList" listKey="key" listValue="value"
					  name="promotionVO.status" cssClass="selectWidth"></s:select>
		</div>
	</div>
	 
	
	
	<div class="control-group">
		<label class="control-label" for="notes">说明</label>
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
<script>
    $("#cou").select2();
    $("#operatorId").select2();
    var operatorSelect2 = $("#operatorId").select2();
    var operators = '';
    operators = "<s:property value="promotionVO.operatorId"/>";
    if(operators !=null||operators!=""){
        data = operators.split(",");
        operatorSelect2.val(data).trigger("change");
    }
</script>