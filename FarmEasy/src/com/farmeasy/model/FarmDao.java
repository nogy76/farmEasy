package com.farmeasy.model;

import javax.naming.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.websocket.SendResult;

import com.memberEx.ex.MemberDao;

public class FarmDao {

	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
//	static farmDao instance = new farmDao();
	
	static FarmDao farmDao;
	
	
	public FarmDao() {
		
	}
	
	public static FarmDao getInstance() {
		if(farmDao==null) {
			farmDao = new FarmDao();
		}
		return farmDao;
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

	public ArrayList<FarmDto> getList(){
		ArrayList<FarmDto> addrList = new ArrayList<>();
		
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
				
				addrList.add(new FarmDto(seq, name, id, pw, email, mobile, authority, date));
			}
		}catch(SQLException e) {
			System.out.println("getList() 예외 발생!!");
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
		
		return addrList;
		
		}
	
	
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
		            ri = FarmDao.MEMBER_EXISTENT;
		            // 1 존재하는 아이디
		         } else {
		        	 ri = FarmDao.MEMBER_NONEXISTENT;
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
		

		public int login(String m_id, String m_pw) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			String sql = "SELECT M_PW FROM TB_MEMBER WHERE M_ID=?";
			
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, m_id);
				rs = pstmt.executeQuery();
				
			
				if (rs.next()) {
		            if(rs.getString(1).equals(m_pw)) {
		            	System.out.println("1번");
		            	System.out.println("로그인  성공"+m_id+m_pw );
		            	return 1; 
		            	//로그인 성공
		            } else {
		            	System.out.println("비밀번호 오류"+m_id+m_pw );
		            	return 0;
		            	//로그인 실패
		            } // end of else
				} // end of if-첫번째
				else {   
					System.out.println("아이디가 없다?"+m_id+m_pw );
					return -1;  // 아이디 없음
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
			System.out.println("아이디가 없다?"+m_id+m_pw );
			return -2;
			//디비오류
		}
	
		
		
		
		
	
		public int insertAddr(FarmDto farmDto){
			int ri = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("INSERT INTO TB_MEMBER(m_seq, m_name, m_id, m_pw, m_email, m_mobile) VALUES(m_seq.nextval,?,?,?,?,?)");
				pstmt.setString(1, farmDto.getM_name());
				pstmt.setString(2, farmDto.getM_Id());
				pstmt.setString(3, farmDto.getM_pw());
				pstmt.setString(4, farmDto.getM_email());
				pstmt.setString(5, farmDto.getM_mobile());
				pstmt.executeQuery();
				//pstmt.executeUpdate();
				ri = MemberDao.MEMBER_JOIN_SUCCESS;
				
			}catch(SQLException e) {
				System.out.println("insertAddr() 예외 발생!!");
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
		} //end of insertAddr
		
		
		

		public FarmDto readById(int m_seq) {
			FarmDto farmDto = new FarmDto();
			
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
				
				farmDto = new FarmDto(m_seq, id, name ,pw, email, mobile, authority, date);
			
			} catch(SQLException e) {
				System.out.println("readById 예외 발생!!");
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
			
			return farmDto;
		}
		
		//업데이트
		public void updateAddr(int m_seq, FarmDto addr) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("update TB_MEMBER set m_name=?, m_id=?, m_pw=?, M_email=?, M_mobile=?, M_authority=?, M_date=?where m_seq="+m_seq);
			
				pstmt.setString(1, addr.getM_name());
				pstmt.setString(2, addr.getM_Id());
				pstmt.setString(3, addr.getM_pw());
				pstmt.setString(4, addr.getM_email());
				pstmt.setString(5, addr.getM_mobile());
				pstmt.setString(6, addr.getM_authority());
				pstmt.setString(7, addr.getM_date());
				
				
				pstmt.executeUpdate();
			} catch(SQLException e) {
				System.out.println("updateAddr() 예외 발생!!");
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
		
		public void deleteAddr(int m_seq) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM TB_MEMBER WHERE m_seq="+m_seq);
				
				pstmt.executeUpdate();
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
		}
}