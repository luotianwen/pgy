<%@ page   isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.kkgame.hz.entities.*" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<style>
<!--

.table {
  width: 100%;
  margin-bottom: 20px;
}

.table th,
.table td {
  padding: 8px;
  line-height: 20px;
  text-align: left;
  vertical-align: top;
  border-top: 1px solid #dddddd;
}

.table th {
  font-weight: bold;
}

.table thead th {
  vertical-align: bottom;
}

.table caption + thead tr:first-child th,
.table caption + thead tr:first-child td,
.table colgroup + thead tr:first-child th,
.table colgroup + thead tr:first-child td,
.table thead:first-child tr:first-child th,
.table thead:first-child tr:first-child td {
  border-top: 0;
}

.table tbody + tbody {
  border-top: 2px solid #dddddd;
}

.table .table {
  background-color: #ffffff;
}
-->
</style>
<s:form id="customerForm" action="" onsubmit="return VerifyData(this);" theme="simple" >
<table class="table">
<tr>		
	<td>客户名称</td>
	<td>商务拓展人员</td>
	<td>状态</td>
	<td>城市</td>
</tr>
<s:if test="customerList.size> 0"> 
		<s:iterator value="customerList" status="stat"  >
			<tr>
				<td >
				${name}
				</td>			
				<td>${businessDeveloperName}</td>	
				<td>${statusDisplay}</td>							
				<td>${city}</td>	
			</tr>
		</s:iterator>
</s:if>
<s:else>
<tr>		
 <td  colspan="4" >无查询记录</td>
</tr>
</s:else>
</table>
</s:form>
