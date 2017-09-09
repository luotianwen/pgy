<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="ddlProjectVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="ddlProjectVO.id==0">新建DDL项目</s:if>
	<s:if test="ddlProjectVO.id>0">修改DDL项目</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">产品</td>	
				<td>
					<s:select list="#Option.ddlServerProductList" listKey="id" listValue="name" name="ddlProjectVO.productId" id="productId" cssClass="selectWidth" > </s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">渠道</td>	
				<td>
					<s:select list="#Option.ddlServerChannelList" listKey="id" listValue="name" name="ddlProjectVO.channelId" id="channelId" cssClass="selectWidth" > </s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">coo_id</td>	
				<td>
					<input type="text" class="input-medium" id="coo_id" name="ddlProjectVO.coo_id" value="${ddlProjectVO.coo_id}" />
				</td>
				</tr>
				<tr>
				<td class="table_td_title">推广URL</td>	
				<td>
					<input type="text" size="80" class="input-xxlarge" id="clickUrl" name="ddlProjectVO.clickUrl" value="${ddlProjectVO.clickUrl}" />
				</td>
				</tr>
				<tr>
				<td class="table_td_title">转发URL</td>	
				<td>
					<input type="text" size="80" class="input-xxlarge" id="saleUrl" name="ddlProjectVO.saleUrl" value="${ddlProjectVO.saleUrl}" />
				</td>
				</tr>
				<tr>
				<td class="table_td_title">开放类型</td>	
				<td>
					<s:iterator value="#Option.uaTypeList">
						<input id="inlineCheckbox_<s:property value="key"/>" name="uaTypeCheckBox" type="checkbox" class="ccc"
						value="<s:property value="key"/>">
						<s:property value="value"/>
					</s:iterator>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">转化率</td>	
				<td>
					<input type="text" class="input-medium" id="rate" name="ddlProjectVO.rate" value="${ddlProjectVO.rate}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">转化</td>	
				<td>
					<input type="text" class="input-medium" id="countSale" name="ddlProjectVO.countSale" value="${ddlProjectVO.countSale}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">是否已满</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isAllSale" name="ddlProjectVO.isAllSale"></s:select>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">状态</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="status" name="ddlProjectVO.status"></s:select>
				</td>
				</tr>
				<tr>
				<td class="table_td_title">下载地址</td>	
				<td>
					<textarea rows="5" cols="80" id="redirectUrl">${ddlProjectVO.redirectUrl}</textarea>
				</td>
				</tr>
				<tr>
				<td colspan="2">
					<input id="createBtn" type="button" class="btn btn-primary" value="确定" onclick="return updateInfo();">
				</td>
				</tr>
			</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">

var uaTypes = '<s:property value="ddlProjectVO.uaTypes"/>';
var data = uaTypes.toString().split(",");
for(var i = 0; i < data.length; i = i + 1){
	$("#inlineCheckbox_" + data[i]).attr("checked", true);
}

function updateInfo() {
	var path = '<%=path%>/ddl/DdlProject!save.action';
	var id =  $("#cid").val();
    var status =  $("#status").val();
    var productId =  $("#productId").val();
    var channelId =  $("#channelId").val();
    var countSale =  $("#countSale").val();
    var isAllSale =  $("#isAllSale").val();
    var rate =  $("#rate").val();
    var clickUrl =  $("#clickUrl").val();
    var saleUrl =  $("#saleUrl").val();
    var redirectUrl =  $("#redirectUrl").val();
    var coo_id =  $("#coo_id").val();
	var uaTypeChecked=$("input[name='uaTypeCheckBox']:checked").val([]);
	var uaTypeCheckValues="";
	for(var i=0;i<uaTypeChecked.length;i++){
		uaTypeCheckValues += uaTypeChecked[i].value +",";
	} 
	if(clickUrl=="") {
    	showInfoToastMiddle("请填写推广url!");
    	return false;
    }
	if(uaTypeCheckValues=="") {
    	showInfoToastMiddle("请选择开放类型!");
    	return false;
    }
	$("#createBtn").attr("disabled", "disabled");
    jQuery.post(path,{'ddlProjectVO.id':id,
       	'ddlProjectVO.status':status,
       	'ddlProjectVO.version':1,
       	'ddlProjectVO.productId':productId,
       	'ddlProjectVO.channelId':channelId,
       	'ddlProjectVO.countSale':countSale,
       	'ddlProjectVO.isAllSale':isAllSale,
       	'ddlProjectVO.rate':rate,
       	'ddlProjectVO.clickUrl':clickUrl,
       	'ddlProjectVO.saleUrl':saleUrl,
       	'ddlProjectVO.redirectUrl':redirectUrl,
       	'ddlProjectVO.coo_id':coo_id,
       	'ddlProjectVO.uaTypes':uaTypeCheckValues
       	}
       	,function(response){
			if(response=="-1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				$("#load").css({"display":"none"});
				showInfoToastMiddle(response);
	           	doPageBottom('turn');
			}
		});
}

