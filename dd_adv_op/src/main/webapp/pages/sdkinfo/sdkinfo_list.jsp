<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="sdkInfoVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createSdkInfo('<%=path %>/sdkinfo')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="570px"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="sdkInfoVO.id" value="<s:property value="sdkInfoVO.id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="sdkInfoVO.name" value="<s:property value="sdkInfoVO.name"/>" style="width: 100px">
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
						<%--<th>ID</th>--%>
						<th>SdkId</th>
						<th>名称</th>				
						<th>类类型</th>
						<%--<th>启动类</th>--%>
						<%--<th>启动参数</th>--%>
						<th>排序等级</th>
						<th>url</th>
						<th>md5</th>
						<th>版本号</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="sdkInfoVOList">  
		    		<tr >
		    		<%--<td><s:property value="id"/></td>--%>
		    		<td><s:property value="sdkId"/></td>
		    		<td><s:property value="name"/></td>
					<td>
						<s:if test="classType==0">接口</s:if>
						<s:if test="classType==1">静态类</s:if>
					</td>
					<%--<td><s:property value="startClass"/> </td>--%>
					<%--<td><s:property value="startArg"/> </td>--%>
					<td><s:property value="rank"/> </td>
		    		<td><s:property value="url"/></td>
					<td><s:property value="md5"></s:property></td>
					<td><s:property value="sdkVersion"/></td>
					<td>
						<s:if test="status==0">关闭</s:if>
						<s:if test="status==1">打开</s:if>
					</td>
					<td><s:property value="createTime"/></td>
		    		<td>
		    			 			 	
						<a class="btn btn-primary" href="javascript: modifySdkInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="修改">
						修改
						</a>
						
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="9">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="sdkInfoVO.page" operation="/sdkinfo/SdkInfo!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="sdkInfoVO.isPage==0">
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
		argus += "sdkInfoVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&sdkInfoVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&sdkInfoVO.id=";
        argus += id;
        argus += "&sdkInfoVO.name=";
        argus += name;
        argus += "&sdkInfoVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/sdkinfo/SdkInfo!list.action";
		jQuery.post(url,
				{"sdkInfoVO.id":id,
				"sdkInfoVO.name":name,
				"sdkInfoVO.isPage":1
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
	
	
	function modifySdkInfo(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/SdkInfo!modify.action', {"sdkInfoVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createSdkInfo(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/SdkInfo!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



