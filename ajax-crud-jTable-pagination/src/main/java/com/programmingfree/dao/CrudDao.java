package com.programmingfree.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.programmingfree.model.User;

public class CrudDao {

	private UserDAO userDAO = null;

	public CrudDao() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDAO = ctx.getBean("userDAOTemplate", UserDAO.class);
	}

	public int getUserCount() {
		int count = userDAO.getUserCount();
		return count;
	}

	public void addUser(User user) {
		userDAO.save(user);
	}

	public void deleteUser(int userId) {
		userDAO.deleteById(userId);
	}

	public void updateUser(User user) throws ParseException {
		userDAO.update(user);
	}

	public List<User> getAllUsers(int jtStartIndex, int jtPageSize) {
		List<User> users = userDAO.getAll();
		return users;
	}

	public User getUserById(int userId) {
		User user = userDAO.getById(userId);
		return user;
	}
}
