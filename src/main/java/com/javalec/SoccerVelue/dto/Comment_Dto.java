package com.javalec.SoccerVelue.dto;

public class Comment_Dto {
	int BOARD_NO;
	int COMMENT_GROUP;
	int COMMENT_STEP;
	int GROUP_COUNT;
	String USER_ID;
	String COMMENT_TEXT;
	String COMMENT_DATE;

	public Comment_Dto() {
		
	}
	
	public Comment_Dto(int BOARD_NO, int COMMENT_GROUP, int COMMENT_STEP, String USER_ID, String COMMENT_TEXT, String COMMENT_DATE,int GROUP_COUNT ) {
		this.BOARD_NO=BOARD_NO;
		this.COMMENT_GROUP=COMMENT_GROUP;
		this.COMMENT_STEP=COMMENT_STEP;
		this.USER_ID=USER_ID;
		this.COMMENT_TEXT=COMMENT_TEXT;
		this.COMMENT_DATE=COMMENT_DATE;
		this.GROUP_COUNT=GROUP_COUNT;
	}

	public int getGROUP_COUNT() {
		return GROUP_COUNT;
	}

	public void setGROUP_COUNT(int gROUP_COUNT) {
		GROUP_COUNT = gROUP_COUNT-1;
	}

	public int getBOARD_NO() {
		return BOARD_NO;
	}

	public void setBOARD_NO(int bOARD_NO) {
		BOARD_NO = bOARD_NO;
	}

	public int getCOMMENT_GROUP() {
		return COMMENT_GROUP;
	}

	public void setCOMMENT_GROUP(int cOMMENT_GROUP) {
		COMMENT_GROUP = cOMMENT_GROUP;
	}

	public int getCOMMENT_STEP() {
		return COMMENT_STEP;
	}

	public void setCOMMENT_STEP(int cOMMENT_STEP) {
		COMMENT_STEP = cOMMENT_STEP;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getCOMMENT_TEXT() {
		return COMMENT_TEXT;
	}

	public void setCOMMENT_TEXT(String cOMMENT_TEXT) {
		COMMENT_TEXT = cOMMENT_TEXT;
	}

	public String getCOMMENT_DATE() {
		return COMMENT_DATE;
	}

	public void setCOMMENT_DATE(String cOMMENT_DATE) {
		COMMENT_DATE = cOMMENT_DATE;
	}
	
}
