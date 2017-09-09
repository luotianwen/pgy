<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:hidden id="userId" name="portalUserVO.id" />
<div class="control-group">
	<label class="control-label" for="loginId">登录帐号</label>
	<div class="controls">
    <input id="loginId" type="text" class="input-medium" name="portalUserVO.loginId" size="30" onblur="checkLoginId()" />
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="passwd">登录密码</label>
	<div class="controls">
		<input id="passwd" type="text" class="input-medium" name="portalUserVO.passwd" size="30" value="<s:property value="portalUserVO.passwd"/>"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="status">状态</label>
	<div class="controls">
		<s:select id="status" list="#{'0':'正常','1':'受限','2':'锁定'}"  name="portalUserVO.status" cssStyle="width:166px"></s:select>
	</div>
</div>

<script type="text/javascript">
var LOGINID_VALID = false;
function checkLoginId(){
	jQuery("#loginId").val(jQuery.trim(jQuery("#loginId").val()));
   	var loginId = jQuery("#loginId").val();
   	if(loginId == ""){
	   showInfoToastMiddle("请输入登陆账号");
//	   jQuery("#loginId").focus();
       return;
    }
    var url = "<%=request.getContextPath()%>/PortalUser!validLoginId.action";
    jQuery.post(url, {"portalUserVO.loginId":loginId}, function(response){    
       if(jQuery.trim(response) == "false"){
           LOGINID_VALID = true;
       } else if(jQuery.trim(response) == "true"){
			LOGINID_VALID = false;
        	showInfoToastMiddle("该登陆账号已存在，请重新输入");
//        	jQuery("#loginId").focus();
       }
   });
}

</script>