<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<s:hidden id="cid" name="adjustVO.id"/>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h2><s:if test="adjustVO.id>0">修改Adjust</s:if><s:else>新建Adjust</s:else></h2>
</div>
<div class="modal-body">
    <form class="form-horizontal" style="margin: 0px;">
        <fieldset>


            <div class="control-group">
                <label class="control-label" for="cpnameKey">产品名称</label>
                <div class="controls">
                    <input id="cpname" type="text" class="input-medium" name="adjustVO.cpname"
                           value="<s:property value="adjustVO.cpname"/>"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="usernameKey">帐号</label>
                <div class="controls">
                    <input id="username" type="text" class="input-medium" name="adjustVO.username"
                           value="<s:property value="adjustVO.username"/>"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="passwKey">密码</label>
                <div class="controls">
                    <input id="passw" type="text" class="input-medium" name="adjustVO.passw"
                           value="<s:property value="adjustVO.passw"/>"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="apkKey">应用识别码</label>
                <div class="controls">
                    <input id="apk" type="text" class="input-medium" name="adjustVO.apk"

                           value="<s:property value="adjustVO.apk"/>"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="campaignKey">CAMPAIGN</label>
                <div class="controls">
                    <input id="campaign" type="text" class="input-medium" name="adjustVO.campaign"
                           value="<s:property value="adjustVO.campaign"/>" size="50"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="httpsKey">HTTPS</label>
                <div class="controls">
                    <input id="https" type="text" class="input-medium" name="adjustVO.https"
                           value="<s:property value="adjustVO.https"/>" size="50"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="sdateKey">帐号申请时间</label>
                <div class="controls">
                    <input id="sdate" type="text" onfocus="javascript:WdatePicker()" readonly="readonly" class="input-medium" name="adjustVO.sdate"
                           value="<s:property value="adjustVO.sdate"/>"/>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="psdateKey">帐号过期时间</label>
                <div class="controls">
                    <input id="psdate" type="text" onfocus="javascript:WdatePicker()" readonly="readonly" class="input-medium" name="adjustVO.psdate"
                           value="<s:property value="adjustVO.psdate"/>"/>
                </div>
            </div>


        </fieldset>
    </form>
</div>
<div class="modal-footer">
    <button type="button" id="surebtn" onclick="javascript:updateadjust('<%=path %>');" class="btn btn-primary"><s:if
            test="adjustVO.id>0">修 改</s:if><s:else>确 定</s:else></button>
</div>
