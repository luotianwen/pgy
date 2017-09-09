<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/util.js"></script>
<script type="text/javascript" src="<%=path%>/pages/user/js/role.js?version=1.1"></script>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2>授权资源</h2>
</div>
<s:hidden id="roleid" name="roleVO.id"></s:hidden>
<div class="modal-body">
<form class="form-horizontal">
<fieldset>		
		<table>
			<tr>
			<td>
			<span id="lblmanagetitle">授权资源:&nbsp;(&nbsp;<font color='red'>${roleVO.roleDesc}</font>&nbsp;)</span></td>
			</tr>
		<tr >
			 <td>
					<s:iterator value="resVOList" status="stat">
						<label class="checkbox inline">
						<input type="checkbox" name="" id="res_<s:property value="id"/>" value="<s:property value="id"/>" onclick="javascript:setRes('<%=path%>', '<s:property value="id"/>', this, 1)"/>
						<b><s:property value="resDesc"/></b>
						</label><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
							<s:iterator value="childList" status="c">
								<label class="checkbox inline">
								<input type="checkbox" name="" id="res_<s:property value="id"/>" value="<s:property value="id"/>" onclick="javascript:setRes('<%=path%>', '<s:property value="id"/>', this, 2)"
									myattr="my_<s:property value="resVOList[#stat.index].id"/>"/><s:property value="resDesc"/>
								</label>
								<s:if test="(#c.index+1)%4==0"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:if>
							</s:iterator>
						<br/>	
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
		var ids = '${resIds}';
		var data = ids.toString().split(",");
		for(var i = 0; i < data.length; i = i + 1){
			$("#res_" + data[i]).attr("checked", true);
		}
</script>