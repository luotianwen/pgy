<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:hidden id="querycustomerStatus" name="customerVO.status" />
<s:hidden id="queryrelayStatus" name="customerVO.relayStatus" />
<s:hidden id="queryexpiryDate"  name="customerVO.expiryDate" />
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>客户协调中心<span class="divider">/</span></li>
      	<li class="active"><s:if test="customerVO.status==0">新建的</s:if>
		<s:elseif test="customerVO.status==1">待审核的</s:elseif>
		<s:elseif test="customerVO.status==2">拓展中的</s:elseif>
		<s:elseif test="customerVO.status==3">审核不通过的</s:elseif>
		<s:elseif test="customerVO.status==4">已合作的</s:elseif>
		<s:elseif test="customerVO.status==5">放弃的</s:elseif>
		<s:elseif test="customerVO.status==7">已过期的</s:elseif>
		<s:elseif test="customerVO.status==9">闲置的</s:elseif>
		<s:elseif test="customerVO.relayStatus==6">被收回的</s:elseif>
		<s:elseif test="customerVO.relayStatus==10">移交中</s:elseif>
		<s:elseif test="customerVO.status==90">我的</s:elseif>客户列表</li>
   		</ul>
   	</div>
   	<div class="span12">
   		<form class="form-inline">
   			<fieldset>
   				<legend>
   					<h3 class="inline">查询条件</h3>
   				</legend>
            	<label class="control-label inline" for="searchId">编号</label>
              	<input type="text" class="input-medium inline" id="searchId" name="customerVO.searchId" value="<s:property value="customerVO.searchId"/>" onkeyup="this.value=this.value.replace(/[^\d]/g,'');">
            	<label class="control-label inline" for="searchName">名称</label>
              	<input type="text" class="input-medium inline" id="searchName" name="customerVO.searchName" value="<s:property value="customerVO.searchName"/>">
           		<button type="button" class="btn btn-primary" onclick="searchList()">查 询</button>
           		<button type="reset" class="btn">清 空</button><br/>          		
   			</fieldset>
   		</form>
   	</div>
</div>
<div class="row">
  	<div class="span12">
  	<form class="form-horizontal well" id="dataForm">
  		<fieldset>
  			<table class="">
	   		<tbody>
	   		<tr>
		   		<td>	   			
		   			<span style="float: left; PADDING-RIGHT: 10px;"> 
					<img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
					<div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>	   			
		   		</td>
	   		</tr>
	   		</tbody>
	   		</table>
   			<div id="dataList"></div>
  		</fieldset>
  	</form>
	</div>
</div>

<script type="text/javascript">	
	if($("#searchId").val() == 0){
		$("#searchId").val('');
	}
	function getArgus() {		
		var argus ="";
		argus += "customerVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&customerVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#searchId").val();
		var name = $("#searchName").val();		
		var status = $("#querycustomerStatus").val();
		var relayStatus = $("#queryrelayStatus").val();
		var expiryDate = $("#queryexpiryDate").val();
        argus += "&customerVO.searchId=";
        argus += id;
        argus += "&customerVO.searchName=";
        argus += name;
        argus += "&customerVO.relayStatus=";
        argus += relayStatus;
        argus += "&customerVO.status=";
        argus += status;
        argus += "&customerVO.expiryDate=";
        argus += expiryDate;
        argus += "&customerVO.isPage=1";
		return argus;
	}
	function searchList() {		
		$("#load").css({"display":""});
		$("#dataList").html("");  		
		var id = $("#searchId").val();
		var name = $("#searchName").val();				
		var status = $("#querycustomerStatus").val();
		var relayStatus = $("#queryrelayStatus").val();
		var expiryDate = $("#queryexpiryDate").val();
		url = "<%=request.getContextPath()%>/Customer!list.action";
		$.post(url,{"customerVO.searchId":id,"customerVO.searchName":name,
			"customerVO.status":status,"customerVO.relayStatus":relayStatus,"customerVO.expiryDate":expiryDate
			},function(response){
			if(response=="-1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				jQuery("#dataList").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}
		});
	}
	searchList();
	var $modal = $('#ajax-modal'); 
	$modal.on('click', '.update', function(){
		$modal.modal('loading');
	  	setTimeout(function(){
	    	$modal.modal('loading').find('.modal-body')
	        .prepend('<div class="alert alert-info fade in">' +
	        'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
	        '</div>');
	  	}, 1000);
	});
		
	//提交审核
	function submitConfirm(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_WAIT_CONFIRM"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	//申请闲置客户
	function  doCustomerApply(id,businessDeveloperId,status,relayBdId,relayStatus){	
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_WAIT_CONFIRM"/>';
		jQuery.post("Customer!customerApply.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	
	//进入审核页面
	function auditCustomer(id,businessDeveloperId,status,relayBdId,relayStatus){
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Customer!gotoAuditCustomerView.action?<%=Math.random()*1999.0 %>', 
	  		{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus}
			, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	//签约
	function  sign_up(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_SIGN_UP"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	
	function  call_back(id,businessDeveloperId,status,relayBdId,relayStatus,name){
		var newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_CALL_BACK"/>';
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Customer!gotoReasonEditView.action?<%=Math.random()*1999.0 %>', 
	  		{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId,"customerVO.newStatus":newStatus
			,"customerVO.relayStatus":relayStatus}
			, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	//移交客户
	function  devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_DEVOLVE"/>';
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Customer!gotoDevolveCustomer.action?<%=Math.random()*1999.0 %>', 
	  		{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId,"customerVO.newStatus":newStatus
			,"customerVO.relayStatus":relayStatus}
			, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	//放弃客户
	function  abandon(id,businessDeveloperId,status,relayBdId,relayStatus,name){
		var newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_ABANDON"/>';
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Customer!gotoReasonEditView.action?<%=Math.random()*1999.0 %>', 
	  		{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId,"customerVO.newStatus":newStatus
			,"customerVO.relayStatus":relayStatus}
			, function(){
	     		$modal.modal();
	    	});
	  	}, 100);
	}
	
	
	//同意转移
	function  agree_devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEVOLVE_AGREE"/>';
		jQuery.post("Customer!customerDevolve.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	
	//不同意转移
	function  disagree_devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEVOLVE_DISAGREE"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	
	//取消转移
	function  cancel_devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEVOLVE_CANCEL"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	//申请延期
	function  deferred(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_DEFERRED"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	//同意延期
	function  agree_deferred(id,businessDeveloperId,status,relayBdId,relayStatus,expiryDate){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMERS_DEFERRED_AGREE"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	//不同意延期
	function  disagree_deferred(id,businessDeveloperId,status,relayBdId,relayStatus){
		var newStatus='<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEFERRED_DISAGREE"/>';
		jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});
	}
	
	function detailCustomer(id ,portalUserId){
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Customer!detail.action?<%=Math.random()*1999.0 %>', {"customerVO.id":id,"customerVO.portalUserId":portalUserId}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function removeCustomer(id,portalUserId ,name){
		bootbox.confirm("确定要删除 ["+name+"] 吗?", function(result) {
				if(result) {
					jQuery.post("Customer!remove.action",{"customerVO.id":id,"customerVO.portalUserId":portalUserId},function(data){				
						if(data == "-1"){
							showErrorToast("提示","系统出错，请重试或联系管理员");
						} else {
							showInfoToastMiddle(data);
			       			doPageBottom('turn');
						}
					});
				}
			});
	}
	function modifyCustomer(id,portalUserId) {	
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Customer!modify.action?<%=Math.random()*1999.0 %>', {"customerVO.id":id,"customerVO.portalUserId":portalUserId}, function(){
	     		$modal.modal();
	    	});
		  }, 100);	
	}
</script>