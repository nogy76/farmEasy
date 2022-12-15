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
import com.farmeasy.model.member.*;
import com.farmeasy.service.board.*;
import com.farmeasy.service.member.*;


//파일 업로드, 다운로드를 위한 import문
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionBoard(request, response);
	}

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
		

		//boardUpload.do로 게시글 insert와 파일 첨부까지 동시에 진행
		
//		if(command.equals("/boardInsert.do")) {	
//			System.out.println("boardInsert.do 입니다.");
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
//			response.sendRedirect("/FarmEasy/boardList.do");
		
		
		//게시글 리스트 커맨드
		if(command.equals("/boardList.do")) {
			
			System.out.println("boardList.do 입니다.");
			
			BoardListService listService = new BoardListServiceImpl();
			ArrayList<BoardDto> boardList = listService.execute(request, response);
			
			request.setAttribute("boardList", boardList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board.jsp");
			dispatcher.forward(request, response);
		
			
		//게시글 업데이트 커맨드
		} else if(command.equals("/boardUpdate.do")) {
			
			System.out.println("boardUpdate.do 입니다.");
			HttpSession session = request.getSession();
			
			int updateBoardId = (int)session.getAttribute("updateDeleteBoardId");
			
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_title(request.getParameter("board_title"));
			boardDto.setBoard_content(request.getParameter("board_content"));
			
			request.setAttribute("updateBoardId", updateBoardId);
			request.setAttribute("boardDto", boardDto);
			
			BoardUpdateService updateService = new BoardUpdateServiceImpl();
			updateService.execute(request, response);
						
			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board_content.jsp?board_id="+updateBoardId);
			dispatcher.forward(request, response);
		
		
		//게시글 삭제 커맨드
		} else if(command.equals("/boardDelete.do")) {
			
			System.out.println("boardDelete.do 입니다.");
			HttpSession session = request.getSession();
			
			int deleteBoardId = (int)session.getAttribute("updateDeleteBoardId");
			request.setAttribute("deleteBoardId", deleteBoardId);
			
			BoardDeleteService deleteService = new BoardDeleteServiceImpl();
			deleteService.execute(request, response);
			response.sendRedirect("/FarmEasy/boardList.do");
		
		
		//게시글 업로드 커맨드(게시글 작성과 게시글의 파일 업로드를 동시에 처리)
		} else if(command.equals("/boardUpload.do")) {
			
			System.out.println("boardUpload.do 입니다.");
			
			//각각 다른 DB에 넣기 위해 각각 객체 생성
			BoardDto boardDto = new BoardDto();			
			BoardFileDto boardFileDto = new BoardFileDto();
			
			//상대 파일 경로
			String saveFolder = request.getSession().getServletContext().getRealPath("/upload");
			int maxSize = 5 * 1024 * 1024;
			
			//파일 업로드/다운로드를 위한 로직. form에서 encType을 다르게 받기 떄문에 multi.getParameter를 사용해야 값을 불러올 수 있음
			try {
				MultipartRequest multi = null;
				multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8", new DefaultFileRenamePolicy());
				
				HttpSession session = request.getSession();
				
				//세션에 세팅된 멤버 아이디를 활용하여 로그인한 회원의 ID를 작성자 ID로 사용하기 위한 세터 메소드
				boardDto.setUser_idName((String)session.getAttribute("m_id"));
				
				boardDto.setBoard_title(multi.getParameter("board_title"));
				boardDto.setBoard_content(multi.getParameter("board_content"));
				request.setAttribute("boardDto", boardDto);
				
				//업로드된 파일의 이름(실제 저장되는 이름)
				boardFileDto.setBoard_file_name(multi.getFilesystemName("board_file"));
				
				//압로드된 파일의 실제 이름(파일 자체의 실제 이름)
				boardFileDto.setBoard_file_realName(multi.getOriginalFileName("board_file"));
				
				//파일 업로드 유무에 따른 게시글 insert를 하기 위한 if문
				if(multi.getOriginalFileName("board_file") != null) {
				
					//업로드된 파일의 크기(byte단위)
					boardFileDto.setBoard_file_byte(String.valueOf(multi.getFile("board_file").length()));
					
					request.setAttribute("boardFileDto", boardFileDto);
					
					BoardUploadService uploadService = new BoardUploadServiceImpl();
					uploadService.execute(request, response);
					
					response.sendRedirect("/FarmEasy/boardList.board");
				
				//파일 업로드를 하지 않고 그냥 게시글만 쓸 경우
				} else {
					BoardInsertService insertService = new BoardInsertServiceImpl();
					insertService.execute(request, response);
					response.sendRedirect("/FarmEasy/boardList.board");
				}
				
			} catch(IOException e) {
				System.out.println("boardUpload 에러 발생!");
				e.printStackTrace();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		
		
		//id체크 커맨드
		} else if(command.equals("/memberCheckId.do")) {
			
			System.out.println("memberCheckId.do 입니다.");
			
			String m_id = request.getParameter("m_id");
			System.out.println("id를 가져옴 : " + m_id);
			
			request.setAttribute("m_id", m_id);
			MemberCheckIdService checkIdService = new MemberCheckIdServiceImpl();
			
			if(checkIdService.execute(request, response) == 1) {
				request.setAttribute("check", 1);
	            //틀려써! 체크에 1을 담아서 보낸다!
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_idCheck.jsp");
				requestDispatcher.forward(request, response);
			    //패킷에 데이터가 있는데, 페이지마다 이동 request. forward는 다음 페이지에 보내는것
			} else {
		        //request.setAttribute("check", 0);
	        	//사용가능해..
				response.sendRedirect("e_idCheck.jsp");
				//메인페이지로 떨궈주면 되고..
			}
		
			
		//회원 insert 커맨드
		} else if(command.equals("/memberInsert.do")) {
			
			System.out.println("memberInsert.do 입니다");
			
			MemberDto memberDto = new MemberDto();
			
			
			memberDto.setM_name(request.getParameter("m_name"));
			memberDto.setM_Id(request.getParameter("m_id"));
			memberDto.setM_pw(request.getParameter("m_pw"));
			memberDto.setM_email(request.getParameter("m_email"));
			memberDto.setM_mobile(request.getParameter("m_mobile"));
			memberDto.setM_authority(request.getParameter("m_authority"));
			memberDto.setM_date(request.getParameter("m_date"));
			
			request.setAttribute("memberDto", memberDto);
			
			BoardInsertService InsertService = new BoardInsertServiceImpl();
			InsertService.execute(request, response);
			response.sendRedirect("/FarmEasy/index.jsp");
		
		
		//로그인 커맨드
		} else if(command.equals("/memberLogin.do")) {
			
			System.out.println("memberLogin.do 입니다.");
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			System.out.println("id를 가져옴 : " + m_id);
			System.out.println("pw를 가져옴 : " + m_pw);
			request.setAttribute("m_id", m_id);
			request.setAttribute("m_pw", m_pw);
			
			MemberLoignService loignService = new MemberLoignServiceImpl();
			int i= loignService.execute(request, response);
			
			if(i == -1 || i == 0  || i == -2) {
				// 0 비밀번호 불일치, -1아이디 없음 , -2 데이터 베이스 오류
				  request.setAttribute("check", i);
		          RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginCheck.jsp");
		          requestDispatcher.forward(request, response);
				  //1번이면 통과..!
				
			} else {
				System.out.println("로그인 성공");
				HttpSession session = request.getSession();
				session.setAttribute("m_id", m_id);
				//클라이언트 단위로 정보를 유지하려고 HttpSession 객체에 등록  name, value
				response.sendRedirect("index.jsp");
			}
		
			
		// -------------------------
		// -----  아이디 찾기 ----------
		// -------------------------
		} else if(command.equals("/memberFindId.do")) {
			
			System.out.println("memberFindId.do 입니다.");
			
			String m_name = request.getParameter("m_name");
			String m_email = request.getParameter("m_email");
			System.out.println("아이디 찾기에서 입력된 m_name : " + m_name);
			System.out.println("아이디 찾기에서 입력된  m_email : " + m_email);
			request.setAttribute("m_name", m_name);
			request.setAttribute("m_email", m_email);
		
			MemberFindIdService findIdService = new MemberFindIdServiceImpl();
			String i= findIdService.execute(request, response);
			//set해서 보내기~
			
				// 이메일과 이름이 맞는 경우 1이 들어옴	
			if(i == "0" ||  i == "-1") {
			  request.setAttribute("findid", i);
			  //위에 request들고 체크jsp로 이동
		      RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginCheck.jsp");
		      requestDispatcher.forward(request, response);
			}else {
			 request.setAttribute("findid", i);
			  RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginCheck.jsp");
		      requestDispatcher.forward(request, response);
			}
			
//				
//				// 없어 돌아가
//				System.out.println("로그인 성공");
//				HttpSession session = request.getSession();
//				session.setAttribute("m_email", m_email);
//				//클라이언트 단위로 정보를 유지하려고 HttpSession 객체에 등록  name, value
//				response.sendRedirect("index.jsp");
//			}
		
		//end of login.do
		
		
		// -------------------------
		// ----- 비밀번호 찾기 ---------
		// -------------------------
		} else if(command.equals("/memberFindPw.do")) {
					
			System.out.println("memberFindPw.do 입니다");
					
			MemberDto memberDto = new MemberDto();
			
			memberDto.setM_name(request.getParameter("m_name"));
			memberDto.setM_Id(request.getParameter("m_id"));
			memberDto.setM_email(request.getParameter("m_email"));
			
			
			//입력받은 값 콘솔창에서 확인
			String m_name = request.getParameter("m_name");
			String m_id = request.getParameter("m_id");
			String m_email = request.getParameter("m_email");
			System.out.printf("비밀번호 찾기에서 입력된 값 %n 아이디 : %s %n 이름 : %s %n 이메일 : %s %n" ,m_id, m_name,m_email);
			//입력받은 값 콘솔창에서 확인
			
			request.setAttribute("memberDto", memberDto);
			
			MemberFindPwService findPwService = new MemberFindPwServiceImpl();
			String i= findPwService.execute(request, response);
			//set해서 보내기~
			
			
			if(i == "0" ||  i == "-1" || i == "1") {
				request.setAttribute("findpw", i);
				//위에 request들고 체크jsp로 이동
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginFindPw.jsp");
				requestDispatcher.forward(request, response);
			}else {
				request.setAttribute("findpw", i);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginFindPw.jsp");
				requestDispatcher.forward(request, response);
			}
			
			
			
//						System.out.println("로그인 성공");
//						HttpSession session = request.getSession();
//						session.setAttribute("m_email", m_email);
//						//클라이언트 단위로 정보를 유지하려고 HttpSession 객체에 등록  name, value
//						response.sendRedirect("index.jsp");
//					}
			
		} //end of findPw.do
		
	}
}