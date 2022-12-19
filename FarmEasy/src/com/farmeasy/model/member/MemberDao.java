package com.farmeasy.model.member;

import javax.naming.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import sun.security.mscapi.CKeyPairGenerator.RSA;

public class MemberDao {

	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	static MemberDao memberDao;
	
	
	public MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		if(memberDao==null) {
			memberDao = new MemberDao();
		}
		return memberDao;
	}
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/Oracle11g2");
			connection = dataSource.getConnection();
			System.out.println("getConnection 성공!");
		}
		catch(Exception e) {
			System.out.println("getConnection() 예외 발생!");
			e.printStackTrace();
		}
		
		return connection;
		
	} // end of getConnection()

	public ArrayList<MemberDto> getMemberList(){
		ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from TB_MEMBER order by m_seq desc");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int seq = rs.getInt("m_seq");
				String name = rs.getString("m_name");
				String id = rs.getString("m_id");
				String pw = rs.getString("m_pw");
				String email = rs.getString("m_email");
				String mobile = rs.getString("m_mobile");
				String authority = rs.getString("m_authority");
				String date = rs.getString("m_date");
				String score = rs.getString("m_score");
				
				memberList.add(new MemberDto(seq, name, id, pw, email, mobile, authority, date,score));
			}
		}catch(SQLException e) {
			System.out.println("getMemberList() 예외 발생!!");
			 e.printStackTrace();
		 }finally {
			 try {
				 rs.close();
				 pstmt.close();
				 conn.close();
			 } catch(Exception e2) {
				 e2.printStackTrace();
			 }
		 	} // end of finally
		
		return memberList;
		
		}
	
	
	
	// -------------------------
	// -----  아이디 찾기 ----------
	// -------------------------	
	
	public String findId(String m_name, String m_email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT M_ID, M_EMAIL FROM TB_MEMBER WHERE M_NAME=?";
		String ri="";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_name); //물음표에 m_name을 넣어주고
			rs = pstmt.executeQuery(); // 결과값은 rs에 저장
			
			if (rs.next()) {
				if(rs.getString("m_email").equals(m_email)) {
					System.out.println("rs.next에 m_email는 "+rs.getString("m_email") + " | rs.next에 m_id는 "+ rs.getString("m_id")); 
					ri = rs.getString("m_id");
					return ri;
					// 이름이 있으면 아이디 리턴받음
				}else {
					System.out.println("이메일이 없음 rs.next에 m_email는  "+rs.getString("m_email") + "| 들어온 값 : " + m_email + " rs.next에 m_id는  "+ rs.getString("m_id"));
					ri = "0";
					return ri;
					// 이메일이 없으면 0
				}
			} else {
				ri = "-1";
				return ri;
				// 이름이 없다 -1
			}
		}catch(SQLException e) {
			System.out.println("findId 예외 발생!!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		} 
		System.out.println("ri값 체크 : " + ri);
		return ri;
	}
	
	
	
	// -------------------------
	// ----- 비밀번호 찾기 ---------
	// -------------------------
	
	
	public String findPW(MemberDto memberDto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT M_NAME, M_EMAIL, M_PW FROM TB_MEMBER WHERE M_ID=?";
		
		//return값을 확인하기 위한 변수 선언
		String ri="";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getM_Id()); //물음표에 m_name을 넣어주고
			//DB에서 이름, 이메일, 패스워드를 행으로 읽기
			rs = pstmt.executeQuery(); // 결과값은 rs에 저장
			
			// String타입의 m_email에 쿼리문에서 받은 m_email을 넣어주고
			// 폼에서 넘어온 m_name, m_eamil, m_id 세팅
			String m_name = memberDto.getM_name();
			String m_email = memberDto.getM_email();
			
			
			//이름, 아이디, 이메일 주소를 비교 
			if (rs.next()) {
				//이름으로 검색해서 이름이 있다면
				if(rs.getString("m_name").equals(m_name) && rs.getString("m_email").equals(m_email)) {
					//세 개가 다 같다면
					String name = rs.getString("m_name");
					String email = rs.getString("m_email");
					System.out.printf("1. DB에 있는 이름 : %s, 이메일 : %s" , name, email); 
					System.out.printf("%n1. 비번찾기에 있는 이름 : %s, 이메일 : %s" , m_name, m_email);
					ri = rs.getString("m_pw");
					return ri;
					// 이름과 이메일 >> 세개 다 맞는 경우는 비밀번호 보여주기
				}else if(rs.getString("m_name").equals(m_name)) {
					String name = rs.getString("m_name");
					String email = rs.getString("m_email");
					System.out.printf("2. DB에 있는 이름 : %s, 이메일 : %s" , name, email); 
					System.out.printf("%n2. 비번찾기에 있는 이름 : %s, 이메일 : %s " , m_name, m_email);
					ri = "1";
					return ri;
					// 이메일이 틀림
				}else{
					String name = rs.getString("m_name");
					String email = rs.getString("m_email");
					System.out.printf("DB에 있는 이름 : %s, 이메일 : %s %n" , name, email); 
					System.out.printf("2. 비번찾기에 있는 이름 : %s, 이메일 : %s" , m_name, m_email);
					ri = "0";
					return ri;
					// 이름이 틀리다면 0
				}
			} else {
				ri = "-1";
				return ri;
				// 아이디가 없으면 -1
			}
		}catch(SQLException e) {
			System.out.println("findPw 예외 발생!!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		} 
		System.out.println("ri값 체크 : " + ri);
		return "-2";
		//이것도 저것도 아니다 
	}
	
	
	
	// -------------------------
	// ------ 아이디 체크 ---------
	// -------------------------
	
	
	public int checkId(String id) {
		
		int ri=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String m_id = null;
		String sql = "SELECT M_ID FROM TB_MEMBER WHERE M_ID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				m_id = rs.getString("m_id");
				System.out.println("rs.next에 m_id는 "+m_id);
				ri = MemberDao.MEMBER_EXISTENT;
				// 1 존재하는 아이디
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT;
				//0
			}
		}catch(SQLException e) {
			System.out.println("checkId() 예외 발생!!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		} 
		if (m_id != null) {
			System.out.println("id체크() 존재하는 id" + ri);
			return 1;
		}else {
			System.out.println("id체크() 중복없는 id" + ri);
			return 0;
		}
	}
	
	
	// -------------------------
	// -------- 점수 체크 ---------
	// -------------------------
	public boolean checkScore(MemberDto memberDto) {
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update TB_MEMBER set m_score=? where m_seq=?");
			pstmt.setString(1, memberDto.getM_score());			
			pstmt.setInt(2, memberDto.getM_seq());	
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("checkScore() 예외 발생!!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
	}
	
	
	
	
	
	// -------------------------
	// --------- 로그인 ----------
	// -------------------------
	
	public MemberDto login(MemberDto memberDto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/* String sql = "SELECT M_PW FROM TB_MEMBER WHERE M_ID=?"; */
		String sql = "SELECT * FROM TB_MEMBER WHERE M_ID=? and M_PW=?";
		
		try {
			
			String m_id = memberDto.getM_Id();
			String m_pw = memberDto.getM_pw();
			
			System.out.println(m_id + "" + m_pw);
			//폼에서 넘어온 id랑 비밀번호 세팅해서
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id); // 1번 물음표에 m_id가 들어감
			pstmt.setString(2, m_pw);
			rs = pstmt.executeQuery();

			
			if (rs.next()) {	
					// resultset이 디비에서 쿼리문이 실행했을 때의 값을 들고옴
					System.out.println("1번");
					System.out.println("로그인  성공");
					
					int m_seq = rs.getInt("m_seq");
					String name = rs.getString("m_name");
					String id = rs.getString("m_id");
					String pw = rs.getString("m_pw");
					String email = rs.getString("m_email");
					String mobile = rs.getString("m_mobile");
					String authority = rs.getString("m_authority");
					String date = rs.getString("m_date");
					String score = rs.getString("m_score");
					
					System.out.printf("아이디  : %s ", id);
					
					memberDto = new MemberDto(m_seq, name , id, pw, email, mobile, authority, date, score);

					//로그인 성공 아이디 패스워드가 맞다면
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		} 
		return memberDto; 
	}
	
	
	// -------------------------
	// -------- 회원가입 ---------
	// -------------------------
	
	public int insertMember(MemberDto memberDto){
		int ri = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO TB_MEMBER(m_seq, m_name, m_id, m_pw, m_email, m_mobile) VALUES(m_seq.nextval,?,?,?,?,?)");
			pstmt.setString(1, memberDto.getM_name());
			pstmt.setString(2, memberDto.getM_Id());
			pstmt.setString(3, memberDto.getM_pw());
			pstmt.setString(4, memberDto.getM_email());
			pstmt.setString(5, memberDto.getM_mobile());
			pstmt.executeQuery();
			//pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
			
		}catch(SQLException e) {
			System.out.println("insertMember() 예외 발생!!");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
				
			}
		}
		return ri;
	} //end of insertMember
	
	
	
	public MemberDto readById(int m_seq) {
		MemberDto memberDto = new MemberDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM TB_MEMBER WHERE m_id=?");
			pstmt.setInt(1, m_seq);
			rs = pstmt.executeQuery();
			// 유저아이디랑 none아이디[m_seq]
			// 유저 아이디를 검색하는 것 : 매개변수로 아이디를 입력해서 dao에 매소드가 하나잇어야한다
			// dao에서 쿼리문  폼에서 서밋해서 넘어가는데, 모든값이 입력
			
			rs.next();
			String name = rs.getString("m_name");
			String id = rs.getString("m_id");
			String pw = rs.getString("m_pw");
			String email = rs.getString("m_email");
			String mobile = rs.getString("m_mobile");
			String authority = rs.getString("m_authority");
			String date = rs.getString("m_date");
			String score = rs.getString("m_score");
			
			System.out.printf("아이디  : %s ", id);
			
			memberDto = new MemberDto(m_seq, name , id, pw, email, mobile, authority, date, score);
			
		} catch(SQLException e) {
			System.out.println("readById() 예외 발생!!");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return memberDto;
	}
	
	
	
	// -------------------------
	// -------- 회원 수정 ---------
	// -------------------------
	
	public void updateMember(int m_seq, MemberDto memberDto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update TB_MEMBER set m_pw=?, M_email=?, M_mobile=?where m_seq="+m_seq);
			
			pstmt.setString(1, memberDto.getM_pw());
			pstmt.setString(2, memberDto.getM_email());
			pstmt.setString(3, memberDto.getM_mobile());
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("updateMember() 예외 발생!!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	
	// -------------------------
	// -------- 회원 탈퇴 ---------
	// -------------------------
	
	public boolean deleteMember(int m_seq,String m_pw) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			pstmt1 = conn.prepareStatement("select * from TB_MEMBER WHERE m_seq=? and M_PW=?");
			pstmt1.setInt(1, m_seq); // 1번 물음표에 m_id가 들어감
			pstmt1.setString(2, m_pw);
			rs = pstmt1.executeQuery();

			
			if (rs.next()) {	
				pstmt = conn.prepareStatement("DELETE FROM TB_MEMBER WHERE m_seq="+m_seq);
				pstmt.executeUpdate();
				return true;
			} else {
				return false;
			}
		}catch(SQLException e) {
			System.out.println("deleteAddr() 예외 발생!!");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
}