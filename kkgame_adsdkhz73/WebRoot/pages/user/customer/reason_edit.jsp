<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="customerVO.newStatus==6">收回客户</s:if>
		<s:elseif test="customerVO.newStatus==5">放弃客户</s:elseif></h2>
</div>

<s:form id="customerForm" action="" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="customerVO.id"/>
<s:hidden  id="newStatus"  name="customerVO.newStatus" />
<s:hidden  id="businessDeveloperId"  name="customerVO.businessDeveloperId" />
<s:hidden id="status"   name="customerVO.status" />
<s:hidden id="relayBdId"  name="customerVO.relayBdId" />
<s:hidden id="relayStatus"  name="customerVO.relayStatus" />
	<table  class="table">
		<tr >	
			<td>客户名称-ID</td>			 
            <td>${customerVO.name}-C${customerVO.id}</td>
		</tr>
		<tr>
		<td>城市</td>	
			<td>${customerVO.city}	</td>					
		</tr>
		<tr ><td>所属代理商</td>	
			<td>${customerVO.agentName}</td>
		</tr>
		<tr>
			<td>所属拓展人员</td>	
			<td>${customerVO.businessDeveloperName}</td>
		</tr>	
		<s:if test="customerVO.newStatus==6">
			<tr id="cause"  >
			<td>收回原因</td>			 
            <td><textarea id="callBackReason" name="customerVO.callBackReason" cols="53" rows="5" ></textarea></td>
			</tr>
		</s:if>
		<s:elseif test="customerVO.newStatus==5">
			<tr id="cause"  >
			<td>放弃原因</td>			 
            <td><textarea id="abandonReason" name="customerVO.abandonReason" cols="53" rows="5" ></textarea></td>
			</tr>
			</s:elseif>	
		<tr >
		    <td  colspan="2">
			<div align="center">
			<input type="button" class="btn btn-primary" id="backButton1" value=" 提 交 " onclick="javascript:returnCode();"/>&nbsp;
		    </div>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
function returnCode(){ 
	var id = $("#id").val();
	var relayBdId = $("#relayBdId").val();
	var status = $("#status").val();
	var relayStatus = $("#relayStatus").val();
	var businessDeveloperId = $("#businessDeveloperId").val();
	var newStatus = $("#newStatus").val();
	var callBackReason = null;
	var abandonReason = null;
	if(newStatus==6) {
		callBackReason = $("#callBackReason").val();
		if(callBackReason==""||callBackReason==null) {
			showInfoToastMiddle("请填写收回原因！");
			$("#callBackReason").focus();
			return false;
		}
		
	} else if(newStatus==5) {
		abandonReason = $("#abandonReason").val();
		if(abandonReason==""||abandonReason==null) {
			showInfoToastMiddle("请填写放弃原因！");
			$("#abandonReason").focus();
			return false;
		}
	}
	jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId,"customerVO.callBackReason":callBackReason
			,"customerVO.abandonReason":abandonReason,"customerVO.relayStatus":relayStatus,"customerVO.newStatus":newStatus},
			function(data){				
				if(data == "-1"){
					showErrorToast("提示","系统出错，请重试或联系管理员");
				} else {
					showInfoToastMiddle(data);
	       			doPageBottom('turn');
				}
	});	
}
</script>