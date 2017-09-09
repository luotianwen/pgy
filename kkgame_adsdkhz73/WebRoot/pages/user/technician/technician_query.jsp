<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>商务管理中心<span class="divider">/</span></li>
      	<li class="active">技术人员列表</li>
   		</ul>
   	</div>
   	<div class="span12">
   		<form class="form-inline">
   			<fieldset>
   				<legend>
   					<h3 class="inline">查询条件</h3>
   				</legend>
            	<label class="control-label inline" for="searchId">编号</label>
              	<input type="text" class="input-medium inline" id="searchId" name="portalUserVO.searchId" value="<s:property value="portalUserVO.searchId"/>" onkeyup="this.value=this.value.replace(/[^\d]/g,'');">
            	<label class="control-label inline" for="searchLoginId">名称</label>
              	<input type="text" class="input-medium inline" id="searchLoginId" name="portalUserVO.searchLoginId" value="<s:property value="portalUserVO.searchLoginId"/>">
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
		   			<INPUT class="btn btn-primary" type="button" onclick="createTh()" value="新增"></span>
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
		argus += "portalUserVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&portalUserVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#searchId").val();
		var searchLoginId = $("#searchLoginId").val();		
        argus += "&portalUserVO.searchId=";
        argus += id;
        argus += "&portalUserVO.searchLoginId=";
        argus += searchLoginId;
        argus += "&portalUserVO.isPage=1";
		return argus;
	}
	function searchList() {		
		$("#load").css({"display":""});
		$("#dataList").html("");  		
		var id = $("#searchId").val();
		var searchLoginId = $("#searchLoginId").val();				
		url = "<%=request.getContextPath()%>/Technician!list.action";
		$.post(url,{"portalUserVO.searchId":id,"portalUserVO.searchLoginId":searchLoginId},function(response){
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
	function modifyTh(id) {	
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Technician!modify.action?<%=Math.random()*1999.0 %>', {"portalUserVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);	
	}	
	function createTh() {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Technician!create.action?<%=Math.random()*1999.0 %>', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function removeTh(id,loginId){
		bootbox.confirm("确定要删除 ["+loginId+"] 吗?", function(result) {
				if(result) {
					jQuery.post("Technician!remove.action",{"portalUserVO.id":id},function(data){				
						if(data == "-1"){
							showErrorToast("提示","系统出错，请重试或联系管理员");
						} else {
							showInfoToastMiddle(data);
			       			searchList();
						}
					});
				}
			});
	}
	
</script>