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
</head>
<body>
<c:choose>
	<c:when test="${check == -1}">
		<script type="text/javascript">
			alert("아이디가 없습니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${check == 0}">
		<script type="text/javascript">
			alert("비밀번호가 틀렸습니다.");
			history.back();
		</script>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${fn:contains(findid, '0')}">
		<script type="text/javascript">
			alert("이메일이 존재하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${fn:contains(findid, '-1')}">
		<script type="text/javascript">
			alert("이름이 존재하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("아이디는 ${findid} 입니다.");
			document.location.href="e_login.jsp";
		</script>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${fn:contains(findpw, '0')}">
		<script type="text/javascript">
			alert("이메일이 존재하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:when test="${fn:contains(findpw, '-1')}">
		<script type="text/javascript">
			alert("이름 혹은 아이디가 존재하지 않습니다.");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("비밀번호는 ${findpw} 입니다.");
			document.location.href="e_login.jsp";
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>