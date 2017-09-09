<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
	UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<div class="box box-success">
	<div class="span12">
		<div class="box-header">
				<h3 class="box-title">查询条件</h3>
		</div>
		<div class="widget-box">
      		<form class="form-horizontal">
      		<table class="table">
      		<tr><td>请选择时间<font color="red">*</font></td>
				<td >
		        	<input type="text" name="searchVO.startDate" id="start" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.startDate}" style="width:150px;">
					-
					<input type="text" name="searchVO.endDate" id="end" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.endDate}" style="width:150px;">&nbsp;		
		        	
		        	</td>
	        	</tr>
        		
				<tr >	
					<td>产品</td>
					<td >
						<s:select list="#Option.ddlProductList" listKey="id" listValue="name" id="productId" name="searchVO.productId" cssClass="selectWidth" headerKey="0" headerValue="--所有产品--"></s:select>
  					</td>
  					</tr>
  					<tr >	
					<td>渠道</td>
					<td >
						<s:select list="#Option.ddlChannelList" listKey="channelId" listValue="channelName" id="channelId" name="searchVO.channelId" cssClass="selectWidth" headerKey="0" headerValue="--所有渠道--"></s:select>
  					</td>
  					</tr>
				<tr >	
					<td>项目</td>
					<td >
						<s:select list="#Option.ddlProjectList" listKey="id" listValue="name" id="projectId" name="searchVO.projectId" cssClass="selectWidth" headerKey="0" headerValue="--所有项目--"></s:select>
  					</td>
  					</tr>
				<tr >
					<td>国家</td>
					<td >
						<s:select list="#Option.countryList" listKey="id" listValue="name" id="country" name="searchVO.country" cssClass="selectWidth" headerKey="0" headerValue="--所有国家--"></s:select>
  					</td>
  					</tr>
				<tr >	
					<td>类型</td>
					<td >
						<s:select list="#Option.uaTypeList" listKey="key" listValue="value" id="type" name="searchVO.type" cssClass="selectWidth" headerKey="0" headerValue="--所有类型--"></s:select>
  					</td>
  				</tr>
  				<tr>
  				<td>统计显示列<font color="red">*</font></td>
  				<td >		
						<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_DATE" />" checked="checked"/>时间
						</label>
						
						<label class="checkbox-inline">
							<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_PRODUCT"/>"  />产品
						</label>
						<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_CHANNEL" />" />渠道
						</label>
						<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_PROJECT" />" />项目
						</label>
						<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_COUNTRY" />" />国家
						</label>
						<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_TYPE" />"/>类型
						</label>
					</td></tr>
					<tr><td colspan="2" style="text-align: center">	
						<input id="btn" class="btn btn-primary" type="button" value=" 查 询 " onclick="searchList();"/>&nbsp;&nbsp;
   						<button type="reset" class="btn">清 空</button>
					</td></tr>
				</table>
      		</form>
      		</div>
   		</div>
   	</div> 		
<div class="row-fluid">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>  			
   			<div id="dataList">
			</div>
			<div style='text-align:center;'>
			<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
   			</div>
  		</fieldset>
  	</form>
</div>
</div>
<script type="text/javascript">
	$("#productId").select2();
	$("#projectId").select2();
	$("#channelId").select2();
	$("#country").select2();
	$("#type").select2();
	
	function searchList() {
		var url="<%=request.getContextPath()%>/data/DdlData!list.action";
		if($("#start").val() == "") {
			showInfoToastMiddle("请选择开始时间");
			$("#start").focus();
			return false;
		}
		if($("#end").val() == "") {
			showInfoToastMiddle("请选择结束时间");
			$("#end").focus();
			return false;
		}
		var startDate = $("#start").val();
		var endDate = $("#end").val();
		if(startDate!="" && endDate!="") {
			var date1 = startDate.split("-");
       		var date2 = endDate.split("-");          
       		var myDate1 = new Date(date1[0],date1[1]-1,date1[2]);
       		var myDate2 = new Date(date2[0],date2[1]-1,date2[2]);
         		if (myDate1 <= myDate2) {
         			if(myDate1.getMonth()!=myDate2.getMonth()) {
         				showInfoToastMiddle("只能查询一个月的数据！");
         				return false;
         			}
         		} else {
           		showInfoToastMiddle ("开始时间不能大于结束时间！");
            	return false;
           	}
        }	
		var productId =  $("#productId").val();
		var projectId =  $("#projectId").val();
		var channelId =  $("#channelId").val();
		var country = $("#country").val();
		var type =$("#type").val();
		if('undefined'==typeof(productId)) {
			productId=0;
		}
		if('undefined'==typeof(channelId)) {
			channelId=0;
		}
		if('undefined'==typeof(country)) {
			country=0;
		}
		if('undefined'==typeof(type)) {
			type=0;
		}
		var chk_value="";
	  	$('input[name="searchVO.rowFields"]:checked').each(function(){ 
	   		chk_value+=$(this).val();
	   		chk_value+=",";
	  	});
	  	if(chk_value=="") {
	  		alert("请至少选择一项统计列！");
			return false;
	  	}	
	  	$("#load").css({"display":""});
		jQuery("#dataList").html("");
		jQuery.post(url,{"searchVO.startDate":startDate,
			"searchVO.endDate":endDate,
			"searchVO.productId":productId,
			"searchVO.projectId":projectId,
			"searchVO.channelId":channelId,
			"searchVO.country":country,
			"searchVO.type":type,
			"searchVO.rowFieldString":chk_value
			
		},
		function(response){
			if(response=="-1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");				
				$("#load").css({"display":"none"});
			} else {
				jQuery("#dataList").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}			
		});
	}
	
	function selBd(agentId) {
		if(agentId != 0) {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			url = "<%=request.getContextPath()%>/customer/getAllBdList.action";
			jQuery.post(url,{"bdVO.agentId":agentId},function(response){
				jQuery("#bdId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#bdId").html('<option value="0">--所有商务--</option>');
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
	
	function selCustomer(businessDeveloperId) {
		if(agentId != 0) {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			var url = "<%=request.getContextPath()%>/customer/getAllCustomer.action";
			jQuery.post(url,{"customerVO.businessDeveloperId":businessDeveloperId},function(response){
				jQuery("#customerId").html(jQuery.trim(response));
				jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			});
		} else {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
	function selProject(customerId) {
		if(customerId != 0) {
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			url = "<%=request.getContextPath()%>/customer/getAllProject.action";
			jQuery.post(url,{"projectVO.customerId":customerId},function(response){
				jQuery("#projectId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
</script>