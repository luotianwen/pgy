<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<s:form id="bhForm" action="Bh!update.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="bhVO.id"/>
<role:equal type="BD">
<s:hidden id="businessDeveloperId" name="bhVO.businessDeveloperId"/>
</role:equal>
<table align=center cellPadding=0 cellSpacing=0 id=tb_content>
<tbody>
		<tr>
		<td width="56%" nowrap>
		<div class=div_subtitle>当前位置<span class="arrow_subtitle">&gt;</span>商务管理中心<span class="arrow_subtitle">&gt;</span>
		修改商务协助人员信息</div></td>     
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
            <td width="85%" colspan="3" nowrap="nowrap"><input id="name" name="bhVO.name" value="<s:property value="bhVO.name"/>" size="30" onblur="checkName()" /><font color="red" id="bhName_message"></font></td>
		</tr>
		<role:equal type="SP,MG,AG">
		<tr >	<td nowrap="nowrap" width="15%" class="td_title">所属商务拓展人员<font color="red">*</font></td>	
			<td width="85%" colspan="3" nowrap="nowrap"><s:select list="bdList" listKey="id" listValue="name" name="bhVO.businessDeveloperId"  cssStyle="width:220px;" headerKey="0" headerValue="--选择所属商务人员--"></s:select> </td>
		</tr>
		</role:equal>
		<tr >	<td nowrap="nowrap" width="15%" class="td_title">类型</td>	
			<td width="85%" colspan="3" nowrap="nowrap">
			<s:radio list="#{'false':'其他','true':'商务本人'}"  name = "bhVO.ifOwn" ></s:radio>
			</td>
		</tr>		
			
			
<%@ include file="../common/user_modify.jsp" %>

		<tr ><td nowrap="nowrap" width="15%" class="td_title">备注</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="description" name="bhVO.description" cols="80" rows="5" ><s:property value="bhVO.description"/></textarea></td>
		</tr>
		<tr >
		    <td  colspan="4">
			<div align="center">
		       <input id="btn" class="button" type="submit" value=" 修 改 "  />&nbsp;&nbsp;
			<input type="button" class="button" id="backButton" value=" 返 回  " onclick="history.back();"/>
		    </div>
			</td>
		</tr>
	</table>
   

</s:form>
<script type="text/javascript">

var BH_NAME_VALID = false;
checkName()
function checkName(){
	$("#id").val($.trim($("#id").val()));
	$("#name").val($.trim($("#name").val()));
	var id = $("#id").val();
   var name = $("#name").val();
   if(name == ""){
	   $("#bhName_message").html("请输入姓名");
       return;
    }
    var url = "<%=request.getContextPath()%>/Bh!validName.action";
    $.post(url, {"bhVO.name":name,"bhVO.id":id}, function(response){    
       if($.trim(response) == "false"){
    	   BH_NAME_VALID = true;
           $("#bhName_message").html("");
       }
        else if($.trim(response) == "true")
        {
        	$("#bhName_message").html("该姓名已存在");
        	$("#name").focus();
        }
   });
}
function VerifyData(form){	
	if (form["bhVO.name"].value ==""){
		alert("请输入商务协助人员名称！");
		form["bhVO.name"].focus();
		return false;
	}
	
	if (form["portalUserVO.passwd"].value==""){
		alert("请填写登陆密码！");
		form["portalUserVO.passwd"].focus();
		return false;
	}
	return true;
}

function doBack() {
	document.getElementById("bhForm").action="Bh!list.action";
	document.getElementById("bhForm").submit();
}
</script>