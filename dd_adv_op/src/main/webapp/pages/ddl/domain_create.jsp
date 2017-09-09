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
        <s:if test="domainVO.id==0">新建域名</s:if>
        <s:if test="domainVO.id>0">修改域名</s:if>
    </h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <table class="table">
            <tr>
                <td class="table_td_title">外放域名(只能一个)</td>
                <td>
                    <input id="wwwDomain" type="text" size="50" value="<s:property value="domainVO.wwwDomain"/>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"   align="center"> 多下载域名请用,隔开 </td>
            </tr>
            <tr>
                <td class="table_td_title">下载域名</td>
                <td>
                    <input id="downDomain" type="text" size="100" value="<s:property value="domainVO.downDomain"/>"/>
                </td>
            </tr>
            <tr>
                <td class="table_td_title">状态</td>
                <td>
                    <s:select list="#Option.enumStatus" listKey="key" listValue="value" id="status"
                              name="domainVO.status"></s:select>
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
<script type="text/javascript">
    function updateInfo() {
        var path = '<%=path%>/ddl/DdlProject!domainSave.action';
        var id = '<s:property value="domainVO.id"/>';
        var wwwDomain = $("#wwwDomain").val().trim();
        var downDomain = $("#downDomain").val().trim();
        var status = $("#status").val();
        if (wwwDomain == "") {
            showInfoToastMiddle("请填写外放域名！");
            return false;
        }
        if (downDomain == "") {
            showInfoToastMiddle("请填写下载域名！");
            return false;
        }
        var totalStatus = getDomainTotalStatus();
        var originalStatus = '<s:property value="domainVO.status"/>';
        // 存在一个域名且待修改的状态为“是”的情况
        if (totalStatus==1 && status==3200) {
            if (!(originalStatus==3200 && id>0)) { // 过滤待编辑的数据原先本来就是“是”状态的域名
                showInfoToastMiddle("只能有一个外放域名！");
                return false;
            }
        }

        $("#createBtn").attr("disabled", "disabled");
        jQuery.post(path, {
                    'domainVO.id': id,
                    'domainVO.wwwDomain': wwwDomain,
                    'domainVO.downDomain': downDomain,
                    'domainVO.status': status
                }
                , function (response) {
                    if (response == "-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display": "none"});
                    } else {
                        $("#load").css({"display": "none"});
                        if (status==3201 && originalStatus==3200 && id>0) {
                            response += "<br/>注意！必须要有一个外放域名噢，记得添加。";
                        }
                        showInfoToastMiddle(response);
                        jQuery.post('<%=path%>/ddl/DdlProject!domainList.action',{"refreshRow":1},function(response){
                            if(response==-1) {
                                showErrorToast("提示","系统出错，请重试或联系管理员");
                            } else {
                                $("#domainListTBody").html(jQuery.trim(response));
                            }
                        });
                    }
                });
    }
</script>