package com.farmeasy.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;


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
