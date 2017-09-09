<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:hidden name="productVO.id" id="productId"></s:hidden>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />		
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="productVO.id>0">修改产品</s:if><s:else >创建产品</s:else></h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="productName">产品名称</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.name" value="${productVO.name}" id="productName" onblur="checkName()">
   	<p id="productName_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="select">可选合作方式</label>
	<div class="controls">
		<s:select id="cooperateMode" list="#Policy.cooperateList" name="productVO.cooperateMode" listKey="key" listValue="value" headerKey="0" headerValue="激活/分成">
        </s:select>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="bdBuildInRegisterPrice">商务不可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bdBuildInRegisterPrice" value="${productVO.bdBuildInRegisterPrice}" id="bdBuildInRegisterPrice">
   	<p id="bdBuildInRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="cuBuildInRegisterPrice">客户不可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.cuBuildInRegisterPrice" value="${productVO.cuBuildInRegisterPrice}" id="cuBuildInRegisterPrice">
   	<p id="cuBuildInRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="bdUninstallRegisterPrice">商务可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bdUninstallRegisterPrice" value="${productVO.bdUninstallRegisterPrice}" id="bdUninstallRegisterPrice">
   	<p id="bdUninstallRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="cuUninstallRegisterPrice">客户可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.cuUninstallRegisterPrice" value="${productVO.cuUninstallRegisterPrice}" id="cuUninstallRegisterPrice">
   	<p id="cuUninstallRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="bdDividePercent">商务分成比例</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bdDividePercent" value="${productVO.bdDividePercent}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" id="bdDividePercent">%
   	<p id="bdDividePercent_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="cuDividePercent">客户分成比例</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.cuDividePercent" value="${productVO.cuDividePercent}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" id="cuDividePercent">%
   	<p id="cuDividePercent_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="url">ddl预览链接</label>
	<div class="controls">
    <input type="text" class="input-large" name="productVO.url" value="${productVO.url}"  id="url">
   	<p id="cuDividePercent_message" class="help-block"></p>
	</div>
</div>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="btn" onclick="javascript:updateProduct();" class="btn btn-primary"><s:if test="productVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>
<script type="text/javascript">
	function checkName() {
		jQuery("#productName").val(jQuery.trim(jQuery("#productName").val()));
	   	var name = jQuery("#productName").val();
	   	if(name == ""){
		   showInfoToastMiddle("请输入产品名称");
		   jQuery("#productName").focus();
		   jQuery("#btn").attr('disabled', 'disabled');
	       return;
	    } else {
	    	jQuery("#btn").removeAttr('disabled');
	    }
	    var id = jQuery("#productId").val();
	    var url = "<%=request.getContextPath()%>/Product!vaildName.action";
	    jQuery.post(url, {"productVO.name":name,"productVO.id":id}, function(response){
	       if(jQuery.trim(response) == "false"){
	           jQuery("#productName_message").html("");
	           jQuery("#btn").removeAttr('disabled');	           
	       } else if(jQuery.trim(response) == "true"){
	        	showInfoToastMiddle("该产品名称已存在，请重新输入");
	        	jQuery("#productName").focus();
	        	jQuery("#btn").attr('disabled', 'disabled');
	        }
	   });
	}
	
	function updateProduct() {
		jQuery("#productName").val(jQuery.trim(jQuery("#productName").val()));
	   	var name = jQuery("#productName").val();
	   	var cooperateMode = jQuery("#cooperateMode").val();
	   	var bdBuildInRegisterPrice = jQuery("#bdBuildInRegisterPrice").val();
	   	var cuBuildInRegisterPrice = jQuery("#cuBuildInRegisterPrice").val();
	   	var bdUninstallRegisterPrice = jQuery("#bdUninstallRegisterPrice").val();
	   	var cuUninstallRegisterPrice = jQuery("#cuUninstallRegisterPrice").val();
	   	var bdDividePercent = jQuery("#bdDividePercent").val();
	   	var cuDividePercent = jQuery("#cuDividePercent").val();
	   	var ddlurl = jQuery("#url").val();
	   	var id = jQuery("#productId").val();
	   	if(name == ""){
		   showInfoToastMiddle("请输入产品名称");
	       return;
	    }
	    var url = "";
	    if(id>0) {
	    	url = "<%=request.getContextPath()%>/Product!update.action";
	    } else {
	    	url = "<%=request.getContextPath()%>/Product!save.action";
	    	
	    }
	    jQuery.post(url, {"productVO.id":id,"productVO.name":name,
	    	"productVO.bdBuildInRegisterPrice":bdBuildInRegisterPrice,"productVO.cuBuildInRegisterPrice":cuBuildInRegisterPrice,
	    	"productVO.bdUninstallRegisterPrice":bdUninstallRegisterPrice,"productVO.cuUninstallRegisterPrice":cuUninstallRegisterPrice,
	    	"productVO.bdDividePercent":bdDividePercent,"productVO.cuDividePercent":cuDividePercent,
	    	"productVO.cooperateMode":cooperateMode,"productVO.url":ddlurl	
	    }, function(response){
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