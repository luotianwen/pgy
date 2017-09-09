<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<s:if test="apkActusersModelVO.isPage==0">
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
                 <input type="text" name="qname" id="qname" onfocus="javascript:WdatePicker()" value="${apkActusersModelVO.cdate}" readonly="readonly"  style="width:150px;">
           		 <label class="control-label inline" for="pname">SDK类型</label>
           		 <s:select list="#{'sdk':'sdk','sink':'下沉','lead':'引导','silence':'线下','websdk':'网页链接'}" key="key" value="value" id="sdkType"></s:select>
           		 <label class="control-label inline" for="pname">活跃类型</label>
           		 <s:select list="#{'1':'活跃'}" key="key" value="value" id="type"></s:select>
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
				<table id="dataTable" class="table table-bordered table-striped table-hover">
					 	</s:if>
					<thead>
						<tr>

						<th >coo_id</th>
						<th >imei</th>
						<th >通道</th>
						<th >创建日期</th>
						<th >sdk版本</th>
						<th >销量时间</th>
						<th >插件ID</th>
						<th >机型</th>
						<th >系统版本</th>
						<th >imsi</th>
						<th >网络类型</th>
						<th >运营商</th>
						<th >国家</th>
						<th >销量coo_id</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="apkActusersModelVOList">
		    		<tr >
						<td><s:property value="cooId"/></td>
						<td><s:property value="imei"/></td>
						<td><s:property value="channelid"/></td>
						<td><s:property value="cdate"/></td>
						<td><s:property value="sdkversion"/></td>
						<td><s:property value="xdate"/></td>
						<td><s:property value="xcCooId"/></td>
						<td><s:property value="xmodel"/></td>
						<td><s:property value="xversion"/></td>
						<td><s:property value="ximsi"/></td>
						<td>
							<s:if test="xinternet==1">wifi</s:if>
							<s:if test="xinternet==0">gprs</s:if>
						</td>
						<td><s:property value="xoperator"/></td>
						<td>
							<s:property value="xcou"/>
						</td>
						<td><s:property value="scooId"/></td>

		    		</tr>
				</s:iterator>

				</tbody>
				 	<s:if test="apkActusersModelVO.isPage==0">
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
		if( name==""){
			return ;
		}
		var coo_id = $("#coo_id").val();
		var sdkversion = $("#sdkversion").val();
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");
		url = "<%=request.getContextPath()%>/detail/ActusersModel!list.action";
		jQuery.post(url,{"apkActusersModelVO.imei":id,"apkActusersModelVO.cooId":coo_id,"apkActusersModelVO.sdkversion":sdkversion,"apkActusersModelVO.cdate":name,"apkActusersModelVO.isPage":1,"apkActusersModelVO.type":type,"apkActusersModelVO.sdkType":sdkType
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
