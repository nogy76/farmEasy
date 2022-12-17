package com.farmeasy.model.board;

public class BoardReplyDto {
	//멤버 변수 선언
	private int reply_id;			//댓글 번호
	private int board_id;			//게시판 번호
	private String user_idName;		//회원 ID
	private int reply_bundle;		//댓글 큰 묶음
	private int reply_order;		//댓글 순서
	private int reply_level;		//댓글 층계
	private String reply_content;	//댓글 내용
	private String insert_date;		//댓글 작성 날짜
	private String update_date;		//댓글 수정 날짜
	
	
	public BoardReplyDto() {
		
	}
	
	public BoardReplyDto(int reply_id, String user_idName, int reply_bundle, int reply_order, int reply_level, String reply_content, String insert_date, String update_date) {
		this.reply_id = reply_id; 
		this.user_idName = user_idName;
		this.reply_bundle = reply_bundle;
		this.reply_order = reply_order;
		this.reply_level = reply_level;
		this.reply_content = reply_content;
		this.insert_date = insert_date;
		this.update_date = update_date;
	}

	
	public int getReply_id() {
		return reply_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public String getUser_idName() {
		return user_idName;
	}

	public int getReply_bundle() {
		return reply_bundle;
	}

	public int getReply_order() {
		return reply_order;
	}

	public int getReply_level() {
		return reply_level;
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

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public void setUser_idName(String user_idName) {
		this.user_idName = user_idName;
	}

	public void setReply_bundle(int reply_bundle) {
		this.reply_bundle = reply_bundle;
	}

	public void setReply_order(int reply_order) {
		this.reply_order = reply_order;
	}

	public void setReply_level(int reply_level) {
		this.reply_level = reply_level;
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
	
}
