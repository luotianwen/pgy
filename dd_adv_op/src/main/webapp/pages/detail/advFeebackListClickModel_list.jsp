<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:if test="advFeebackListClickModelVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:100%">
                        <tr>
                            <td>
                            <td align="right">
                                <div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
                                <label class="control-label inline" for="pname">imei</label>
                                <input id="qid" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label inline" for="pname">cooid</label>
                                <input id="cooid" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;
                                <label class="control-label inline" for="pname">版本</label>
                                <input id="sdkversion" type="text" class="input-medium inline" style="width: 200px">&nbsp;&nbsp;
                                <label class="control-label inline" for="pname"> 时间</label>
                                <input type="text" name="qname" id="qname" onfocus="javascript:WdatePicker()"
                                       readonly="readonly" value="${advFeebackListClickModelVO.cdate}"
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
    <table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
<tr>
    <th>coo_id</th>
    <th>imei</th>
    <th>插件类型</th>
    <th>广告类型</th>
    <th>通道</th>
    <th>apkid</th>
    <th>创建日期</th>
    <th>国家</th>
    <th>sdk版本</th>
    <th>包id</th>
    <th>插件id</th>

</tr>
</thead>
<tbody>
<s:iterator value="advFeebackListClickModelVOList">
    <tr>
        <td><s:property value="coo_id"/></td>
        <td><s:property value="imei"/></td>
        <td><s:iterator value="#Option.typeList">
            <s:if test="key==sdkstyle">
                <s:property value="value"/>
            </s:if>
        </s:iterator>
        </td>
        <td>
            <s:iterator value="#Option.sdkType">
                <s:if test="key==sdk">
                    <s:property value="value"/>
                </s:if>
            </s:iterator>
        </td>
        <td><s:property value="channelid"/></td>
        <td><s:property value="apkid"/></td>
        <td><s:property value="cdate"/></td>
        <td>
            <s:iterator value="#Option.countryList">
                <s:if test="id==cou">
                    <s:property value="name"/>
                </s:if>
            </s:iterator>
        </td>
        <td><s:property value="sdkversion"/></td>
        <td><s:property value="pkgid"/></td>
        <td><s:property value="xc_coo_id"/></td>

    </tr>
</s:iterator>

</tbody>
<s:if test="advFeebackListClickModelVO.isPage==0">
    </table>
    </div>
    <div style='text-align:center;'>
        <img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
    </div>
    </fieldset>
    </form>
    </div>
    </div>
    <script type="text/javascript">

        function searchList() {

            var id = $("#qid").val();
            var name = $("#qname").val();
            if (name == "") {
                return;
            }
            var coo_id = $("#cooid").val();
            var sdkversion = $("#sdkversion").val();
            $("#load").css({"display": ""});
            jQuery("#dataTable").html("");
            url = "<%=request.getContextPath()%>/detail/AdvFeebackListClickModel!list.action";
            jQuery.post(url, {
                "advFeebackListClickModelVO.isPage": 1,
                "advFeebackListClickModelVO.coo_id": coo_id,
                "advFeebackListClickModelVO.sdkversion": sdkversion,
                "advFeebackListClickModelVO.imei": id,
                "advFeebackListClickModelVO.cdate": name,
                "advFeebackListClickModelVO.type": "${advFeebackListClickModelVO.type}"

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
