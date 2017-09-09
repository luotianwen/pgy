<%--
  User: XJ
  Date: 2016/3/31
  Time: 11:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="offlineapkVO.id==0">新建线下APK</s:if>
        <s:if test="offlineapkVO.id>0">修改线下APK</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">包名</td>
                <td>
                    <input id="packgeName" type="text" size="60" value="<s:property value="offlineapkVO.packageName"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">启动类名</td>
                <td>
                    <input id="activityName" type="text" size="60" value="<s:property value="offlineapkVO.activityName"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">下载地址</td>
                <td>
                    <input id="url" type="text" size="60" value="<s:property value="offlineapkVO.url"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">MD5</td>
                <td>
                    <input id="md5" type="text" size="60" value="<s:property value="offlineapkVO.md5"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">服务名</td>
                <td>
                    <input id="serviceName" type="text" size="60"  value="<s:property value="offlineapkVO.serviceName"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">插件ID</td>
                <td>
                    <input id="apkId" type="number"  value="<s:property value="offlineapkVO.apkId"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">版本</td>
                <td>
                    <input id="version" type="number"  value="<s:property value="offlineapkVO.version"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">安装时间(点)</td>
                <td>
                    <input id="installClock" type="number"  value="<s:property value="offlineapkVO.installClock"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">安装区域</td>
                <td>
                    <s:select id="dataOrSys" name="offlineapkVO.dataOrSys" list="#{'8200800':'data','8200801':'sys' }"
                              listKey="key" listValue="value" cssClass="selectWidth" />
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#{'1':'开','0':'关'}" listKey="key" listValue="value"
                              id="status" name="offlineapkVO.status"></s:select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确定"
                           onclick="return updateInfo();">
                </td>
            </tr>
        </table>
    </form>
</div>
 <%--临时加js--%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/kkpay.js"></script>
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/offline/Offapk!save.action';
        var id = '<s:property value="offlineapkVO.id"/>';
        var packgeName = $("#packgeName").val().trim();
        var activityName = $("#activityName").val().trim();
        var url = $("#url").val().trim();
        var md5 = $("#md5").val().trim();
        var serviceName = $("#serviceName").val().trim();
        var apkId = $("#apkId").val();
        var version = $("#version").val();
        var dataOrSys = $("#dataOrSys").val();
        var installClock = $("#installClock").val();
        var status = $("#status").val();
        if (packgeName == "") {
            showInfoToastMiddle("请填写包名！");
            return false;
        }
        if (url == "") {
            showInfoToastMiddle("请填写下载地址！");
            return false;
        }
        if (md5 == "") {
            showInfoToastMiddle("请填写md5！");
            return false;
        }
        if (serviceName == "") {
            showInfoToastMiddle("请填写服务名！");
            return false;
        }
        if (apkId == "" || apkId <= 0) {
            showInfoToastMiddle("请填写apkId！并且大于0");
            return false;
        }
        if (version == "" || version <= 0) {
            showInfoToastMiddle("请填写版本号！并且大于0");
            return false;
        }

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                    'offlineapkVO.id': id,
                    'offlineapkVO.packageName': packgeName,
                    'offlineapkVO.activityName': activityName,
                    'offlineapkVO.url': url,
                    'offlineapkVO.md5': md5,
                    'offlineapkVO.serviceName': serviceName,
                    'offlineapkVO.apkId': apkId,
                    'offlineapkVO.dataOrSys': dataOrSys,
                    'offlineapkVO.installClock': installClock,
                    'offlineapkVO.version': version,
                    'offlineapkVO.status': status
                }
                , function (response) {
                    if (response == "-1") {
                        showInfoToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display": "none"});
                    } else {
                        $("#load").css({"display": "none"});
                        showInfoToastMiddle(response);
                    }
                });
    }
</script>