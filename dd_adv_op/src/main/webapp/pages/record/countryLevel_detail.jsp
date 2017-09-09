<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"
	language="java"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
<!--
.table_td_title {
	
}
-->
</style>
<div class="widget-box">
	<div class="widget-title">
		<span class="icon">
			<i class="icon-th"></i>
		</span>
		<h5>配置详情</h5>
	</div>
<div class="widget-content nopadding" style="padding-top: 10px!important">
<table class="table table-bordered table-striped">
			 <tr>	
				<td class="table_td_title">产品</td>	
				<td><s:property value="countryLevelVO.productname" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">国家</td>	
				<td><s:property value="countryLevelVO.countryname" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">级别</td>	
				<td>
				       <s:if test="countryLevelVO.level==1">高</s:if>
			      		<s:if test="countryLevelVO.level==2">中</s:if>
			      		<s:if test="countryLevelVO.level==3">底</s:if>
				 </td>
			</tr>
	
	 
	 
</table>
</div>
</div>