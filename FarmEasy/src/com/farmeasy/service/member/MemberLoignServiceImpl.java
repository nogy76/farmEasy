package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberLoignServiceImpl implements MemberLoignService{
	
	private MemberDao memberDao;
	
	public MemberLoignServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public MemberDto execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = (MemberDto) request.getAttribute("memberDto");
		return memberDao.login(memberDto);
	}
}
