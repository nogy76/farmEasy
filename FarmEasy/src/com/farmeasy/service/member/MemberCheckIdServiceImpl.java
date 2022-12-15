package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberCheckIdServiceImpl implements MemberCheckIdService{
	
	private MemberDao memberDao;
	
	public MemberCheckIdServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = (String)request.getAttribute("m_id");
		return memberDao.checkId(id);
	}
}
