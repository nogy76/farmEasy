package com.farmeasy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UploadService {
	public boolean execute(HttpServletRequest request, HttpServletResponse response);
}
