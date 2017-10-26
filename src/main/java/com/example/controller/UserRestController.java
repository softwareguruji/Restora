package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/admin/listUserRest", method = RequestMethod.GET)
	public Page<User> list(Pageable pageable){
		Page<User> pageUser = userService.listAllByPage(pageable);
		return pageUser;
	}

}
