<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar" %>

<s:if test="refreshRow==0">
    <div class="box box-success">
        <div class="span12">
            <div class="box-header">
                <h3 class="box-title">查询条件</h3>
            </div>
            <div class="widget-box">
                <form class="form-horizontal" id="exportForm">
                    <table class="table">
                        <tr>
                            <td>请选择时间<font color="red">*</font></td>
                            <td>
                                <input type="text" name="searchVO.startDate" id="start"
                                       onfocus="javascript:WdatePicker()"
                                       readonly="readonly" value="${searchVO.startDate}" style="width:150px;">
                                -
                                <input type="text" name="searchVO.endDate" id="end" onfocus="javascript:WdatePicker()"
                                       readonly="readonly" value="${searchVO.endDate}" style="width:150px;">&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>项目</td>
                            <td>
                                <select id="projectId" name="searchVO.projectId" class="selectWidth">
                                    <s:action name="getAllProject" namespace="/customer" executeResult="true">
                                    </s:action>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>三方SDK</td>
                            <td>
                                <select id="sdkId" name="searchVO.sdkId" class="selectWidth">
                                    <s:action name="getAllThreeSdk" namespace="/sdkinfo" executeResult="true">
                                    </s:action>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>统计显示列<font color="red">*</font></td>
                            <td>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="searchVO.rowFields"
                                           value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_DATE" />"
                                           checked="checked"/>时间
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="searchVO.rowFields"
                                           value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_PROJECT" />"/>项目
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="searchVO.rowFields"
                                           value="<s:property value="@com.kkgame.feeop.data.bean.RowFieldVO@ROW_FIELD_SDK" />"/>三方SDK
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: center">
                                <input id="btn" class="btn btn-primary" type="button" value=" 查 询 "
                                       onclick="searchList();"/>&nbsp;&nbsp;
                                <button type="reset" class="btn">清 空</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <div class="row-fluid" style="overflow:auto">
        <div class="span12">
            <form class="form-horizontal" id="dataForm">
                <fieldset>
                    <div id="dataList">
</s:if>
<s:if test="refreshRow==1">
    <s:if test="null==projectThreeStatVOList || 0==projectThreeStatVOList.size">
        <font color="red">暂无数据</font>
    </s:if>
    <s:else>
                        <div class="box box-primary">
                            <table class="table table-bordered table-striped table-hover">
                                <thead>
                                    <tr>
                                        <s:if test="searchVO.rowFieldVO.isShowDate==1">
                                            <th>时间</th>
                                        </s:if>
                                        <s:if test="searchVO.rowFieldVO.isShowProject==1">
                                            <th>项目</th>
                                        </s:if>
                                        <s:if test="searchVO.rowFieldVO.isShowSdk==1">
                                            <th>三方SDK</th>
                                        </s:if>
                                        <th>销量</th>
                                        <th>收入</th>
                                        <th>收入 / 销量</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="projectThreeStatVOList">
                                    <tr>
                                        <s:if test="searchVO.rowFieldVO.isShowDate==1">
                                            <td><s:property value="saleDate"/> </td>
                                        </s:if>
                                        <s:if test="searchVO.rowFieldVO.isShowProject==1">
                                            <td>
                                                <s:property value="projectName"/>
                                                [<s:property value="projectId"/>]
                                            </td>
                                        </s:if>
                                        <s:if test="searchVO.rowFieldVO.isShowSdk==1">
                                            <td>
                                                <s:property value="sdkName"/>
                                                [<s:property value="sdkId"/>]
                                            </td>
                                        </s:if>
                                        <td>
                                            <s:property value="activate"/>
                                        </td>
                                        <td>
                                            <s:property value="income"/>
                                        </td>
                                        <td>
                                            <s:property value="getPrice()"/>
                                        </td>
                                    </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
    </s:else>
</s:if>
<s:if test="refreshRow==0">
                    </div>
                    <div style='text-align:center;'>
                        <img id="load" src="<%=request.getContextPath() %>/img/ajax-loader.gif" style="display: none"/>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        $("#projectId").select2();
        $("#sdkId").select2();

        function searchList() {
            var url = "<%=request.getContextPath()%>/sdkinfo/ThreeSdk!projectThreeStatList.action";
            if ($("#start").val() == "") {
                showInfoToastMiddle("请选择开始时间");
                $("#start").focus();
                return false;
            }
            if ($("#end").val() == "") {
                showInfoToastMiddle("请选择结束时间");
                $("#end").focus();
                return false;
            }
            var startDate = $("#start").val();
            var endDate = $("#end").val();
            if (startDate != "" && endDate != "") {
                var date1 = startDate.split("-");
                var date2 = endDate.split("-");
                var myDate1 = new Date(date1[0], date1[1] - 1, date1[2]);
                var myDate2 = new Date(date2[0], date2[1] - 1, date2[2]);
                if (myDate1 <= myDate2) {
                    if (myDate1.getMonth() != myDate2.getMonth()) {
                        showInfoToastMiddle("只能查询一个月的数据！");
                        return false;
                    }
                } else {
                    showInfoToastMiddle("开始时间不能大于结束时间！");
                    return false;
                }
            }
            var projectId = $("#projectId").val();
            var sdkId = $("#sdkId").val();
            if ('undefined' == typeof(projectId)) {
                projectId = 0;
            }
            if ('undefined' == typeof(sdkId)) {
                sdkId = 0;
            }
            var chk_value = "";
            $('input[name="searchVO.rowFields"]:checked').each(function () {
                chk_value += $(this).val();
                chk_value += ",";
            });
            if (chk_value == "") {
                alert("请至少选择一项统计列！");
                return false;
            }
            $("#load").css({"display": ""});
            jQuery("#dataList").html("");
            jQuery.post(url, {
                        "searchVO.startDate": startDate,
                        "searchVO.endDate": endDate,
                        "searchVO.projectId": projectId,
                        "searchVO.sdkId": sdkId,
                        "searchVO.rowFieldString": chk_value,
                        "refreshRow": 1
                    },
                    function (response) {
                        if (response == "-1") {
                            showErrorToast("提示", "系统出错，请重试或联系管理员");
                            $("#load").css({"display": "none"});
                        } else {
                            jQuery("#dataList").html(jQuery.trim(response));
                            $("#load").css({"display": "none"});
                        }
                    });

        }
    </script>
</s:if>