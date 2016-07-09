<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, java.sql.*, javax.naming.*, javax.sql.*" %>
<%
request.setCharacterEncoding("UTF-8");
Context context = new InitialContext();
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/nobuweb");
Connection db = ds.getConnection();
PreparedStatement ps = db.prepareStatement("SELECT * FROM user_info where user_id=?");
ps.setString(1, request.getParameter("id"));
ResultSet rs = ps.executeQuery();
if(rs.next()) {
%>
<!DOCTYPE html>
<html>
<head>
<style>
.errorMsg{color:red;}
</style>
<meta charset="UTF-8" />
<title>ユーザー情報画面</title>
</head>
<body>
<form method="POST" action="/nobuweb/UserServlet">
<div>
	<label for="user_id"> id </label><br/>
		
		<input type="text" name="user_id" size="50" maxlength="225" value="<%=rs.getString("user_id") %>"/>
		<span class="errorMsg"><%=request.getAttribute("idErrorMsg") %>></span>
</div>

<div>
	<label>名前:<br />
		<input type="text" name="name" size="50" maxlength="16" value="<%=rs.getString("name") %>"/>
	</label>
</div>
<div>
		<input type="submit" value="更新" />
		<input type="submit" name="delete" value="削除"
			onclick="return confirm('本当に削除しても良いですか？')" />
</div>
</form>
</body>
</html>
<%
}
%>