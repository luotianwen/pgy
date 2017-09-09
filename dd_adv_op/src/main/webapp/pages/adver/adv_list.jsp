<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="advVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createAdv('<%=path %>/adver')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="600" ></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="advVO.id" value="<s:property value="advVO.id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="advVO.name" value="<s:property value="advVO.name"/>" style="width: 100px">
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
						<th>名称</th>				
						<th>网络类型</th>				
						<th>状态</th>				
						<th>创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
				
		    	<s:iterator value="advVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td>
		    			
    				<s:if test="linkType==1">
    					wifi
    				</s:if>
    				<s:if test="linkType==2">
    					grps
    				</s:if>
		    		</td>
		    		<td>		    		
		    			<s:if test="status==0">关闭</s:if>
						<s:if test="status==1">打开</s:if>
					</td>
		    		<td><s:property value="createTime"/></td>
		    		<td> 	
						<a class="btn btn-primary" href="javascript: modifyAdv('<%=path%>/adver','<s:property value="id"/>')" title="修改">
						修改
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="advForm" property="advVO.page" operation="/adver/Adv!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="advVO.isPage==0">
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
		argus += "advVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&advVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&advVO.id=";
        argus += id;
        argus += "&advVO.name=";
        argus += name;
        argus += "&advVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/adver/Adv!list.action";
		jQuery.post(url,
				{"advVO.id":id,
				"advVO.name":name,
				"advVO.isPage":1
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
	
	
	function modifyAdv(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Adv!modify.action', {"advVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createAdv(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Adv!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



