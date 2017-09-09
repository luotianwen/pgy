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
				<td class="table_td_title">NAME</td>	
				<td><s:property value="linkadsconfigVO.name" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">LINK_TYPE</td>	
				<td><s:property value="linkadsconfigVO.linkType" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">LINK_URL</td>	
				<td><s:property value="linkadsconfigVO.linkUrl" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">DESK_ICON_NAME</td>	
				<td><s:property value="linkadsconfigVO.deskIconName" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">DESK_ICON_URL</td>	
				<td><s:property value="linkadsconfigVO.deskIconUrl" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS_BAR_TITLE</td>	
				<td><s:property value="linkadsconfigVO.statusBarTitle" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS_BAR_CONTENT</td>	
				<td><s:property value="linkadsconfigVO.statusBarContent" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">CREATE_TIME</td>	
				<td><s:property value="linkadsconfigVO.createTime" /></td>
			</tr>
			<tr>	
				<td class="table_td_title">STATUS</td>	
				<td><s:property value="linkadsconfigVO.status" /></td>
			</tr>
	
	 
	 
</table>
</div>
</div>