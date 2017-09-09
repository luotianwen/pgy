<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserVO userVO = (UserVO)request.getSession().getAttribute("SESSION_USER");
%>
<s:if test="productVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   	<!-- 	<input class="btn btn-success" type="button" onclick="createProduct('<%=path %>/customer')" value="新增"></td> -->
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="560"></div>
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="productVO.id" value="<s:property value="productVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="qname" type="text" class="input-medium inline" name="productVO.name" value="<s:property value="productVO.name"/>" style="width: 100px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn btn-primary" onclick="clearInfo()">清 空</button></td></tr></table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 20px;overflow:auto">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
   			<div id="dataList">
				<table id="dataTable" class="table table-bordered table-striped table-hover">
					</s:if>
					<thead>
						<tr>
						<th>APP ID</th>				
						<th>应用名称</th>				
						<th>高</th>				
						<th>中</th>				
						<th>低</th>	
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="productVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td><s:property value="highPrice"/></td>
		    		<td><s:property value="midPrice"/></td>
		    		<td><s:property value="lowPrice"/></td>
						<td>
	    				
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: modifyProduct('<%=path%>/customer','<s:property value="id"/>')" title="修改">
						修改
						</a>
						<!-- 
						&nbsp;&nbsp;|&nbsp;&nbsp;				
						<a class="btn btn-danger" href="javascript: deleteProduct('<%=path%>/customer','<s:property value="id"/>','<s:property value="realName"/>')" title="删除" >
						删除
						</a> -->
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="6">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="productVO.page" operation="/customer/Product!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="productVO.isPage==0">
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
		argus += "productVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&productVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		
        argus += "&productVO.id=";
        argus += id;
        argus += "&productVO.name=";
        argus += name;
        argus += "&productVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/customer/Product!list.action";
		jQuery.post(url,{"productVO.id":id,"productVO.name":name,"productVO.isPage":1
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
	function modifyProduct(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Product!modify.action', {"productVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createProduct(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Product!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function detailProduct(url,id) {
		$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>应用列表</li><li class=\"active\">应用详情</li>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"productVO.id":id},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}
	
	function updateProduct(path) {
	
		var id = $("#id").val();
		var name = $("#appname").val();
		var highPrice = $("#highPrice").val();
		var midPrice = $("#midPrice").val();
		var lowPrice = $("#lowPrice").val();
		var url = path +'/customer/Product!update.action';
		if(id==0) {
			url = path+'/customer/Product!save.action';
		}
		if(VerifyUtil.verifyNull("appname")){
			showInfoToastMiddle("应用名称不能为空!");
			$("#appname").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("packageName")){
			showInfoToastMiddle("应用包名不能为空!");
			$("#packageName").focus();
			return false;
		}
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"productVO.id":id,"productVO.name":name,"productVO.highPrice":highPrice,
		"productVO.midPrice":midPrice,"productVO.lowPrice":lowPrice}, 
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
	
	function  clearInfo() {
		$("#qid").val(0)
		$("#qname").val("")
	}
	
	
	
	
	function detailCustomChannel(url,productId) {
		$("#breadcrumb").html("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i>首页</a></li><li>应用列表</li><li class=\"active\">渠道列表</li>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"customChannelVO.productId":productId},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}
</script>
</s:if>
