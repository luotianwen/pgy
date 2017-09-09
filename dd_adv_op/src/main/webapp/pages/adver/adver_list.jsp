<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="adverVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createAdver('<%=path %>/adver')" value="����"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" data-width="550" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="pid" type="text" class="input-medium inline" name="adverVO.id" value="<s:property value="adverVO.id"/>" style="width: 100px">
           		<label class="control-label inline" for="pname">����</label>
   				<input id="pname" type="text" class="input-medium inline" name="adverVO.name" value="<s:property value="adverVO.name"/>" style="width: 100px">
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
							<th>����</th>
							<th>״̬</th>
							<th>��ϵ��</th>
							<th>�绰</th>
							<th>QQ</th>
							<th>��ַ</th>
							<th>����ʱ��</th>
							<th>����</th>
						</tr>
					</thead>
				<tbody>
				
		    	<s:iterator value="adverVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td>
		    			<s:iterator value="#Option.adverType">
		    				<s:if test="type==key">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
		    		</td>
					<td><s:property value="linkmanName"/></td>
					<td><s:property value="linkmanPhone"/></td>
					<td><s:property value="linkmanQQ"/></td>
					<td><s:property value="linkmanAddress"/></td>
		    		<td>		    		
		    			<s:if test="status==0">�ر�</s:if>
						<s:if test="status==1">��</s:if>
					</td>
		    		<td><s:property value="createTime"/></td>
		    		<td> 	
						<a class="btn btn-primary" href="javascript: modifyAdver('<%=path%>/adver','<s:property value="id"/>')" title="�޸�">
						�޸�
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="adverForm" property="adverVO.page" operation="/adver/Adver!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="adverVO.isPage==0">
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
		argus += "adverVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&adverVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&adverVO.id=";
        argus += id;
        argus += "&adverVO.name=";
        argus += name;
        argus += "&adverVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/adver/Adver!list.action";
		jQuery.post(url,
				{"adverVO.id":id,
				"adverVO.name":name,
				"adverVO.isPage":1
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
	
	
	function modifyAdver(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Adver!modify.action', {"adverVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createAdver(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Adver!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	
</script>

</s:if>



