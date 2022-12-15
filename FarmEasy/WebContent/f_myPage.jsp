<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/myPage.css">

<title>마이 페이지</title>
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/77e29b57dd.js"
	crossorigin="anonymous">
	
</script>
<script src="js/mypage.js" defer></script>
</head>
<body id="body">
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
			<li class="depth1"><a href="/FarmEasy/boardList.board">게시판</a>
				<ul class="submenu">
					<li><a href="/FarmEasy/boardList.board">게시판</a></li>
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


	<div id="myPage_wrap" class="mb-auto mt-5">
		<div id="myPage_title">
			<ul>
				<li>마이 페이지</li>
				<li><a href="#">- 정책 리스트 보기</a></li>
				<li><a href="f_myPage_CheckPw.jsp">- 회원 정보 수정</a></li>
				<li><a href="#">- 회원 탈퇴</a></li>
			</ul>
		</div>
		<div id="myPage_list">
			<ul class="myPage_hi">
				<li><img src="images/user.png"></li>
				<li><span>${m_id}</span>님,<br>안녕하세요.</li>
				<li>리스트 수
					<p>3</p>
				</li>
			</ul>
		</div>
	</div>

	<div id="myPage_showList_wrap" class="mb-auto wd-basic-960 mt-5">
		<div class="wd-300"></div>
		<div class="list_wrap">
			<h3>추가한 리스트</h3>
			<ul class="myPage_showList">
				<li>
					<table>
						<tr>
							<td>번호</td>
							<td>시군</td>
							<td>시군구</td>
							<td>정책명</td>
						</tr>
					</table>

				</li>
				<li></li>
			</ul>
		</div>
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