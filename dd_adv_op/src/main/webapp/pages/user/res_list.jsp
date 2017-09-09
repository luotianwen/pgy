<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/pages/user/js/res.js?version=1.0"></script>
<div class="row-fluid" style="min-height: 40px;margin-top: 30px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset> 
					<input type="button" class="btn btn-success" onclick="javascript:createRes('<%=path%>/user','${resVO.parentId }');" value="新增资源">
					<input type="button" class="btn btn-primary" onclick="javascript:goBack('<%=path%>/user','${resVO.ppId }');" value="返  回">
					<div id="ajax-modal" class="modal fade" tabindex="-1"></div>	   				   			
			</fieldset>
   		</div>
   	</div>
</div>
<div class="box box-primary" style="margin-top: 15px">
  	<div class="span12">
  	<form class="form-horizontal" id="dataForm">
  		<fieldset>
	   		<div id="dataList">
 			
				<table id="dataTable" class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
						<th>资源名称</th>				
						<th>资源描述</th>
						<th>上级名称</th>				
						<th>操作</th>
					    </tr>
					</thead>
					<tbody>
						<s:iterator value="resVOList">
						<tr id="res_<s:property value="id"/>">
							<td class="td_detail" width="20%">
								<a href="javascript:goBack('<%=path%>/user','${id }');"><s:property value="resName"/></a>
							</td>
							<td class="td_detail" width="20%"><s:property value="resDesc"/></td>
							<td class="td_detail" width="20%"><s:property value="resVO.resDesc"/></td>
							<td class="td_detail" align="center" nowrap="nowrap">
								<a class="btn btn-warning" href="javascript: modifyRes('<%=path%>/user','<s:property value="id"/>');" title="修改">
									修改
								</a>
								&nbsp;&nbsp;|&nbsp;&nbsp;					
								<a class="btn btn-danger" href="javascript: deleteRes('<%=path%>/user','<s:property value="id"/>','<s:property value="parentId"/>')" title="删除" >
									删除
								</a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div style='text-align:center;'>
			<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
   			</div>
  		</fieldset>
  	</form>
	</div>
</div>