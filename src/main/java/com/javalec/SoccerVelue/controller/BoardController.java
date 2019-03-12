package com.javalec.SoccerVelue.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.SoccerVelue.dao.Board_IDao;
import com.javalec.SoccerVelue.dao.Comment_IDao;
import com.javalec.SoccerVelue.util.PageMaker;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/board/list")
	public String list(HttpServletRequest request,Model model) {
		String find_text=request.getParameter("text_find");
		
		PageMaker pagemaker = new PageMaker();
		String pagenum;
		String contentnum;
		if(request.getParameter("pagenum")==null) {
			pagenum="1";
		}
		else {
		pagenum = request.getParameter("pagenum");
		}//페이지 값을 입력 받는다.
		if(request.getParameter("contentnum")==null) {
	    contentnum = "10";
		}
		else{
			contentnum = request.getParameter("contentnum");
		}//한 페이지에 몇개 보일지
	    int cpagenum = Integer.parseInt(pagenum);//int 형으로 몇 번 페이지인지 페이지 값 형변환
	    int ccontentnum = Integer.parseInt(contentnum);//int 형으로 한 페이지에 몇개 보일지 값 형변환
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
	    
	/*---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------*/
		if(find_text!=null)pagemaker.setTotalcount(dao.pagecount_find(find_text));//전체 게시글 개수 지정한다
		else pagemaker.setTotalcount(dao.pagecount());
		
	    pagemaker.setPagenum(cpagenum-1);//현재 페이지를 페이지 객체에 다시 지정해준다//몇번 페이지인지 PageMaker에 세팅한다
	    pagemaker.setContentnum(ccontentnum);//한 페이지에 몇개씩 보여줄지 세팅한다
	    pagemaker.setCurrentblock(cpagenum);//현재 페이지블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
	    pagemaker.setLastblock(pagemaker.getTotalcount());//마지막 블록 번호를 전체 게시글 수를 통해서 정한다
	/*---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------*/
	    
	    pagemaker.prevnext(cpagenum);//현재 페이지 번호로 화살표 나타낼지 결정한다
	    pagemaker.setStartPage(pagemaker.getCurrentblock());//시작페이지 번호를 현재 페이지 블록으로 정한다
	    pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
	    //현재 블록 번호와 마지막 블록 번호를 보내서 대조하고 페이지 블록의 마지막 번호를 지정한다
	    //매퍼로 한 페이지에 몇개 보일지 ,몇번 페이지 인지 전달//매퍼.xml 에서 사용하기 위해서 곱하기 10을 한다

	    if(find_text!=null)
	    	model.addAttribute("list", dao.pagelist_find(find_text,pagemaker.getPagenum()*10, pagemaker.getContentnum()));//sql로 얻은 리스트를 .jsp페이지로 전달
	    else
	    	model.addAttribute("list", dao.pagelist(pagemaker.getPagenum()*10, pagemaker.getContentnum()));//sql로 얻은 리스트를 .jsp페이지로 전달
	    
	    model.addAttribute("page", pagemaker);//페이지 번호 객체 .jsp페이지로 전달
	    model.addAttribute("text_find",find_text);
		return "board/list";
	}

	@RequestMapping("/board/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		return "board/write_view";
	}

	@RequestMapping("/board/update_view")
	public String update_view(HttpServletRequest request,Model model) {
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
		String bId=request.getParameter("bId");
		model.addAttribute("content_view",dao.contentView(bId));
		return "board/write_view";
	}

	@RequestMapping("/board/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dao.writeDao(user.getUsername(),request.getParameter("bTitle"),request.getParameter("contents"));
		int result=dao.writeDao_move(user.getUsername());
		return "redirect:content_view?bId="+Integer.toString(result);
	}
	

	@RequestMapping("/board/update")
	public String update(HttpServletRequest request, Model model) {
		System.out.println("update()");
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
		String bId=request.getParameter("bId");
		String result=dao.UserId(bId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(result.equals(user.getUsername())) {
		dao.updateDao(bId,request.getParameter("bTitle"),request.getParameter("contents"));}
		return "redirect:content_view?bId="+bId;
	}

	@RequestMapping("/board/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
		dao.deleteDao(request.getParameter("bId"));
		return "redirect:list";
	}
	

	@RequestMapping("/board/content_view")
	public String content_view(HttpServletRequest request,Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
		Comment_IDao comment_dao = sqlSession.getMapper(Comment_IDao.class);
		
		String bId=request.getParameter("bId");
		dao.upHit(bId);
		model.addAttribute("content_view",dao.contentView(bId));
		model.addAttribute("comment",comment_dao.commentlist(bId));
		model.addAttribute("login_user",user.getUsername());
		
		return "board/content_view";
	}

}
