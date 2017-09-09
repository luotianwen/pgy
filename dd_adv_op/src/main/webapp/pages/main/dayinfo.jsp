<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/permission.tld"  prefix="authz"%>

<%
UserVO userVO = (UserVO)request.getSession().getAttribute("SESSION_USER");
%>
<div class="row">
 <div class="col-lg-3 col-xs-6">
  
   <div class="small-box bg-green">
     <div class="inner">
       <h3><s:property value="projectDataVO.totalUserCount"/><sup style="font-size: 20px">个</sup></h3>
       <p>当月SDK销量</p>
     </div>
     <div class="icon">
       <i class="ion ion-stats-bars"></i>
     </div>
     <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!query.action',311)" class="small-box-footer">更多 <i class="fa fa-arrow-circle-right"></i></a>
   </div>
 </div>
 <div class="col-lg-3 col-xs-6">
  
   <div class="small-box bg-blue">
     <div class="inner">
       <h3><s:property value="projectDataVO.userCount"/><sup style="font-size: 20px">个</sup></h3>
       <p> 当日SDK销量 </p>
     </div>
     <div class="icon">
       <i class="ion ion-stats-bars"></i>
     </div>
     <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!query.action',311)" class="small-box-footer">更多 <i class="fa fa-arrow-circle-right"></i></a>
   </div>
 </div> 
	<div class="col-lg-3 col-xs-6">
   <!-- small box -->
      <div class="small-box bg-red">
     <div class="inner">
       <h3><s:property value="projectDataVO.totalActiveCount"/><sup style="font-size: 20px">个</sup></h3>
       <p>当日总活跃 </p>
     </div>
     <div class="icon">
       <i class="ion ion-stats-bars"></i>
     </div>
     <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!query.action',311)" class="small-box-footer">更多 <i class="fa fa-arrow-circle-right"></i></a>
   </div>
 </div><!-- ./col -->
 <div class="col-lg-3 col-xs-6">
   <!-- small box -->
      <div class="small-box bg-yellow">
     <div class="inner">
       <h3><s:property value="projectDataVO.installCount"/><sup style="font-size: 20px">个</sup></h3>
       <p>当日广告总安装数 </p>
     </div>
     <div class="icon">
       <i class="ion ion-stats-bars"></i>
     </div>
     <a href="javascript: mainLoadData('<%=request.getContextPath() %>/data/ProjectData!query.action',311)" class="small-box-footer">更多 <i class="fa fa-arrow-circle-right"></i></a>
   </div>
 </div><!-- ./col -->
</div><!-- /.row -->
<div class="row">
   <div class="col-md-6">
   	<div class="box box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">当月总销量</h3>
        </div>
        <div class="box-body">
          <div class="chart" id="totalChart" style="height:250px">
          </div>
        </div><!-- /.box-body -->
      </div><!-- /.box -->
		<div class="box box-danger">
          <div class="box-header with-border">
            <h3 class="box-title">每日销量</h3>
          </div>
          <div class="box-body"  id="dayChart" style="height:250px">
          </div><!-- /.box-body -->
        </div><!-- /.box -->
   </div>
   <div class="col-md-6">
   	<div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">每日总活跃</h3>
                </div>
                <div class="box-body">
                  <div class="chart" id="activeChart" style="height:250px">
                  </div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->

              <!-- BAR CHART -->
              <div class="box box-success">
                <div class="box-header with-border">
                  <h3 class="box-title">每日总安装</h3>
                </div>
                <div class="box-body">
                  <div class="chart" id="installChart" style="height:230px">
                  </div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
   </div>
</div>
<script type="text/javascript">
var url = '<%=request.getContextPath() %>/data/ProjectData!dayInfoChart.action';
var options = {
	title: {
   		x: -20 //center
	},
	chart: {type: 'column'}

};
render_chart('totalChart','',url,{'dataType':1},false,options);
render_chart('dayChart','',url,{'dataType':2},false,options);
render_chart('activeChart','',url,{'dataType':3},false,options);
render_chart('installChart','',url,{'dataType':4},false,options);
</script>

