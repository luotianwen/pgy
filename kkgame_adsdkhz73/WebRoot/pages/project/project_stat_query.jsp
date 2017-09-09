<%--
  User: XJ
  Date: 2016/3/21
  Time: 11:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy" />
<div class="row">
    <div class="span12">
        <ul class="breadcrumb">
            <li>当前位置 <span class="divider">/</span></li>
            <li>项目中心<span class="divider">/</span></li>
            <li class="active"><s:if test="queryType==1">商务出包统计</s:if></li>
        </ul>
        <form class="form-horizontal well">
            <input id="queryType" type="hidden" value="<s:property value="queryType"/>">
            <fieldset>
                <legend>
                    <h3 class="inline">查询条件</h3>
                </legend>
                时间
                <input type="text" id="start"
                       onfocus="javascript:WdatePicker()" readonly="readonly"
                       value="${projectStatVO.startTime}" class="selectWidth">
                -
                <input type="text" id="end"
                       onfocus="javascript:WdatePicker()" readonly="readonly"
                       value="${projectStatVO.endTime}" class="selectWidth">
                商务
                <select id="bdId" class="selectWidth">
                    <s:action name="getAllBdList" namespace="/" executeResult="true">
                    </s:action>
                </select>
                状态
                <s:select list="#Policy.packageStatusList" listKey="key" listValue="value" id="status" headerKey="0" headerValue="--所有状态--" cssStyle="width: auto"></s:select>
                <button type="button" class="btn btn-primary" onclick="searchList()">查 询</button>
                <button type="reset" class="btn">清 空</button>
            </fieldset>
        </form>
    </div>
</div>
<div class="row">
    <div class="span12">
        <form class="form-horizontal well" id="dataForm">
            <fieldset>
                <img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>
                <div id="dataList"></div>
            </fieldset>
        </form>
    </div>
</div>
<script type="text/javascript">
    function searchList(form) {
        // filter parameter
        var isShowTime = 1;
        var start = $("#start").val();
        var end = $("#end").val();
        if(start!="" && end!="") {
            var date1 = start.split("-");
            var date2 = end.split("-");
            var myDate1 = new Date(date1[0],date1[1]-1,date1[2]);
            var myDate2 = new Date(date2[0],date2[1]-1,date2[2]);
            if (myDate1 > myDate2) {
                showInfoToastMiddle ("开始时间不能大于结束时间！");
                return false;
            }
        } else if (start=="" && end=="") {
            isShowTime = 0;
        }
        // begin request
        $("#load").css({"display":""});
        jQuery("#dataList").html("");
        var queryType = $("#queryType").val();
        var agentId = $("#agentId").val();
        var bdId = $("#bdId").val();
        var status = $("#status").val();
        url = "<%=request.getContextPath()%>/Project!statList.action";
        jQuery.post(url,{"projectStatVO.queryType":queryType,"projectStatVO.startTime":start,
            "projectStatVO.endTime":end,"projectStatVO.agentId":agentId,"projectStatVO.bdId":bdId,
            "projectStatVO.status":status,"projectStatVO.isShowTime":isShowTime
        },function(response){
            if(response=="1") {
                showErrorToastMiddle('<font color=\'red\'>系统出错，请重试或联系管理员!</font>');
                $("#load").css({"display":"none"});
            } else {
                jQuery("#dataList").html(jQuery.trim(response));
                $("#load").css({"display":"none"});
            }
        });
    }
</script>
