<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="ddlChannelVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px;overflow:auto">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<!-- 
		   		<input class="btn btn-success" type="button" onclick="createDdlChannel('<%=path %>/ddl')" value="����"></td>
				 --><td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="ddlChannelVO.id" value="<s:property value="ddlChannelVO.id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">����</label>
   				<input id="pname" type="text" class="input-medium inline" name="ddlChannelVO.name" value="<s:property value="ddlChannelVO.name"/>" style="width: 100px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">�� ѯ</button>
           		<button type="reset" class="btn btn-primary">�� ��</button></td></tr></table>
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
						<th>����</th>				
						<th>״̬</th>				
						<th>����ʱ��</th>
						<th>����</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="ddlChannelVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td>
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==status">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
		    		</td>
		    		<td><s:property value="createDate"/></td>
		    		<td>
		    			 			 	
						<a class="btn btn-primary" href="javascript: modifyDdlChannel('<%=path%>/ddl','<s:property value="id"/>')" title="�޸�">
						�޸�
						</a>
						
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="sdkInfoForm" property="ddlChannelVO.page" operation="/ddl/DdlChannel!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="ddlChannelVO.isPage==0">
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
		argus += "ddlChannelVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&ddlChannelVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&ddlChannelVO.id=";
        argus += id;
        argus += "&ddlChannelVO.name=";
        argus += name;
        argus += "&ddlChannelVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/ddl/DdlChannel!list.action";
		jQuery.post(url,
				{"ddlChannelVO.id":id,
				"ddlChannelVO.name":name,
				"ddlChannelVO.isPage":1
				},
		function(response){
			if(response=="-1") {
				showErrorToastMiddle("ϵͳ���������Ի���ϵ����Ա");
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
	
	
	function modifyDdlChannel(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/DdlChannel!modify.action', {"ddlChannelVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createDdlChannel(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/DdlChannel!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



