package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@PostMapping("/user/create")
	public String create(String userId, String password, String name, String email){
		System.out.println(userId);
		System.out.println(password);
		System.out.println(name);
		System.out.println(email);
		return "redirect:/";	
	}
	
}
