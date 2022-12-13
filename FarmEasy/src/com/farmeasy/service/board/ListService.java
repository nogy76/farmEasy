package com.farmeasy.service.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardDto;

public interface ListService {
	public ArrayList<BoardDto> execute(HttpServletRequest request, HttpServletResponse response);
}
