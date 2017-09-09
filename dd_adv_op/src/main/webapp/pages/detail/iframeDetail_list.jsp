<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:if test="iframeDetailVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
    <div class="span12">
    <div class="form-inline">
    <fieldset>
    <table style="width:100%">
    <tr>
    <td>
    <td align="right">
    <div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
    <label class="control-label inline" for="clickId">clickId</label>
    <input id="clickId" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <label class="control-label inline" for="pid">pid</label>
    <input id="pid" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <label class="control-label inline" for="ctime"> 时间</label>
    <input type="text" name="ctime" id="ctime" onfocus="javascript:WdatePicker()"
    readonly="readonly" value="${iframeDetailVO.cdate}"
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
        <th>激活id</th>
        <th>iframe位置</th>
        <th>渠道点击Id</th>
        <th>渠道</th>
        <th>运营商</th>
        <th>国家</th>
        <th>金额</th>
        <th>创建日期</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="iframeDetailVOList">
            <tr>
                <td><s:property value="clickId"/></td>
                <td>
                    <s:if test="index==-1">其他链接</s:if>
                    <s:if test="index==0">top</s:if>
                    <s:if test="index==1">1</s:if>
                    <s:if test="index==2">2</s:if>
                    <s:if test="index==3">3</s:if>
                    <s:if test="index==4">4</s:if>
                    <s:if test="index==5">5</s:if>
                </td>
                <td><s:property value="channelId"/></td>
                <td><s:property value="cid"/></td>
                <td><s:property value="operatorId"/></td>
                <td><s:property value="cou"/></td>
                <td><s:property value="payout"/></td>
                <td><s:property value="cdate"/></td>
            </tr>
        </s:iterator>

        </tbody>
        </table>
        </div>
        <s:if test="iframeDetailVO.isPage==0">
            <div style='text-align:center;'>
                <img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
            </div>
            </fieldset>
            </form>
            </div>
            </div>
            <script type="text/javascript">

                function searchList() {

                    var clickId = $("#clickId").val();
                    var ctime = $("#ctime").val();
                    var pid = $("pid").val();
                    $("#load").css({"display": ""});
                    jQuery("#dataTable").html("");
                    url = "<%=request.getContextPath()%>/detail/IframeDetail!list.action";
                    jQuery.post(url, {
                        "iframeDetailVO.isPage": 1,
                        "iframeDetailVO.clickId": clickId,
                        "iframeDetailVO.cdate": ctime,
                        "iframeDetailVO.pid": pid

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