package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/index")	
	public String index(){
		return "redirect:/";	
	}
//	
//	@GetMapping("/")	
//	public String home(){
//		return "redirect:/question/questions";	
//	}
}
