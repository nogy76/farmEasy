<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>귀농하여 지방살리기</title>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/header-footer.css">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<!-- <a href='https://.pngtree.com/so/항목의'>항목의 png에서 .pngtree.com/</a> -->
<style>
</style>
<script>
	function loginFunction() {
		alert("로그인 성공");
		opener.parent.location = 'index_login.jsp';
		// window.opener.location.reload();
		window.close();
	}

	function popFindId() {
		window.open("e_find_id.jsp", "popFI",
				"width=550, height=450, left=750, top=100");
		window.opener.location.close();
	}

	function popFindPw() {
		window.open("e_find_pw.jsp", "popFP",
				"width=550, height=500, left=750, top=100");
	}

	function popSignup() {
		window.open("e_signup.jsp", "popS",
				"width=750, height=800, left=800, top=50");
	}
</script>
</head>

<div class="main-container">
	<div class="main-wrap">
		<header>
			<div class="logo-wrap">
				<h1>로그인</h1>
			</div>
		</header>
		<form action="login.jsp">
			<section class="login-input-section-wrap">
				<div class="login-input-wrap">
					<input placeholder="아이디" type="text"></input>
				</div>
				<div class="login-input-wrap password-wrap">
					<input placeholder="비밀번호" type="password"></input>
				</div>
				<div class="login-button-wrap">
					<input type="submit" value="로그인" onclick="loginFunction()"></input>
					<!-- <button>로그인</button> -->
				</div>
				<ul class="find_wrap" id="find_wrap">
					<li class="hover_green"><a href="#" onclick="popFindId()">아이디
							찾기</a></li>
					<li class="hover_green"><a href="#" onclick="popFindPw()">비밀번호
							찾기</a></li>
					<li class="hover_green"><a href="#" onclick="popSignup()">회원가입</a></li>
				</ul>
			</section>
		</form>
	</div>
</div>
</body>
</html>