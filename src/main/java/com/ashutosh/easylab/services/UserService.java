package com.ashutosh.easylab.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ashutosh.easylab.entity.User;
import com.ashutosh.easylab.repository.UserRepo;

public interface UserService{
		
	List<User> findAll();
	User findById(Long id);
	String save(User user);
	String delete(Long id);
			
	
}
