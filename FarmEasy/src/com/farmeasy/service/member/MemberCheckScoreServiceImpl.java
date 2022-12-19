package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberCheckScoreServiceImpl implements MemberCheckScoreService{
	
	private MemberDao memberDao;
	
	public MemberCheckScoreServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
	    return memberDao.checkScore((MemberDto)request.getAttribute("memberDto"));
	}
}
