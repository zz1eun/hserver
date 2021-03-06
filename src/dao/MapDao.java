package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import domain.HG_Facility;
import util.ConnectDB;

public class MapDao {
	private static MapDao instance = new MapDao();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	String returns = "";

	public static MapDao getInstance() {
		return instance;
	}

	public String mapPlace_fac_id_one(int id){
		
		HG_Facility hg = new HG_Facility();
		
		String result ="";
		Gson gson = new Gson();
		try {

			ConnectDB connectDB = new ConnectDB();
			
		    System.out.println("접속에 성공!");
		    
		    sql = "select * from HG_Facility where FAC_id= ?";
		    pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				
				hg.setFAC_id(rs.getInt("FAC_id"));
				hg.setFAC_name(rs.getString("FAC_name"));
				hg.setCategory_no(rs.getInt("Category_no"));
				hg.setFAC_Address(rs.getString("Category_no"));
				hg.setFAC_phone(rs.getString("FAC_phone"));
				hg.setFAC_Proifle(rs.getString("FAC_Profile"));
				hg.setFAC_LOC_Lati(rs.getDouble("FAC_LOC_Lati"));
				hg.setFAC_LOC_Longi(rs.getDouble("FAC_LOC_Longi"));
				hg.setFAC_Place_no(rs.getInt("Fac_Place_no"));
			}
		
			result += gson.toJson(hg).toString();
		       
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		
		
		return result;
	}

	//지구 리스트 뽑아낸다.
	public String mapPlace_list(int id)
	{
		List<HG_Facility> list = new ArrayList<HG_Facility>();
		Gson gson = new Gson();
		String change_json="{\"fac\":";
		try {

			ConnectDB connectDB = new ConnectDB();
			
		 
		    System.out.println("접속에 성공!");
		    if(id == 0)
		    {
		    	sql = "select * from HG_Facility";
		    	pstmt = connectDB.getConnection().prepareStatement(sql);
		    }
		    else
		    {
			    //일단 잠실로되어있음. 
				sql = "select * from HG_Facility where Category_no= ?";
			    
				pstmt = connectDB.getConnection().prepareStatement(sql);
				pstmt.setInt(1, id);
		    }
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				HG_Facility  hg = new HG_Facility();
				hg.setFAC_id(rs.getInt("FAC_id"));
				hg.setFAC_name(rs.getString("FAC_name"));
				hg.setCategory_no(rs.getInt("Category_no"));
				hg.setFAC_Address(rs.getString("FAC_Address"));
				hg.setFAC_phone(rs.getString("FAC_phone"));
				hg.setFAC_Proifle(rs.getString("FAC_Profile"));
				hg.setFAC_LOC_Lati(rs.getDouble("FAC_LOC_Lati"));
				hg.setFAC_LOC_Longi(rs.getDouble("FAC_LOC_Longi"));
				hg.setFAC_Place_no(rs.getInt("Fac_Place_no"));
				list.add(hg);
			}
		
			change_json += gson.toJson(list);
			change_json += "}";
		       
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		
		
		return change_json;
	}

}
