<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="offlinesdkVO.isPage==0">
<div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
				<input class="btn btn-success" type="button" onclick="createsdk('<%=path %>/offline')" value="新增">
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
		
   			<div id="dataList">
				<table id="dataTable" class="table table-bordered table-striped table-hover">
					</s:if>
					<thead>
						<tr>
						<th>id</th>
						<th>时间间隔</th>
						<th>状态</th>
						<th>版本</th>
						<th>创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="offlinesdkVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="timeStep"/></td>
		    		<td>
		    				<s:if test="status==1">开</s:if>
		    				<s:if test="status==0">关</s:if>
		    		</td>
					<td><s:property value="version"></s:property></td>
		    		<td><s:property value="cdate"/></td>
		    		<td> 			 	
						<a class="btn btn-primary" href="javascript: modifyOffsdk('<%=path%>/offline','<s:property value="id"/>')" title="修改">
						修改
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="10">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="offlinesdkVO.page" operation="/offline/Offsdk!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="offlinesdkVO.isPage==0">
				</table>
   			</div>
   			<div style='text-align:center;'>
			<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
   			</div>
  		</fieldset>
  	</form>
	</div>
</div>



<script type="text/javascript">		
	 
	function getArgus() {		
		var argus ="";
		argus += "offlinesdkVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&offlinesdkVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
        argus += "&offlinesdkVO.coo_id=";
        argus += id;
        argus += "&offlinesdkVO.name=";
        argus += name;
        argus += "&offlinesdkVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/offline/Offsdk!list.action";
		jQuery.post(url,
				{"offlinesdkVO.coo_id":id,
				"offlinesdkVO.name":name,
				"offlinesdkVO.isPage":1
				},
		function(response){
			if(response=="-1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				jQuery("#dataTable").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}
		});
	}
	
	
	var $modal = $('#ajax-modal'); 
	$modal.on('click', '.update', function(){
		$modal.modal('loading');
	  	setTimeout(function(){
	    	$modal.modal('loading').find('.modal-body')
	        .prepend('<div class="alert alert-info fade in">' +
	        'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
	        '</div>');
	  	}, 100);
	});
	
	
	function modifyOffsdk(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Offsdk!modify.action', {"offlinesdkVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createsdk(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Offsdk!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



