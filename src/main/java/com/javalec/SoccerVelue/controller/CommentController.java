package com.javalec.SoccerVelue.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalec.SoccerVelue.dao.Comment_IDao;
import com.javalec.SoccerVelue.dto.Comment_Dto;

@Controller
public class CommentController {

	@Autowired
	private SqlSession sqlSession;

	@ResponseBody
	@RequestMapping(value="/board/comment_write", method = RequestMethod.POST)
	public String comment_write(@RequestBody Map<String, Object> params,HttpServletRequest request) {
		System.out.println("comment_write()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Comment_IDao comment_dao = sqlSession.getMapper(Comment_IDao.class);
		String bId=(String)params.get("bId");
		comment_dao.create_group(bId,user.getUsername(),(String)params.get("comment_content"));
		return bId;
	}

	@ResponseBody
	@RequestMapping(value="/board/comment_more_write", method = RequestMethod.POST)
	public int comment_more_write(@RequestBody Map<String, Object> params,HttpServletRequest request) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Comment_IDao comment_dao = sqlSession.getMapper(Comment_IDao.class);
		int result = comment_dao.comment_more_write((String)params.get("bId"),(String)params.get("comment_group"),user.getUsername(),(String)params.get("comment_more_text"));
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="board/comment", method = RequestMethod.GET)
	public List<Comment_Dto> comment_list(@RequestParam("bId") String bId) {
		Comment_IDao insert_dao = sqlSession.getMapper(Comment_IDao.class);
		List<Comment_Dto> list = insert_dao.commentlist(bId);
		return list;
	}
	@ResponseBody
	@RequestMapping(value="board/comment_more", method = RequestMethod.GET)
	public List<Comment_Dto> more_list(@RequestParam("comment_group") String comment_group) {
		Comment_IDao insert_dao = sqlSession.getMapper(Comment_IDao.class);
		List<Comment_Dto> list = insert_dao.commentlist_more(comment_group);
		return list;
	}
	@ResponseBody
	@RequestMapping(value="board/comment_delete", method = RequestMethod.POST)
	public int comment_delete(@RequestBody Map<String, Object> params,HttpServletRequest request) {
		System.out.println((String)params.get("comment_select")+(String)params.get("comment_num"));
		Comment_IDao insert_dao = sqlSession.getMapper(Comment_IDao.class);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if("COMMENT_GROUP".equals((String)params.get("comment_select"))) {
			insert_dao.comment_group_delete(user.getUsername(),(String)params.get("comment_num"));
		}
		else if("COMMENT_STEP".equals((String)params.get("comment_select"))) {
			insert_dao.comment_step_delete(user.getUsername(),(String)params.get("comment_num"));
		}
		return 1;
	}
	@ResponseBody
	@RequestMapping(value="board/comment_update", method = RequestMethod.POST)
	public int comment_update(@RequestBody Map<String, Object> params,HttpServletRequest request) {
		Comment_IDao update_dao = sqlSession.getMapper(Comment_IDao.class);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String jsp_name = update_dao.user_name((String)params.get("comment_group"));
		String con_name = user.getUsername();
		int result=0;
		if(jsp_name.equals(con_name)) {
		result=update_dao.comment_update((String)params.get("comment_group"),(String)params.get("comment_text"));
		}
		else {
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value="board/comment_more_update", method = RequestMethod.POST)
	public int comment_more_update(@RequestBody Map<String, Object> params,HttpServletRequest request) {
		Comment_IDao update_dao = sqlSession.getMapper(Comment_IDao.class);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String jsp_name = update_dao.user_name((String)params.get("comment_group"));
		String con_name = user.getUsername();
		int result=0;
		if(jsp_name.equals(con_name)) {
		result=update_dao.comment_more_update((String)params.get("comment_group"),(String)params.get("comment_text"));
		}
		else {
		}
		return result;
	}
}
