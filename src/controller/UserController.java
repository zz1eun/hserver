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
	
	public String requestSetUserNickname(String nickname, String email, String gender, String profile) throws Exception {
		
		return userDao.insertNickname(nickname, email, gender, profile);
		
	}
	
}
