<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<style>
<!--

-->
</style>
<div class="row">
  	<div class="span12">
  		<ul class="breadcrumb">
      	<li>当前位置 <span class="divider">/</span></li>
      	<li>项目中心<span class="divider">/</span></li>
      	<li class="active">项目详情</li>
   		</ul>
   	</div>
</div>
<s:form id="projectForm" action="Project2!list.action" theme="simple" >
	<s:hidden id="id" name="projectVO.id"></s:hidden>
	<s:hidden id="status" name="projectVO.status"></s:hidden>
	<s:hidden id="priceStatus" name="projectVO.priceStatus"></s:hidden>
	<s:hidden id="fileId" name="projectFileVO.id"></s:hidden>
	<s:hidden id="projectId" name="projectFileVO.projectId"></s:hidden>
	<s:hidden id="filePath" name="projectFileVO.filePath"></s:hidden>
	<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
	
	<table class="table">		
		<tr>
			<td class="table_td_title">项目名称-ID</td>			 
            <td>
            	<s:property value="projectVO.name"/>&nbsp;&nbsp;-&nbsp;&nbsp;P.[<font color="red"><s:property value="projectVO.id"/></font>]
            </td>
            <td class="table_td_title">渠道商名称</td>			 
            <td><s:property value="projectVO.schemeName"/></td>
		</tr>	
		<tr >
			<td class="table_td_title">所属代理商</td>	
			<td width="35%"  nowrap="nowrap" >
				<s:iterator value="#Policy.agentList">
					<s:if test="id==projectVO.agentId">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">客户</td>	
			<td width="35%"  nowrap="nowrap">
				<s:iterator value="#Policy.customerList">
					<s:if test="projectVO.customerId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>		
		</tr>
		<tr ><td class="table_td_title">商务拓展人员</td>	
			<td width="35%"  nowrap="nowrap" >
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td><td class="table_td_title">商务协助人员</td>	
			<td width="35%"  nowrap="nowrap" >
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>			
		
		<tr >
			<td class="table_td_title">项目类型</td>			 
            <td   colspan="3">
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
			<td class="table_td_title">平台</td>			 
            <td >
            	<s:iterator value="#Policy.osList">
					<s:if test="projectVO.osId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">分辨率</td>			 
            <td>
            	<s:iterator value="#Policy.screenList">
					<s:if test="projectVO.screenId==key">
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
            <td class="table_td_title">SDK名称<font color="red">*</font></td>			 
            <td><s:property value="projectVO.feeName"/></td>
		</tr>	
		<tr >
			<td class="table_td_title">创建时间</td>			 
            <td   colspan="3">
            	<s:property value="projectVO.createTime"/>
			</td>
		</tr>		
		
		<tr >
			<td class="table_td_title">备注</td>			 
            <td   colspan="3"><textarea cols="50" rows="5" ><s:property value="projectVO.intro"/></textarea></td>
		</tr>
		<tr >
			<td class="table_td_title">项目状态</td>			 
            <td   colspan="3">
            	<s:if test="projectVO.status==1">新建</s:if>
				<s:if test="projectVO.status==2">待技术审核</s:if>
				<s:if test="projectVO.status==3">待出包</s:if>
				<s:if test="projectVO.status==4">已出包</s:if>
				<s:if test="projectVO.status==5">测试通过</s:if>
				<s:if test="projectVO.status==6">技术参数错误</s:if>
				<s:if test="projectVO.status==7">技术审核未通过</s:if>
            </td>
		</tr>
		<s:if test="projectVO.status==7">
		<tr >
			<td class="table_td_title">技术审核未通过原因</td>
		    <td  colspan="3">
		    <textarea cols="50" rows="5" ><s:property value="projectVO.confirmText"/></textarea>		    
			
			</td>
		</tr>
		</s:if>
		<s:if test="projectVO.status==6">
		<tr class="table_td_title">
			<td >技术测试未通过原因</td>
		    <td  colspan="3">
		    <textarea cols="50" rows="5" ><s:property value="projectVO.testText"/></textarea>		    
			
			</td>
		</tr>
		</s:if>
		<tr >
			<td class="table_td_title">价格审核</td>			 
            <td   colspan="3">
            	<s:if test="projectVO.priceStatus==0">未提交审核</s:if>
            	<s:if test="projectVO.priceStatus==1">待价格审核</s:if>
				<s:if test="projectVO.priceStatus==2">审核通过</s:if>
				<s:if test="projectVO.priceStatus==3">审核未通过</s:if>
            </td>
		</tr>
		<s:if test="projectVO.priceStatus==3">
		<tr >
			<td class="table_td_title">价格审核未通过原因</td>
		    <td  colspan="3">
		    <textarea cols="50" rows="5" ><s:property value="projectVO.priceText"/></textarea>		    
			
			</td>
		</tr>
		</s:if>
	</table>
	<div style="padding-bottom: 5px"></div>
	<table class="table">
		<tr >
			<td class="table_td_title">产品</td>
			<td class="table_td_title">合作模式</td>
			<td class="table_td_title">角色</td>
			<td class="table_td_title">不可卸载激活单价(<font color="red">分</font>)</td>
			<td class="table_td_title">可卸载激活单价(<font color="red">分</font>)</td>	
			<td class="table_td_title">分成比例</td>	
		</tr>
		<s:iterator value="existProductVOList" status="stat">			
		<tr >
			<td rowspan="<s:if test="projectVO.bhId>0">3</s:if><s:else>2</s:else>">
				<s:property value="name"/>
				<input type="hidden" name="productVOList[<s:property value="#stat.index"/>].id" value="<s:property value="id"/>" />
			</td>
			<td rowspan="<s:if test="projectVO.bhId>0">3</s:if><s:else>2</s:else>">
				<s:if test="cooperateMode==1">激活</s:if>
				<s:if test="cooperateMode==2">分成</s:if>
			</td>
			<td>客户</td>		
			<td >
				<s:if test="cooperateMode==1"><s:property value="cuBuildInRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
				
			</td>
			<td>
				<s:if test="cooperateMode==1"><s:property value="cuUninstallRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
			</td>
			<td>				
				<s:if test="cooperateMode==1">-</s:if>
				<s:if test="cooperateMode==2"><s:property value="cuDividePercent"/></s:if>
			</td>
		</tr>
		<tr>
			<td>商务拓展人员</td>
			<td>
				<s:if test="cooperateMode==1"><s:property value="bdBuildInRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
				
			</td>
			<td>
				<s:if test="cooperateMode==1"><s:property value="bdUninstallRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
			</td>
			<td>				
				<s:if test="cooperateMode==1">-</s:if>
				<s:if test="cooperateMode==2"><s:property value="bdDividePercent"/></s:if>
			</td>
		</tr>
		<s:if test="projectVO.bhId>0">
		<tr>
			<td >商务协助人员</td>
			<td >
				<s:if test="cooperateMode==1"><s:property value="bhBuildInRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
				
			</td>
			<td>
				<s:if test="cooperateMode==1"><s:property value="bhUninstallRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
			</td>
			<td>				
				<s:if test="cooperateMode==1">-</s:if>
				<s:if test="cooperateMode==2"><s:property value="bhDividePercent"/></s:if>
			</td>
		</tr>
		</s:if>
		</s:iterator>
	</table>
	<div style="padding-bottom: 5px"></div>
	<s:if test="projectFileVOList.size>0">
		<table class="table">
			<tr>
				<td class="table_td_title">文件名</td>
				<td class="table_td_title">说明</td>
				<td class="table_td_title">发布时间</td>
				<td class="table_td_title">下载次数</td>
				<td class="table_td_title">操作</td>
			</tr>
			<s:iterator value="projectFileVOList">
				<tr>
					<td >
						<s:property value="filePath"/>
					</td>
					<td >
						<s:property value="info"/>
					</td>
					<td >
						<s:property value="createTime"/>
					</td>
					<td >
						<s:property value="times"/>
					</td>
					<td >
						<a class="btn btn-primary" href="javascript: download('<s:property value="id"/>','<s:property value="projectId"/>','<s:property value="filePath"/>')">下载</a>
					</td>
				</tr>
			</s:iterator>
			
		</table>
	</s:if>
	<table class="table">
		<tr>
				<td>
				   <input id="btn2" class="btn btn-primary" type="button" value=" 返 回 " onclick="doBack('<s:property value="projectVO.queryType"/>');"/>&nbsp;&nbsp;
				</td>
			</tr>
	</table>
</s:form>
<script type="text/javascript">
	var myForm = document.getElementById("projectForm");
	function download(id,projectId,filePath) {
		$("#id").val(projectId);
		$("#fileId").val(id);
		$("#projectId").val(projectId);
		$("#filePath").val(filePath);
		myForm.action = "Project!downProject.action";
		myForm.submit();
	}
	
</script>