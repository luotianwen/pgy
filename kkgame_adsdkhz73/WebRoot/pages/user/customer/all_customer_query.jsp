<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:hidden id="querycustomerStatus" name="customerVO.status" />
<s:hidden id="queryrelayStatus" name="customerVO.relayStatus" />
<s:hidden id="queryexpiryDate"  name="customerVO.expiryDate" />
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li><s:if test="customerVO.status==90">自有客户列表</s:if>
	<s:elseif test="customerVO.status==24">
	拓展中或已合作的客户总表
	</s:elseif>
	<s:else>所有客户列表</s:else></li>
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
            	<label class="control-label inline" for="searchName" style="margin-left: 200px">名称</label>
              	<input type="text" class="input-medium inline" id="searchName" name="customerVO.searchName" value="<s:property value="customerVO.searchName"/>">
   			</fieldset>
   			<div style="padding-bottom: 8px"></div>
   			<fieldset>
            	<label class="control-label inline" for="searchBdId">商务</label>
            	<select  name="customerVO.searchBdId" id="searchBdId" style="width: 165px" >					
				<s:action name="getAllBdList" namespace="/" executeResult="true">
						<s:param name="bdVO.id" value="customerVO.searchBdId"></s:param>
				</s:action>
				</select>
            	<label class="control-label inline" for="searchConfirmTime" style="margin-left: 170px;">审核时间</label>
				<select id="searchConfirmTime" name="customerVO.confirmTime" style="width: 165px" >	
				<option value="">--请选择查询时间--</option>			 
				<calendar:month from="201306" /> 		    		
				</select>
			
				<script>
					<s:if test='customerVO.confirmTime!=null && customerVO.confirmTime !=""'>
						$("#searchConfirmTime").val('<s:property value="customerVO.confirmTime"/>');
					</s:if>
				</script>           		
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
		var searchBdId = $("#searchBdId").val();
		var searchConfirmTime = $("#searchConfirmTime").val();
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
        argus += "&customerVO.searchBdId=";
        argus += searchBdId;
         argus += "&customerVO.confirmTime=";
        argus += searchConfirmTime;
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
		var searchBdId = $("#searchBdId").val();
		var searchConfirmTime = $("#searchConfirmTime").val();
		url = "<%=request.getContextPath()%>/Customer!allCustomerList.action";
		$.post(url,{"customerVO.searchId":id,"customerVO.searchName":name,
			"customerVO.status":status,"customerVO.relayStatus":relayStatus,"customerVO.expiryDate":expiryDate
			,"customerVO.searchBdId":searchBdId,"customerVO.confirmTime":searchConfirmTime
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