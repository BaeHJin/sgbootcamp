package com.example.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
