<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript" src="<%=path%>/js/util.js"></script>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>授权用户</h2>
</div>
<s:hidden id="puid" name="userVO.id"></s:hidden>

<div class="modal-body">
<form class="form-horizontal">
<fieldset>
<table>
	<tr>
	<td>
		<span>授权用户:&nbsp;(&nbsp;<font color='red'>${userVO.realName},${roleIds}</font>&nbsp;)</span></td>
	</tr>		
	<tr >
	 <td >
			<s:iterator value="roleVOList" status="stat">
	 			<label class="checkbox inline">
				<input type="checkbox" name="" id="role_<s:property value="id"/>" value="<s:property value="id"/>" onclick="javascript:setPermission('<%=path%>', '<s:property value="id"/>', this)"/>
				<s:property value="roleDesc"/>
	 			</label>
				<s:if test="(#stat.index+1)%4==0"><br/></s:if>	 			
			</s:iterator>				
		</td>           
       </tr>
</table>
</fieldset>
</form>
</div>
<div class="modal-footer">
</div>
<script type="text/javascript">
		var ids = '${roleIds}';
    	
		var data = ids.toString().split(",");
		for(var i = 0; i < data.length; i = i + 1){
			$("#role_" + data[i]).attr("checked", true);
		}
</script>