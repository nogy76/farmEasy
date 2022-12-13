package com.farmeasy.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.farmeasy.model.member.FarmDto;
import com.farmeasy.service.member.*;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
		
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		//uri에서 경로의 길이만큼 자르면, '/'뒤 명령어를 뽑아낼 수 있음
		String command = uri.substring(conPath.length());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(command.equals("/checkId.do")) {
			
			String m_id = request.getParameter("m_id");
			System.out.println("id를 가져옴 : " + m_id);
			
			request.setAttribute("m_id", m_id);
			CheckIdService checkIdService = new CheckIdServiceImpl();
			
			if(checkIdService.execute(request, response) == 1) {
				request.setAttribute("check", 1);
	            //틀려써! 체크에 1을 담아서 보낸다!
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_idCheck.jsp");
				requestDispatcher.forward(request, response);
			}else {
		        //request.setAttribute("check", 0);
	        	//사용가능해..
				response.sendRedirect("e_idCheck.jsp");
				//메인페이지로 떨궈주면 되고..
			}
			
		} else if(command.equals("/insert.do")) {
			System.out.println("insert.do 입니다");
			
			FarmDto farmDto = new FarmDto();
			
			
			farmDto.setM_name(request.getParameter("m_name"));
			farmDto.setM_Id(request.getParameter("m_id"));
			farmDto.setM_pw(request.getParameter("m_pw"));
			farmDto.setM_email(request.getParameter("m_email"));
			farmDto.setM_mobile(request.getParameter("m_mobile"));
			farmDto.setM_authority(request.getParameter("m_authority"));
			farmDto.setM_date(request.getParameter("m_date"));
			
			request.setAttribute("farmDto", farmDto);
			
			
			InsertService InsertService = new InsertServiceImpl();
			InsertService.execute(request, response);
			response.sendRedirect("/FarmEasy/index.jsp");
			
		} else if(command.equals("/login.do")) {
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			System.out.println("id를 가져옴 : " + m_id);
			System.out.println("pw를 가져옴 : " + m_pw);
			request.setAttribute("m_id", m_id);
			request.setAttribute("m_pw", m_pw);
			
			LoignService loignService = new LoignServiceImpl();
			int i= loignService.execute(request, response);
			if(i == -1 || i == 0  || i == -2) {
				// 0 비밀번호 불일치, -1아이디 없음 , -2 데이터 베이스 오류
				  request.setAttribute("check", i);
		          RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginCheck.jsp");
		          requestDispatcher.forward(request, response);
				  //1번이면 통과..!
				
			}else {
				System.out.println("로그인 성공");
				HttpSession session = request.getSession();
				session.setAttribute("m_id", m_id);
				//클라이언트 단위로 정보를 유지하려고 HttpSession 객체에 등록  name, value
				response.sendRedirect("index.jsp");
			}
		}
	}
}