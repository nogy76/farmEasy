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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		// uri에서 경로의 길이만큼 자르면, '/'뒤 명령어를 뽑아낼 수 있음
		String command = uri.substring(conPath.length());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		// 게시글 insert 커맨드(게시글 작성과 게시글의 파일 업로드를 동시에 처리)
		if (command.equals("/boardInsert.do")) {

			System.out.println("boardInsert.do 입니다.");

			// 각각 다른 DB에 넣기 위해 각각 객체 생성
			BoardDto boardDto = new BoardDto();
			BoardFileDto boardFileDto = new BoardFileDto();

			// 상대 파일 경로
			String saveFolder = request.getSession().getServletContext().getRealPath("/fileUpload");
			System.out.println(saveFolder);
			int maxSize = 5 * 1024 * 1024;

			// 파일 업로드/다운로드를 위한 로직. form에서 encType을 다르게 받기 떄문에 multi.getParameter를 사용해야 값을
			// 불러올 수 있음
			try {
				// 아래 코드로 파일 저장됨
				MultipartRequest multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8",
						new DefaultFileRenamePolicy());

				// 세션에 세팅된 멤버 아이디를 활용하여 로그인한 회원의 ID를 작성자 ID로 사용하기 위한 세터 메소드
				boardDto.setUser_idName((String) request.getSession().getAttribute("m_id"));

				boardDto.setBoard_title(multi.getParameter("board_title"));
				boardDto.setBoard_content(multi.getParameter("board_content"));

				request.setAttribute("boardDto", boardDto);

				if ((multi.getOriginalFileName("board_file")) != null) {

					// 업로드된 파일의 이름(실제 저장되는 이름)
					boardFileDto.setBoard_file_name(multi.getFilesystemName("board_file"));

					// 압로드된 파일의 실제 이름(파일 자체의 실제 이름)
					boardFileDto.setBoard_file_realName(multi.getOriginalFileName("board_file"));

					// 업로드된 파일의 크기(byte단위)
					boardFileDto.setBoard_file_byte(String.valueOf(multi.getFile("board_file").length()));

					request.setAttribute("boardFileDto", boardFileDto);

				} else {
					// 파일을 첨부하지 않았을 때
					boardFileDto = null;
					request.setAttribute("boardFileDto", boardFileDto);
				}

				BoardInsertService insertService = new BoardInsertServiceImpl();
				insertService.execute(request, response);

				response.sendRedirect("/FarmEasy/boardList.do");

			} catch (IOException e) {
				System.out.println("boardInsert 에러 발생!");
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
		// 게시글 리스트 커맨드
		} else if(command.equals("/boardList.do")) {

			System.out.println("boardList.do 입니다.");

			BoardListService listService = new BoardListServiceImpl();
			ArrayList<BoardDto> boardList = listService.execute(request, response);

			request.setAttribute("boardList", boardList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board.jsp");
			dispatcher.forward(request, response);

			
		// 게시글 업데이트 커맨드
		} else if (command.equals("/boardUpdate.do")) {

			System.out.println("boardUpdate.do 입니다.");
			HttpSession session = request.getSession();

			int updateBoardId = (int) session.getAttribute("updateDeleteBoardId");

			// 각각 다른 DB에 넣기 위해 각각 객체 생성
			BoardDto boardDto = new BoardDto();
			BoardFileDto boardFileDto = new BoardFileDto();

			// 상대 파일 경로
			String saveFolder = request.getSession().getServletContext().getRealPath("/fileUpload");
			int maxSize = 5 * 1024 * 1024;

			// 파일 업로드/다운로드를 위한 로직. form에서 encType을 다르게 받기 떄문에 multi.getParameter를 사용해야 값을
			// 불러올 수 있음
			try {
				MultipartRequest multi = null;
				multi = new MultipartRequest(request, saveFolder, maxSize, "utf-8", new DefaultFileRenamePolicy());

				boardDto.setBoard_title(multi.getParameter("board_title"));
				boardDto.setBoard_content(multi.getParameter("board_content"));
				request.setAttribute("boardDto", boardDto);
				request.setAttribute("updateBoardId", updateBoardId);

				// 업로드된 파일의 이름(실제 저장되는 이름)
				boardFileDto.setBoard_file_name(multi.getFilesystemName("board_file"));

				// 압로드된 파일의 실제 이름(파일 자체의 실제 이름)
				boardFileDto.setBoard_file_realName(multi.getOriginalFileName("board_file"));

				// 파일 업로드 유무에 따른 게시글 insert를 하기 위한 if문
				if (multi.getOriginalFileName("board_file") != null) {

					// 업로드된 파일의 크기(byte단위)
					boardFileDto.setBoard_file_byte(String.valueOf(multi.getFile("board_file").length()));

					request.setAttribute("boardFileDto", boardFileDto);

					BoardUpdateService updateService = new BoardUpdateServiceImpl();
					updateService.execute(request, response);

					BoardFileUpdateService fileUpdateService = new BoardFileUpdateServiceImpl();
					fileUpdateService.execute(request, response);

					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/d_board_content.jsp?board_id=" + updateBoardId);
					dispatcher.forward(request, response);

					// 파일 업로드를 하지 않고 그냥 게시글만 쓸 경우
				} else {
					BoardUpdateService updateService = new BoardUpdateServiceImpl();
					updateService.execute(request, response);

					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/d_board_content.jsp?board_id=" + updateBoardId);
					dispatcher.forward(request, response);
				}

			} catch (IOException e) {
				System.out.println("boardUpdate 에러 발생!");
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			
		// 게시글 삭제 커맨드
		} else if (command.equals("/boardDelete.do")) {

			System.out.println("boardDelete.do 입니다.");
			HttpSession session = request.getSession();

			int deleteBoardId = (int) session.getAttribute("updateDeleteBoardId");
			request.setAttribute("deleteBoardId", deleteBoardId);

			BoardDeleteService deleteService = new BoardDeleteServiceImpl();
			deleteService.execute(request, response);
			response.sendRedirect("/FarmEasy/boardList.do");

			
		// 댓글 작성 커맨드
		} else if (command.equals("/replyInsert.do")) {

			System.out.println("replyInsert.do 입니다.");

			BoardReplyDto replyDto = new BoardReplyDto();

			HttpSession session = request.getSession();

			int board_id = (int) session.getAttribute("updateDeleteBoardId");

			replyDto.setBoard_id(board_id);
			replyDto.setUser_idName((String) session.getAttribute("m_id"));
			replyDto.setReply_content((request.getParameter("reply_content")));

			request.setAttribute("replyDto", replyDto);

			ReplyInsertService replyInsertService = new ReplyInsertServiceImpl();
			replyInsertService.execute(request, response);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board_content.jsp?board_id=" + board_id);
			dispatcher.forward(request, response);

			
		// 댓글 업데이트 커맨드
		} else if (command.equals("/replyUpdate.do")) {

			System.out.println("replyUpdate.do 입니다.");
			
//			HttpSession session = request.getSession();
//			int updateId = (int) session.getAttribute("updateDeleteBoardId");

			// DB에 넣기 위해 객체 생성
			BoardReplyDto replyDto = new BoardReplyDto();

			replyDto.setReply_content(request.getParameter("reply_content"));
			
			request.setAttribute("reply_id", Integer.parseInt(request.getParameter("reply_id")));
			request.setAttribute("replyDto", replyDto);

			ReplyUpdateService replyUpdateService = new ReplyUpdateServiceImpl();
			replyUpdateService.execute(request, response);

//			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board_content.jsp?board_id=" + updateId);
//			dispatcher.forward(request, response);

			
		// 댓글 삭제 커맨드
		} else if (command.equals("/replyDelete.do")) {

			System.out.println("replyDelete.do 입니다.");

			int board_id = (int) request.getSession().getAttribute("updateDeleteBoardId");

			int reply_id = Integer.parseInt(request.getParameter("reply_id"));
			request.setAttribute("reply_id", reply_id);

			ReplyDeleteService replyDeleteService = new ReplyDeleteServiceImpl();
			replyDeleteService.execute(request, response);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/d_board_content.jsp?board_id=" + board_id);
			dispatcher.forward(request, response);

			
		// id체크 커맨드
		} else if (command.equals("/memberCheckId.do")) {

			System.out.println("memberCheckId.do 입니다.");

			String m_id = request.getParameter("m_id");
			System.out.println("id를 가져옴 : " + m_id);

			request.setAttribute("m_id", m_id);
			MemberCheckIdService checkIdService = new MemberCheckIdServiceImpl();

			if (checkIdService.execute(request, response) == 1) {
				request.setAttribute("check", 1);
				// 틀려써! 체크에 1을 담아서 보낸다!
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_idCheck.jsp");
				requestDispatcher.forward(request, response);
				// 패킷에 데이터가 있는데, 페이지마다 이동 request. forward는 다음 페이지에 보내는것
			} else {
				// request.setAttribute("check", 0);
				// 사용가능해..
				response.sendRedirect("e_idCheck.jsp");
				// 메인페이지로 떨궈주면 되고..
			}

			
		// 회원 insert 커맨드
		} else if (command.equals("/memberInsert.do")) {

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

			MemberInsertService InsertService = new MemberInsertServiceImpl();
			InsertService.execute(request, response);
			response.sendRedirect("/FarmEasy/index.jsp");

			
		// 로그인 커맨드
		} else if(command.equals("/memberLogin.do")) {
			
			System.out.println("memberLogin.do 입니다.");
			
			
			String m_id = request.getParameter("m_id");
			String m_pw = request.getParameter("m_pw");
			
			MemberDto memberDto = new MemberDto(m_id,m_pw);
			
			System.out.println("id를 가져옴 : " + m_id);
			System.out.println("pw를 가져옴 : " + m_pw);
			
			request.setAttribute("memberDto", memberDto);
			MemberLoignService loignService = new MemberLoignServiceImpl();
			memberDto = loignService.execute(request, response);
			
			HttpSession session = request.getSession();
			// dto에 들어감 
			
			if(memberDto.getM_email() != null) {
				System.out.println("로그인 성공");
				session.setAttribute("memberDto",memberDto);
				session.setAttribute("m_name", memberDto.getM_name());
				session.setAttribute("m_id", memberDto.getM_Id());
				session.setAttribute("m_pw", memberDto.getM_pw());
				session.setAttribute("m_pw", memberDto.getM_pw());
				session.setAttribute("m_email", memberDto.getM_email());
				session.setAttribute("m_mobile", memberDto.getM_mobile());
				System.out.printf("아이디 :%s , 비밀번호:%s , 이름 : %s", memberDto.getM_Id(),memberDto.getM_pw(),memberDto.getM_name());
				response.sendRedirect("index.jsp");
			}else {
				memberDto=null;
				System.out.println("로그인 실패");
				session.setAttribute("memberDto",memberDto);
				
	            //틀려써! 체크에 1을 담아서 보낸다!
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginCheck.jsp");
				requestDispatcher.forward(request, response);
			}	
			
		// -------------------------
		// ----- 아이디 찾기 ----------
		// -------------------------
		} else if (command.equals("/memberFindId.do")) {

			System.out.println("memberFindId.do 입니다.");

			String m_name = request.getParameter("m_name");
			String m_email = request.getParameter("m_email");
			System.out.println("아이디 찾기에서 입력된 m_name : " + m_name);
			System.out.println("아이디 찾기에서 입력된  m_email : " + m_email);
			request.setAttribute("m_name", m_name);
			request.setAttribute("m_email", m_email);

			MemberFindIdService findIdService = new MemberFindIdServiceImpl();
			String i = findIdService.execute(request, response);
			// set해서 보내기~

			// 이메일과 이름이 맞는 경우 1이 들어옴
			if (i == "0" || i == "-1") {
				request.setAttribute("findid", i);
				// 위에 request들고 체크jsp로 이동
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginCheck.jsp");
				requestDispatcher.forward(request, response);
			} else {
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

			// end of login.do

			
		// -------------------------
		// ----- 비밀번호 찾기 ----------
		// -------------------------
		} else if (command.equals("/memberFindPw.do")) {

			System.out.println("memberFindPw.do 입니다");

			MemberDto memberDto = new MemberDto();

			memberDto.setM_name(request.getParameter("m_name"));
			memberDto.setM_Id(request.getParameter("m_id"));
			memberDto.setM_email(request.getParameter("m_email"));

			// 입력받은 값 콘솔창에서 확인
			String m_name = request.getParameter("m_name");
			String m_id = request.getParameter("m_id");
			String m_email = request.getParameter("m_email");
			System.out.printf("비밀번호 찾기에서 입력된 값 %n 아이디 : %s %n 이름 : %s %n 이메일 : %s %n", m_id, m_name, m_email);
			// 입력받은 값 콘솔창에서 확인

			request.setAttribute("memberDto", memberDto);

			MemberFindPwService findPwService = new MemberFindPwServiceImpl();
			String i = findPwService.execute(request, response);
			// set해서 보내기~

			if (i == "0" || i == "-1" || i == "1") {
				request.setAttribute("findpw", i);
				// 위에 request들고 체크jsp로 이동
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("e_loginFindPw.jsp");
				requestDispatcher.forward(request, response);
			} else {
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

		} // end of findPw.do

	}
}