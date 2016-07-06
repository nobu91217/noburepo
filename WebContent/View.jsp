<%@ page contentType="text/html; charset=UTF-8"
	import="java.sql.*, javax.naming.*, javax.sql.*, java.text.*" %>
<%
Context context = new InitialContext();
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/nobuweb");
Connection db = ds.getConnection();
PreparedStatement ps = db.prepareStatement("SELECT * FROM user_info ORDER BY user_id, password");
ResultSet rs = ps.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>登録ユーザー情報</title>
</head>
<body>
<table border="1">
<tr>
	<th>ユーザーID</th><th>パスワード</th>
</tr>
<% while(rs.next()) { %>
	<tr>
		<td><%=rs.getString("user_id") %></td>
		<td><%=rs.getString("password") %></td>	
		<td>
			<a href="edit.jsp?id=<%=rs.getString("user_id") %>">削除</a>
		</td>
	</tr>
<%
}
rs.close();
ps.close();
db.close();
%>
</table>
</body>
</html>
