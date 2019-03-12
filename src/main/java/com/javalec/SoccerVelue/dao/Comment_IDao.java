package com.javalec.SoccerVelue.dao;

import java.util.ArrayList;

import com.javalec.SoccerVelue.dto.Comment_Dto;

public interface Comment_IDao {
	public ArrayList<Comment_Dto> commentlist(String bId);
	public ArrayList<Comment_Dto> commentlist_more(String comment_group);
	public int count(int bno);
	public String user_name(String user);
	public int comment_more_write(String bId,String group,String User,String content);
	public void create_group(String bId,String User,String content);
	public int comment_group_delete(String User,String comment_num);
	public int comment_step_delete(String User,String comment_num);
	public int comment_update(String comment_group,String comment_text);
	public int comment_more_update(String comment_group,String comment_text);
}
