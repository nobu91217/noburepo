<%@page import="java.util.List"%>
<%@page import="jp.nobu.domain.User"%>
<%@ page contentType="text/html; charset=UTF-8" %>
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
<% foreach(User user : (List<User>)request.getAttribute("users")) { %>
	<tr>
		<td><%= user.getUserId() %></td>
		<td><%= user.getUserName() %></td>	
		<td>
			<a href="edit.jsp?id=<%=user.getUserId()  %>">削除</a>
		</td>
	</tr>
<%
}
%>
</table>
</body>
</html>
