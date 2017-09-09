<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="advVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="advVO.id==0">新建广告</s:if>
	<s:if test="advVO.id>0">修改广告</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<table class="table">
				<tr>
				<td class="table_td_title">名称</td>	
				<td>
					<input type="text" class="input-medium" id="name" name="advVO.name" value="${advVO.name}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">广告主</td>	
					<td>
						<s:select list="#Option.adverList" listKey="id" listValue="name" id="adverId" name="advVO.adverId"></s:select>
						
					</td>
				</tr>
				<tr>
				<td class="table_td_title">链接URL</td>	
				<td>
					<input type="text" class="input-medium" id="linkUrl" size="50" name="advVO.linkUrl" value="${advVO.linkUrl}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">跳转URL</td>	
				<td>
					<input type="text" class="input-medium" id="redirectUrl" size="50" name="advVO.redirectUrl" value="${advVO.redirectUrl}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">桌面图标名称</td>	
				<td>
					<input type="text" class="input-medium" id="deskIconName" name="advVO.deskIconName" value="${advVO.deskIconName}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">桌面图标URL</td>	
				<td>
					<input type="text" class="input-medium" id="deskIconUrl" name="advVO.deskIconUrl" value="${advVO.deskIconUrl}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">状态栏标题</td>	
				<td>
					<input type="text" class="input-medium" id="statusBarTitle" name="advVO.statusBarTitle" value="${advVO.statusBarTitle}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">状态栏内容</td>	
				<td>
					<input type="text" class="input-medium" id="statusBarContent" name="advVO.statusBarContent" value="${advVO.statusBarContent}" />
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">类型</td>	
					<td>
						<s:select list="#Option.advNetType" listKey="key" listValue="value" id="linkType" name="advVO.linkType"></s:select>
						
					</td>
				</tr>
				<tr>
				<td class="table_td_title">状态</td>	
				<td>
					<s:select list="#{'0':'关闭','1':'打开'}" listKey="key" listValue="value" id="status" name="advVO.status"></s:select>
					
				</td>
				</tr>
				<tr>
				<td class="table_td_title">排序值</td>	
					<td>
					<input type="text" class="input-medium" id="orders" name="advVO.orders" value="${advVO.orders}" />
						
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
	var path = '<%=path%>/adver/Adv!save.action';
	var id =  $("#cid").val();
    var name =  $("#name").val();
    var adverId =  $("#adverId").val();
    var status =  $("#status").val();
    var linkUrl =  $("#linkUrl").val();
    var redirectUrl =  $("#redirectUrl").val();
    var deskIconName =  $("#deskIconName").val();
    var deskIconUrl =  $("#deskIconUrl").val();
    var statusBarTitle =  $("#statusBarTitle").val();
    var statusBarContent =  $("#statusBarContent").val();
    var orders =  $("#orders").val();
    if(name=="") {
    	showInfoToastMiddle("请填写名称!");
    	$("#name").focus();
    	return false;
    }
    if(linkUrl=="") {
    	showInfoToastMiddle("链接URL!");
    	$("#linkUrl").focus();
    	return false;
    }
    if(redirectUrl=="") {
    	showInfoToastMiddle("跳转URL!");
    	$("#redirectUrl").focus();
    	return false;
    }
    if(deskIconName=="") {
    	showInfoToastMiddle("桌面图标名称!");
    	$("#deskIconName").focus();
    	return false;
    }
    if(deskIconUrl=="") {
    	showInfoToastMiddle("桌面图标URL!");
    	$("#deskIconUrl").focus();
    	return false;
    }
    if(statusBarTitle=="") {
    	showInfoToastMiddle("状态栏标题!");
    	$("#statusBarTitle").focus();
    	return false;
    }
    if(statusBarContent=="") {
    	showInfoToastMiddle("状态栏内容!");
    	$("#statusBarContent").focus();
    	return false;
    }
    if(orders==""||orders==0) {
    	orders= 0 ;
    }
    var linkType =  $("#linkType").val();
	$("#createBtn").attr("disabled", "disabled");

    jQuery.post(path,
    	{'advVO.id':id,
       	'advVO.status':status,
       	'advVO.adverId':adverId,
       	'advVO.linkType':linkType,
       	'advVO.linkUrl':linkUrl,
       	'advVO.redirectUrl':redirectUrl,
       	'advVO.orders':orders,
       	'advVO.deskIconName':deskIconName,
       	'advVO.deskIconUrl':deskIconUrl,
       	'advVO.statusBarTitle':statusBarTitle,
       	'advVO.statusBarContent':statusBarContent,
       	'advVO.name':name
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
