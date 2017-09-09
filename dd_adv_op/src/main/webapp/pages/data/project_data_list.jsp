<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kkgame.feeop.user.bean.UserVO"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
UserVO userVO = (UserVO)session.getAttribute("SESSION_USER");
%>
<s:if test="projectDataVOList.size==0">
<font color="red">暂无数据</font>
</s:if>
<s:else>
		<s:if test="searchVO.type!=600405&&searchVO.type!=600406">
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>推送人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装人数</th>
		      		<th>安装数</th>
					<th>安装率</th>
					<th>aipu</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
				<td><s:property value="pushSendCount"/></td>
				<td><s:property value="pushSendTimes"/></td>
      			<td><s:property value="pushReceiveTimes"/></td>
      			<td><s:property value="receivePushPercent"/>%</td>
				<td><s:property value="pushShowCount"/></td>
				<td><s:property value="pushShowTimes"/></td>
				<td><s:property value="showPushPercent"/>%</td>
      			<td><s:property value="pushClickTimes"/></td>
 				<td><s:property value="clickPushPercent"/>%</td>
      			<td><s:property value="pushDownloadTimes"/></td>
   				<td><s:property value="downloadPushPercent"/>%</td>
      			<td><s:property value="pushInstallCount"/></td>
      			<td><s:property value="pushInstallTimes"/></td>
   			   	<td><s:property value="installPushPercent"/>%</td>
   			   	<td><s:property value="pushAipu"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<div class="box box-info" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>插屏人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装人数</th>
		      		<th>安装数</th>
					<th>安装率</th>			
					<th>aipu</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
   				<td><s:property value="sendCount"/></td>
   				<td><s:property value="sendTimes"/></td>
      			<td><s:property value="receiveTimes"/></td>
      			<td><s:property value="receivePercent"/>%</td>
				<td><s:property value="showCount"/></td>
				<td><s:property value="showTimes"/></td>
				<td><s:property value="showPercent"/>%</td>
      			<td><s:property value="clickTimes"/></td>
   				<td><s:property value="clickPercent"/>%</td>
      			<td><s:property value="downloadTimes"/></td>
   				<td><s:property value="downloadPercent"/>%</td>
      			<td><s:property value="installCount"/></td>
      			<td><s:property value="installTimes"/></td>
   			   	<td><s:property value="installPercent"/>%</td>
   			   	<td><s:property value="aipu"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:if>
		
		<s:if test="searchVO.type==600400||searchVO.type==0">
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>下沉人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装人数</th>
		      		<th>安装数</th>
					<th>安装率</th>
					<th>aipu</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       				<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator>
				</td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
 				<td>-</td>
      			<td><s:property value="sinkDownloadTimes"/></td>
   				<td><s:property value="downloadSinkPercent"/>%</td>
      			<td><s:property value="sinkInstallCount"/></td>
      			<td><s:property value="sinkInstallTimes"/></td>
   			   	<td><s:property value="installSinkPercent"/>%</td>
   			   	<td><s:property value="sinkAipu"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<div class="box box-primary" style="overflow:auto">
		<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>引导人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装人数</th>
		      		<th>安装数</th>
					<th>安装率</th>
					<th>aipu</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
				<td>-</td>
				<td>-</td>
      			<td>-</td>
      			<td>-</td>
				<td><s:property value="leadShowCount"/></td>
				<td><s:property value="leadShowTimes"/></td>
				<td><s:property value="showLeadPercent"/>%</td>
      			<td><s:property value="leadClickTimes"/></td>
 				<td><s:property value="clickLeadPercent"/>%</td>
      			<td><s:property value="leadDownloadTimes"/></td>
   				<td><s:property value="downloadLeadPercent"/>%</td>
      			<td><s:property value="leadInstallCount"/></td>
      			<td><s:property value="leadInstallTimes"/></td>
   			   	<td><s:property value="installLeadPercent"/>%</td>
   			   	<td><s:property value="leadAipu"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:if>
		<s:if test="searchVO.type==600405||searchVO.type==600400||searchVO.type==0">
		<div class="box box-primary" style="overflow:auto">
			<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		<th>线下人数</th>
		      		<th>次数</th>
		      		<th>成功数</th>
   				    <th>接收率</th>
		      		<th>展示人数</th>
		      		<th>展示数</th>
					<th>展示率</th>
		      		<th>点击数</th>
		      		<th>点击率</th>
		      		<th>下载数</th>
					<th>下载率</th>
		      		<th>安装人数</th>
		      		<th>安装数</th>
					<th>安装率</th>
		      		<th>激活数</th>
					<th>aipu</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
				<td><s:property value="silenceSendCount"/></td>
				<td><s:property value="silenceSendTimes"/></td>
      			<td><s:property value="silenceReceiveTimes"/></td>
      			<td><s:property value="receiveSilencePercent"/>%</td>
				<td><s:property value="silenceShowCount"/></td>
				<td><s:property value="silenceShowTimes"/></td>
				<td><s:property value="showSilencePercent"/>%</td>
      			<td><s:property value="silenceClickTimes"/></td>
 				<td><s:property value="clickSilencePercent"/>%</td>
      			<td><s:property value="silenceDownloadTimes"/></td>
   				<td><s:property value="downloadSilencePercent"/>%</td>
      			<td><s:property value="silenceInstallCount"/></td>
      			<td><s:property value="silenceInstallTimes"/></td>
   			   	<td><s:property value="installSilencePercent"/>%</td>
      			<td><s:property value="activateTimes"/></td>
   			   	<td><s:property value="silencePercent"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:if>
		<s:if test="searchVO.type==600406||searchVO.type==0">
		<div class="box box-primary" style="overflow:auto">
			<table class="table table-bordered table-striped table-hover">
			<s:iterator value="projectDataVOList" status="stat">
				<s:if test="#stat.index%30 ==0">
					<thead>
					<tr >
					<s:if test="searchVO.rowFieldVO.isShowDate==1"><th>时间</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowAgent==1"><th>代理</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowBd==1"><th>商务</th></s:if>
					<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><th>客户</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><th>项目</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><th>产品</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><th>国家</th></s:if>
		      		<s:if test="searchVO.rowFieldVO.isShowType==1"><th>类型</th></s:if>
		      		<th>销量</th>
		      		<th>项目销量</th>
		      		<th>活跃</th>
		      		<th>总活跃</th>
		      		</tr>
		      		</thead>
		      	</s:if>
		      	<tr>
				<s:if test="searchVO.rowFieldVO.isShowDate==1"><td><s:property value="statDate"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowAgent==1"><td><s:property value="agentName"/></td></s:if>
				<s:if test="searchVO.rowFieldVO.isShowBd==1"><td><s:property value="bdName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCustomer==1"><td><s:property value="customerName"/></td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProject==1"><td><s:property value="projectName"/>[<s:property value="projectId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowProduct==1"><td><s:property value="productName"/>[<s:property value="productId"/>]</td></s:if>
	      		<s:if test="searchVO.rowFieldVO.isShowCountry==1"><td><s:property value="countryName"/></td></s:if>
       			<s:if test="searchVO.rowFieldVO.isShowType==1"><td>
       			<s:iterator value="#Option.typeList">
       				<s:if test="key==type">
       					<s:property value="value"/>
       				</s:if>
       			</s:iterator></td></s:if>  
      			<td><s:property value="userCount"/></td>
      			<td><s:property value="projectUserCount"/></td>
				<td><s:property value="activeCount"/></td>
				<td><s:property value="totalActiveCount"/></td>
				</tr>
			</s:iterator>
		</table>
		</div>
		</s:if>
</s:else>