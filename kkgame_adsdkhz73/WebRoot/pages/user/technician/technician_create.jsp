<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:hidden id="id" name="portalUserVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="technicianVO.id>0">修改技术人员信息</s:if><s:else >新建技术人员信息</s:else></h2>
</div>

<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<s:if test="portalUserVO.id==0">
<%@ include file="../common/user_create.jsp" %>
</s:if>
<s:if test="portalUserVO.id>0">
<%@ include file="../common/user_modify.jsp" %>
</s:if>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="btn" onclick="javascript:updateTh();" class="btn btn-primary"><s:if test="productVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>
<script type="text/javascript">
function updateTh() {
	var url = "<%=request.getContextPath()%>/Technician!save.action";
    var id = $("#id").val();
    if(id>0) {
    	url = "<%=request.getContextPath()%>/Technician!update.action";
    }
    var loginId = $("#loginId").val();
    var userId = $("#userId").val();
    if(loginId=="") {
    	showInfoToastMiddle("请输入登录账号!");
    	$("#loginId").focus();
    	return ;
    }
    var passwd = $("#passwd").val();
    if(passwd=="") {
    	showInfoToastMiddle("请输入账号密码!");
    	$("#passwd").focus();
    	return ;
    }
    if(passwd.length<6) {
    	showInfoToastMiddle("密码小于6位，请重新输入!");
    	$("#passwd").focus();
    	return ;
    }
    var status = $("#status").val();
    jQuery.post(url, {"portalUserVO.loginId":loginId,"portalUserVO.passwd":passwd,"portalUserVO.id":userId,"portalUserVO.status":status}, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#btn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	          	jQuery("#btn").removeAttr('disabled');
	           	searchList();
	        }
   });
}
</script>