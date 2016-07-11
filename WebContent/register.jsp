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
	<label for="user_id"> id </label><br/>
	<input type="text" name="id" size="15" /><span class="errorMsg"><%=request.getAttribute("idErrorMsg") %></span>
</div>
<div>
	<label for="password"> パスワード </label><br/>
		<input type="password" name="pass" size="15" /><span class="errorMsg"><%=request.getAttribute("passwordErrorMsg") %></span>
</div>
<div>
	<label for="user_name"> 名前 </label><br/>
		<input type="text" name="name" size="15" /><span class="errorMsg"><%=request.getAttribute("nameErrorMsg") %></span>
</div>
<div>
	<input type="submit" name="register" value="登録" />
</div>
</form>
</body>
</html>
