package com.farmeasy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.BoardDto;
import com.farmeasy.model.FarmEasyDao;

public class UpdateServiceImpl implements UpdateService {
	private FarmEasyDao farmEasyDao;
	
	public UpdateServiceImpl() {
		farmEasyDao = FarmEasyDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		farmEasyDao.updateBoard((int)request.getAttribute("updateBoardId"), (BoardDto)request.getAttribute("boardDto"));
	}
	
}
