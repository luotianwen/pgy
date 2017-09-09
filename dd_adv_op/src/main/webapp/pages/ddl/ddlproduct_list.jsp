<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="ddlProductVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<!-- 
		   		<input class="btn btn-success" type="button" onclick="createDdlProduct('<%=path %>/ddl')" value="新增"></td>
				 --><td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="ddlProductVO.id" value="<s:property value="ddlProductVO.id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="ddlProductVO.name" value="<s:property value="ddlProductVO.name"/>" style="width: 100px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn btn-primary">清 空</button></td></tr></table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto;overflow:auto">
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
						<th>单价</th>				
						<th>状态</th>				
						<th>创建时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="ddlProductVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td><s:property value="price"/></td>
		    		<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==status">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
		    		</td>
		    		<td><s:property value="createDate"/></td>
		    		<td> 			 	
						<a class="btn btn-primary" href="javascript: modifyDdlProduct('<%=path%>/ddl','<s:property value="id"/>')" title="修改">
						修改
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="ddlProductVO.page" operation="/ddl/DdlProduct!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="ddlProductVO.isPage==0">
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
		argus += "ddlProductVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&ddlProductVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&ddlProductVO.id=";
        argus += id;
        argus += "&ddlProductVO.name=";
        argus += name;
        argus += "&ddlProductVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/ddl/DdlProduct!list.action";
		jQuery.post(url,
				{"ddlProductVO.id":id,
				"ddlProductVO.name":name,
				"ddlProductVO.isPage":1
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
	
	
	function modifyDdlProduct(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/DdlProduct!modify.action', {"ddlProductVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createDdlProduct(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/DdlProduct!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	

	function checkStatus(dataString) {
		var data = dataString.toString().split(",");
		for(var i = 0; i < data.length; i = i + 1){
			$("#inline_" + data[i]).prop("checked", true);	
		}
	}

	function updateInfo() {
		var path = '<%=path%>/ddl/DdlProduct!save.action';
		var id =  $("#cid").val();
	    var name =  $("#name").val();
	    var status =  $("#status").val();
	    var price =  $("#price").val();
	    if(name=="") {
	    	showInfoToastMiddle("请填写渠道名称!");
	    	$("#name").focus();
	    	return false;
	    }
		var asiaChecked=$("input[name^='asiaCheckBox']:checked").val([]);
		var southChecked=$("input[name^='southCheckBox']:checked").val([]);
		var northChecked=$("input[name^='northCheckBox']:checked").val([]);
		var europeChecked=$("input[name^='europeCheckBox']:checked").val([]);
		var oceaniaChecked=$("input[name^='oceaniaCheckBox']:checked").val([]);
		var africaChecked=$("input[name^='africaCheckBox']:checked").val([]);
		var asiaCheckValues="";
		for(var i=0;i<asiaChecked.length;i++){
			asiaCheckValues += asiaChecked[i].value +",";
		}
		for(var i=0;i<southChecked.length;i++){
			asiaCheckValues += southChecked[i].value +",";
		} 
		for(var i=0;i<northChecked.length;i++){
			asiaCheckValues += northChecked[i].value +",";
		} 
		for(var i=0;i<europeChecked.length;i++){
			asiaCheckValues += europeChecked[i].value +",";
		} 
		for(var i=0;i<oceaniaChecked.length;i++){
			asiaCheckValues += oceaniaChecked[i].value +",";
		} 
		for(var i=0;i<africaChecked.length;i++){
			asiaCheckValues += africaChecked[i].value +",";
		} 
	    
	    
		$("#createBtn").attr("disabled", "disabled");

	    jQuery.post(path,{'ddlProductVO.id':id,
	       	'ddlProductVO.status':status,
	       	'ddlProductVO.name':name,
	       	'ddlProductVO.price':price,
	       	'ddlProductVO.country':asiaCheckValues,
	       	'ddlProductVO.version':1
	       	}
	       	,function(response){
				if(response=="-1") {
					showErrorToastMiddle("系统出错，请重试或联系管理员");
					$("#load").css({"display":"none"});
				} else {
					$("#load").css({"display":"none"});
					showInfoToastMiddle(response);
					
		           	doPageBottom('turn');
		           	checkStatus(asiaCheckValues);
		           
				}
			});
	}

</script>

</s:if>



