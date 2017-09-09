<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>

<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">项目详情</li>
   		</ul>
   	</div>
</div>
<s:form   theme="simple" >

	
	<table class="table">		
		<tr>
			<td class="table_td_title">项目名称-ID</td>			 
            <td>
            	<s:property value="projectVO.name"/>&nbsp;&nbsp;-&nbsp;&nbsp;P.[<font color="red"><s:property value="projectVO.id"/></font>]
            </td>
            <td class="table_td_title">渠道商名称</td>			 
            <td><s:property value="projectVO.schemeName"/></td>
		</tr>	
		<tr >
			<td class="table_td_title">所属代理商</td>	
			<td width="35%"  nowrap="nowrap" >
				<s:iterator value="#Policy.agentList">
					<s:if test="id==projectVO.agentId">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">客户</td>	
			<td width="35%"  nowrap="nowrap">
				<s:iterator value="#Policy.customerList">
					<s:if test="projectVO.customerId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>		
		</tr>
		<tr ><td class="table_td_title">商务拓展人员</td>	
			<td width="35%"  nowrap="nowrap" >
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td><td class="table_td_title">商务协助人员</td>	
			<td width="35%"  nowrap="nowrap" >
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>			
		<tr>
			<td class="table_td_title">单价</td>
            <td>
            	<s:property value="projectVO.price"/>
            </td>
            <td class="table_td_title">产品名称</td>
            <td><s:property value="projectVO.productIds"/></td>
		</tr>	
		<tr >
			<td class="table_td_title">创建时间</td>			 
            <td   colspan="3">
            	<s:property value="projectVO.createTime"/>
			</td>
		</tr>		
		
		<tr >
			<td class="table_td_title">备注</td>			 
            <td   colspan="3"><textarea cols="50" rows="5" readonly><s:property value="projectVO.intro"/></textarea></td>
		</tr>
		<tr >
			<td class="table_td_title">项目状态</td>			 
            <td   colspan="3">
            	<s:if test="projectVO.status==1">是</s:if>
				<s:if test="projectVO.status==2">否</s:if>

            </td>
		</tr>

	</table>

	<table class="table">
		<tr>
			<td>
				<input id="btn2" class="btn btn-primary" type="button" value=" 返 回 " onclick="doBack()"/>&nbsp;&nbsp;
			</td>
		</tr>
	</table>


</s:form>
<script>
function doBack(queryType) {
var url = "Project!subscribeQuery.action";
jQuery.post(url,{},function(data) {
$("#containerData").empty().html(data);
});
}
</script>