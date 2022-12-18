/**
 * 
 */
	function deleteCheck() {
		result = confirm("정말로 게시글을 삭제하시겠습니까?");
		
		if(result) {
			document.FEForm1.action="boardDelete.do";
			document.FEForm1.submit();
		} else {
			return;
		}
	}
	
 	function replyDelete(reply_id) {
		result = confirm("정말로 댓글을 삭제하시겠습니까?");
		
		if(result) {
			document.location.href="/FarmEasy/replyDelete.do?reply_id="+reply_id;
		}
	}
 	
 	//팝업창 중앙정렬
 	function replyUpdate(reply_id) {
 	 	var popupX = (document.body.offsetWidth / 2) - (970 / 2);
 	 	var popupY = (window.screen.height / 2) - (115/2);
 		window.name="parentForm";
		window.open("d_reply_update.jsp?reply_id="+reply_id,"replyUpdateForm","status=no, width=970, height=115, left="+popupX+", top="+popupY+", resizable=no, scrollbars=no");
 	}
 	