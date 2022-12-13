package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;
import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardDto;
import com.farmeasy.model.board.BoardFileDto;

public class UploadServiceImpl implements UploadService {
	private BoardDao boardDao;
	
	public UploadServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		boardDao.insertBoard((BoardDto)request.getAttribute("boardDto"));
		boardDao.uploadFile((BoardFileDto)request.getAttribute("boardFileDto"));
		return false;
	}

}
