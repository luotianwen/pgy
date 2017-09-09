<%@ page  contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:hidden id="id" name="agentVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="agentVO.id>0">修改代理商信息</s:if><s:else >新建代理商信息</s:else></h2>
</div>

<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="name">代理商名称</label>
	<div class="controls">
    <input type="text" class="input-medium" name="agentVO.name" value="${agentVO.name}" id="name" onblur="checkName()">
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="type">代理商类型</label>
	<div class="controls">
	<s:select list="#{'1':'企业代理','2':'个人代理'}" id="type"  name = "agentVO.type" cssStyle="width:166px"></s:select>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="cooperateMode">合作模式描述</label>
	<div class="controls">
    <input type="text" class="input-medium" name="agentVO.cooperateMode" value="${agentVO.name}" id="cooperateMode">
	</div>
</div>
<div class="control-group">	
	<label class="control-label" for="level">代理商级别</label>
	<div class="controls">
	<s:select list="#{'5':'5','10':'10','20':'20','50':'50','100':'100','1000':'1000'}" name="agentVO.level" id="level"  cssStyle="width:166px"></s:select>
	</div>
</div>
<s:if test="agentVO.id==0">
<%@ include file="../common/user_create.jsp" %>
</s:if>
<s:if test="agentVO.id>0">
<%@ include file="../common/user_modify.jsp" %>
</s:if>
<div class="control-group">
	<label class="control-label" for="description">备注</label>
	<div class="controls">
		<textarea id="description" name="agentVO.description" cols="80" rows="5"><s:property value="agentVO.description"/></textarea>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="btn" onclick="javascript:updateAgent();" class="btn btn-primary"><s:if test="agentVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>

<script type="text/javascript">
var AGENT_NAME_VALID = false;
function checkName(){
	$("#id").val($.trim($("#id").val()));
	$("#name").val($.trim($("#name").val()));
	var id = $("#id").val();
   	var name = $("#name").val();
   	if(name == ""){
	   	showInfoToastMiddle("请输入代理商名称");
       	return;
    }
    var url = "<%=request.getContextPath()%>/Agent!validName.action";
    $.post(url, {"agentVO.name":name,"agentVO.id":id}, function(response){    
       if($.trim(response) == "false"){
    	   AGENT_NAME_VALID = true;
   			$("#btn").disabled=false;
       }else if($.trim(response) == "true") {
      		showInfoToastMiddle("该代理商名称已存在，请重新输入");
        	$("#name").focus();
        }
   });
}

function updateAgent() {
    var url = "<%=request.getContextPath()%>/Agent!save.action";
    var id = $("#id").val();
    if(id>0) {
    	url = "<%=request.getContextPath()%>/Agent!update.action";
    }
    var name = $("#name").val();
    if(name=="") {
    	showInfoToastMiddle("请输入代理商名称!");
    	$("#name").focus();
    	return ;
    }
    var type = $("#type").val();
    var level = $("#level").val();
    var userId = $("#userId").val();
    var cooperateMode = $("#cooperateMode").val();
    var description = $("#description").val();
    var loginId = $("#loginId").val();
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
    jQuery.post(url, {"agentVO.id":id,"agentVO.type":type,"agentVO.level":level,"agentVO.name":name,
    	"agentVO.cooperateMode":cooperateMode,"agentVO.description":description,
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