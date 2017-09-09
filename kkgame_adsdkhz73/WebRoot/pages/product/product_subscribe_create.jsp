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
	<label class="control-label" for="productName">产品id</label>
	<div class="controls">
    <input type="text" class="input-medium" name="productVO.projectId" value="${productVO.projectId}" id="projectId"  >
   	<p id="productName_message" class="help-block"></p>
	</div>
</div>

	<div class="control-group">
		<label class="control-label" for="productName">产品名称</label>
		<div class="controls">
			<input type="text" class="input-medium" name="productVO.name" value="${productVO.name}" id="productName"  >
			<p id="productName_message" class="help-block"></p>
		</div>
	</div>

<div class="control-group">
	<label class="control-label" for="select">状态</label>
	<div class="controls">
		<s:select id="status" list="#{'1':'是','2':'否'}" name="productVO.status" listKey="key" listValue="value" headerKey="0" headerValue="状态">
        </s:select>
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

	
	function updateProduct() {
	   	var name = jQuery("#productName").val();
	   	var status = jQuery("#status").val();
	   	var id = jQuery("#productId").val();
		var projectId = jQuery("#projectId").val();

	   	if(name == ""){
		   showInfoToastMiddle("请输入产品名称");
	       return;
	    }
	    var url = "";
	    if(id>0) {
	    	url = "<%=request.getContextPath()%>/Product!subscribeUpdate.action";
	    } else {
	    	url = "<%=request.getContextPath()%>/Product!subscribeSave.action";
	    	
	    }
	    jQuery.post(url, {"productVO.id":id,"productVO.projectId":projectId,   "productVO.name":name,
	    	 "productVO.status":status
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