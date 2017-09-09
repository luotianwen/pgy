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
		   		<input class="btn btn-success" type="button" onclick="createCustomer('<%=path %>/customer')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">ID</label>
   				<input id="qid" type="text" class="input-medium inline" name="customerVO.id" value="<s:property value="customerVO.id"/>" style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		<label class="control-label inline" for="pname">名称</label>
   				<input id="qname" type="text" class="input-medium inline" name="customerVO.name" value="<s:property value="customerVO.name"/>" style="width: 100px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" onclick="clearInfo()" class="btn btn-primary">清 空</button></td></tr></table>
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
						<th>客户ID</th>
						<th>名称</th>
						<th>代理商</th>
						<th>联系人</th>
						<th>联系方式</th>
						<th>结算比例</th>
						<th>结算方式</th>
						<th>操作</th>
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
						<a class="btn btn-info" href="javascript: modifyCustomer('<%=path%>/customer','<s:property value="id"/>')" title="修改">
						修改
						</a>
						<!-- 
						&nbsp;&nbsp;|&nbsp;&nbsp;				
						<a class="btn btn-danger" href="javascript: deleteCustomer('<%=path%>/customer','<s:property value="id"/>','<s:property value="realName"/>')" title="删除" >
						删除
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
			showInfoToastMiddle("客户名称不能为空!");
			$("#cname").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("ccontact")){
			showInfoToastMiddle("客户联系人信息不能为空!");
			$("#ccontact").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("cmobile")){
			showInfoToastMiddle("客户联系人手机号不能为空!");
			$("#cmobile").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("loginId")){
			showInfoToastMiddle("用户账号信息不能为空!");
			$("#loginId").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("balanceType")){
			showInfoToastMiddle("结算方式信息不能为空!");
			$("#balanceType").focus();
			return false;
		}
		if(VerifyUtil.verifyNull("balancePercent")){
			showInfoToastMiddle("结算比例信息不能为空!");
			$("#balancePercent").focus();
			return false;
		}
		/**if($("#loginId").val().length < 6 || $("#loginId").val().length > 15){
			alert("用户账号长度在6-15个字符之间!");
			$("#loginId").focus();
			return false;
		}**/
		if(VerifyUtil.verifyNull("passwd")){
			showInfoToastMiddle("密码信息不能为空!");
			$("#passwd").focus();
			return false;
		}
		if($("#passwd").val().length < 6 || $("#passwd").val().length > 15){
			showInfoToastMiddle("密码长度在6-15个字符之间!");
			$("#passwd").focus();
			return false;
		}
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, {"customerVO.id":id,"customerVO.name":cname,"customerVO.contact":contact,"customerVO.agentId":agentId,
		"customerVO.mobile":mobile,"customerVO.qq":qq,"customerVO.mail":mail,"customerVO.address":address,"customerVO.balanceType":balanceType,"customerVO.balancePercent":balancePercent,
		"userVO.id":uid,"userVO.loginId":loginId,"userVO.passwd":passwd,"userVO.realName":cname}, 
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
	
	//检测用户是否存在
	function checkExisitPU(val){
		if (VerifyUtil.verifyNull("loginId")){
			showInfoToastMiddle("<font color=red>请填写登陆账号!</font>");
			$("#loginId").focus();
			$("#surebtn").attr("disabled","disabled");
			return;
		} else{
			$("#surebtn").removeAttr('disabled');
		}
		$.post("user/User!exist.action",{loginId:val},function(data){
			if (data == "0"){
				showInfoToastMiddle("<font color=red>登录账号已存在</font>");
				$("#loginId").focus();
				$("#surebtn").attr("disabled","disabled");
			}else{
				$("#surebtn").removeAttr('disabled');
			}
		})
	};
	
</script>
</s:if>
