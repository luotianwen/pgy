<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:bean id="Option" name="com.kkgame.feeop.tag.Option" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<s:hidden id="cid" name="countryLevelVO.isUpdate"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h2><s:if test="countryLevelVO.isUpdate>0">修改配置</s:if><s:else >新建配置</s:else></h2>
</div>
<div class="modal-body">
	<form class="form-horizontal" style="margin: 0px;">
	<fieldset>
		<div class="control-group">
		<label class="control-label" for="countrynameKey">产品</label>
		<div class="controls">
			<s:if test="countryLevelVO.isUpdate==1">
				<s:hidden id="productId" name="countryLevelVO.productId"></s:hidden>
				<s:property value="countryLevelVO.productname"/>
			</s:if>
			<s:else>
            <s:select list="#Option.productList" listKey="id" listValue="name" id="productId" name="productId" cssClass="selectWidth" headerKey="0" headerValue="--所有产品--"></s:select>
           </s:else>  
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="countrynameKey">国家</label>
		<div class="controls">
			<s:if test="countryLevelVO.isUpdate==1">
				<s:hidden id="countryId" name="countryLevelVO.countryId"></s:hidden>
				<s:property value="countryLevelVO.countryname"/>
			</s:if>
			<s:else>
			<s:select list="#Option.countryList" listKey="id" listValue="name"  id="countryId" name="countryId"   cssClass="selectWidth" headerKey="1"  ></s:select>
			</s:else>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="levelKey">等级</label>
		<div class="controls">
			<s:select list="#Option.countryLevel" listKey="key" listValue="value" id="level"  value="countryLevelVO.level" cssClass="selectWidth" headerKey="1"  ></s:select>
		</div>
	</div>
	</fieldset>
	</form>
</div>
<div class="modal-footer">
 <button type="button" id="surebtn" onclick="javascript:updatecountryLevel('<%=path %>');" class="btn btn-primary"><s:if test="countryLevelVO.id>0">修 改</s:if><s:else >确 定</s:else></button>
 <button type="reset" class="btn">重设</button>
</div> 
<script type="text/javascript">

$("*[name=productId]").select2();

$("*[name=countryId]").select2();
$("#level").select2();
</script>