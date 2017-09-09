<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>修改客户信息</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css> 
</STYLE>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
<link href="<%=request.getContextPath()%>/css/kkfun.css"
  rel="stylesheet" type="text/css" />
<script type="text/javascript"  language="javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.js"></script>
<script type="text/javascript">
	function clickNext(){
		if(window.VerifyData)
		{
			return true;
		}
		else
		{
			alert("页面信息未完全载入,请稍后!");
			return false;
		}
	}
</script>
</HEAD>
<BODY style="	margin-left: 3px;margin-top: 0px;margin-right: 3px;margin-bottom: 0px;">
<s:form id="bdForm" action="Customer!update.action" onsubmit="return VerifyData(this);" theme="simple">
<s:hidden id="id" name="customerVO.id"/>
<s:hidden  name="customerVO.flowStatus"/>
<s:hidden   name="customerVO.page.pageNum" />
<s:hidden name="customerVO.page.totalRow" ></s:hidden>	
<s:hidden  name="customerVO.agentId"/>
<s:hidden  name="customerVO.businessDeveloperId"/>

	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="4">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon">
		<span id="lblmanagetitle" style="">修改客户信息</span></td>
		</tr>
	<tr >	<td nowrap="nowrap" width="15%" class="td_title">客户名称<font color="red">*</font></td>			 
            <td width="35%" nowrap="nowrap" >
            <role:equal type="BD,AG">
            <input id="name" name="customerVO.name" size="30"  value="${customerVO.name}"   
            <s:if test="customerVO.status != 0 && customerVO.status !=3"> readonly="readonly" </s:if>
            <s:else>onblur="checkName()"</s:else> />
            </role:equal>
			<role:equal type="SP,MG,BH"> 
			<input id="name" name="customerVO.name" size="30"  value="${customerVO.name}" />
			</role:equal> 
            <font color="red" id="userName_message"></font></td>            
			<td nowrap="nowrap" width="15%" class="td_title">客户ID</td>			 
            <td width="35%" nowrap="nowrap" >C${customerVO.id} </td>
		</tr>	
		

		<tr >
			<td nowrap="nowrap" width="15%" class="td_title">城市<font color="red">*</font></td>			 
          <td width="35%" nowrap="nowrap" colspan="3"><input id="city" name="customerVO.city" size="30"  value="${customerVO.city}"  />
			</td>			
			</tr>
						
	<role:equal type="SP,MG,BH">
		<tr ><td nowrap="nowrap" width="15%" class="td_title">所属代理商<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">${customerVO.agentName}
			</td>			
		</tr>
	</role:equal>		
	<role:equal type="SP,MG,AG,BH">
		<tr ><td nowrap="nowrap" width="15%" class="td_title">所属商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">${customerVO.businessDeveloperName}
			</td>			
		</tr>
	</role:equal>


<%@ include file="../common/user_modify.jsp" %>

		<tr ><td nowrap="nowrap" width="15%" class="td_title">备注</td>			 
            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="description" name="customerVO.description" cols="80" rows="5" >${customerVO.description}</textarea></td>
		</tr>
		<tr >
		    <td  colspan="4">
			<div align="center">
		       <input id="btn" class="button" type="submit" value=" 修 改 "  onclick="return clickNext();"/>&nbsp;&nbsp;
			<input type="button" class="button" id="backButton" value=" 返 回  " onclick="javascript: history.back()"/>
		    </div>
			</td>
		</tr>
	</table>
   

</s:form>
</BODY>

<script type="text/javascript">
jQuery.ajaxSetup({async:false});

//验证客户名称
<role:equal type="BD,AG">
	<s:if test="customerVO.status == 0 || customerVO.status ==3">
	checkName();
</s:if>
</role:equal>
var CUSTOMER_NAME_VALID = false;
function checkName(){
	jQuery("#name").val(jQuery.trim(jQuery("#name").val()));
	jQuery("#id").val(jQuery.trim(jQuery("#id").val()));
   var name = jQuery("#name").val();
   var id = jQuery("#id").val();
   if(name == ""){
	   jQuery("#userName_message").html("请输入客户名称");
       return;
    }
    var url = "<%=request.getContextPath()%>/Customer!validName.action";
   jQuery.post(url, {"customerVO.name":name,"customerVO.id":id}, function(response){    
       if(jQuery.trim(response) == "false"){
           CUSTOMER_NAME_VALID = true;
   		document.getElementById("btn").disabled=false;
           jQuery("#userName_message").html("");
       }else if($.trim(response) == "true"){
			CUSTOMER_NAME_VALID = false;
        	jQuery("#userName_message").html("客户名称重复");
        	jQuery("#name").focus();
        }
   });
}



function VerifyData(form){
	if (form["customerVO.name"].value ==""){
		alert("请输入客户名称！");
		form["customerVO.name"].focus();
		return false;
	}
<role:equal type="BD,AG">
	<s:if test="customerVO.status == 0 || customerVO.status ==3">
	if(CUSTOMER_NAME_VALID == false){
		checkName();
	}
	if (CUSTOMER_NAME_VALID == false){
		alert("您填写的客户名称已存在，请正确填写！");
		form["customerVO.name"].focus();
		return false;
	}
	</s:if>
</role:equal>
	if (form["customerVO.city"].value ==""){
		alert("请输入城市！");
		form["customerVO.city"].focus();
		return false;
	}

	if (form["portalUserVO.passwd"].value==""){
		alert("请填写登陆密码！");
		form["portalUserVO.passwd"].focus();
		return false;
	}
	return true;
}



function removeOptions(form){
	if(form.options.length>0){
		for(var i=(form.options.length-1);i>=0;i--){
			var o=form.options[i];
			form.options[i] = null;
			}	
		}
}

</script>
</HTML>
