<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
<s:if test="osVO.isPage==0">
<script src="<%=request.getContextPath()%>/js/bootbox.min.js"></script>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>系统资源管理<span class="divider">/</span></li>
      	<li class="active">平台中心</li>
   		</ul>
   		<form class="form-horizontal well">
   		<s:hidden id="id" name="osVO.id"></s:hidden>
   		<fieldset>
   		<table class="">
   		<tbody>
   		<tr>
	   		<td>	   			
	   			<span style="float: left; PADDING-RIGHT: 10px;"> 
	   			<INPUT class="btn btn-primary" type="button" onclick="create()" value="新增平台"></span>
	   			<div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>	   			
	   		</td>
   		</tr>
   		</tbody>
   		</table>
   		<div>
   		<img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
   		</div>   				
   		<table id="dataTable" class="table table-bordered table-striped table-hover">
   		</s:if> 
		    <thead>
		      <tr>
		        <th>ID</th>
		        <th>平台名称</th>
		        <th>平台介绍</th>
		        <th>操作</th>
		      </tr>
		    </thead>		    
		    <tbody>
		      <s:if test="osVOList.size > 0">
		      <s:iterator value="osVOList">
		      <tr>
		      	<td>
				<s:property value="id" />
				</td>
				<td>
				<s:property value="name" />
				</td>
				<td>
				<s:property value="intro" />
				</td>
				<td>
				<button class="btn btn-info" type="button" onclick="javascript:modify('<s:property value="id"/>')">
				修改
				</button>
				<button class="btn btn-danger" type="button" onclick="javascript:remove('<s:property value="id"/>','<s:property value="name"/>')">
				删除
				</button>
				</td>
				</tr>
		      </s:iterator>
		      </s:if>
		      <tr>
		      	<td colspan="4">
		      		<div align="right">
						<page:paginationAjax  formName="dataForm" property="osVO.page" operation="OsAdapter!list.action" />
					</div>
		      	</td>
		      </tr>
		    </tbody>
		    <s:if test="osVO.isPage==0">
		  </table>		  
   		</fieldset>   		
   		
   		</form>
   	</div>
</div>
</s:if>
<script type="text/javascript">
	
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
	
	function modify(id) {
	/**
		jQuery.post("OsAdapter!modify.action?",{"osVO.id":id},function(data){				
			if(data == "-1"){
				return ;
			} else {
				$('#editDiv').html(data);
				return ;
			}
		});
		*/
	  $('body').modalmanager('loading');
	  setTimeout(function(){
  		$modal.load('OsAdapter!modify.action?<%=Math.random()*1999.0 %>', {"osVO.id":id}, function(){
     		$modal.modal();
    	});
	  }, 1000);
	}
	function create() {
		$('body').modalmanager('loading');
	  	setTimeout(function(){
     	$modal.load('OsAdapter!create.action?<%=Math.random()*1999.0 %>', '', function(){
	      	$modal.modal();
	    });
	  }, 1000);
	}	
	
	function remove(id,name){
		/**
		if(!sure('确定要删除 ['+name+'] 吗？')){
            return;
    	}
		jQuery.post("OsAdapter!delete.action",{"osVO.id":id},function(data){				
			if(data == "-1"){
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				showInfoToastMiddle(data);
       			doPageBottom('turn');
			}
		});
		*/
		bootbox.confirm("确定要删除 ["+name+"] 吗?", function(result) {
			if(result) {
				jQuery.post("OsAdapter!delete.action",{"osVO.id":id},function(data){				
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
	
	function getArgus() {		
		var argus ="";
		argus += "osVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&osVO.page.pageSize=";
		argus += $("#pageSize").val();   
		argus += "&osVO.isPage=1";  
		return argus;
	}
</script>
