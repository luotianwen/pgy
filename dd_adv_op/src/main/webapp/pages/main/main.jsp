<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@page import="com.kkgame.feeop.user.bean.UserVO" %>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/permission.tld" prefix="authz" %>

<%
    UserVO userVO = (UserVO) request.getSession().getAttribute("SESSION_USER");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>云视传媒广告平台</title>
    <meta charset="UTF-8"/>
    <!--
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/fullcalendar.css" />	
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/unicorn.main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/unicorn.grey.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/kkpay.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chosen.css" class="skin-color" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/uniform.css" />
   		
   		-->
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- FontAwesome 4.3.0
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
     --><!-- Ionicons 2.0.0
	    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />    
	    --><!-- Theme style -->
    <link href="<%=request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/css/ionicons.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="<%=request.getContextPath() %>/css/skins/skin-blue.css" rel="stylesheet" type="text/css"/>
    <!-- iCheck -->
    <link href="<%=request.getContextPath() %>/css/iCheck/flat/blue.css" rel="stylesheet" type="text/css"/>
    <!-- Morris chart -->
    <link href="<%=request.getContextPath() %>/css/morris/morris.css" rel="stylesheet" type="text/css"/>
    <!-- jvectormap -->
    <link href="<%=request.getContextPath() %>/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet"
          type="text/css"/>
    <!-- Date Picker -->
    <link href="<%=request.getContextPath() %>/css/datepicker/datepicker3.css" rel="stylesheet" type="text/css"/>
    <!-- Daterange picker -->
    <link href="<%=request.getContextPath() %>/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet"
          type="text/css"/>
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="<%=request.getContextPath() %>/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/toastr.css" class="skin-color"/>
    <link href="<%=request.getContextPath()%>/css/bootstrap-modal.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/bootstrap-modal-bs3patch.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/kkpay.css" class="skin-color"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/select2.css" class="skin-color"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body id="databody" class="skin-blue">
<div class="wrapper">
    <header class="main-header">
        <!-- Logo -->
        <% if (userVO.getIsBd() == 0 || userVO.getId() == 23) {
        %>
        <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!dayInfo.action',110)"
           class="logo"><b>云视传媒广告</b>平台</a>
        <%
        } else {%>
        <a href="javascript: void(0)" class="logo"><b>云视传媒广告</b>平台</a>
        <% } %>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">

                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="<%=request.getContextPath() %>/dist/img/user2-160x160.jpg" class="user-image"
                                 alt="User Image"/>
                            <span class="hidden-xs"><%=userVO.getRealName() %></span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="<%=request.getContextPath() %>/dist/img/user2-160x160.jpg" class="img-circle"
                                     alt="User Image"/>
                                <p>
                                    <%=userVO.getRealName() %>
                                    <small id="clock"></small>
                                </p>
                            </li>

                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="javascript: mainLoadData('<%=request.getContextPath()%>/user/User!modifyPasswd.action',413);"
                                       class="btn btn-warning">修改密码</a>
                                </div>
                                <div class="pull-right">
                                    <a href="<%=request.getContextPath()%>/user/Login!logout.action"
                                       class="btn btn-info">退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <ul class="sidebar-menu">
                <li class="header">云视传媒广告平台</li>
                <authz:Permission resName="RES_DETAIL">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>明细数据查询</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/UsersModel!list.action',111)"><i
                                            class="fa fa-files-o"></i>用户销量</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/SdkUsersModel!projectlist.action',119)"><i
                                            class="fa fa-files-o"></i>项目销量排重</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/SdkUsersModel!projectValidlist.action',705)"><i
                                            class="fa fa-files-o"></i>项目非法销量</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Ddl!queryClick.action',704)"><i
                                            class="fa fa-files-o"></i><span>DDL重复点击</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/ActusersModel!list.action',112)"><i
                                            class="fa fa-files-o"></i>活跃查询</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/AdvSentsListModel!list.action',113)"><i
                                            class="fa fa-files-o"></i>下发</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/AdvFeebackListClickModel!list.action?advFeebackListClickModelVO.type=1',312)"><i
                                            class="fa fa-files-o"></i>展示回传</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/AdvFeebackListClickModel!list.action?advFeebackListClickModelVO.type=2',312)"><i
                                            class="fa fa-files-o"></i>点击回传</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/AdvFeebackListClickModel!list.action?advFeebackListClickModelVO.type=3',312)"><i
                                            class="fa fa-files-o"></i>下载回传</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/AdvFeebackListClickModel!list.action?advFeebackListClickModelVO.type=4',312)"><i
                                            class="fa fa-files-o"></i>安装回传</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/ActusersModel!guardList.action',312)"><i
                                            class="fa fa-files-o"></i>守护数据</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/AdLinkDetail!list.action',119)"><i
                                            class="fa fa-files-o"></i>链接数据</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/DdlDetail!list.action',118)"><i
                                            class="fa fa-files-o"></i>DDL数据</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/SdkDetail!list.action',822)"><i
                                            class="fa fa-files-o"></i>电商数据明细</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/SubDetail!list.action',823)"><i
                                            class="fa fa-files-o"></i>聚合数据明细</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/LinkDetail!list.action',824)"><i
                                            class="fa fa-files-o"></i>外放数据明细</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/IframeDataDetail!list.action',1001)"><i
                                            class="fa fa-files-o"></i>IFRAME下发数据明细</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DETAIL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/detail/IframeDetail!list.action',1002)"><i
                                            class="fa fa-files-o"></i>IFRAME激活数据明细</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>
                <authz:Permission resName="RES_INPUT">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>数据录入</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_INPUT_PRODUCT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Product!list.action',210)"><i
                                            class="fa fa-circle-o"></i>产品管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_INPUT_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/record/ApkCphcTotalModel!list.action',211)"><i
                                            class="fa fa-circle-o"></i>广告收益录入</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_INPUT_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/record/ProjectHzTotalModel!list.action',212)"><i
                                            class="fa fa-circle-o"></i>项目第三方收益录入</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_INPUT_PROJECT_PERCENT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/customer/Project!list.action',213)"><i
                                            class="fa fa-circle-o"></i>项目转化率设置</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_INPUT_COUNTRY">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/record/CountryLevel!list.action',214)"><i
                                            class="fa fa-circle-o"></i>国家设置</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_INPUT_COUNTRY">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/record/Operator!list.action',215)"><i
                                            class="fa fa-circle-o"></i>运营商设置</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>
                <authz:Permission resName="RES_DATA">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>数据统计</span></a>
                        <ul class="treeview-menu">

                            <authz:Permission resName="RES_DATA_AFFAIR_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!totalaffairQuery.action',309)"><i
                                            class="fa fa-files-o"></i><span>商务数据总览查询</span></a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!affairQuery.action',311)"><i
                                            class="fa fa-files-o"></i><span>商务项目数据查询</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_PROJECT_COUNT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!ProjectTotalQuery.action',308)"><i
                                            class="fa fa-files-o"></i><span>累计数据查询</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_PROJECT_TOTAL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!totalQuery.action',309)"><i
                                            class="fa fa-files-o"></i><span>数据总览查询</span></a></li>
                            </authz:Permission>


                            <authz:Permission resName="RES_DATA_PROJECT_TOTAL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectIncome!totalQuery.action',310)"><i
                                            class="fa fa-files-o"></i><span>每日项目数据总览</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_PROJECT_EFFECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectIncome!effectQuery.action',320)"><i
                                            class="fa fa-files-o"></i><span>项目效果分析</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_AD_EFFECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/AdData!effectQuery.action',321)"><i
                                            class="fa fa-files-o"></i><span>广告效果分析</span></a></li>
                            </authz:Permission>

                            <authz:Permission resName="RES_DATA_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!query.action',311)"><i
                                            class="fa fa-files-o"></i><span>项目数据查询</span></a></li>
                            </authz:Permission>

                            <authz:Permission resName="RES_DATA_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/AdData!query.action',312)"><i
                                            class="fa fa-files-o"></i><span>广告数据查询</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_VERSION">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/VersionData!query.action',319)"><i
                                            class="fa fa-files-o"></i><span>版本数据查询</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_PROJECT_INCOME">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectIncome!query.action',313)"><i
                                            class="fa fa-files-o"></i><span>项目收益查询</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_AD_INCOME">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/AdIncome!query.action',314)"><i
                                            class="fa fa-files-o"></i><span>广告收益查询</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_RETENTION">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/Retention!query.action',315)"><i
                                            class="fa fa-files-o"></i><span>留存数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_PROJECT_INCOME">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectIncome!postQuery.action',316)"><i
                                            class="fa fa-files-o"></i><span>结算数据同步</span></a></li>
                            </authz:Permission>
                                <%--<authz:Permission resName="RES_DATA_AD_FILL">
                                    <li ><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/UserAdData!query.action',323)"><i class="fa fa-files-o"></i><span>广告填充数据</span></a></li>
                                </authz:Permission>   --%>
                            <authz:Permission resName="RES_DATA_LINK_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/AdLinkData!query.action',322)"><i
                                            class="fa fa-files-o"></i><span>链接广告数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_DDL_CLICK">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/DdlData!query.action',317)"><i
                                            class="fa fa-files-o"></i><span>DDL点击数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_DDL_SALE">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/DdlData!saleQuery.action',318)"><i
                                            class="fa fa-files-o"></i><span>DDL销量数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_LINK_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/SdkData!Query.action',319)"><i
                                            class="fa fa-files-o"></i><span>电商数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_LINK_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/SubData!Query.action',319)"><i
                                            class="fa fa-files-o"></i><span>聚合数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_LINK_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/OfferSdkData!Query.action',319)"><i
                                            class="fa fa-files-o"></i><span>订阅SDK数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_LINK_AD">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/IframeData!Query.action',319)"><i
                                            class="fa fa-files-o"></i><span>IFRAME数据</span></a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DATA_THREE_SDK">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/ThreeSdk!projectThreeStatList.action',327)"><i
                                            class="fa fa-files-o"></i>三方SDK项目</a></li>
                            </authz:Permission>
                                <%--<authz:Permission resName="RES_DATA_PROJECT_DISTINCT">
                                    <li ><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectDistinct!query.action',325)"><i class="fa fa-files-o"></i><span>项目排重数据</span></a></li>
                                </authz:Permission>--%>
                                <%--<authz:Permission resName="RES_DATA_GUARD">
                                    <li ><a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/UserGuard!query.action',326)"><i class="fa fa-files-o"></i><span>守护数据</span></a></li>
                                </authz:Permission>--%>
                        </ul>
                    </li>
                </authz:Permission>
                <authz:Permission resName="RES_SDK">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>SDK管理</span></a>
                        <ul class="treeview-menu">

                            <authz:Permission resName="RES_SDK_LIST">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/SdkInfo!list.action',510)"><i
                                            class="fa fa-circle-o"></i>动态加载管理</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/ThreeSdk!list.action',517)"><i
                                            class="fa fa-circle-o"></i>三方SDK管理</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/ThreeSdk!projectSdkList.action',518)"><i
                                            class="fa fa-circle-o"></i>三方SDK项目管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SDK_PLUGIN">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/ApkInfo!list.action?apkInfoVO.state=3200',511)"><i
                                            class="fa fa-circle-o"></i>后台插件管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SDK_SILENT_PLUGIN">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/SilentPluginInfo!list.action',516)"><i
                                            class="fa fa-circle-o"></i>cps插件管理</a></li>
                            </authz:Permission>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/Sdkdomain!list.action',517)"><i
                                        class="fa fa-circle-o"></i>sdk域名配置列表</a></li>
                        </ul>
                    </li>
                </authz:Permission>
                <authz:Permission resName="RES_CONFIG">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>配置管理</span></a>
                        <ul class="treeview-menu">


                            <authz:Permission resName="RES_SDK_GUIDE">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/GuideInfo!list.action',512)"><i
                                            class="fa fa-circle-o"></i>引导配置</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SDK_SILENT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/SilentInfo!list.action',513)"><i
                                            class="fa fa-circle-o"></i>线下配置</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SDK_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/SdkProject!list.action',514)"><i
                                            class="fa fa-circle-o"></i>项目配置</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_INSIDE_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/ThreeSdk!insidelist.action',514)"><i
                                            class="fa fa-circle-o"></i>内部项目配置</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SDK_PROEJCT_ADV">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/SdkProject!advList.action',515)"><i
                                            class="fa fa-circle-o"></i>项目广告配置</a></li>
                            </authz:Permission>
                                <%-- <li><a href="javascript: mainLoadData('<%=request.getContextPath() %>/record/Adjust!list.action',516)"><i class="fa fa-circle-o"></i>adjust管理</a></li>--%>

                        </ul>
                    </li>
                </authz:Permission>


                <authz:Permission resName="RES_WEB">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>链接广告</span></a>
                        <ul class="treeview-menu">
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Linkadsconfig!list.action',614)"><i
                                        class="fa fa-circle-o"></i>链接广告配置</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Linkads2!list.action',615)"><i
                                        class="fa fa-circle-o"></i>链接广告管理</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Webdomain!list.action',616)"><i
                                        class="fa fa-circle-o"></i>链接域名管理</a></li>
                        </ul>
                    </li>
                </authz:Permission>


                <authz:Permission resName="RES_PROMOTION">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>推广链接设置</span></a>
                        <ul class="treeview-menu">
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Pcustomer!list.action',619)"><i
                                        class="fa fa-circle-o"></i>渠道链接管理</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Promotion!list.action',617)"><i
                                        class="fa fa-circle-o"></i>推广链接配置</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/PromotionCustomer!list.action',618)"><i
                                        class="fa fa-circle-o"></i>推广渠道管理</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Spromotion!list.action',620)"><i
                                        class="fa fa-circle-o"></i>订阅渠道链接管理</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Subscribe!list.action',621)"><i
                                        class="fa fa-circle-o"></i>订阅广告</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/PromotionDomain!list.action',620)"><i
                                        class="fa fa-circle-o"></i>推广链接域名管理</a></li>



                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Pcustomer!iframeList.action',619)"><i
                                        class="fa fa-circle-o"></i>渠道iframe链接管理</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Promotion!iframeList.action',617)"><i
                                        class="fa fa-circle-o"></i>推广iframe链接配置</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/PromotionDomain!iframeList.action',620)"><i
                                        class="fa fa-circle-o"></i>iframe推广链接域名管理</a></li>

                        </ul>
                    </li>
                </authz:Permission>


                <authz:Permission resName="RES_PROMOTION">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>电商数据录入</span></a>
                        <ul class="treeview-menu">
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/DesktopInfo!list.action',800)"><i
                                        class="fa fa-circle-o"></i>桌面配置</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/LinkAdver!list.action',801)"><i
                                        class="fa fa-circle-o"></i>电商广告配置</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/SdkConfig!list.action',802)"><i
                                        class="fa fa-circle-o"></i>sdk配置</a></li>
                            <li>
                                <a href="javascript: mainLoadData('<%=request.getContextPath() %>/sdkinfo/AppName!list.action',803)"><i
                                        class="fa fa-circle-o"></i>app包名</a></li>
                        </ul>
                    </li>
                </authz:Permission>


                <authz:Permission resName="RES_ADV">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>广告管理</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_ADVER_INFO">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Adver!list.action',610)"><i
                                            class="fa fa-circle-o"></i>广告主管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_ADV_LINK_INFO">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Adv!list.action',611)"><i
                                            class="fa fa-circle-o"></i>web1.2广告管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_ADV_INFO">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/AdvSdk!list.action',612)"><i
                                            class="fa fa-circle-o"></i>广告管理</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/AdvLinkman!list.action',613)"><i
                                            class="fa fa-circle-o"></i>接入人员管理</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/AdvSdk!countryList.action',612)"><i
                                            class="fa fa-circle-o"></i>广告国家查询</a></li>
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/adver/Video!list.action',617)"><i
                                            class="fa fa-circle-o"></i>视频管理</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>

                <authz:Permission resName="RES_DDL">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>DDL管理</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_DDL_CHANNEL">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/ddl/DdlChannel!list.action',700)"><i
                                            class="fa fa-circle-o"></i>DDL渠道</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DDL_PRODUCT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/ddl/DdlProduct!list.action',701)"><i
                                            class="fa fa-circle-o"></i>DDL产品</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DDL_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/ddl/DdlProject!list.action',702)"><i
                                            class="fa fa-circle-o"></i>DDL项目</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_DDL_PROJECT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/ddl/DdlProject!domainList.action',703)"><i
                                            class="fa fa-circle-o"></i>域名管理</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>

                <authz:Permission resName="RES_OFFER">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>订阅管理</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_OFFER_INFO">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/offerinfo/OfferSdk!list.action?offerSdkVO.status=1',800)"><i
                                            class="fa fa-circle-o"></i>订阅信息</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>

                <authz:Permission resName="RES_OFFLINE">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>线下管理</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_OFF_SDK">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/offline/Offsdk!list.action',901)"><i
                                            class="fa fa-circle-o"></i>线下SDK</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_OFF_JAR">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/offline/Offjar!list.action',900)"><i
                                            class="fa fa-circle-o"></i>线下JAR</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_OFF_APK">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/offline/Offapk!list.action',902)"><i
                                            class="fa fa-circle-o"></i>线下APK</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>

                <authz:Permission resName="RES_SYSTEM">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-th"></i><span>系统管理</span></a>
                        <ul class="treeview-menu">
                            <authz:Permission resName="RES_SYSTEM_ACCOUNT">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/user/User!list.action',411)"><i
                                            class="fa fa-circle-o"></i>用户管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SYSTEM_ROLE">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/user/Role!list.action',421)"><i
                                            class="fa fa-circle-o"></i>角色管理</a></li>
                            </authz:Permission>
                            <authz:Permission resName="RES_SYSTEM_RES">
                                <li>
                                    <a href="javascript: mainLoadData('<%=request.getContextPath() %>/user/Res!list.action',431)"><i
                                            class="fa fa-circle-o"></i>资源管理</a></li>
                            </authz:Permission>
                        </ul>
                    </li>
                </authz:Permission>


            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <div id="content" class="content-wrapper">
        <section class="content-header">
            <h1>&nbsp;</h1>
            <ol id="breadcrumb" class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">首页</li>
            </ol>
        </section>
        <section class="content">
            <div class="container-fluid" id="dataContent" style="min-height: 570px">
                <!--
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
				</div> -->
            </div>
        </section>
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>版本</b> 3.0.3
        </div>
        <strong>Copyright &copy; 2014-2016 <a href="#">云视传媒网络</a>.</strong> All rights reserved.
    </footer>
</div>
<!-- jQuery 2.1.3 -->
<script src="<%=request.getContextPath() %>/css/jQuery/jQuery-2.1.3.min.js"></script>
<!-- jQuery UI 1.11.2 -->
<script src="<%=request.getContextPath() %>/js/jquery-ui.min.js" type="text/javascript"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.2 JS -->
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js" type="text/javascript"></script>
<!-- Morris.js charts -->
<script src="<%=request.getContextPath() %>/js/raphael-min.js"></script>
<!-- Sparkline -->
<script src="<%=request.getContextPath() %>/css/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- jvectormap -->
<script src="<%=request.getContextPath() %>/css/jvectormap/jquery-jvectormap-1.2.2.min.js"
        type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/css/jvectormap/jquery-jvectormap-world-mill-en.js"
        type="text/javascript"></script>
<!-- jQuery Knob Chart -->
<script src="<%=request.getContextPath() %>/css/knob/jquery.knob.js" type="text/javascript"></script>
<!-- daterangepicker -->
<script src="<%=request.getContextPath() %>/css/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- datepicker -->
<script src="<%=request.getContextPath() %>/css/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="<%=request.getContextPath() %>/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
        type="text/javascript"></script>
<!-- iCheck -->
<script src="<%=request.getContextPath() %>/css/iCheck/icheck.min.js" type="text/javascript"></script>
<!-- Slimscroll -->
<script src="<%=request.getContextPath() %>/css/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- FastClick -->
<script src='<%=request.getContextPath() %>/css/fastclick/fastclick.min.js'></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/dist/js/app.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/clock.js"></script>

<!-- AdminLTE dashboard demo (This is only for demo purposes) -->

<!-- AdminLTE for demo purposes -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/kkpay.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/select2.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/toastr.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/util.js"></script>
<!--
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/excanvas.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.ui.custom.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.flot.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.flot.resize.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.peity.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/fullcalendar.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/unicorn.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/unicorn.dashboard.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/kkpay.js"></script>
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
		 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/exporting.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/no-data-to-display.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/highcharts/kkgame-chart.js"></script>

<script type="text/javascript">
    var clock = new Clock();
    clock.display(document.getElementById("clock"));
    <% if((userVO.getIsBd()==0 && userVO.getId()!=56)||userVO.getId()==23) {
        %>
    mainLoadData('<%=request.getContextPath() %>/data/ProjectData!dayInfo.action', 110);
    <%
}%>
</script>
</body>
</html>