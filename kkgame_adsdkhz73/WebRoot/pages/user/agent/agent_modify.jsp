<%@ page isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form id="agentForm" action="Agent!update.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="agentVO.id"/>
<table align=center cellPadding=0 cellSpacing=0 id=tb_content>
	<tbody>
		<tr>
		<td width="56%" nowrap>
		<div class=div_subtitle>当前位置<span class="arrow_subtitle">&gt;</span>商务管理中心<span class="arrow_subtitle">&gt;</span>
		修改代理商信息</div></td>     
		<td width="44%" align="right" valign="bottom" nowrap>&nbsp; </td>
		</tr>
		</tbody>
	</table>
	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="4">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
		<span id="lblmanagetitle" style="">修改代理商信息</span></td>
		</tr>
		<tr >	<td nowrap="nowrap" width="15%" class="td_title">代理商名称<font color="red">*</font></td>			 
            <td width="35%" nowrap="nowrap"><input id="name" name="agentVO.name" value="<s:property value="agentVO.name"/>" size="30" onblur="checkName()" /><font color="red" id="agentName_message"></font></td>
			<td nowrap="nowrap" width="15%" class="td_title">代理商类型<font color="red">*</font></td>	
			<td width="35%" nowrap="nowrap"><s:radio list="#{'1':'企业代理','2':'个人代理'}"  name = "agentVO.type" ></s:radio></td>
		</tr>
     	 <tr >	
			<td nowrap="nowrap" width="15%" class="td_title">合作模式描述<font color="red">*</font></td>			 
            <td width="35%" ><input id="name" name="agentVO.cooperateMode" size="30"  value="<s:property value="agentVO.cooperateMode"/>"/>
			<td nowrap="nowrap" width="15%" class="td_title">代理商级别<font color="red">*</font></td>			 
            <td width="35%" ><s:select list="#{'5':'5','10':'10','20':'20','50':'50','100':'100','1000':'1000'}" name="agentVO.level"  ></s:select></td>
		</tr>
		<%@ include file="../common/user_modify.jsp" %>

		<tr ><td nowrap="nowrap" width="15%" class="td_title">备注</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="description" name="agentVO.description" cols="80" rows="5" ><s:property value="agentVO.description"/></textarea></td>
		</tr>
		<tr >
		    <td  colspan="4">
			<div align="center">
		       <input id="btn" class="button" type="submit" value=" 修 改 "  />&nbsp;&nbsp;
			<input type="button" class="button" id="backButton" value=" 返 回  " onclick="javascript: history.back()"/>
		    </div>
			</td>
		</tr>
	</table>
   

</s:form>
<script type="text/javascript">
var AGENT_NAME_VALID = false;
function checkName(){
	$("#id").val($.trim($("#id").val()));
	$("#name").val($.trim($("#name").val()));
	var id = $("#id").val();
   var name = $("#name").val();
   if(name == ""){
	   $("#agentName_message").html("请输入代理商名称");
       return;
    }
    var url = "<%=request.getContextPath()%>/Agent!validName.action";
    $.post(url, {"agentVO.name":name,"agentVO.id":id}, function(response){    
       if($.trim(response) == "false"){
    	   AGENT_NAME_VALID = true;
   		document.getElementById("btn").disabled=false;
           $("#agentName_message").html("");
       }
        else if($.trim(response) == "true")
        {
        	$("#agentName_message").html("该名称已存在");
        	$("#name").focus();
        }
   });
}

function VerifyData(form){
	if (form["agentVO.name"].value ==""){
		alert("请输入代理商名称！");
		form["agentVO.name"].focus();
		return false;
	}
	var type = document.getElementsByName("agentVO.type");
	var flag = false ;
	for(var i=0; i<type.length;i++){			
	        if(type[i].checked){
	        	flag = true;
			}
		}
	if (flag == false){
		alert("请选择代理商类型！");
		return false;
	}

	if (form["agentVO.cooperateMode"].value==""){
		alert("请填写合作模式描述！");
		form["agentVO.cooperateMode"].focus();
		return false;
	}
	if (form["agentVO.level"].value=="" ){
		alert("请选择代理商级别！");
		form["agentVO.level"].focus();
		return false;
	}
	if (form["portalUserVO.passwd"].value==""){
		alert("请填写登陆密码！");
		form["portalUserVO.passwd"].focus();
		return false;
	}
	return true;
}
</script>