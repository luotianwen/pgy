<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<%--<s:hidden id="status" name="projectVO.status"></s:hidden>--%>
<s:hidden id="priceStatus" name="projectVO.priceStatus"></s:hidden>
<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active"><s:if test="projectVO.queryType==1">项目总表</s:if>
						<s:if test="projectVO.queryType==2">新建的项目</s:if>
						<s:if test="projectVO.queryType==3">待技术审核的项目</s:if>
						<s:if test="projectVO.queryType==4">待出包的项目</s:if>
						<s:if test="projectVO.queryType==5">已出包的项目</s:if>
						<s:if test="projectVO.queryType==6">测试通过的项目</s:if>
						<s:if test="projectVO.queryType==7">技术参数错误的项目</s:if>
						<s:if test="projectVO.queryType==8">待价格审核的项目</s:if>
						<s:if test="projectVO.queryType==9">价格审核通过的项目</s:if>
						<s:if test="projectVO.queryType==10">审核未通过的项目</s:if></li>
   		</ul>
   	</div>
   	<div class="span12">
   		<form id="exportForm" class="form-inline">
   			<fieldset>
   				<legend>
   					<h3 class="inline">查询条件</h3>
   				</legend>
            	<label class="control-label inline" for="id">项目编号</label>
				<input id="id" name="projectVO.id" type="text" class="input-medium inline" value="<s:property value="projectVO.id"/>" style="WIDTH: 200px"  onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/>
            	<label class="control-label inline" for="customerName">客户名称</label>
				<input id="customerName" type="text" class="input-medium inline" name="projectVO.customerName" value="<s:property value="projectVO.customerName"/>" style="WIDTH: 150px"/>
				<%--代理商
				<select id="agentId" onchange="selBd(this.value);" name="projectVO.agentId" style="width:auto;">
					<s:action name="getAllAgent" namespace="" executeResult="true">
					</s:action>
				</select>--%>
				商务
				<select id="bdId" name="projectVO.bdId" class="selwidth">
					<s:action name="getAllBdList" namespace="" executeResult="true">
					</s:action>
				</select>
				<div style="margin-top: 5px;">
				<label class="control-label inline" for="confirmTime">审核开始时间</label>
				<input id="confirmTime" type="text" class="input-medium inline" onfocus="javascript:WdatePicker()" readonly="readonly"  name="projectVO.confirmTime" value="<s:property value="projectVO.confirmTime"/>" style="WIDTH: 150px"/>
				<label class="control-label inline" for="confirmTime">审核结束时间</label>
				<input id="confirmEndTime" type="text" class="input-medium inline" onfocus="javascript:WdatePicker()" readonly="readonly"  name="projectVO.confirmEndTime" value="<s:property value="projectVO.confirmEndTime"/>" style="WIDTH: 150px"/>
				</div>
				<div style="margin-top: 5px;">
					<label class="control-label inline" for="qversion">版本号</label>
					<s:select list="#Policy.versionList" name="projectVO.version" listKey="key" listValue="value" id="qversion" headerKey="0" headerValue="--所有版本--" cssStyle="width: auto"></s:select>
					<label class="control-label inline" for="isDevCustomer">开发者</label>
					<s:select id="isDevCustomer" name="projectVO.isDevCustomer" list="#{2:'所有',0:'否',1:'是'}" cssStyle="width: auto"></s:select>
					<label class="control-label inline" for="reviewStatus">状态</label>
					<s:select id="reviewStatus" name="projectVO.status" list="#{0:'所有',1:'提交技术审核',2:'审核',3:'上传包',4:'测试',5:'重新出包'}" cssStyle="width: auto"></s:select>
					<button type="button" class="btn btn-primary" onclick="searchList()">查 询</button>
					<button type="reset" class="btn">清 空</button>
					<input class="btn btn-primary" type="button" value=" 导出数据 " onclick="exportData();"/>
				</div>
   			</fieldset>
   		</form>
   	</div>
</div>

<div class="row">
  	<div class="span12">
  	<form class="form-horizontal well" id="dataForm">
  		<fieldset>
			<img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
   			<div id="dataList"></div>
  		</fieldset>
  	</form>
	</div>
</div>
<script type="text/javascript">
	function exportData() {
		var exportForm = document.getElementById("exportForm");
		exportForm.action = "<%=request.getContextPath()%>/Project!exportData.action";
		exportForm.submit();
	}

	function getArgus() {		
		var argus ="";
		argus += "projectVO.page.pageNum=";
		argus += document.getElementById("pageNum").value;
		argus += "&projectVO.page.pageSize=";
		argus += document.getElementById("pageSize").value;
		var id = $("#id").val();
		var customerName = $("#customerName").val();		
		var confirmTime = $("#confirmTime").val();		
		var priceStatus = $("#priceStatus").val();
		var queryType = $("#queryType").val();
		var version = $("#qversion").val();
		var isDevCustomer = $("#isDevCustomer").val();
		var status = $("#reviewStatus").val();
		var bdId = $("#bdId").val();
        argus += "&projectVO.id="; argus += id;
        argus += "&projectVO.customerName="; argus += customerName;
        argus += "&projectVO.status="; argus += status;
		argus += "&projectVO.version="; argus += version;
		argus += "&projectVO.isDevCustomer="; argus += isDevCustomer;
//		argus += "&projectVO.agentId="; argus += agentId;
		argus += "&projectVO.bdId="; argus += bdId;
        argus += "&projectVO.priceStatus="; argus += priceStatus;
		argus += "&projectVO.confirmTime="; argus += confirmTime;
        argus += "&projectVO.queryType="; argus += queryType;
        argus += "&projectVO.isPage=1";
		return argus;
	}

	function selBd(agentId) {
		var agentId = $("#agentId").val();
		if(agentId != 0) {
			url = "getAllBd.action";
			jQuery.post(url,{"bdVO.agentId":agentId},function(response){
				jQuery("#bdId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#bdId").html('<option value="0">--所有商务--</option>');
		}
	}
	function searchList(form) {
		$("#load").css({"display":""});
		jQuery("#dataList").html("");  		
		var id = $("#id").val();
		var customerName = $("#customerName").val();	
		var status = $("#reviewStatus").val();
		var priceStatus = $("#priceStatus").val();
		var queryType = $("#queryType").val();
		var confirmTime = $("#confirmTime").val();
        var confirmEndTime = $("#confirmEndTime").val();
        var version = $("#qversion").val();
		var isDevCustomer = $("#isDevCustomer").val();
		var agentId = $("#agentId").val();
		var bdId = $("#bdId").val();
		url = "<%=request.getContextPath()%>/Project!list.action";
		jQuery.post(url,{"projectVO.id":id,"projectVO.customerName":customerName,"projectVO.confirmTime":confirmTime,"projectVO.confirmEndTime":confirmEndTime,
			"projectVO.status":status,"projectVO.priceStatus":priceStatus,"projectVO.version":version,"projectVO.queryType":queryType,
			"projectVO.isDevCustomer":isDevCustomer,"projectVO.agentId":agentId,"projectVO.bdId":bdId
		},function(response){
			if(response=="1") {
				showErrorToastMiddle('<font color=\'red\'>系统出错，请重试或联系管理员!</font>');
				$("#load").css({"display":"none"});
			} else {
				jQuery("#dataList").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}
		});
	}
	searchList();
</script>