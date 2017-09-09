<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.kkgame.hz.entities.PortalUserVO" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>

<%
	PortalUserVO userVO = (PortalUserVO)session.getAttribute("SESSION_PORTALUSER");
%>
		<div class="row">
    	<div class="span12">
    		<ul class="breadcrumb">
        	<li>当前位置 <span class="divider">/</span></li>
        	<li>合作收益<span class="divider">/</span></li>
        	<li class="active">用户按<s:if test="queryType==1">日</s:if><s:if test="queryType==2">月</s:if>分成数据查询</li>
      		</ul>
      		<form class="form-horizontal well">
      		<fieldset>
      		<table class="querytable" >
      			<tbody>
			      <tr>
			        <td>请选择时间<font color="red">*</font></td>			        
			        <td>
			        	<s:if test="billSearchVO.queryType==1">
			        	<input type="text" name="billSearchVO.startTime" id="start" onfocus="javascript:WdatePicker()" readonly="readonly" value="${billSearchVO.startTime}" style="width:80px;">
						-
						<input type="text" name="billSearchVO.endTime" id="end" onfocus="javascript:WdatePicker()" readonly="readonly" value="${billSearchVO.endTime}" style="width:80px;">&nbsp;		
			        	</s:if>
			        	<s:if test="billSearchVO.queryType==2">
		        		<select id="searchMonth" name="billSearchVO.searchMonth" style="WIDTH: 215px" >
			    			<calendar:month from="201306" />
						</select>
			        	</s:if>
			        </td>			        
			      </tr>
			      <role:equal type="SP,MG">
				<tr>
					<td>代理商</td>
					<td>
						<select id="agentId" onchange="selBd(this.value);" name="billSearchVO.agentId" style="WIDTH: 215px">
   							<s:action name="getAllAgent" namespace="" executeResult="true">
   							</s:action>
   						</select>
					&nbsp;&nbsp;商务
						<select id="bdId" onchange="selCustomer(this.value);"  id="bdId" name="billSearchVO.bdId" style="WIDTH: 215px">
   							<option value="0">--所有商务--</option>
   						</select>
					</td>
				</tr>
				</role:equal>
				<role:equal type="AG">
				<tr>
					<td>商务</td>
					<td  class="td_detail">
					<select id="bdId" onchange="selCustomer(this.value);" name="billSearchVO.bdId" style="WIDTH: 215px">
  							<s:action name="getAllBd" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
				</role:equal>
				<role:equal type="SP,MG,AG,BD">
				<tr>
					<td>客户</td>
					<td>
						<select id="customerId" onchange="selProject(this.value);" name="billSearchVO.customerId" style="WIDTH: 215px">
   							<option value="0">--所有客户--</option>
   						</select>
					&nbsp;&nbsp;项目
						<select id="projectId" name="billSearchVO.projectId" style="WIDTH: 215px">
   							<option value="0">--所有项目--</option>
   						</select>
					</td>
				</tr>
				<tr>
					<td>产品</td>
					<td>
					<select id="productId" name="billSearchVO.productId" style="WIDTH: 215px">
  							<s:action name="getAllProduct" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
				</role:equal>
				<role:equal type="CM">
				<tr>
					<td>项目</td>
					<td>
					<select id="projectId" name="billSearchVO.projectId" style="WIDTH: 215px">
  							<s:action name="getAllProject" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
  				<tr>
					<td>产品</td>
					<td>
					<select id="productId" name="billSearchVO.productId" style="WIDTH: 215px">
  							<s:action name="getAllProduct" namespace="" executeResult="true">
  							</s:action>
  					</select>
  					</td>
  				</tr>
				</role:equal>				
				<tr>	
					<td>统计显示列<font color="red">*</font></td>
					<td>
						<label class="checkbox inline">
						<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_DATE" />" checked="checked"/>时间
						</label>
						<role:equal type="SP,MG,BH">
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_AGENT" />"/>代理商
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_BD" />"/>商务
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
							</label>
							
						</role:equal>
						<role:equal type="AG">
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_BD" />"/>商务
							</label>
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
							</label>
						</role:equal>
						<role:equal type="BD">
							<label class="checkbox inline">
							<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
							</label>
						</role:equal>
						<label class="checkbox inline">
						<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PROJECT" />"/>项目
						</label>
						<label class="checkbox inline">
						<input type="checkbox" name="billSearchVO.rowFields" value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PRODUCT" />"/>产品
						</label>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="form-actions">
						<input id="btn" class="btn btn-primary" type="button" value=" 查 询 " onclick="searchList();"/>&nbsp;&nbsp;
   						<button type="reset" class="btn">清 空</button>
   						<role:equal type="SP">
						<input id="btn" class="btn btn-warning" type="button" value=" 上传数据 " onclick="showUpload();"/>&nbsp;&nbsp;
						</role:equal>
					</td>
				</tr>
			    </tbody>
      		</table>  			
      		</fieldset>
      		</form>
    	</div>
  		</div>
		<div style="padding-bottom: 5px"></div>
		<img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
		<div id="data">		
		</div>
		<div id="uploadDiv" style="display:none;">
			<table class="table">
				<tr>
				<td class="table_td_title">数据</td>	
				<td>
					<input class="btn" type="file" id="file" name="file" />
					<img src="<%=request.getContextPath()%>/images/loading.gif" id="loading" style="display: none;">
				</td>
				</tr>
				<tr>
				<td colspan="2">
					<input id="uploadBtn" type="button" class="btn btn-primary" value="上传" onclick="return ajaxFileUpload();">
				</td>
				</tr>
			</table>
			</div>
<script type="text/javascript">
	function showUpload() {
		$("#data").hide();
		$("#uploadDiv").show();
	}

	function searchList() {
		$("#uploadDiv").hide();
		$("#data").show();
		var url="<%=request.getContextPath()%>/UserDivide!listMonth.action";
		var queryType = <s:property value="billSearchVO.queryType"/>;
	  	if(queryType == 1) {
	  		var url="<%=request.getContextPath()%>/UserDivide!listDay.action";
	  		if($("#start").val() == "") {
				alert("请选择开始时间");
				$("#start").focus();
				return false;
			}
			if($("#end").value == "") {
				alert("请选择结束时间");
				$("#end").focus();
				return false;
			}
			var startTime = $("#start").val();
			var endTime = $("#end").val();
			if(startTime!="" && endTime!="") {
				var date1 = startTime.split("-");
          		var date2 = endTime.split("-");          
          		var myDate1 = new Date(date1[0],date1[1]-1,date1[2]);
         		var myDate2 = new Date(date2[0],date2[1]-1,date2[2]);
           		if (myDate1 <= myDate2) {
           			if(myDate1.getMonth()!=myDate2.getMonth()) {
           				alert("提示：只能查询一个月的数据！");
           				return false;
           			}
           		} else {
	            	alert ("提示:  开始时间不能大于结束时间！");
	             	return false;
             	}
             }
			
	  	}
		
		var startTime = $("#start").val();
		var endTime = $("#end").val();
		var searchMonth = $("#searchMonth").val();
		var agentId = 0;
		var bdId = 0;
		var customerId = 0;
		var projectId = 0;	
		var productId = 0;	
		agentId = $("#agentId").val();
		bdId = $("#bdId").val();
		customerId = $("#customerId").val();
		projectId = $("#projectId").val();
		agentId = $("#agentId").val();
		bdId = $("#bdId").val();
		customerId = $("#customerId").val();
		projectId = $("#projectId").val();
		productId = $("#productId").val();
		if("undefined"==typeof(agentId)) {
			agentId=0;
		}
		if('undefined'==typeof(bdId)) {
			bdId=0;
		}
		if('undefined'==typeof(customerId)) {
			customerId=0;
		}
		if('undefined'==typeof(typeof(agentId))) {
			projectId=0;
		}
		var chk_value="";
	  	$('input[name="billSearchVO.rowFields"]:checked').each(function(){ 
	   		chk_value+=$(this).val();
	   		chk_value+=",";
	  	});
	  	if(chk_value=="") {
	  		alert("请至少选择一项统计列！");
			return false;
	  	}	
	  	$("#load").css({"display":""});
		jQuery("#data").html("");
		jQuery.post(url,{"billSearchVO.startTime":startTime,"billSearchVO.endTime":endTime
		,"billSearchVO.searchMonth":searchMonth,"billSearchVO.customerId":customerId,"billSearchVO.agentId":agentId
		,"billSearchVO.bdId":bdId,"billSearchVO.projectId":projectId,"billSearchVO.productId":productId
		,"billSearchVO.rowFieldString":chk_value
		},
		function(response){
			if(response=="1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");				
				$("#load").css({"display":"none"});
			} else {
				jQuery("#data").html(jQuery.trim(response));
				$("#load").css({"display":"none"});
			}			
		});
	}
	
	var agentId = <s:property value ="billSearchVO.agentId"/>
	if(agentId!=0) {
		selBd(agentId);
	}	
	function selBd(agentId) {
		if(agentId != 0) {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			url = "<%=request.getContextPath()%>/getAllBd.action";
			jQuery.post(url,{"bdVO.agentId":agentId},function(response){
				jQuery("#bdId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#bdId").html('<option value="0">--所有商务--</option>');
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
	var bdId = <s:property value ="billSearchVO.bdId"/>
	if(bdId!=0) {
		selCustomer(bdId);
	}
	
	function selCustomer(bdId) {
		if(bdId != 0) {			
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
			url = "<%=request.getContextPath()%>/getAllCustomer.action";
			jQuery.post(url,{"customerVO.businessDeveloperId":bdId},function(response){
				jQuery("#customerId").html(jQuery.trim(response));
			});
		} else {
			jQuery("#customerId").html('<option value="0">--所有客户--</option>');
			jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
	var customerId = <s:property value="billSearchVO.customerId"/>;
	if(customerId !=0) {
		selProject(customerId);
	}
	
	function selProject(customerId) {
		if(customerId != 0) {
			url = "<%=request.getContextPath()%>/getAllProject.action";
			jQuery.post(url,{"projectVO.customerId":customerId},function(response){
				jQuery("#projectId").html(jQuery.trim(response));
			});
		} else {
				jQuery("#projectId").html('<option value="0">--所有项目--</option>');
		}
	}
	
		
function ajaxFileUpload()
{
	$("#load").ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
    });//文件上传完成将图片隐藏起来  
    var file = $("#file").val();
    if(file=="") {
    	showInfoToastMiddle("请选择上传文件包!");
    	$("#file").focus();
    	return false;
    }
    jQuery("#uploadBtn").attr('disabled', 'disabled');	 
    $.ajaxFileUpload({
    	url:'UserDivide!upload.action',//用于文件上传的服务器端请求地址
        secureuri:false,//一般设置为false
        fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
        dataType: 'json',//返回值类型 一般设置为json
        success: function (data, status) {
            //服务器成功响应处理函数 
            showInfoToastMiddle(data.msg);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
     
        },
        error: function (data, status, e) {
            	//服务器响应失败处理函数
       		alert(e);
        }
    });
    return false;
}
</script>