package com.farmeasy.service.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.board.BoardReplyDto;

public interface ReplyListService {
	public ArrayList<BoardReplyDto> execute(HttpServletRequest request, HttpServletResponse response);
}
