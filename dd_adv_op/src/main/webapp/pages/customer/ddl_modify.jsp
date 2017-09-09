<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>转化率修改</h2>
</div>

<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
	<div class="control-group">
		<label class="control-label" for="sdateKey">项目ID</label>
		<div class="controls">
			<s:property value="ddlVO.projectId"/>
			
		</div>
	</div>
	 
	<div class="control-group">
		<label class="control-label" for="apkidKey">转化率</label>
		<div class="controls">
			<input id="rate" type="text" class="input-medium" name="ddlVO.rate"  size="11" value="<s:property value="ddlVO.rate"/>"/>
		</div>
	</div>
	
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updateDdlVO('<%=path %>','<s:property value="ddlVO.projectId"/>');" class="btn btn-primary">确 定</button>
</div> 

<script type="text/javascript">
//修改or新增
	function updateDdlVO(path,projectId) {
		var url = path +'/customer/Ddl!update.action';
		 var rate=$("#rate").val();
		
		$("#surebtn").attr("disabled","disabled");
		jQuery.post(url, { 
			"ddlVO.rate":rate,
			"ddlVO.projectId":projectId
		 }, 
    	function(response){   
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#surebtn").attr('disabled', 'disabled');	           
	       	} else {
	       		showInfoToastMiddle(response);
	        }
	   });
	}
	
</script>