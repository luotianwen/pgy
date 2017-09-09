<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="guideInfoVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="guideInfoVO.id==0">新建引导配置</s:if>
	<s:if test="guideInfoVO.id>0">修改引导配置</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">引导次数</td>	
				<td>
					<input type="text" class="input-medium" id="ydcs" name="guideInfoVO.ydcs" value="${guideInfoVO.ydcs}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">引导间隔</td>	
				<td>
					<input type="text" class="input-medium" id="ydjg" name="guideInfoVO.ydjg" value="${guideInfoVO.ydjg}"  onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
					
				</td>
				</tr>
				<tr>
				<td colspan="2">
					<input id="createBtn" type="button" class="btn btn-primary" value="确 定" onclick="return updateInfo();">
				</td>
				</tr>
			</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">
function updateInfo() {
	var path = '<%=path%>/sdkinfo/GuideInfo!save.action';
	var id =  $("#cid").val();
    var ydcs =  $("#ydcs").val();
    var ydjg =  $("#ydjg").val();
	$("#createBtn").attr("disabled", "disabled");

    jQuery.post(path,{'guideInfoVO.id':id,
       	'guideInfoVO.ydcs':ydcs,
       	'guideInfoVO.ydjg':ydjg,
       	'guideInfoVO.creator':1,
       	'guideInfoVO.version':1}
       	,function(response){
			if(response=="-1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				$("#load").css({"display":"none"});
				showInfoToastMiddle(response);
	           	doPageBottom('turn');
			}
		});
}
</script>
