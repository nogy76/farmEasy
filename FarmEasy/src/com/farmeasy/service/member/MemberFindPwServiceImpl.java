package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.member.*;


public class MemberFindPwServiceImpl implements MemberFindPwService{
	
	private MemberDao memberDao;
	
	public MemberFindPwServiceImpl() {
		memberDao = MemberDao.getInstance();
	}
	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = (MemberDto) request.getAttribute("memberDto");
		return memberDao.findPW(memberDto);
		//memberDao의 findPw 매소드이용해서 맞는 경우 ri를 리턴해줌
	}
}
