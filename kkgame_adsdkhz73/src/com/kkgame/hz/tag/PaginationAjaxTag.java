package com.kkgame.hz.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.views.jsp.TagUtils;

import com.kkgame.hz.base.PkigConstants;
import com.opensymphony.xwork2.util.ValueStack;


@SuppressWarnings("serial")
public class PaginationAjaxTag extends TagSupport {
	private String formName;
	private String property;
	private String operation;

	@Override
	public int doStartTag() {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		// pageContext.getResponse().setContentType("text/html; charset=GBK");
		try {
			JspWriter out = pageContext.getOut();
			ValueStack vS = TagUtils.getStack(pageContext);
			Pagination page = null;
			if (null != vS.findValue(property)){
				page = (Pagination) vS.findValue(property);
			}else{
				page = new Pagination();
			}	
			String pagePosition = "Bottom";
			StringBuilder sb = new StringBuilder();
			String strCR = "\n";
			sb.append("<script>").append(strCR);
			sb.append("  function doPage").append(pagePosition).append("(navigate) {").append(strCR);
			sb.append("    if (\"first\" == navigate) {").append(strCR);
			sb.append("      document.getElementById(\"pageNum\").value=1;").append(strCR);
			sb.append("    } else if (\"prev\" == navigate) {").append(strCR);
			sb.append("      var pageNumObj = document.getElementById(\"pageNum\");").append(strCR);
			sb.append("      pageNumObj.value = parseInt(pageNumObj.value) - 1;").append(strCR);
			sb.append("    } else if (\"next\" == navigate) {").append(strCR);
			sb.append("      var pageNumObj = document.getElementById(\"pageNum\");").append(strCR);
			sb.append("      pageNumObj.value = parseInt(pageNumObj.value) + 1;").append(strCR);
			sb.append("    } else if (\"last\" == navigate) {").append(strCR);
			sb.append("      document.getElementById(\"pageNum\").value = ").append(page.getPageCount()).append(";").append(strCR);
			sb.append("    } else if (\"turn\" == navigate) {").append(strCR);
			//sb.append("      document.getElementById(\"").append(property).append(".pageNum\").value = 1").append(strCR);
			sb.append("      document.getElementById(\"pageSize\").value = document.getElementById(\"pageSize\").value;").append(strCR);
			sb.append("    } else if (\"go\" == navigate) {").append(strCR);
			sb.append("      document.getElementById(\"pageNum\").value =document.getElementById(\"pageNum\").value;").append(strCR);
			sb.append("    }").append(strCR);
			sb.append("		var argus = getArgus();").append(strCR);
			sb.append("		if(argus==false) {").append(strCR);
			sb.append("			return false;").append(strCR);
			sb.append("		} else {").append(strCR);
			sb.append("			$(\"#load\").css({\"display\":\"\"});").append(strCR);
			sb.append("			jQuery(\"#dataTable\").html(\"\")").append(strCR);
			sb.append("			jQuery.post(\"").append(operation).append("\",").append(strCR);;
			//argus   pageSize pageNum
			sb.append("				argus,").append(strCR);;
			sb.append("				function(response){ ").append(strCR);
			sb.append("					if(response==\"1\") {").append(strCR);
			sb.append("						$(\"#mes\").html(\"<font color=\'red\'>系统出错，请重试或联系管理员!</font>\");").append(strCR);
			sb.append("						$(\"#load\").css({\"display\":\"none\"});").append(strCR);
			sb.append("					} else {").append(strCR);
			sb.append("						jQuery(\"#dataTable\").html(jQuery.trim(response));").append(strCR);
			sb.append("						$(\"#load\").css({\"display\":\"none\"});").append(strCR);
			sb.append("					}").append(strCR);
			sb.append("				});").append(strCR);
			sb.append("		}}").append(strCR);
			//sb.append("    document.getElementById(\"").append(formName).append("\").action = \"").append(operation)
			//		.append("\";").append(strCR);
			//sb.append("    document.getElementById(\"").append(formName).append("\").submit();").append(strCR);
			//sb.append("  }").append(strCR);
			sb.append("</script>").append(strCR);
			sb.append("<span>每页显示</span>").append(strCR);
			sb.append("<select class=\"input-small\" id=\"pageSize\" name = \"pageSize").append("\" onchange = \"doPage").append(
					pagePosition).append("('turn')\">").append(strCR);
	
			String pageSizeList = (String) pageContext.getServletContext().getAttribute("pageSizeList");
			if (pageSizeList == null) {
				pageSizeList = PkigConstants.PAGE_SIZE_LIST;
			}
			String[] arrPageSizeList = pageSizeList.split(",");
			for (int i = 0; i < arrPageSizeList.length; i++) {
				int pageSize = Integer.parseInt(arrPageSizeList[i]);
				sb.append("  <option value = \"").append(pageSize).append("\"");
				if (pageSize == page.getPageSize()) {
					sb.append(" selected");
				}
				sb.append(">").append(pageSize).append("</option>").append(strCR);
			}
	
			sb.append("</select>").append(strCR).append("条&nbsp;&nbsp;");
			sb.append("<span>页次:").append(page.getPageNum())
					.append("/").append(page.getPageCount()).append("页").append("&nbsp;&nbsp;记录总数:").append(page.getTotalRow())
					.append("</span>").append(strCR);
			if (page.getPageCount() == page.getPageNum()) {
				if (page.getPageCount() > 1) {
					sb.append("<a href = \"#\" onclick = \"doPage").append(pagePosition).append("('first')\"> 首页 </a>").append(
							strCR);
					sb.append("<a href = \"#\" onclick = \"doPage").append(pagePosition).append("('prev')\"> 前页 </a>")
							.append(strCR);
				} else {
					sb.append(" 首页 ").append(strCR);
					sb.append(" 前页 ").append(strCR);
				}
				sb.append(" 下页 ").append(strCR);
				sb.append(" 末页 ").append(strCR);
			} else {
				if (page.getPageNum() > 1) {
					sb.append("<a href = \"#\" onclick = \"doPage").append(pagePosition).append("('first')\"> 首页 </a>").append(
							strCR);
					sb.append("<a href = \"#\" onclick = \"doPage").append(pagePosition).append("('prev')\"> 前页  </a>").append(
							strCR);
				} else {
					sb.append(" 首页 </a>").append(strCR);
					sb.append(" 前页 ").append(strCR);
				}
				sb.append("<a href = \"#\" onclick = \"doPage").append(pagePosition).append("('next')\"> 下页 </a>").append(strCR);
				sb.append("<a href = \"#\" onclick = \"doPage").append(pagePosition).append("('last')\"> 末页 </a>").append(strCR);
			}
	
			sb.append("&nbsp;&nbsp;&nbsp;<input class=\"input-mini\" onkeyup=\"this.value=this.value.replace(/[^\\d]/g,'');\" type = \"text\" id = \"pageNum").append("\" name = \"pageNum")
					.append("\" value = \"").append(page.getPageNum()).append("\" size = \"3\">").append(strCR);
			sb.append("<input type = \"button\" class=\"btn\" value = \"跳转\" onclick = \"doPage").append(pagePosition).append("('go')\"/>")
					.append(strCR);

			out.write(sb.toString());
		} catch (Exception e) {
			throw new JspException(e.toString());
		}
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		formName = null;
		property = null;
		operation = null;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getFormName() {
		return formName;
	}

	public String getProperty() {
		return property;
	}

	public String getOperation() {
		return operation;
	}
}