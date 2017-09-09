<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:if test="offerSdkVO.isPage==0">
    <div class="row-fluid" style="min-height: 40px;padding-top: 20px">
        <div class="span12">
            <div class="form-inline">
                <fieldset>
                    <table style="width:100%">
                        <tr>
                            <td>
                                <input class="btn btn-success" type="button"
                                       onclick="createOfferSdk('<%=path %>/offerinfo')" value="����"></td>
                            <td align="right">
                                <div id="ajax-modal" class="modal fade" data-width="1000" tabindex="-1"></div>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="control-label inline" for="state">״̬</label>
                                <select id="state" name="offerSdkVO.status">
                                    <option value="1">��</option>
                                    <option value="0">��</option>
                                </select>
                                <label class="control-label inline" for="qname">�����</label>
                                <input id="qname" style="width: 10%">
                                <label class="control-label inline" for="qman">������Ա</label>
                                <s:select id="qman" list="#Option.advLinkmanList" listKey="id" listValue="name"
                                          headerKey="0" headerValue="��ѡ�������Ա" cssClass="selectWidth"
                                          cssStyle="width: 10%"/>
                                <label class="control-label inline" for="qoperator">��Ӫ��</label>
                                <s:select id="qoperator" list="#Option.operatorsList" listKey="key" listValue="value"
                                          cssClass="selectWidth" cssStyle="width: 10%" headerKey=""
                                          headerValue="��ѡ��"> </s:select>
                                <label class="control-label inline" for="qadver">�����</label>
                                <s:select id="qadver" list="#Option.adverList" listKey="id" listValue="name"
                                          name="offerSdkVO.adverId" cssClass="selectWidth" cssStyle="width: 10%"
                                          headerKey="0" headerValue="��ѡ��"></s:select>
                                <label class="control-label inline" for="qcou">����</label>
                                <s:select id="qcou" list="#Option.countryList" listKey="id" listValue="name"
                                          cssClass="selectWidth" cssStyle="width: 10%" headerKey="" headerValue="��ѡ��"
                                          onchange="selectOperator()"> </s:select>
                                <button type="button" class="btn btn-primary" onclick="searchList('<%=path%>')">�� ѯ
                                </button>
                                <button type="reset" class="btn btn-primary">�� ��</button>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </div>
    </div>
    <div class="box box-primary" style="margin-top: 15px;overflow:auto">
    <div class="span12">
    <form class="form-horizontal" id="dataForm">
    <fieldset>

    <div id="dataList">
    <table id="dataTable" class="table table-bordered table-striped table-hover">
</s:if>
<thead>
<script>
    $("#qoperator").select2();
    $("#qadver").select2();
    $("#qcou").select2();

</script>
<tr>
    <th>id</th>
    <th>�����</th>
    <th>����</th>
    <th>ͼƬ��</th>
    <%--<th width="20">�ƹ�����</th>--%>
    <%--<th width="20">��ѡ�ƹ�����</th>--%>
    <%--<th >ͼƬ����</th>--%>
    <%--<th>����</th>--%>
    <%--<th>�Ƿ����</th>--%>
    <th>������Ա</th>
    <th>�����</th>
    <th>��������</th>
    <th>״̬</th>
    <th>ecpm״̬</th>
    <th>�ֶ�ecpm</th>
    <th>�Զ�ecpm</th>
    <th>ctr</th>
    <th>�۸�</th>
    <th>����ʱ��</th>
    <th>����</th>
</tr>
</thead>
<tbody>
<s:iterator value="offerSdkVOList">
    <tr>
        <td><s:property value="id"/></td>
        <td><s:property value="adName"/></td>
        <td><s:property value="title"/></td>
        <td><s:property value="imageName"></s:property></td>
        <%--<td><s:property value="promotionUrl"/></td>--%>
        <%--<td><s:property value="_promotionUrl"/></td>--%>
            <%--<td><s:property value="imageUrl"/></td>--%>
        <%--<td><s:property value="remark"/></td>--%>
            <%--<td><s:if test="review==1">��</s:if><s:if test="review==0">��</s:if></td>--%>
        <td><s:iterator value="#Option.advLinkmanList">
            <s:if test="advLinkManId==id">
                <s:property value="name"/>[<s:property value="id"/>]
            </s:if>
        </s:iterator></td>
        <td>
            <s:iterator value="#Option.adverList">
                <s:if test="adverId==id">
                    <s:property value="name"/>[<s:property value="id"/>]
                </s:if>
            </s:iterator>
        </td>
        <td><s:if test="internet==1">WIFI</s:if><s:if test="internet==0">GPRS</s:if><s:if test="internet==2">����</s:if></td>

        <td><s:if test="status==1">��</s:if>
            <s:if test="status==0">��</s:if>
        </td>
        <td><s:if test="ecpmStatus==1">��</s:if>
            <s:if test="ecpmStatus==0">��</s:if>
        </td>
        <td><s:property value="manuEcpm"/></td>
        <td><s:property value="autoEcpm"/></td>
        <td><s:property value="ctr"></s:property></td>
        <td><s:property value="price"/></td>
        <td><s:property value="cdate"/></td>
        <td>
            <a class="btn btn-primary"
               href="javascript: modifyOfferSdk('<%=path%>/offerinfo','<s:property value="id"/>')" title="�޸�">
                �޸�
            </a>
            <a class="btn btn-danger"
               href="javascript: deleteOfferSdk('<%=path%>/offerinfo','<s:property value="id"/>')" title="ɾ��">
                ɾ��
            </a>
            <a class="btn btn-danger"
               href="javascript: deleteOfferSdk('<%=path%>/offerinfo','<s:property value="id"/>')" title="����">
                ����
            </a>
        </td>
    </tr>
</s:iterator>
<tr>
    <td colspan="7">
        <div class="pagelist" id="pagelist1" align="right">
            <page:paginationAjax formName="OfferSdkForm" property="offerSdkVO.page"
                                 operation="/offerinfo/OfferSdk!list.action"/>
        </div>
    </td>
</tr>
</tbody>
<s:if test="offerSdkVO.isPage==0">
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

        function getArgus() {
            var argus = "";
            argus += "offerSdkVO.page.pageNum=";
            argus += $("#pageNum").val();
            argus += "&offerSdkVO.page.pageSize=";
            argus += $("#pageSize").val();
            var id = $("#pid").val();
            var name = $("#pname").val();

            argus += "&offerSdkVO.apkId=";
            argus += id;
            argus += "&offerSdkVO.isPage=1";
            return argus;
        }

        function searchList() {
            var qname = $("#qname").val();
            var state = $("#state").val();
            var qcou = $("#qcou").val();
            var qman = $("#qman").val();
            var qoperator = $("#qoperator").val();
            var qadver = $("#qadver").val();
            $("#load").css({"display": ""});
            jQuery("#dataTable").html("");
            var url = "<%=request.getContextPath()%>/offerinfo/OfferSdk!list.action";
            jQuery.post(url,
                {
                    "offerSdkVO.operator": qoperator,
                    "offerSdkVO.isPage": 1,
                    "offerSdkVO.adName": qname,
                    "offerSdkVO.advLinkManId": qman,
                    "offerSdkVO.adverId": qadver,
                    "offerSdkVO.cou": qcou,
                    "offerSdkVO.status": state
                },
                function (response) {
                    if (response == "-1") {
                        showErrorToastMiddle("ϵͳ���������Ի���ϵ����Ա");
                        $("#load").css({"display": "none"});
                    } else {
                        jQuery("#dataTable").html(jQuery.trim(response));
                        $("#load").css({"display": "none"});
                    }
                });
        }


        var $modal = $('#ajax-modal');
        $modal.on('click', '.update', function () {
            $modal.modal('loading');
            setTimeout(function () {
                $modal.modal('loading').find('.modal-body')
                    .prepend('<div class="alert alert-info fade in">' +
                        'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '</div>');
            }, 100);
        });


        function modifyOfferSdk(path, id) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/OfferSdk!modify.action', {"offerSdkVO.id": id}, function () {
                    $modal.modal();
                });
            }, 100);
        }
        function createOfferSdk(path) {
            $('body').modalmanager('loading');
            setTimeout(function () {
                $modal.load(path + '/OfferSdk!create.action', {}, function () {
                    $modal.modal();
                });
            }, 100);
        }

        function deleteOfferSdk(path, id) {
            if (!sure('ȷ��Ҫɾ�� [' + id + '] ��')) {
                return;
            }
            jQuery.post(path + "/OfferSdk!delete.action", {"offerSdkVO.id": id},
                function (data) {
                    if (data == "-1") {
                        showErrorToast("��ʾ", "ϵͳ���������Ի���ϵ����Ա");
                    } else {
                        showInfoToastMiddle(data);
                        $("#apkinfo_" + id).remove();
                        searchList();
                    }
                });
        }

    </script>

</s:if>



