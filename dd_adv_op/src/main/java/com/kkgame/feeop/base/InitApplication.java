package com.kkgame.feeop.base;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class InitApplication extends HttpServlet {

	@Override
	public void init() throws ServletException {
		PkigConstants.PKIG_WEB_CONTEXT = getServletContext();
		PkigConstants.PKIG_WEB_ROOT = new File(getServletContext().getRealPath("WEB-INF")).getParent().replaceAll("\\\\","/");
	}
}
