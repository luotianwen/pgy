<%@ page contentType="text/html" %>
<%@page import="java.util.*"%>
<%
Runtime runtime=Runtime.getRuntime();
String verb=request.getParameter("verb");
if("gc".equals(verb)) runtime.gc();
%>
<html>
<head>
<title>dbconn</title>
</head>
<body>
<form method="post" name="frm">
<input type="hidden" name="verb" value="">
<h1>Server Status</h1>
<UL>
	 
	<li><h2>
			Memory Status 
			<input type="button" value="Run Garbage Collection" onclick="document.frm.verb.value='gc';submit()">
		</h2>
		<UL>
			<li><b>processors</b>:&nbsp;<%=runtime.availableProcessors()%></li>
			<li><b>freeMemory</b>:&nbsp;<%=Math.round(runtime.freeMemory()*100/(1024*1024D))/100D%>M</li>
			<li><b>totalMemory</b>:&nbsp;<%=Math.round(runtime.totalMemory()*100/(1024*1024D))/100D%>M</li>
			<li><b>maxMemory</b>:&nbsp;<%=Math.round(runtime.maxMemory()*100/(1024*1024D))/100D%>M</li>
		</UL>
		<br>
	</li>
	<%-- 
	<li><h2>Servlet Context Status</h2>
		<UL>
			<li><h3>version:&nbsp;<%=application.getMajorVersion()+"."+application.getMinorVersion()%></h3></li>
			<li><h3>initial parameters</h3>
				<UL>
				<%
					Enumeration enums=application.getInitParameterNames();
					while(enums.hasMoreElements())
					{
						String key=(String)enums.nextElement();
						out.println("<li><b>"+key+"</b>:&nbsp;"+application.getInitParameter(key)+"</li>");
					}
				%>
				</UL>
			</li>
			<li><h3>attributes</h3>
				<UL>
				<%
					enums=application.getAttributeNames();
					while(enums.hasMoreElements())
					{
						String key=(String)enums.nextElement();
						out.println("<li><b>"+key+"</b>:&nbsp;"+application.getAttribute(key).toString()+"</li>");
					}
				%>
				</UL>
			</li>
		</UL>
		<br>
	</li>
	<li><h2>System Properties</h2>
		<UL>
		<%
			enums=System.getProperties().keys();
			while(enums.hasMoreElements())
			{
				String key=(String)enums.nextElement();
				out.println("<li><b>"+key+"</b>:&nbsp;"+System.getProperty(key)+"</li>");
			}
		%>
		</UL>
		<br>
	</li>
	--%>
</UL>
</form>
</body>
</html>