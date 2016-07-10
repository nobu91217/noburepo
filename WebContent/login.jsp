<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>ログイン画面</title>
</head>
<style>
.errorMsg{color:red;}
</style>
<body>
<form method="POST" action="/nobuweb/LoginServlet">
<div>
		<label for="user_id"> id </label><br/>
		<input type="text" name="id" size="15" /><span class="errorMsg"><%=request.getAttribute("idErrorMsg") %>></span>
</div>
<div>
	<label for="password"> パスワード</label> <br/>
		<input type="password" name="pass" size="15" /><span class="errorMsg"><%=request.getAttribute("passErrorMsg") %>></span>
</div>
<div>
	<input type="submit" value="ログイン" />
</div>
</form>
</body>
</html>
