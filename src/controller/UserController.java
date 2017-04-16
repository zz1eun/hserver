package controller;

import dao.UserDao;
import domain.User;

public class UserController {
	private UserDao userDao;
	
	public UserController() {
		userDao = new UserDao();
	}

	public String requestInsertUser(User newUser) throws Exception {
		
		return userDao.insert(newUser);
		
	}
}
