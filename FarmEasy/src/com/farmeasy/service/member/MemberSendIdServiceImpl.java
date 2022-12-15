package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberSendIdServiceImpl implements MemberSendIdService{
	
	private MemberDao memberDao;
	
	public MemberSendIdServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String m_id = (String)request.getAttribute("m_id");
		return m_id;
	}
}
