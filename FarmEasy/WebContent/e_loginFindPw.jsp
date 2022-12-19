<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 1 = 이메일 이름 존재 / 0 이메일없음 / -1 이름이 없음 -->
<!-- 로그인 체크 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="css/find_id.css">
</head>
<body>

<c:choose>
	<c:when test="${fn:contains(findpw, '0')}">
		<script type="text/javascript">
			alert("이름이 존재하지 않습니다. ${findpw}");
			history.back();
		</script>
	</c:when>
	<c:when test="${fn:contains(findpw, '-1')}">
		<script type="text/javascript">
			alert("아이디가 존재하지 않습니다. ${findpw} ");
			history.back();
		</script>
	</c:when>
		<c:when test="${fn:contains(findpw, '1')}">
		<script type="text/javascript">
			alert("이메일이 존재하지 않습니다. ${findpw} ");
			history.back();
		</script>
	</c:when>
<c:otherwise>
	
	<div id="headLine"><a href="index.jsp">귀농쉽농</a></div>

	<div class="main-container">
		<div class="main-wrap">
			<div class="find-id-title">비밀번호 재설정</div>
			
			
		<form id="updatePw" action="updatePw.do" onsubmit="return validate();">
			<section class="find-id-input-section-wrap">
			<div class="shadow_wrap">	
				<div class="find-id-input-wrap">
					<input placeholder="비밀번호" name="m_Pw" type="password" required></input>
				</div>
				<div class="find-id-input-wrap password-wrap">
					<input placeholder="비밀번호 확인" name="m_pw_confirm" type="password" required></input>
				</div>
			</div>	
				<div class="find-id-button-wrap">
					<input type="submit" value="비밀번호 변경">
				</div>
			</section>
		</form>
		</div>
	</div>
	</c:otherwise>
</c:choose>
</body>
</html>