package com.javalec.SoccerVelue.dao;


public interface User_IDao {

	public int idCheck(String userid);
	public void sign_up(String Email,String Nickname,String Password);
	
}
