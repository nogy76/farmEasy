package com.farmeasy.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardUploadService {
	public boolean execute(HttpServletRequest request, HttpServletResponse response);
}
