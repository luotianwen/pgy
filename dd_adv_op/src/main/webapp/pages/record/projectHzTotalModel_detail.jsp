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
		<h5>资费组合配置详情</h5>
	</div>
<div class="widget-content nopadding" style="padding-top: 10px!important">
<table class="table table-bordered table-striped">
			<tr>	
				<td class="table_td_title">SDATE</td>	
				<td><s:property value="projectHzTotalModelVO.sdate" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">PROJECT_ID</td>	
				<td><s:property value="projectHzTotalModelVO.projectId" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">SDKTYPE</td>	
				<td><s:property value="projectHzTotalModelVO.sdktype" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">NEWUSERS</td>	
				<td><s:property value="projectHzTotalModelVO.newusers" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">ACTUSERS</td>	
				<td><s:property value="projectHzTotalModelVO.actusers" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">INSTALL_HIGH</td>	
				<td><s:property value="projectHzTotalModelVO.installHigh" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">INSTALL_MID</td>	
				<td><s:property value="projectHzTotalModelVO.installMid" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">INSTALL_LOW</td>	
				<td><s:property value="projectHzTotalModelVO.installLow" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">INSTALL_NONE</td>	
				<td><s:property value="projectHzTotalModelVO.installNone" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">SHOWTOTAL</td>	
				<td><s:property value="projectHzTotalModelVO.showtotal" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">INCOME</td>	
				<td><s:property value="projectHzTotalModelVO.income" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">THIRDINCOME</td>	
				<td><s:property value="projectHzTotalModelVO.thirdincome" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">HIGHUSERS</td>	
				<td><s:property value="projectHzTotalModelVO.highusers" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">MIDUSERS</td>	
				<td><s:property value="projectHzTotalModelVO.midusers" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">LOWUSERS</td>	
				<td><s:property value="projectHzTotalModelVO.lowusers" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">OUTCOME</td>	
				<td><s:property value="projectHzTotalModelVO.outcome" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS</td>	
				<td><s:property value="projectHzTotalModelVO.status" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">CREATOR</td>	
				<td><s:property value="projectHzTotalModelVO.creator" /></td>
			</tr>
	
	 
	 
</table>
</div>
</div>