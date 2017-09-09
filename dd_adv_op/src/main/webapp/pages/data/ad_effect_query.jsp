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
      		<form class="form-horizontal" id="exportForm">
      		<s:hidden id="rowFieldString" name="searchVO.rowFieldString"></s:hidden>
      		<table class="table">
      		<tr><td>请选择时间<font color="red">*</font></td>
				<td>
		        	<input type="text" name="searchVO.startDate" id="start" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.startDate}" style="width:150px;">
					-
					<input type="text" name="searchVO.endDate" id="end" onfocus="javascript:WdatePicker()" readonly="readonly" value="${searchVO.endDate}" style="width:150px;">&nbsp;		
		        	
		        	</td>
	        	</tr>
	        	<tr >	
					<td>广告</td>
					<td >
						<s:select list="#Option.adList" listKey="id" listValue="name" id="adId" name="searchVO.adId" cssClass="selectWidth" headerKey="0" headerValue="--所有广告--"></s:select>
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

	$("#adId").select2();
	function searchList() {
		var url="<%=request.getContextPath()%>/data/AdData!effectList.action";
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
		var adId =$("#adId").val();
		if('undefined'==typeof(adId)) {
			adId=0;
		}
	  	$("#load").css({"display":""});
		jQuery("#dataList").html("");
		jQuery.post(url,{"searchVO.startDate":startDate,
			"searchVO.endDate":endDate,
			"searchVO.startTime":startTime,
			"searchVO.endTime":endTime,
			"searchVO.adId":adId
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
		exportForm.action="<%=request.getContextPath()%>/data/AdData!export.action";
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
		var adId = $("#adId").val();
		var country = $("#country").val();
		var type =$("#type").val();
		if('undefined'==typeof(adId)) {
			adId=0;
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
		var params = {"searchVO.startDate":startDate,
			"searchVO.endDate":endDate,
			"searchVO.adId":adId,
			"searchVO.country":country,
			"searchVO.type":type,
			"searchVO.rowFieldString":chk_value
		};
		$("#rowFieldString").val(chk_value)
		exportForm.submit();
	}
	
	function selCustomer(agentId) {
		if(agentId != 0) {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#productId").html('<option value="0">--所有产品--</option>');
			jQuery("#customChannelId").html('<option value="">--所有渠道--</option>');
			url = "<%=request.getContextPath()%>/customer/getAllCustomer.action";
			jQuery.post(url,{"customerVO.agentId":agentId},function(response){
				jQuery("#customerId").html(jQuery.trim(response));
				jQuery("#productId").html('<option value="0">--所有产品--</option>');
			});
		} else {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#productId").html('<option value="0">--所有产品--</option>');
			jQuery("#customChannelId").html('<option value="">--所有渠道--</option>');
		}
	}
	
	function selProduct(customerId) {
		if(customerId != 0) {
			jQuery("#productId").html('<option value="0">--所有产品--</option>');
			jQuery("#customChannelId").html('<option value="">--所有渠道--</option>');
			url = "<%=request.getContextPath()%>/customer/getAllProduct.action";
			jQuery.post(url,{"productVO.customerId":customerId},function(response){
				jQuery("#productId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#productId").html('<option value="0">--所有产品--</option>');
			jQuery("#customChannelId").html('<option value="">--所有渠道--</option>');
		}
	}
	
	
	function selCustomChannel(productId) {
		if(productId != 0) {
			jQuery("#customChannelId").html('<option value="">--所有渠道--</option>');
			url = "<%=request.getContextPath()%>/customer/getAllCustomChannel.action";
			jQuery.post(url,{"customChannelVO.productId":productId},function(response){
				jQuery("#customChannelId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#customChannelId").html('<option value="">--所有渠道--</option>');
		}
    }
	
</script>