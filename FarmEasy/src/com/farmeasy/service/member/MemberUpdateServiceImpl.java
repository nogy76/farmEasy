package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.MemberDao;
import com.farmeasy.model.member.MemberDto;

public class MemberUpdateServiceImpl implements MemberUpdateService {
	
	private MemberDao memberDao;
	
	public MemberUpdateServiceImpl() {
		memberDao = MemberDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		memberDao.updateMember((int)request.getAttribute("m_seq"), 
				(MemberDto)request.getAttribute("memberDto"));
	}
	
}
