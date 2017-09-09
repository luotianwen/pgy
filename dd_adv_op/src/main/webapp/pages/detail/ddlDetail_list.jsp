<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<s:if test="ddlDetailVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr>
				<td align="right">
				<div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">imei</label>
   				<input id="imei" type="text" class="input-medium inline"    style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <label class="control-label inline" for="pname"> 时间</label> 
                 <input type="text" id="createTime" onfocus="javascript:WdatePicker()" value="${ddlDetailVO.createTime}" readonly="readonly"   >
           		 <label class="control-label inline" for="pname">销量类型</label>
           		 <s:select list="#{'1':'sdk','2':'adjust'}" key="key" value="value" id="type"  ></s:select>
           		 <label class="control-label inline" for="pname" >项目</label>
				 <s:select list="#Option.ddlProjectList" listKey="id" listValue="name" cssStyle="width: 200px" id="projectId" name="ddlDetailVO.projectId"    headerKey="0" headerValue="--所有项目--"></s:select>
           		 <label class="control-label inline" for="pname">国家</label>
				 <s:select list="#Option.countryList" listKey="id" listValue="name" cssStyle="width: 200px" id="countryId" name="ddlDetailVO.productId"   headerKey="0" headerValue="--所有国家--"></s:select>
           		</td>
           		</tr>
           		<tr>
           		<td align="right">
         		<label class="control-label inline" for="pname">clickId</label>
   					<input id="clickId" type="text" class="input-medium inline"    style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
            	 <label class="control-label inline" for="pname">转化状态</label>
           		 <s:select list="#{'3200':'是','3201':'否'}" key="key" value="value" id="status"   headerKey="0" headerValue="--所有--"></s:select>
           		 <label class="control-label inline" for="pname" >转发状态</label>
           		 <s:select list="#{'3200':'是','3201':'否'}" key="key" value="value" id="zstatus"  headerKey="0" headerValue="--所有--"></s:select>
           		<button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询</button>
           		</td>
           		</tr></table>
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
						<s:if test="ddlDetailVO.type==1">
						<th >imei</th></s:if>
						<s:if test="ddlDetailVO.type==2">
						<th >clickId</th></s:if>
						
						<th >国家</th>
						<th >产品</th>
						<th >渠道</th>
						<th >销量转化</th>
						<th >销量转发</th>
						<th >创建日期</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="ddlDetailVOList">  
		    		<tr >
						<td><s:iterator value="#Option.ddlProjectList">
							<s:if test="id==projectId">
								<s:property value="name"/>
							</s:if>
						</s:iterator>[<s:property value="projectId"/>]</td>
						<s:if test="ddlDetailVO.type==1">
						<td><s:property value="simei"/></td></s:if>
						<s:if test="ddlDetailVO.type==2">
						<td><s:property value="clickId"/></td></s:if>
						<td><s:property value="countryName"/></td>
						<td><s:property value="productName"/></td>
						<td><s:property value="channelName"/>[<s:property value="channelId"/>]</td>
						<td><s:property value="statusName"/></td>
						<td><s:property value="zstatusName"/></td>
						<td><s:property value="createTime"/></td>
		    		</tr>
				</s:iterator>
				 <tr>
					<td colspan="8">
			 		<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="ddlDetailVO.page" operation="/detail/DdlDetail!list.action"/>
 					</div>
 					</td>
 					</tr>
				</tbody>
				 	<s:if test="ddlDetailVO.isPage==0">
				 	
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
	$("#projectId").select2();
	$("#countryId").select2();
	$("#type").select2();
	$("#status").select2();
	$("#zstatus").select2();
	function getArgus() {		
		var argus ="";
		argus += "ddlDetailVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&ddlDetailVO.page.pageSize=";
		argus += $("#pageSize").val();
		var imei = $("#imei").val();
		var projectId = $("#projectId").val();
		var countryId = $("#countryId").val();
		var type = $("#type").val();	
 		var createTime = $("#createTime").val();
 		var status = $("#status").val();
 		var zstatus = $("#zstatus").val();
 		var clickId = $("#clickId").val();
        argus += "&ddlDetailVO.simei=";
        argus += imei;
        argus += "&ddlDetailVO.type=";
        argus += type;
         argus += "&ddlDetailVO.projectId=";
        argus += projectId;
         argus += "&ddlDetailVO.countryId=";
        argus += countryId;
        argus += "&ddlDetailVO.createTime=";
        argus += createTime;
        argus += "&ddlDetailVO.status=";
        argus += status;
        argus += "&ddlDetailVO.clickId=";
        argus += clickId;
        argus += "&ddlDetailVO.zstatus=";
        argus += zstatus;
        argus += "&ddlDetailVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var imei = $("#imei").val();
		var createTime = $("#createTime").val();
		var channelId = $("#channelId").val();
		var projectId = $("#projectId").val();
		var countryId = $("#countryId").val();
		var status = $("#status").val();
 		var zstatus = $("#zstatus").val();
		var type = $("#type").val();
 		var clickId = $("#clickId").val();
		if(createTime==""){
			return ;
		}	
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/detail/DdlDetail!list.action";
		jQuery.post(url,
				{"ddlDetailVO.simei":imei,
				"ddlDetailVO.createTime":createTime,
				"ddlDetailVO.projectId":projectId,
				"ddlDetailVO.countryId":countryId,
				"ddlDetailVO.status":status,
				"ddlDetailVO.zstatus":zstatus,
				"ddlDetailVO.clickId":clickId,
				"ddlDetailVO.isPage":1,
				"ddlDetailVO.type":type
				},
		function(response){
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
