package com.example.springboot.dao;
import com.example.springboot.dto.User;
public interface UserRepository {
	  Iterable<User> findAll();
	  User findOne(Long id);
	  User save(User user);
	  User update(User user);
	void delete(Long id);

}
