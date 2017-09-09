<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>商务管理中心<span class="divider">/</span></li>
      	<li class="active">商务协助人员列表</li>
   		</ul>
   	</div>
   	<div class="span12">
   		<form class="form-inline">
   			<fieldset>
   				<legend>
   					<h3 class="inline">查询条件</h3>
   				</legend>
            	<label class="control-label inline" for="searchId">编号</label>
              	<input type="text" class="input-medium inline" id="searchId" name="bhVO.searchId" value="<s:property value="bhVO.searchId"/>" onkeyup="this.value=this.value.replace(/[^\d]/g,'');">
            	<label class="control-label inline" for="searchName">名称</label>
              	<input type="text" class="input-medium inline" id="searchName" name="bhVO.searchName" value="<s:property value="bhVO.searchName"/>">
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
		   			<INPUT class="btn btn-primary" type="button" onclick="createBh()" value="新增"></span>
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
		argus += "bhVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&bhVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#searchId").val();
		var name = $("#searchName").val();		
        argus += "&bhVO.searchId=";
        argus += id;
        argus += "&bhVO.searchName=";
        argus += name;
        argus += "&bhVO.isPage=1";
		return argus;
	}
	function searchList() {		
		$("#load").css({"display":""});
		$("#dataList").html("");  		
		var id = $("#searchId").val();
		var name = $("#searchName").val();				
		url = "<%=request.getContextPath()%>/Bh!list.action";
		$.post(url,{"bhVO.searchId":id,"bhVO.searchName":name},function(response){
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
	function modifyBh(id,portalUserId) {	
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Bh!modify.action?<%=Math.random()*1999.0 %>', {"bhVO.id":id,"bhVO.portalUserId":portalUserId}, function(){
	     		$modal.modal();
	    	});
		  }, 100);	
	}	
	function createBh() {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Bh!create.action?<%=Math.random()*1999.0 %>', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function detailBh(id ,portalUserId){
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Bh!detail.action?<%=Math.random()*1999.0 %>', {"bhVO.id":id,"bhVO.portalUserId":portalUserId}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function removeBh(id,portalUserId ,name){
		bootbox.confirm("确定要删除 ["+name+"] 吗?", function(result) {
				if(result) {
					jQuery.post("Bh!remove.action",{"bhVO.id":id,"bhVO.portalUserId":portalUserId},function(data){				
						if(data == "-1"){
							showErrorToastMiddle("系统出错，请重试或联系管理员");
						} else {
							showInfoToastMiddle(data);
			       			doPageBottom('turn');
						}
					});
				}
			});
	}
	
</script>