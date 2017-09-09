<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户授权资源</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="<%=path%>/js/util.js"></script>
    <script type="text/javascript" src="<%=path%>/system/role/js/role.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
	<script type="text/javascript">
		$(function(){
			var ids = '${resIds}';
			var data = ids.toString().split(",");
			for(var i = 0; i < data.length; i = i + 1){
				$("#res_" + data[i]).attr("checked", true);
			}
		});
	</script>
  </head>
  
  <body>
  <form method="post" id="agent_add_form" onsubmit="">
  	<input type="hidden" id="roleid" value="${roleid}"/>
	<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
	  <tr  bgcolor="#EEF4EA">
	    <td  colspan="4" background="images/wbg.gif" class='title'><span>&nbsp;系统管理中心  &gt;&gt; 用户资源授权:&nbsp;(&nbsp;<font color='red'>${role.roleDesc}</font>&nbsp;)</span></td>
	  </tr>	  
	  <tr bgcolor="#FFFFFF">
		<td height="100%">
			<div style="margin-left:80px;margin-top:30px;margin-bottom:10px;">
				<c:forEach items="${resList}" var="o">
					<span style="width:200px;float:left;width:90%;margin-top:10px;">
						<input type="checkbox" name="" id="res_${o.id}" value="${o.id}" onclick="javascript:setRes('<%=path%>', '${o.id}', this, 1)"/>
						<!--  -->
						<b>${o.resDesc}</b>
					</span>
					<span style="margin-left:50px;margin-top:10px;float:left">
						<c:forEach items="${o.childList}" var="c">
							<span style="float:left;width:220px;">
							<input type="checkbox" name="" id="res_${c.id}" value="${c.id}" onclick="javascript:setRes('<%=path%>', '${c.id}', this, 2)"
								myattr="my_${o.id}"/>${c.resDesc}
							</span>
						</c:forEach>
					</span>
				</c:forEach>
				<div style="height:50px;float:left;"></div>
			</div>
		</td>
	  </tr>
	  
	  <tr bgcolor="#FFFFFF">
		<td colspan="4" align="center">
			<input type="button" value="返  回" id="backButton" onclick="javascript:window.location='<%=path%>/role.action'"/>
		</td>
	  </tr>
	</table>  
	
   </form> 
  </body>
</html>
