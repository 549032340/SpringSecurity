package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * spring security控制器
 * @author Administrator
 *
 */

import com.example.domain.User;
import com.example.service.MethodSecurityService;
@Controller
public class DemoController {
	
@Autowired
MethodSecurityService methodSecurityService;


	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String hello(Model model) {
		@SuppressWarnings("unused")
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		model.addAttribute("list",methodSecurityService.userForm());
		return "hello";
	}
	
	@GetMapping("/show")
	public String show(Model model) {
		@SuppressWarnings("unused")
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		model.addAttribute("list",methodSecurityService.showList());
		return "hello";
	}
	
	@GetMapping("/delete")
	public String deletet(User user) {
		methodSecurityService.delete(user.getLoginName());
		return "redirect:show";

	}
	
}