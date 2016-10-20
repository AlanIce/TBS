<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%session.removeAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onselectstart="return false;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>题库管理系统-欢迎登陆</title>
<link rel="icon" href="/TBS/favicon.ico" type="image/x-icon" />
<script src="/TBS/lib/js/jquery.min.js"></script>
<script src="/TBS/lib/js/supersized.3.2.7.min.js"></script>
<script src="/TBS/lib/js/supersized-init.js"></script>
<script src="/TBS/lib/js/sha1.js"></script>
<link rel="stylesheet" href="/TBS/lib/css/supersized.css">
<style type="text/css">
body {
	background: #f8f8f8;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	text-align: center;
	color: #fff;
}

.page-container {
	margin: 7.5% auto 0 auto;
}

h1 {
	font-size: 75px;
	font-weight: 700;
	text-shadow: 0 1px 4px rgba(0, 0, 0, .2);
	margin-bottom: 50px;
}

form {
	position: relative;
	width: 305px;
	margin: 15px auto 0 auto;
	text-align: center;
}

input, select {
	width: 270px;
	height: 42px;
	margin-top: 25px;
	padding: 0 15px;
	background: #2d2d2d; /* browsers that don't support rgba */
	background: rgba(45, 45, 45, .15);
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #3d3d3d; /* browsers that don't support rgba */
	border: 1px solid rgba(255, 255, 255, .15);
	-moz-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
	-webkit-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
	box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	font-size: 14px;
	color: #fff;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
	-o-transition: all .2s;
	-moz-transition: all .2s;
	-webkit-transition: all .2s;
	-ms-transition: all .2s;
}

form select {
	width: 300px;
}

form select option {
	font-size:20px;
	background: #ccc;
}

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

.button {
	cursor: pointer;
	width: 300px;
	height: 44px;
	margin-top: 25px;
	margin-bottom: 25px;
	padding: 0;
	background: #ef4300;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #ff730e;
	-moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset, 0 2px 7px
		0 rgba(0, 0, 0, .2);
	-webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset, 0 2px
		7px 0 rgba(0, 0, 0, .2);
	box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
	font-family: 'PT Sans', Helvetica, Arial, sans-serif;
	font-size: 14px;
	font-weight: 700;
	color: #fff;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
	-o-transition: all .2s;
	-moz-transition: all .2s;
	-webkit-transition: all .2s;
	-ms-transition: all .2s;
}

.button:hover {
	-moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px 7px
		0 rgba(0, 0, 0, .2);
	-webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px
		7px 0 rgba(0, 0, 0, .2);
	box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px 7px 0
		rgba(0, 0, 0, .2);
}

.button:active {
	-moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px 7px
		0 rgba(0, 0, 0, .2);
	-webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset, 0 2px
		7px 0 rgba(0, 0, 0, .2);
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, .1) inset, 0 1px 4px 0
		rgba(0, 0, 0, .1);
	border: 0px solid #ef4300;
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

.form-container {
	width: 400px;
	background: rgba(45, 45, 45, .15);
	margin: auto;
	padding: auto;
}

.header {	
	height: 50px;
	border-bottom: 1px solid #e2e2e2;
	position: relative;
	font-family: "Microsoft Yahei";
}

.header .switch {
	height: 45px;
	position: absolute;
	left: 60px;
	bottom: 0;
	font-size: 16px;
}

.header .switch #switch_signin {
	margin-right: 85px;
}

.header .switch .switch_btn {
	color: #ccc;
	display: inline-block;
	height: 45px;
	line-height: 45px;
	outline: none;
	text-decoration:none;
	*hide-focus: expression(this.hideFocus = true);
}

.header .switch .switch_btn_focus {
	color: #333;
	display: inline-block;
	height: 45px;
	line-height: 45px;
	outline: none;
	text-decoration:none;
	*hide-focus: expression(this.hideFocus = true);
}

.header .switch .switch_btn:hover {
	color: #333;
	text-decoration: none;
}

.header .switch .switch_btn_focus:hover {
	text-decoration: none;
}

#switch_bottom {
	position: absolute;
	bottom: -1px;
	_bottom: -2px;
	border-bottom: 2px solid #848484;
}

input:-webkit-autofill {background-color:rgb(196,196,196)!important;}
</style>
<script>
$(function() {
	if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) { 
		$(window).load(function(){ 
			$('ul input:not(input[type=submit])').each(function(){ 
				var outHtml = this.outerHTML; 
				$(this).append(outHtml); 
			}); 
		}); 
	} 
	
	$('#switch_signup').click(function(){
		$('#switch_signin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_signup').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'154px',width:'70px'});		
		$('.signin').hide();
		$('.signup').show();
		$('form input').each(function() {
			if (!$(this).is('.button'))
				$(this).val('');
		});
	});
	
	$('#switch_signin').click(function(){
		$('#switch_signin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_signup').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('.signin').show();
		$('.signup').hide();
		$('form input').each(function() {
			if (!$(this).is('.button'))
				$(this).val('');
		});
	});
})

check_signin = function() {
	var tag = true;
	$('form').find('.formtips').remove();
	var role = $('.signin form select.role').val();
	var username = $('.signin form input.username').val();
	var password = $('.signin form input.password').val();
	if (role == 'normal') {
		$('form').find('.formtips').remove();
		if (!/^[0-9]{13}/.test(username)) {
			var errorMsg = '请输入13位的学号';
			$('.signin form input.username').after('<div class="formtips"><span>'+errorMsg+'</span></div>');
			tag = false;
		}
		if (password == "" || password.length < 6) {
			var errorMsg = '请输入至少6位的密码';
			$('.signin form input.password').after('<div class="formtips"><span>'+errorMsg+'</span></div>');
			tag = false;
		}
	}
	
	if (tag) {
		password = CryptoJS.SHA1(username + ':' + password).toString();
		$.ajax({
            type: "POST",
            url: "SignAction_signin",
            data: {
            	role: role,
            	username: username,
            	password: password
            },
            dataType: "json",
            success: function(data){
            	if (data.result == "success")            		
            		window.location.href = "homepage";
            	else {
            		var errorMsg = '登录失败，' + data.message;
            		$('.signin .role').before('<div class="formtips"><span>'+errorMsg+'</span></div>');
            	}            		
            }
        });
	}
}

check_signup = function() {
	var tag = true;
	$('form').find('.formtips').remove();
	var username = $('.signup form input.username').val();
	var sname = $('.signup form input.name').val();
	var email = $('.signup form input.email').val();
	var password = $('.signup form input.password').val();
	if (!/^[0-9]{13}/.test(username)) {
		var errorMsg = '请输入13位的学号';
		$('.signup form input.username').after('<div class="formtips"><span>'+errorMsg+'</span></div>');
		tag = false;
	}	
	if (sname == "") {
		var errorMsg = '请输入名字';
		$('.signup form input.name').after('<div class="formtips"><span>'+errorMsg+'</span></div>');
		tag = false;
	}	
	if (!/^[a-zA-Z0-9][a-zA-Z0-9_]+@[a-zA-Z0-9_]+\.[a-zA-Z0-9_]+/.test(email)) {
		var errorMsg = '请输入正确的email地址';
		$('.signup form input.email').after('<div class="formtips"><span>'+errorMsg+'</span></div>');
		tag = false;
	}
	if (password == "" || password.length < 6) {
		var errorMsg = '请输入至少6位的密码';
		$('.signup form input.password').after('<div class="formtips"><span>'+errorMsg+'</span></div>');
		tag = false;
	}	
	if (tag) {
		password = CryptoJS.SHA1(username + ':' + password).toString();
		$.ajax({
            type: "POST",
            url: "SignAction_signup",
            data: {
            	username: username,
            	name: sname,
            	email: email.toLowerCase(),
            	password: password
            },
            dataType: "json",
            success: function(data){
            	if (data.result == "success") {
            		$('.signup .username').before('<div class="formtips"><span style="color:green;">注册成功！</span></div>');
            		setTimeout(function() {
            			window.location.href = "homepage";
            		}, 1500);
            	} else {            		
            		var errorMsg = '注册失败，' + data.message;
            		$('.signup .username').before('<div class="formtips"><span>'+errorMsg+'</span></div>');
            	}            		
            }
        });
	}
}
</script>
</head>

<body>
	<div class="page-container">
		<h1>题库管理系统</h1>
		<div class="form-container">
			<div class="header">
		        <div class="switch" id="switch">
		        	<a class="switch_btn_focus" id="switch_signin" href="javascript:void(0);" tabindex="7">快速登录</a>
					<a class="switch_btn" id="switch_signup" href="javascript:void(0);" tabindex="8">快速注册</a>
					<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
		        </div>
		    </div>
			<div class="signin">
				<form name="signin" id="signinform" action="SignAction_signin" method="post" onsubmit="return false" autocomplete="off">				
					<select name="role" class="role">
					    <option value="normal">普通用户</option>
						<option value="admin">管理员</option>
					</select>
					<input type="text" name="username" class="username" placeholder="用户名">
					<input type="password" name="password" class="password"	placeholder="密码">					
					<input type="button" class="button" onclick="check_signin()" value="登录">
				</form>
			</div>
			<div class="signup" style="display:none;">
				<form name="signup" id="signupform" action="SignAction_signup" method="post" onsubmit="return false" autocomplete="off">
					<input type="text" name="username" class="username" placeholder="学号">
					<input type="text" name="name" class="name" placeholder="姓名">
					<input type="text" name="email" class="email" placeholder="邮箱">
					<input type="password" name="password" class="password" placeholder="密码">
					<input type="button" class="button" onclick="check_signup()" value="注册">				
				</form>
			</div>		
		</div>
	</div>
</body>

</html>