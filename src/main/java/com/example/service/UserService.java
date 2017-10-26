package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> getAllUser();
	public Page<User> listAllByPage(Pageable pageable);

}
