package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public String insertNickname(String nickname, String email, String gender, String profile) throws Exception {
		
		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null;
		String result = null;
		int rs1;
		
		try {
			
			String sql = "select * from user where user_nickname = ?";			
			ConnectDB connectDB = new ConnectDB();
			pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = "exist";
				
			} else {
				
				sql = "insert into user(user_email, user_password, user_gender, user_nickname, user_role_id, user_profile) values(?,?,?,?,?,?)";			
				pstmt1 = connectDB.getConnection().prepareStatement(sql);
				pstmt1.setString(1, email);
				pstmt1.setString(2, "null");
				pstmt1.setString(3, gender);
				pstmt1.setString(4, nickname);
				pstmt1.setInt(5, 2);
				pstmt1.setString(6, profile);
				rs1 = pstmt1.executeUpdate();
				result = nickname;
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("닉네임 설정 예외 발생");
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt1 != null) {
				try { pstmt1.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			
		}
		
		return result;
		
	}
	
}
