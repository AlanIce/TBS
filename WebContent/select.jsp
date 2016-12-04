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
<style>
.inline-button {
	cursor: pointer;
	width: 80px;
	height: 36px;
	margin-top: 25px;
	margin-right: 15px;
	padding: 0 15px;
	background: #2d2d2d; /* browsers that don't support rgba */
	background: rgba(255, 255, 255, .75);
	color: balck;
	border-radius: 6px;
	border: 1px solid #3d3d3d; /* browsers that don't support rgba */
	border: 1px solid rgba(255, 255, 255, .15);
	box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	font-size: 14px;
	color: #111;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
}
.inline-button:hover {
	box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
}

.inline-button:active {
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, .1) inset, 0 1px 4px 0
		rgba(0, 0, 0, .1);
	border: 0px solid #ef4300;
}

.uk-width-medium-1-3 {
	margin-bottom: 50px;
}
</style>
<script type="text/javascript">
function appendCourse(courseID, courseName, courseImgSrc) {
	var html = '<div class="uk-width-medium-1-3 uk-text-center">'
		 + '<div class="uk-thumbnail uk-overlay-hover">'
		 + '<figure class="uk-overlay">'
		 + '<img width="400" height="400" src="'+courseImgSrc+'" alt="">'
		 + '<figcaption class="uk-overlay-panel uk-overlay-background uk-flex uk-flex-center uk-flex-middle uk-text-center uk-overlay-fade">'
		 + '<input type="button" class="inline-button uk-text-large uk-margin-top-remove" value="考试" onclick="window.location.href=\'/TBS/test?courseID='+courseID+'\'">'
		 + '</figcaption>'
		 + '</figure>'
		 + '</div>'
		 + '<h2 class="uk-margin-bottom-remove">'+courseName+'</h2>'
		 + '</div>';
	$('.all-course .uk-grid').append(html);
}
$(function() {
	$.ajax({
        type: "GET",
        url: "SelectAction_getCourseList",
        dataType: "json",
        success: function(data){
        	for (var i = 0; i < data.total; i++) {
        		appendCourse(data.data[i].CourseID, data.data[i].CourseName, data.data[i].CourseImgSrc);
        	}
        }
    });
})
</script>
</head>

<body>
	<div class="uk-navbar x-container">             
        <a href="/" class="uk-navbar-brand uk-visible-large">题库管理系统</a>
        <a href="/" class="uk-navbar-brand uk-hidden-large"><i class="uk-icon-home"></i></a>
        <ul id="ul-navbar" class="uk-navbar-nav uk-hidden-small">                    
            <li><a href="/TBS/homepage">主页</a></li>                    
            <li class="uk-active"><a href="/TBS/select">选择科目</a></li>                    
            <li><a href="/TBS/person">个人中心</a></li>                    
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
			<div class="uk-width-1-1">
				<h1 class="uk-heading-large">选择科目</h1>
				<p class="uk-text-large">请选择需要考试的科目</p>
			</div>
		</div>

		<div class="uk-grid">
			<div class="uk-width-1-1">
				<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#switcher-content'}">
					<li class="uk-active"><a href="#">全部</a></li>
					<li><a href="#">我选的课程</a></li>
					<li><a href="#">未考的课程</a></li>					
				</ul>

				<ul id="switcher-content" class="uk-switcher">
					<li class="uk-active all-course">
						<div class="uk-grid">
							
						</div>
					</li>

					<li>
						<div class="uk-grid">
							<div class="uk-width-medium-1-3">
								<div class="uk-thumbnail uk-overlay-hover">
									<figure class="uk-overlay"> <img width="600" src="/TBS/lib/image/background/1.jpg" alt="">
										<figcaption class="uk-overlay-panel uk-overlay-icon uk-overlay-background uk-overlay-fade"></figcaption>
										<a class="uk-position-cover" href="#"></a>
									</figure>
								</div>
							</div>
						</div>
					</li>
				
					<li>
						<div class="uk-grid">
							<div class="uk-width-medium-1-3">
								<div class="uk-thumbnail uk-overlay-hover">
									<figure class="uk-overlay"> <img width="600" src="/TBS/lib/image/background/1.jpg" alt="">
										<figcaption class="uk-overlay-panel uk-overlay-icon uk-overlay-background uk-overlay-fade"></figcaption>
										<a class="uk-position-cover" href="#"></a>
									</figure>
								</div>
							</div>
						</div>
					</li>
					
				</ul>

			</div>
		</div>
	</div>
</body>
</html>