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


	/**
	 * 跳转到登录页面
	* @Title: login   
	* @Description: TODO  
	* @param @return   
	* @return String   
	* @throws
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 登录成功跳转页面
	* @Title: hello   
	* @Description: TODO  
	* @param @param model
	* @param @return   
	* @return String   
	* @throws
	 */
	@GetMapping("/")
	public String hello(Model model) {
		@SuppressWarnings("unused")
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		model.addAttribute("list",methodSecurityService.userForm());
		return "hello";
	}
	
	/**
	 * 显示所有信息
	* @Title: show   
	* @Description: TODO  
	* @param @param model
	* @param @return   
	* @return String   
	* @throws
	 */
	@GetMapping("/show")
	public String show(Model model) {
		@SuppressWarnings("unused")
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		model.addAttribute("list",methodSecurityService.showList());
		return "hello";
	}
	
	/**
	 * 根据用户名删除信息
	* @Title: deletet   
	* @Description: TODO  
	* @param @param user
	* @param @return   
	* @return String   
	* @throws
	 */
	@GetMapping("/delete")
	public String deletet(User user) {
		methodSecurityService.delete(user.getLoginName());
		return "redirect:show";
	}
	
}