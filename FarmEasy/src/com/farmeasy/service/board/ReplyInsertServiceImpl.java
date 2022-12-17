package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardReplyDto;

public class ReplyInsertServiceImpl implements ReplyInsertService {
	private BoardDao boardDao;
	
	public ReplyInsertServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.insertReply((BoardReplyDto)request.getAttribute("replyDto"));
		return true;
	}

}
