<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="ddlVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
	<div class="span12">
		<div class="form-inline">
			<fieldset>
				<table style="width:99%"><tr><td>
					<td align="right"><div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
						<label class="control-label inline" for="pname">coo_id</label>
						<input id="qid" type="text" class="input-medium inline"    style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="control-label inline" for="pname"> 时间</label>
						<input type="text" name="qname" id="qname"  onfocus="javascript:WdatePicker()" value="${ddlVO.createTime}" readonly="readonly"   style="width:150px;">
						<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
					</td></tr></table>
			</fieldset>
		</div>
	</div>
</div>
<div class="box box-primary" style="margin-top: 15px">
	<div class="span12">
		<form class="form-horizontal" id="dataForm">
			<fieldset>
				<div id="dataList">
					<table id="dataTable" style="width:95%" class="table table-bordered table-striped table-hover">
</s:if>
						<thead>
						<tr>
							<th>coo_id</th>
							<th>IP地址</th>
							<th>重复数</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="ddlVOList">
							<tr>
								<td><s:property value="projectId"/></td>
								<td><s:property value="projectName"/></td>
								<td><s:property value="clickCount"/></td>
							</tr>
						</s:iterator>
						</tbody>
<s:if test="ddlVO.isPage==0">
					</table>
				</div>
				<div style='text-align:center;'>
					<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">
	function searchList() {
		var pId= $("#qid").val();
		var createTime = $("#qname").val();
		if(createTime=="" || pId == "" || isNaN(pId)){
			return ;
		}
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");
		url = "<%=request.getContextPath()%>/customer/Ddl!listClick.action";
		jQuery.post(url,{"ddlVO.projectId":pId,"ddlVO.createTime":createTime,"ddlVO.isPage":1},function(response){
			if(response=="-1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				$("#load").css({"display":"none"});
			} else {
				jQuery("#dataTable").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}
		});
	}
</script>
</s:if>