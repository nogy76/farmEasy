package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardReplyDto;

public class ReplyUpdateServiceImpl implements ReplyUpdateService {
	private BoardDao boardDao;
	
	public ReplyUpdateServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
<<<<<<< HEAD
		boardDao.updateReply((int)request.getAttribute("reply_id"), (BoardReplyDto)request.getAttribute("replyDto"));
=======
		boardDao.updateReply((int)request.getAttribute("updateBoardId"), 
				(BoardReplyDto)request.getAttribute("replyDto"));
>>>>>>> branch 'main' of https://github.com/nogy76/farmEasy
	}
	
}
