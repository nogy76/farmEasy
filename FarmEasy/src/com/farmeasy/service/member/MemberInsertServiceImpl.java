package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberInsertServiceImpl implements MemberInsertService{
	
	private MemberDao memberDao;
	
	public MemberInsertServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = (MemberDto) request.getAttribute("memberDto");
		memberDao.insertMember(memberDto);
	}
}
