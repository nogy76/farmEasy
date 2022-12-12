package com.farmeasy.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;

public class ListServiceImpl implements ListService {
	private FarmEasyDao farmEasyDao;
	
	public ListServiceImpl() {
		farmEasyDao = FarmEasyDao.getInstance();
	}

	@Override
	public ArrayList<BoardDto> execute(HttpServletRequest request, HttpServletResponse response) {
		return farmEasyDao.getList();
	}

}
