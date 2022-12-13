package com.farmeasy.model.board;

public class BoardFileDto {
	//멤버 변수 선언
	private int board_file_id;			//게시판 파일 번호
	private int board_id;				//게시판 번호
	private String board_file_name; 	//게시판 파일 이름
	private String board_file_realName; //게시판 파일 실제 이름
	private String board_file_byte;		//파일 바이트
	
	public BoardFileDto() {
		
	}
	
	public BoardFileDto(int board_id, String board_file_name, String board_file_realName, String board_file_byte) {
		this.board_id = board_id;
		this.board_file_name = board_file_name;
		this.board_file_realName = board_file_realName;
		this.board_file_byte = board_file_byte;
	}

	public int getBoard_file_id() {
		return board_file_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public String getBoard_file_name() {
		return board_file_name;
	}

	public String getBoard_file_realName() {
		return board_file_realName;
	}

	public String getBoard_file_byte() {
		return board_file_byte;
	}

	public void setBoard_file_id(int board_file_id) {
		this.board_file_id = board_file_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public void setBoard_file_name(String board_file_name) {
		this.board_file_name = board_file_name;
	}

	public void setBoard_file_realName(String board_file_realName) {
		this.board_file_realName = board_file_realName;
	}

	public void setBoard_file_byte(String board_file_byte) {
		this.board_file_byte = board_file_byte;
	}
	
	
	
}
