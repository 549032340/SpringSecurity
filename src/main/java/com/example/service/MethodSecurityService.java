package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.domain.User;

/**
 * 
 * @ClassName: MethodSecurityService
 * @Description: TODO
 * @author zs
 * @date 2018年6月4日 下午2:53:38
 *
 */
@Service

public class MethodSecurityService {
	@Secured("ROLE_ADMIN")
	public String roleAdmin() {
		String role = "我是一个admin";
		return role;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN' )and #num>5")
	public void test1(int num) {
		System.out.println("拥有权限并且数字大于5");
	}

	@PostAuthorize("returnObjict == 'abc'")
	public String test2() {
		System.out.println("执行方法并且返回一个字符对象");
		return "abcd";
	}

	@PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_USER'})")
	@PostFilter("hasRole('ROLE_ADMIN') || " + "filterObject.username == principal.username")
	public List<User> test3() {
		User u1 = new User("u1", "男", 19, "123");
		User u2 = new User("u2", "女", 19, "123");
		User u3 = new User("u3", "男", 19, "123");
		List<User> list = new ArrayList<>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		return list;
	}

	@PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_USER'})")
	@PostFilter("hasRole('ROLE_ADMIN') || " + "filterObject.username == principal.username")
	public void test4(List<User> users) {
		for (User user : users) {
			System.out.println(user.getLoginName());
		}
	}

	public List<User> list = new ArrayList<>();

	public List<User> showList() {
		return list;
	}

	/**
	 * 根据权限显示信息
	* @Title: userForm   
	* @Description: TODO  
	* @param @return   
	* @return List<User>   
	* @throws
	 */
	@PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_USER'})")
	@PostFilter("hasRole('ROLE_ADMIN') || " + "filterObject.loginName == principal.username")
	public List<User> userForm() {
		User u1 = new User("admin", "123", 23, "男");
		User u2 = new User("user", "123", 23, "男");
		User u3 = new User("易群", "123", 23, "男");
		list.add(u1);
		list.add(u2);
		list.add(u3);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getLoginName().equals(list.get(i).getLoginName())) {
					list.remove(j);
				}
			}
		}
		return list;
	}

	public List<User> delete(String del) {
		for (User user : list) {
			if (user.getLoginName().equals(del)) {
				list.remove(user);
				break;
			}
		}
		return list;
	}
}