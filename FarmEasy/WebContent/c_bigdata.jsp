<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bigdata.css">
<title>농산물 빅데이터</title>
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/77e29b57dd.js"
	crossorigin="anonymous"></script>
<script>
	function popLogin() {
		window.open("e_login.jsp", "popL",
				"width=650, height=650, left=800, top=50");
	}

	function popSignup() {
		window.open("e_signup.jsp", "popS",
				"width=750, height=800, left=800, top=50");
	}
</script>
<script src="js/index.js" defer></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<!-- nav 시작 -->
	<!-- container -->
	<nav class="navbar">
		<!-- logo -->
		<div class="navbar-logo">
			<a href="index.jsp">귀농쉽농</a>
		</div>
		<!-- menu -->
		<ul class="navbar-menu">
			<li class="depth1"><a href="a_select_step.jsp">길라잡이</a>
				<ul class="submenu">
					<li><a href="a_select_step.jsp">귀농절차</a></li>
					<li><a href="a_select_chung.jsp">지역선정</a></li>
				</ul></li>
			<li class="depth1"><a href="b_all_info.jsp">정책 조회</a>
				<ul class="submenu">
					<li><a href="b_chungcheong_info.jsp">충청도 정책</a></li>
					<li><a href="b_gyeongsang_info.jsp">경상도 정책</a></li>
					<li><a href="b_jeolla_info.jsp">전라도 정책</a></li>
				</ul></li>
			<li class="depth1"><a href="c_bigdata.jsp">농산물 빅데이터</a></li>
			<li class="depth1"><a href="/FarmEasy/list.do">게시판</a>
				<ul class="submenu">
					<li><a href="/FarmEasy/list.do">게시판</a></li>
					<li><a href="d_notice.jsp">갤러리</a></li>
				</ul></li>
		</ul>
		<!-- icon -->
		<ul class="navbar-icon">
			<li><a href="e_login.jsp">로그인</a></li>
			<li><a href="e_signup.jsp">회원가입</a></li>
		</ul>
		<a href="#" class="navbar-more"> <i class='fa fa-bars'
			style='color: white; margin-top: 14px;'></i>
		</a>
	</nav>


	<!-- nav 끝 -->

	<div id="sub-title" class="wd-basic-960 mb-auto">농산물 빅데이터</div>

	<div id="sta-location" class="wd-basic-960">
		<ul>
			<li></li>
			<li><a href="index.jsp">홈&nbsp;</a>&nbsp;>&nbsp;&nbsp;농산물 빅데이터</li>
		</ul>
	</div>

	<div class="wd-basic-960 mb-auto mb-4" style="height: auto;">
		<ul class="label_bigBox">
			<li>품목(가격기준)</li>
			<li>전일</li>
			<li>가격예측<br>(7일평균)
			</li>
			<li>예측가격변동률<br>(%)
			</li>
			<li>월간예측가격</li>
			<li>가격예측 <br>그래프(7일간)
			</li>
		</ul>
	</div>

	<div class="wd-basic-960 mb-auto mb-4" style="height: auto;">
		<ul class="big_box">
			<li class="big_depth">
				<div class="item_box">
					<div class="item_img">
						<img src="images/strawberry.png">
					</div>
					<span class="item_name">설향(kg)</span>
				</div> <!-- 아이템박스 -->
				<div class="day_before">15,342</div>
				<div class="sevendays">13,000</div>
				<div class="persent">
					<span>▼ 2654 </span>
					<p>(-15.2%)</p>
				</div>
				<div class="monthly">
					<span>11,296/kg</span>
				</div>
				<div class="click_graph">
					<span>그래프보기</span>
				</div>
			</li>
		</ul>
	</div>

	<div class="wd-basic-960 mb-auto mb-4" style="height: auto;">
		<ul class="big_box">
			<li class="big_depth">
				<div class="item_box">
					<div class="item_img">
						<img src="images/garlic.png">
					</div>
					<span class="item_name">햇마늘 난지(kg)</span>
				</div> <!-- 아이템박스 -->
				<div class="day_before">2,596</div>
				<div class="sevendays">3,747</div>
				<div class="persent">
					<span>▼ 1151 </span>
					<p>(-44.3%)</p>
				</div>
				<div class="monthly">
					<span>3,468/kg</span>
				</div>
				<div class="click_graph">
					<span>그래프보기</span>
				</div>
			</li>
		</ul>
	</div>

	<div class="wd-basic-960 mb-auto mb-5" style="height: 300px;"></div>


	<footer>
		<div class="container">
			<div>
				<span>&copy; 귀농 인구를 위한 지역 및 농산물 추천 서비스</span>
			</div>
			<ul>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<img src="images/iconmonstr-twitter-4.svg"></svg></a></li>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<img src="images/iconmonstr-facebook-4.svg"></svg></a></li>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<img src="images/iconmonstr-instagram-14.svg"></svg></a></li>
			</ul>
		</div>
	</footer>

</body>
</html>