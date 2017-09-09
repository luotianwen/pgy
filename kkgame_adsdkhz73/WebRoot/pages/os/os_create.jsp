<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
<!--
.form-horizontal .control-group2 {
  margin-bottom: 5px ;
  *zoom: 1;
}
-->
</style>
<s:hidden id="osId" name="osVO.id"></s:hidden>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="osVO.id>0">修改平台</s:if><s:else >创建平台</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal">
	<fieldset>
	<div class="control-group">
		<label class="control-label" for="name">平台名称</label>
		<div class="controls">
	    <input type="text" class="input-xlarge" name="osVO.name" value="${ osVO.name }" id="name" onblur="checkOs()">
	   	<p id="os_message" class="help-block"></p>
		</div>
	</div>
	<div class="control-group2">
	 <label class="control-label line" for="intro">平台介绍</label>
	 <div class="controls">
	   <input type="text" class="input-xlarge" name="osVO.intro" value="${ osVO.intro }" id="intro">
	   <p class="help-block"></p>
	 </div>
	</div>
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="btn" onclick="javascript:save();" class="btn btn-primary"><s:if test="osVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>
<script type="text/javascript">
jQuery("#name").focus();
function save(){
	var name = jQuery("#name").val();
	if(name == ""){
	   jQuery("#os_message").html("<font color=\"red\">请输入平台名称</font>");
	   jQuery("#name").focus();
       return false;
    }
    var id = jQuery("#osId").val();
    var intro = jQuery("#intro").val();
    var url = "<%=request.getContextPath()%>/OsAdapter!save.action";
    if(id>0) {
    	url = "<%=request.getContextPath()%>/OsAdapter!update.action";
    }    
    jQuery.post(url, {"osVO.id":id,"osVO.name":name,"osVO.intro":intro}, function(response){    
       if(jQuery.trim(response) == "-1"){
            showErrorToast("提示","系统出错，请重试或联系管理员");
       } else {
       		showInfoToastMiddle(response);
       		doPageBottom('turn');
       }
   });
}

var LOGINID_VALID = false;
function checkOs(){
	jQuery("#name").val(jQuery.trim(jQuery("#name").val()));
   	var name = jQuery("#name").val();
   	if(name == ""){
	   showInfoToastMiddle("<font color=\"red\">请输入平台名称</font>");
	   jQuery("#btn").attr('disabled', 'disabled');
       return;
    }
    var id = jQuery("#osId").val();
    var url = "<%=request.getContextPath()%>/OsAdapter!validOsName.action";
    jQuery.post(url, {"osVO.id":id,"osVO.name":name}, function(response){    
       if(jQuery.trim(response) == "false"){
           LOGINID_VALID = true;
           jQuery("#os_message").html("");
           jQuery("#btn").removeAttr('disabled');           
       } else if(jQuery.trim(response) == "true"){
			LOGINID_VALID = false;
        	showInfoToastMiddle("<font color=\"red\">该平台名称已存在，请重新输入</font>");
        	jQuery("#name").focus();
        	jQuery("#btn").attr('disabled', 'disabled');
        }
   });
}
</script>
