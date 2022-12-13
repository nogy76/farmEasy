package com.farmeasy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmeasy.model.*;

public class UploadServiceImpl implements UploadService {
	private FarmEasyDao farmEasyDao;
	
	public UploadServiceImpl() {
		farmEasyDao = FarmEasyDao.getInstance();
	}

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		farmEasyDao.insertBoard((BoardDto)request.getAttribute("boardDto"));
		farmEasyDao.uploadFile((BoardFileDto)request.getAttribute("boardFileDto"));
		return false;
	}

}
