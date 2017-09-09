<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="offlineapkVO.isPage==0">
<div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
				<input class="btn btn-success" type="button" onclick="createapk('<%=path %>/offline')" value="新增">
				<a class="btn btn-warning"
				   href="http://new.google8abc.com/cacheUtil?act=set&key=offlineapk&type=b"
				   target="_blank" title="修改缓存">修改缓存</a>
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
						<th>包名</th>
						<th>启动类</th>
						<th>版本号</th>
						<th>服务名</th>
						<th>MD5</th>
						<th>安装时间(点)</th>
						<th>安装区域</th>
						<th>状态</th>
						<th>APK下载地址</th>
						<th>创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="offlineapkVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="packageName"/></td>
					<td><s:property value="activityName"/></td>
					<td><s:property value="version"/></td>
					<td><s:property value="serviceName"/></td>
					<td><s:property value="md5"/></td>
					<td><s:property value="installClock"/></td>
					<td>
						<s:if test="dataOrSys == 8200800">数据区</s:if>
						<s:if test="dataOrSys == 8200801">系统区</s:if>
					</td>
		    		<td>
		    				<s:if test="status==1">开</s:if>
		    				<s:if test="status==0">关</s:if>
		    		</td>
					<td><s:property value="url"></s:property></td>
		    		<td><s:property value="cdate"/></td>
		    		<td> 			 	
						<a class="btn btn-primary" href="javascript: modifyOffapk('<%=path%>/offline','<s:property value="id"/>')" title="修改">
						修改
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="10">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="apkInfoForm" property="offlineapkVO.page" operation="/offline/Offapk!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="offlineapkVO.isPage==0">
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
		argus += "offlineapkVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&offlineapkVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
        argus += "&offlineapkVO.coo_id=";
        argus += id;
        argus += "&offlineapkVO.name=";
        argus += name;
        argus += "&offlineapkVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/offline/Offapk!list.action";
		jQuery.post(url,
				{"offlineapkVO.coo_id":id,
				"offlineapkVO.name":name,
				"offlineapkVO.isPage":1
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
	
	
	function modifyOffapk(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Offapk!modify.action', {"offlineapkVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createapk(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Offapk!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



