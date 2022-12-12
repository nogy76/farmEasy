<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>귀농하여 지방살리기</title>
<link rel="stylesheet" href="css/signup.css">
<!-- <link rel="stylesheet" href="../css/layout.css"> -->
<link rel="stylesheet" href="css/header-footer.css">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<!-- <a href='https://.pngtree.com/so/항목의'>항목의 png에서 .pngtree.com/</a> -->
<script>
	function success_signup() {
		alert("회원가입 성공!");
		window.opener.location.reload();
		window.close();
	}
</script>
</head>

<body>
	<form action="signup.jsp">
		<div class="main-container">
			<div class="main-wrap">
				<fieldset id="signup_fieldset">
					<header>
						<div class="logo-wrap">
							<h1>귀농쉽농 회원가입</h1>
						</div>
					</header>
					<div class="signup_div">
						<table>
							<tr>
								<td align=left class="td_name">성명 <span>*</span></td>
								<td><input class="login-input-wrap" type="text"
									name="user_name" required></input></td>
							</tr>
							<tr id="br-bt-none">
								<td align=left class="td_name">아이디 <span>*</span></td>
								<td><input class="login-input-wrap" type="text"
									name="user_id" required></input></td>
							</tr>
							<tr class="check_id_tr">
								<td></td>
								<td id="check_id">사용 가능한 아이디입니다. / 이미 사용중인 아이디입니다.</td>
							</tr>
							<tr>
								<td align=left class="td_name">비밀번호 <span>*</span></td>
								<td><input class="login-input-wrap" type="password"
									name="user_pw" required></input></td>
							</tr>
							<tr>
								<td align=left class="td_name">비밀번호 확인 <span>*</span></td>
								<td><input class="login-input-wrap" type="password"
									name="user_pw_confirm" required></input></td>
							</tr>
							<tr>
								<td align=left class="td_name">이메일 주소 <span>*</span></td>
								<td><input class="login-input-wrap" type="email"
									name="user_addr" required></input></td>
							</tr>
							<tr>
								<td align=left class="td_name">휴대전화 <span>*</span></td>
								<td><input class="login-input-wrap" type="tel"
									name="user_phone" required></input></td>
						</table>
					</div>
					<div class="login-button-wrap">
						<input type="submit" value="회원가입" onclick="success_signup()"></input>
					</div>
				</fieldset>
			</div>
		</div>
	</form>
</body>

</html>