<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/gyeongsang_info.css">
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/77e29b57dd.js"
	crossorigin="anonymous"></script>
<script>
	function popInfo() {
		window.open("b_pop_info.jsp", "",
				"width=750, height=800, left=800, top=50");
	}
</script>
<script src="js/index.js" defer></script>
<title>경상도 정책 조회</title>
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
			<li class="depth1"><a href="/FarmEasy/list.board">게시판</a>
				<ul class="submenu">
					<li><a href="/FarmEasy/list.board">게시판</a></li>
					<li><a href="d_notice.jsp">갤러리</a></li>
				</ul></li>
		</ul>
		<!-- icon -->
		<ul class="navbar-icon">
		<c:choose>
		<c:when test="${sessionScope.m_id eq null}">
				<li><a href="e_login.jsp">로그인</a></li>
				<li><a href="e_signup.jsp">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="e_logout.jsp">로그아웃</a></li>
				<li><a href="f_myPage.jsp">${m_id} 님</a></li>
			</c:otherwise>
		</c:choose>
		</ul>
		<a href="#" class="navbar-more"> <i class='fa fa-bars'
			style='color: white; margin-top: 14px;'></i>
		</a>
	</nav>


	<!-- nav 끝 -->

	<div id="sub-title" class="wd-basic-960 mb-auto">경상도 정책 조회</div>

	<div id="sta-location" class="wd-basic-960">
		<ul>
			<li><span><a href="b_all_info.jsp">전체보기</a></span> <span><a
					href="b_chungcheong_info.jsp">충청도</a></span> <span>경상도</span> <span><a
					href="b_jeolla_info.jsp">전라도</a></span></li>
			<li><a href="index.jsp">홈&nbsp;</a>&nbsp;>&nbsp;경상도 정책
				조회&nbsp;&nbsp;</li>
		</ul>
	</div>

	<div class="wd-basic-960 mb-auto mt-5" id="info-bottom"
		style="height: 440px;">
		<div id="map_right">
			<h4 class="mb-2">지자체별 정책</h4>
			<table>
				<tr>
					<th>번호</th>
					<th>시도</th>
					<th>시군구</th>
					<th>정책명</th>
					<th>자세히 보기</th>
				</tr>
				<tr>
					<td>274</td>
					<td>경상북도</td>
					<td>청도군</td>
					<td>청년농업인 농지임대료 지원사업</td>
					<td><a href="#" onclick="popInfo()">[자세히 보기]</a></td>
				</tr>
				<tr>
					<td>273</td>
					<td>경상북도</td>
					<td>봉화군</td>
					<td>경북 농민수당</td>
					<td><a href="#" onclick="popInfo()">[자세히 보기]</a></td>
				</tr>
				<tr>
					<td>272</td>
					<td>경상북도</td>
					<td>경주시</td>
					<td>2022년 창농활성화 브랜드 개발 지원</td>
					<td><a href="#" onclick="popInfo()">[자세히 보기]</a></td>
				</tr>
				<tr>
					<td>271</td>
					<td>경상남도</td>
					<td>함양군</td>
					<td>마을단위 찾아가는 융화교육사업 지원</td>
					<td><a href="#" onclick="popInfo()">[자세히 보기]</a></td>
				</tr>
				<tr>
					<td>270</td>
					<td>경상남도</td>
					<td>함양군</td>
					<td>청년 후계농 영농정착 지원사업</td>
					<td><a href="#" onclick="popInfo()">[자세히 보기]</a></td>
				</tr>
			</table>
		</div>

	</div>

	<!-- 페이징 처리 -->
	<div style="text-align: center;" id="no_paging" class="mt-4">
		<p>
			<span> < </span> <span> 1 </span> <span> 2 </span> <span> 3 </span> <span>
				4 </span> <span> 5 </span> <span> > </span>
		</p>
	</div>


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