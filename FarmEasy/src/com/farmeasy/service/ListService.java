package com.farmeasy.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.BoardDto;

public interface ListService {
	public ArrayList<BoardDto> execute(HttpServletRequest request, HttpServletResponse response);
}
