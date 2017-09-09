<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-calendar.tld" prefix="calendar"%>
<s:bean id="Policy" name="com.kkgame.hz.tag.Policy"/>
<s:hidden id="projectId" name="projectVO.id"></s:hidden>
<s:hidden id="queryType" name="projectVO.queryType"></s:hidden>
	<div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
	<table class="table">
		<tr>
			<td class="table_td_title">项目名称-ID<font color="red">*</font></td>			 
            <td>
            	<s:property value="projectVO.name"/>&nbsp;&nbsp;-&nbsp;&nbsp;P.[<font color="red"><s:property value="projectVO.id"/></font>]
            </td>
            <td class="table_td_title">渠道商名称<font color="red">*</font></td>			 
            <td><s:property value="projectVO.schemeName"/></td>
		</tr>	
		<role:equal type="SP,MG,BD">
		<tr >
			<td class="table_td_title">所属代理商<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.agentList">
					<s:if test="id==projectVO.agentId">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>	
		<tr ><td class="table_td_title">商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		<tr ><td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		</role:equal>
		<role:equal type="AG">
		<tr ><td class="table_td_title">商务拓展人员<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bdList">
					<s:if test="projectVO.bdId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>
		<tr ><td class="table_td_title">商务协助人员</td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.bhList">
					<s:if test="projectVO.bhId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>			
		</tr>
		</role:equal>				
		<role:equal type="SP,MG,AG,BD">	
		<tr ><td class="table_td_title">客户<font color="red">*</font></td>	
			<td width="85%"  nowrap="nowrap" colspan="3">
				<s:iterator value="#Policy.customerList">
					<s:if test="projectVO.customerId==id">
						<s:property value="name"/>
					</s:if>
				</s:iterator>
			</td>		
		</tr>
		</role:equal>
		<tr >
			<td class="table_td_title">合作模式<font color="red">*</font></td>			 
            <td >
            	<s:iterator value="#Policy.cooperateTypeList">
					<s:if test="projectVO.cooperateType==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">推广方式<font color="red">*</font></td>			 
            <td>
            	<s:iterator value="#Policy.populariseTypeList">
					<s:if test="projectVO.populariseType==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">平台<font color="red">*</font></td>			 
            <td >
            	<s:iterator value="#Policy.osList">
					<s:if test="projectVO.osId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
			<td class="table_td_title">分辨率<font color="red">*</font></td>			 
            <td>
            	<s:iterator value="#Policy.screenList">
					<s:if test="projectVO.screenId==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">产品<font color="red">*</font></td>			 
            <td colspan="3" id="inData">
            	
			</td>
		</tr>
		<tr >
			<td class="table_td_title">是否去掉LOGO<font color="red">*</font></td>			 
            <td colspan="3">
            	<s:if test="projectVO.isRemoveLogo==0">是</s:if>
            	<s:if test="projectVO.isRemoveLogo==1">否</s:if>
			</td>
		</tr>		
		<tr >
			<td class="table_td_title">机型价格</td>			 
            <td colspan="3">
            	<s:iterator value="#Policy.priceList">
					<s:if test="projectVO.price==key">
						<s:property value="value"/>
					</s:if>
				</s:iterator>
			</td>
		</tr>	
		<tr >
			<td class="table_td_title">备注</td>			 
            <td colspan="3"><textarea cols="50" rows="5" ><s:property value="projectVO.intro"/></textarea></td>
		</tr>
	</table>	
	<div style="padding-bottom: 5px"></div>
	<table class="table">
		<tr >
			<td class="table_td_title">产品</td>
			<td class="table_td_title">合作模式</td>
			<td class="table_td_title">角色</td>
			<td class="table_td_title">不可卸载激活单价(<font color="red">分</font>)</td>
			<td class="table_td_title">可卸载激活单价(<font color="red">分</font>)</td>	
			<td class="table_td_title">分成比例</td>	
			<td class="table_td_title">操作</td>	
		</tr>
		<s:iterator value="existProductVOList" status="stat">			
		<tr >
			<td rowspan="<s:if test="projectVO.bhId>0">3</s:if><s:else>2</s:else>">
				<s:property value="name"/>
				<input type="hidden" name="productVOList[<s:property value="#stat.index"/>].id" value="<s:property value="id"/>" />
			</td>
			<td rowspan="<s:if test="projectVO.bhId>0">3</s:if><s:else>2</s:else>">
				<s:if test="cooperateMode==1">激活</s:if>
				<s:if test="cooperateMode==2">分成</s:if>
			</td>
			<td >客户</td>		
			<td id="cuInPrice_<s:property value="id"/>">
				<s:if test="cooperateMode==1"><s:property value="cuBuildInRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
				
			</td>
			<td id="cuPrice_<s:property value="id"/>">
				<s:if test="cooperateMode==1"><s:property value="cuUninstallRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
			</td>
			<td id="cuPercent_<s:property value="id"/>">				
				<s:if test="cooperateMode==1">-</s:if>
				<s:if test="cooperateMode==2"><s:property value="cuDividePercent"/></s:if>
			</td>
			<td>
				<input type="button" class="btn btn-primary" id="btn1" onclick="modifyPrice('<s:property value="projectId"/>','<s:property value="id"/>');" title="修改价格" value=" 修 改 " />&nbsp;
				
			</td>
		</tr>
		<tr>
			<td>商务拓展人员</td>
			<td id="bdInPrice_<s:property value="id"/>">
				<s:if test="cooperateMode==1"><s:property value="bdBuildInRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
				
			</td>
			<td id="bdPrice_<s:property value="id"/>">
				<s:if test="cooperateMode==1"><s:property value="bdUninstallRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
			</td>
			<td id="bdPercent_<s:property value="id"/>">				
				<s:if test="cooperateMode==1">-</s:if>
				<s:if test="cooperateMode==2"><s:property value="bdDividePercent"/></s:if>
			</td>
			<td></td>
		</tr>
		<s:if test="projectVO.bhId>0">
		<tr>
			<td>商务协助人员</td>
			<td id="bhInPrice_<s:property value="id"/>">
				<s:if test="cooperateMode==1"><s:property value="bhBuildInRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
				
			</td>
			<td id="bhPrice_<s:property value="id"/>">
				<s:if test="cooperateMode==1"><s:property value="bhUninstallRegisterPrice"/></s:if>
				<s:if test="cooperateMode==2">-</s:if>
			</td>
			<td id="bhPercent_<s:property value="id"/>">				
				<s:if test="cooperateMode==1">-</s:if>
				<s:if test="cooperateMode==2"><s:property value="bhDividePercent"/></s:if>
			</td>
			<td></td>
		</tr>
		</s:if>
		</s:iterator>
	</table>
	<div style="padding-bottom: 5px"></div>
	<table id="uploadTable" class="table">
		<tr>
			<td class="table_td_title">价格审核意见&nbsp;<font color="red">*</font></td>
			<td>
			<input type="radio" name="auditResult" value="Y" > 
				通过 <input type="radio"
					name="auditResult" value="N"> 
				不通过</td>
		</tr>
		<tr id="cause" >
			<td class="table_td_title">不通过原因</td>			 
            <td ><textarea id="auditFailReason" name="projectVO.confirmReason" cols="53" rows="5" ></textarea></td>
		</tr>
		<tr >
		    <td  colspan="2">
			<div align="center">
				<input type="button" class="btn btn-primary" id="btn1" onclick="doConfirm();" value=" 提 交 " />&nbsp;
			   <input id="btn2" class="btn" type="button" value=" 返 回 " onclick="doBack('<s:property value="projectVO.queryType"/>');"/>&nbsp;&nbsp;
		     </div>
			</td>
		</tr>
	</table>
	
<script type="text/javascript">
	function doConfirm() {
		var id = $("#projectId").val();
		var queryType = $("#queryType").val();
		var auditResult = '';	
	 	var auditFailReason='';
	 	var newStatus=0;
        auditResult = $("input[name=auditResult]:checked").val();
	   	if(auditResult == 'Y'){
	   		newStatus = 2;
	   	} else if(auditResult == 'N'){
	   		$("#auditFailReason").val($.trim($("#auditFailReason").val()));
		 	auditFailReason = $("#auditFailReason").val();
		 	if(auditFailReason == null || auditFailReason == ''){
		 		showInfoToastMiddle("请填写不通过原因！");
		 		$("#auditFailReason").focus();
		 		return false;
			}
			newStatus=3;
	   	} else {
	   		showInfoToastMiddle("请选择是否通过！");
	   		return false;
	   	}
	   	jQuery.post("Project!updatePriceStatus.action",{"projectVO.id":id,"projectVO.queryType":queryType,"projectVO.priceStatus":newStatus,"projectVO.priceText":auditFailReason},function(data){				
			if(data == "0"){					
				showErrorToastMiddle("系统出错，请重试或联系管理员");
				return ;
			} else if(newStatus=="2") {
				//出包
				$('#containerData').html(data);
				return ;
			} else if(newStatus=="3"){
				//加载详情
				$('#containerData').html(data);
				return ;
			}
		});	
	}
	
	var $modal = $('#ajax-modal'); 
		$modal.on('click', '.update', function(){
			$modal.modal('loading');
		  	setTimeout(function(){
		    	$modal.modal('loading').find('.modal-body')
		        .prepend('<div class="alert alert-info fade in">' +
		        'Updated!<button type="button" class="close" data-dismiss="alert">&times;</button>' +
		        '</div>');
		  	}, 1000);
		});
	function modifyPrice(projectId,productId) {	
		$('body').modalmanager('loading');
		  setTimeout(function(){
	  		$modal.load('Project!modifyPrice.action?<%=Math.random()*1999.0 %>', {"productVO.id":productId,"productVO.projectId":projectId}, function(){
	     		$modal.modal();
	    	});
		  }, 1000);
		/**
		jQuery.facebox('<div><iframe src=\"<%=request.getContextPath()%>/Product!modify.action?productVO.id='+id+'&<%=Math.random()*1999.0 %>\" width=\"520px\" height=\"300px\" frameborder=\"0\"></iframe>')
		var box = null;
		while(box==null) {
			box = jQuery('#facebox');
		}
		jQuery('#facebox').css('top',100);
		*/
	}
</script>	
