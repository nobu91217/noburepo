<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ログイン画面</title>
</head>
<style>
.errorMsg{color:red;}
</style>
<body>
<form method="POST" action="/nobuweb/LoginServlet">
<div>
		<label for="id"> id </label><br/>
		<input type="text" name="id" size="15" /><span class="errorMsg"><%=request.getAttribute("idErrorMsg") %>></span>
</div>
<div>
	<label>パスワード：<br />
		<input type="password" name="pass" size="15" />
	</label>
</div>
<div>
	<input type="submit" value="ログイン" />
</div>
</form>
</body>
</html>
