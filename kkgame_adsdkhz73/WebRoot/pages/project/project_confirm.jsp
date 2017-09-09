<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<s:hidden id="projectId" name="projectVO.id"></s:hidden>
<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">项目审核</li>
   		</ul>
   	</div>
</div>
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
            	<s:iterator value="#Policy.bigList">
            		<s:if test="key==projectVO.big">
            			<s:property value="value"/>
            		</s:if>
            	</s:iterator>
            	</td>
        </tr>
		<tr >
			<td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea cols="50" rows="5"  id="intro"><s:property value="projectVO.intro"/></textarea></td>
		</tr>
	</table>
	<div style="padding-bottom: 5px"></div>
	<table id="uploadTable" class="table">
		<tr>
			<td class="table_td_title">版本号&nbsp;<font color="red">*</font></td>
			<td>
				<s:select list="#Policy.versionList" listKey="key" listValue="value" id="version" headerKey="0" headerValue="--请选择版本号--"></s:select>
		</tr>
		<tr>
			<td class="table_td_title">审核意见&nbsp;<font color="red">*</font></td>
			<td>
			<input type="radio" name="auditResult" value="Y"> 
				通过 <input type="radio"
					name="auditResult" value="N"> 
				不通过</td>
		</tr>
		
		<tr>
			<td class="table_td_title">不通过原因</td>			 
            <td><textarea id="auditFailReason" name="projectVO.confirmReason" cols="53" rows="5" ></textarea></td>
		</tr>
		<tr >
		    <td  colspan="2">
			<div align="center">
				<input type="button" class="btn btn-primary" id="btn1" onclick="doConfirm();" value=" 提 交 " />&nbsp;
			   <input id="btn2" class="btn" type="button" value=" 返 回 " onclick="doBack('<s:property value="projectVO.queryType"/>');"/>&nbsp;&nbsp;
		    </div>
			</td>
		</tr>
	</table>
	
<script type="text/javascript">
	function doConfirm() {
		var id = $("#projectId").val();
		var auditResult = '';	
	 	var auditFailReason='';
	 	var newStatus=0;
	 	var intro = $("#intro").val();
	 	var queryType=$("#queryType").val();
	 	var version=$("#version").val();
	 	if($("input[name=items]:checked")){ 
          auditResult = $("input[name=auditResult]:checked").val();
	   	}
	   	if(auditResult == 'Y'){
	   		newStatus = 3;
	   	} else if(auditResult == 'N'){
	   		$("#auditFailReason").val($.trim($("#auditFailReason").val()));
		 	auditFailReason = $("#auditFailReason").val();
		 	if(auditFailReason == null || auditFailReason == ''){
		 		showInfoToastMiddle("请填写不通过原因！");
		 		$("#auditFailReason").focus();
		 		return false;
			}
			newStatus=7;
	   	}
	   	jQuery.post("Project!confirm.action",{"projectVO.id":id,
	   	"projectVO.status":newStatus,"projectVO.intro":intro,
	   	"projectVO.confirmText":auditFailReason,
	   	"projectVO.version":version,
	   	"projectVO.queryType":queryType
	   	},function(data){				
			if(data == "0"){					
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				return ;
			} else if(newStatus=="3") {
				//出包
				$('#containerData').html(data);
				return ;
			} else if(newStatus=="7"){
				//加载详情
				$('#containerData').html(data);
				return ;
			}
		});	
	}
</script>	
