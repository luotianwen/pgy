<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
<s:hidden id="projectId" name="projectVO.id"></s:hidden>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">添加项目备注</li>
   		</ul>
   	</div>
</div>

<s:form id="projectForm" method="post" action="Project!modifyCooperateInput.action" theme="simple" enctype="multipart/form-data">
	<table class="table">
		<tr>
			<td class="table_td_title">项目名称-ID<font color="red">*</font></td>			 
            <td>
            	<s:property value="projectVO.name"/>&nbsp;&nbsp;-&nbsp;&nbsp;P.[<font color="red"><s:property value="projectVO.id"/></font>]
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
		<tr >
			<td class="table_td_title">是否去掉LOGO<font color="red">*</font></td>			 
            <td colspan="3">
            	<s:if test="projectVO.isRemoveLogo==0">是</s:if>
            	<s:if test="projectVO.isRemoveLogo==1">否</s:if>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">机型价格</td>			 
            <td colspan="3">
            	<s:iterator value="#Policy.priceList">
					<s:if test="projectVO.price==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>	
		<tr>
			<td class="table_td_title">产品名称<font color="red">*</font></td>			 
            <td>
            	<s:property value="projectVO.gameName"/>
            </td>
            <td class="table_td_title">计费SDK名称<font color="red">*</font></td>			 
            <td><s:property value="projectVO.feeName"/></td>
		</tr>	
		<tr>
			<td class="table_td_title">大小包<font color="red">*</font></td>			 
            <td colspan="3">
            	<s:iterator value="#Oplicy.bigList">
            		<s:if test="key==projectVO.big">
            			<s:property value="value"/>
            		</s:if>
            	</s:iterator>
            	</td>
        </tr>
		<tr >
			<td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea cols="50" rows="5" id="oldIntro"><s:property value="projectVO.intro"/></textarea></td>
		</tr>
		<tr >
				<td class="table_td_title">添加备注</td>			 
	            <td width="85%" nowrap="nowrap"  colspan="3"><textarea id="intro" name="projectVO.intro" cols="50" rows="5" ></textarea></td>
		</tr>
		<tr >
		    <td  colspan="4">
			<div align="center">				
			   <input id="sb" class="btn btn-primary" type="button" value=" 添加备注 " onclick="addInfo();"/>&nbsp;&nbsp;
			   <input id="sb" class="btn" type="button" value=" 返 回 " onclick="doBack('<s:property value="projectVO.queryType"/>');"/>&nbsp;&nbsp;
		    </div>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">	

function addInfo(){	
	var projectId  =$("#projectId").val();
	var oldIntro = $("#oldIntro").val()
	var intro = $("#intro").val()
	var queryType = $("#queryType").val();
	if ( intro ==""){
		showInfoToastMiddle("<font color=\"red\">请输入添加备注！</font>");
		$("#intro").focus();
		return false;
	}	
    var url = "<%=request.getContextPath()%>/Project!saveAddInfo.action";
    jQuery.post(url, {"projectVO.id":projectId,"projectVO.intro":intro,"projectVO.oldIntro":oldIntro
	    }, function(response){
	      if(jQuery.trim(response) == "0"){
				showErrorToastMiddle("系统出错，请重试或联系管理员");
    		} else {    			
    			var url = "<%=request.getContextPath()%>/Project!query.action";
    			jQuery.post(url,{"projectVO.queryType":queryType},function(data) {
    				 $("#containerData").empty().html(data);
    			});			
    		}
   });
}

</script>