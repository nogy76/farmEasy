package com.farmeasy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.FarmEasyDao;

public class DeleteServiceImpl implements DeleteService {
	private FarmEasyDao farmEasyDao;
	
	public DeleteServiceImpl() {
		farmEasyDao = FarmEasyDao.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		farmEasyDao.deleteBoard((int)request.getAttribute("deleteBoardId"));
	}

}
