package com.example.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.utils.HttpSessionUtils;

@Controller
@RequestMapping("/user")	//대표url (아래 mapping의  중복제거)
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/loginCheck")
	public String login(String userId, String password, HttpSession session){
		User user = userRepository.findByUserId(userId);

		if(user == null){
			return "redirect:/user/login.html";
		}
		
		if(user.checkPassword(password)){
			session.setAttribute(HttpSessionUtils.LOGIN_USER, user);
			return "redirect:/user/list2";
		}
		return "redirect:/user/login.html";	
	}
	
	@PostMapping("/create")
	public String create(User user){
		log.debug(user+" ");
		userRepository.save(user);
		return "redirect:/user/list2";	
	}
	
	@PutMapping("/{id}/update")
	public String update(User user, @PathVariable long id, HttpSession session){
		
		checkOwner(id, session);
		
		User dbUser = userRepository.findOne(id); 
		dbUser.update(user);
		userRepository.save(dbUser);	//id값이 db에 없으면 insert로 되버린다.
										//근데 없어도 알아서 업데이트된다 orm메모리에서 알아서 판단해서 처리하기 때문 
		
		return "redirect:/user/list2";	
	}
	
	@GetMapping("/list2")
	public String list(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/form")
	public String userForm(){
		return "user/form";
	}
	
	@GetMapping("/login")
	public String useLogin(){
		return "user/login";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id, Model model, HttpSession session){
		checkOwner(id, session);
		model.addAttribute("user", userRepository.findOne(id));
		return "user/updateForm";
	}

	private void checkOwner(long id, HttpSession session) {
		Object temp = session.getAttribute(HttpSessionUtils.LOGIN_USER);
		if(temp == null){
			throw new IllegalStateException("로그인 하지 않은 사용자.");
		}
		User loginUser =  HttpSessionUtils.getUserFromSession(session);
		log.debug(id+"------------"+loginUser.getId());
		if(!loginUser.checkId(id)){
			throw new IllegalStateException("다른 사용자 정보를 수정할 수 없습니다.");
		}
	}
	
}
