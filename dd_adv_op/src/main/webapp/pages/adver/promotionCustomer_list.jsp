<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="promotionCustomerVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
		   		<input class="btn btn-primary" type="button" onclick="createpromotionCustomer('<%=path %>/adver')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal  fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="promotionCustomerVO.id" value="<s:property value="promotionCustomerVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="qname" type="text" class="input-medium inline" name="promotionCustomerVO.countFee" value="<s:property value="promotionCustomerVO.countFee"/>" style="width: 100px">
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
					    <th >渠道ID</th>
						<th >名称</th>
						<th >创建时间</th>
						<th >说明</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="promotionCustomerVOList">  
		    		<tr >
						<td><s:property value="id"/></td>
						<td><s:property value="name"/></td>
						<td><s:property value="cdate"/></td>
						<td><s:property value="notes"/></td>
		    		<td>
	    			<%--	<a class="btn btn-info" href="javascript: detailpromotionCustomer('<%=path%>/adver/PromotionCustomer!detail.action','<s:property value="id"/>')" title="详情">
						详情
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;--%>
						<a class="btn btn-warning" href="javascript: modifypromotionCustomer('<%=path%>/adver','<s:property value="id"/>')" title="修改">
						修改
						</a> </td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="promotionCustomerVO.page" operation="/adver/PromotionCustomer!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				 	<s:if test="promotionCustomerVO.isPage==0">
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
		argus += "promotionCustomerVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&promotionCustomerVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		
        argus += "&promotionCustomerVO.id=";
        argus += id;
        argus += "&promotionCustomerVO.name=";
        argus += name;
        argus += "&promotionCustomerVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/adver/PromotionCustomer!list.action";
		jQuery.post(url,{"promotionCustomerVO.id":id,"promotionCustomerVO.countFee":name,"promotionCustomerVO.isPage":1
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
	function modifypromotionCustomer(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/PromotionCustomer!modify.action', {"promotionCustomerVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createpromotionCustomer(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/PromotionCustomer!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function detailpromotionCustomer(url,id) {
		$("#breadcrumb").html("<a href=\"#\" title=\"首页\" class=\"tip-bottom\"><i class=\"icon-home\"></i>渠道中心</a><a href=\"javascript: mainLoadData('<%=request.getContextPath() %>/adver/PromotionCustomer!list.action',212)\" class=\"tip-bottom\">降低资费配置列表</a><a href=\"#\" class=\"current\">降低资费配置详情</a>");
		var loading_img = $("<div class='container-fluid' style='text-align:center;padding-top:150px;min-height:350px'><img src='../img/ajax-loader.gif' /></div>");
		$("#dataContent").html('').append(loading_img);
		$.post(url,{"promotionCustomerVO.id":id},function(response){
			if(response==-1) {
				showErrorToast("提示","系统出错，请重试或联系管理员");
			} else {
				$("#dataContent").html(jQuery.trim(response));
			}
		});
	}
	//修改or新增
	function updatepromotionCustomer(path) {
		var id = $("#cid").val();
		 
		var url = path +'/adver/PromotionCustomer!update.action';
		if(id==0) {
			url = path+'/adver/PromotionCustomer!save.action';
		}

		 var name=$("#name").val();
		 var cdate=$("#cdate").val();
		 var notes=$("#notes").val();
		var contact=$("#contact").val();
		var email=$("#email").val();


		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {
			"promotionCustomerVO.id":id,
			"promotionCustomerVO.name":name,
			"promotionCustomerVO.cdate":cdate,
			"promotionCustomerVO.contact":contact,
			 "promotionCustomerVO.email":email,
			"promotionCustomerVO.notes":notes
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
