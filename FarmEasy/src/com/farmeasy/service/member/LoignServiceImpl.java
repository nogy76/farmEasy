package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class LoignServiceImpl implements LoignService{
	
	private FarmDao farmDao;
	
	public LoignServiceImpl() {
		farmDao = FarmDao.getInstance();
	}
	
	
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = (String)request.getAttribute("m_id");
		String pw = (String)request.getAttribute("m_pw");
		return farmDao.login(id,pw);
	}
}
