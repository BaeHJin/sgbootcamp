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

import com.example.domain.Answer;
import com.example.domain.AnswerRepository;
import com.example.domain.Question;
import com.example.domain.User;
import com.example.utils.HttpSessionUtils;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	private static final Logger log = LoggerFactory.getLogger(AnswerController.class);

	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String answerCreate(@PathVariable Long questionId, HttpSession session, String contents){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "/users/loginForm";
		}
		
		Answer answer = new Answer();
		answer.setWriter(HttpSessionUtils.getUserFromSession(session));
		answerRepository.save(answer);
		log.debug(answer.getContents()+"");
		return "redirect:/";	
	}
	
	@GetMapping("/answer/")
	public String answerList(Model model){
		model.addAttribute("questions", answerRepository.findAll());
	return "/index";
	}
	
	@GetMapping("/answer/form")
	public String answerPage(HttpSession session, Model model){
		User loginUser = (User)session.getAttribute(HttpSessionUtils.LOGIN_USER);
		
		if(loginUser == null){
			return "/user/login";
		}
		model.addAttribute("userId", loginUser.getUserId());
	return "qna/form";
	}
	
	@GetMapping("/answer/{id}")
	public String answerDetailPage(Model model,  @PathVariable long id){
		model.addAttribute("question",answerRepository.findOne(id));
	return "qna/show";
	}
	
	@GetMapping("/answer/{id}/form")
	public String updateForm(Answer answer, @PathVariable long id, HttpSession session, Model model){
		
		Answer dbAnswer = answerRepository.findOne(id);
		if(!checkOwner(dbAnswer.getWriter(), session)){
			return "/user/login";
		}
		model.addAttribute("answer", dbAnswer);
				 
		return "/qna/updateForm";	
	}
	
	@PutMapping("/answer/{id}/update")
	public String update(Answer answer, @PathVariable long id, HttpSession session){
		
		Answer dbAnswer = answerRepository.findOne(id); 
		dbAnswer.update(answer);
		answerRepository.save(dbAnswer);	//id값이 db에 없으면 insert로 되버린다.
										//근데 없어도 알아서 업데이트된다 orm메모리에서 알아서 판단해서 처리하기 때문 
		
		return "redirect:/";	
	}
	
	@DeleteMapping("/answer/{id}/delete")
	public String delete(Question question, @PathVariable long id, HttpSession session){
		
		Answer dbAnswer = answerRepository.findOne(id);
		if(!checkOwner(dbAnswer.getWriter(), session)){
			return "/user/login";
		}
		answerRepository.delete(dbAnswer);	//id값이 db에 없으면 insert로 되버린다.
										//근데 없어도 알아서 업데이트된다 orm메모리에서 알아서 판단해서 처리하기 때문 
		
		return "redirect:/";	
	}
	
	private boolean checkOwner(User writer, HttpSession session) {
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
