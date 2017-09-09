<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="ddlChannelVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="ddlChannelVO.id==0">新建DDL渠道</s:if>
	<s:if test="ddlChannelVO.id>0">修改DDL渠道</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">名称</td>	
				<td>
					<input type="text" class="input-medium" id="name" name="ddlChannelVO.name" value="${ddlChannelVO.name}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">状态</td>	
				<td>
					<s:select list="#Option.enumStatus" listKey="key" listValue="value" id="status" name="ddlChannelVO.status"></s:select>
					
				</td>
				</tr>
				<tr>
				<td colspan="2">
					<input id="createBtn" type="button" class="btn btn-primary" value="确定" onclick="return updateInfo();">
				</td>
				</tr>
			</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">
function updateInfo() {
	var path = '<%=path%>/ddl/DdlChannel!save.action';
	var id =  $("#cid").val();
    var name =  $("#name").val();
    var status =  $("#status").val();
    if(name=="") {
    	showInfoToastMiddle("请填写渠道名称!");
    	$("#name").focus();
    	return false;
    }
	$("#createBtn").attr("disabled", "disabled");

    jQuery.post(path,{'ddlChannelVO.id':id,
       	'ddlChannelVO.status':status,
       	'ddlChannelVO.name':name,
       	'ddlChannelVO.version':1
       	}
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