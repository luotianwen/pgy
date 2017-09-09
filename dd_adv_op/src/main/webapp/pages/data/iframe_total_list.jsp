<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.kkgame.feeop.user.bean.UserVO" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option"/>
<%
    UserVO userVO = (UserVO) session.getAttribute("SESSION_USER");
%>
<s:if test="iframeDataVOList.size==0">
    <font color="red">暂无数据</font>
</s:if>
<s:else>
    <s:set name="activeTimes" value="0"/>
    <s:set name="totalShowTimes" value="0"/>
    <s:set name="totalClickTimes" value="0"/>

    <div class="box box-primary" style="overflow:auto">
        <table class="table table-bordered table-striped table-hover">
            <thead>
            <tr>
                <s:if test="searchVO.rowFieldVO.isShowDate==1">
                    <th>日期</th>
                </s:if>
                <s:if test="searchVO.rowFieldVO.isShowAd==1">
                    <th>广告</th>
                </s:if>
                <s:if test="searchVO.rowFieldVO.isShowProject==1">
                    <td>项目</td>
                </s:if>
                <s:if test="searchVO.rowFieldVO.isShowCountry==1">
                    <th>国家</th>
                </s:if>
                <s:if test="searchVO.rowFieldVO.isShowOperator==1">
                    <th>运营商</th>
                </s:if>
                <th>展示次数</th>
                <th>激活量</th>
                <th>收入</th>
            </tr>
            </thead>

            <s:iterator value="iframeDataVOList">
                <tr>
                    <s:if test="searchVO.rowFieldVO.isShowDate==1">
                        <td><s:property value="cdate"/></td>
                    </s:if>
                    <s:if test="searchVO.rowFieldVO.isShowAd==1">
                        <td><s:property value="pidName"/>[<s:property value="pid"/>]</td>
                    </s:if>
                    <s:if test="searchVO.rowFieldVO.isShowProject==1">
                        <td><s:property value="cid"/></td>
                    </s:if>
                    <s:if test="searchVO.rowFieldVO.isShowCountry==1">
                        <td><s:property value="couName"/></td>
                    </s:if>

                        <s:if test="searchVO.rowFieldVO.isShowOperator==1"><td><s:property value="operator"/>[<s:property value="operatorId"/>]</td></s:if>
                    <td><s:property value="totalShowCount"/></td>
                    <td><s:property value="activeCount"/></td>
                    <td><s:property value="payout"/></td>
                </tr>
            </s:iterator>
        </table>
    </div>
</s:else>