<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />

<s:if test="adLinkDetailVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
   	<div class="span12">
   		<div class="form-inline">
   			<fieldset>   				
		   		<table style="width:100%"><tr>
				<td align="right">
				<div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
            	<label class="control-label inline" for="pname">imei</label>
   				<input id="imei" type="text" class="input-medium inline"    style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <label class="control-label inline" for="pname">coo_id</label>
					<input id="coo_id" type="text" class="input-medium inline"    style="width: 100px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 <label class="control-label inline" for="pname"> 时间</label> 
                 <input type="text" id="cdate" onfocus="javascript:WdatePicker()" value="${adLinkDetailVO.cdate}" readonly="readonly"  style="width:100px;">
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
						<th >项目ID</th>
						<th >imei</th>
						<th >广告ID</th>
						<th >广告类型</th>
						<th >点击类型</th>
						<th >创建时间</th>
					    </tr>
					</thead>
				<tbody>
		    	<s:iterator value="adLinkDetailVOList">  
		    		<tr >
						<td><s:property value="coo_id"/></td>
						<td><s:property value="imei"/></td>
						<td><s:property value="adId"/></td>
						<td>
							<s:if test="linkType==1">wifi</s:if>
							<s:if test="linkType==0">gprs</s:if>
						</td>
						<td>
							<s:if test="clickType==1">定时</s:if>
							<s:if test="clickType==2">桌面</s:if>
							<s:if test="clickType==3">push</s:if>
							<s:if test="clickType==4">浏览器</s:if>
						</td>
						<td><s:property value="cdate"/></td>
		    		</tr>
				</s:iterator>
				 <tr>
					<td colspan="6">
			 		<div class="pagelist" id="pagelist1" align="right">
						<page:paginationAjax formName="productForm" property="adLinkDetailVO.page" operation="/detail/AdLinkDetail!list.action"/>
 					</div>
 					</td>
 					</tr>
				</tbody>
				 	<s:if test="adLinkDetailVO.isPage==0">
				 	
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
	 
	function getArgus() {		
		var argus ="";
		argus += "adLinkDetailVO.page.pageNum=";
		argus += $("#pageNum").val();
		argus += "&adLinkDetailVO.page.pageSize=";
		argus += $("#pageSize").val();
		var imei = $("#imei").val();
		var cdate = $("#cdate").val();
		var coo_id = $("#coo_id").val();

        argus += "&adLinkDetailVO.imei=";
        argus += imei;
		argus += "&adLinkDetailVO.coo_id=";
		argus += coo_id;

		argus += "&adLinkDetailVO.cdate=";
        argus += cdate;
        argus += "&adLinkDetailVO.isPage=1";
		return argus;
	}
	 
	function searchList() {
		var imei = $("#imei").val();
		var cdate = $("#cdate").val();
		if(cdate==""){
			return ;
		}
		var coo_id = $("#coo_id").val();
		$("#load").css({"display":""});
		jQuery("#dataTable").html("");  	
		var url = "<%=request.getContextPath()%>/detail/AdLinkDetail!list.action";
		jQuery.post(url,
				{"adLinkDetailVO.imei":imei,
				"adLinkDetailVO.cdate":cdate,
				"adLinkDetailVO.coo_id":coo_id,
				"adLinkDetailVO.isPage":1
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
