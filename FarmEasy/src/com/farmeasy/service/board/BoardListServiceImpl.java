package com.farmeasy.service.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;
import com.farmeasy.model.board.BoardDao;
import com.farmeasy.model.board.BoardDto;

public class BoardListServiceImpl implements BoardListService {
	private BoardDao boardDao;
	
	public BoardListServiceImpl() {
		boardDao = BoardDao.getInstance();
	}

	@Override
	public ArrayList<BoardDto> execute(HttpServletRequest request, HttpServletResponse response) {
		return boardDao.getBoardList();
	}

}
