package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sist.vo.Preview;
import com.sist.vo.Review;

public class PreviewDAO {
	
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@211.238.142.148:1521:orcl";
		String user="DD";
		String pwd="123456";
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("접속 실패, 계정과 비밀번호를 확인하세요.");
			e.printStackTrace();
		}
		return con;
	}
	

	public ArrayList<Preview> listPreview() {
		Connection con;
		//Statement st = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		ArrayList<Preview> pList = null;
		
		String sql = "SELECT * FROM PREVIEW";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			pList = new ArrayList<>();
			while(rs.next()) {
				Preview nt = new Preview();
				nt.setSeq(rs.getString("seq"));
				nt.setTitle(rs.getString("title"));
				nt.setContent(rs.getString("content"));
				nt.setImage(rs.getString("image"));
				nt.setHit(rs.getInt("hit"));
				nt.setRegdate(rs.getString("regdate"));
				
				pList.add(nt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("프리뷰 전체조회 중 오류발생");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				//st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("접속해제실패");
				e.printStackTrace();
			}
		}
		return pList;
	}
	
	
	

}
