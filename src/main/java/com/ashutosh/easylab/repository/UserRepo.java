package com.ashutosh.easylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashutosh.easylab.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
}
