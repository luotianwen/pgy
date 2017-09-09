<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="customerVO.id>0">修改客户信息</s:if><s:else >新建客户商信息</s:else></h2>
</div>
<div class="modal-body">
<form id="customerForm" class="form-horizontal">
<s:hidden id="customerId" name="customerVO.id"/>
<s:if test="customerVO.id==0">
<role:equal type="AG">
<s:hidden id="agentId"  name="customerVO.agentId"/>
</role:equal>
<role:equal type="BD">
<s:hidden id="agentId"  name="customerVO.agentId"/>
<s:hidden id="bdId"  name="customerVO.businessDeveloperId"/>
</role:equal>
</s:if>
<s:if test="customerVO.id>0">
<s:hidden id="agentId"  name="customerVO.agentId"/>
<s:hidden id="bdId"  name="customerVO.businessDeveloperId"/>
<s:hidden id="contactPerson"  name="customerVO.contactPerson"/>
<s:hidden id="contactPerson"  name="customerVO.contactInfo"/>
</s:if>
<div class="control-group">
<label class="control-label" for="name">客户名称</label>
<div class="controls">
<input id="name" type="text" class="input-medium" name="customerVO.name" value="${ customerVO.name}" onblur="checkName()" /> 
<IMG id="Img01" height="13" src="<%=request.getContextPath()%>/images/search1.gif" style="CURSOR: hand"  alt="检查此客户是否存在" width="13"   onclick="searchCustomerList(); return true;"> &nbsp; <font color="red" id="customerName_message"></font>
</div>
</div>
<s:if test="customerVO.id==0">
<div class="control-group">
<label class="control-label" for="contactPerson">联系人</label>
<div class="controls">
    <input id="contactPerson" type="text" class="input-medium" name="customerVO.contactPerson" value="${ customerVO.contactPerson}" /> 
</div>
</div>
<div class="control-group">
<label class="control-label" for="contactInfo">联系电话</label>
<div class="controls">
    <input id="contactInfo" type="text" class="input-medium" name="customerVO.contactInfo" value="${ customerVO.contactInfo}" /> 
</div>
</div>
</s:if>
<s:if test="customerVO.id>0">
<div class="control-group">
<label class="control-label" for="contactPerson">联系人</label>
<div class="controls">
${ customerVO.contactPerson}
</div>
</div>
<div class="control-group">
<label class="control-label" for="contactInfo">联系电话</label>
<div class="controls">
${ customerVO.contactInfo}
</div>
</div>
</s:if>
<div class="control-group">
<label class="control-label" for="city">城市</label>
<div class="controls">
  	<input id="city" type="text" class="input-medium" name="customerVO.city" value="${ customerVO.city}"   />
</div>
</div>
<s:if test="customerVO.id==0">
<role:equal type="SP,MG,BH">
<div class="control-group">
<label class="control-label" for="agentId">所属代理商</label>
<div class="controls">
  	<s:select id ="agentId" list="agentList" listKey="id" listValue="name" name="customerVO.agentId" headerKey="0" headerValue="--请选择代理商--" cssStyle="width:215px;"  onchange="javascript:changeAgent();"></s:select> 
			<font color="red" id="customerOfAgent_message"></font>
</div>
</div>	
<div class="control-group">
<label class="control-label" for="bdId">所属商务拓展人员</label>
<div class="controls">
  	<select id="bdId"  name="customerVO.businessDeveloperId" Style="width:215px;" onchange="javascript:changeBusinessDeveloper();">
				<option value="0">请选择商务拓展人员</option>
			</select>
			<font color="red" id="customerOfBd_message"></font>
</div>
</div>		
</role:equal>
<role:equal type="AG">
<div class="control-group">
<label class="control-label" for="bdId">所属商务拓展人员</label>
<div class="controls">
  	<s:select id ="bdId" list="bdList" listKey="id" listValue="name" name="customerVO.businessDeveloperId" headerKey="0" headerValue="--请选择商务拓展人员--" cssStyle="width:215px;"  onchange="javascript:changeBusinessDeveloper();"></s:select>
			<font color="red" id="customerOfBd_message"></font>
</div>
</div>	
</role:equal>
</s:if>
<s:if test="customerVO.id>0">
<role:equal type="SP,MG,BH">
<div class="control-group">
<label class="control-label" >所属代理商</label>
<div class="controls">
${customerVO.agentName}
</div>
</div>
</role:equal>		
<role:equal type="SP,MG,AG,BH">
<div class="control-group">
<label class="control-label" >所属商务拓展人员</label>
<div class="controls">
${customerVO.businessDeveloperName}
</div>
</div>
</role:equal>
</s:if>
<s:if test="customerVO.id==0">
<%@ include file="../common/user_create.jsp" %>
</s:if>
<s:if test="customerVO.id>0">
<%@ include file="../common/user_modify.jsp" %>
</s:if>
<div class="control-group">
<label class="control-label" for="city">备注</label>
<div class="controls">
  	<textarea id="description" name="customerVO.description" cols="80" rows="5" >${ customerVO.description}</textarea>
</div>
</div>	
<div class="control-group">
<label class="control-label" for="city"></label>
<div class="controls">
	<input id="btn" class="btn btn-primary" type="button" value="<s:if test="customerVO.id==0">创  建</s:if><s:if test="customerVO.id>0">修 改</s:if>" onclick="javascript:save();"/>&nbsp;&nbsp;

</div>
</div>	
</form>
<script type="text/javascript">
var CUSTOMER_NAME_VALID = false;
<s:if test="customerVO.id>0">
CUSTOMER_NAME_VALID = true;
</s:if>
$("#name").focus();
function checkName(){
	var id = $("#customerId").val();
	jQuery("#name").val(jQuery.trim(jQuery("#name").val()));
   	var name = jQuery("#name").val();
   	if(name == ""){
	   jQuery("#customerName_message").html("请输入客户名称");
       return;
    }
    var url = "<%=request.getContextPath()%>/Customer!validName.action";
    jQuery.post(url, {"customerVO.id":id,"customerVO.name":name}, function(response){    
       if(jQuery.trim(response) == "false"){
       		CUSTOMER_NAME_VALID = true;
  			document.getElementById("btn").disabled=false;
           	jQuery("#customerName_message").html("");
       }else if(jQuery.trim(response) == "true"){
       		CUSTOMER_NAME_VALID = false;
        	jQuery("#customerName_message").html("该客户名称已存在");
        	jQuery("#name").focus();
        }
   });
}

function save(){
	var url = "<%=request.getContextPath()%>/Customer!save.action";
	var id = $("#customerId").val();
	if(id>0) {
		url = "<%=request.getContextPath()%>/Customer!update.action";
	}
	var name = $("#name").val();
	if(name=="") {
		showInfoToastMiddle("请输入客户名称!");
		$("#name").focus();
		return false;
	}
	if (CUSTOMER_NAME_VALID == false){
		showInfoToastMiddle("您填写的客户名称已存在，请正确填写！");
		$("#name").focus();
		return false;
	}
	var contactPerson = $("#contactPerson").val();
	if(contactPerson=="") {
		showInfoToastMiddle("请输入联系人!");
		$("#contactPerson").focus();
		return false;
	}
	var contactInfo = $("#contactInfo").val();
	if(contactInfo=="") {
		showInfoToastMiddle("请输入联系电话!");
		$("#contactInfo").focus();
		return false;
	}
	var city = $("#city").val();
	if(city=="") {
		showInfoToastMiddle("请输入城市!");
		$("#city").focus();
		return false;
	}
	var description = $("#description").val();
	var agentId = $("#agentId").val();
	<role:equal type="SP,MG,BH">
	if(agentId==""||agentId==0) {
		showInfoToastMiddle("请选择代理商!");
		$("#agentId").focus();
		return false;
	}
	</role:equal>
	var businessDeveloperId = $("#bdId").val();
	<role:equal type="SP,MG,AG,BH">
	if(businessDeveloperId==""||businessDeveloperId==0) {
		showInfoToastMiddle("请选择商务拓展人员!");
		$("#bdId").focus();
		return false;
	}
	</role:equal>
	var loginId = $("#loginId").val();
    if(loginId=="") {
    	showInfoToastMiddle("请输入登录账号!");
    	$("#loginId").focus();
    	return ;
    }
    var passwd = $("#passwd").val();
    if(passwd=="") {
    	showInfoToastMiddle("请输入账号密码!");
    	$("#passwd").focus();
    	return ;
    }
    if(passwd.length<6) {
    	showInfoToastMiddle("密码小于6位，请重新输入!");
    	$("#passwd").focus();
    	return ;
    }
    var userId = $("#userId").val();
    var status = $("#status").val(); 
    jQuery("#btn").attr('disabled', 'disabled');	      
    jQuery.post(url, {"customerVO.id":id,"customerVO.name":name,"customerVO.contactPerson":contactPerson,"customerVO.description":description
    	,"customerVO.contactInfo":contactInfo,"customerVO.city":city,"customerVO.agentId":agentId,"customerVO.businessDeveloperId":businessDeveloperId,
    	"portalUserVO.loginId":loginId,"portalUserVO.passwd":passwd,"portalUserVO.id":userId,"portalUserVO.status":status}, 
    	function(response){
    	 	if(jQuery.trim(response) == "-1"){
	       		showErrorToastMiddle("系统出错，请重试或联系管理员");
	        	jQuery("#btn").attr('disabled', 'disabled');	           
	       	} else if(jQuery.trim(response) == "-1") {
	       		showErrorToastMiddle("暂无创建客户空间!");
	        	jQuery("#btn").attr('disabled', 'disabled');	           
	        } else {
				showInfoToastMiddle(response);
				if(id>0) {
					doPageBottom('turn');
				}
	        }
   });
}

<s:if test="customerVO.id==0">
//代理商 列表
var  agentList = new Array();
<s:iterator value="agentList" status="stat"  >
var str = new Array();
str.push('<s:property value="id"/>');
str.push('<s:property value="name"/>');
str.push('<s:property value="level"/>');
agentList.push(str);
</s:iterator>
//商务人员列表
var  bdList = new Array();
<s:iterator value="bdList" status="stat"  >
var str = new Array();
str.push('<s:property value="id"/>');
str.push('<s:property value="name" escape="false"/>');
str.push('<s:property value="level"/>');
str.push('<s:property value="agentId"/>');
bdList.push(str);
</s:iterator>
//商务人员拥有客户的数量
var  cuCountForBdMap = new Array();
<s:iterator value="cuCountForBdMap" status="stat"  >
var str = new Array();
str.push('<s:property value="key"/>');
str.push('<s:property value="value"/>');
cuCountForBdMap.push(str);
</s:iterator>
//代理商拥有客户的数量
var  cuCountForAgentMap = new Array();
<s:iterator value="cuCountForAgentMap" status="stat"  >
var str = new Array();
str.push('<s:property value="key"/>');
str.push('<s:property value="value"/>');
cuCountForAgentMap.push(str);
</s:iterator>

<role:equal type="SP,MG,BH">
function changeAgent(){
	setBdList();
 	var agentId = jQuery("#agentId").val();
	var agentName =	''; 
	var agentLevel = 0;
	var countOfAgent = 0;
	for(var i=0;i<agentList.length;i++){
		if(agentList[i][0] == agentId ){
		agentName = agentList[i][1];	
		agentLevel = agentList[i][2];	
		}
	}
	for(var i=0;i<cuCountForAgentMap.length;i++){
		if(cuCountForAgentMap[i][0] == agentId ){
		countOfAgent = cuCountForAgentMap[i][1];
		}
	}
	if(agentId !=0){	
	 	jQuery("#customerOfAgent_message").html("代理商["+agentName+"] 拓展级别："+agentLevel+" , 现有拓展客户："+countOfAgent);
	}else{
	 	jQuery("#customerOfAgent_message").html("");
	}
}

function setBdList(){
	var agentId = $("#agentId").val();
	var businessDeveloperId = $("#bdId").val();
	var businessDeveloperSelect = document.getElementById("bdId");
 	$("#bdId").val(0);
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
		if(bdList[i][3] == agentId ){
			index +=1;
			businessDeveloperSelect.options[index] = new Option(bdList[i][1],bdList[i][0]);
		}
	}
}
</role:equal>
function changeBusinessDeveloper(){
	var businessDeveloperId = jQuery("#bdId").val();
	var businessDeveloperName = ''; 
	var businessDeveloperLevel = 0; 
	var countOfBd = 0;
	for(var i=0;i<bdList.length;i++){
		if(bdList[i][0] == businessDeveloperId ){
			businessDeveloperName = bdList[i][1];	
			businessDeveloperLevel	 = bdList[i][2];	
		}
	}	
	for(var i=0;i<cuCountForBdMap.length;i++){
		if(cuCountForBdMap[i][0] == businessDeveloperId ){
		countOfBd = cuCountForBdMap[i][1];
		}
	}
	if(businessDeveloperId !=0){
		jQuery("#customerOfBd_message").html("商务拓展人员["+businessDeveloperName+"] 拓展级别："+businessDeveloperLevel+" , 现有拓展客户："+countOfBd);
	}else{
		jQuery("#customerOfBd_message").html("");
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
</s:if>
var myForm = $("#customerForm")[0];
function searchCustomerList()
{
	var name = jQuery("#name").val();
	if (jQuery("#name").val() == ""){
	      alert("请输入查询条件(客户名称).");
	      jQuery("#name").focus();
		return;
	}
	var url = "Customer!getCustomerExistent.action?customerVO.name="+encodeURIComponent(name);	
  	window.open(encodeURI(url),"","height=300px,width=460px,toolbar=no,menubar=no,resizable=no,location=no,status=no");
}

</script>
