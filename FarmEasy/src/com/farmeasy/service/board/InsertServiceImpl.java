package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardDto;

public class InsertServiceImpl implements InsertService {
	private BoardDao boardDao;
	
	public InsertServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.insertBoard((BoardDto)request.getAttribute("boardDto"));
		return false;
	}

}
