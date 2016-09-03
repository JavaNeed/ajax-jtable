package com.programmingfree.dao;

import java.util.List;

import com.programmingfree.model.User;

public interface UserDAO {
	//Create
	public void save(User user);
	//Read
	public User getById(int id);
	//Update
	public void update(User user);
	//Delete
	public void deleteById(int id);
	//Get All
	public List<User> getAll();
	
	public int getUserCount();
}
