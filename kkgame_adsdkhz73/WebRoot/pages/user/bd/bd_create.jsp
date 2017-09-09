<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
	int roleId = userVO.getRoleId();
%>
<s:hidden id="id" name="bdVO.id"/>
<role:equal type="AG">
<s:hidden id="agentId" name="bdVO.agentId"/>
</role:equal>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="bdVO.id>0">修改商务拓展人员</s:if><s:else >新建商务拓展人员</s:else></h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="name">姓名</label>
	<div class="controls">
	<input id="name" type="text" class="input-medium" name="bdVO.name" value="<s:property value="bdVO.name"/>" size="30" onblur="checkName()" />
	</div>
</div>
<role:equal type="SP,MG">
<div class="control-group">
	<label class="control-label" for="agentId">所属代理商</label>
	<div class="controls">
		<s:select id="agentId" list="agentList" listKey="id" listValue="name" name="bdVO.agentId" cssStyle="width:166px"></s:select>
	</div>
</div>
</role:equal>
<div class="control-group">
	<label class="control-label" for="level">拓展级别</label>
	<div class="controls">
		<s:select list="#{'5':'5','10':'10','15':'15','20':'20','30':'30','50':'50'}" name="bdVO.level" id="level" cssStyle="width:166px"></s:select> [最大可拓展客户数]
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="telephone">电话号码</label>
	<div class="controls">
		<input id="telephone" type="text" class="input-medium" name="bdVO.telephone" value="<s:property value="bdVO.telephone"/>" size="30" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" />
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="mail">邮箱</label>
	<div class="controls">
		<input id="mail" type="text" class="input-medium" name="bdVO.mail" value="<s:property value="bdVO.mail"/>" size="30"/>
	</div>
</div>
<s:if test="bdVO.id==0">
<%@ include file="../common/user_create.jsp" %>
</s:if>
<s:if test="bdVO.id>0">
<%@ include file="../common/user_modify.jsp" %>
</s:if>
<div class="control-group">
	<label class="control-label" for="description">备注</label>
	<div class="controls">
		<textarea id="description" name="bdVO.description" cols="80" rows="5"><s:property value="bdVO.description"/></textarea>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="btn" onclick="javascript:updateBd();" class="btn btn-primary"><s:if test="productVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>

<script type="text/javascript">
var BD_NAME_VALID = false;
function checkName(){
	var id = $("#id").val();
	$("#name").val($.trim($("#name").val()));
   	var name = $("#name").val();
   	if(name == ""){
	   showInfoToastMiddle("请输入姓名");
       return;
    }
    var url = "<%=request.getContextPath()%>/Bd!validName.action";
    $.post(url, {"bdVO.id":id,"bdVO.name":name}, function(response){    
       if($.trim(response) == "false"){
    	   BD_NAME_VALID = true;
       } else if($.trim(response) == "true") {
        	showInfoToastMiddle("该姓名已存在");
        	$("#name").focus();
       }
   });
}

function updateBd() {
	var url = "<%=request.getContextPath()%>/Bd!save.action";
    var id = $("#id").val();
    if(id>0) {
    	url = "<%=request.getContextPath()%>/Bd!update.action";
    }
    var name = $("#name").val();
    if(name=="") {
    	showInfoToastMiddle("请输入商务拓展人员名称!");
    	$("#name").focus();
    	return ;
    }
    var agentId = $("#agentId").val();
    var telephone = $("#telephone").val();
    var mail = $("#mail").val();
    var level = $("#level").val();
    var description = $("#description").val();
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
    if(mail=="") {
    	showInfoToastMiddle("请输入商务拓展人员邮箱!");
    	$("#mail").focus();
    	return ;
    }
    var status = $("#status").val();
    jQuery.post(url, {"bdVO.id":id,"bdVO.level":level,"bdVO.name":name,"bdVO.mail":mail,"bdVO.agentId":agentId,"bdVO.description":description,"bdVO.telephone":telephone,
    	"portalUserVO.loginId":loginId,"portalUserVO.passwd":passwd,"portalUserVO.id":userId,"portalUserVO.status":status}, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#btn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	          	jQuery("#btn").removeAttr('disabled');
	           	doPageBottom('turn');
	        }
   });
}
</script>
