<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String role = (boolean)session.getAttribute("role") ? "管理员" : "普通用户"; session.setMaxInactiveInterval(7200);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onselectstart="return false;">
<head>
<title>考试页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<script src="/TBS/lib/js/jquery.min.js"></script>
<script src="/TBS/lib/js/uikit.min.js"></script>
<link rel="stylesheet" href="/TBS/lib/css/uikit.gradient.min.css">
<link rel="stylesheet" href="/TBS/lib/css/header.css">
<style type="text/css">
.main-grid {
	border-right:1px solid #E5E5E5;
}
.question-level label {
	font-size:16px;
}
.question-level img {
	margin:0px 50px 25px;
	max-width:420px;
}
.uk-margin-large-bottom {
	margin-bottom: 200px!important;
}
.button-center {
	width: 20%;
	margin: auto 40%;
}
</style>
<script type="text/javascript">
$(function() {
	$.ajax({
		type: "POST",
		url: "TestAction_getQuestionList",
		data: {
			CourseID: "<%=request.getParameter("courseID") %>"
		},
		dataType: "json",
		success: function(data){
			$('#Project').html(data.Project);
			total = data.total;
			for (var i = 1; i <= total; i++) {
				appendQuestion(i, data.data[i - 1].Question, data.data[i - 1].ImgSrc, data.data[i - 1].OptionA, 
						data.data[i - 1].OptionB, data.data[i - 1].OptionC, data.data[i - 1].OptionD);
			}
			questionIDList = data.questionIDList;
		}
	});
})

questionIDList = '';
total = 0;

appendQuestion = function(index, question, imgSrc, optionA, optionB, optionC, optionD) {
	html = '<li>'
		 + '<h2 id="question_'+index+'">'+index+'. '+question+'</h2>'
		 + '<img alt="" src="'+imgSrc+'">'
		 + '<ul class="uk-list uk-list-space">'
		 + '<li>'
		 + '<input type="radio" name="question_'+index+'" id="question_'+index+'_A" value="A">'
		 + '<label for="question_'+index+'_A">A. '+optionA+'</label>'
		 + '</li>'
		 + '<li>'
		 + '<input type="radio" name="question_'+index+'" id="question_'+index+'_B" value="B">'
		 + '<label for="question_'+index+'_B">B. '+optionB+'</label>'
		 + '</li>'
		 + '<li>'
		 + '<input type="radio" name="question_'+index+'" id="question_'+index+'_C" value="C">'
		 + '<label for="question_'+index+'_C">C. '+optionC+'</label>'
		 + '</li>'
		 + '<li>'
		 + '<input type="radio" name="question_'+index+'" id="question_'+index+'_D" value="D">'
		 + '<label for="question_'+index+'_D">D. '+optionD+'</label>'
		 + '</li>'
		 + '</ul>'
		 + '</li>';
	$('.paper-grid .question-level>ul').append(html);
}

submitPaper = function() {
	answerList = '';
	for (var i = 1; i <= total; i++) {
		var t = $('input:radio[name="question_'+i+'"]:checked').val();
		if (typeof(t) == "undefined") {
			var target = $('#question_' + i).offset().top;//获取位置
			$("html,body").animate({scrollTop: target},300);//跳转
			alert("请回答完整！");
			return false;
		}
		if (i > 1) 
			answerList += ",";
		answerList += t;
	}
	$.ajax({
		type: "POST",
		url: "TestAction_submitPaper",
		data: {
			username: "<%=session.getAttribute("username") %>",
			courseID: "<%=request.getParameter("courseID") %>",
			questionIDList: questionIDList,
			answerList: answerList
		},
		dataType: "json",
		success: function(data){
			alert("得分为：" + data.score);
			window.location.href = "homepage";
		}
	});
	return true;
}

subTest = function() {

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
			<div class="main-grid uk-width-medium-3-4">
				<div class="uk-vertical-align uk-text-center">
					<h1 class="uk-heading-large"><span id="Project"></span>考试</h1>
					<p class="uk-text-large uk-text-right">欢迎使用 题库管理系统 ！</p>
				</div>
				
				<div class="paper-grid">
					<div class="question-level uk-form-stacked">
						<ul class="uk-list uk-list-space uk-list-striped">
							
						</ul>
					</div>
					<button class="uk-button uk-button-primary uk-button-large button-center" onclick="submitPaper()">提交</button>
				</div>        		
			</div>
			
			
			<div class="uk-width-medium-1-4">
			
			</div>
		</div>
	</div>
</body>
</html>