<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
	UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="box box-success">
	<div class="span12">
		<div class="box-header">
				<h3 class="box-title">查询条件</h3>
		</div>
		<div id="ajax-modal" class="modal fade" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="widget-box">
      		<form class="form-horizontal" id="exportForm">
      		<s:hidden id="rowFieldString" name="searchVO.rowFieldString"></s:hidden>
      		<s:hidden id="endDate" name="searchVO.endDate"></s:hidden>
      		<table class="table">
      						
      		
      		<tr><td>请选择时间<font color="red">*</font></td>
				<td >
		        	<input type="text" name="searchVO.startDate" id="start" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.startDate}" style="width:150px;" >
					
		        	</td>
	        	</tr>
				<tr>
					<td>商务</td>
					<td>
						<select id="bdId" name="searchVO.bdId" class="selectWidth" onchange="selCustomer(this.value)">
							<s:action name="getAllBdList" namespace="/customer" executeResult="true">
							</s:action>
						</select>
					</td>
				</tr>
				<tr>
					<td>客户</td>
					<td>
						<select id="customerId" name="searchVO.customerId" class="selectWidth" onchange="selProject(this.value)">
							<option value="0">--所有客户--</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>项目</td>
					<td>
						<select id="projectId" name="searchVO.projectId" class="selectWidth">
							<option value="0">--所有项目--</option>
							<%--<s:action name="getAllProject" namespace="/customer" executeResult="true">
							</s:action>--%>
						</select>
					</td>
				</tr>
				<%--<tr >
					<td>类型</td>
					<td >
						<s:select list="#Option.typeList" listKey="key" listValue="value" id="type" name="searchVO.type" cssClass="selectWidth" headerKey="0" headerValue="--所有类型--"></s:select>
  					</td>
  				</tr>--%>
  				<tr><td>统计显示列<font color="red">*</font></td>
					<td>		
						<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_DATE" />" checked="checked"  />时间
						</label>
							
						<label class="checkbox-inline">
							<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_PROJECT"/>"   checked="checked"/>项目
						</label>
						<%--<label class="checkbox-inline">
						<input type="checkbox" name="searchVO.rowFields" value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_TYPE" />"/>类型
						</label>--%>
					</td></tr>
					<tr><td colspan="4" style="text-align: center">	
						<input id="btn" class="btn btn-primary" type="button" value=" 查 询 " onclick="searchList();"/>&nbsp;&nbsp;
						<input id="btnGen" class="btn btn-info" type="button" value=" 生成数据 " onclick="generateData();"/>&nbsp;&nbsp;
						<input id="btnSync" class="btn btn-warning" type="button" value=" 同步数据 " onclick="syncData();"/>&nbsp;&nbsp;
   						<input id="btnExport" class="btn btn-primary" type="button" value=" 导出数据" onclick="exportData();"/>&nbsp;&nbsp;
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
//	$("#type").val(600400);
//	$("#type").select2();
	$("#bdId").select2();
	if($("#projectId")) {
		$("#projectId").select2();
	}
	$("#customerId").select2();

	function searchList() {
		var url="<%=request.getContextPath()%>/data/ProjectIncome!postList.action";
		if($("#start").val() == "") {
			showInfoToastMiddle("请选择时间");
			$("#start").focus();
			return false;
		}
		var startDate = $("#start").val();
			
		var projectId =  $("#projectId").val();
		var type =$("#type").val();
		var bdId = $("#bdId").val();
		var customerId = $("#customerId").val();
		if('undefined'==typeof(projectId)) {
			projectId=0;
		}
		if('undefined'==typeof(bdId)) {
			bdId=0;
		}
		if('undefined'==typeof(customerId)) {
			customerId=0;
		}
//		if('undefined'==typeof(type)) {
//			type=0;
//		}

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
			"searchVO.endDate":startDate,
			"searchVO.projectId":projectId,
			"searchVO.bdId":bdId,
			"searchVO.customerId":customerId,
//			"searchVO.type":type,
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

	function exportData() {
		var exportForm = document.getElementById("exportForm");
		exportForm.action="<%=request.getContextPath()%>/data/ProjectIncome!export.action";
		if($("#start").val() == "") {
			showInfoToastMiddle("请选择时间");
			$("#start").focus();
			return false;
		}
		var startDate = $("#start").val();
			
		var projectId =  $("#projectId").val();
		var type =$("#type").val();
		if('undefined'==typeof(projectId)) {
			projectId=0;
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
	  	var params = {"searchVO.startDate":startDate,
			"searchVO.endDate":startDate,
			"searchVO.projectId":projectId,
			"searchVO.type":type,
			"searchVO.rowFieldString":chk_value
		}
		$("#rowFieldString").val(chk_value);
		$("#endDate").val(startDate);
	  	exportForm.submit();
	}
	
	/*function selBd(agentId) {
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
	}*/
	
	
	/*function selCustomer(businessDeveloperId) {
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
	}*/

	function selCustomer(bdId) {
		jQuery("#customerId").html('<option value="0">--所有客户--</option>');
		jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		if(bdId != 0) {
			url = "<%=request.getContextPath()%>/customer/getAllCustomer.action";
			jQuery.post(url,{"customerVO.businessDeveloperId":bdId},function(response){
				jQuery("#customerId").html(jQuery.trim(response));
			});
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
	
var $modal = $('#ajax-modal'); 
$modal.on('click', '.update', function(){
	$modal.modal('loading');
  	setTimeout(function(){
    	$modal.modal('loading').find('.modal-body')
        .prepend('<div class="alert alert-info fade in">' +
        'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
        '</div>');
  	}, 100);
});

function modifyConfig(path,statDate,projectId) {
	$('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load(path+'/ProjectIncome!postModify.action', {"projectIncomeVO.statDate":statDate,"projectIncomeVO.projectId":projectId}, function(){
     		$modal.modal();
    	});
	  }, 100);
}

//校验用户表单提交
function updateConfig(path,statDate,projectId) {
	var rateHigh = $("#rateHigh").val();
	var rateLow = $("#rateLow").val();
	var rateMid = $("#rateMid").val();
	var minIncomeRate = $("#minIncomeRate").val();
	var expectPrice = $("#expectPrice").val();
	url = path+'/data/ProjectIncome!postUpdate.action';
	if('undefined'==typeof(rateHigh)) {
			rateHigh=0;
	}
	if('undefined'==typeof(rateLow)) {
			rateLow=0;
	}
	if('undefined'==typeof(rateMid)) {
			rateMid=0;
	}
	if('undefined'==typeof(minIncomeRate)) {
			minIncomeRate=0;
	}
	if('undefined'==typeof(expectPrice)) {
			expectPrice=0;
	}
	jQuery.post(url, 
		{"projectIncomeVO.projectId":projectId,
		"projectIncomeVO.statDate":statDate,
		"projectIncomeVO.rateLow":rateLow,
		"projectIncomeVO.rateMid":rateMid,
		"projectIncomeVO.rateHigh":rateHigh,
		"projectIncomeVO.minIncomeRate":minIncomeRate,
		"projectIncomeVO.expectPrice":expectPrice}, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	       	} else {
	       		showInfoToastMiddle(response);
	        }
   });	
}

//操作
function postData(path, statDate, projectId){
	bootbox.confirm("你确定要执行此项操作吗?", function(result) {
		if(result) {
			jQuery.post(path+"/ProjectIncome!postData.action",{"projectIncomeVO.statDate":statDate,"projectIncomeVO.projectId":projectId},function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
				}
			});
		}
	});
}
	function generateData() {
		bootbox.confirm("你确定要生成数据吗?", function(result) {
		if(result) {
		var url="<%=request.getContextPath()%>/data/ProjectIncome!generateData.action";
		if($("#start").val() == "") {
			showInfoToastMiddle("请选择时间");
			$("#start").focus();
			return false;
		}
		
		var startDate = $("#start").val();
		var projectId =  $("#projectId").val();
		
		if('undefined'==typeof(projectId)) {
			projectId=0;
		}
	  	$("#load").css({"display":""});
		jQuery.post(url,{"projectIncomeVO.statDate":startDate,
			"projectIncomeVO.projectId":projectId
		},
		function(response){
			if(response=="-1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");				
				$("#load").css({"display":"none"});
			} else {
				showInfoToastMiddle(response);
				$("#load").css({"display":"none"});
			}			
		});
		}
		});
	}
	
	function generateProjectData(path,statDate,projectId) {
	bootbox.confirm("你确定要生成数据吗?", function(result) {
		if(result) {
		var url=path+"/ProjectIncome!generateData.action";
		
	  	$("#load").css({"display":""});
		jQuery.post(url,{"projectIncomeVO.statDate":statDate,
			"projectIncomeVO.projectId":projectId
		},
		function(response){
			if(response=="-1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");				
				$("#load").css({"display":"none"});
			} else {
				showInfoToastMiddle(response);
				$("#load").css({"display":"none"});
			}			
		});
		}
		});
	}
	
	function syncData() {
		bootbox.confirm("你确定要同步数据吗?", function(result) {
		if(result) {
			var url="<%=request.getContextPath()%>/data/ProjectIncome!postDataList.action";
			if($("#start").val() == "") {
				showInfoToastMiddle("请选择时间");
				$("#start").focus();
				return false;
			}
			var startDate = $("#start").val();
			var projectId =  $("#projectId").val();
			if('undefined'==typeof(projectId)) {
				projectId=0;
			}
		  	$("#load").css({"display":""});
			jQuery.post(url,{"searchVO.startDate":startDate,
				"searchVO.endDate":startDate,
				"searchVO.projectId":projectId
			},
			function(response){
				if(response=="-1") {
					showErrorToast("提示","系统出错，请重试或联系管理员");				
					$("#load").css({"display":"none"});
				} else {
					showInfoToastMiddle(response);
					$("#load").css({"display":"none"});
				}			
			});
		}
		});
	}

</script>