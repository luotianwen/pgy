<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar" %>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>
<div class="row">
    <div class="span12">
        <ul class="breadcrumb">
            <li>DDL Report <span class="divider">/</span></li>

        </ul>
        <form id="exportForm" class="form-horizontal well">
            <s:hidden id="rowFieldString" name="billSearchVO.rowFieldString"></s:hidden>
            <input type="hidden" name="billSearchVO.queryType" value="<s:property value="billSearchVO.queryType"/>">
            <fieldset>
                <table class="querytable">
                    <tbody>
                    <tr>
                        <td>Date<font color="red">*</font></td>
                        <td>
                            <s:if test="billSearchVO.queryType==1||billSearchVO.queryType==3">
                                <input type="text" name="billSearchVO.startTime" id="start"
                                       onfocus="javascript:WdatePicker()" readonly="readonly"
                                       value="${billSearchVO.startTime}" class="selwidth">
                                -
                                <input type="text" name="billSearchVO.endTime" id="end"
                                       onfocus="javascript:WdatePicker()" readonly="readonly"
                                       value="${billSearchVO.endTime}" class="selwidth">&nbsp;
                            </s:if>
                            <s:if test="billSearchVO.queryType==2">
                                <input type="text" name="billSearchVO.searchMonth" id="searchMonth"
                                       onfocus="javascript:WdatePicker({ dateFmt: 'yyyyMM' })" readonly="readonly"
                                       value="${billSearchVO.searchMonth}" class="selwidth">&nbsp;
                            </s:if>
                        </td>
                    </tr>
                    <role:equal type="SP,MG">
                        <tr>
                            <td>代理商</td>
                            <td>
                                <select id="agentId" onchange="selBd(this.value);" name="billSearchVO.agentId"
                                        class="selwidth">
                                    <s:action name="getAllAgent" namespace="" executeResult="true">
                                    </s:action>
                                </select>
                                &nbsp;&nbsp;商务
                                <select id="bdId" onchange="selCustomer(this.value);" id="bdId" name="billSearchVO.bdId"
                                        class="selwidth">
                                    <option value="0">--所有商务--</option>
                                </select>
                            </td>
                        </tr>
                    </role:equal>
                    <role:equal type="AG">
                        <tr>
                            <td>商务</td>
                            <td class="td_detail">
                                <select id="bdId" onchange="selCustomer(this.value);" name="billSearchVO.bdId"
                                        class="selwidth">
                                    <s:action name="getAllBd" namespace="" executeResult="true">
                                    </s:action>
                                </select>
                            </td>
                        </tr>
                    </role:equal>
                    <role:equal type="SP,MG,AG,BD">
                        <tr>
                            <td>客户</td>
                            <td>
                                <select id="customerId" onchange="selProject(this.value);"
                                        name="billSearchVO.customerId" class="selwidth">
                                    <option value="0">--所有客户--</option>
                                </select>
                                &nbsp;&nbsp;Project
                                <select id="projectId" name="billSearchVO.projectId" class="selwidth">
                                    <option value="0">--ALL Project--</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Product</td>
                            <td>
                                <select id="productId" name="billSearchVO.productId" class="selwidth">
                                    <s:action name="getAllProduct" namespace="" executeResult="true">
                                    </s:action>
                                </select>
                            </td>
                        </tr>
                    </role:equal>
                    <role:equal type="CM">
                        <tr>
                            <td>Project</td>
                            <td>
                                <select id="projectId" name="billSearchVO.projectId" class="selwidth">
                                    <s:action name="getAllProject" namespace="" executeResult="true">
                                        <s:param name="projectVO.type" value="2"></s:param>
                                    </s:action>
                                </select>
                            </td>
                        </tr>
                        <role:equal type="BD">
                        <tr>
                            <td>Product</td>
                            <td>
                                <select id="productId" name="billSearchVO.productId" class="selwidth">
                                    <s:action name="getAllProduct" namespace="" executeResult="true">
                                    </s:action>
                                </select>
                            </td>
                        </tr>
                        </role:equal>
                    </role:equal>
                    <tr>
                        <td>Rows<font color="red">*</font></td>
                        <td>
                            <label class="checkbox inline">
                                <input type="checkbox" name="billSearchVO.rowFields"
                                       value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_DATE" />"
                                       checked="checked"/>Date
                            </label>
                            <role:equal type="SP,MG,BH">
                                <label class="checkbox inline">
                                    <input type="checkbox" name="billSearchVO.rowFields"
                                           value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_AGENT" />"/>代理商
                                </label>
                                <label class="checkbox inline">
                                    <input type="checkbox" name="billSearchVO.rowFields"
                                           value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_BD" />"/>商务
                                </label>
                                <label class="checkbox inline">
                                    <input type="checkbox" name="billSearchVO.rowFields"
                                           value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
                                </label>
                            </role:equal>
                            <role:equal type="AG">
                                <label class="checkbox inline">
                                    <input type="checkbox" name="billSearchVO.rowFields"
                                           value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_BD" />"/>商务
                                </label>
                                <label class="checkbox inline">
                                    <input type="checkbox" name="billSearchVO.rowFields"
                                           value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
                                </label>
                            </role:equal>
                            <role:equal type="BD">
                                <label class="checkbox inline">
                                    <input type="checkbox" name="billSearchVO.rowFields"
                                           value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_CUSTOMER" />"/>客户
                                </label>

                            </role:equal>
                            <label class="checkbox inline">
                                <input type="checkbox" name="billSearchVO.rowFields"
                                       value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PROJECT" />"/>Project
                            </label>
                            <label class="checkbox inline">
                                <input type="checkbox" name="billSearchVO.rowFields"
                                       value="<s:property value="@com.kkgame.hz.entities.RowFieldVO@ROW_FIELD_PRODUCT" />"/>Product
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="form-actions">
                            <input id="btn" class="btn btn-primary" type="button" value=" Search "
                                   onclick="searchList();"/>&nbsp;&nbsp;
                            <button type="reset" class="btn">Reset</button>
                            <input class="btn btn-primary" type="button" value=" Export " onclick="exportData();"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<div style="padding-bottom: 5px"></div>
<img id="load" src="<%=request.getContextPath() %>/images/loading.gif" style="display: none"/>

<div id="data">

</div>

<script type="text/javascript">

    function showUpload() {
        $("#data").hide();
        $("#uploadDiv").show();
    }

    function exportData() {
        $("#uploadDiv").hide();
        $("#data").show();
        var queryType = <s:property value="billSearchVO.queryType"/>;
        if (queryType == 1 || queryType == 3) {
            if ($("#start").val() == "") {
                alert("Please select start time");
                $("#start").focus();
                return false;
            }
            if ($("#end").value == "") {
                alert("Please choose the end time");
                $("#end").focus();
                return false;
            }
            var startTime = $("#start").val();
            var endTime = $("#end").val();
            if (startTime != "" && endTime != "") {
                var date1 = startTime.split("-");
                var date2 = endTime.split("-");
                var myDate1 = new Date(date1[0], date1[1] - 1, date1[2]);
                var myDate2 = new Date(date2[0], date2[1] - 1, date2[2]);
                if (myDate1 <= myDate2) {
                    if (myDate1.getMonth() != myDate2.getMonth()) {
                        alert("Tip: can only query data for one month! ");
                        return false;
                    }
                } else {
                    alert("Tip: start time not greater than end time! ");
                    return false;
                }
            }
        }
        var chk_value = "";
        $('input[name="billSearchVO.rowFields"]:checked').each(function () {
            chk_value += $(this).val();
            chk_value += ",";
        });
        if (chk_value == "") {
            showInforToastMiddle("Please select at least one statistics column!");
            return false;
        }
        $("#rowFieldString").val(chk_value);
        var exportForm = document.getElementById("exportForm");
        exportForm.action = "<%=request.getContextPath()%>/DdlData!exportData.action";
        exportForm.submit();
    }

    function searchList() {
        $("#uploadDiv").hide();
        $("#data").show();
        var url = "<%=request.getContextPath()%>/DdlData!listDay.action";
        var queryType = <s:property value="billSearchVO.queryType"/>;
        if (queryType == 1 || queryType == 3) {
            if (queryType == 1) {
                url = "<%=request.getContextPath()%>/DdlData!listDay.action";
            } else if (queryType == 3) {
                url = "<%=request.getContextPath()%>/DdlData!listPercentDay.action";
            }
            if ($("#start").val() == "") {
                alert("Please select start time");
                $("#start").focus();
                return false;
            }
            if ($("#end").value == "") {
                alert("Please choose the end time");
                $("#end").focus();
                return false;
            }
            var startTime = $("#start").val();
            var endTime = $("#end").val();
            if (startTime != "" && endTime != "") {
                var date1 = startTime.split("-");
                var date2 = endTime.split("-");
                var myDate1 = new Date(date1[0], date1[1] - 1, date1[2]);
                var myDate2 = new Date(date2[0], date2[1] - 1, date2[2]);
                if (myDate1 <= myDate2) {
                    if (myDate1.getMonth() != myDate2.getMonth()) {
                        alert("Tip: can only query data for one month! ");
                        return false;
                    }
                } else {
                    alert("Tip: start time not greater than end time! ");
                    return false;
                }
            }
        }
        var startTime = $("#start").val();
        var endTime = $("#end").val();
        var searchMonth = $("#searchMonth").val();

        var agentId = $("#agentId").val();
        var bdId = $("#bdId").val();
        var customerId = $("#customerId").val();
        var projectId = $("#projectId").val();
        var productId = $("#productId").val();
        if ("undefined" == typeof(agentId)) {
            agentId = 0;
        }
        if ('undefined' == typeof(bdId)) {
            bdId = 0;
        }
        if ('undefined' == typeof(customerId)) {
            customerId = 0;
        }
        if ('undefined' == typeof(typeof(agentId))) {
            projectId = 0;
        }
        var chk_value = "";
        $('input[name="billSearchVO.rowFields"]:checked').each(function () {
            chk_value += $(this).val();
            chk_value += ",";
        });
        if (chk_value == "") {
            showInforToastMiddle("Please select at least one statistics column!");
            return false;
        }
        $("#load").css({"display": ""});
        jQuery("#data").html("");
        jQuery.post(url, {
                    "billSearchVO.queryType": queryType,
                    "billSearchVO.startTime": startTime,
                    "billSearchVO.endTime": endTime
                    ,
                    "billSearchVO.searchMonth": searchMonth,
                    "billSearchVO.customerId": customerId,
                    "billSearchVO.agentId": agentId
                    ,
                    "billSearchVO.bdId": bdId,
                    "billSearchVO.projectId": projectId,
                    "billSearchVO.productId": productId
                    ,
                    "billSearchVO.rowFieldString": chk_value
                },
                function (response) {
                    if (response == "1") {
                        showErrorToast("Tip", "system error, please retry or contact administrator");
                        $("#load").css({"display": "none"});
                    } else {
                        jQuery("#data").html(jQuery.trim(response));
                        $("#load").css({"display": "none"});
                    }
                });
    }

    var agentId = <s:property value ="billSearchVO.agentId"/>
            selBd(agentId);
    function selBd(agentId) {
        if (agentId != 0) {
            jQuery("#customerId").html('<option value="0">--所有客户--</option>');
            jQuery("#projectId").html('<option value="0">--ALL Project--</option>');
            url = "<%=request.getContextPath()%>/getAllBd.action";
            jQuery.post(url, {"bdVO.agentId": agentId}, function (response) {
                jQuery("#bdId").html(jQuery.trim(response));
            });
        } else {
            jQuery("#bdId").html('<option value="0">--所有商务--</option>');
            jQuery("#customerId").html('<option value="0">--所有客户--</option>');
            jQuery("#projectId").html('<option value="0">--ALL Project--</option>');
        }
    }

    var bdId = <s:property value ="billSearchVO.bdId"/>
            selCustomer(bdId);
    function selCustomer(bdId) {
        if (bdId != 0) {
            jQuery("#projectId").html('<option value="0">--ALL Project--</option>');
            url = "<%=request.getContextPath()%>/getAllCustomer.action";
            jQuery.post(url, {"customerVO.businessDeveloperId": bdId}, function (response) {
                jQuery("#customerId").html(jQuery.trim(response));
            });
        } else {
            jQuery("#customerId").html('<option value="0">--所有客户--</option>');
            jQuery("#projectId").html('<option value="0">--ALL Project--</option>');
        }
    }
    var customerId = <s:property value="billSearchVO.customerId"/>;
    selProject(customerId);
    function selProject(customerId) {
        if (customerId != 0) {
            url = "<%=request.getContextPath()%>/getAllProject.action";
            jQuery.post(url, {"projectVO.customerId": customerId, "projectVO.type": 2}, function (response) {
                jQuery("#projectId").html(jQuery.trim(response));
            });
        } else {
            jQuery("#projectId").html('<option value="0">--ALL Project --</option>');
        }
    }

    function ajaxFileUpload() {
        $("#load").ajaxStart(function () {
                    $(this).show();
                })//开始上传文件时显示一个图片
                .ajaxComplete(function () {
                    $(this).hide();
                });//文件上传完成将图片隐藏起来
        var file = $("#file").val();
        if (file == "") {
            showInfoToastMiddle("请选择上传文件包!");
            $("#file").focus();
            return false;
        }
        jQuery("#uploadBtn").attr('disabled', 'disabled');
        $.ajaxFileUpload({
            url: 'UserRegister!upload.action',//用于文件上传的服务器端请求地址
            secureuri: false,//一般设置为false
            fileElementId: 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json',//返回值类型 一般设置为json
            success: function (data, status) {
                //服务器成功响应处理函数
                showInfoToastMiddle(data.msg);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量

            },
            error: function (data, status, e) {
                //服务器响应失败处理函数
                alert(e);
            }
        });
        return false;
    }
</script>