<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="sdkdomainVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createsdkdomain('<%=path %>/sdkinfo')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade" tabindex="-1"></div>

   				 </td></tr></table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 15px">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
   			<div id="dataList">

				<table id="dataTable" style="width:95%" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
						<tr>
						<th >域名</th>
						<th >创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="sdkdomainVOList">
					<tr id="guide_<s:property value="id"/>">
						<td><s:property value="download"/></td>
						<td><s:property value="cdate"/></td>
		    		<td>


						<a class="btn btn-info"
						   href="http://new.google8abc.com/cacheUtil?act=get&key=downloadUrl"
						   title="查看缓存" target="_blank">查看缓存</a>
						&nbsp;|&nbsp;

						<a class="btn btn-warning"
						   href="http://new.google8abc.com/cacheUtil?act=set&val=<s:property value="download"/>&key=downloadUrl"
						   target="_blank" title="修改缓存">
							修改缓存</a>

						<a class="btn btn-primary" href="javascript: modifysdkdomain('<%=path%>/sdkinfo','<s:property value="id"/>')"
						   title="修改">
							修改
						</a>
						<a class="btn  btn-danger" href="javascript: deletesdkdomain('<%=path%>/sdkinfo','<s:property value="id"/>','<s:property value="download"/>')"
						   title="删除">
							删除
						</a>
                    </td>
		    		</tr>
				</s:iterator>

				</tbody>
<s:if test="sdkdomainVO.isPage==0">
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

	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");
		url = "<%=request.getContextPath()%>/sdkinfo/Sdkdomain!list.action";
		jQuery.post(url,{	"sdkdomainVO.isPage":1	},function(response){
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
	function modifysdkdomain(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Sdkdomain!modify.action', {"sdkdomainVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createsdkdomain(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Sdkdomain!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function deletesdkdomain(path,id,download) {
		if(!sure('确定要删除 ['+download+'] 吗？')){
			return;
		}
		jQuery.post(path+"/Sdkdomain!delete.action",{"sdkdomainVO.id":id},
				function(data){
					if(data == "-1"){
						showErrorToast("提示","系统出错，请重试或联系管理员");
					} else {
						showInfoToastMiddle(data);
						$("#guide_"+id).remove();
					}
				});
	}

	//修改or新增
	function updatesdkdomain(path) {
		var id = $("#cid").val();
		var url = path +'/sdkinfo/Sdkdomain!update.action';
		if(id==0) {
			url = path+'/sdkinfo/Sdkdomain!save.action';
		}
		 var download=$("#download").val();
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"sdkdomainVO.id":id,
			"sdkdomainVO.download":download

		 }, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
				searchList();
	            
	        }
	   });
	}
	 
	 
</script>
</s:if>