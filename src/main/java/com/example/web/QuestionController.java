package com.example.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@Controller
@RequestMapping()
public class QuestionController {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("/question")
	public String qnacreate(Question question){
		
		questionRepository.save(question);
		log.debug(question+"");
		return "redirect:/questionlist";	
	}
	
	@GetMapping("/questionlist")
	public String questionlist(Model model){
		model.addAttribute("questions", questionRepository.findAll());
	return "/index";
	}
	
	@GetMapping("/qna/form")
	public String qnapage(HttpSession session, Model model){
		User loginUser = (User)session.getAttribute(HttpSessionUtils.LOGIN_USER);
		
		if(loginUser == null){
			return "/user/login";
		}
		model.addAttribute("userId", loginUser.getUserId());
	return "qna/form";
	}
	
	@GetMapping("/question/{id}")
	public String qnaDetailPage(Model model,  @PathVariable long id){
		model.addAttribute("question",questionRepository.findOne(id));
	return "qna/show";
	}
	
	@GetMapping("/question/{id}/form")
	public String updateForm(Question question, @PathVariable long id, HttpSession session, Model model){
		
		Question dbQuestion = questionRepository.findOne(id);
		if(!checkOwner(dbQuestion.getWriter(), session)){
			return "/user/login";
		}
		model.addAttribute("question", dbQuestion);
				 
		return "/qna/updateForm";	
	}
	
	@PutMapping("/question/{id}/update")
	public String update(Question question, @PathVariable long id, HttpSession session){
		
		Question dbQuestion = questionRepository.findOne(id); 
		dbQuestion.update(question);
		questionRepository.save(dbQuestion);	//id값이 db에 없으면 insert로 되버린다.
										//근데 없어도 알아서 업데이트된다 orm메모리에서 알아서 판단해서 처리하기 때문 
		
		return "redirect:/questionlist";	
	}
	
	@DeleteMapping("/questions/{id}/delete")
	public String delete(Question question, @PathVariable long id, HttpSession session){
		
		Question dbQuestion = questionRepository.findOne(id);
		if(!checkOwner(dbQuestion.getWriter(), session)){
			return "/user/login";
		}
		questionRepository.delete(dbQuestion);	//id값이 db에 없으면 insert로 되버린다.
										//근데 없어도 알아서 업데이트된다 orm메모리에서 알아서 판단해서 처리하기 때문 
		
		return "redirect:/questionlist";	
	}
	
	private boolean checkOwner(String writer, HttpSession session) {
		Object temp = session.getAttribute(HttpSessionUtils.LOGIN_USER);
		if(temp == null){
			return false;
		}
		User loginUser =  HttpSessionUtils.getUserFromSession(session);
		if(!loginUser.checkUserId(writer)){
			return false;
		}
		return true;
	}
	
	
}
