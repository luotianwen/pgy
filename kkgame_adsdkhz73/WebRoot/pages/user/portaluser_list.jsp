<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Untitled Page</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css> 
</STYLE>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
<link href="<%=request.getContextPath()%>/css/kkfun.css"
  rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/common.js"></script>

</HEAD>
<BODY style="	margin-left: 3px;margin-top: 0px;margin-right: 3px;margin-bottom: 0px;">
<s:form id="portalUserForm" action="Technician!save.action" onsubmit="return VerifyData();" theme="simple">
<s:hidden id="id" name="portalUserVO.id"/>
<s:hidden name="portalUserVO.roleType"></s:hidden>


 <table class=tb_searchbar cellSpacing=1 width="100%">
		<TBODY>
			<TR> <TD colSpan=6 class="td_head"><IMG src="<%=request.getContextPath()%>/images/search1.gif" width="13" height="13" class=icon> 请填写查询条件 </TD>     
					                </TR>
		<TR><TD noWrap class="td_title">编号</TD>
		<TD class="detail"><INPUT id="searchId" name="portalUserVO.searchId"  value="<s:property value="portalUserVO.searchId"/>" style="WIDTH: 200px"  onkeyup="this.value=this.value.replace(/[^\d]/g,'');"> </TD>
		<TD noWrap class="td_title">登陆账号</TD>
		<TD  class="td_detail"><INPUT id="searchName" name="portalUserVO.searchName" value="<s:property value="portalUserVO.searchName"/>" style="WIDTH: 200px" ></TD>		
		</TR>
	 <TR align="center">
    <TD colspan="4" noWrap align="right"><span style="float:left;PADDING-LEFT: 10px;"><INPUT class=button type=button onclick="create()" value=新增技术人员 name=Submit52272></span>
	<span style="float:right;PADDING-RIGHT: 10px;"><INPUT class=button type=button onclick="searchList()" value=查询 name=Submit52272>
    <INPUT class=button type=button onclick="removeall()" value=清空 name=Submit52273></span></TD>
	</TR></TBODY></table>
  <div style="padding-bottom: 5px;"></div>
 <table width="100%" style="WIDTH: 100%; BORDER-COLLAPSE: collapse"  cellSpacing=0 rules=all  border=0  class="tb_datalist">
		<tr  align=middle>
			<td width="10%" class="td_title" nowrap="nowrap"><span id="id">编号</span></td>
			<td width="20%" class="td_title" nowrap="nowrap"><span id="loginId">登陆账号</span></td>
			<td width="20%" class="td_title" nowrap="nowrap"><span id="passwd">登陆密码</span></td>
			<td width="20%"  class="td_title" nowrap="nowrap"><span id="status">帐号状态</span></td>
			<td width="20%" class="td_title" nowrap="nowrap"><span id="lastLogin">最后登录</span></td>
			<td width="10%" class="td_title"  align="center" nowrap="nowrap">操作</td>
		</tr>

		<s:iterator value="portalUserList" status="stat"  >
			<tr onmouseover="javascript:changeBgcolor(this, '#E8F7F7')"
				onmouseout="javascript:changeBgcolor(this, '#FFFFFF')">
				<td class=td_detail><s:property value="id"/></td>	
				<td class=td_detail><s:property value="loginId"/></td>	
				<td  class=td_detail><s:property value="passwd"/></td>	
				<td class=td_detail>
					<s:if test="status==0">正常 </s:if> 
					<s:elseif test="status == 1"><font color="blue">受限</font></s:elseif>
					<s:elseif test="status == 2"><font color="red">锁定</font></s:elseif></td>
				<td class=td_detail><s:property value="lastLogin"/></td>								
				<td class=td_detail align="center">
				<a href="javascript:modify('<s:property value="id"/>')">修改</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:remove('<s:property value="id"/>','<s:property value="loginId"/>')">删除</a>	
				</td>
			</tr>
		</s:iterator>

	</table>
   <!--        <div class="pagelist" id="pagelist1" align="right"><page:pagination formName="portalUserForm" property="portalUserVO.page"
    operation="Technician!list.action"/></div>
-->  
</s:form>
</BODY>

<script type="text/javascript">

<s:if test="!errorMsg.equals('') && errorMsg != null">
alert('<s:property value="errorMsg" escape="false"/>');
<s:set name="errorMsg"  value="" />
</s:if>

if(document.getElementById("searchId").value == 0){
	document.getElementById("searchId").value ='';
}


var myForm = document.getElementById("portalUserForm");

function  removeall(){
	myForm["portalUserVO.searchId"].value="";
	myForm["portalUserVO.searchLoginId"].value="";
	document.all("searchId").value = "";
	document.all("searchLoginId").value = "";
}

function searchList(){
	if(myForm["portalUserVO.searchId"].value==""){
		myForm["portalUserVO.searchId"].value=0;
		}
	myForm.action = "Technician!list.action";
	myForm.submit(); 
}

function create(){
	myForm.action = "Technician!create.action";
	myForm.submit(); 
}

function modify(id ){
		myForm["portalUserVO.id"].value = id ;
		myForm.action = "Technician!modify.action";
		myForm.submit(); 
}
	
function remove(id,loginId){
		if(!sure('确定要删除 ['+loginId+'] 吗？')){
            return;
    	}	
		myForm["portalUserVO.id"].value = id ;		
		myForm.action = "Technician!remove.action";
		myForm.submit(); 
}
</script>
</HTML>
