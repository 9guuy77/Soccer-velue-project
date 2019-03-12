package com.javalec.SoccerVelue.controller;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalec.SoccerVelue.dao.User_IDao;
import com.javalec.SoccerVelue.dto.User_Dto;

@Controller
public class LoginController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@ResponseBody
	@RequestMapping(value="/idCheck",method=RequestMethod.POST)
	public int PostIdCheck(@RequestBody String UserId){
		System.out.println("idCheck()");
		User_IDao dao= sqlSession.getMapper(User_IDao.class);
        int count = dao.idCheck(UserId);
	    return count;
	}

	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("home()");
		return "home";	
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		System.out.println("login()");
		return "login";	
	}

    @RequestMapping(value="/sign_up", method = RequestMethod.GET)
    public String sign_up(@ModelAttribute("User_Dto")User_Dto User_Dto) {
        return "sign_up";
    }
    
    @RequestMapping(value="/sign_up", method = RequestMethod.POST) 
    public String sign_up(@ModelAttribute("User_Dto")@Valid User_Dto User_Dto, BindingResult bindingResult){
        
        if(bindingResult.hasErrors()) {
            System.out.println("에러1");
            return "sign_up";
        }
        boolean check = User_Dto.isPwEqualToCheckPw();
        if(!check) {
            System.out.println("에러2");
            bindingResult.rejectValue("checkPw", "noMatch", "비밀번호를 확인해주세요.");
            return "sign_up";
        }
		User_IDao dao= sqlSession.getMapper(User_IDao.class);
    	dao.sign_up(User_Dto.getUserId(),User_Dto.getEmail(),User_Dto.getPassword());
        return "sign_up";
    }
}
