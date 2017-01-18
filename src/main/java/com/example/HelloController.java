package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello")		//templates 폴더에 있는 hello 파일로 연결된다.
	public String hello(String name, int age, Model model){
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		System.out.println(name);
		return "hello";	
	}
	
	
}
