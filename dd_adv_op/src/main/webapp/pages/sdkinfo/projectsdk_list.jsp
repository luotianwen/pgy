<%--
  User: XJ
  Date: 2016/4/1
  Time: 10:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/tld/pkig-page-ajax.tld" prefix="page"%>

<%
    String path = request.getContextPath() + "/sdkinfo";
    String basep= request.getContextPath();
%>

<s:if test="projectSdkVO.isPage==0">
<div class="row-fluid" style="min-height: 40px;padding-top: 20px">
    <div class="span12">
        <div class="form-inline">
            <fieldset>
                <table style="width:100%">
                    <tr>
                        <td>
                            <input class="btn btn-success" type="button" onclick="create()" value="新增"/>
                            <input class="btn btn-primary" type="button" onclick="importData()" value="导入项目数据">
                            <input class="btn btn-primary" onclick="downloadTemplate()" type="button" value="下载导入模板">
                            <form id="exportForm"/>
                        </td>
                        <td align="right">
                            <div id="ajax-modal" class="modal fade" tabindex="-1"></div>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            项目Id
                            <select id="projectId" name="searchVO.projectId" class="selectWidth">
                                <s:action name="getAllProject" namespace="/customer" executeResult="true">
                                </s:action>
                            </select>
                            sdkId
                            <select id="sdkId" name="searchVO.sdkId" class="selectWidth">
                                <s:action name="getAllThreeSdk" namespace="/sdkinfo" executeResult="true">
                                </s:action>
                            </select>
                            <button type="button" class="btn btn-primary" onclick="searchList()">查 询</button>
                            <button type="reset" class="btn btn-primary">清 空</button>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
</div>
<div class="box box-primary" style="margin-top: 15px;overflow:auto">
    <div class="span12">
        <form class="form-horizontal">
            <fieldset>
                <div>
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th>项目Id</th>
                                <th>sdkId</th>
                                <th>key</th>
                                <th>说明</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="dataTable">
</s:if>
                        <s:iterator value="projectSdkVOList">
                        <tr>
                            <td>
                                <s:property value="projectName"/>
                                [<s:property value="projectId"/>]
                            </td>
                            <td>
                                <s:property value="sdkName"/>
                                [<s:property value="sdkId"/>]
                            </td>
                            <td>
                                <s:property value="sdkKey"/>

                            </td>
                            <td>
                                <s:property value="note"/>

                            </td>
                            <td>
                                <a class="btn btn-primary"
                                   href="javascript: modify('<s:property value="projectId"/>','<s:property value="sdkId"/>')" title="修改">
                                    修改
                                </a>
                                <a class="btn btn-primary"
                                   href="javascript: delect('<s:property value="projectId"/>','<s:property value="projectName"/>',
                                   '<s:property value="sdkId"/>','<s:property value="sdkName"/>',
                                   '<s:property value="sdkKey"/>','<s:property value="note"/>')" title="删除">
                                    删除
                                </a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="7">
                                <div class="pagelist" id="pagelist1" align="right">
                                    <page:paginationAjax formName="projectSdkForm" property="projectSdkVO.page"
                                                         operation="/sdkinfo/ThreeSdk!projectSdkList.action"/>
                                </div>
                            </td>
                        </tr>
<s:if test="projectSdkVO.isPage==0">
                        </tbody>
                    </table>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<script type="text/javascript">
    $("#projectId").select2();
    $("#sdkId").select2();

    function getArgus() {
        var argus ="projectSdkVO.page.pageNum="+$("#pageNum").val()
                +"&projectSdkVO.page.pageSize="+$("#pageSize").val()
                +"&projectSdkVO.isPage=1";
        // 查询参数
        argus += "&projectSdkVO.projectId="+$("#projectId").val()
                +"&projectSdkVO.sdkId="+$("#sdkId").val();
        return argus;
    }
    function searchList() {
        var projectId = $("#projectId").val();
        var sdkId = $("#sdkId").val();

        $("#load").css({"display":""});
        jQuery("#dataTable").html("");
        var url = "<%=path%>/ThreeSdk!projectSdkList.action";
        jQuery.post(url,
                {
                    "projectSdkVO.projectId":projectId,
                    "projectSdkVO.sdkId":sdkId,
                    "projectSdkVO.isPage":1
                },
                function(response){
                    if(response=="-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display":"none"});
                    } else {
                        jQuery("#dataTable").html(jQuery.trim(response));
                        $("#load").css({"display":"none"});
                    }
                });
    }
    function create() {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load('<%=path%>/ThreeSdk!projectSdkCreate.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function modify(projectId, sdkId) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load('<%=path%>/ThreeSdk!projectSdkModify.action',
                    {"projectSdkVO.projectId": projectId, "projectSdkVO.sdkId": sdkId}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function delect(projectId, projectName, sdkId, sdkName, sdkKey, note) {
        if(!sure('确定要删除[项目:'+projectId+',sdk:'+sdkId+']吗？')){
            return;
        }
        jQuery("#dataTable").html("");
        var url = "<%=path%>/ThreeSdk!projectSdkDelect.action";
        jQuery.post(url,
                {
                    "projectSdkVO.projectId":projectId,
                    "projectSdkVO.projectName":projectName,
                    "projectSdkVO.sdkId":sdkId,
                    "projectSdkVO.sdkKey":sdkKey,
                    "projectSdkVO.note":note,
                    "projectSdkVO.sdkName":sdkName,
                    "projectSdkVO.isPage":1
                },
                function(response){
                    if(response=="-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display":"none"});
                    } else {
                        jQuery("#dataTable").html(jQuery.trim(response));
                        $("#load").css({"display":"none"});
                        doPageBottom('turn');
                    }
                });
    }
    function importData() {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load('<%=basep%>/pages/sdkinfo/projectthree_importdata.jsp', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function downloadTemplate() {
        var exportForm = document.getElementById("exportForm");
        exportForm.action="<%=path%>/ThreeSdk!downloadTemplate.action";
        exportForm.submit();
    }
</script>
</s:if>