<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>귀농하여 지방살리기</title>
<link rel="stylesheet" href="css/pop_info.css">
<!-- <link rel="stylesheet" href="../css/layout.css"> -->
<link rel="stylesheet" href="css/header-footer.css">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<!-- <a href='https://.pngtree.com/so/항목의'>항목의 png에서 .pngtree.com/</a> -->
</head>

<body>
	<form action="signup.jsp">
		<div class="main-container">
			<div class="main-wrap">
				<fieldset id="signup_fieldset">
					<header>
						<div class="logo-wrap">
							<h2>
								정책 상세 조회 페이지
								</h1>
						</div>
					</header>
					<div class="signup_div">
						<table>
							<tr>
								<td align=left class="td_name">ㆍ사업명</td>
								<td>귀농 농업창업 및 주택구입 지원사업</td>
							</tr>
							<tr>
								<td align=left class="td_name">ㆍ지원금</td>
								<td>세대당 3억 한도 이내</td>
							</tr>
							<tr>
								<td align=left class="td_name">ㆍ이자율</td>
								<td>고정금리(연 2%) 또는 변동금리 중 선택(선택 후 변경 불가)</td>
							</tr>
							<tr>
								<td align=left class="td_name">ㆍ지원 내용</td>
								<td>농업 창업 : 영농기반, 농식품 제조·가공시설 신축·수리 또는 구입<br> 주택 구입 :
									신축(대지구입 포함), 자기 소유 노후 농가주택을 증·개축
								</td>
							</tr>
							<tr>
								<td align=left class="td_name">ㆍ담당 부서 전화번호</td>
								<td>1899-9097</td>
							</tr>
							<tr>
								<td align=left class="td_name">ㆍ관련 링크</td>
								<td>귀농귀촌 종합센터<br> <a href="http://www.returnfarm.com"
									target="_blank">http://www.retrunfarm.com</a>
								</td>
							</tr>
						</table>
					</div>
					<div class="login-button-wrap">
						<input type="button" value="리스트에 담기" class="pick"> <input
							type="button" value="닫기" onclick="closePop()" class="close_info">
					</div>
				</fieldset>
			</div>
		</div>
	</form>
	<script>
		function closePop() {
			window.close();
		}
	</script>
</body>

</html>