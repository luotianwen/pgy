<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="bugVO.type==3">
	<script type="text/javascript">
		var _parentWin = window.parent ;
		var id = '<s:property value="bugVO.id"/>';
		_parentWin.dealDelete(id);
	</script>
</s:if>
<s:else>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled page</title>
<!-- CP查看详情页面 -->
<link href="<%=request.getContextPath() %>/css/pkig.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.3.2.js" language="javascript"></script>
</head>
<body style="margin-left: 3px;margin-top: 0px;margin-right: 3px;margin-bottom: 0px;">
	<s:form id="cpForm" action="data/Product!create.action" onsubmit="return VerifyData(this);">
		<s:hidden name="productVO.id" id="productId"></s:hidden>
		<s:hidden name="productVO.type" id="type"></s:hidden>
		<table align="center" cellpadding="0" cellspacing="0" id="tb_content">
			<tbody>
				<tr>
					<td width="56%" nowrap>
					<div class="div_subtitle">当前位置<span class="arrow_subtitle">&gt;</span>系统资源管理
						<span class="arrow_subtitle">&gt;</span>添加产品
					</div>
					</td>
					<td width="44%" align="right" valign="bottom" nowrap="nowrap">&nbsp;</td>
				</tr>
			</tbody>			
		</table>
		<table   width="100%"  border="0" cellpadding="2" cellspacing="1" class="tb_input" style="width: 100%">
		<tr>
            <td nowrap="nowrap" width="15%" class="td_title">产品ID</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:property value="productVO.id"/>
            </td> 
		</tr>		
        <tr>
            <td nowrap="nowrap" width="15%" class="td_title">产品名称</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:property value="productVO.name"/>
            </td> 
		</tr>
		<tr>
			<td nowrap="nowrap" width="15%" class="td_title">可选合作方式</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:if test="cooperateMode==0">分成/激活</s:if>
				<s:elseif test="cooperateMode==1">激活</s:elseif>
				<s:elseif test="cooperateMode==2">分成</s:elseif>
            </td> 
        </tr>
        <tr>
            <td nowrap="nowrap" width="15%" class="td_title">商务不可卸载激活单价</td>
            <td width="35%" nowrap="nowrap"  >
            	<s:text name="global.format.number"><s:param value="bdBuildInRegisterPrice/100.0"/></s:text>
            </td> 
		</tr>
		<tr>
			<td nowrap="nowrap" width="15%" class="td_title">客户不可卸载激活单价</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:text name="global.format.number"><s:param value="cuBuildInRegisterPrice/100.0"/></s:text>
            </td>	
        </tr>
        <tr>
			<td nowrap="nowrap" width="15%" class="td_title">商务可卸载激活单价</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:text name="global.format.number"><s:param value="bdUninstallRegisterPrice/100.0"/></s:text>
            </td>	
        </tr>
        <tr>
			<td nowrap="nowrap" width="15%" class="td_title">客户可卸载激活单价</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:text name="global.format.number"><s:param value="cuUninstallRegisterPrice/100.0"/></s:text>
            </td>	
        </tr>
        <tr>
			<td nowrap="nowrap" width="15%" class="td_title">商务分成比例</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:property value="bdDividePercent"/>%
            </td>	
        </tr>
        <tr>
			<td nowrap="nowrap" width="15%" class="td_title">客户分成比例</td>			 
            <td width="35%" nowrap="nowrap"  >
            	<s:property value="cuDividePercent"/>%
            </td>	
        </tr>
	</table>
	</s:form>
</body>
<script type="text/javascript">
	function clickNext(){
		if(window.VerifyData) {
			return true;
		} else {
			alert("页面信息未完全载入,请稍后!");
			return false;
		}
	}
	
	function VerifyData(form) {	
		$('#isDeal').val("1");
		return true;
	}
</script>
</html>
</s:else>