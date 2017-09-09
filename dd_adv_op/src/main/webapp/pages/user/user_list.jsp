<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:if test="userVO.isPage==0">
<script type="text/javascript" src="<%=path%>/pages/user/js/user.js?version=1.3"></script>	
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr><td>
		   		<input class="btn btn-success" type="button" onclick="createUser('<%=path %>/user')" value="新增"></td>
				<td align="right"><div id="ajax-modal" class="modal fade" tabindex="-1"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<label class="control-label inline" for="pname">名称</label>
   				<input id="pname" type="text" class="input-medium inline" name="userVO.loginId" value="<s:property value="userVO.loginId"/>" style="width: 100px">
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		<button type="reset" class="btn btn-primary">清 空</button></td></tr></table>
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
					</s:if>
					<thead>
						<tr>
						<th>ID</th>				
						<th>登录账号</th>				
						<th>状态</th>				
						<th>最后登录时间</th>
						<th>操作</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="userVOList">  
		    		<tr >
		    		<td><s:property value="id"/></td>
		    		<td><s:property value="loginId"/></td>
		    		<td>		    		
		    			<s:if test="status==0">正常</s:if>
						<s:if test="status==1">锁定</s:if>
					</td>
		    		<td><s:property value="lastLogin"/></td>
		    		<td>
		    			<a class="btn btn-warning" href="javascript: oper('<%=path%>/user','<s:property value="id"/>',<s:if test="status==0">1</s:if><s:if test="status==1">0</s:if>)" title="<s:if test="status==0">锁定</s:if><s:if test="status==1">解锁</s:if>" >
						<s:if test="status==0">锁定</s:if>
						<s:if test="status==1">解锁</s:if>
						</a>   			 	
						&nbsp;&nbsp;|&nbsp;&nbsp;				
						<a class="btn btn-primary" href="javascript: modifyUser('<%=path%>/user','<s:property value="id"/>')" title="修改">
						修改
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;				
						<a class="btn btn-danger" href="javascript: deleteUser('<%=path%>/user','<s:property value="id"/>','<s:property value="realName"/>')" title="删除" >
						删除
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<a class="btn btn-info" href="javascript: grantRole('<%=path%>/user','<s:property value="id"/>')">分配角色
						</a>
		    		</td>
		    		</tr>
				</s:iterator>
				<tr>
				<td colspan="5">
					<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="userForm" property="userVO.page" operation="/user/User!list.action"/>
 					</div>					
				</td>
				</tr>
				</tbody>
				<s:if test="userVO.isPage==0">
				</table>
   			</div>
   			<div style='text-align:center;'>
			<img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
   			</div>
  		</fieldset>
  	</form>
	</div>
</div>
</s:if>



