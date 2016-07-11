<%@ page contentType="text/html; charset=UTF-8"
	import="jp.nobu.servlet.SendInfoServlet" %>
<%
request.setCharacterEncoding("UTF-8");
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
		<input type="text" name="user_id" size="50" maxlength="225" value="<%=request.getAttribute("id") %>"/><span class="errorMsg"><%=request.getAttribute("idErrorMsg") %></span>
</div>

<div>
	<label for="name"> 名前 </label><br/>
		<input type="text" name="name" size="50" maxlength="16" value="<%=request.getAttribute("name") %>"/><span class="errorMsg"><%=request.getAttribute("nameErrorMsg") %></span>
</div>
<div>
		<input type="submit" name="update" value="更新" />
		<input type="submit" name="delete" value="削除"
			onclick="return confirm('本当に削除しても良いですか？')" />
</div>
</form>
</body>
</html>
