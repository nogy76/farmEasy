<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.farmeasy.model.board.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/board.css">
<title>게시판</title>

<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/77e29b57dd.js"
	crossorigin="anonymous"></script>
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
			<li class="depth1"><a href="/FarmEasy/boardList.do">게시판</a>
				<ul class="submenu">
					<li><a href="/FarmEasy/boardList.do">게시판</a></li>
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


	<div id="sub-title" class="wd-basic-960 mb-auto">게시판</div>

	<div id="sta-location" class="wd-basic-960">
		<ul>
			<li></li>
			<li><a href="index.jsp">홈&nbsp;</a>&nbsp;>&nbsp;게시판&nbsp;&nbsp;</li>
		</ul>
	</div>

	<div class="wd-basic-960 mb-auto" id="no_table" style="height: 650px;">
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자 ID</th>
					<th>등록날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			<%
				ArrayList<BoardDto> boardList = (ArrayList<BoardDto>)request.getAttribute("boardList");
				if(boardList != null) {
					for(BoardDto boardDto : boardList) {
			%>
			
			<tbody>
				<tr>
					<td><%=boardDto.getBoard_id() %></td>
					<td><a href="d_board_content.jsp?board_id=<%=boardDto.getBoard_id() %>"><%=boardDto.getBoard_title() %></a></td>
					<td><%=boardDto.getUser_idName() %></td>
					<td><%=boardDto.getInsert_date() %></td>
					<td><%=boardDto.getBoard_hits() %></td>
				</tr>
			</tbody>
			
			<% 
					}
				}
			%>
			
		</table>
			<c:choose>
			<c:when test="${sessionScope.m_id eq null}">

				</c:when>
				<c:otherwise>
					<div id="no_btn">
						<a href="d_board_write.jsp">글쓰기</a>
					</div>
				</c:otherwise>
			</c:choose>

		<div style="text-align: center;" class="mt-5" id="no_paging">
			<p>
				<span> < </span> <span> 1 </span> <span> 2 </span> <span> 3 </span>
				<span> 4 </span> <span> 5 </span> <span> > </span>
			</p>
		</div>

	</div>



	<footer>
		<div class="container">
			<div>
				<span>&copy; 귀농 인구를 위한 지역 및 농작물 추천 서비스</span>
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