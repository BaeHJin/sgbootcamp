package com.example.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Question;

@Controller
@RequestMapping()
public class QuestionController {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	private List<Question> questions = new ArrayList<>();
	
	@PostMapping("/question")
	public String qnacreate(Question question){
		Date date = new Date();
		question.setDate(date);
		questions.add(question);
		log.debug(questions.size()+" "+questions.toString());
		return "redirect:/questionlist";	
	}
	
	@GetMapping("/questionlist")
	public String questionlist(Model model){
		model.addAttribute("questions", questions);
	return "index";
	}
	
	@GetMapping("/qna/form")
	public String qnapage(){
	return "qna/form";
	}
}
