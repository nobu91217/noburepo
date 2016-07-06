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
<meta charset="UTF-8" />
<title>ユーザー情報画面</title>
</head>
<body>
<form method="POST" action="/nobuweb/DeleteServlet">
<div>
	<label>ユーザーID:<br />
		<input type="hidden" name="id"value="<%=rs.getString("user_id") %>"/>
		<input type="text" name="user_id" size="50" maxlength="16" value="<%=rs.getString("user_id") %>"/>
		
		
	</label>
</div>

<div>
	<label>パスワード:<br />
		<input type="text" name="password" size="50" maxlength="16" value="<%=rs.getString("password") %>"/>
	</label>
</div>
<div>
		<input type="submit" name="update" value="更新" />
		<input type="submit" name="delete" value="削除"
			onclick="return confirm('本当に削除しても良いですか？')" />
</div>
</form>
</body>
</html>
<%
}
%>