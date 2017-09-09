<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="pcustomerVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="pcustomerVO.id>0">修改渠道链接配置</s:if><s:else >新建渠道链接配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	
 
	
	<div class="control-group">
		<label class="control-label" for="promotionId">iframe链接ID</label>
		<div class="controls">
			<s:select id="promotionId" list="#Option.promotionIframeList" listKey="id" listValue="name" name="pcustomerVO.promotionId"
					  headerKey="0" headerValue="请选择链接" cssClass="selectWidth"></s:select>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="customerId">渠道ID</label>
		<div class="controls">
			<s:select id="customerId" list="#Option.pcustomerList" listKey="id" listValue="name" name="pcustomerVO.customerId"
					  headerKey="0" headerValue="请选择渠道" cssClass="selectWidth"></s:select>
		</div>
	</div>


		<div class="control-group">
			<label class="control-label" for="status">状态</label>
			<div class="controls">
				<s:select id="status" list="#Option.selectList" listKey="key" listValue="value"
						  name="pcustomerVO.status" cssClass="selectWidth"></s:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="status">单价</label>
			<div class="controls">
				<input type="number" id="payout" name="pcustomerVO.payout" value="<s:property value="pcustomerVO.payout"/>">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="linkUrl">推广链接(不能修改)</label>
			<div class="controls">
				<input id="linkUrl" type="text" class="input-medium" readonly name="pcustomerVO.linkUrl" size="70" value="<s:property value="pcustomerVO.linkUrl"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="redirectUrl">跳转URL(必须配置)</label>
			<div class="controls">
				<input id="redirectUrl" type="text" class="input-medium" name="pcustomerVO.redirectUrl" size="70" value="<s:property value="pcustomerVO.redirectUrl"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"  >其他跳转URL</label>
			<div class="controls">
				<input id="otherUrl" type="text" class="input-medium" name="pcustomerVO.otherUrl" size="70" value="<s:property value="pcustomerVO.otherUrl"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"  >渠道回调URL</label>
			<div class="controls">
				<input id="channelBackUrl" type="text" class="input-medium" name="pcustomerVO.channelBackUrl" size="70" value="<s:property value="pcustomerVO.channelBackUrl"/>"/>
			</div>
		</div>

		<tr >
			<td>国家</td>
			<td >
				<s:select list="#Option.countryList" listKey="id" listValue="name" id="cou" name="pcustomerVO.cou" cssClass="selectWidth" headerKey="0" headerValue="--所有国家--"></s:select>
			</td>
		</tr>
		<tr>
			<td class="table_td_title">运营商</td>
			<td>
				<select id="operatorId" name="pcustomerVO.operatorId" class="selectWidth"
						multiple="multiple">
					<s:action name="getAllOperator" namespace="/record" executeResult="true" >
					</s:action>
				</select>
			</td>
		</tr>
		<div class="control-group">
			<label class="control-label" for="notes">说明</label>
			<div class="controls">
				<textarea rows="2" cols="50" id="notes">${pcustomerVO.notes}</textarea>
			</div>
		</div>



	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatepcustomer('<%=path %>');" class="btn btn-primary"><s:if test="pcustomerVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>
 <script>
	 $("#customerId").select2();
	 $("#promotionId").select2();
     $("#operatorId").select2();
     $("#cou").select2();
 </script>