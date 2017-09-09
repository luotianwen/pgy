<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:hidden id="cid" name="adverVO.id"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>
	<s:if test="adverVO.id==0">新建广告主</s:if>
	<s:if test="adverVO.id>0">修改广告主</s:if>
	</h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
		<table class="table">
			<tr>
				<td class="table_td_title">名称</td>
				<td>
					<input type="text" class="input-medium" id="name" name="adverVO.name" value="${adverVO.name}"/>

				</td>
				<td class="table_td_title">类型</td>
				<td>
					<s:select list="#Option.adverType" listKey="key" listValue="value" id="type"
							  name="adverVO.type"></s:select>
				</td>
			</tr>
			<tr>
				<td class="table_td_title">状态</td>
				<td>
					<s:select list="#{'0':'关闭','1':'打开'}" listKey="key" listValue="value" id="status"
							  name="adverVO.status"></s:select>
				</td>
			</tr>
			<tr>
				<td class="table_td_title">联系人</td>
				<td>
					<input type="text" class="input-medium" id="linkmanName" name="adverVO.linkmanName" value="${adverVO.linkmanName}"/>
				</td>
				<td class="table_td_title">电话</td>
				<td>
					<input type="text" class="input-medium" id="linkmanPhone" name="adverVO.linkmanPhone" value="${adverVO.linkmanPhone}"/>
				</td>
			</tr>
			<tr>
				<td class="table_td_title">QQ</td>
				<td>
					<input type="text" class="input-medium" id="linkmanQQ" name="adverVO.linkmanQQ" value="${adverVO.linkmanQQ}"/>
				</td>

			</tr>
			<tr>
				<td class="table_td_title">地址</td>
				<td colspan="3">
					<input type="text" class="input-medium" size="56" id="linkmanAddress" name="adverVO.linkmanAddress" value="${adverVO.linkmanAddress}"/>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input id="createBtn" type="button" class="btn btn-primary" value="确 定"
						   onclick="return updateInfo();">
				</td>
			</tr>
		</table>
	</form>
</div>
<div class="modal-footer">
</div> 
<script type="text/javascript">
function updateInfo() {
	var path = '<%=path%>/adver/Adver!save.action';
	var id =  $("#cid").val();
    var name =  $("#name").val();
    var status =  $("#status").val();
	var linkmanName = $("#linkmanName").val();
	var linkmanPhone = $("#linkmanPhone").val();
	var linkmanQQ = $("#linkmanQQ").val();
	var linkmanAddress = $("#linkmanAddress").val();
    if(name=="") {
    	showInfoToastMiddle("请填写名称!");
    	$("#name").focus();
    	return false;
    }
    var type =  $("#type").val();
	$("#createBtn").attr("disabled", "disabled");

    jQuery.post(path,
    	{
			'adverVO.id':id,
			'adverVO.status':status,
			'adverVO.type':type,
			'adverVO.name':name,
			'adverVO.linkmanName':linkmanName,
			'adverVO.linkmanPhone':linkmanPhone,
			'adverVO.linkmanQQ':linkmanQQ,
			'adverVO.linkmanAddress':linkmanAddress
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