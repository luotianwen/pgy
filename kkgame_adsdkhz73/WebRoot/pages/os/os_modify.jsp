<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:form id="produceAdapterForm" onsubmit="return VerifyData(this);" action="OsAdapter!update.action" theme="simple">
<s:hidden id="id" name="osVO.id"></s:hidden>
	<table align=center cellPadding="0" cellSpacing="0" id=tb_content>
		<tbody>
		<tr>
		<td width="56%" nowrap>
		<div class=div_subtitle>当前位置<span class="arrow_subtitle">&gt;</span>系统资源管理<span class="arrow_subtitle">&gt;</span>
		修改平台</div></td>     
		<td width="44%" align="right" valign="bottom" nowrap>&nbsp; </td>
		</tr>
		</tbody>
	</table>
	<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input">
		<tr><td class="td_head" colspan="2">
		<img id="images1"  src="<%=request.getContextPath()%>/images/icon_note.gif" width="18" height="16" class="icon"/>
		<span id="lblmanagetitle" style="">修改平台 </span></td>
		</tr>
		<tr >
			<td nowrap="nowrap" width="15%" class="td_title">平台名称<font color="red">*</font></td>			 
            <td width="85%"  nowrap="nowrap">
        		<input type="text" name="osVO.name" id="name" onblur="checkOs()" value="<s:property value="osVO.name"/>">
        		 <font color="red" id="os_message"></font>
			</td>
		</tr>	
		<tr >
			<td nowrap="nowrap" width="15%" class="td_title">平台介绍</td>			 
            <td width="85%" nowrap="nowrap" >
					<input type="text" name="osVO.intro" id="intro"  value="<s:property value="osVO.intro"/>">
			</td>
		</tr>
		
		<tr >
		    <td  colspan="2">
			<div align="center">
		       <input id="btn" class="button" type="submit" value=" 修 改 "/>&nbsp;&nbsp;
			<input type="button" class="button" id="backButton" value=" 返 回 " onclick="javascript: history.back()"/>
		    </div>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
//去掉分辨率0*0
	document.getElementById("screenId").options.remove(1);

function VerifyData(form){
	var name = jQuery("#name").val();
	if(name == ""){
	   jQuery("#os_message").html("请输入平台名称");
	   jQuery("#name").focus();
       return false;
    }
	return true;
}

var LOGINID_VALID = false;
function checkOs(){
	jQuery("#name").val(jQuery.trim(jQuery("#name").val()));
   var name = jQuery("#name").val();
   var id = jQuery("#id").val();
   if(name == ""){
	   jQuery("#os_message").html("请输入平台名称");
       return;
    }
    var url = "<%=request.getContextPath()%>/OsAdapter!validOsName.action";
    jQuery.post(url, {"osVO.name":name,"osVO.id":id}, function(response){    
       if(jQuery.trim(response) == "false"){
           LOGINID_VALID = true;
           jQuery("#os_message").html("");
           jQuery("#btn").removeAttr('disabled');
       }
        else if(jQuery.trim(response) == "true"){
			LOGINID_VALID = false;
        	jQuery("#os_message").html("该平台名称已存在，请重新输入");
        	jQuery("#name").focus();
        	jQuery("#btn").attr('disabled', 'disabled');
        }
   });
}

</script>
