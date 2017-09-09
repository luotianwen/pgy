<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:if test="linkDetailVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
    <div class="span12">
    <div class="form-inline">
    <fieldset>
    <table style="width:100%">
    <tr>
    <td>
    <td align="right">
    <div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
    <label class="control-label inline" for="adid">adid</label>
    <input id="adid" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <label class="control-label inline" for="imei">cooid</label>
    <input id="cooid" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <label class="control-label inline" for="ctime"> 时间</label>
    <input type="text" name="ctime" id="ctime" onfocus="javascript:WdatePicker()"
    readonly="readonly" value="${linkDetailVO.cdate}"
    style="width:150px;">
    <button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询
    </button>
    </td>
    </tr>
    </table>
    </fieldset>
    </div>
    </div>
    </div>
    <div class="box box-primary" style="margin-top: 15px">
    <div class="span12">
    <form class="form-horizontal" id="dataForm">
    <fieldset>

    <div id="dataList">
</s:if>
<table id="dataTable" class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
        <th>clickid</th>
        <th>adid</th>
        <th>cooid</th>
        <%--<th>运营商</th>--%>
        <th>浏览器</th>
        <th>代理</th>
        <th>点击网页</th>
        <th>网络类型</th>
        <th>分辨率(宽)</th>
        <th>分辨率(高)</th>
        <th>国家</th>
        <th>创建日期</th>

        </tr>
        </thead>
        <tbody>
        <s:iterator value="linkDetailVOList">
            <tr>
                <td><s:property value="clickId"/></td>
                <td><s:property value="adId"/></td>
                <td><s:property value="cooid"/></td>
                <%--<td>--%>
                    <%--<s:iterator value="#Option.globalOperatorList">--%>
                        <%--<s:if test="xoperator == code">--%>
                            <%--<s:property value="operatorName"></s:property>--%>
                        <%--</s:if>--%>
                    <%--</s:iterator>--%>
                <%--</td>--%>
                <td><s:property value="browser"/></td>
                <td><s:property value="agent"/></td>
                <td><s:property value="clickReferer"/></td>

                <td>
                    <s:if test="internet == 1">WIFI</s:if>
                    <s:if test="internet == 0">GPRS</s:if>
                </td>
                <td><s:property value="width"/></td>
                <td><s:property value="height"/></td>
                <td>
                    <s:iterator value="#Option.countryList">
                        <s:if test="cou==id">
                            <s:property value="name"/>
                        </s:if>
                    </s:iterator>
                </td>
                <td><s:property value="cdate"/></td>
            </tr>
        </s:iterator>

        </tbody>
        </table>
        </div>
        <s:if test="linkDetailVO.isPage==0">
            <div style='text-align:center;'>
                <img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
            </div>
            </fieldset>
            </form>
            </div>
            </div>
            <script type="text/javascript">

                function searchList() {

                    var adid = $("#adid").val();
                    var ctime = $("#ctime").val();
                    var cooid = $("cooid").val();
                    $("#load").css({"display": ""});
                    jQuery("#dataTable").html("");
                    url = "<%=request.getContextPath()%>/detail/LinkDetail!list.action";
                    jQuery.post(url, {
                        "linkDetailVO.isPage": 1,
                        "linkDetailVO.adId": adid,
                        "linkDetailVO.cdate": ctime,
                        "linkDetailVO.cooid": cooid

                    }, function (response) {
                        if (response == "-1") {
                            showErrorToastMiddle("系统出错，请重试或联系管理员");
                            $("#load").css({"display": "none"});
                        } else {
                            jQuery("#dataTable").html(jQuery.trim(response));
                            $("#load").css({"display": "none"});
                        }
                    });
                }


            </script>
        </s:if>