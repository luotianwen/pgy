package com.kkgame.hz.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.kkgame.hz.util.CalendarFormat;

public class CalendarTag extends TagSupport {

	private String from;
	private String to ;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	private List<String> getList() {
		List<String> aList = new ArrayList<String>();
		String toMonth = CalendarFormat.getStringCurrentMonth(); 
		if("thisMonth".equalsIgnoreCase(to)){
			toMonth = CalendarFormat.getStringCurrentMonth();
		}else if("lastMonth".equalsIgnoreCase(to)){
			toMonth = CalendarFormat.getStringLastMonth();
		}else if("nextMonth".equalsIgnoreCase(to)) {
		    toMonth = CalendarFormat.getDateString(CalendarFormat.getMonth(1), CalendarFormat.ymFormat);
		}
		int intTo = Integer.parseInt(toMonth);
		int intFrom = Integer.parseInt(from);
		// in desc order
		for (int i = intTo; i >= intFrom; i--) {
			if (i % 100 > 12 || i % 100 == 0) {
				continue;
			}
			aList.add(i + "");
		}
		return aList;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		List<String> aList = getList();
		StringBuilder sb = new StringBuilder();
		for (String month : aList) {
			sb.append("<option value=\"" + month + "\"> ");
			sb.append(month.substring(0, 4));
			sb.append("-");
			sb.append(month.substring(4));
			sb.append(" </option>");
		}
		try {
			out.write(sb.toString());
		} catch (Exception e) {
			throw new JspException(e.toString());
		}
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		from = null;
	}

}