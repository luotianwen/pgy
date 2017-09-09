<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="silentInfoVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createSilentInfo('<%=path %>/sdkinfo')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="silentInfoVO.id" value="<s:property value="silentInfoVO.id"/>" style="width: 100px">
           		
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn btn-primary">清 空</button></td></tr></table>
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
						<th>线下频率</th>
						<th>线下次数</th>
						<th>下发多少个广告</th>				
						<th>线下版本</th>
						<th>状态</th>
						<th>备注</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="silentInfoVOList">  
		    		<tr id="silent_<s:property value="id"/>">
		    		<td><s:property value="frequency"/></td>
		    		<td><s:property value="times"/></td>
		    		<td><s:property value="days"/></td>
		    		<td><s:property value="versions"/></td>
		    		<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==status">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
		    			</td>
		    		<td>
		    		<a href="#" class="tooltip-test" data-toggle="tooltip" 
  					 title="<s:property value="note"/>"><s:property value="noteInfo"/></a>
		    		
		    		</td>
		    		<td>
		    			 			 	
						<a class="btn btn-primary" href="javascript: modifySilentInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="修改">
						修改
						</a>
						<a class="btn btn-danger" href="javascript: deleteSilentInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="删除">
						删除
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="silentInfoVO.page" operation="/sdkinfo/SilentInfo!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="silentInfoVO.isPage==0">
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
		argus += "silentInfoVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&silentInfoVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&silentInfoVO.id=";
        argus += id;
        argus += "&silentInfoVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/sdkinfo/SilentInfo!list.action";
		jQuery.post(url,
				{"silentInfoVO.id":id,
				"silentInfoVO.isPage":1
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
	
	
	function modifySilentInfo(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/SilentInfo!modify.action', {"silentInfoVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createSilentInfo(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/SilentInfo!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function deleteSilentInfo(path,id) {
		if(!sure('确定要删除 ['+id+'] 吗？')){
            return;
    	}
		jQuery.post(path+"/SilentInfo!delete.action",{"silentInfoVO.id":id},
		function(data){				
			if(data == "-1"){
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				showInfoToastMiddle(data);
				$("#silent_"+id).remove();
			}
		});
	}
	
</script>

</s:if>



