<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>

<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>系统资源管理<span class="divider">/</span></li>
      	<li class="active">产品列表</li>
   		</ul>
   	</div>
   	<div class="span12">
   		<form class="form-inline">
   			<fieldset>
   				<legend>
   					<h3 class="inline">查询条件</h3>
   				</legend>
            	<label class="control-label inline" for="input01">产品ID</label>
              	<input type="text" class="input-medium inline" id="id"   value="<s:property value="productVO.projectId"/>" onkeyup="this.value=this.value.replace(/[^\d]/g,'');">
            	<label class="control-label inline" for="input01">产品名称</label>
              	<input type="text" class="input-medium inline" id="name" name="productVO.name" value="<s:property value="productVO.name"/>">
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
		   			<role:equal type="DT,SP">			
		   			<span style="float: left; PADDING-RIGHT: 10px;"> 
		   			<INPUT class="btn btn-primary" type="button" onclick="createProduct()" value="新增产品"></span>
		   			</role:equal>
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
		
	function getArgus() {		
		var argus ="";
		argus += "productVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&productVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#id").val();
		var name = $("#name").val();		
        argus += "&productVO.projectId=";
        argus += id;
        argus += "&productVO.name=";
        argus += name;
        argus += "&productVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataList").html("");  		
		var id = $("#id").val();
		var name = $("#name").val();		
		url = "<%=request.getContextPath()%>/Product!subscribeList.action";
		jQuery.post(url,{"productVO.projectId":id,"productVO.name":name
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
	function modifyProduct(id) {	
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Product!subscribeModify.action?<%=Math.random()*1999.0 %>', {"productVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 1000);		

	}	
	function createProduct() {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Product!subscribeCreate.action?<%=Math.random()*1999.0 %>', {}, function(){
	     		$modal.modal();
	    	});
		  }, 1000);
	}
	function updateList(id,type) {
		jQuery(document).trigger('close.facebox');
		var url = "<%=request.getContextPath()%>/Product!subscribeList.action";
		if(type==1) {
			$('#mes').html('<font color=\'red\'>产品添加成功!</font>');
		} else if(type==2) {
			$('#mes').html('<font color=\'red\'>产品修改成功!</font>');			
		}
		jQuery.post(url,{"productVO.id":id
		},function(response){
			if(response=="1") {
				$('#mes').html('<font color=\'red\'>系统出错，请重试或联系管理员!</font>');
				$("#load").css({"display":"none"});
			} else {
				jQuery("#data").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}
		});
	}	
	
</script>