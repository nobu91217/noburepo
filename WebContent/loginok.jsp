<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ログイン成功</title>
</head>
<body>
ようこそ<%= request.getAttribute("id") %>さん。
<% out.print("<p>ログインできました！</p>"); %>
</body>
</html>