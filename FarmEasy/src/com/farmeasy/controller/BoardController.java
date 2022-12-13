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

import com.farmeasy.model.board.*;
import com.farmeasy.service.board.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//.board로 매핑
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionBoard(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionBoard(request, response);
	}
		
	private void actionBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		//uri에서 경로의 길이만큼 자르면, '/'뒤 명령어를 뽑아낼 수 있음
		String command = uri.substring(conPath.length());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		//upload.board 커맨드로 insert까지 한 번에 처리
		if(command.equals("/insert.board")) {
			
//			System.out.println("insert.board 입니다.");
//			
//			BoardDto insBoard = new BoardDto();
////			insBoard.setUser_id(Integer.parseInt(request.getParameter("user_id")));
//			insBoard.setUser_name(request.getParameter("user_name"));
//			
//			insBoard.setBoard_title(request.getParameter("board_title"));
//			insBoard.setBoard_content(request.getParameter("board_content"));
//			
//			request.setAttribute("boardDto", boardDto);
//			
//			InsertService insertService = new InsertServiceImpl();
//			insertService.execute(request, response);
//			response.sendRedirect("/FarmEasy/list.board");
		
		//게시글 리스트 커맨드
		} else if(command.equals("/list.board")) {
			
			System.out.println("list.board 입니다.");
			
			ListService listService = new ListServiceImpl();
			ArrayList<BoardDto> boardList = listService.execute(request, response);
			
			request.setAttribute("boardList", boardList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board.jsp");
			dispatcher.forward(request, response);
		
		//게시글 업데이트 커맨드
		} else if(command.equals("/update.board")) {
			
			System.out.println("update.board 입니다.");
			HttpSession session = request.getSession();
			
			int updateBoardId = (int)session.getAttribute("updateDeleteBoardId");
			
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_title(request.getParameter("board_title"));
			boardDto.setBoard_content(request.getParameter("board_content"));
			
			request.setAttribute("updateBoardId", updateBoardId);
			request.setAttribute("boardDto", boardDto);
			
			UpdateService updateService = new UpdateServiceImpl();
			updateService.execute(request, response);
			response.sendRedirect("/FarmEasy/list.board");
			
		//게시글 삭제 커맨드
		} else if(command.equals("/delete.board")) {
			
			System.out.println("delete.board 입니다.");
			HttpSession session = request.getSession();
			
			int deleteBoardId = (int)session.getAttribute("updateDeleteBoardId");
			request.setAttribute("deleteBoardId", deleteBoardId);
			
			DeleteService deleteService = new DeleteServiceImpl();
			deleteService.execute(request, response);
			response.sendRedirect("/FarmEasy/list.board");
		
		//게시글 업로드 커맨드(게시글 작성과 게시글의 파일 업로드를 동시에 처리)
		} else if(command.equals("/upload.board")) {
			
			System.out.println("upload.board 입니다.");
			
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
					String boardFile = multi.getOriginalFileName("board_file");
				if(boardFile != null) {
					boardFileDto.setBoard_file_byte(boardFile);
					request.setAttribute("boardFileDto", boardFileDto);
					
					UploadService uploadService = new UploadServiceImpl();
					uploadService.execute(request, response);
					
					response.sendRedirect("/FarmEasy/list.board");
				} else {
					InsertService insertService = new InsertServiceImpl();
					insertService.execute(request, response);
					response.sendRedirect("/FarmEasy/list.board");
				}
				
				
			} catch(IOException e) {
				System.out.println("upload 에러 발생!");
				e.printStackTrace();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		} 
	}
}