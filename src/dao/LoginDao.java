package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectDB;
import util.SHA256;

public class LoginDao {
	SHA256 sha = SHA256.getInsatnce();
	
	public String login(String email, String pw) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "fail";
		
		String shaPass = sha.getSha256(pw.getBytes()); //pw 암호화
		
		try {
			
			String sql = "select * from user where user_email = ? and user_password = ?";
			ConnectDB connectDB = new ConnectDB();
			pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, shaPass);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = "success";
			}
			
		} catch (SQLException e) {
			System.out.println("제품 목록 보기에서 예외 발생");
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return result;
		
	}
	
	public String userInfoCheck(String email, String nickname) {
		
		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null, rs1 = null;
		String result = "notExist";
		
		try {
			
			String sql = "select * from user where user_email = ?";
			ConnectDB connectDB = new ConnectDB();
			pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = "email";
			}
			
			sql = "select * from user where user_nickname = ?";
			pstmt1 = connectDB.getConnection().prepareStatement(sql);
			pstmt1.setString(1, nickname);
			rs1 = pstmt1.executeQuery();
			
			if(rs1.next()) {
				result = "nickname";
			}
			
		} catch (SQLException e) {
			System.out.println("회원가입 중복 체크에서 예외 발생");
			e.printStackTrace();
		} finally {
			if(rs1 != null) {
				try { rs1.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt1 != null) {
				try { pstmt1.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return result;
		
	}
	
	public String userNickname(String email) {
		
		PreparedStatement pstmt = null, pstmt1 = null;
		ResultSet rs = null, rs1 = null;
		String result = "null";

		try {
			
			String sql = "select * from user where user_email = ?";
			ConnectDB connectDB = new ConnectDB();
			pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getString("user_nickname");
				
			}
//			} else {
//				
//				sql = "insert into user(user_email, user_gender) values(?,?)";
//				pstmt1 = connectDB.getConnection().prepareStatement(sql);
//				pstmt1.setString(1, email);
//				pstmt1.setString(2, gender);
//				pstmt1.executeUpdate();				
//				
//			}
			
			
		} catch (SQLException e) {
			System.out.println("닉네임 조회에서 예외 발생");
			e.printStackTrace();
		} finally {
			if(rs1 != null) {
				try { rs1.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt1 != null) {
				try { pstmt1.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return result;
		
	}
	
}
	
