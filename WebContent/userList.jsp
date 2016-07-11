<%@page import="java.util.List"%>
<%@page import="jp.nobu.domain.User"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>登録ユーザー情報</title>
<style>
.errorMsg{color:red;}
</style>
</head>
<body>


<p class="errorMsg"><%=request.getAttribute("deleteErrorMsg") %></p>
<table border="1">
<tr>
	<th>ユーザーID</th><th>名前</th>
</tr>
<% for(User user : (List<User>)request.getAttribute("users")) { %>
	<tr>
		<td><%= user.getUserId() %></td>
		<td><%= user.getUserName() %></td>	
		<td>
			<a href="edit.jsp?id=<%=user.getUserId()  %>">編集</a>
		</td>
	</tr>
<%
}
%>
</table>
</body>
</html>
