package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDao;

public class BoardDeleteServiceImpl implements BoardDeleteService {
	private BoardDao boardDao;
	
	public BoardDeleteServiceImpl() {
		boardDao = BoardDao.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.deleteBoard((int)request.getAttribute("deleteBoardId"));
	}

}
