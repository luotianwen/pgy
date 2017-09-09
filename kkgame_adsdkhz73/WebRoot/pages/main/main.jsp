<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.kkgame.hz.entities.PortalUserVO"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<%
PortalUserVO userVO = (PortalUserVO)request.getSession().getAttribute("SESSION_PORTALUSER");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Cloudmob</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A calm, blue sky.">
    <meta name="author" content="Thomas Park">
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootswatch.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/toastr.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/kkgame-hz.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-modal.css" rel="stylesheet" />
     <style type="text/css">	
     .dropdown-menu .divider{
			height:1px;margin:5px 1px !important;
		}	
     </style>

</head>	
<body class="preview" id="top" data-spy="scroll" data-target=".subnav" data-offset="80">
	<script src="<%=request.getContextPath()%>/js/bsa.js"></script>
   	<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">		
		<div class="container">			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	         <span class="icon-bar"></span>
	         <span class="icon-bar"></span>
	         <span class="icon-bar"></span>
	       </a>	
	        <a class="brand" href="#">Cloudmob</a>
	       	<div class="nav-collapse collapse" id="main-menu">
	        <ul class="nav" id="main-menu-left">
          		<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Login!firstPage.action');">Dashboard</a></li>
	          	<role:equal type="MG,AG,SP,BD">
	          	<li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">商务管理中心<b class="caret"></b></a>
	            <ul class="dropdown-menu" id="swatch-menu">
              	<role:equal type="MG,SP">
              		<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Agent!query.action');">代理商管理</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="MG,AG,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Bd!query.action');">商务拓展人员管理</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="MG,SP,BD">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Bh!query.action');">商务协助人员管理</a></li>
	              	<li class="divider"></li>
              	</role:equal>
			  	<role:equal type="SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Technician!query.action');">技术人员管理</a></li>
              	</role:equal>	
           		</ul>
	          	</li>
	          	</role:equal>
	          	<role:equal type="MG,AG,BD,SP">
              	<li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">客户协调中心<b class="caret"></b></a>
	            <ul class="dropdown-menu" id="swatch-menu">
              	<role:equal type="MG,AG,BD,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/pages/user/customer/rule_info.html');">客户协调规则说明</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="MG,AG,BD,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!create.action');">创建客户</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              		<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=0');">新建的客户</a></li>
              		<li class="divider"></li>
              		<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=1');">待审核客户</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=2');">拓展中客户</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=4');">已合作客户</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=3');">审核未通过客户</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.relayStatus=6');">被收回的客户</a></li>
	              	<li class="divider"></li>
              	<role:equal type="MG,AG,BD,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=7&customerVO.expiryDate=45');">45天过期客户</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=7&customerVO.expiryDate=60');">60天过期客户</a></li>
               	  	<li class="divider"></li>
           	  	</role:equal>
           	  	<role:equal type="BD">
	             	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=9');">闲置的客户</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="MG,AG,BD,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.relayStatus=10');">移交中客户</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="BD,AG">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!query.action?customerVO.status=90');">自有客户列表</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="MG,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Customer!allQuery.action');">所有客户列表</a></li>
              	</role:equal>
	            </ul>
	          	</li>
	          	</role:equal>

				<role:equal type="BD,BH,TC,MG,AG,SP">
					<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">订阅项目管理<b class="caret"></b></a>
					<ul class="dropdown-menu" id="swatch-menu">
					<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!subscribeQuery.action');">订阅项目管理</a></li>
					<li class="divider"></li>
					<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!subscribeCreate.action');">创建项目项目</a></li>

					</ul>
					</li>
				</role:equal>
	          	<role:equal type="BD,BH,TC,MG,AG,SP">
	        	<li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">项目管理中心<b class="caret"></b></a>
	            <ul class="dropdown-menu" id="swatch-menu">
              	<role:equal type="BD,BH,MG,TC,AG,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=1&projectVO.isDevCustomer=2');">项目总表</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="BD,MG,AG,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!create.action');">创建项目</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=2');">新建的项目</a></li>
	              	<li class="divider"></li>
              	</role:equal>
				<role:equal type="BH,SP">
					<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!statQuery.action?queryType=1');">商务出包统计</a></li>
					<li class="divider"></li>
				</role:equal>
              	<role:equal type="BD,TC,MG,AG,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=3');">待技术审核的项目</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=4');">待出包的项目</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=5');">已出包的项目</a></li>
	              	<li class="divider"></li>

	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=6');">测试通过的项目</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=7');">测试未通过项目</a></li>
	              	<li class="divider"></li>
              	</role:equal>
              	<role:equal type="BD,MG,AG,SP">
	             	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=8');">待价格审核的项目</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=9');">审核通过的项目</a></li>
	              	<li class="divider"></li>
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Project!query.action?projectVO.queryType=10');">审核未通过的项目</a></li>
              	</role:equal>
	            </ul>
	         	</li>
	         	</role:equal>
	         	<role:equal type="DT,SP,BD">
	         	<li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">系统资源管理<b class="caret"></b></a>
	            <ul class="dropdown-menu" id="swatch-menu">
	              <role:equal type="DT,SP">
	              	<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/OsAdapter!list.action');">平台管理</a></li>
	              	<li class="divider"></li>
	              </role:equal>
	              <role:equal type="DT,SP,BD,BH">
					  <li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Product!query.action');">产品管理</a></li>
					  <li class="divider"></li>
					  <li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/Product!subscribeQuery.action');">订阅产品管理</a></li>
					  <li class="divider"></li>
	              </role:equal>
	            </ul>
	         	</li>
	         	</role:equal>
	         	<role:equal type="CM,AG,BD,BH,SP">
	         	<li class="dropdown">
		            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Reports<b class="caret"></b></a>
		            <ul class="dropdown-menu" id="swatch-menu">
		              <li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/UserRegister!getUserRegisterQuery.action?queryType=1');">day Report</a></li>
		              <li class="divider"></li>
		              <role:equal type="AG,BD,BH,SP">
		              <li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/UserRegister!getUserRegisterQuery.action?queryType=2');">month Report</a></li>
		              <li class="divider"></li>
		              </role:equal>
		             <li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/DdlData!query.action?queryType=1');">DDL Report</a></li>
		              <li class="divider"></li>
					<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/UserDivide!specialsubscribeQuery.action?queryType=1');">Subscribe Report</a></li>
					<li class="divider"></li>
		            </ul>
		         </li>
		         </role:equal>
	         </ul>
	        <ul class="nav pull-right" id="main-menu-right">
	          <li><a rel="tooltip" href="javascript: mainLoadData('<%=request.getContextPath()%>/Login!goModifyPasswd.action');">Change Password</a></li>
	          <li><a rel="tooltip"  href="<%=request.getContextPath()%>/Login!logout.action" >Logout</a></li>
	        </ul>
	       </div>
	     </div>
	   </div>
	 </div>	
	 <div class="container" id="containerData">
	 </div><!-- /container -->
   	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
   	<script src="<%=request.getContextPath()%>/js/jquery.smooth-scroll.min.js"></script>
   	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
   	<script src="<%=request.getContextPath()%>/js/bootswatch.js"></script>
   	<script src="<%=request.getContextPath()%>/js/bootstrap-modal.js"></script>
   	<script src="<%=request.getContextPath()%>/js/bootstrap-modalmanager.js"></script>
   	<script src="<%=request.getContextPath()%>/js/kkgame-hz.js"></script>
   	<script src="<%=request.getContextPath()%>/js/toastr.js"></script>  
	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
   	<script type="text/javascript">
	var agent = navigator.userAgent;
 		if(agent.indexOf('MSIE 7.0') >= 0||agent.indexOf('MSIE 8.0') >= 0) {
			$("#containerData").css({"padding-top":"90px"});
		}  		
	<role:equal type="BD,MG,AG,SP">
		goFirstPage();   		
	</role:equal>
   	</script>
   </body>
</html>
