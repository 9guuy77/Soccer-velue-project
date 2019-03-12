package com.javalec.SoccerVelue.dao;

import java.util.ArrayList;

import com.javalec.SoccerVelue.dto.Board_Dto;
public interface Board_IDao {
	
	public ArrayList<Board_Dto> pagelist(int pagenum,int contentnum);
	public ArrayList<Board_Dto> pagelist_find(String find_text,int pagenum,int contentnum);
	public int pagecount();
	public int pagecount_find(String find);
	public int writeDao(String BName, String BTitle,String BContent);
	public int writeDao_move(String BName);
	public String UserId(String bId);
	public void updateDao(String bId,String BTitle,String BContent);
	public Board_Dto viewDao(String strID);
	public void deleteDao(String bId);
	public void upHit(String bId);
	public Board_Dto contentView(String bId);
}
