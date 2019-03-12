package com.javalec.SoccerVelue.dto;

public class Board_Dto {//데이터베이스에있는걸 객체로변환
	int bId;
	String bName;
	String bTitle;
	String bContent;
	String bDate;
	int bHit;
	int BOARD_COMMENT;
	
	public Board_Dto() {
		
	}
	
	public Board_Dto(int bId, String bName,String bTitle,String bContent, String bDate, int bHit) {
		this.bId=bId;
		this.bName=bName;
		this.bTitle=bTitle;
		this.bDate=bDate;
		this.bHit=bHit;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public int getBOARD_COMMENT() {
		return BOARD_COMMENT;
	}

	public void setBOARD_COMMENT(int bOARD_COMMENT) {
		BOARD_COMMENT = bOARD_COMMENT;
	}

}
