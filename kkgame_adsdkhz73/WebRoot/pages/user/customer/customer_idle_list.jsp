<%@ page  isELIgnored ="false" contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page import="com.kkgame.hz.entities.*" %>
<%@ taglib uri="/WEB-INF/tld/pkig-role.tld" prefix="role"%>
<%@ taglib uri="/WEB-INF/tld/pkig-page.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Untitled Page</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css> 
</STYLE>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
<script type="text/javascript"  language="javascript" src="<%=request.getContextPath()%>/js/jquery-1.3.2.js"></script>
<script type="text/javascript"  language="javascript" src="<%=request.getContextPath()%>/js/jquery.ui.draggable.js"></script>
<script type="text/javascript"  language="javascript" src="<%=request.getContextPath()%>/js/jquery.alerts.js"></script>
<link href="<%=request.getContextPath()%>/css/kkfun.css"
  rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/js/jquery.alerts.css"
  rel="stylesheet" type="text/css" />

<script language="javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
</HEAD>
<BODY style="	margin-left: 3px;margin-top: 0px;margin-right: 3px;margin-bottom: 0px;">
<s:form id="customerForm" action="" onsubmit="return VerifyData(this);" theme="simple" >
<s:hidden  name="customerVO.id"/>
<s:hidden  name="customerVO.portalUserId"/>
<s:hidden  name="customerVO.sortIndex"/>
<s:hidden  name="customerVO.sortType"/>
<s:hidden   name="customerVO.newStatus" />
<s:hidden   name="customerVO.businessDeveloperId" />
<s:hidden   name="customerVO.status" />
<s:hidden   name="customerVO.relayBdId" />
<s:hidden   name="customerVO.relayStatus" />
<s:hidden   name="customerVO.callBackReason" />
<s:hidden   name="customerVO.abandonReason" />
<s:hidden   name="customerVO.expiryDate" />
<s:hidden name="customerVO.page.totalRow" ></s:hidden>	


<table align=center cellPadding=0 cellSpacing=0 id=tb_content>
		<tbody>
		<tr>
		<td width="56%" nowrap>
		<div class=div_subtitle>当前位置<span class="arrow_subtitle">&gt;</span>客户协调中心<span class="arrow_subtitle">&gt;</span>
		闲置的 客户列表</div></td>     
		<td width="44%" align="right" valign="bottom" nowrap>&nbsp; </td>
		</tr>
		</tbody>
	</table>

 <table class=tb_searchbar cellSpacing=1 width="100%">
		<TBODY>
			<TR> <TD colSpan=6 class="td_head"><IMG src="<%=request.getContextPath()%>/images/search1.gif" width="13" height="13" class=icon> 请填写查询条件 </TD>     
					                </TR>
		<TR><TD noWrap class="td_title">编号</TD>
		<TD class="detail"><INPUT id="searchId" name="customerVO.searchId"  value="<s:property value="customerVO.searchId"/>" style="WIDTH: 200px"  onkeyup="this.value=this.value.replace(/[^\d]/g,'');"> </TD>
		<TD noWrap class="td_title">名称</TD>
		<TD  class="td_detail"><INPUT id="searchName" name="customerVO.searchName" value="<s:property value="customerVO.searchName"/>" style="WIDTH: 200px" ></TD>		
		</TR>
	 <TR align="center">
    <TD colspan="4" noWrap align="right">
	<span style="float:right;PADDING-RIGHT: 10px;"><INPUT class=button type=button onclick="searchList()" value=查询 name=Submit52272>
    <INPUT class=button type=button onclick="removeall()" value=清空 name=Submit52273></span></TD>
	</TR></TBODY></table>
  <div style="padding-bottom: 5px;"></div>
 <table width="100%" style="WIDTH: 100%; BORDER-COLLAPSE: collapse"  cellSpacing=0 rules=all  border=0  class="tb_datalist">

		<tr  align=middle>
			<td width="5%" class="td_title" nowrap="nowrap"><span id="id"></span></td>
			<td width="10%" class="td_title" nowrap="nowrap"><span id="name"></span></td>
			<td width="10%" class="td_title" nowrap="nowrap"><span id="agentId"></span></td>
			<td width="10%" class="td_title" nowrap="nowrap"><span id="businessDeveloperId"></span></td>
			<td width="5%"  class="td_title" nowrap="nowrap"><span id="city"></span></td>
			<td width="10%" class="td_title" nowrap="nowrap"><span id="status"></span></td>
			<td width="15%" class="td_title" nowrap="nowrap">
			<s:if test="status ==0"><span id="createTime"></span></s:if>
			<s:if test="status ==1"><span id="submitTime"></span></s:if>
			<s:if test="status !=1"><span id="confirmTime"></span></s:if>
			</td>
			<td width="15%" class="td_title"  align="center" nowrap="nowrap">状态流转</td>
			<td width="10%" class="td_title"  align="center" nowrap="nowrap">操作</td>
		</tr>


<s:if test="customerList.size> 0"> 


		<s:iterator value="customerList" status="stat"  >
			<tr   onmouseover="javascript:changeBgcolor(this, '#FFFFD0')"
				onmouseout="javascript:changeBgcolor(this, '#FFFFFF')" >
				<td class=td_detail style="TEXT-ALIGN: left">C<s:property value="id"/></td>	
				<td class=td_detail style="TEXT-ALIGN: left"><a href="javascript:detail('<s:property value="id"/>','<s:property value="portalUserId"/>')"><s:property value="name"/></a></td>	
	
				<td class=td_detail>${agentName} </td>	
				<td class=td_detail>${businessDeveloperName}</td>				
				<td  class=td_detail>${city}</td>	
				<td class=td_detail>
					<s:if test="status==0">新建 </s:if> 
					<s:elseif test="status == 1">待审</s:elseif>
					<s:elseif test="status == 2">拓展中(剩<s:property value="expiryDate-leftDays" />天)
					<s:if test='(expiryDate-leftDays) > 0  && 3 >(expiryDate-leftDays)'><font color="red">预警</font> </s:if>
					</s:elseif>
					<s:elseif test="status == 3">审核未通过</s:elseif>
					<s:elseif test="status == 4">已签约</s:elseif>
					<s:elseif test="status == 5">放弃</s:elseif>
					<s:elseif test="status == 6">被收回(${callBackDays})</s:elseif>
					<s:elseif test="status == 7">已过期(<s:property value="leftDays-expiryDate" />)</s:elseif>
					<s:elseif test="status == 8">延期</s:elseif>
					<s:elseif test="status == 9">空闲的</s:elseif> <s:if test="relayStatus==10">(移交中)</s:if></td>
				<s:if test="status ==0">
				<td class=td_detail>${createTime}</td>	
				</s:if>				
				<s:elseif test="status ==1">
				<td class=td_detail>${submitTime}</td>	
				</s:elseif>
				<s:elseif  test="status >1 || relayStatus == 6 ">
				<td class=td_detail>${confirmTime}</td>	
				</s:elseif>
<!-- 流程控制开始 -->	
	<td class=td_detail nowrap="nowrap">


	<s:elseif test="status == 9">
		<!-- 客户收回后 原商务拓展人员 30天内不能申请 -->
		<s:if test="#session.SESSION_BD.id == relayBdId && callBackDays <=3 ">
		
		</s:if><s:else>
		<a href="javascript:doCustomerApply('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">申请</a>&nbsp;
		</s:else>
	</s:elseif>
		
<role:equal type="BD">
<!-- 商务人员 在客户非闲置状态 才有权移交放弃 -->
	<!-- relayStatus=8 申请延期状态 -->
	<s:if test='status ==7 && relayStatus !=8 && isMaster == "Y"'>
		<a href="javascript:deferred('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">申请延期</a>&nbsp;	
	</s:if>
<!-- isMaster ，是否是客户的所有者 -->
	<s:if test='status !=9 && isMaster == "Y"'>
			<s:if test="status !=1 ">
				<a href="javascript:devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">移交</a>&nbsp;	
				</s:if>
			<s:elseif test="status == 1">
				审核中
			</s:elseif>	
				<a href="javascript:abandon('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}','${name}')">放弃</a>&nbsp;	
	</s:if>
		
	</role:equal>
</s:if>

<!-- 转移状态的特殊处理 -->
<s:if test="relayStatus ==10 ">
		<role:equal type="AG,SP,BH">客户移交中</role:equal>
	<role:equal type="BD">
		<s:if test='isMaster.equals("Y")'>
		<a href="javascript:cancel_devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">取消转移</a>	
		</s:if><s:elseif test='isMaster.equals("N")'>
		待审核移交
		</s:elseif>	
</role:equal>
<role:equal type="MG">
		<a href="javascript:agree_devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">同意</a>
		<a href="javascript:disagree_devolve('${id}','${businessDeveloperId}','${status}','${relayBdId}','${relayStatus}')">不同意</a>
</role:equal>			
</s:if>
	</td>			
<!-- 流程控制结束 -->	
						
	<td class=td_detail align="center"  nowrap="nowrap">
	
		<role:equal type="BD">		
				<s:if test='isMaster.equals("Y")'>	
				<a href="javascript:modify('${id}','${portalUserId}');"> <img   src="<%=request.getContextPath()%>/images/edit.gif" alt="修改资料" border="0" /></a>&nbsp;	
				</s:if>		
		</role:equal>
		<role:equal type="AG">	
		<a href="javascript:modify('${id}','${portalUserId}');"> <img   src="<%=request.getContextPath()%>/images/edit.gif" alt="修改资料" border="0" /></a>&nbsp;			
		</role:equal>
		<!-- 新建和通过状态 -->
		<s:if test="(status == 0 ||  status == 3) ">
			<role:equal type="BD">	
		<!-- 只有自己的客户才能删除 -->
		 			<s:if test='isMaster.equals("Y")'>			
						<a href="javascript:remove('${id}','${portalUserId}','${name}')"><img src="<%=request.getContextPath()%>/images/del.gif" alt="删除" border="0" /></a>					
					</s:if>			
			</role:equal>		
			<role:equal type="AG">			
				<a href="javascript:remove('${id}','${portalUserId}','${name}')"><img src="<%=request.getContextPath()%>/images/del.gif" alt="删除" border="0" /></a>					
			</role:equal>			
		</s:if>						
		<role:equal type="SP,MG,BH">
		<a href="javascript:modify('${id}','${portalUserId}');"> <img   src="<%=request.getContextPath()%>/images/edit.gif" alt="修改资料" border="0" /></a>&nbsp;	
		<a href="javascript:remove('${id}','${portalUserId}','${name}')"><img src="<%=request.getContextPath()%>/images/del.gif" alt="删除" border="0" /></a>						
		</role:equal>			
			</td>
		</tr>
		</s:iterator>

	</table>
<s:if test="customerList.size> 0"> 
      <div class="pagelist" id="pagelist1" align="right"><page:pagination formName="customerForm" property="customerVO.page"
    operation="Customer!list.action"/></div> 
</s:if>

</s:form>
</BODY>
<%  %>
<script type="text/javascript">

if(document.getElementById("searchId").value == 0){
	document.getElementById("searchId").value ='';
}


var myForm = $("#customerForm")[0];
function showSort(){
	var sortIndex=myForm["customerVO.sortIndex"].value;
	var sortType=myForm["customerVO.sortType"].value;
	var status = myForm["customerVO.status"].value;

	
	

	//管理员   客户栏目 
	<role:equal type="SP,MG,BH">
	var objs=["id","name","agentId","businessDeveloperId","city","status","confirmTime"];
	var titles=["编号","客户名称","代理商","拓展人员","城市","状态","审核通过时间"];
	var items=[1,2,3,4,5,6,7,8];// sort by ...	
	</role:equal>
	
	//代理商   客户栏目 
	<role:equal type="AG">
	var objs=["id","name","businessDeveloperId","city","status","confirmTime"];
	var titles=["编号","客户名称","拓展人员","城市","状态","审核通过时间"];
	var items=[1,2,4,5,6,7,8];// sort by ...	
	</role:equal>

	//拓展人员   客户栏目 
	<role:equal type="BD">
	var objs=["id","name","city","status","confirmTime"];
	var titles=["编号","客户名称","城市","状态","审核通过时间"];
	var items=[1,2,5,6,7,8];// sort by ...	
	</role:equal>

	if(status == 0){
		for(var i=0;i<items.length;i++){		
	      //objs[i] = "submitTime";
	      if(items[i]==8){
	        titles[i]="创建时间";
	        items[i]== 9;
	      }
		}
	}
	if(status == 1){
		for(var i=0;i<items.length;i++){		
	      //objs[i] = "submitTime";
	      if(items[i]==8){
	        titles[i]="提交审核时间";
	        items[i]== 9;
	      }
		}
	}
	var imagePath = '<%=request.getContextPath()%>';
	sortTitle(imagePath,sortIndex,sortType,objs,titles,items);	
	}
	showSort();

	function doSort(sortIndex,sortType) {	
		myForm["customerVO.sortIndex"].value = sortIndex;
		myForm["customerVO.sortType"].value = sortType;
		doSearch();
	}

	function doSearch(){
		myForm.action = "Customer!list.action" ;
		myForm.submit(); 
	}
//提交审核	

function  submitConfirm(id,businessDeveloperId,status,relayBdId,relayStatus){	
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;				
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_WAIT_CONFIRM"/>';
		myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
		myForm.submit(); 
}
//申请闲置客户
function  doCustomerApply(id,businessDeveloperId,status,relayBdId,relayStatus){	
	myForm["customerVO.id"].value = id;
	myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
	myForm["customerVO.status"].value = status;
	myForm["customerVO.relayBdId"].value = relayBdId;
	myForm["customerVO.relayStatus"].value = relayStatus;				
	myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_WAIT_CONFIRM"/>';
	myForm.action = "<%=request.getContextPath()%>/Customer!customerApply.action";
	myForm.submit(); 
}



//进入审核页面
function  auditCustomer(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm.action = "Customer!gotoAuditCustomerView.action";
		myForm.submit(); 
}
//签约
function  sign_up(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
	myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_SIGN_UP"/>';
	myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
	myForm.submit(); 
}
//收回
function  call_back(id,businessDeveloperId,status,relayBdId,relayStatus,name){
	var newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_CALL_BACK"/>';
	var a = new Array(1);	
	 a = window.showModalDialog("<%=request.getContextPath()%>/Customer!gotoReasonEditView.action?customerVO.id="+id+"&customerVO.newStatus="+newStatus,window,"dialogWidth:600px;dialogHeight:400,center:yes,resizable:no,status:no");
	if(a != null){
		myForm["customerVO.callBackReason"].value =  a[0];
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;	

	myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_CALL_BACK"/>';
	myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
	myForm.submit(); 
	}
}

//移交客户
function  devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
	myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_DEVOLVE"/>';
	myForm.action = "<%=request.getContextPath()%>/Customer!gotoDevolveCustomer.action";
	myForm.submit(); 
}
//放弃客户
function  abandon(id,businessDeveloperId,status,relayBdId,relayStatus,name){
	var newStatus = '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_ABANDON"/>';
	var a = new Array(1);	
 	a = window.showModalDialog("<%=request.getContextPath()%>/Customer!gotoReasonEditView.action?customerVO.id="+id+"&customerVO.newStatus="+newStatus,window,"dialogWidth:600px;dialogHeight:400,center:yes,resizable:no,status:no");
	if(a != null){	
		myForm["customerVO.abandonReason"].value = a[0];
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_ABANDON"/>';
	myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
	myForm.submit(); 
	}	
}

//同意转移
function  agree_devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
	myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEVOLVE_AGREE"/>';
	myForm.action = "<%=request.getContextPath()%>/Customer!customerDevolve.action";
	myForm.submit(); 
}

//不同意转移
function  disagree_devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEVOLVE_DISAGREE"/>';
		myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
		myForm.submit(); 
}

//取消转移
function  cancel_devolve(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEVOLVE_CANCEL"/>';
		myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
		myForm.submit(); 
}
//申请延期
function  deferred(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_STATUS_DEFERRED"/>';
		myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
		myForm.submit(); 
}
//同意延期
function  agree_deferred(id,businessDeveloperId,status,relayBdId,relayStatus,expiryDate){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm["customerVO.expiryDate"].value = expiryDate;		
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMERS_DEFERRED_AGREE"/>';
		myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
		myForm.submit(); 
}
//不同意延期
function  disagree_deferred(id,businessDeveloperId,status,relayBdId,relayStatus){
		myForm["customerVO.id"].value = id;
		myForm["customerVO.businessDeveloperId"].value = businessDeveloperId;
		myForm["customerVO.status"].value = status;
		myForm["customerVO.relayBdId"].value = relayBdId;
		myForm["customerVO.relayStatus"].value = relayStatus;
		myForm["customerVO.newStatus"].value= '<s:property value="@com.kkgame.hz.base.PkigConstants@CUSTOMER_DEFERRED_DISAGREE"/>';
		myForm.action = "<%=request.getContextPath()%>/Customer!updateStruts.action";
		myForm.submit(); 
}

function  removeall(){
	myForm["customerVO.searchId"].value="";
	myForm["customerVO.searchName"].value="";
	document.all("searchId").value = "";
	document.all("searchName").value = "";
}

function searchList(){
	if(myForm["customerVO.searchId"].value==""){
		myForm["customerVO.searchId"].value=0;
		}
	myForm.action = "Customer!list.action";
	myForm.submit(); 
}

function create(){
	myForm.action = "Customer!create.action";
	myForm.submit(); 

}

function modify(id ,portalUserId){
		myForm["customerVO.id"].value = id ;
		myForm["customerVO.portalUserId"].value = portalUserId;
		myForm.action = "Customer!modify.action";
		myForm.submit(); 
}

function detail(id ,portalUserId){
	myForm["customerVO.id"].value = id ;
	myForm["customerVO.portalUserId"].value = portalUserId;
	myForm.action = "Customer!detail.action";
	myForm.submit(); 
}
	
function remove(id,portalUserId,name){
		if(!sure('确定要删除 ['+name+'] 吗？')){
            return;
    	}	
		myForm["customerVO.id"].value = id ;	
		myForm["customerVO.portalUserId"].value = portalUserId ;		
		myForm.action = "Customer!remove.action";
		myForm.submit(); 
}

 





</script>
</HTML>
