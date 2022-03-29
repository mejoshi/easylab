package com.ashutosh.easylab.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashutosh.easylab.entity.User;
import com.ashutosh.easylab.repository.UserRepo;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
	UserRepo userRepo;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User findById(Long id) {
		return null;
	}

	@Override
	public String save(User user) {
		User user_saved = userRepo.save(user);
		return user_saved.getEmail() + " is registred!!";
	}

	@Override
	public String delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
