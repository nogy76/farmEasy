<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>귀농하여 지방살리기</title>
<link rel="stylesheet" href="css/find_pw.css">
<!-- <link rel="stylesheet" href="../css/layout.css"> -->
<link rel="stylesheet" href="css/header-footer.css">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<!-- <a href='https://.pngtree.com/so/항목의'>항목의 png에서 .pngtree.com/</a> -->
<style>
</style>
</head>

<div class="main-container">
	<div class="main-wrap">
		<header>
			<div class="find-pw-title">비밀번호 찾기</div>
		</header>
		<form action="find_pw.jsp">
			<section class="find-pw-input-section-wrap">
				<div class="find-pw-input-wrap">
					<input placeholder="이름" type="text" required></input>
				</div>
				<div class="find-pw-input-wrap id-wrap">
					<input placeholder="아이디" type="text" required></input>
				</div>
				<div class="find-pw-input-wrap password-wrap">
					<input placeholder="이메일 주소" type="email" required></input>
				</div>
				<div class="find-pw-button-wrap">
					<input type="submit" value="확인" onclick="reloadLogin()"></input>
				</div>
			</section>
		</form>
	</div>
</div>
<script>
	function reloadLogin() {
		alert("가입시 입력하신 이메일로 비밀번호가 발송되었습니다.");
		window.opener.location.reload();
		window.close();
	}
</script>
</body>
</html>