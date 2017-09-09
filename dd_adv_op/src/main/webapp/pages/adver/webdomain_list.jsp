<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="webdomainVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createwebdomain('<%=path %>/adver')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade" tabindex="-1"></div>
           		<label class="control-label inline" for="pname">域名</label>
   				<input id="qname" type="text" class="input-medium inline" name="webdomainVO.download" value="<s:property value="webdomainVO.download"/>" style="width: 200px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn">清 空</button></td></tr></table>
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
						<th >创建日期</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="webdomainVOList">  
		    		<tr >
						<td><s:property value="download"/></td>
						<td><s:property value="cdate"/></td>
		    		<td>
	    				<a class="btn btn-info" href="javascript: detailwebdomain('<%=path%>/adver/Webdomain!detail.action','<s:property value="id"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: modifywebdomain('<%=path%>/adver','<s:property value="id"/>')" title="修改">
						修改
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: removewebdomain('<%=path%>/adver','<s:property value="id"/>')" title="修改">
							删除
						</a>
					</td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="webdomainVO.page" operation="/adver/Webdomain!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				 	<s:if test="webdomainVO.isPage==0">
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
		argus += "webdomainVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&webdomainVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		

        argus += "&webdomainVO.download=";
        argus += name;
        argus += "&webdomainVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/adver/Webdomain!list.action";
		jQuery.post(url,{ "webdomainVO.download":name,"webdomainVO.isPage":1
		},function(response){
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
	function modifywebdomain(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Webdomain!modify.action', {"webdomainVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createwebdomain(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Webdomain!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function removewebdomain(path,id){
		var url = path +'/adver/Webdomain!remove.action';
		jQuery.post(url, {"webdomainVO.id":id },
				function(response){
					if(jQuery.trim(response) == "-1"){
						showErrorToastMiddle("系统出错，请重试或联系管理员");
						jQuery("#surebtn").attr('disabled', 'disabled');
					} else {
						showInfoToastMiddle(response);
						doPageBottom('turn');
					}
				});
	}
	function detailwebdomain(url,id) {
		//$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/Webdomain!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"webdomainVO.id":id},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}
	//修改or新增
	function updatewebdomain(path) {
		var id = $("#cid").val();
		 
		var url = path +'/adver/Webdomain!update.action';
		if(id==0) {
			url = path+'/adver/Webdomain!save.action';
		}
		 var id=$("#id").val();
		 var download=$("#download").val();
		 var cdate=$("#cdate").val();
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"webdomainVO.id":id,
			"webdomainVO.download":download,
			"webdomainVO.cdate":cdate,
		 }, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	           	doPageBottom('turn');
	            
	        }
	   });
	}
	 
	 
</script>
</s:if>
