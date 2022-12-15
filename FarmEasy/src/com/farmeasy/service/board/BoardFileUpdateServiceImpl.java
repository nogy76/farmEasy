package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardFileDto;

public class BoardFileUpdateServiceImpl implements BoardFileUpdateService {
	private BoardDao boardDao;
	
	public BoardFileUpdateServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.updateFile((int)request.getAttribute("updateBoardId"), (BoardFileDto)request.getAttribute("boardFileDto"));
	}
	
}
