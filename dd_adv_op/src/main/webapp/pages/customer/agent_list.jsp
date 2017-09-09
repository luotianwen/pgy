<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="agentVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr>
		   		<td>
		   		<button class="btn btn-success" type="button" onclick="createAgent('<%=path %>/customer')" >����</button>
		   		</td>
				<td align="right">
				<div id="ajax-modal" class="modal fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="agentVO.id" value="<s:property value="agentVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">����</label>
   				<input id="qname" type="text" class="input-medium inline" name="agentVO.name" value="<s:property value="agentVO.name"/>" style="width: 100px">
           		
           		<button class="btn btn-primary" onclick="searchList('<%=path%>')">�� ѯ</button>
           		<button type="reset" onclick="clearInfo()" class="btn btn-primary">�� ��</button>
           		</td>
           		</tr>
           		</table>
   			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 20px">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
   			<div id="dataList">
				<table id="dataTable" class="table table-bordered table-striped dataTable">
					</s:if>
					<thead>
						<tr>
						<th>ID</th>				
						<th>����</th>				
						<th>��ϵ��</th>				
						<th>��ϵ��ʽ</th>				
						<th>����ʱ��</th>
						<th>����</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="agentVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="name"/></td>
		    		<td><s:property value="contact"/></td>
		    		<td><s:property value="mobile"/></td>
		    		<td><s:property value="createTime"/></td>
		    		<td>
		    				
						<a class="btn btn-info" href="javascript: modifyAgent('<%=path%>/customer','<s:property value="id"/>')" title="�޸�">
						�޸�
						</a>
						<!-- 
						&nbsp;&nbsp;|&nbsp;&nbsp;				
						<a class="btn btn-danger" href="javascript: deleteAgent('<%=path%>/customer','<s:property value="id"/>','<s:property value="realName"/>')" title="ɾ��" >
						ɾ��
						</a> -->
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="6">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="customerForm" property="agentVO.page" operation="/customer/Agent!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="agentVO.isPage==0">
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
		argus += "agentVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&agentVO.page.pageSize=";
		argus += $("#pageSize").val();
		var id = $("#qid").val();
		var name = $("#qname").val();		
        argus += "&agentVO.id=";
        argus += id;
        argus += "&agentVO.name=";
        argus += name;
        argus += "&agentVO.isPage=1";
		return argus;
	}
	function searchList() {
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  		
		var id = $("#qid").val();
		var name = $("#qname").val();		
		url = "<%=request.getContextPath()%>/customer/Agent!list.action";
		jQuery.post(url,{"agentVO.id":id,"agentVO.name":name,"agentVO.isPage":1
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
	function modifyAgent(path,id) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Agent!modify.action', {"agentVO.id":id}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	function createAgent(path) {
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load(path+'/Agent!create.action', {}, function(){
	     		$modal.modal();
	    	});
		  }, 100);
	}
	
	function updateAgent(path) {
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
		
		var url = path +'/customer/Agent!update.action';
		if(id==0) {
			url = path+'/customer/Agent!save.action';
		}
		if(VerifyUtil.verifyNull("cname")){
			showInfoToastMiddle("���������Ʋ���Ϊ��!");
			$("#cname").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("ccontact")){
			showInfoToastMiddle("��������ϵ����Ϣ����Ϊ��!");
			$("#ccontact").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("cmobile")){
			showInfoToastMiddle("��������ϵ���ֻ��Ų���Ϊ��!");
			$("#cmobile").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("loginId")){
			showInfoToastMiddle("�û��˺���Ϣ����Ϊ��!");
			$("#loginId").focus();
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
		jQuery.post(url, {"agentVO.id":id,"agentVO.name":cname,"agentVO.contact":contact,
		"agentVO.mobile":mobile,"agentVO.qq":qq,"agentVO.mail":mail,"agentVO.address":address,
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
	
	function  clearInfo() {
		$("#qid").val(0)
		$("#qname").val("")
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
	
	function checkExistAgentName(path,val) {
		var url =  path+'/customer/Agent!exist.action';
		if (VerifyUtil.verifyNull("cname")){
			showInfoToastMiddle("<font color=red>����д����������!</font>");
			$("#cname").focus();
			$("#surebtn").attr("disabled","disabled");
			return;
		} else {
			$("#surebtn").removeAttr('disabled');
		}
		$.post(url,{"agentVO.name":val},function(data){
			if (data == "0"){
				showInfoToastMiddle("<font color=red>�������Ѵ���</font>");
				$("#cname").focus();
				$("#surebtn").attr("disabled","disabled");
			}else{
				$("#surebtn").removeAttr('disabled');
			}
		})
	};
	
</script>
</s:if>
