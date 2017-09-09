<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>

<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">订阅项目</li>
   		</ul>
   	</div>
   	<div class="span12">
   		<form id="exportForm" class="form-inline">
   			<fieldset>
   				<legend>
   					<h3 class="inline">查询条件</h3>
   				</legend>
            	<label class="control-label inline" for="id">项目编号</label>
				<input id="id" name="projectVO.id" type="text" class="input-medium inline" value="<s:property value="projectVO.id"/>"    onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/>
            	<label class="control-label inline" for="customerName">客户名称</label>
				<input id="customerName" type="text" class="input-medium inline" name="projectVO.customerName" value="<s:property value="projectVO.customerName"/>" style="WIDTH: 150px"/>

				商务
				<select id="bdId" name="projectVO.bdId" class="selwidth">
					<s:action name="getAllBdList" namespace="" executeResult="true">
					</s:action>
				</select>

					<label class="control-label inline" for="schemeName">订阅渠道id</label>
				 <input id="schemeName" type="text" class="input-medium inline" name="projectVO.schemeName" value="<s:property value="projectVO.schemeName"/>" style="WIDTH: 150px"/>
				<label class="control-label inline" for="status">状态</label>
				<s:select id="status" list="#{0:'全部',2:'否',1:'是'}" cssStyle="width: auto"></s:select>

				<button type="button" class="btn btn-primary" onclick="searchList()">查 询</button>
					<button type="reset" class="btn">清 空</button>
					<%--<input class="btn btn-primary" type="button" value=" 导出数据 " onclick="exportData();"/>--%>

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
		var schemeName = $("#schemeName").val();
		var status = $("#status").val();		
		var bdId = $("#bdId").val();
        argus += "&projectVO.id="; argus += id;
        argus += "&projectVO.customerName="; argus += customerName;
        argus += "&projectVO.status="; argus += status;
		argus += "&projectVO.bdId="; argus += bdId;
		argus += "&projectVO.schemeName="; argus += schemeName;
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
		var status = $("#status").val();
		var schemeName = $("#schemeName").val();
		var bdId = $("#bdId").val();
		url = "<%=request.getContextPath()%>/Project!subscribeList.action";
		jQuery.post(url,{"projectVO.id":id,"projectVO.customerName":customerName,"projectVO.status":status,
			"projectVO.schemeName":schemeName, "projectVO.bdId":bdId
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