package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.MemberDao;

public class MemberDeleteServiceImpl implements MemberDeleteService {
	
	private MemberDao memberDao;
	
	public MemberDeleteServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		return memberDao.deleteMember((int)request.getAttribute("m_seq"),(String)request.getAttribute("m_pw"));
	}

}
