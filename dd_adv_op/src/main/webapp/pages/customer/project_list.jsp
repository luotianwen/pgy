<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="projectVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>项目转化率设置
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="560"></div>
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="projectVO.id" onkeyup="this.value=this.value.replace(/\D/g,'')" value="<s:property value="projectVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="qname" type="text" class="input-medium inline" name="projectVO.name" value="<s:property value="projectVO.name"/>" style="width: 100px">
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
						<th>coo_ID</th>				
						<th>应用名称</th>				
						<th>结算转化率-高</th>	
						<th>结算转化率-中</th>	
						<th>结算转化率-低</th>	
						<th>最低利润率</th>	
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="projectVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    	    <td><s:property value="rateHigh"/></td>
		    		 <td><s:property value="rateMid"/></td>
		    		 <td><s:property value="rateLow"/></td>
		    		 <td><s:property value="minIncomeRate"/></td>
		    		<td>
		    			 
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: modifyProduct('<%=path%>/customer','<s:property value="id"/>')" title="修改">
						修改
						</a>
						 
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
					<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="projectVO.page" operation="/customer/Project!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="projectVO.isPage==0">
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
		argus += "projectVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&projectVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		
        argus += "&projectVO.id=";
        argus += id;
        argus += "&projectVO.name=";
        argus += name;
        argus += "&projectVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/customer/Project!list.action";
		jQuery.post(url,{"projectVO.id":id,"projectVO.name":name,"projectVO.isPage":1
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
	  		$modal.load(path+'/Project!modify.action', {"projectVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	 
	function updateProduct(path) {
	
		var id = $("#id").val();
		var rateHigh = $("#rateHigh").val();
		var rateLow = $("#rateLow").val();
		var rateMid = $("#rateMid").val();
		var minIncomeRate = $("#minIncomeRate").val();
		var url = path +'/customer/Project!update.action';
		 
		if(VerifyUtil.verifyNull("rateHigh")){
			showInfoToastMiddle("不能为空!");
			$("#rateHigh").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("rateLow")){
			showInfoToastMiddle("不能为空!");
			$("#rateLow").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("rateMid")){
			showInfoToastMiddle("不能为空!");
			$("#rateMid").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("minIncomeRate")){
			showInfoToastMiddle("不能为空!");
			$("#minIncomeRate").focus();
			return false;
		}
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"projectVO.id":id,"projectVO.rateHigh":rateHigh,"projectVO.rateLow":rateLow,
		"projectVO.rateMid":rateMid,"projectVO.minIncomeRate":minIncomeRate}, 
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
