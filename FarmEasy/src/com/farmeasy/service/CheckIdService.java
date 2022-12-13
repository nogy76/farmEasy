package com.farmeasy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CheckIdService {

	int execute(HttpServletRequest request, HttpServletResponse response);

}
