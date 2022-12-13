package com.farmeasy.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;


public class MemberInsertServiceImpl implements MemberInsertService{
	
	private FarmDao farmDao;
	
	public MemberInsertServiceImpl() {
		farmDao = FarmDao.getInstance();
	}
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FarmDto farmDto = (FarmDto) request.getAttribute("farmDto");
		farmDao.insertAddr(farmDto);
	}
}
