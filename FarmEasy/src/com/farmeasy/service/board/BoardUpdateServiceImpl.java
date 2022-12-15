package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardDto;

public class BoardUpdateServiceImpl implements BoardUpdateService {
	private BoardDao boardDao;
	
	public BoardUpdateServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.updateBoard((int)request.getAttribute("updateBoardId"), (BoardDto)request.getAttribute("boardDto"));
	}
	
}
