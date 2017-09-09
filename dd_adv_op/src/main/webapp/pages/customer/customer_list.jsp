<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="customerVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createCustomer('<%=path %>/customer')" value="����"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="customerVO.id" value="<s:property value="customerVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">����</label>
   				<input id="qname" type="text" class="input-medium inline" name="customerVO.name" value="<s:property value="customerVO.name"/>" style="width: 100px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">�� ѯ</button>
           		<button type="reset" onclick="clearInfo()" class="btn btn-primary">�� ��</button></td></tr></table>
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
						<th>�ͻ�ID</th>
						<th>����</th>
						<th>������</th>
						<th>��ϵ��</th>
						<th>��ϵ��ʽ</th>
						<th>�������</th>
						<th>���㷽ʽ</th>
						<th>����</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="customerVOList">  
		    		<tr>
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td><s:property value="agentName"/></td>
		    		<td><s:property value="contact"/></td>
		    		<td><s:property value="mobile"/></td>
		    		<td><s:property value="balancePercent"/></td>
		    		<td><s:property value="balanceType"/></td>
		    		<td>			
						<a class="btn btn-info" href="javascript: modifyCustomer('<%=path%>/customer','<s:property value="id"/>')" title="�޸�">
						�޸�
						</a>
						<!-- 
						&nbsp;&nbsp;|&nbsp;&nbsp;				
						<a class="btn btn-danger" href="javascript: deleteCustomer('<%=path%>/customer','<s:property value="id"/>','<s:property value="realName"/>')" title="ɾ��" >
						ɾ��
						</a> -->
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="8">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="customerForm" property="customerVO.page" operation="/customer/Customer!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="customerVO.isPage==0">
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
		argus += "customerVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&customerVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		
        argus += "&customerVO.id=";
        argus += id;
        argus += "&customerVO.name=";
        argus += name;
        argus += "&customerVO.isPage=1";
		return argus;
	}
	function  clearInfo() {
		$("#qid").val(0)
		$("#qname").val("")
	}
	
	
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/customer/Customer!list.action";
		jQuery.post(url,{"customerVO.id":id,"customerVO.name":name,"customerVO.isPage":1
		},function(response){
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
	function modifyCustomer(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Customer!modify.action', {"customerVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createCustomer(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Customer!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function updateCustomer(path) {
		var uid = $("#uid").val();
		var loginId = $("#loginId").val();
		var passwd = $("#passwd").val();
		var id = $("#id").val();
		var cname = $("#cname").val();
		var contact = $("#ccontact").val();
		var mobile = $("#cmobile").val();
		var qq = $("#cqq").val();
		var mail = $("#cmail").val();
		var address = $("#caddress").val();
		var agentId = $("#agentId").val();
		var balanceType = $("#cbalanceType").val();
		var balancePercent = $("#cbalancePercent").val();
		
		var url = path +'/customer/Customer!update.action';
		if(id==0) {
			url = path+'/customer/Customer!save.action';
		}
		if(VerifyUtil.verifyNull("cname")){
			showInfoToastMiddle("�ͻ����Ʋ���Ϊ��!");
			$("#cname").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("ccontact")){
			showInfoToastMiddle("�ͻ���ϵ����Ϣ����Ϊ��!");
			$("#ccontact").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("cmobile")){
			showInfoToastMiddle("�ͻ���ϵ���ֻ��Ų���Ϊ��!");
			$("#cmobile").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("loginId")){
			showInfoToastMiddle("�û��˺���Ϣ����Ϊ��!");
			$("#loginId").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("balanceType")){
			showInfoToastMiddle("���㷽ʽ��Ϣ����Ϊ��!");
			$("#balanceType").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("balancePercent")){
			showInfoToastMiddle("���������Ϣ����Ϊ��!");
			$("#balancePercent").focus();
			return false;
		}
		/**if($("#loginId").val().length < 6 || $("#loginId").val().length > 15){
			alert("�û��˺ų�����6-15���ַ�֮��!");
			$("#loginId").focus();
			return false;
		}**/
		if(VerifyUtil.verifyNull("passwd")){
			showInfoToastMiddle("������Ϣ����Ϊ��!");
			$("#passwd").focus();
			return false;
		}
		if($("#passwd").val().length < 6 || $("#passwd").val().length > 15){
			showInfoToastMiddle("���볤����6-15���ַ�֮��!");
			$("#passwd").focus();
			return false;
		}
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"customerVO.id":id,"customerVO.name":cname,"customerVO.contact":contact,"customerVO.agentId":agentId,
		"customerVO.mobile":mobile,"customerVO.qq":qq,"customerVO.mail":mail,"customerVO.address":address,"customerVO.balanceType":balanceType,"customerVO.balancePercent":balancePercent,
		"userVO.id":uid,"userVO.loginId":loginId,"userVO.passwd":passwd,"userVO.realName":cname}, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("ϵͳ���������Ի���ϵ����Ա");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	           	doPageBottom('turn');
	        }
	   });
	}	
	
	//����û��Ƿ����
	function checkExisitPU(val){
		if (VerifyUtil.verifyNull("loginId")){
			showInfoToastMiddle("<font color=red>����д��½�˺�!</font>");
			$("#loginId").focus();
			$("#surebtn").attr("disabled","disabled");
			return;
		} else{
			$("#surebtn").removeAttr('disabled');
		}
		$.post("user/User!exist.action",{loginId:val},function(data){
			if (data == "0"){
				showInfoToastMiddle("<font color=red>��¼�˺��Ѵ���</font>");
				$("#loginId").focus();
				$("#surebtn").attr("disabled","disabled");
			}else{
				$("#surebtn").removeAttr('disabled');
			}
		})
	};
	
</script>
</s:if>
