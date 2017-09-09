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
					
		        	</td>
	        	</tr>
        		<tr>     
				<td>项目</td>
				<td>
					<select id="projectId" name="searchVO.projectId" class="selectWidth">
						<s:action name="getAllProject" namespace="/customer" executeResult="true">
						</s:action>
					</select>
				</td>
				</tr>
				<tr>     
				<td>创建时间</td>
				<td>
					
					<input type="text" name="searchVO.startTime" id="startTime" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.startTime}" style="width:150px;">
					-
					<input type="text" name="searchVO.endTime" id="endTime" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.endTime}" style="width:150px;">&nbsp;		
		        	
					
				</td>
				</tr>
				<tr><td colspan="4" style="text-align: center">	
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
	$("#type").select2();
	if($("#projectId")) {
		$("#projectId").select2();
	}
	function searchList() {
		var url="<%=request.getContextPath()%>/data/BdData!list.action";
		if($("#start").val() == "") {
			showInfoToastMiddle("请选择开始时间");
			$("#start").focus();
			return false;
		}
		if($("#startTime").val() == "") {
			showInfoToastMiddle("请选择项目创建开始时间");
			$("#startTime").focus();
			return false;
		}
		if($("#endTime").val() == "") {
			showInfoToastMiddle("请选择项目创建结束时间");
			$("#endTime").focus();
			return false;
		}
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(startTime!="" && endTime!="") {
			var date1 = startTime.split("-");
       		var date2 = endTime.split("-");          
       		var myDate1 = new Date(date1[0],date1[1]-1,date1[2]);
       		var myDate2 = new Date(date2[0],date2[1]-1,date2[2]);
       		if (myDate1 > myDate2) {
           		showInfoToastMiddle ("开始时间不能大于结束时间！");
            	return false;
           	}
        }	
		var startDate = $("#start").val();
		var projectId =  $("#projectId").val();
		if('undefined'==typeof(projectId)) {
			projectId=0;
		}
	  	$("#load").css({"display":""});
		jQuery("#dataList").html("");
		jQuery.post(url,
		{"searchVO.startDate":startDate,
			"searchVO.startTime":startTime,
			"searchVO.endTime":endTime,
			"searchVO.projectId":projectId
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
	var bdId=<s:property value="searchVO.bdId"/>;
	if(bdId>0) {
		selProject(bdId);
	}
	function selProject(bdId) {
		jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		url = "<%=request.getContextPath()%>/customer/getAllProject.action";
		jQuery.post(url,{"projectVO.bdId":bdId},function(response){
			jQuery("#projectId").html(jQuery.trim(response));
		});
	
	}
	
</script>