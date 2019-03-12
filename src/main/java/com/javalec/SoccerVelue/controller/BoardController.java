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
		}//������ ���� �Է� �޴´�.
		if(request.getParameter("contentnum")==null) {
	    contentnum = "10";
		}
		else{
			contentnum = request.getParameter("contentnum");
		}//�� �������� � ������
	    int cpagenum = Integer.parseInt(pagenum);//int ������ �� �� ���������� ������ �� ����ȯ
	    int ccontentnum = Integer.parseInt(contentnum);//int ������ �� �������� � ������ �� ����ȯ
		Board_IDao dao = sqlSession.getMapper(Board_IDao.class);
	    
	/*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
		if(find_text!=null)pagemaker.setTotalcount(dao.pagecount_find(find_text));//��ü �Խñ� ���� �����Ѵ�
		else pagemaker.setTotalcount(dao.pagecount());
		
	    pagemaker.setPagenum(cpagenum-1);//���� �������� ������ ��ü�� �ٽ� �������ش�//��� ���������� PageMaker�� �����Ѵ�
	    pagemaker.setContentnum(ccontentnum);//�� �������� ��� �������� �����Ѵ�
	    pagemaker.setCurrentblock(cpagenum);//���� ����������� ������� ���� ������ ��ȣ�� ���ؼ� �����Ѵ�
	    pagemaker.setLastblock(pagemaker.getTotalcount());//������ ��� ��ȣ�� ��ü �Խñ� ���� ���ؼ� ���Ѵ�
	/*---------������ ��ü�� ���ο� ���� �ٽ� �������ִ� �κ�------------------*/
	    
	    pagemaker.prevnext(cpagenum);//���� ������ ��ȣ�� ȭ��ǥ ��Ÿ���� �����Ѵ�
	    pagemaker.setStartPage(pagemaker.getCurrentblock());//���������� ��ȣ�� ���� ������ ������� ���Ѵ�
	    pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock());
	    //���� ��� ��ȣ�� ������ ��� ��ȣ�� ������ �����ϰ� ������ ����� ������ ��ȣ�� �����Ѵ�
	    //���۷� �� �������� � ������ ,��� ������ ���� ����//����.xml ���� ����ϱ� ���ؼ� ���ϱ� 10�� �Ѵ�

	    if(find_text!=null)
	    	model.addAttribute("list", dao.pagelist_find(find_text,pagemaker.getPagenum()*10, pagemaker.getContentnum()));//sql�� ���� ����Ʈ�� .jsp�������� ����
	    else
	    	model.addAttribute("list", dao.pagelist(pagemaker.getPagenum()*10, pagemaker.getContentnum()));//sql�� ���� ����Ʈ�� .jsp�������� ����
	    
	    model.addAttribute("page", pagemaker);//������ ��ȣ ��ü .jsp�������� ����
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
