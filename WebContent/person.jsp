<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% String role = (boolean)session.getAttribute("role") ? "管理员" : "普通用户"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onselectstart="return false;">
<head>
<title>个人中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
username = "<%=session.getAttribute("username") %>";
</script>
<script src="/TBS/lib/js/jquery.min.js"></script>
<script src="/TBS/lib/js/uikit.min.js"></script>
<script src="/TBS/Extjs/ext-all-debug.js"></script>
<script src="/TBS/lib/js/person.js"></script>
<script src="/TBS/lib/js/sha1.js"></script>
<link rel="stylesheet" href="/TBS/lib/css/uikit.gradient.min.css">
<link href="Extjs/resources/css/ext-all-neptune.css" rel="stylesheet" />
<link rel="stylesheet" href="/TBS/lib/css/header.css">
<style>
.switcher { list-style-type: none; }
.switcher>li.inactive{ display: none; }
.table-grid {
	border-left: 1px solid #E5E5E5;
	min-height: 600px;
}
.x-panel-header{ text-align:center; }
.x-grid-table .x-grid-cell-inner { font-size: 16px; }
tr.x-grid-record-red .x-grid-td { color: RED; }

input:-moz-placeholder {
	color: #fff;
}

input:-ms-input-placeholder {
	color: #fff;
}

input::-webkit-input-placeholder {
	color: #fff;
}

input:focus {
	outline: none;
	-moz-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
	-webkit-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
	box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
}

.formtips {
	margin-top: 20px;
	height: 40px;
	background: #2d2d2d; /* browsers that don't support rgba */
	background: rgba(45, 45, 45, .25);
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	border-radius: 8px;
}

.formtips span {
	display: inline-block;
	margin-left: 2px;
	font-size: 20px;
	color: red;
	font-weight: 700;
	line-height: 40px;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
}

form {
	position: relative;
	width: 350px;
	margin: 15px 25px 0 25px;
	text-align: center;
}

label { font-size: 16px; }

input {
	width: 270px;
	height: 35px;
	margin-top: 15px;
	padding: 0 15px;
	border-radius: 6px;
	border: 1px solid rgba(0, 0, 0, .5);
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	font-size: 14px;
	color: #000;
}
</style>

<script type="text/javascript">
Ext.onReady(function() {
	$('ul.uk-nav>li').click(function() {
		$('ul.uk-nav>li').each(function() {
			$(this).removeClass('uk-active');
		});
		$(this).addClass('uk-active');
		
		var index = $(this).index();
		switch (index) {
			case 0:	
				usercoursePanel.show();
				testrecordPanel.hide();
				$('ul.switcher>li').eq(2).addClass('inactive');
				break;
			case 1:	
				usercoursePanel.hide();
				testrecordPanel.show();
				$('ul.switcher>li').eq(2).addClass('inactive');
				break;
			case 2:	
				usercoursePanel.hide();
				testrecordPanel.hide();
				$('ul.switcher>li').eq(2).removeClass('inactive');
				break;
		}
	});
	testrecordPanel.hide();
})
</script>
<script type="text/javascript">
function chpwd() {
	$('form').find('.formtips').remove();
	var rawpassword = $('form input.rawpassword').val();
	var newpassword = $('form input.newpassword').val();
	var reppassword = $('form input.reppassword').val();
	if (rawpassword.length < 6) {
		var errorMsg = '旧密码长度至少为6';
		$('form label').eq(0).before('<div class="formtips"><span>'+errorMsg+'</span></div>');
		return;
	} else if (newpassword.length < 6) {
		var errorMsg = '新密码长度至少为6';
		$('form label').eq(0).before('<div class="formtips"><span>'+errorMsg+'</span></div>');
		return;
	} else if (newpassword != reppassword) {
		var errorMsg = '两次密码应相同';
		$('form label').eq(0).before('<div class="formtips"><span>'+errorMsg+'</span></div>');
		return;
	}
	rawpassword = CryptoJS.SHA1(username + ':' + rawpassword).toString();
	newpassword = CryptoJS.SHA1(username + ':' + newpassword).toString();
	$.ajax({
		type: "POST",
		url: "SignAction_changePassword",
		data: {
			rawpassword: rawpassword,
			newpassword: newpassword
		},
		dataType: "json",
		success: function(data){
			if (data.result == "success") {
				Ext.Msg.alert("提示","密码修改成功！");
				window.location.href = "";
			} else {
				var errorMsg = data.message;
				$('form label').eq(0).before('<div class="formtips"><span>'+errorMsg+'</span></div>');
			}
		}
	});
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
			<li class="uk-active"><a href="/TBS/person">个人中心</a></li>
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
	
	<div class="uk-container uk-container-center uk-margin-large-top uk-margin-large-bottom">
		<div class="uk-grid">
			<div class="main-grid uk-width-medium-1-4">
				<ul class="uk-nav uk-nav-side uk-nav-sub">
					<li class="uk-active"><a href="#">选课情况</a></li>
					<li><a href="#">考试记录</a></li>
					<li><a href="#">修改密码</a></li>
				</ul>
			</div>
			<div class="table-grid uk-width-medium-3-4">
				<ul class="switcher">
					<li>
						<div id="UserCourseTable" class="uk-container uk-container-center"></div>
					</li>
					<li>
						<div id="TestRecordTable" class="uk-container uk-container-center"></div>
					</li>
					<li class="inactive">
						<form name="signup" id="signupform" action="SignAction_signup" method="post" onsubmit="return false" autocomplete="off">
							<label>旧密码　：</label><input type="password" name="rawpassword" class="rawpassword">
							<label>新密码　：</label><input type="password" name="newpassword" class="newpassword">
							<label>重复密码：</label><input type="password" name="reppassword" class="reppassword">
							<button class="uk-button uk-button-primary uk-margin-large-top button-center" onclick="chpwd()">修改密码</button>				
						</form>	
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>