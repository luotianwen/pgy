<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:hidden name="productVO.id" id="productId"></s:hidden>
<s:hidden name="productVO.projectId" id="projectId"></s:hidden>
<s:hidden name="productVO.bhId" id="bhId"></s:hidden>
<s:hidden name="productVO.cooperateMode" id="cooperateMode"></s:hidden>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />		
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>修改项目产品价格</h2>
</div>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<div class="control-group">
	<label class="control-label" for="productName">产品名称</label>
	<div class="controls">
	<s:property value="productVO.name"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="select">合作方式</label>
	<div class="controls">
		<s:iterator value="#Policy.cooperateList">
			<s:if test="productVO.cooperateMode==key">
				<s:property value="value"/>
			</s:if>
		</s:iterator>
	</div>
</div>
<s:if test="productVO.cooperateMode==1">
<div class="control-group">
	<label class="control-label" for="cuBuildInRegisterPrice">客户不可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.cuBuildInRegisterPrice" value="${productVO.cuBuildInRegisterPrice}" id="cuBuildInRegisterPrice">
   	<p id="cuBuildInRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="bdBuildInRegisterPrice">商务不可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bdBuildInRegisterPrice" value="${productVO.bdBuildInRegisterPrice}" id="bdBuildInRegisterPrice">
   	<p id="bdBuildInRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<s:if test="productVO.bhId>0">
<div class="control-group">
	<label class="control-label" for="bhBuildInRegisterPrice">中间人不可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bhBuildInRegisterPrice" value="${productVO.bhBuildInRegisterPrice}" id="bhBuildInRegisterPrice">
   	<p id="cuBuildInRegisterPrice_message" class="help-block"></p>
	</div>
</div>
</s:if>
<div class="control-group">
	<label class="control-label" for="cuUninstallRegisterPrice">客户可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.cuUninstallRegisterPrice" value="${productVO.cuUninstallRegisterPrice}" id="cuUninstallRegisterPrice">
   	<p id="cuUninstallRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="bdUninstallRegisterPrice">商务可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bdUninstallRegisterPrice" value="${productVO.bdUninstallRegisterPrice}" id="bdUninstallRegisterPrice">
   	<p id="bdUninstallRegisterPrice_message" class="help-block"></p>
	</div>
</div>
<s:if test="productVO.bhId>0">
<div class="control-group">
	<label class="control-label" for="bhUninstallRegisterPrice">中间人可卸载激活(<font color="red">分</font>)</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bhUninstallRegisterPrice" value="${productVO.bhUninstallRegisterPrice}" id="bhUninstallRegisterPrice">
   	<p id="bhUninstallRegisterPrice_message" class="help-block"></p>
	</div>
</div>
</s:if>
</s:if>
<s:if test="productVO.cooperateMode==2">
<div class="control-group">
	<label class="control-label" for="cuDividePercent">客户分成比例</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.cuDividePercent" value="${productVO.cuDividePercent}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" id="cuDividePercent">%
   	<p id="cuDividePercent_message" class="help-block"></p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="bdDividePercent">商务分成比例</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bdDividePercent" value="${productVO.bdDividePercent}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" id="bdDividePercent">%
   	<p id="bdDividePercent_message" class="help-block"></p>
	</div>
</div>

<s:if test="productVO.bhId>0">
<div class="control-group">
	<label class="control-label" for="bhDividePercent">中间人分成比例</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.bhDividePercent" value="${productVO.bhDividePercent}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" id="bhDividePercent">%
   	<p id="bhDividePercent_message" class="help-block"></p>
	</div>
</div>
</s:if>
</s:if>
</fieldset>
</form>
</div>
<div class="modal-footer">
 <button type="button" id="updatebtn" onclick="javascript:updatePrice();" class="btn btn-primary"><s:if test="productVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div>
<script type="text/javascript">
	function updatePrice() {
		var bhId = jQuery("#bhId").val();
	   	var cooperateMode = jQuery("#cooperateMode").val();
	   	var bdBuildInRegisterPrice = 0;
	   	var cuBuildInRegisterPrice = 0;
	   	var bdUninstallRegisterPrice = 0;
	   	var cuUninstallRegisterPrice = 0;
	   	var bhBuildInRegisterPrice = 0;
	   	var bhUninstallRegisterPrice = 0;
	   	var bdDividePercent = 0;
	   	var cuDividePercent = 0;
	   	var bhDividePercent = 0;
	   	if(cooperateMode==1) {
 			bdBuildInRegisterPrice = jQuery("#bdBuildInRegisterPrice").val();
	   		cuBuildInRegisterPrice =jQuery("#cuBuildInRegisterPrice").val();
	   		bdUninstallRegisterPrice = jQuery("#bdUninstallRegisterPrice").val();
	   		cuUninstallRegisterPrice =  jQuery("#cuUninstallRegisterPrice").val();
	   		if(bhId>0) {
		   		bhUninstallRegisterPrice =  jQuery("#bhUninstallRegisterPrice").val();
		   		bhBuildInRegisterPrice =jQuery("#bhBuildInRegisterPrice").val();
	   		}
	   	} else {
   			bdDividePercent = jQuery("#bdDividePercent").val();
	   		cuDividePercent = jQuery("#cuDividePercent").val();
	   		if(bhId>0) {
		   		bhDividePercent =  jQuery("#bhDividePercent").val();
	   		}
	   	}
	   
	   
	   	var id = jQuery("#productId").val();
	   	var projectId = jQuery("#projectId").val();
	   	
	    var url ="<%=request.getContextPath()%>/Project!updatePrice.action";
	    jQuery.post(url, {"productVO.id":id,"productVO.projectId":projectId,
	    	"productVO.bdBuildInRegisterPrice":bdBuildInRegisterPrice,
	    	"productVO.cuBuildInRegisterPrice":cuBuildInRegisterPrice,
	    	"productVO.bhBuildInRegisterPrice":bhBuildInRegisterPrice,
	    	"productVO.bdUninstallRegisterPrice":bdUninstallRegisterPrice,
	    	"productVO.cuUninstallRegisterPrice":cuUninstallRegisterPrice,
	    	"productVO.bhUninstallRegisterPrice":bhUninstallRegisterPrice,
	    	"productVO.bdDividePercent":bdDividePercent,
	    	"productVO.cuDividePercent":cuDividePercent,
	    	"productVO.bhDividePercent":bhDividePercent,
	    	"productVO.cooperateMode":cooperateMode	    
	    }, function(response){
	       if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#updatebtn").attr('disabled', 'disabled');	           
	       } else {
	       		showInfoToastMiddle("修改产品价格成功");
	          	jQuery("#updatebtn").removeAttr('disabled');
	          	if(cooperateMode==1) {
	          		$("#cuInPrice_"+id).text(cuBuildInRegisterPrice);
	          		$("#cuPrice_"+id).text(cuUninstallRegisterPrice);
	          		$("#bdInPrice_"+id).text(bdBuildInRegisterPrice);
	          		$("#bdPrice_"+id).text(bdUninstallRegisterPrice);
	          		if(bhId>0) {
	          			$("#bhInPrice_"+id).text(bhBuildInRegisterPrice);
	          			$("#bhPrice_"+id).text(bhUninstallRegisterPrice);
	          		}
	          	} else if(cooperateMode==2) {
	          		$("#cuPercent_"+id).text(cuDividePercent);
	          		$("#bdPercent_"+id).text(bdDividePercent);	          		
	          		if(bhId>0) {
	          			$("#bhPercent_"+id).text(bhDividePercent);
	          		}
	          	}
	        }
	   });	   	
	}
</script>