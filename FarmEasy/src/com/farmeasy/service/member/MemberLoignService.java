package com.farmeasy.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.farmeasy.model.member.*;

public interface MemberLoignService {
	MemberDto execute(HttpServletRequest request, HttpServletResponse response);
}
