<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:if test="advSdkVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:100%">
                        <tr>
                            <td>

                            <td align="right">
                                <div id="ajax-modal" class="modal fade" data-width="1000" tabindex="-1"></div>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label inline" for="pname">广告ID</label>
                                <s:select list="#Option.adList" listKey="id" listValue="name" id="adId"
                                          cssClass="selectWidth" headerKey="-1"
                                          headerValue="--所有广告--"></s:select>

                                <label class="control-label inline" for="pname">国家名称</label>
                                <s:select list="#Option.countryList" listKey="id" listValue="name" id="country"
                                          cssClass="selectWidth" headerKey="-2"
                                          headerValue="--所有国家--"></s:select>

                                <label class="control-label inline" for="pname">是否推送</label>
                                <s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value"
                                          id="pPushStatus" name="advSdkVO.pushStatus"/>
                                <label class="control-label inline" for="pname">是否线下</label>
                                <s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value"
                                          id="pIsSlient" name="advSdkVO.isSlient"/>
                                <label class="control-label inline" for="pname">是否插屏</label>
                                <s:select list="#{'-1':'全部','3200':'是','3201':'否'}" listKey="key" listValue="value"
                                          id="pIsTablePlaque" name="advSdkVO.isTablePlaque"/>
                                <button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">查 询
                                </button>
                                <button type="reset" class="btn btn-primary">清 空</button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </div>
    </div>

    <div class="box box-primary" style="overflow:auto">
    <div class="span12">
    <form class="form-horizontal" id="dataForm">
    <fieldset>
    <div id="dataList">
    <table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>

<thead>
<tr>

    <th>国家名称</th>
    <th>广告数量</th>
    <th>推送数量</th>
    <th>线下数量</th>
    <th>插屏数量</th>

</tr>
</thead>
<tbody>

<s:iterator value="advSdkVOList">
    <tr>

        <td><s:property value="countryName"/></td>
        <td><s:property value="cpid"/></td>
        <td><s:property value="tsUp"/></td>
        <td><s:property value="cap"/></td>
        <td><s:property value="cpUp"/></td>


    </tr>
</s:iterator>
<tr>
    <td colspan="10">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="advForm" property="advSdkVO.page" operation="/adver/AdvSdk!countryList.action"/>
        </div>
    </td>
</tr>
</tbody>
<s:if test="advSdkVO.isPage==0">
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
        $("#country").select2();
        $("#adId").select2();
        $("#pPushStatus").select2();
        $("#pIsSlient").select2();
        $("#pIsTablePlaque").select2();
        function getArgus() {
            var argus = "";
            argus += "advSdkVO.page.pageNum=";
            argus += $("#pageNum").val();
            argus += "&advSdkVO.page.pageSize=";
            argus += $("#pageSize").val();
            var id = $("#adId").val();
            var country = $("#country").val();
            var pPushStatus = $("#pPushStatus").val();
            var pIsSlient = $("#pIsSlient").val();
            var pIsTablePlaque = $("#pIsTablePlaque").val();
            argus += "&advSdkVO.id=";
            argus += id;
            argus += "&advSdkVO.cpid=";
            argus += country;
            argus += "&advSdkVO.pushStatus=";
            argus += pPushStatus;
            argus += "&advSdkVO.isSlient=";
            argus += pIsSlient;
            argus += "&advSdkVO.isTablePlaque=";
            argus += pIsTablePlaque;

            argus += "&advSdkVO.isPage=1";
            return argus;
        }

        function searchList() {
            var id = $("#adId").val();
            var country = $("#country").val();
            var pPushStatus = $("#pPushStatus").val();
            var pIsSlient = $("#pIsSlient").val();
            var pIsTablePlaque = $("#pIsTablePlaque").val();

            $("#load").css({"display": ""});
            jQuery("#dataTable").html("");
            var url = "<%=request.getContextPath()%>/adver/AdvSdk!countryList.action";
            jQuery.post(url,
                    {
                        "advSdkVO.id": id,
                        "advSdkVO.cpid": country,
                        "advSdkVO.pushStatus": pPushStatus,
                        "advSdkVO.isSlient": pIsSlient,
                        "advSdkVO.isTablePlaque": pIsTablePlaque,
                        "advSdkVO.isPage": 1
                    },
                    function (response) {
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



