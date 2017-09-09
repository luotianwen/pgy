<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="sdkProjectVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<%--<input class="btn btn-success" type="button" onclick="createSdkProject('<%=path %>/sdkinfo')" value="新增"></td>--%>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="800"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">coo_id</label>
   				<input id="pid" type="text" class="input-medium inline" name="sdkProjectVO.coo_id" value="<s:property value="sdkProjectVO.coo_id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="sdkProjectVO.apkName" value="<s:property value="sdkProjectVO.apkName"/>" style="width: 100px">
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
						<th>项目名称:coo_id</th>				
						<th>推送</th>				
						<th>插屏</th>				
						<th>下沉</th>				
						<th>线下安装</th>
						<th>提示界面</th>
						<th>预下载</th>
						<th>插件升级</th>
						<th>审核</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="sdkProjectVOList">  
		    		<tr >
		    		<td><s:property value="apkName"/>[<s:property value="coo_id"/>]</td>
		    		
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==status">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isTablePlaque">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isTablePlaqueDown">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
		    		<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isCjPush">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isPops">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==ydownload">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isOpen">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
					<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==pass">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
		    		<td> 			 	
						<a class="btn btn-primary" href="javascript: modifySdkProject('<%=path%>/sdkinfo','<s:property value="id"/>')" title="修改">
						修改
						</a>
						
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="10">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="ApkInfoForm" property="sdkProjectVO.page" operation="/sdkinfo/SdkProject!advList.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="sdkProjectVO.isPage==0">
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
		argus += "sdkProjectVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&sdkProjectVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&sdkProjectVO.coo_id=";
        argus += id;
        argus += "&sdkProjectVO.apkName=";
        argus += name;
        argus += "&sdkProjectVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/sdkinfo/SdkProject!advList.action";
		jQuery.post(url,
				{"sdkProjectVO.coo_id":id,
				"sdkProjectVO.apkName":name,
				"sdkProjectVO.isPage":1
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
	
	
	function modifySdkProject(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/SdkProject!advModify.action', {"sdkProjectVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
//	function createSdkProject(path) {
//		$('body').modalmanager('loading');
//		  setTimeout(function(){
//	  		$modal.load(path+'/SdkProject!advCreate.action', {}, function(){
//	     		$modal.modal();
//	    	});
//		  }, 100);
//	}
	
	
</script>

</s:if>



