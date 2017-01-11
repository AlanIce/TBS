<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String role = (boolean)session.getAttribute("role") ? "管理员" : "普通用户"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onselectstart="return false;">
<head>
<title>题库管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<script src="/TBS/lib/js/jquery.min.js"></script>
<script src="/TBS/lib/js/uikit.min.js"></script>
<script src="/TBS/Extjs/ext-all-debug.js"></script>
<script src="/TBS/lib/js/testbase.js"></script>
<link rel="stylesheet" href="/TBS/lib/css/uikit.gradient.min.css">
<link rel="stylesheet" href="Extjs/resources/css/ext-all-neptune.css">
<link rel="stylesheet" href="/TBS/lib/css/header.css">
<style type="text/css">
.table-grid {
	border-left: 1px solid #E5E5E5;
	min-height: 600px;
}
.x-panel-header{ text-align:center; }
.x-grid-table .x-grid-cell-inner { font-size: 16px; }
</style>
<script type="text/javascript">
var role = '<%=role%>';
if (role == "普通用户") {
	alert("该页面只能由管理员访问！");
	window.location.href = "homepage";
}
</script>
</head>

<body>
	<div class="uk-navbar x-container">             
        <a href="/TBS" class="uk-navbar-brand uk-visible-large">题库管理系统</a>
        <a href="/TBS" class="uk-navbar-brand uk-hidden-large"><i class="uk-icon-home"></i></a>
        <ul id="ul-navbar" class="uk-navbar-nav uk-hidden-small">                    
            <li><a href="/TBS/homepage">主页</a></li>                    
            <li><a href="/TBS/select">选择科目</a></li>
            <li><a href="/TBS/person">个人中心</a></li>
            <li><a href="/TBS/scores">成绩管理</a></li>
            <li class="uk-active"><a href="/TBS/testbase">题库管理</a></li>
        </ul>

        <div class="uk-navbar-flip">
            <ul class="uk-navbar-nav">
                <li class="uk-parent x-display-if-signin" data-uk-dropdown="">
                    <a href="#0"><i class="uk-icon-user"></i><span class="x-hidden-tiny">&nbsp;</span><span class="x-user-name x-hidden-tiny"></span></a>
                    <div class="uk-dropdown uk-dropdown-navbar">
                        <ul class="uk-nav uk-nav-navbar">
                            <li><a><i class="uk-icon-info"></i>&nbsp;&nbsp;姓名：<%=session.getAttribute("user") %></a></li>
                            <li><a><i class="uk-icon-info"></i>&nbsp;&nbsp;学号：<%=session.getAttribute("username") %></a></li>
                            <li><a><i class="uk-icon-info"></i>&nbsp;&nbsp;类型：<%=role %></a></li>
                            <li class="uk-nav-divider"></li>
                            <li><a href="/TBS/welcome"><i class="uk-icon-power-off"></i>&nbsp;&nbsp;登出</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    
	<div class="uk-container uk-container-center uk-margin-large-top uk-margin-large-bottom">
        <div class="uk-grid">
        	<div class="main-grid uk-width-medium-1-1">
                <div id="TestBaseTable" class="uk-container uk-container-center"></div>   
        	</div>
                
        </div>
    </div>
</body>
</html>