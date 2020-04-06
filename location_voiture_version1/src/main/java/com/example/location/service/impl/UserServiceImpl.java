package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.location.bean.User;
import com.example.location.dao.UserRepository;
import com.example.location.service.facade.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Lazy
	@Autowired
	private UserRepository userRepository;

	@Override
	public int modify(User user) {
		User foundedUser = findByEmail(user.getEmail());
		if (foundedUser == null)
			return -1;
		else {
			user.setId(foundedUser.getId());
			userRepository.save(user);
			return 1;
		}
	}

	@Override
	public int remove(User user) {
		User foundedUser = findByEmail(user.getEmail());
		if (foundedUser == null)
			return -1;
		else {
			userRepository.delete(user);
			return 1;
		}
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByNumberPhone(String numberPhone) {
		return findByNumberPhone(numberPhone);
	}

	@Override
	public int login(User user) {
		System.out.println(user);
		User foundedUser = findByEmail(user.getEmail());
		if (foundedUser == null) {
			return -1;
		} else if (!foundedUser.getPassword().equals(user.getPassword())) {
			return -2;
		} else if (foundedUser.getType().equals("user")) {
			return 1;
		} else if(foundedUser.getType().equals("admin")) {
			return 2;
		}else {
			return 3;
		}
	}

	@Override
	public int register(User user) {
		User foundedUser = findByEmail(user.getEmail());
		if(foundedUser != null) {
			return -1;
		}else {
			userRepository.save(user);
			return 1;
		}
		
	}

}
