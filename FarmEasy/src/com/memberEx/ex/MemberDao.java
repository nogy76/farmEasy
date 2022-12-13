package com.memberEx.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle11g2");
			connection = dataSource.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public int insertMember(MemberDto dto) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TB_MEMBER(m_seq, m_name, m_id, m_pw, m_email, m_mobile) VALUES(m_seq.nextval,?,?,?,?,?)";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, dto.getM_name());
			pstmt.setString(2, dto.getM_Id());
			pstmt.setString(3, dto.getM_pw());
			pstmt.setString(4, dto.getM_email());
			pstmt.setString(5, dto.getM_mobile());
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				} 
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int confirmId(String m_id) {
		int ri = 0;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select m_id from TB_MEMBER where m_id = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, m_id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int userCheck(String m_id, String m_pw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select m_pw from TB_MEMBER where m_id=?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, m_id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dbPw = set.getString("m_pw");
				if(dbPw.contentEquals(m_pw)) {
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD;
				}
			} else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public MemberDto getMember(String m_id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where id=?";
		MemberDto dto = null;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, m_id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MemberDto();
				dto.setM_name(set.getString("m_name"));
				dto.setM_Id(set.getString("m_id"));
				dto.setM_pw(set.getString("m_pw"));
				dto.setM_email(set.getString("m_email"));
				dto.setM_mobile(set.getString("m_mobile"));
				dto.setM_authority(set.getString("m_authority"));
				dto.setM_date(set.getString("m_date"));
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	public int updateMember(MemberDto dto) {
		int ri = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update TB_MEMBER set m_name=?, m_id=?, m_pw=?, M_email=?, M_mobile=?, M_authority=?, M_date=?");
		
			pstmt.setString(1, dto.getM_name());
			pstmt.setString(2, dto.getM_Id());
			pstmt.setString(3, dto.getM_pw());
			pstmt.setString(4, dto.getM_email());
			pstmt.setString(5, dto.getM_mobile());
			pstmt.setString(6, dto.getM_authority());
			pstmt.setString(7, dto.getM_date());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return ri;
	}
}
