<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<title>个人主页</title>
<script>
show = function() {
	password_label = document.getElementById("password");
	password_label.style.display="inline";
}
</script>
</head>
<body>
<h1>嘿，<%=session.getAttribute("user") %>，你中计了！</h1>
<hr/>
<h3><%=session.getAttribute("username") %>你的密码是:<br/><span id="password" style="display:none"><%=session.getAttribute("password") %></span><h3>
<br/>
<button onclick="show()">点击显示</button>
<hr/>
<button onclick="window.location.href = 'welcome';">登出</button>
<% %>
</body>
</html>