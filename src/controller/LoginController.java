package controller;

import dao.LoginDao;

public class LoginController {

	private LoginDao loginDao;
	
	public LoginController() {
		loginDao = new LoginDao();
	}
	
	public String requestLogin(String email, String pw) throws Exception {

		return loginDao.login(email, pw);
		
	}
	
	public String requestFacebookLogin() throws Exception {
		
		return "null";
		
	}
	
	public String requestUserInfoCheck(String email, String nickname) throws Exception {
		
		return loginDao.userInfoCheck(email, nickname);
		
	}

}
