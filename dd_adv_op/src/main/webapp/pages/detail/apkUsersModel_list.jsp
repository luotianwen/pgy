<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="apkUsersModelVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>
		   		<table style="width:99%"><tr><td>
				<td align="right"><div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">imei</label>
   				<input id="qid" type="text" class="input-medium inline"    style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label class="control-label inline" for="pname">coo_id</label>
					<input id="coo_id" type="text" class="input-medium inline"    style="width: 200px">&nbsp;&nbsp;
					<label class="control-label inline" for="pname">版本</label>
					<input id="sdkversion" type="text" class="input-medium inline"    style="width: 200px">&nbsp;&nbsp;
                 <label class="control-label inline" for="pname"> 时间</label>
                <input type="text" name="qname" id="qname" onfocus="javascript:WdatePicker()" value="${apkUsersModelVO.cdate}" readonly="readonly"   style="width:150px;">
           		 <label class="control-label inline" for="pname">SDK类型</label>
           		 <s:select list="#{'sdk':'sdk','sink':'下沉','lead':'引导','silence':'线下','websdk':'网页链接'}" key="key" value="value" id="sdkType"></s:select>
           		 <label class="control-label inline" for="pname">销量类型</label>
           		 <s:select list="#{'1':'日用户','2':'总用户','3':'项目用户'}" key="key" value="value" id="type"></s:select>
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
						<th >通道</th>
						<th >创建日期</th>
						<th >插件ID</th>
						<th >机型</th>
						<th >系统版本</th>
						<th >imsi</th>
						<th >网络类型</th>
						<th >运营商</th>
						<th >国家</th>
						<th >sdk版本</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="apkUsersModelVOList">
		    		<tr >
						<td><s:property value="cooId"/></td>
						<td><s:property value="imei"/></td>
						<td><s:property value="channelid"/></td>
						<td><s:property value="cdate"/></td>
						<td><s:property value="xcCooId"/></td>
						<td><s:property value="xmodel"/></td>
						<td><s:property value="xversion"/></td>
						<td><s:property value="ximsi"/></td>
						<td>
							<s:if test="xinternet==1">wifi</s:if>
							<s:if test="xinternet==0">gprs</s:if>
						</td>
						<td><s:property value="xoperator"/></td>
						<td><s:property value="xcou"/></td>
						<td><s:property value="sdkversion"/></td>
		    	 
		    		</tr>
				</s:iterator>
				 
				</tbody>
				 	<s:if test="apkUsersModelVO.isPage==0">
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
		var type = $("#type").val();
		var sdkType = $("#sdkType").val();
		if(name==""){
			return ;
		}
		var coo_id = $("#coo_id").val();
		var sdkversion = $("#sdkversion").val();
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		url = "<%=request.getContextPath()%>/detail/UsersModel!list.action";
		jQuery.post(url,{"apkUsersModelVO.imei":id,"apkUsersModelVO.cooId":coo_id,"apkUsersModelVO.sdkversion":sdkversion,"apkUsersModelVO.cdate":name,"apkUsersModelVO.isPage":1,"apkUsersModelVO.type":type,"apkUsersModelVO.sdkType":sdkType
		},function(response){
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
