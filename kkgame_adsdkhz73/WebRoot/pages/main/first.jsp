<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="com.kkgame.hz.entities.*" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%
PortalUserVO userVO = (PortalUserVO)request.getSession().getAttribute("SESSION_PORTALUSER");
%>
<style>
<!--
section {
    margin-top: 10px !important;
    padding-top: 10px !important;
}
-->
</style>
<header  id="overview">
<div class="row">
  <div class="span6">
    <h2><%=userVO.getLoginId() %></h2>
    <p class="lead">Welcome to Cloudmob</p>
  </div>		    
</div>
<!-- 
<div class="subnav">
  <ul class="nav nav-pills">
    <li><a href="#registerdata">当月激活数据</a></li>
    <li><a href="#dividedata">当月分成数据</a></li>
  </ul>
</div>
</header>
<section id="registerdata">
<div class="page-header">
   	<h1>当月激活数据</h1>
</div>		  
<img id="registerload" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
<div id="registerloadData"></div>
</section>
<section id="dividedata">	
<div class="page-header">
   	<h1>当月分成数据</h1>
<img id="divideload" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
</div>
 <div id="divideloadData"></div>
</section>
 -->
<script type="text/javascript">
function getData(){
	var url="<%=request.getContextPath()%>/UserRegister!listDay.action";
	$("#registerload").css({"display":""});
	$("#divideload").css({"display":""});
	var now = new Date();       
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();  
	var clock  = year + "-";
	if(month < 10) {
		clock  += "0";
	}
	clock  += month + "-";
	var startTime = clock + "01";	
	if(day < 10) {
		clock  += "0";
	}
	var endTime = clock + day;
	jQuery.post(url,{"billSearchVO.queryType":1,"billSearchVO.startTime":startTime,"billSearchVO.endTime":endTime,"billSearchVO.rowFieldString":'1,'},
		function(response){
			if(response=="1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");
				$("#registerload").css({"display":"none"});
			} else {
				jQuery("#registerloadData").html(jQuery.trim(response));
				$("#registerload").css({"display":"none"});
			}			
	});
	url="<%=request.getContextPath()%>/UserDivide!listDay.action";	
	jQuery.post(url,{"billSearchVO.startTime":startTime,"billSearchVO.endTime":endTime,"billSearchVO.rowFieldString":'1,'},
		function(response){
			if(response=="1") {
				showErrorToast("提示","系统出错，请重试或联系管理员");				
				$("#divideload").css({"display":"none"});
			} else {
				jQuery("#divideloadData").html(jQuery.trim(response));
				$("#divideload").css({"display":"none"});
			}			
	});
}
</script>