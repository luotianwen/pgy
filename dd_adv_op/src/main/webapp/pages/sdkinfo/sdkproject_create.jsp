<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<s:hidden id="cid" name="sdkProjectVO.id"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2>
        <s:if test="sdkProjectVO.id==0">新建项目配置</s:if>
        <s:if test="sdkProjectVO.id>0">修改项目配置</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">coo_id</td>
                <td>
                    <s:if test="sdkProjectVO.id==0">
                        <input type="text" class="input-medium" id="coo_id" name="sdkProjectVO.coo_id"
                               value="${sdkProjectVO.coo_id}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');
                               $('#saleurl').val('http://192.154.96.210:8480/210.jsp?coo_id='+this.value)"/>
                    </s:if>
                    <s:else>
                        <input type="text" class="input-medium" id="coo_id" name="sdkProjectVO.coo_id"
                               value="${sdkProjectVO.coo_id}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
                    </s:else>
                </td>
                <td class="table_td_title">项目名称</td>
                <td>
                    <input type="text" class="input-medium" id="apkName" name="sdkProjectVO.apkName"
                           value="${sdkProjectVO.apkName}"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="stauts"
                              name="sdkProjectVO.stauts"></s:select>
                </td>
                <td class="table_td_title">是否通过</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="pass"
                              name="sdkProjectVO.pass"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否强制安装</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="exe"
                              name="sdkProjectVO.exe"></s:select>
                </td>
                <td class="table_td_title">是否动态加载</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="changeState"
                              name="sdkProjectVO.changeState"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否显示</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="deletes"
                              name="sdkProjectVO.deletes"></s:select>
                </td>
                <td class="table_td_title">是否同步数据</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="issyndata"
                              name="sdkProjectVO.issyndata"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否检测</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isopen"
                              name="sdkProjectVO.isOpen"></s:select>
                </td>
                <td class="table_td_title">是否检测已满</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isfull100"
                              name="sdkProjectVO.isfull100"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否内置运营</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isseloper"
                              name="sdkProjectVO.isseloper"></s:select>
                </td>
                <td class="table_td_title">是否开启视频</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isvideo"
                              name="sdkProjectVO.isVideo"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">检测用户有数</td>
                <td colspan="3">
                    <input type="text" size="50" class="input-medium" id="fulls" name="sdkProjectVO.fulls"
                           value="${sdkProjectVO.fulls}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否转发销量</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="issale"
                              name="sdkProjectVO.issale"></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">销量路径</td>
                <td colspan="3">
					<input type="text" size="50" class="input-medium" id="saleurl" name="sdkProjectVO.saleurl"
                           value="${sdkProjectVO.saleurl}"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">延迟时间</td>
                <td colspan="3">
                    <input type="text" size="50" class="input-medium" id="dalyTime" name="sdkProjectVO.dalyTime"
                           value="${sdkProjectVO.dalyTime}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">延迟多少天开广告</td>
                <td colspan="3">
                    <input type="text" size="50" class="input-medium" id="day" name="sdkProjectVO.day"
                           value="${sdkProjectVO.day}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">扣量比例</td>
                <td colspan="3">
                    <input type="text" size="50" class="input-medium" id="klbl" name="sdkProjectVO.klbl"
                           value="${sdkProjectVO.klbl}" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"/>

                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否推送</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="ispush"
                              name="sdkProjectVO.isPush"  ></s:select>
                </td>
                <td class="table_td_title">是否插屏</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isplaque"
                              name="sdkProjectVO.isPlaque"  ></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否浏览器劫持</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isbrowserhold"
                              name="sdkProjectVO.isBrowserHold" ></s:select>
                </td>
                <td class="table_td_title">是否app劫持</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isapphold"
                              name="sdkProjectVO.isAppHold"   ></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否浮动按钮list</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="islevitate"
                              name="sdkProjectVO.isLevitate"  ></s:select>
                </td>
                <td class="table_td_title">是否创建图标</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="iscreateicon"
                              name="sdkProjectVO.isCreateIcon"   ></s:select>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">是否执行线下sdk</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isofflionsdk"
                              name="sdkProjectVO.isOfflionSdk" ></s:select>
                </td>
                <td class="table_td_title">是否更新</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="isupdate"
                    name="sdkProjectVO.isUpdate"></s:select>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <input id="createBtn" type="button" class="btn btn-primary" value="确定"
                           onclick="return updateInfo();">
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="modal-footer">
</div>
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/sdkinfo/SdkProject!save.action';
        var id = $("#cid").val();
        var coo_id = $("#coo_id").val();
        var apkName = $("#apkName").val();
        var stauts = $("#stauts").val();
        var pass = $("#pass").val();
        var exe = $("#exe").val();
        var changeState = $("#changeState").val();
        var deletes = $("#deletes").val();
        var issyndata = $("#issyndata").val();
        var isopen = $("#isopen").val();
        var isfull100 = $("#isfull100").val();
        var isseloper = $("#isseloper").val();
        var fulls = $("#fulls").val();
        var issale = $("#issale").val();
        var saleurl = $("#saleurl").val();
        var dalyTime = $("#dalyTime").val();
        var day = $("#day").val();
        var klbl = $("#klbl").val();
        var ispush = $("#ispush").val();
        var isplaque = $("#isplaque").val();
        var isbrowserhold = $("#isbrowserhold").val();
        var isapphold = $("#isapphold").val();
        var islevitate = $("#islevitate").val();
        var iscreateicon = $("#iscreateicon").val();
        var isofflionsdk = $("#isofflionsdk").val();
        var isupdate = $("#isupdate").val();

        var isvideo=$("#isvideo").val();

        $("#createBtn").attr("disabled", "disabled");

        jQuery.post(path, {
                    'sdkProjectVO.id': id,
                    'sdkProjectVO.coo_id': coo_id,
                    'sdkProjectVO.apkName': apkName,
                    'sdkProjectVO.stauts': stauts,
                    'sdkProjectVO.pass': pass,
                    'sdkProjectVO.exe': exe,
                    'sdkProjectVO.changeState': changeState,
                    'sdkProjectVO.deletes': deletes,
                    'sdkProjectVO.issyndata': issyndata,
                    'sdkProjectVO.isOpen': isopen,
                    'sdkProjectVO.isfull100': isfull100,
                    'sdkProjectVO.isseloper': isseloper,
                    'sdkProjectVO.fulls': fulls,
                    'sdkProjectVO.issale': issale,
                    'sdkProjectVO.saleurl': saleurl,
                    'sdkProjectVO.dalyTime': dalyTime,
                    'sdkProjectVO.day': day,
                    'sdkProjectVO.klbl': klbl,
                    'sdkProjectVO.version': 1,
                    'sdkProjectVO.note': '',
                    'sdkProjectVO.creator': 1,
                    'sdkProjectVO.member': 1,
                    'sdkProjectVO.apkType': 200200,
                    'sdkProjectVO.advType': 100200,
                    'sdkProjectVO.htdownload': '',
                    'sdkProjectVO.xuhao': 1,
                    'sdkProjectVO.isopen100': 3201,
                    'sdkProjectVO.isPush': ispush,
                    'sdkProjectVO.isPlaque': isplaque,
                    'sdkProjectVO.isBrowserHold': isbrowserhold,
                    'sdkProjectVO.isAppHold': isapphold,
                    'sdkProjectVO.isLevitate': islevitate,
                    'sdkProjectVO.isCreateIcon': iscreateicon,
                    'sdkProjectVO.isOfflionSdk': isofflionsdk,
                    'sdkProjectVO.isUpdate': isupdate,

                    'sdkProjectVO.isVideo':isvideo
                }
                , function (response) {
                    if (response == "-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display": "none"});
                    } else {
                        $("#load").css({"display": "none"});
                        showInfoToastMiddle(response);
                        doPageBottom('turn');
                    }
                });
    }
</script>
