<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>

<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
		<s:if test="productVO.isPage==0">
		<script src="<%=request.getContextPath()%>/js/bootbox.min.js"></script>
		<div style="padding-bottom: 5px"></div>
   		<table id="dataTable" class="table table-bordered table-striped table-hover">
   		</s:if>
			<thead>
		      <tr>
				  <th>id</th>
				  <th>名称</th>
				  <th>状态</th>
				<role:equal type="DT,SP">	
				<th>操作</th>
				</role:equal>
		      </tr>
		    </thead>
		    <tbody>
				<s:iterator value="productVOList" status="st">
				<tr>
					<td><s:property value="projectId"/></td>
					<td><s:property value="name"/></td>
					<td>
						<s:if test="status==1">是</s:if>
						<s:elseif test="status==2">否</s:elseif>
					</td>					

					<role:equal type="DT,SP">
					<td>
						<button class="btn btn-info inline" type="button" onclick="javascript:modifyProduct('<s:property value="id"/>');">
						修改
						</button>
						<button class="btn btn-danger inline" type="button" onclick="javascript:removeProduct('<s:property value="id"/>','<s:property value="name"/>')">
						删除
						</button>
					</td>
					</role:equal>
				</tr>
			</s:iterator>
			<tr>
			<role:equal type="BD">
				<td colspan="3">
				</role:equal>
			<role:equal type="DT,SP">
				<td colspan="4">
				</role:equal>
					<div align="right">
						<page:paginationAjax formName="dataForm" property="productVO.page" operation="Product!subscribeList.action"/>
					</div>
				</td>
			</tr>
			</tbody>
		<s:if test="productVO.isPage==0">
		</table>
		
		</s:if>
	<script type="text/javascript">		
		function removeProduct(id,name) {
			bootbox.confirm("确定要删除 ["+name+"] 吗?", function(result) {
				if(result) {
					jQuery.post("Product!subscribeDelete.action",{"productVO.id":id},function(data){
						if(data == "-1"){
							showErrorToast("提示","系统出错，请重试或联系管理员");
						} else {
							showInfoToastMiddle(data);
			       			doPageBottom('turn');
						}
					});
				}
			});
		}				
		function allSelect() {
			var tmp = document.getElementsByName("productId");
			for(var i = 0; i< tmp.length; i++) {
				if(!tmp[i].checked) {
					tmp[i].checked=true;
				}
			}
		}		
		function reverseSelect() {
			var tmp = document.getElementsByName("productId");
			for(var i = 0; i< tmp.length; i++) {
				if(!tmp[i].checked) {
					tmp[i].checked=true;
				} else {
					tmp[i].checked=false;
				}
			}
		}		
		function deleteSelect() {
			var myForm  = document.getElementById("productForm");
			var tmp = document.getElementsByName("productId");
			var ids = "";
			for(var i = 0; i< tmp.length; i++) {
				if(tmp[i].checked) {
					var id = tmp[i].value;
					ids += tmp[i].value +",";
				}
			}
			if(ids.length>0) {
				ids = ids.substr(0,ids.length-1);
			}
			if(ids == "") {
				alert('请为选择要删除的产品');
				return false;	
			}
			if(!sure('确定要删除['+ids+']吗?')) {
				return;
			}
			jQuery.post("Product!subscribeDelete.action",{"productVO.ids":ids},function(data){
				if(data == "1"){					
					for(var i = tmp.length-1; i>=0 ; i--) {
						if(tmp[i].checked) {
							$('#bug_'+tmp[i].value).remove();
						}
					}
					$('#mes').html('<font color=\'red\'>产品['+ids+']删除成功</font>')
					return ;
				} else {
					$('#mes').html('<font color=\'red\'>删除产品出错，请重试或联系管理员!</font>');
					return ;
				}
			});			
		}
	</script>