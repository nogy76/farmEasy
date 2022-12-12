package com.farmeasy.model;

public class BoardReplyDto {
	//멤버 변수 선언
	private int reply_id;			//댓글 번호
	private int board_id;			//게시판 번호
	private int user_id;			//회원 번호
	private String reply_content;	//댓글 내용
	private String insert_date;		//댓글 작성 날짜
	private String update_date;		//댓글 수정 날짜
	private int parent_reply_id;	//부모 댓글 번호
	
	public BoardReplyDto() {
		
	}

	public int getReply_id() {
		return reply_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public int getParent_reply_id() {
		return parent_reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public void setParent_reply_id(int parent_reply_id) {
		this.parent_reply_id = parent_reply_id;
	}
	
	
	
}
