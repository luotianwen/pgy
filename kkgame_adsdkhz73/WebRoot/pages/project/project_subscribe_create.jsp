<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>

<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">新建项目</li>
   		</ul>
   	</div>
</div>

<form id="projectForm" class="form-horizontal" >
	<table class="table">
		<tr>
			<td class="table_td_title">项目名称<font color="red">*</font></td>			 
            <td><input type="text" class="input-medium" id="name" name="projectVO.name" /></td>
            <td class="table_td_title">渠道商名称<font color="red">*</font></td>			 
            <td><input type="text" class="input-medium" id="schemeName" name="projectVO.schemeName"/></td>
		</tr>		

		<tr >
			<td class="table_td_title"> 所属代理商<font color="red">*</font></td>	
			<td colspan="3">
				<s:select id ="agentId" list="#Policy.agentList" listKey="id" listValue="name" name="projectVO.agentId" headerKey="0" headerValue="--请选择代理商--" cssStyle="width:215px;"  onchange="javascript:changeAgent();"></s:select> 
				<font color="red" id="projectOfAgent_message"></font>
			</td>			
		</tr>	
		<tr ><td class="table_td_title">所属商务拓展人员<font color="red">*</font></td>	
			<td  >
				<select id="bdId"  name="projectVO.bdId" style="width:215px;" onchange="javascript:changeBusinessDeveloper();">
					<option value="0">请选择商务拓展人员</option>
			</select>
			<font color="red" id="projectOfBd_message"></font>
			</td>			

			<td class="table_td_title">商务协助人员</td>	
			<td id="bhHtml"  >
			<font color="red" id="projectOfBh_message"></font>
			</td>			
		</tr>



		<tr >
			<td class="table_td_title">客户<font color="red">*</font></td>
			<td >
			<select id="customerId"  name="projectVO.customerId" style="width: 215px" >
				<option value="0">--请选择客户--</option>
			</select>
			[必须为审核通过的客户]
			</td>
			<td class="table_td_title">单价</td>

			<td><input type="text" class="input-medium" id="price" name="projectVO.price"/></td>

		</tr>

		<tr >
			<td class="table_td_title">选择产品<font color="red">*</font></td>
			<td colspan="3" id="inData">
				<select id="productIds" name="projectVO.productIds" class="selwidth">
					<s:action name="getAllSubscribeProduct" namespace="" executeResult="true">
					</s:action>
				</select>
			</td>
		</tr>


		<tr >
			<td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea id="intro" name="projectVO.intro" cols="50" rows="5" ></textarea></td>
		</tr>
		<tr >
		    <td  colspan="4">
			<div align="center">				
			   <input id="btn1" class="btn btn-primary" type="button" value=" 下一步 " onclick="VerifyData(1);"/>&nbsp;&nbsp;

		    </div>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	var myForm = document.getElementById("projectForm");

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


function VerifyData(status){
	
	var form = jQuery("#projectForm")[0];	
	var name = $("#name").val();
	if ( name==""){
		showInfoToastMiddle("请输入项目名称!");
		$("#name").focus();
		return false;
	}
	var schemeName = $("#schemeName").val();
	if ( schemeName ==""){
		showInfoToastMiddle("请输入渠道商名称!");
		$("#schemeName").focus();
		return false;
	}


	var agentId = 0;

		agentId = $("#agentId").val();
		if (agentId =="" || agentId ==0){
			showInfoToastMiddle("请选择代理商!");
			$("#agentId").focus();
			return false;
		}

	var bdId = 0

		bdId = $("#bdId").val()
		if (bdId =="" || bdId ==0){
			showInfoToastMiddle("请选择商务拓展人员!");
			$("#bdId").focus();
			return false;
		}

	var customerId = $("#customerId").val();
	if(customerId == ""||customerId == 0) {
		showInfoToastMiddle("请选择客户!");
		$("#customerId").focus();
		return false;
	}

	var productIds = $("#productIds").val();;

    if(productIds=="") {
    	showInfoToastMiddle("请选择产品!");
     	return false;
    }
    var intro = $("#intro").val();
	var bhId=0;
    if(bhId==null||bhId=='undefined') {
     	bhId = 0;
    }
	var price = $("#price").val();

    var url = "<%=request.getContextPath()%>/Project!subscribeSave.action";
    jQuery.post(url, {"projectVO.name":name,"projectVO.schemeName":schemeName,"projectVO.agentId":agentId,
				    	"projectVO.bdId":bdId,"projectVO.customerId":customerId,
				    	"projectVO.price":price,
				    	"projectVO.productIds":productIds,"projectVO.intro":intro,
				    	"projectVO.bhId":bhId
				    	 
    }, function(response){
			if(jQuery.trim(response) == "-1"){
				showErrorToastMiddle("系统出错，请重试或联系管理员");
	       	} else {
				jQuery("#projectForm").html(response);
				showInfoToastMiddle(response);

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



</script>