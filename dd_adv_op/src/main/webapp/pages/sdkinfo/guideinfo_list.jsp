<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="guideInfoVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createGuideInfo('<%=path %>/sdkinfo')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	</td></tr></table>
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
						<th>一天几次</th>				
						<th>时间间隔（分钟）</th>	
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="guideInfoVOList">  
		    		<tr id="guide_<s:property value="id"/>">
		    		<td><s:property value="ydcs"/></td>
		    		<td><s:property value="ydjg"/></td>
		    		<td>
		    			 			 	
						<a class="btn btn-primary" href="javascript: modifyGuideInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="修改">
						修改
						</a>
						<a class="btn btn-danger" href="javascript: deleteGuideInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="删除">
						删除
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="guideInfoVO.page" operation="/sdkinfo/GuideInfo!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="guideInfoVO.isPage==0">
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
		argus += "guideInfoVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&guideInfoVO.page.pageSize=";
		argus += $("#pageSize").val();
		
        argus += "&guideInfoVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/sdkinfo/GuideInfo!list.action";
		jQuery.post(url,
				{
				"guideInfoVO.isPage":1
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
	
	
	function modifyGuideInfo(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/GuideInfo!modify.action', {"guideInfoVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createGuideInfo(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/GuideInfo!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function deleteGuideInfo(path,id) {
		if(!sure('确定要删除 ['+id+'] 吗？')){
            return;
    	}
		jQuery.post(path+"/GuideInfo!delete.action",{"guideInfoVO.id":id},
		function(data){				
			if(data == "-1"){
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				showInfoToastMiddle(data);
				$("#guide_"+id).remove();
			}
		});
	}
</script>

</s:if>



