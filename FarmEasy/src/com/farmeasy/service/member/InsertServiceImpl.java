package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class InsertServiceImpl implements InsertService{
	
	private FarmDao farmDao;
	
	public InsertServiceImpl() {
		farmDao = FarmDao.getInstance();
	}
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FarmDto farmDto = (FarmDto) request.getAttribute("farmDto");
		farmDao.insertAddr(farmDto);
	}
}
