<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:choose>
		<c:when test="${check == 1}">
			<script type="text/javascript">
				alert("아이디가 존재합니다! 다시 입력하세요.");
				history.back();
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("사용가능한 아이디입니다.");
				history.back();
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>