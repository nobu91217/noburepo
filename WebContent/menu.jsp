<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>メニュー</title>
</head>
<body>
ようこそ<%= request.getAttribute("users") %>さん。
<% out.print("<p>ログインできました！</p>"); %>

<div>

<a href="UserList">ユーザー一覧</a>

</div>

</body>
</html>