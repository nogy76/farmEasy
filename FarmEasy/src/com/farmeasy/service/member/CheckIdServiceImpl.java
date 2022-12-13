package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class CheckIdServiceImpl implements CheckIdService{
	
	private FarmDao farmDao;
	
	public CheckIdServiceImpl() {
		farmDao = FarmDao.getInstance();
	}
	
	
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = (String)request.getAttribute("m_id");
		return farmDao.checkId(id);
	}
}
