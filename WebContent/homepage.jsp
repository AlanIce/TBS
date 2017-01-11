<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String role = (boolean)session.getAttribute("role") ? "管理员" : "普通用户"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onselectstart="return false;">
<head>
<title>题库管理系统-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<script src="/TBS/lib/js/jquery.min.js"></script>
<script src="/TBS/lib/js/uikit.min.js"></script>
<link rel="stylesheet" href="/TBS/lib/css/uikit.gradient.min.css">
<link rel="stylesheet" href="/TBS/lib/css/header.css">
</head>

<body>
	<div class="uk-navbar x-container">
        <a href="/TBS" class="uk-navbar-brand uk-visible-large">题库管理系统</a>
        <a href="/TBS" class="uk-navbar-brand uk-hidden-large"><i class="uk-icon-home"></i></a>
        <ul id="ul-navbar" class="uk-navbar-nav uk-hidden-small">                    
            <li class="uk-active"><a href="/TBS/homepage">主页</a></li>                    
            <li><a href="/TBS/select">选择科目</a></li>
            <li><a href="/TBS/person">个人中心</a></li>
            <li><a href="/TBS/scores">成绩管理</a></li>
            <li><a href="/TBS/testbase">题库管理</a></li>
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
    
	<div class="uk-container uk-container-center uk-margin-top uk-margin-large-bottom">
        <div class="uk-grid">
            <div class="uk-width-medium-1-1">
                <div class="uk-vertical-align uk-text-center" style="height: 450px;">
                    <div class="uk-vertical-align-middle uk-width-1-2">
                        <h1 class="uk-heading-large" style="font-size: 56px;">欢迎</h1>
                        <p class="uk-text-large" style="font-size: 28px;">欢迎使用题库管理系统 ！</p>
                        <br><br>
                    </div>
                    
					<div class="uk-vertical-align-middle uk-width-1-2">
                        <p class="uk-text-large" align="left" style="font-size: 30px;">新注册用户指南：</p>
                        <p class="uk-text-large" align="left">STEP1：进入个人中心页面选择相应课程，加入自己的课表</p>
                        <p class="uk-text-large" align="left">STEP2：进入课程选择页面选择相应课程，点击图像进入考试</p>
                        <p class="uk-text-large" align="left">STEP3：进入个人中心页面查看成绩</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>