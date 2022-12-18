<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.farmeasy.model.board.*" %>

<%
	BoardDao boardDao = BoardDao.getInstance();
	BoardReplyDto replyDto = boardDao.getReply(Integer.parseInt(request.getParameter("reply_id")));
	int board_id = (int)session.getAttribute("updateDeleteBoardId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/board_content.css">
<title>댓글 수정</title>
<script>
	
	function popClose() {
		setTimeout(function() {
			opener.parent.location.reload;
		    self.close();
		},1000);	
	}

</script>
</head>
<body>
	<form name="FEForm2" method="post" action="replyUpdate.do?reply_id=<%=replyDto.getReply_id() %>">
		<div class="wd-basic-960" id="comment">
			<p>작성자 ID : <%=replyDto.getUser_idName() %></p>
			<input type="text" name="reply_content" maxLength="1900" value="<%=replyDto.getReply_content() %>">
			<button type="submit" onclick="popClose()">수정</button>
		</div>
	</form>
</body>
</html>