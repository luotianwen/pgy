<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/pages/user/js/role.js?verison=1.1"></script>

<div class="row-fluid" style="min-height: 40px;margin-top: 30px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset> 
   				<input class="btn btn-success" type="button" onclick="createRole('<%=path %>/user')" value="新增">
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
						<th>角色名称</th>				
						<th>角色描述</th>	
						<th>操作</th>
					    </tr>
					</thead>
					<s:iterator value="roleVOList">
						<tr id="role_<s:property value="id"/>">
						<td class="td_detail" width="30%"><s:property value="roleName"/></td>
						<td class="td_detail" width="30%"><s:property value="roleDesc"/></td>
						<td class="td_detail" align="center" nowrap="nowrap">
						<a class="btn btn-info" href="javascript: grantRes('<%=path %>/user','<s:property value="id"/>')" title="授权资源">
							授权资源
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-warning" href="javascript: modifyRole('<%=path %>/user','<s:property value="id"/>')" title="修改">
							修改
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;					
						<a class="btn btn-danger" href="javascript: deleteRole('<%=path%>/user','<s:property value="id"/>')" title="删除" >
							删除
						</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div style='text-align:center;'>
			<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
   			</div>
  		</fieldset>
  	</form>
	</div>
</div>