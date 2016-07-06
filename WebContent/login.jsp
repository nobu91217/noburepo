<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ログイン画面</title>
</head>
<body>
<form method="POST" action="/nobuweb/LoginServlet">
<div>
	<label>ID：<br />
		<input type="text" name="id" size="15" />
	</label>
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
