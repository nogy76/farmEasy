package com.farmeasy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.BoardDto;
import com.farmeasy.model.FarmEasyDao;

public class InsertServiceImpl implements InsertService {
	private FarmEasyDao farmEasyDao;
	
	public InsertServiceImpl() {
		farmEasyDao = FarmEasyDao.getInstance();
	}

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		farmEasyDao.insertBoard((BoardDto)request.getAttribute("insBoard"));
		return false;
	}

}
