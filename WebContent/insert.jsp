<%@ page contentType="text/html;charset=UTF-8" 
	import="java.sql.*, javax.naming.*, javax.sql.*"  %>
<%
request.setCharacterEncoding("UTF-8");
Context context = new InitialContext();
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/nobuweb");
Connection db = ds.getConnection();
PreparedStatement ps = db.prepareStatement("INSERT INTO user_info(id,password) VALUES(?,?)");
ps.setString(1, request.getParameter("id"));
ps.setString(2, request.getParameter("pass"));
ps.executeUpdate();
ps.close();
db.close();
response.sendRedirect("login.jsp");
%>