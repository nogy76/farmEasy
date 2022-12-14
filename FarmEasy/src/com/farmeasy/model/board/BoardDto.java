package com.farmeasy.model.board;

public class BoardDto {
	//멤버 변수 선언
	private int board_id;			//게시판 번호
//	private int user_id;			//회원 번호
	private String user_idName;
	
	private String fe_type;			//회원 유형('N'ormal, 'M'anager)
	private String board_title;		//글 제목
	private String board_content;	//글 내용
	private String insert_date;		//글 작성 날짜
	private String update_date;		//글 수정 날짜
	private int board_hits;			//조회수

	public BoardDto() {
		
	}
	
	public BoardDto(int board_id, String user_idName, String fe_type, String board_title, String board_content, String insert_date, String update_date, int board_hits) {
		this.board_id = board_id;
//		this.user_id = user_id;
		this.user_idName = user_idName;
		
		this.fe_type = fe_type;
		this.board_title = board_title;
		this.board_content = board_content;
		this.insert_date = insert_date;
		this.update_date = update_date;
		this.board_hits = board_hits;
	}

	public int getBoard_id() {
		return board_id;
	}
	
	public String getFe_type() {
		return fe_type;
	}

	public String getBoard_title() {
		return board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public String getUser_idName() {
		return user_idName;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

//	public void setUser_id(int user_id) {
//		this.user_id = user_id;
//	}

	public void setFe_type(String fe_type) {
		this.fe_type = fe_type;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public void setUser_idName(String user_idName) {
		this.user_idName = user_idName;
	}
	
	
	
}
