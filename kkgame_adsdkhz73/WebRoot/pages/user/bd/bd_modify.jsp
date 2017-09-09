<%@ page isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	int roleId = userVO.getRoleId();
%>
<s:form id="bdForm" action="Bd!update.action"  onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="bdVO.id"/>
<role:equal type="AG">
<s:hidden id="agentId" name="bdVO.agentId"/>
</role:equal>
<table align=center cellPadding=0 cellSpacing=0 id=tb_content>
	<tbody>
		<tr>
		<td width="56%" nowrap>
		<div class=div_subtitle>当前位置<span class="arrow_subtitle">&gt;</span>商务管理中心<span class="arrow_subtitle">&gt;</span>
		修改商务拓展人员信息</div></td>     
		<td width="44%" align="right" valign="bottom" nowrap>&nbsp; </td>
		</tr>
		</tbody>
	</table>
	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="4">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
		<span id="lblmanagetitle" style="">修改商务拓展人员信息</span></td>
		</tr>
		<tr >	<td nowrap="nowrap" width="15%" class="td_title">姓名<font color="red">*</font></td>			 
            <td width="85%" colspan="3" nowrap="nowrap"><input id="name" name="bdVO.name" value="<s:property value="bdVO.name"/>" size="30" onblur="checkName()" /><font color="red" id="bdName_message"></font></td>
		</tr>
		<!-- agentList  for   SP  MG  -->
		<role:equal type="SP,MG">
		<tr >	<td nowrap="nowrap" width="15%" class="td_title">所属代理商<font color="red">*</font></td>	
			<td width="85%" colspan="3" nowrap="nowrap"><s:select list="agentList" listKey="id" listValue="name" name="bdVO.agentId"  cssStyle="width:220px;"></s:select> </td>
		</tr>
		</role:equal>
		<tr ><td nowrap="nowrap" width="15%" class="td_title">拓展级别<font color="red">*</font></td>			 
          <td width="85%" nowrap="nowrap" colspan="3"><s:select list="#{'5':'5','10':'10','15':'15','20':'20','30':'30','50':'50'}" name="bdVO.level"  ></s:select> [同一时间段内该拓展人员最大可以拓展的客户数量]</td>			
		</tr>
		<tr ><td nowrap="nowrap" width="15%" class="td_title">电话号码<font color="red">*</font></td>			 
            <td width="35%" nowrap="nowrap"  colspan="3">
            <input id="telephone" name="bdVO.telephone" value="${bdVO.telephone }" size="30" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" /></td>
		</tr>
		<tr ><td nowrap="nowrap" width="15%" class="td_title">邮箱<font color="red">*</font></td>			 
            <td width="35%" nowrap="nowrap"  colspan="3">
            <input id="mail" name="bdVO.mail" value="${bdVO.mail }" size="30" /></td>
		</tr>
		<%@ include file="../common/user_modify.jsp" %>
		<tr ><td nowrap="nowrap" width="15%" class="td_title">备注</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="description" name="bdVO.description" cols="80" rows="5" ><s:property value="bdVO.description"/></textarea></td>
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
var BD_NAME_VALID = false;
function checkName(){
	$("#id").val($.trim($("#id").val()));
	$("#name").val($.trim($("#name").val()));
   var id = $("#id").val();
   var name = $("#name").val();
   if(name == ""){
	   $("#bdName_message").html("请输入姓名");
       return;
    }
    var url = "<%=request.getContextPath()%>/Bd!validName.action";
    $.post(url, {"bdVO.name":name,"bdVO.id":id}, function(response){    
       if($.trim(response) == "false"){
    	   BD_NAME_VALID = true;
           $("#bdName_message").html("");
       }
        else if($.trim(response) == "true")
        {
        	$("#bdName_message").html("该姓名已存在");
        	$("#name").focus();
        }
   });
}

function VerifyData(form){

	if (form["bdVO.name"].value ==""){
		alert("请输入商务拓展人员名称！");
		form["bdVO.name"].focus();
		return false;
	}
	jQuery("#loginId").val(jQuery.trim(jQuery("#loginId").val()));
   	var loginUserId = jQuery("#loginId").val();
	if (form["bdVO.agentId"].value =="" || form["bdVO.agentId"].value == 0){
		alert("请选择代理商！");
		form["bdVO.agentId"].focus();
		return false;
	}
	if (form["bdVO.telephone"].value ==""){
		alert("请输入商务拓展人员电话号码！");
		form["bdVO.telephone"].focus();
		return false;
	}
	if (form["portalUserVO.loginId"].value==""){
		alert("请填写登陆账号！");
		form["portalUserVO.loginId"].focus();
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
