<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>情報登録画面</title>
</head>
<style>
.errorMsg{color:red;}
</style>
<body>
<form method="POST" action="/nobuweb/UserServlet">
<div>
	<label for="id"> id </label><br/>
	<input id="id" type="text" name="id" size="15" /><span class="errorMsg"><%=request.getAttribute("idErrorMsg") %>></span>
</div>
<div>
	<label>パスワード：<br />
		<input type="password" name="pass" size="15" />
	</label>
</div>
<div>
	<label>名前：<br />
		<input type="text" name="name" size="15" />
	</label>
</div>
<div>
	<input type="submit" name="register" value="登録" />
</div>
</form>
</body>
</html>
