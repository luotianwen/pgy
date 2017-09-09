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
		   		<input class="btn btn-success" type="button" onclick="createDdlProject('<%=path %>/ddl')" value="����"></td>
				 -->
				 <td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1" data-width="1000"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">coo_id</label>
   				<input id="pid" type="text" class="input-medium inline" name="ddlProjectVO.coo_id" value="<s:property value="ddlProjectVO.coo_id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">����</label>
   				<input id="pname" type="text" class="input-medium inline" name="ddlProjectVO.name" value="<s:property value="ddlProjectVO.name"/>" style="width: 100px">
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
						<th>coo_id</th>				
						<th>��Ʒ</th>				
						<th>����</th>				
						<th>ת����</th>
						<th>ת��</th>
						<th>�Ƿ�����</th>
						<th>״̬</th>
						<th>����ʱ��</th>
						<th>����</th>
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
						<a class="btn btn-primary" href="javascript: modifyDdlProject('<%=path%>/ddl','<s:property value="id"/>')" title="�޸�">
						�޸�
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



