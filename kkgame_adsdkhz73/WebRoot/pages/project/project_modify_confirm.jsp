<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
	<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
	<s:hidden id="projectId" name="projectVO.id"></s:hidden>
	<s:hidden id="name" name="projectVO.name"></s:hidden>
	<s:hidden id="schemeName" name="projectVO.schemeName"></s:hidden>
	<s:hidden id="agentId" name="projectVO.agentId"></s:hidden>
	<s:hidden id="bhId" name="projectVO.bhId"></s:hidden>
	<s:hidden id="bdId" name="projectVO.bdId"></s:hidden>
	<s:hidden id="customerId" name="projectVO.customerId"></s:hidden>
	<s:hidden id="osId" name="projectVO.osId"></s:hidden>
	<s:hidden id="screenId" name="projectVO.screenId"></s:hidden>	
	<s:hidden id="cooperateType" name="projectVO.cooperateType"></s:hidden>
	<s:hidden id="populariseType" name="projectVO.populariseType"></s:hidden>
	<s:hidden id="productIds" name="projectVO.productIds"></s:hidden>
	<s:hidden id="intro" name="projectVO.intro"></s:hidden>
	<s:hidden id="price" name="projectVO.price"></s:hidden>
	<s:hidden id="feeName" name="projectVO.feeName"></s:hidden>
	<s:hidden id="gameName" name="projectVO.gameName"></s:hidden>
	<s:hidden id="type" name="projectVO.type"></s:hidden>	
	<table class="table">
		<tr>
			<td class="table_td_title">项目名称<font color="red">*</font></td>			 
            <td>
            	<s:property value="projectVO.name"/>
            </td>
            <td class="table_td_title">渠道商名称<font color="red">*</font></td>			 
            <td><s:property value="projectVO.schemeName"/></td>
		</tr>	
		<role:equal type="SP,MG,BD">
		<tr >
			<td class="table_td_title">所属代理商<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.agentList">
					<s:if test="id==projectVO.agentId">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>	
		<tr ><td class="table_td_title">商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		<tr ><td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		</role:equal>
		<role:equal type="AG">
		<tr ><td class="table_td_title">商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>
		<tr ><td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		</role:equal>				
		<role:equal type="SP,MG,AG,BD">	
		<tr ><td class="table_td_title">客户<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.customerList">
					<s:if test="projectVO.customerId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>		
		</tr>
		</role:equal>
		<tr >
			<td class="table_td_title">项目类型<font color="red">*</font></td>			 
            <td colspan="3">
            	<s:if test="projectVO.type==1">广告SDK</s:if>
            	<s:if test="projectVO.type==2">DDL</s:if>
			</td>
		</tr>	
		<tr >
			<td class="table_td_title">合作模式<font color="red">*</font></td>			 
            <td >
            	<s:iterator value="#Policy.cooperateTypeList">
					<s:if test="projectVO.cooperateType==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">推广方式<font color="red">*</font></td>			 
            <td>
            	<s:iterator value="#Policy.populariseTypeList">
					<s:if test="projectVO.populariseType==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">平台<font color="red">*</font></td>			 
            <td >
            	<s:iterator value="#Policy.osList">
					<s:if test="projectVO.osId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">分辨率<font color="red">*</font></td>			 
            <td>
            	<s:iterator value="#Policy.screenList">
					<s:if test="projectVO.screenId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">产品<font color="red">*</font></td>			 
            <td colspan="3" id="inData">
            	
			</td>
		</tr>
			
		<tr>
			<td class="table_td_title">产品名称<font color="red">*</font></td>			 
            <td>
            	<s:property value="projectVO.gameName"/>
            </td>
            <td class="table_td_title">SDK名称<font color="red">*</font></td>			 
            <td><s:property value="projectVO.feeName"/></td>
		</tr>	
		
		<tr >
			<td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea cols="50" rows="5" ><s:property value="projectVO.intro"/></textarea></td>
		</tr>
	</table>
	<div style="padding-bottom: 5px"></div>
	<table class="table">
		<tr style="" align="center">
			<td class="table_td_title">产品</td>
			<td class="table_td_title">合作模式</td>
			<td class="table_td_title">角色</td>
			<td class="table_td_title">不可卸载激活单价</td>
			<td class="table_td_title">可卸载激活单价</td>	
			<td class="table_td_title">分成比例</td>	
		</tr>
		<s:iterator value="productVOList" status="stat">			
			<input type="hidden" id="bdInPrice_<s:property value="id"/>" value="<s:property value="bdBuildInRegisterPrice"/>">
			<input type="hidden" id="bdOutPrice_<s:property value="id"/>" value="<s:property value="bdUninstallRegisterPrice"/>">
			<input type="hidden" id="bdPercent_<s:property value="id"/>" value="<s:property value="bdDividePercent"/>">
			<input type="hidden" id="cuPercent_<s:property value="id"/>" value="<s:property value="cuDividePercent"/>">
		<tr >
			<td rowspan="<s:if test="projectVO.bhId>0">3</s:if><s:else>2</s:else>">
				<s:property value="name"/>
				<input type="hidden" name="productVOList[<s:property value="#stat.index"/>].id" value="<s:property value="id"/>" />
			</td>
			<td rowspan="<s:if test="projectVO.bhId>0">3</s:if><s:else>2</s:else>">
				<s:if test="cooperateMode==0">
					<input type="radio" name="productVOList[<s:property value="#stat.index"/>].cooperateMode"  value="2" checked="checked">分成
					<input type="radio" name="productVOList[<s:property value="#stat.index"/>].cooperateMode"  value="1" >激活
				</s:if>
				<s:if test="cooperateMode==1">
					<input type="radio" name="productVOList[<s:property value="#stat.index"/>].cooperateMode"  value="1" checked="checked">激活
				</s:if>
				<s:if test="cooperateMode==2">
					<input type="radio" name="productVOList[<s:property value="#stat.index"/>].cooperateMode" value="2" checked="checked">分成
				</s:if>
			</td>
			<td>客户</td>		
			<td >
				<input type="text" class="input-small" style="background-color: #DDDDDD" name="productVOList[<s:property value="#stat.index"/>].cuBuildInRegisterPrice" readonly="readonly" value="<s:property value="cuBuildInRegisterPrice"/>" size="5" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/> 分
			</td>
			<td >
				<input type="text" class="input-small" style="background-color: #DDDDDD" name="productVOList[<s:property value="#stat.index"/>].cuUninstallRegisterPrice" readonly="readonly" value="<s:property value="cuUninstallRegisterPrice"/>" size="5" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/> 分
			</td>
			<td >
				<input type="text" class="input-small" id="cuDividePercent_<s:property value="id"/>"  name="productVOList[<s:property value="#stat.index"/>].cuDividePercent" value="<s:property value="cuDividePercent"/>" size="5" onblur="changeBdPrice(3,'<s:property value="id"/>',1);" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" />%&nbsp;比例范围：(<font color="red">0-<s:property value="cuDividePercent"/></font>)
			</td>
		</tr>
		<tr>
			<td>商务拓展人员</td>
			<td >
				<input type="text" class="input-small" id="bdBuildInRegisterPrice_<s:property value="id"/>" style="background-color: #DDDDDD" name="productVOList[<s:property value="#stat.index"/>].bdBuildInRegisterPrice" readonly="readonly" value="<s:property value="bdBuildInRegisterPrice"/>" size="5" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/> 分
			</td>
			<td >
				<input type="text" class="input-small" id="bdUninstallRegisterPrice_<s:property value="id"/>" style="background-color: #DDDDDD" name="productVOList[<s:property value="#stat.index"/>].bdUninstallRegisterPrice" readonly="readonly" value="<s:property value="bdUninstallRegisterPrice"/>" size="5" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/> 分	
			</td>
			<td >
				<input type="text" class="input-small" id="bdDividePercent_<s:property value="id"/>"  name="productVOList[<s:property value="#stat.index"/>].bdDividePercent"  value="<s:property value="bdDividePercent"/>" size="5" onblur="changeBdPrice(3,'<s:property value="id"/>',2);" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" />%&nbsp;比例范围：(<font color="red">0-<s:property value="bdDividePercent"/></font>)
			</td>
		</tr>
		<s:if test="projectVO.bhId>0">
		<tr>
			<td>商务协助人员</td>
			<td >
				<input type="text" class="input-small" id="bhBuildInRegisterPrice_<s:property value="id"/>" name="productVOList[<s:property value="#stat.index"/>].bhBuildInRegisterPrice" value="0" size="5" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" onblur="changeBdPrice(1,'<s:property value="id"/>',0);"/> 分
			</td>
			<td >
				<input type="text" class="input-small" id="bhUninstallRegisterPrice_<s:property value="id"/>" name="productVOList[<s:property value="#stat.index"/>].bhUninstallRegisterPrice" value="0" size="5" onkeyup="this.value=this.value.replace(/[^\d]/g,'');" onblur="changeBdPrice(2,'<s:property value="id"/>',0);"/> 分	
			</td>
			<td >
				<input type="text" class="input-small" id="bhDividePercent_<s:property value="id"/>" name="productVOList[<s:property value="#stat.index"/>].bhDividePercent" value="0" size="5" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');" onblur="changeBdPrice(3,'<s:property value="id"/>',3);"/>%&nbsp;比例范围：(<font color="red">0-<s:property value="bdDividePercent"/> 客户，商务，协助人员总和不超过：<s:property value="bdDividePercent+cuDividePercent"/>)
			</td>
		</tr>
		</s:if>
		</s:iterator>
		<tr >
		    <td  colspan="4">
			<div align="center">				
			   <input id="btn" class="btn btn-primary" type="button" value=" 修 改 " onclick="projectSave();"/>&nbsp;&nbsp;
		    </div>
			</td>
		</tr>
	</table>
<script type="text/javascript">
var productArray = new Array();
<s:iterator value="#Policy.productList" status="stat">
var str = new Array();
str.push('<s:property value="id"/>');
str.push('<s:property value="name" escape="false"/>');
productArray.push(str);
</s:iterator>

var existProductArray = new Array();
<s:iterator value="existProductVOList" status="stat">
var str = new Array();
str.push('<s:property value="id"/>');
str.push('<s:property value="bhBuildInRegisterPrice"/>');
str.push('<s:property value="bhUninstallRegisterPrice"/>');
str.push('<s:property value="bhDividePercent"/>');
str.push('<s:property value="bdBuildInRegisterPrice"/>');
str.push('<s:property value="bdUninstallRegisterPrice"/>');
str.push('<s:property value="bdDividePercent"/>');
existProductArray.push(str);
</s:iterator>
setCheckedProduct();
function setCheckedProduct() {
	var productIds = '<s:property value="projectVO.productIds"/>';
	var productIdArray = productIds.split(",");
	var appendHtml = "";
	for(var i in productIdArray) {
		for(var j in productArray) {
			if(productArray[j][0]==productIdArray[i]) {
				appendHtml+="<div style=\"width: 150px; float: left\">";
         		appendHtml+="<font color=\"blue\">";
         		appendHtml+=productArray[j][1];
         		appendHtml+="</font>";
				appendHtml+="</div>";
				if((i+1)%3==0) {
					appendHtml+="</br>";
				}
			}
		}		
	}
	$("#inData").html(appendHtml);
}


function projectSave(){
	var argus = "";
	var name = $("#name").val();
	var id = $("#projectId").val();
	var schemeName = $("#schemeName").val();
	var agentId = $("#agentId").val();
	var bdId = $("#bdId").val();
	var customerId = $("#customerId").val();
	var osId = $("#osId").val();
	var screenId = $("#screenId").val();
	var productIds = $("#productIds").val();
	var size = productIds.split(",").length-1;
	var intro = $("#intro").val();
	var bhId = $("#bhId").val();
	var cooperateType = $("#cooperateType").val();
	var populariseType = $("#populariseType").val();
	var gameName = $("#gameName").val();
	var feeName = $("#feeName").val();
	var type = $("#type").val();
	argus += "&projectVO.id=";
	argus += id;
	argus += "&projectVO.name=";
	argus += name;
	argus += "&projectVO.schemeName=";
	argus += schemeName;
	argus += "&projectVO.agentId=";
	argus += agentId;
	argus += "&projectVO.bdId=";
	argus += bdId;
	argus += "&projectVO.bhId=";
	argus += bhId;
	argus += "&projectVO.customerId=";
	argus += customerId;
	argus += "&projectVO.osId=";
	argus += osId;
	argus += "&projectVO.screenId=";
	argus += screenId;
	argus += "&projectVO.cooperateType=";
	argus += cooperateType;
	argus += "&projectVO.populariseType=";
	argus += populariseType;
	argus += "&projectVO.productIds=";
	argus += productIds;
	argus += "&projectVO.intro=";
	argus += intro;
	argus += "&projectVO.price=";
	argus += price;
	argus += "&projectVO.gameName=";
	argus += gameName;
	argus += "&projectVO.feeName=";
	argus += feeName;
	argus += "&projectVO.type=";
	argus += type;
	argus += "&projectVO.price=0";
	for(var i=0;i<size;i++) {
		var cooperateMode = $('input:radio[name="productVOList['+i+'].cooperateMode"]:checked').val();
		argus += "&projectVO.productVOList[";
		argus +=i;
		argus += "].cooperateMode=";
		argus += cooperateMode;
		var bdBuildInRegisterPrice = jQuery('input[name="productVOList['+i+'].bdBuildInRegisterPrice"]').val();
	   	var cuBuildInRegisterPrice = jQuery('input[name="productVOList['+i+'].cuBuildInRegisterPrice"]').val();
	   	var bdUninstallRegisterPrice = jQuery('input[name="productVOList['+i+'].bdUninstallRegisterPrice"]').val();
	   	var cuUninstallRegisterPrice = jQuery('input[name="productVOList['+i+'].cuUninstallRegisterPrice"]').val();
	   	var bdDividePercent = jQuery('input[name="productVOList['+i+'].bdDividePercent"]').val();
	   	var cuDividePercent = jQuery('input[name="productVOList['+i+'].cuDividePercent"]').val();
	   	var id = jQuery('input[name="productVOList['+i+'].id"]').val();
	   	argus += "&projectVO.productVOList["+i+"].bdBuildInRegisterPrice=";
	   	argus += bdBuildInRegisterPrice;
	   	argus += "&projectVO.productVOList["+i+"].cuBuildInRegisterPrice=";
	   	argus += cuBuildInRegisterPrice;
	   	argus += "&projectVO.productVOList["+i+"].bdUninstallRegisterPrice=";
	   	argus += bdUninstallRegisterPrice;
	   	argus += "&projectVO.productVOList["+i+"].cuUninstallRegisterPrice=";
	   	argus += cuUninstallRegisterPrice;
	   	argus += "&projectVO.productVOList["+i+"].bdDividePercent=";
	   	argus += bdDividePercent;
	   	argus += "&projectVO.productVOList["+i+"].cuDividePercent=";
	   	argus += cuDividePercent;	
	   	argus += "&projectVO.productVOList["+i+"].id=";
	   	argus += id;   
	   	if(bhId>0) {
			var bhBuildInRegisterPrice = jQuery('input[name="productVOList['+i+'].bhBuildInRegisterPrice"]').val();
		   	var bhUninstallRegisterPrice = jQuery('input[name="productVOList['+i+'].bhUninstallRegisterPrice"]').val();
		   	var bhDividePercent = jQuery('input[name="productVOList['+i+'].bhDividePercent"]').val();
   			argus += "&projectVO.productVOList["+i+"].bhBuildInRegisterPrice=";
	   		argus += bhBuildInRegisterPrice;
	   		argus += "&projectVO.productVOList["+i+"].bhUninstallRegisterPrice=";
	   		argus += bhUninstallRegisterPrice;
	   		argus += "&projectVO.productVOList["+i+"].bhDividePercent=";
	   		argus += bhDividePercent;
		}		
	}	
	argus += "&projectVO.status=";
   	argus += 1;
   	argus += "&projectVO.priceStatus=";
   	argus += 0;
	var queryType = $("#queryType").val();
	var url = "<%=request.getContextPath()%>/Project!update.action";
	jQuery.post(url, argus, function(response){
    		if(jQuery.trim(response) == "-1"){
				showErrorToastMiddle("系统出错，请重试或联系管理员");
    		} else {
    			showInfoToastMiddle(response);
    			var url = "<%=request.getContextPath()%>/Project!query.action";
    			jQuery.post(url,{"projectVO.queryType":queryType},function(data) {
    				 $("#containerData").empty().html(data);
    			});		
    		}
	});	
}


function changeBdPrice(type,id,ptype) {
	var bhId = $("#bhId").val();
	if(type==1) {
		var bhBuildInRegisterPrice = parseInt(jQuery('#bhBuildInRegisterPrice_'+id).val());
		var bdInPrice = parseInt(jQuery('#bdInPrice_'+id).val());
		if(bhBuildInRegisterPrice==""||isNaN(bhBuildInRegisterPrice)) {
			jQuery('#bhBuildInRegisterPrice_'+id).val(0);
		}
		bhBuildInRegisterPrice = parseInt(jQuery('#bhBuildInRegisterPrice_'+id).val());
		bdInPrice = parseInt(jQuery('#bdInPrice_'+id).val());
		if(bhBuildInRegisterPrice>bdInPrice) {
			jQuery('#bhBuildInRegisterPrice_'+id).val(0);
			jQuery('#bdBuildInRegisterPrice_'+id).val(bdInPrice);			
		} else {
			jQuery('#bdBuildInRegisterPrice_'+id).val(bdInPrice-bhBuildInRegisterPrice);			
		}
		
	} else if(type==2) {
		var bhUninstallRegisterPrice = parseInt(jQuery('#bhUninstallRegisterPrice_'+id).val());
		var bdOutPrice = parseInt(jQuery('#bdOutPrice_'+id).val());
		if(bhUninstallRegisterPrice==""||isNaN(bhUninstallRegisterPrice)) {
			jQuery('#bhUninstallRegisterPrice_'+id).val(0);
		}
		bhUninstallRegisterPrice = parseInt(jQuery('#bhUninstallRegisterPrice_'+id).val());
		bdOutPrice = parseInt(jQuery('#bdOutPrice_'+id).val());
		if(bhUninstallRegisterPrice>bdOutPrice) {
			jQuery('#bhUninstallRegisterPrice_'+id).val(0);
			jQuery('#bdUninstallRegisterPrice_'+id).val(bdOutPrice);
		} else { 
			jQuery('#bdUninstallRegisterPrice_'+id).val(bdOutPrice-bhUninstallRegisterPrice);
		}
	} else if(type==3) {
		//中间人分成比例
		//默认商务和客户分成
		var bdPercent = parseFloat(jQuery('#bdPercent_'+id).val());
 		var cuPercent = parseFloat(jQuery('#cuPercent_'+id).val());
 		var bhDividePercent = parseFloat(0.0);
 		var bdDividePercent = parseFloat(jQuery('#bdDividePercent_'+id).val());
		var cuDividePercent = parseFloat(jQuery('#cuDividePercent_'+id).val());		
		if(ptype==1) {
			if(isNaN(cuDividePercent)) {
				jQuery('#cuDividePercent_'+id).val(cuPercent);
			}
			if(cuDividePercent>parseFloat(cuPercent)||cuDividePercent<parseFloat(0)) {
 				jQuery('#cuDividePercent_'+id).val(cuPercent);
 			}
		} else if(ptype==2) {
			if(isNaN(bdDividePercent)) {
				jQuery('#bdDividePercent_'+id).val(bdPercent);
			}
			if(bdDividePercent>parseFloat(bdPercent)||bdDividePercent<parseFloat(0)) {
 				jQuery('#bdDividePercent_'+id).val(bdPercent);
 			}
 			bdDividePercent = parseFloat(jQuery('#bdDividePercent_'+id).val());
 			if(bhId>0) {
				jQuery('#bhDividePercent_'+id).val(bdPercent-bdDividePercent);
 			}
		} else if(ptype==3) {
			bhDividePercent = parseFloat(jQuery('#bhDividePercent_'+id).val());			
	   		if(isNaN(bhDividePercent)) {
				jQuery('#bhDividePercent_'+id).val(0.0);
			}
			if(bhDividePercent>parseFloat(bdPercent)||bhDividePercent<parseFloat(0)) {
 				jQuery('#bhDividePercent_'+id).val(0.0);
 			}
 			bhDividePercent = parseFloat(jQuery('#bhDividePercent_'+id).val());
 			jQuery('#bdDividePercent_'+id).val(bdPercent-bhDividePercent);
		}
	}
}
setExistProduct();
function setExistProduct() {
	for(var i in productArray) {
		for(var j in existProductArray) {
		 	var id = existProductArray[j][0];			
			if(existProductArray[j][0]==productArray[j][0]) {
				jQuery('#bhBuildInRegisterPrice_'+id).val(existProductArray[j][1]);
				jQuery('#bhUninstallRegisterPrice_'+id).val(existProductArray[j][2]);
				jQuery('#bhDividePercent_'+id).val(existProductArray[j][3]);
				jQuery('#bdBuildInRegisterPrice_'+id).val(existProductArray[j][4]);
				jQuery('#bdUninstallRegisterPrice_'+id).val(existProductArray[j][5]);
				jQuery('#bdDividePercent_'+id).val(existProductArray[j][6]);
			}
		}
	}
}
</script>