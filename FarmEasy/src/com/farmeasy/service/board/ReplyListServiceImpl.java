package com.farmeasy.service.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;
import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardReplyDto;

public class ReplyListServiceImpl implements ReplyListService {
	private BoardDao boardDao;
	
	public ReplyListServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public ArrayList<BoardReplyDto> execute(HttpServletRequest request, HttpServletResponse response) {
		return boardDao.getReplyList((int)request.getAttribute("board_id"));
	}

}
