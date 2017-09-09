<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="apkInfoVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createApkInfo('<%=path %>/sdkinfo')" value="����"></td>
				<td align="right"><div id="ajax-modal" class="modal fade"  data-width="1000" tabindex="-1" ></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="control-label inline" for="pname">״̬</label>
				<select id="state" name="apkInfoVO.status">
					<option value="3200">��</option>
					<option value="3201">��</option>
				</select>
            	<label class="control-label inline" for="pname">apkId</label>
   				<input id="pid" type="text" class="input-medium inline" name="apkInfoVO.apkId" value="<s:property value="apkInfoVO.apkId"/>" style="width: 100px">
           		
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
						<th>apkId</th>				
						<th>�汾��</th>
						<th>����</th>				
						<th>״̬</th>				
						<th>����ʱ��</th>
						<th>url</th>
						<th>��ע</th>
						<th>����</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="apkInfoVOList">  
		    		<tr id="apkinfo_<s:property value="id"/>">
		    		<td><s:property value="apkId"/></td>
		    		<td><s:property value="versions"/></td>
		    		<td><s:property value="pkgName"/></td>
		    		<td>		    		
		    			<s:iterator value="#Option.enumStatus">
		    				<s:if test="key==state">
		    					<s:property value="value"/>
		    				</s:if>
		    			</s:iterator>
					</td>
		    		<td><s:property value="cdate"/></td>
		    		<td><s:property value="wwwurl"/></td>
		    		<td><s:property value="note"/></td>
		    		<td>
		    			 			 	
						<a class="btn btn-primary" href="javascript: modifyApkInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="�޸�">
						�޸�
						</a>
						<a class="btn btn-danger" href="javascript: deleteApkInfo('<%=path%>/sdkinfo','<s:property value="id"/>')" title="ɾ��">
						ɾ��
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="7">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="ApkInfoForm" property="apkInfoVO.page" operation="/sdkinfo/ApkInfo!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="apkInfoVO.isPage==0">
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
		argus += "apkInfoVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&apkInfoVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#pid").val();
		var name = $("#pname").val();
		
        argus += "&apkInfoVO.apkId=";
        argus += id;
        argus += "&apkInfoVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var id = $("#pid").val();
		var name = $("#pname").val();
		var state = $("#state").val();

		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/sdkinfo/ApkInfo!list.action";
		jQuery.post(url,
				{"apkInfoVO.apkId":id,
				"apkInfoVO.isPage":1,
				"apkInfoVO.state":state
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
	
	
	function modifyApkInfo(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/ApkInfo!modify.action', {"apkInfoVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createApkInfo(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/ApkInfo!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}

	function deleteApkInfo(path,id) {
		if(!sure('ȷ��Ҫɾ�� ['+id+'] ��')){
            return;
    	}
		jQuery.post(path+"/ApkInfo!delete.action",{"apkInfoVO.id":id},
		function(data){				
			if(data == "-1"){
				showErrorToast("��ʾ","ϵͳ���������Ի���ϵ����Ա");
			} else {
				showInfoToastMiddle(data);
				$("#apkinfo_"+id).remove();
			}
		});
	}	
	
</script>

</s:if>



