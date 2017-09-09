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

                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="dataTable">
</s:if>
                        <s:iterator value="projectSdkVOList">
                        <tr>
                            <td>

                                 <s:property value="projectId"/>
                            </td>

                            <td>
                                <a class="btn btn-primary"
                                   href="javascript: modify('<s:property value="sdkId"/>')" title="修改">
                                    修改
                                </a>
                                <a class="btn btn-primary"
                                   href="javascript: delect('<s:property value="sdkId"/>')" title="删除">
                                    删除
                                </a>
                            </td>
                        </tr>
                        </s:iterator>
                        <tr>
                            <td colspan="2">
                                <div class="pagelist" id="pagelist1" align="right">
                                    <page:paginationAjax formName="projectSdkForm" property="projectSdkVO.page"
                                                         operation="/sdkinfo/ThreeSdk!insidelist.action"/>
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


    function getArgus() {
        var argus ="projectSdkVO.page.pageNum="+$("#pageNum").val()
                +"&projectSdkVO.page.pageSize="+$("#pageSize").val()
                +"&projectSdkVO.isPage=1";
        // 查询参数
        argus += "&projectSdkVO.projectId="+$("#projectId").val();
        return argus;
    }
    function searchList() {
        var projectId = $("#projectId").val();
        $("#load").css({"display":""});
        jQuery("#dataTable").html("");
        var url = "<%=path%>/ThreeSdk!insidelist.action";
        jQuery.post(url,
                {
                    "projectSdkVO.projectId":projectId,
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
            $modal.load('<%=path%>/ThreeSdk!insidelistCreate.action', {}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function modify( sdkId) {
        var $modal = $('#ajax-modal');
        $('body').modalmanager('loading');
        setTimeout(function () {
            $modal.load('<%=path%>/ThreeSdk!insidelistModify.action',
                    { "projectSdkVO.sdkId": sdkId}, function () {
                $modal.modal();
            });
        }, 100);
    }
    function delect(projectId) {
        if(!sure('确定要删除项目:'+projectId+'吗？')){
            return;
        }

        var url = "<%=path%>/ThreeSdk!insideDelete.action";
        jQuery.post(url,
                {
                    "projectSdkVO.sdkId":projectId,
                    "projectSdkVO.isPage":1
                },
                function(response){
                    if(response=="-1") {
                        showErrorToastMiddle("系统出错，请重试或联系管理员");
                        $("#load").css({"display":"none"});
                    } else {
                        $("#load").css({"display":"none"});
                        showInfoToastMiddle(response);
                        doPageBottom('turn');
                    }
                });
    }

</script>
</s:if>