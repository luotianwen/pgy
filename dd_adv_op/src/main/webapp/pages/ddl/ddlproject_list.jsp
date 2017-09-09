<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="ddlProjectVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<!-- 
		   		<input class="btn btn-success" type="button" onclick="createDdlProject('<%=path %>/ddl')" value="新增"></td>
				 -->
				 <td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">coo_id</label>
   				<input id="pid" type="text" class="input-medium inline" name="ddlProjectVO.coo_id" value="<s:property value="ddlProjectVO.coo_id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="ddlProjectVO.name" value="<s:property value="ddlProjectVO.name"/>" style="width: 100px">
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
						<th>ID</th>				
						<th>coo_id</th>				
						<th>产品</th>				
						<th>渠道</th>				
						<th>转化率</th>
						<th>转化</th>
						<th>是否已满</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="ddlProjectVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="coo_id"/></td>
		    		<td><s:property value="productName"/></td>
		    		<td><s:property value="channelName"/></td>
		    		<td><s:property value="rate"/></td>
		    		<td><s:property value="countSale"/></td>
		    		<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==isAllSale">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
		    		</td>
		    		<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==status">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
		    		</td>
		    		<td><s:property value="createTime"/></td>
		    		<td> 			 	
						<a class="btn btn-primary" href="javascript: modifyDdlProject('<%=path%>/ddl','<s:property value="id"/>')" title="修改">
						修改
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="10">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="ddlProjectVO.page" operation="/ddl/DdlProject!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="ddlProjectVO.isPage==0">
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
		argus += "ddlProjectVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&ddlProjectVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
        argus += "&ddlProjectVO.coo_id=";
        argus += id;
        argus += "&ddlProjectVO.name=";
        argus += name;
        argus += "&ddlProjectVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/ddl/DdlProject!list.action";
		jQuery.post(url,
				{"ddlProjectVO.coo_id":id,
				"ddlProjectVO.name":name,
				"ddlProjectVO.isPage":1
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
	
	
	function modifyDdlProject(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/DdlProject!modify.action', {"ddlProjectVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createDdlProject(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/DdlProject!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



