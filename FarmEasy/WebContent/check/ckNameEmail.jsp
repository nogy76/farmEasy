<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<c:otherwise>
		<script type="text/javascript">
			alert("오류, 관리자 문의 바랍니다.");
			history.back();
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>