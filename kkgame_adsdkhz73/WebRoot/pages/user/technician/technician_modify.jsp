<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:form id="technicianForm" action="Technician!update.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="technicianVO.id"/>


	<table align=center cellPadding=0 cellSpacing=0 id=tb_content>
	<tbody>
		<tr>
		<td width="56%" nowrap>
		<div class=div_subtitle>当前位置<span class="arrow_subtitle">&gt;</span>技术人员管理<span class="arrow_subtitle">&gt;</span>
		修改技术人员</div></td>     
		<td width="44%" align="right" valign="bottom" nowrap>&nbsp; </td>
		</tr>
		</tbody>
	</table>
	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="4">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
		<span id="lblmanagetitle" style="">修改技术人员信息</span></td>
		</tr>
		
<%@ include file="../common/user_modify.jsp" %>

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
function VerifyData(form){

	if (form["portalUserVO.passwd"].value==""){
		alert("请填写登陆密码！");
		form["portalUserVO.passwd"].focus();
		return false;
	}
	return true;
}
function update(){
	var myform = document.getElementById("technicianForm");
	myform.action =  "Technician!update.action";
//	var action = "Technician!update.action";
var text = HttpSubmit(myform,myform.action);
}


</script>