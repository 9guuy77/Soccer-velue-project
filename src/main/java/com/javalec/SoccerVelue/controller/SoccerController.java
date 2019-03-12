package com.javalec.SoccerVelue.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SoccerController {
	@Autowired
	private SqlSession sqlSession;
	
	@ResponseBody
	@RequestMapping("/board/userID")
	public String PostIdCheck(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    return user.getUsername();
	}
}
