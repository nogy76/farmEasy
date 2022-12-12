package com.farmeasy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.farmeasy.model.*;
import com.farmeasy.service.*;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
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
		
		if(command.equals("/insert.do")) {
			
			System.out.println("insert.do 입니다.");
			
			BoardDto insBoard = new BoardDto();
//			insBoard.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			insBoard.setUser_name(request.getParameter("user_name"));
			
			insBoard.setBoard_title(request.getParameter("board_title"));
			insBoard.setBoard_content(request.getParameter("board_content"));
			
			request.setAttribute("insBoard", insBoard);
			
			InsertService insertService = new InsertServiceImpl();
			insertService.execute(request, response);
			response.sendRedirect("/FarmEasy/list.do");
		
		} else if(command.equals("/list.do")) {
			
			System.out.println("list.do 입니다.");
			
			ListService listService = new ListServiceImpl();
			ArrayList<BoardDto> boardList = listService.execute(request, response);
			
			request.setAttribute("boardList", boardList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board.jsp");
			dispatcher.forward(request, response);
			
		} else if(command.equals("/update.do")) {
			
			System.out.println("update.do 입니다.");
			HttpSession session = request.getSession();
			
			int updateBoardId = (int)session.getAttribute("updateDeleteBoardId");
			
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_title(request.getParameter("board_title"));
			boardDto.setBoard_content(request.getParameter("board_content"));
			
			request.setAttribute("updateBoardId", updateBoardId);
			request.setAttribute("boardDto", boardDto);
			
			UpdateService updateService = new UpdateServiceImpl();
			updateService.execute(request, response);
			response.sendRedirect("/FarmEasy/list.do");
			
		} else if(command.equals("/delete.do")) {
			
			System.out.println("delete.do 입니다.");
			HttpSession session = request.getSession();
			
			int deleteBoardId = (int)session.getAttribute("updateDeleteBoardId");
			request.setAttribute("deleteBoardId", deleteBoardId);
			
			DeleteService deleteService = new DeleteServiceImpl();
			deleteService.execute(request, response);
			response.sendRedirect("/FarmEasy/list.do");
			
		}
	}
}