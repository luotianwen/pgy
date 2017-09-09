<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="row">
	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>用户管理<span class="divider">/</span></li>
      	<li class="active">修改密码</li>
   		</ul>
		<form class="form-horizontal well">
			<fieldset>
			<div class="control-group">
				<label class="control-label" for="oldPwd">原密码<font color="red">*</font></label>
				<div class="controls">
			    <input type="password" class="input-xlarge" name="settingVO.oldPwd" value="${ settingVO.oldPwd }" id="oldPwd">
				</div>
			</div>
			<div class="control-group">
			 <label class="control-label line" for="newPwd">新密码<font color="red">*</font></label>
			 <div class="controls">
			   <input type="password" class="input-xlarge" name="settingVO.newPwd" value="${ settingVO.newPwd }" id="newPwd">
			 </div>
			</div>
			<div class="control-group">
			 <label class="control-label line" for="newPwd">请再次输入新密码<font color="red">*</font></label>
			 <div class="controls">
			   <input type="password" class="input-xlarge" name="settingVO.confirmPwd" value="${ settingVO.confirmPwd }" id="confirmPwd">
			 </div>
			</div>
		 	<div class="form-actions">
           		<button type="button" id="btn" class="btn btn-primary" onclick="javascript:modifyPwd();">修 改</button>
            	<button type="reset" class="btn">重设</button>
          	</div>
			</fieldset>			
		</form>
	</div>
</div>
<script type="text/javascript">
function modifyPwd(){	
	var oldPwd = $("#oldPwd").val();
	var newPwd = $("#newPwd").val();
	var confirmPwd = $("#confirmPwd").val();
	if(oldPwd=="") {
		showInfoToastMiddle("请输入原密码!");
		$("#oldPwd").focus();
		return ;
	}
	if(newPwd=="") {
		showInfoToastMiddle("请输入新密码!");
		$("#newPwd").focus();
		return ;
	}
	if(newPwd.length<6) {
		showInfoToastMiddle("密码长度不能小于6位!");
		$("#newPwd").focus();
		return ;
	}
	if(confirmPwd=="") {
		showInfoToastMiddle("请再输入新密码!");
		$("#confirmPwd").focus();
		return ;
	} 
	if(confirmPwd.length<6) {
		showInfoToastMiddle("二次密码长度不能小于6位!");
		$("#confirmPwd").focus();
		return ;
	} 
	if(newPwd!=confirmPwd) {
		showInfoToastMiddle("新密码与密码确认不一致,请检查!");
		$("#confirmPwd").focus();
		return ;
	} 
    var url = "<%=request.getContextPath()%>/Login!updatePasswd.action";
    $.post(url, {"settingVO.oldPwd":oldPwd,"settingVO.newPwd":newPwd,"settingVO.confirmPwd":confirmPwd}, function(response){    
       if($.trim(response) == "-1"){
            showErrorToast("提示","系统出错，请重试或联系管理员");
       } else if($.trim(response) == "0") {
            showErrorToast("提示","原始密码错误，请重新输入");
            $("#oldPwd").focus();      	
       } else {
       		showInfoToastMiddle(response);
       }
   });
}
</script>
