<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>客户审核</h2>
</div>

<s:form id="customerForm" action="Customer!updateStruts" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="customerVO.id"/>
<s:hidden id="newStatus"  name="customerVO.newStatus" />
<s:hidden id="businessDeveloperId"  name="customerVO.businessDeveloperId" />
<s:hidden id="status"   name="customerVO.status" />
<s:hidden id="relayBdId"  name="customerVO.relayBdId" />
<s:hidden id="relayStatus"  name="customerVO.relayStatus" />
	<table class="table">
		<tr >	
			<td>客户ID</td>			 
			<td>C${customerVO.id}</td>
			</tr>	
		<tr >	
			<td>客户名称</td>			 
            <td><input id="name" type="text" name="customerVO.name" size="30" value="${customerVO.name}" onblur="checkName()" />
            <role:equal type="SP,MG,BH">
            <IMG class="icon" id="Img01" height="13" src="<%=request.getContextPath()%>/images/search1.gif" style="CURSOR: hand"
										width="13"  onclick="searchCustomerList(); return true;">
			</role:equal>							
			</td>
		</tr><tr>
			<td>城市</td>	
			<td><input id="city" type="text" name="customerVO.city" value="${customerVO.city}" />
			</td>
			</tr>		
		<tr >
			<td>所属代理商</td>	
			<td>${customerVO.agentName}</td>
			</tr><tr>
			<td>所属拓展人员</td>	
			<td>${customerVO.businessDeveloperName}</td>
		</tr>
		<tr><td>${customerVO.businessDeveloperName}拓展级别</td>	
			<td>${customerVO.bdLevel}</td>
			</tr><tr>
			<td>${customerVO.agentName}拓展级别</td>	
			<td>${customerVO.agentLevel}</td>
		</tr>
		<tr >	<td>${customerVO.businessDeveloperName}已有拓展客户数</td>	
			<td>${customerTotalForBd}</td>
			</tr><tr>
			<td>${customerVO.agentName}已有拓展客户数</td>	
			<td>${customerTotalForAgent}</td>
		</tr>
	
		<tr ><td>备注</td>			 
            <td><textarea id="description" name="customerVO.description" cols="53" rows="5" readonly="readonly">${customerVO.description}</textarea></td>
		</tr>
			<tr>
				<td>审核意见&nbsp;<font color="red">*</font></td>
				<td>
			<input type="radio" name="auditResult" value="Y"> 
				通过 <input type="radio" name="auditResult" value="N"> 
				不通过</td>
			</tr>
			<tr id="cause" >
				<td>不通过原因</td>			 
            	<td><textarea id="auditFailReason" name="customerVO.auditFailReason" cols="53" rows="5" ></textarea></td>
		</tr>
		<tr >
		    <td  colspan="2">
			<div align="center">
			<input type="button" class="btn btn-primary" id="backButton1" value=" 提 交 " onclick="javascript:updateStruts();" />&nbsp;
		    </div>
			</td>
		</tr>
	</table>
   

</s:form>
<script type="text/javascript">
var USER_NAME_VALID = false;
function checkName(){
	jQuery("#name").val(jQuery.trim(jQuery("#name").val()));
	jQuery("#id").val(jQuery.trim(jQuery("#id").val()));
   	var name = jQuery("#name").val();
   	var id = jQuery("#id").val();
   	if(name == ""){
	   showInfoToastMiddle("请输入用户名");
       return;
    }
    var url = "<%=request.getContextPath()%>/Customer!validName.action";
    jQuery.post(url, {"customerVO.name":name,"customerVO.id":id}, function(response){    
       if(jQuery.trim(response) == "false"){
           USER_NAME_VALID = true;
       } else if(jQuery.trim(response) == "true"){
        	showInfoToastMiddle("用户名重复");
        	jQuery("#name").focus();
        }
   });
}
//
function updateStruts(){
   	var id = $("#id").val();
	var name = $("#name").val();
	var city = $("#city").val();
	var relayBdId = $("#relayBdId").val();
	var status = $("#status").val();
	var relayStatus = $("#relayStatus").val();
	var businessDeveloperId = $("#businessDeveloperId").val();
	if(name=="") {
		showInfoToastMiddle("请输入客户名称！");
		$("#name").focus();
		return false;
	}
	if(city=="") {
		showInfoToastMiddle("请输入城市！");
		$("#city").focus();
		return false;
	}
 	var auditResult = '';	
 	var auditFailReason=null;
	  // if($("input[type=radio]:checked")){ 
 	if($("input[name=items]:checked")){ 
       	auditResult = $("input[name=auditResult]:checked").val();
 	} 	 
 	if(auditResult == 'Y'){
		 //审核通过 
		 newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_DEVING"/>'; 
 	}else if(auditResult == 'N'){
		 //审核不通过
	 	$("#auditFailReason").val($.trim($("#auditFailReason").val()));
	 	auditFailReason = $("#auditFailReason").val();
	 	if(auditFailReason == null || auditFailReason == ''){
		 	showInfoToastMiddle("请填写不通过原因！");
		 	return false;
		}
	 	newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_NO_PASS"/>'; 
	}else{
		showInfoToastMiddle("请选择审核意见！");
		return false;
	}
	jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.name":name,"customerVO.city":city,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId,"customerVO.auditFailReason":auditFailReason
			,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
		});    
}

function searchCustomerList() {
  window.open("Customer!allCustomerList.action",10,20);
}


</script>
