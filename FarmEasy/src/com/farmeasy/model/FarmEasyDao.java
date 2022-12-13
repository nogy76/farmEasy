package com.farmeasy.model;

import java.sql.*;
import javax.sql.*;

import javax.naming.*;
import java.util.*;


public class FarmEasyDao {

	//싱글턴을 사용하기 위한 변수 생성
	static FarmEasyDao farmEasyDao;
	
	//생성자를 private으로 만들어 접근을 막음
	private FarmEasyDao() {
		
	}
	
	//getInstance 메소드를 통해 최초 생성된 객체 접근을 열어둠
	public static FarmEasyDao getInstance() {
		if(farmEasyDao == null) {
			farmEasyDao = new FarmEasyDao();
		}
		
		return farmEasyDao;
	}
	
	//DataSource로 DB와 연결
	//순수 jdbc로 DB 접근시, 접근 때마다 connection을 맺고 끊는 작업을 함.
	//이 작업을 줄이기 위해 미리 connection을 생성해 두고, 데이터베이스에 접근하고자 하는 사용자에게
	//미리 생성된 connection을 제공하고 돌려받음.
	//이 connection들을 모아두는 장소를 connection pool이라 하며,
	//Datasource는 java 에서 connection pool을 지원하기 위한 인터페이스이다!
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/Oracle11g2");
			connection = dataSource.getConnection();
			System.out.println("getConnection 성공!");
		} catch(Exception e) {
			System.out.println("getConnection() 예외 발생!");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//게시글 생성을 위한 메소드
	public void insertBoard(BoardDto boardDto) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("insert into fe_board(board_id, user_name, board_title, board_content) values(fe_board_seq.nextval,?,?,?)");
			pstmt.setString(1, boardDto.getUser_name());
			pstmt.setString(2, boardDto.getBoard_title());
			pstmt.setString(3, boardDto.getBoard_content());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertBoard() 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	//게시글 갱신을 위한 메소드
	public void updateBoard(int board_id, BoardDto boardDto) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("update fe_board set board_title=?, board_content=?, update_date=to_char(sysdate, 'yyyy.mm.dd') where board_id="+board_id);
			pstmt.setString(1, boardDto.getBoard_title());
			pstmt.setString(2, boardDto.getBoard_content());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateBoard() 예외 발생!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//게시글 삭제를 위한 메소드
	public void deleteBoard(int board_id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("delete from fe_board where board_id="+board_id);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("deleteBoard() 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//특정 게시글을 가져오는 메소드
	public BoardDto getBoardDB(int board_id) {
		BoardDto boardDto = new BoardDto();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("select * from fe_board where board_id=?");
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			
			//데이터 하나만 끌고 오기에 rs.next()를 한 번만 실행
			rs.next();
			
//			int user_id = rs.getInt("user_id");
			String user_name = rs.getString("user_name");
			
			String fe_type = rs.getString("fe_type");
			String board_title = rs.getString("board_title");
			String board_content = rs.getString("board_content");
			String insert_date = rs.getString("insert_date");
			String update_date = rs.getString("update_date");
			int board_hits = rs.getInt("board_hits");
			
			boardDto = new BoardDto(board_id, user_name, fe_type, board_title, board_content, insert_date, update_date, board_hits);
			
		} catch (SQLException e) {
			System.out.println("getBoardDB 예외 발생!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
				
		return boardDto;
	}
	
	//전체 게시글 목록을 가져오는 메소드
	public ArrayList<BoardDto> getList() {
		ArrayList<BoardDto> boardList = new ArrayList<BoardDto>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("select * from fe_board order by board_id desc");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int board_id = rs.getInt("board_id");
//				int user_id = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				
				String fe_type = rs.getString("fe_type");
				String board_title = rs.getString("board_title");
				String board_content = rs.getString("board_content");
				String insert_date = rs.getString("insert_date");
				String update_date = rs.getString("update_date");
				int board_hits = rs.getInt("board_hits");
				
				boardList.add(new BoardDto(board_id, user_name, fe_type, board_title, board_content, insert_date, update_date, board_hits));
			}
		} catch (SQLException e) {
			System.out.println("getList() 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return boardList;
	}
	
	//파일 업로드
	public void uploadFile(BoardFileDto boardFileDto) {
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("insert into fe_board_file(board_file_id, board_id, board_file_name, board_file_realName, board_file_byte) values(fe_board_file_seq.nextval,fe_board_seq.currval,?,?,?)");
			pstmt.setString(1, boardFileDto.getBoard_file_name());
			pstmt.setString(2, boardFileDto.getBoard_file_realName());
			pstmt.setString(3, boardFileDto.getBoard_file_byte());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("uploadFile() 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BoardFileDto getBoardFileDB(int board_id) {
		BoardFileDto boardFileDto = new BoardFileDto();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement("select * from fe_board_file where board_id=?");
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			
			//데이터 하나만 끌고 오기에 rs.next()를 한 번만 실행
			rs.next();
			
//			int user_id = rs.getInt("user_id");
			String board_file_name = rs.getString("board_file_name");
			String board_file_realName = rs.getString("board_file_realName");
			String board_file_byte = rs.getString("board_file_byte");
			
			boardFileDto = new BoardFileDto(board_id, board_file_name, board_file_realName, board_file_byte);
			
		} catch (SQLException e) {
			System.out.println("getBoardDB 예외 발생!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
				
		return boardFileDto;
	}
	
	
}
