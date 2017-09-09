<%@ page   isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form id="customerForm" action="Customer!updateStruts.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="customerVO.id"/>
<s:hidden id="status" name="customerVO.status"/>
<s:hidden id="newStatus" name="customerVO.newStatus" value="10"/>
<s:hidden  id="businessDeveloperId"  name="customerVO.businessDeveloperId" />
<s:hidden id="relayStatus"  name="customerVO.relayStatus" />
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>客户移交</h2>
</div>	
<table class="table">		
	<tr >	<td>客户名称-ID</td>			 
            <td>${customerVO.name}-C${customerVO.id}</td>
		</tr>	
		<tr >
			<td>城市</td>	
			<td><s:property value="customerVO.city"/></td>
		</tr>
		<tr >	<td>所属代理商</td>	
			<td>${customerVO.agentName}</td>
		</tr>
		<tr >
			<td>所属拓展人员</td>	
			<td>${customerVO.businessDeveloperName}</td>
		</tr>
		<tr ><td>备注</td>			 
            <td><textarea id="description" name="bdVO.description" cols="50" rows="5" readonly="readonly">${customerVO.description}</textarea></td>
		</tr>
		<tr ><td>移交给商务拓展人员<font color="red">*</font></td>	
			<td><s:select id="relayBdId" list="bdList" listKey="id" listValue="name" name="customerVO.relayBdId" headerKey="0" headerValue="--请选择商务拓展人员--" onchange="checkvalid()" cssStyle="width:220px;"></s:select> </td>
			
		</tr>
		<tr >
		    <td  colspan="2">
			<div align="center">
		       <input id="btn" class="btn btn-primary" type="button" value=" 提 交 " onclick="javascript:save();"  />&nbsp;&nbsp;
		    </div>
			</td>
		</tr>
	</table>
   

</s:form>
<script type="text/javascript">
var DEVOLVE_VALID = false;
function checkvalid(){

   var relayBdId = $("#relayBdId").val();
    var url = "<%=request.getContextPath()%>/Customer!validDevolveTerm.action";
    if(relayBdId != '' && relayBdId != 0){
    $.post(url, {"customerVO.businessDeveloperId":relayBdId}, function(response){    
       if($.trim(response) == "true"){
    	   DEVOLVE_VALID = true;
        //   $("#userName_message").html("");
       } else if($.trim(response) == "false"){
           showInfoToastMiddle("对方已达到最大拓展数，请选择其他商务拓展人员");
       // 	$("#userName_message").html("客户名称重复");
       // 	$("#name").focus();
        }
   });
   }
}
function save(){
	var id = $("#id").val();
	var status = $("#status").val();
	var relayStatus = $("#relayStatus").val();
	var businessDeveloperId = $("#businessDeveloperId").val();
	var newStatus = $("#newStatus").val();
	var relayBdId = $("#relayBdId").val();
	if(relayBdId == 0){
		showInfoToastMiddle("请选择商务拓展人员！");
		$("#relayBdId").focus();
		return  false;
	}	
	if(DEVOLVE_VALID == false){
		showInfoToastMiddle("请正确选择商务拓展人员！");
		$("#relayBdId").focus();
		return  false;
	}
	jQuery.post("Customer!updateStruts.action",
			{"customerVO.id":id,"customerVO.businessDeveloperId":businessDeveloperId
			,"customerVO.status":status,"customerVO.relayBdId":relayBdId
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
</script>