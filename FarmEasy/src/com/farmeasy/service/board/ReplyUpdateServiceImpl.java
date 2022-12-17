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
		boardDao.updateReply((int)request.getAttribute("updateBoardId"), (BoardReplyDto)request.getAttribute("replyDto"));
	}
	
}
