<%@ page contentType="text/html; charset=UTF-8"
	import="java.sql.*, javax.naming.*, javax.sql.*"%>
<%
request.setCharacterEncoding("UTF-8");
Context context = new InitialContext();
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/nobuweb");
Connection db = ds.getConnection();
PreparedStatement ps;



request.getParameter("delete");
	ps  = db.prepareStatement("DELETE FROM user_info WHERE id=? ");
	ps.setString(1, request.getParameter("id"));
  
	ps.executeUpdate();
	ps.close();
	db.close();
	response.sendRedirect("View.jsp");
%>