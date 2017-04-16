package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.User;
import util.ConnectDB;
import util.SHA256;

public class UserDao {
	SHA256 sha = SHA256.getInsatnce();
	
	public String insert(User newUser) throws Exception {
		
		PreparedStatement pstmt = null;
		int rs;
		String result = "fail";
		
		try {
			
			String sql = "insert into user(user_email, user_password, user_gender, user_nickname, user_profile, user_role_id) values(?,?,?,?,?,?)";			
			ConnectDB connectDB = new ConnectDB();
			pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setString(1, newUser.getUser_email());
			pstmt.setString(2, sha.getSha256(newUser.getUser_password().getBytes()));
			pstmt.setString(3, newUser.getUser_gender());
			pstmt.setString(4, newUser.getUser_nickname());
			pstmt.setString(5, "null");
			pstmt.setInt(6, 2);
			rs = pstmt.executeUpdate();
			
			if(rs==1) {
				
				result = "success";
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("회원가입 예외 발생");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return result;
		
	}
}
