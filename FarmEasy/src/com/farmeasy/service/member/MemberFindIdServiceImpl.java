package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberFindIdServiceImpl implements MemberFindIdService{
	
	private MemberDao memberDao;
	
	public MemberFindIdServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String m_name = (String)request.getAttribute("m_name");
		String m_email = (String)request.getAttribute("m_email");
		//farmDao의 findId 매소드이용해서 맞는 경우 id를 리턴해줌
		return memberDao.findId(m_name,m_email);
	}
}
