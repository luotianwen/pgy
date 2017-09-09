<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
		<div class="row">
    	<div class="span12">
    		<ul class="breadcrumb">
        	<li>当前位置 <span class="divider">/</span></li>
        	<li>合作收益<span class="divider">/</span></li>
        	<li class="active">按天总数</li>
      		</ul>
      		<form class="form-horizontal well">
      		<fieldset>
      		<table class="querytable" >
      			<tbody>
			      <tr>
			        <td>请选择时间<font color="red">*</font></td>			        
			        <td>
			        	<input type="text" name="billSearchVO.startTime" id="start" onfocus="javascript:WdatePicker()" readonly="readonly" value="${billSearchVO.startTime}" style="width:80px;">
						-
						<input type="text" name="billSearchVO.endTime" id="end" onfocus="javascript:WdatePicker()" readonly="readonly" value="${billSearchVO.endTime}" style="width:80px;">&nbsp;		
			        	
			        </td>			        
			      </tr>
			      <role:equal type="SP,MG">
				<tr>
					<td>代理商</td>
					<td>
						<select id="agentId" onchange="selBd(this.value);" name="billSearchVO.agentId" style="WIDTH: 215px">
   							<s:action name="getAllAgent" namespace="" executeResult="true">
   							</s:action>
   						</select>
					&nbsp;&nbsp;商务
						<select id="bdId" onchange="selCustomer(this.value);"  id="bdId" name="billSearchVO.bdId" style="WIDTH: 215px">
   							<option value="0">--所有商务--</option>
   						</select>
					</td>
				</tr>
				</role:equal>
				<role:equal type="AG">
				<tr>
					<td>商务</td>
					<td  class="td_detail">
					<select id="bdId" onchange="selCustomer(this.value);" name="billSearchVO.bdId" style="WIDTH: 215px">
  							<s:action name="getAllBd" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
				</role:equal>
				<role:equal type="SP,MG,AG,BD">
				<tr>
					<td>客户</td>
					<td>
						<select id="customerId" onchange="selProject(this.value);" name="billSearchVO.customerId" style="WIDTH: 215px">
   							<option value="0">--所有客户--</option>
   						</select>
					&nbsp;&nbsp;项目
						<select id="projectId" name="billSearchVO.projectId" style="WIDTH: 215px">
   							<option value="0">--所有项目--</option>
   						</select>
					</td>
				</tr>
				<tr>
					<td>产品</td>
					<td>
					<select id="productId" name="billSearchVO.productId" style="WIDTH: 215px">
  							<s:action name="getAllProduct" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
				</role:equal>
				<role:equal type="CM">
				<tr>
					<td>项目</td>
					<td>
					<select id="projectId" name="billSearchVO.projectId" style="WIDTH: 215px">
  							<s:action name="getAllProject" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
  				<tr>
					<td>产品</td>
					<td>
					<select id="productId" name="billSearchVO.productId" style="WIDTH: 215px">
  							<s:action name="getAllProduct" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
				</role:equal>				
				<tr>	
					<td>统计显示列<font color="red">*</font></td>
					<td>
						<label class="checkbox inline">
						<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_DATE" />" checked="checked"/>时间
						</label>
						<role:equal type="SP,MG,BH">
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_AGENT" />"/>代理商
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_BD" />"/>商务
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PROJECT" />"/>项目
							</label>
						</role:equal>
						<role:equal type="AG">
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_BD" />"/>商务
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PROJECT" />"/>项目
							</label>
						</role:equal>
						<role:equal type="BD">
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PROJECT" />"/>项目
							</label>
						</role:equal>
						<label class="checkbox inline">
						<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PRODUCT" />"/>产品
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="form-actions">
						<input id="btn" class="btn btn-primary" type="button" value=" 查 询 " onclick="searchList();"/>&nbsp;&nbsp;
   						<button type="reset" class="btn">清 空</button>
					</td>
				</tr>
			    </tbody>
      		</table>  			
      		</fieldset>
      		</form>
    	</div>
  		</div>
		<div style="padding-bottom: 5px"></div>
		<img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
		<div id="data">	
						
		</div>
	<script type="text/javascript">

	
	function searchList() {
		$("#data").show();
		var url="<%=request.getContextPath()%>/UserDivide!specialList.action";	
  		if($("#start").val() == "") {
			alert("请选择开始时间");
			$("#start").focus();
			return false;
		}
		if($("#end").value == "") {
			alert("请选择结束时间");
			$("#end").focus();
			return false;
		}
		var startTime = $("#start").val();
		var endTime = $("#end").val();
		if(startTime!="" && endTime!="") {
			var date1 = startTime.split("-");
         		var date2 = endTime.split("-");          
         		var myDate1 = new Date(date1[0],date1[1]-1,date1[2]);
        		var myDate2 = new Date(date2[0],date2[1]-1,date2[2]);
          		if (myDate1 <= myDate2) {
          			if(myDate1.getMonth()!=myDate2.getMonth()) {
          				alert("提示：只能查询一个月的数据！");
          				return false;
          			}
          		} else {
            	alert ("提示:  开始时间不能大于结束时间！");
             	return false;
            	}
            }			
		var startTime = $("#start").val();
		var endTime = $("#end").val();
		var searchMonth = $("#searchMonth").val();
		var agentId = 0;
		var bdId = 0;
		var customerId = 0;
		var projectId = 0;
		var productId = 0;
		agentId = $("#agentId").val();
		bdId = $("#bdId").val();
		customerId = $("#customerId").val();
		projectId = $("#projectId").val();
		productId = $("#productId").val();
		if("undefined"==typeof(agentId)) {
			agentId=0;
		}
		if('undefined'==typeof(bdId)) {
			bdId=0;
		}
		if('undefined'==typeof(customerId)) {
			customerId=0;
		}
		if('undefined'==typeof(typeof(agentId))) {
			projectId=0;
		}
		var chk_value="";
	  	$('input[name="billSearchVO.rowFields"]:checked').each(function(){ 
	   		chk_value+=$(this).val();
	   		chk_value+=",";
	  	});
	  	if(chk_value=="") {
	  		showInforToastMiddle("请至少选择一项统计列！");
			return false;
	  	}
	  	$("#load").css({"display":""});
		jQuery("#data").html("");
		jQuery.post(url,{"billSearchVO.startTime":startTime,"billSearchVO.endTime":endTime
			,"billSearchVO.searchMonth":searchMonth,"billSearchVO.customerId":customerId,"billSearchVO.agentId":agentId
			,"billSearchVO.bdId":bdId,"billSearchVO.projectId":projectId,"billSearchVO.productId":productId
			,"billSearchVO.rowFieldString":chk_value
		},
		function(response){
			if(response=="1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				jQuery("#data").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}			
		});
	}
	
	var agentId = <s:property value ="billSearchVO.agentId"/>
	selBd(agentId);
	function selBd(agentId) {
		if(agentId != 0) {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			url = "<%=request.getContextPath()%>/getAllBd.action";
			jQuery.post(url,{"bdVO.agentId":agentId},function(response){
				jQuery("#bdId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#bdId").html('<option value="0">--所有商务--</option>');
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
	var bdId = <s:property value ="billSearchVO.bdId"/>
	selCustomer(bdId);
	function selCustomer(bdId) {
		if(bdId != 0) {			
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			url = "<%=request.getContextPath()%>/getAllCustomer.action";
			jQuery.post(url,{"customerVO.businessDeveloperId":bdId},function(response){
				jQuery("#customerId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	var customerId = <s:property value="billSearchVO.customerId"/>;
	selProject(customerId);
	function selProject(customerId) {
		if(customerId != 0) {
			url = "<%=request.getContextPath()%>/getAllProject.action";
			jQuery.post(url,{"projectVO.customerId":customerId},function(response){
				jQuery("#projectId").html(jQuery.trim(response));
			});
		} else {
				jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
</script>