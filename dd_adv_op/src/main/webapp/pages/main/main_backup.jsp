<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/permission.tld"  prefix="authz"%>

<%
UserVO userVO = (UserVO)request.getSession().getAttribute("SESSION_USER");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>云视传媒网络计费平台</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/fullcalendar.css" />	
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/unicorn.main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/unicorn.grey.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/toastr.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/select2.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/kkpay.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chosen.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/uniform.css" />
   		<link href="<%=request.getContextPath()%>/css/bootstrap-modal.css" rel="stylesheet" />
	</head>
	<body id="databody">		
		<div id="header">
			<h1><a href="#">云视传媒网络计费平台</a></h1>
		</div>
		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">               
                <li class="btn btn-inverse"><a title="" href="javascript: mainLoadData('<%=request.getContextPath()%>/user/User!modifyPasswd.action',413);"><i class="icon icon-cog"></i> <span class="text">修改密码</span></a></li>
                <li class="btn btn-inverse"><a title="" href="<%=request.getContextPath()%>/user/Login!logout.action"><i class="icon icon-share-alt"></i> <span class="text">退 出</span></a></li>
            </ul>
        </div>            
		<div id="sidebar">
			<a href="#" class="visible-phone"><i class="icon icon-home"></i>云视传媒网络计费平台</a>
			<ul>
				<authz:Permission resName="RES_AGENT">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>代理中心</span> </a>
					<ul>
						<authz:Permission resName="RES_AGENT_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Agent!list.action',511)">代理列表</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_AGENT_INFO">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Agent!detail.action',512)">代理信息</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
				<authz:Permission resName="RES_CUSTOMER">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>客户中心</span> </a>
					<ul>
						<authz:Permission resName="RES_CUSTOMER_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Customer!list.action',111)">客户列表</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_CUSTOMER_INFO">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Customer!detail.action',112)">客户信息</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
				<authz:Permission resName="RES_PRODUCT">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>应用中心</span> </a>
					<ul>
						<authz:Permission resName="RES_PRODUCT_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Product!list.action',113)">应用列表</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
				<authz:Permission resName="RES_SP">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>通道合作中心</span> </a>
					<ul>
						<authz:Permission resName="RES_SP_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/sp/Sp!list.action',211)">通道公司列表</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_SERVICE_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/sp/Service!list.action',213)">业务列表</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_CHANNEL_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/sp/Channel!list.action',212)">代码配置列表</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_OPEN_LIST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/sp/Open!list.action?operator=1',214)">代码开通列表</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
				<authz:Permission resName="RES_STAT">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>数据统计</span></a>
					<ul>
						<authz:Permission resName="RES_STAT_REQUEST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/stat/Request!list.action',611)">计费请求统计</a></li>
						</authz:Permission>
					</ul>
					<ul>
						<authz:Permission resName="RES_STAT_BILLING">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/stat/Billing!list.action',612)">计费统计</a></li>
						</authz:Permission>
					</ul>
					<ul>
						<authz:Permission resName="RES_STAT_PERCENT">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ChannelPercent!query.action',613)">按省转化率</a></li>
						</authz:Permission>
					</ul>
					<ul>
						<authz:Permission resName="RES_STAT_PERCENT">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ChannelDatePercent!query.action',613)">代码转化率</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
				<authz:Permission resName="RES_OP">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>运营报表</span></a>
					<ul>
						<authz:Permission resName="RES_OP_DETAIL">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/Fee!query.action',311)">支付明细</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_OP_REQUEST">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/Request!query.action',314)">支付请求</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_OP_DAY">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/Data!query.action?searchVO.queryType=1',312)">日报表</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_OP_MONTH">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/Data!query.action?searchVO.queryType=2',313)">月报表</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
				<authz:Permission resName="RES_SYSTEM">
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i><span>系统管理</span></a>
					<ul>
						<authz:Permission resName="RES_SYSTEM_ACCOUNT">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/user/User!list.action',411)">用户管理</a></li>
						</authz:Permission>
						<authz:Permission resName="RES_SYSTEM_ROLE">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/user/Role!list.action',421)">角色管理</a></li>
						</authz:Permission>
					  	<authz:Permission resName="RES_SYSTEM_RES">
						<li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/user/Res!list.action',431)">资源管理</a></li>
						</authz:Permission>
					</ul>
				</li>
				</authz:Permission>
			</ul>		
		</div>
		<div id="content">
			<div id="content-header">
				<h1>云视传媒网络计费平台</h1>
			</div>
			<div id="breadcrumb">
				<a href="#" title="首页" class="tip-bottom"><i class="icon-home"></i> 首页</a>
				<a href="#" class="current">欢迎页</a>
			</div>
			<div class="container-fluid" id="dataContent" style="min-height: 570px">
				<div class="row-fluid">
					<div class="span12">
						<div class="alert alert-info">
							<strong><font color="red"><%=userVO.getRealName() %></font></strong>,欢迎您. 
							<a href="#" data-dismiss="alert" class="close">×</a>
						</div>
										
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12" style="min-height:490px">						
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div id="footer" class="span12">
					Copyright&copy; 2011-2013. <a href="http://www.kk-games.com" target="_blank">KOK</a> All rights reserved.
				</div>
			</div>
		</div>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/excanvas.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.ui.custom.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-modalmanager.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-modal.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.flot.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.flot.resize.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.peity.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/fullcalendar.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/unicorn.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/unicorn.dashboard.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/kkpay.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/toastr.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/select2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootbox.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/util.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.chosen.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/unicorn.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/unicorn.form_common.js"></script>  
       	<!-- 
		<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/highcharts.js"></script> 
		<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/exporting.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/no-data-to-display.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/kkgame-chart.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/cached_lay_reports.js"></script> 
		 -->      <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script> 
	</body>
</html>