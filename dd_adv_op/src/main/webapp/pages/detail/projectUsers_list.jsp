<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="sdkUsersModelVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:99%"><tr><td>
				<td align="right"><div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">coo_id</label>
   				<input id="qid" type="text" class="input-medium inline"    style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <label class="control-label inline" for="pname"> 时间</label> 
                 <input type="text" name="qname" id="qname"  onfocus="javascript:WdatePicker()" value="${sdkUsersModelVO.cdate}" readonly="readonly"   style="width:150px;">
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
						<th >coo_id</th>
						<th >imei</th>
						<th >项目ID</th>
						<th >项目日期</th>
						<th >sdk版本</th>
					 
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="sdkUsersModelVOList">  
		    		<tr >
						<td><s:property value="cooId"/></td>
						<td><s:property value="imei"/></td>
						<td><s:property value="xcCooId"/></td>
						<td><s:property value="cdate"/></td>
						<td><s:property value="sdkversion"/></td>

		    		</tr>
				</s:iterator>

				</tbody>
				 	<s:if test="sdkUsersModelVO.isPage==0">
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
		var id = $("#qid").val();
		var name = $("#qname").val();
		if(name==""){
			return ;
		}
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");
		url = "<%=request.getContextPath()%>/detail/SdkUsersModel!projectlist.action";
		jQuery.post(url,{"sdkUsersModelVO.imei":id,"sdkUsersModelVO.cdate":name,"sdkUsersModelVO.isPage":1},function(response){
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
