<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.farmeasy.model.*" %>

<%
	FarmEasyDao farmEasyDao = FarmEasyDao.getInstance();
	BoardDto boardDto = farmEasyDao.getBoardDB(Integer.parseInt(request.getParameter("board_id")));
	session.setAttribute("updateDeleteBoardId", Integer.parseInt(request.getParameter("board_id")));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/board_content.css">
<title>게시판 내용</title>

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
	function deleteCheck() {
		result = confirm("정말로 게시글을 삭제하시겠습니까?");
		
		if(result) {
			document.FEForm1.action="delete.do";
			document.FEForm1.submit();
		} else {
			return;
		}
	}
</script>
<script src="js/index.js" defer></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<!-- 게시판 확인 -->
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
			<li><a href="#" onclick="popLogin()">로그인</a></li>
			<li><a href="#" onclick="popSignup()">회원가입</a></li>
		</ul>
		<a href="#" class="navbar-more"> <i class='fa fa-bars'
			style='color: white; margin-top: 14px;'></i>
		</a>
	</nav>
	<div id="sub-title" class="wd-basic-960 mb-auto">게시판</div>
	<form name="FEForm1" method="post">
		<div class="wd-basic-960 mb-auto mt-5" id="info-bottom"
			style="height: auto">
			<h3><%=boardDto.getBoard_title() %></h3>
			<div>
				<ul>
					<li>등록일 : <%=boardDto.getInsert_date() %></li>
					<li>작성자 : <%=boardDto.getUser_name() %> &nbsp;|&nbsp; 조회수 : <%=boardDto.getBoard_hits() %></li>
				</ul>
			</div>
			<p class="mt-4">
				<pre><%=boardDto.getBoard_content() %></pre>
			</p>
			<p class="post_up">포스터 최종 이미지.jpeg [1721600 byte]</p>
			
			<div class="board_write">
				<a href="d_board_update.jsp"><button type="button" id="board_return">수정</button></a>
				<a href="#"><button type="button" id="board_return" onclick="deleteCheck()">삭제</button></a>
				<a href="/FarmEasy/list.do"><button type="button" id="board_return">목록</button></a>
			</div>
		</div>
	</form>
	<div class="wd-basic-960 mb-auto mt-4" id="comment"
		style="height: auto">
		<p>댓글등록</p>
		<input type="text" title="제목" maxlength="1900">
		<button type="button">등록</button>
		<p>0/1000byte</p>

	</div>


	<footer>
		<div class="container">
			<div>
				<span>&copy; 귀농 인구를 위한 지역 및 농산물 추천 서비스</span>
			</div>
			<ul>
				<li class="ms-3">
					<a class="text-muted" href="#">
						<svg class="bi" width="24" height="24">
							<img src="images/iconmonstr-twitter-4.svg">
						</svg>
					</a>
				</li>
				<li class="ms-3">
					<a class="text-muted" href="#">
						<svg class="bi" width="24" height="24">
							<img src="images/iconmonstr-facebook-4.svg">
						</svg>
					</a>
				</li>
				<li class="ms-3">
					<a class="text-muted" href="#">
						<svg class="bi" width="24" height="24">
							<img src="images/iconmonstr-instagram-14.svg">
						</svg>
					</a>
				</li>
			</ul>
		</div>
	</footer>

</body>
</html>