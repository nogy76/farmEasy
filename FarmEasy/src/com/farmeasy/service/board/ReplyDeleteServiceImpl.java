package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;

public class ReplyDeleteServiceImpl implements ReplyDeleteService {
	private BoardDao boardDao;
	
	public ReplyDeleteServiceImpl() {
		boardDao = BoardDao.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.deleteReply((int)request.getAttribute("reply_id"));
	}

}
