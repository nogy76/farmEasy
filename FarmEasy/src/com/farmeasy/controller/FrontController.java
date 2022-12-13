package com.farmeasy.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.farmeasy.model.*;
import com.farmeasy.service.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;


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
			
//			BoardDto insBoard = new BoardDto();
////			insBoard.setUser_id(Integer.parseInt(request.getParameter("user_id")));
//			insBoard.setUser_name(request.getParameter("user_name"));
//			
//			insBoard.setBoard_title(request.getParameter("board_title"));
//			insBoard.setBoard_content(request.getParameter("board_content"));
//			
//			request.setAttribute("insBoard", insBoard);
//			
//			InsertService insertService = new InsertServiceImpl();
//			insertService.execute(request, response);
//			response.sendRedirect("/FarmEasy/list.do");
		
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
			
		} else if(command.equals("/upload.do")) {
			
			System.out.println("upload.do 입니다.");
			
			BoardDto boardDto = new BoardDto();			
			BoardFileDto boardFileDto = new BoardFileDto();

			//파일 경로
			String saveFolder = "C:\\Users\\OHR\\git\\farmEasy\\FarmEasy\\WebContent\\upload";
			int maxSize = 5 * 1024 * 1024;
			
			try {
				MultipartRequest multi = null;
				multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8", new DefaultFileRenamePolicy());
				
				boardDto.setUser_name(multi.getParameter("user_name"));
				boardDto.setBoard_title(multi.getParameter("board_title"));
				boardDto.setBoard_content(multi.getParameter("board_content"));
				request.setAttribute("boardDto", boardDto);
				
				boardFileDto.setBoard_file_name(multi.getFilesystemName("board_file"));
				boardFileDto.setBoard_file_realName(multi.getOriginalFileName("board_file"));
				boardFileDto.setBoard_file_byte(String.valueOf((multi.getFile("board_file").length())));
				request.setAttribute("boardFileDto", boardFileDto);
				
				UploadService uploadService = new UploadServiceImpl();
				uploadService.execute(request, response);
				
				response.sendRedirect("/FarmEasy/list.do");
				
			} catch(IOException e) {
				System.out.println("upload 에러 발생!");
				e.printStackTrace();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
		} else if(command.equals("/checkId.do")) {
			
			String m_id = request.getParameter("m_id");
			System.out.println("id를 가져옴 : " + m_id);
			
			request.setAttribute("m_id", m_id);
			CheckIdService checkIdService = new CheckIdServiceImpl();
			if(checkIdService.execute(request, response) == 1) {
				request.setAttribute("check", 1);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_idCheck.jsp");
				requestDispatcher.forward(request, response);
			}else {
				response.sendRedirect("e_idCheck.jsp");
				//메인페이지로 떨궈주면 되고..
			}
			
		} else if(command.equals("/memberInsert.do")) {
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
			
			
			MemberInsertService memberInsertService = new MemberInsertServiceImpl();
			memberInsertService.execute(request, response);
			response.sendRedirect("/FarmEasy/index.jsp");
		}
	}
}