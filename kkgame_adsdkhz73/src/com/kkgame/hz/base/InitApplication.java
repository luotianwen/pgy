package com.kkgame.hz.base;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitApplication extends HttpServlet {

	public void init() throws ServletException {
		PkigConstants.KKFUN_WEB_CONTEXT = getServletContext();
		PkigConstants.KKFUN_WEB_ROOT = new File(getServletContext().getRealPath("WEB-INF")).getParent().replaceAll("\\\\",
				"/");
		PkigConstants.EXCEL_TEPLATE_PATH = PkigConstants.KKFUN_WEB_ROOT + "/WEB-INF/classes/report/template/";

	}
}
