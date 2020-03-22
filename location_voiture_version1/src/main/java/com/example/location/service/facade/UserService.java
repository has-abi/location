package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.User;

public interface UserService {
	int modify(User client);
	int remove(User client);
	List<User> findAll();
	User findByEmail(String email);
	User findByNumberPhone(String numberPhone);
	int login(User user);
	int register(User user);
}
