package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberFindIdService {
	String execute(HttpServletRequest request, HttpServletResponse response);
}
