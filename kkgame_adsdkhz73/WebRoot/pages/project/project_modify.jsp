<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">修改项目</li>
   		</ul>
   	</div>
</div>

<form id="projectForm" class="form-horizontal">
	<s:hidden id="projectId" name="projectVO.id"></s:hidden>
	<table class="table">
		<tr>
			<td class="table_td_title">项目名称<font color="red">*</font></td>			 
            <td><input type="text" class="input-medium" id="name" name="projectVO.name" value="<s:property value="projectVO.name"/>" size="30"/></td>
            <td class="table_td_title">渠道商名称<font color="red">*</font></td>			 
            <td><input type="text" class="input-medium" id="schemeName" name="projectVO.schemeName" value="<s:property value="projectVO.schemeName"/>" size="30"/></td>
		</tr>	
		<role:equal type="SP,MG">
		<tr >
			<td class="table_td_title">所属代理商<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:select id ="agentId" list="#Policy.agentList" listKey="id" listValue="name" name="projectVO.agentId" headerKey="0" headerValue="--请选择代理商--" cssStyle="width:215px;"  onchange="javascript:changeAgent();"></s:select> 
				<font color="red" id="projectOfAgent_message"></font>
			</td>			
		</tr>	
		<tr ><td class="table_td_title">所属商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<select id="bdId"  name="projectVO.bdId" style="width:215px;" onchange="javascript:changeBusinessDeveloper();">
					<option value="0">请选择商务拓展人员</option>
			</select>
			<font color="red" id="projectOfBd_message"></font>
			</td>			
		</tr>
		<tr >
			<td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3" id="bhHtml">				
			<font color="red" id="projectOfBh_message"></font>
			</td>			
		</tr>
		</role:equal>
		<role:equal type="AG">
		<tr >
			<td class="table_td_title">所属商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
			<s:select id ="bdId" list="bdList" listKey="id" listValue="name" name="projectVO.bdId" headerKey="0" headerValue="--请选择商务拓展人员--" cssStyle="width:215px;"  onchange="javascript: changeBusinessDeveloper();"></s:select>
			<font color="red" id="projectOfBd_message"></font>
			</td>			
		</tr>
		<tr >
			<td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3" id="bhHtml">				
			<font color="red" id="projectOfBh_message"></font>
			</td>			
		</tr>
		</role:equal>
		<role:equal type="SP,MG,AG">	
		<tr ><td class="table_td_title">客户<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
			<select id="customerId"  name="projectVO.customerId" style="width: 215px" >
				<option value="0">--请选择客户--</option>
			</select>
			[必须为审核通过的客户]
			</td>		
		</tr>
		</role:equal>		
		<role:equal type="BD">		
		<tr >
			<td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3" >
				<s:iterator value="bhList" status="bhStat">
					<input type="radio" name="businessHelperId"  value="<s:property value="id"/>"/><s:property value="name"/>
					<s:if test="(#bhStat.index+1)%4==0">
						</br>
					</s:if>
				</s:iterator>	
			<font color="red" id="projectOfBh_message"></font>
			</td>			
		</tr>
		<tr>
			<td class="table_td_title">客户<font color="red">*</font></td>	
			<td  width="10%"  nowrap="nowrap" colspan="3">
				<select id="customerId" name="projectVO.customerId" style="width: 150px">
				<option value="0">--请选择客户--</option>
					<s:iterator value="customerList">
						<option value="<s:property value="id"/>"><s:property value="name"/></option>
					</s:iterator>
				</select>
				[必须为审核通过的客户]
			</td>
			
		</tr>
		</role:equal>	
		
		<tr >
			<td class="table_td_title">项目类型<font color="red">*</font></td>			 
            <td  colspan="3">
				<input type="radio" value="1" name="projectVO.type" />&nbsp;广告SDK&nbsp;&nbsp;			
				<input type="radio" value="2" name="projectVO.type" />&nbsp;DDL
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">合作模式<font color="red">*</font></td>			 
            <td >
				<s:select list="#Policy.cooperateTypeList" id="cooperateType" name="projectVO.cooperateType" listKey="key" listValue="value" headerKey="0" headerValue="--请选择--" cssStyle="width: 150px" ></s:select>
			</td>
			<td class="table_td_title">推广方式<font color="red">*</font></td>			 
            <td>
					<s:select list="#Policy.populariseTypeList" id="populariseType" name="projectVO.populariseType" listKey="key" listValue="value" headerKey="0" headerValue="--请选择--" cssStyle="width: 150px" ></s:select>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">平台<font color="red">*</font></td>			 
            <td >
				<s:select list="#Policy.osList" id="osId" name="projectVO.osId" listKey="key" listValue="value" headerKey="0" headerValue="--请选择平台--" cssStyle="width: 150px" onchange="selectProduct()" ></s:select>
			</td>
			<td class="table_td_title">分辨率<font color="red">*</font></td>			 
            <td>
					<s:select list="#Policy.screenList" id="screenId" name="projectVO.screenId" listKey="key" listValue="value" headerKey="0" headerValue="--请选择分辨率--" cssStyle="width: 150px" onchange="selectProduct()"></s:select>
			</td>
		</tr>		
		
		<tr >
			<td class="table_td_title">选择产品<font color="red">*</font></td>			 
            <td  colspan="3" id="inData">
            	
			</td>
		</tr>
		<tr>
			<td class="table_td_title">游戏名称<font color="red">*</font></td>			 
            <td><input type="text" class="input-medium" id="gameName" name="projectVO.gameName" value="<s:property value="projectVO.gameName"/>" /></td>
            <td class="table_td_title">SDK<font color="red">*</font></td>			 
            <td><input type="text" class="input-medium" id="feeName" name="projectVO.feeName" value="<s:property value="projectVO.feeName"/>"/></td>
		</tr>
		<tr >
			<td class="table_td_title">备注</td>			 
            <td  colspan="3"><textarea id="intro" name="projectVO.intro" cols="50" rows="5" ><s:property value="projectVO.intro"/></textarea></td>
		</tr>
		<tr >
		    <td  colspan="4">
			<div align="center">				
			   <input id="sb" class="btn btn-primary" type="button" value=" 下一步 " onclick="VerifyData(1);"/>&nbsp;&nbsp;
			   <input id="btn2" class="btn" type="button" value=" 返 回 " onclick="doBack('<s:property value="projectVO.queryType"/>');"/>&nbsp;&nbsp;
		    </div>
			</td>
		</tr>
	</table>
<form>

<script type="text/javascript">
	var myForm = document.getElementById("projectForm");
	//代理商 列表
	<role:equal type="SP,MG,BH">
		var  agentList = new Array();
		<s:iterator value="#Policy.agentList" status="stat"  >
			var str = new Array();
			str.push('<s:property value="id"/>');
			str.push('<s:property value="name" escape="false"/>');
			agentList.push(str);
		</s:iterator>
		//商务人员列表
		var  bdList = new Array();
		<s:iterator value="#Policy.bdList" status="stat"  >
			var str = new Array();
			str.push('<s:property value="id"/>');
			str.push('<s:property value="name" escape="false"/>');
			str.push('<s:property value="parentId"/>');
			bdList.push(str);
		</s:iterator>
	</role:equal>
	<role:equal type="SP,MG,BH,AG">
		//商务协助人员列表
		var  bhList = new Array();
		<s:iterator value="#Policy.bhList" status="stat"  >
			var str = new Array();
			str.push('<s:property value="id"/>');
			str.push('<s:property value="name" escape="false"/>');
			str.push('<s:property value="parentId"/>');
			bhList.push(str);
		</s:iterator>
		//客户列表
		var  customerList = new Array();
		<s:iterator value="#Policy.customerList" status="stat"  >
			var str = new Array();
			str.push('<s:property value="id"/>');
			str.push('<s:property value="name" escape="false"/>');
			str.push('<s:property value="businessDeveloperId"/>');
			customerList.push(str);
		</s:iterator>
	</role:equal>
	/**
		var productList = new Array();
		var str = new Array();
		str.push('<s:property value="id"/>');
		str.push('<s:property value="name" escape="false"/>');
		productList.push(str);
	*/
function VerifyData(status){
	jQuery("#mess").html("");
	var form = jQuery("#projectForm")[0];	
	var name = $("#name").val();
	var projectId  =$("#projectId").val();
	if ( name==""){
		showInfoToastMiddle("请输入项目名称!");
		$("#name").focus();
		return false;
	}
	var schemeName = $("#schemeName").val()
	if ( schemeName ==""){
		showInfoToastMiddle("请输入渠道商名称!");
		$("#schemeName").focus();
		return false;
	}
	var gameName  =$("#gameName").val()
	if ( schemeName ==""){
		showInfoToastMiddle("请输入产品名称!");
		$("#gameName").focus();
		return false;
	}
	var feeName  =$("#feeName").val()
	if ( feeName ==""){
		showInfoToastMiddle("请输入计费SDK名称!");
		$("#feeName").focus();
		return false;
	}
	var agentId = 0;
	<role:equal type="SP,MG">
		agentId = $("#agentId").val();
		if (agentId =="" || agentId ==0){
			showInfoToastMiddle("请选择代理商!");
			$("#agentId").focus();
			return false;
		}
	</role:equal>	
	var bdId = 0
	<role:equal type="SP,MG,AG">
		bdId = $("#bdId").val()
		if (bdId =="" || bdId ==0){
			showInfoToastMiddle("请选择商务拓展人员！");
			$("#bdId").focus();
			return false;
		}
	//form["projectVO.bdName"].value= jQuery("select[name=projectVO.businessDeveloperId] option[selected]").text(); 
	</role:equal>	
	var customerId = $("#customerId").val();
	if(customerId == ""||customerId == 0) {
		showInfoToastMiddle("请选择客户!");
		$("#customerId").focus();
		return false;
	}
	var cooperateType = $("#cooperateType").val();
	if( cooperateType== 0) {
		showInfoToastMiddle("请选择合作模式!");
		$("#cooperateType").focus();
		return false;
	}
	var populariseType = $("#populariseType").val();
	if(screenId == 0) {
		showInfoToastMiddle("请选择推广方式!");
		$("#populariseType").focus();
		return false;
	}
	var osId = $("#osId").val();
	if( osId== 0) {
		showInfoToastMiddle("请选择平台版本!");
		$("#osId").focus();
		return false;
	}
	var screenId = $("#screenId").val();
	if(screenId == 0) {
		showInfoToastMiddle("请选择分辨率!");
		$("#screenId").focus();
		return false;
	}
	var productIds = "";
	$('input[name="productId"]:checked').each(function(){ 
    	productIds += $(this).val()+","
   	});
    if(productIds=="") {
    	showInfoToastMiddle("请选择产品!");
     	return false;
    }
   
    var intro = $("#intro").val();
    var type = $('input[name="projectVO.type"]:checked').val();
    var bhId = $('input[name="businessHelperId"]:checked').val();
    if(bhId==null||bhId=='undefined') {
     	bhId = 0;
    }
 	var queryType = $("#queryType").val();
    var url = "<%=request.getContextPath()%>/Project!modifyCooperateInput.action";
    jQuery.post(url, {"projectVO.id":projectId,"projectVO.name":name,"projectVO.schemeName":schemeName,"projectVO.agentId":agentId,
				    	"projectVO.bdId":bdId,"projectVO.customerId":customerId,
				    	"projectVO.osId":osId,"projectVO.screenId":screenId,"projectVO.cooperateType":cooperateType,"projectVO.populariseType":populariseType,
				    	"projectVO.productIds":productIds,"projectVO.intro":intro,
						"projectVO.bhId":bhId ,"projectVO.queryType":queryType,
				    	"projectVO.gameName":gameName,"projectVO.feeName":feeName,
				    	"projectVO.type":type 
	}, function(response){
	       if(jQuery.trim(response) == "0"){
				showErrorToastMiddle("系统出错，请重试或联系管理员");
	       } else {
        		jQuery("#projectForm").html(response);
	        }
   	});
	
}
function changeAgent(){
 	setBdList();
}

function changeBusinessDeveloper(){
	setBusinessHelperIds();
	setCustomerList();
}
//设置协助人员
function  setBusinessHelperIds(){
	var businessHelper = document.getElementById("bhHtml");
	var businessDeveloperId = document.getElementById("bdId").value;
	var index = 0;
	var checkboxs = "";
	if(bhList.length == 0){
		businessHelper.innerHTML = checkboxs;
		$("#bhHtml").html(bhSelects);
		return	false;
	}
	for (var i=0;i<bhList.length;i++){
		if(bhList[i][2]>0 &&  bhList[i][2] == businessDeveloperId){	
			index++;
			checkboxs += "<input type=\"radio\" name=\"businessHelperId\"  value=\""+bhList[i][0]+"\" />&nbsp;"+bhList[i][1]+"&nbsp;&nbsp;&nbsp;&nbsp;";
			if(index%4==0){
				checkboxs += "<br>";
			}
		}	
		
	}
	businessHelper.innerHTML = checkboxs;
}

function setBdList(){
	var agentId = document.getElementById("agentId").value;
	var businessDeveloperId = document.getElementById("bdId").value;
	var businessDeveloperSelect = document.getElementById("bdId");
 	document.getElementById("bdId").value = 0;
	//选择所有省份时清空列表，且不进行同步更新
	if(agentId==0){
		removeOptions(businessDeveloperSelect);
		businessDeveloperSelect.options[0] = new Option("请选择商务拓展人员",0);
		//将id置为 0		
		return false;
	}
	//清空列表
	removeOptions(businessDeveloperSelect);
	businessDeveloperSelect.options[0] = new Option("请选择商务拓展人员",0);
  	var index = 0;
	for(var i=0;i<bdList.length;i++){
		if(bdList[i][2] == agentId ){
			index +=1;
			businessDeveloperSelect.options[index] = new Option(bdList[i][1],bdList[i][0]);  	
		}
	}
}
function setCustomerList(){
	var businessDeveloperId = document.getElementById("bdId").value;
	var customer = document.getElementById("customerId");
 	document.getElementById("customerId").value = 0;
	//选择所有省份时清空列表，且不进行同步更新
	if(businessDeveloperId==0){
		removeOptions(customer);
		customer.options[0] = new Option("--请选择客户--",0);
		//将id置为 0		
		return false;
	}
	//清空列表
	removeOptions(customer);
	customer.options[0] = new Option("--请选择客户--",0);
	if(businessDeveloperId!=0) {
		var index = 0;
		for(var i=0;i<customerList.length;i++){
			if(customerList[i][2] == businessDeveloperId ){
				index +=1;
				customer.options[index] = new Option(customerList[i][1],customerList[i][0]);
			}
		}
	}
}

//清空列表
function removeOptions(form){
	if(form.options.length>0){
		for(var i=(form.options.length-1);i>=0;i--){
			var o=form.options[i];
			form.options[i] = null;
		}	
	}
}
function viewProjetListOfCustomer(){	
	var customerId = myForm["projectVO.customerId"].value ;
	if(customerId=="" || customerId == "0"){
		alert("请选择一个客户。");
		myForm["projectVO.customerId"].focus();
		return false ;
	}
	window.open('Project!viewProjectListOfCustomer.action?projectVO.customerId='+customerId,"","toolbar=no, scrollbars=yes,resizable=yes,top=100,left=100,width=600,height=500");
}

function selectProduct() {
	var screenId = $("#screenId").val();
	var osId = $("#osId").val();
	if(screenId==0||osId==0) {
		jQuery("#inData").html("");
		return ;
	} else {
		var url = "<%=request.getContextPath()%>/getProductForCheck.action";
		jQuery.post(url,{"productVO.osId":osId,"productVO.screenId":screenId
		},function(response){
			if(response=="1") {
				showErrorToastMiddle("系统出错，请重试或联系管理员");
			} else {
				jQuery("#inData").html(jQuery.trim(response));
				setExistProduct();
			}
		});		
	}
}



function setExistProduct() {
	var productIds = '<s:property value="projectVO.productIds"/>';
	var data = productIds.toString().split(",");
	for(var i = 0; i < data.length; i = i + 1){
		$("#productId_" + data[i]).attr("checked", true);
	}
}

<role:equal type="SP,MG">
changeAgent();
var bdId = '<s:property value="projectVO.bdId"/>';
//$("#bdId option[value='"+bdId+"']").attr("selected", "selected");
$("#bdId").val(bdId);
setBusinessHelperIds();
var bhId = '<s:property value="projectVO.bhId"/>';
$("input[name='businessHelperId'][value="+bhId+"]").attr("checked",'checked');
setCustomerList();
var customerId = '<s:property value="projectVO.customerId"/>';
//$("#customerId option[value='"+customerId+"']").attr("selected", "selected");
$("#customerId").val(customerId);
</role:equal>
<role:equal type="AG">
setBusinessHelperIds();
var bhId = '<s:property value="projectVO.bhId"/>';
$("input[name='businessHelperId'][value="+bhId+"]").attr("checked",'checked');
setCustomerList();
var customerId = '<s:property value="projectVO.customerId"/>';
$("#customerId").val(customerId);
</role:equal>
<role:equal type="BD">
var bhId = '<s:property value="projectVO.bhId"/>';
$("input[name='businessHelperId'][value="+bhId+"]").attr("checked",'checked');
var customerId = '<s:property value="projectVO.customerId"/>';
$("#customerId").val(customerId);
</role:equal>
var type = '<s:property value="projectVO.type"/>';
$("input[name='projectVO.type'][value="+type+"]").attr("checked",'checked');
selectProduct();
</script>