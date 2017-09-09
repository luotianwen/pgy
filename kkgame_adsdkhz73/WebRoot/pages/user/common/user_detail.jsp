<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:hidden id="id" name="portalUserVO.id" />
   <tr>
    <td class="table_td_title">登录帐号</td>
    <td colspan="3"><s:property value="portalUserVO.loginId"/><s:hidden id ="loginId" name ="portalUserVO.loginId" /></td>  	 
 	 </tr>
 <tr >
	<td class="table_td_title">登录密码</td>
    <td colspan="3"><s:property value="portalUserVO.passwd"/></td>
 </tr>
 <tr >
	<td class="table_td_title">状态</td>	
	<td colspan="3">
	<s:if test="portalUserVO.status==0">正常 </s:if> 
	<s:elseif test="portalUserVO.status == 1"><font color="blue">受限</font></s:elseif>
	<s:elseif test="portalUserVO.status == 2"><font color="red">锁定</font></s:elseif></td>
 </tr>
 <tr >
	<td class="table_td_title">最后登录时间</td>
    <td colspan="3"><s:property value="portalUserVO.lastLogin"/></td>
 </tr>