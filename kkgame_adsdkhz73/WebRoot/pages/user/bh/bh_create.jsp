<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<s:hidden id="id" name="bhVO.id"/>
<role:equal type="BD">
<s:hidden id="businessDeveloperId" name="bhVO.businessDeveloperId"/>
</role:equal>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="bhVO.id>0">修改商务协助人员</s:if><s:else >新建商务协助人员</s:else></h2>
</div>

<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="name">姓名</label>
	<div class="controls">
	<input id="name" type="text" class="input-medium" name="bhVO.name" value="<s:property value="bhVO.name"/>" size="30" onblur="checkName()" />
	</div>
</div>
<role:equal type="SP,MG,AG">
<div class="control-group">
	<label class="control-label" for="businessDeveloperId">所属商务拓展人员</label>
	<div class="controls">
		<s:select list="bdList" id="businessDeveloperId" listKey="id" listValue="name" name="bhVO.businessDeveloperId" cssStyle="width:166px"></s:select>
	</div>
</div>
</role:equal>
<div class="control-group">
	<label class="control-label" for="ifOwn">类型</label>
	<div class="controls">
		<s:select list="#{'false':'其他','true':'商务本人'}" name="bhVO.ifOwn" id="ifOwn" cssStyle="width:166px"></s:select>
	</div>
</div>
<s:if test="bhVO.id==0">
<%@ include file="../common/user_create.jsp" %>
</s:if>
<s:if test="bhVO.id>0">
<%@ include file="../common/user_modify.jsp" %>
</s:if>
<div class="control-group">
	<label class="control-label" for="description">备注</label>
	<div class="controls">
		<textarea id="description" name="bhVO.description" cols="80" rows="5"><s:property value="bhVO.description"/></textarea>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="btn" onclick="javascript:updateBh();" class="btn btn-primary"><s:if test="productVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>

<script type="text/javascript">
var BH_NAME_VALID = false;
function checkName(){
	var id = $("#id").val();
	$("#name").val($.trim($("#name").val()));
   	var name = $("#name").val();
   	if(name == ""){
	   showInfoToastMiddle("请输入姓名");
       return;
    }
    var url = "<%=request.getContextPath()%>/Bh!validName.action";
    $.post(url, {"bhVO.id":id,"bhVO.name":name}, function(response){    
       if($.trim(response) == "false"){
    	   BH_NAME_VALID = true;
           
       } else if($.trim(response) == "true"){
        	BH_NAME_VALID = false;
        	showInfoToastMiddle("该姓名已存在");
        	$("#name").focus();
        }
   });
}

function updateBh() {
	var url = "<%=request.getContextPath()%>/Bh!save.action";
    var id = $("#id").val();
    if(id>0) {
    	url = "<%=request.getContextPath()%>/Bh!update.action";
    }
    var name = $("#name").val();
    if(name=="") {
    	showInfoToastMiddle("请输入商务协助人员名称!");
    	$("#name").focus();
    	return ;
    }
    var businessDeveloperId = $("#businessDeveloperId").val();
    var ifOwn = $("#ifOwn").val();
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
    var status = $("#status").val();
    jQuery.post(url, {"bhVO.id":id,"bhVO.name":name,"bhVO.businessDeveloperId":businessDeveloperId,"bhVO.ifOwn":ifOwn,
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
