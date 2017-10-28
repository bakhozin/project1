package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.Members;
import com.sist.vo.Notice;
import com.sist.vo.Shop;

public class ShopDAO {
	
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
	
	public int addProduct(Shop m){ //상품생성 
		Connection con=null;
		PreparedStatement ps = null;
		

		String sql = "INSERT INTO SHOP(IMAGE, CODE, NAME, SIZE, KIND, PRICE, CONTENT, MG, QNT, SELL, REGDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,SYSDATE)";
		con=getConn();

		int af=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getImage());
			ps.setString(2, m.getCode());
			ps.setString(3, m.getName());
			ps.setString(4, m.getSize());
			ps.setString(5, m.getKind());
			ps.setInt(6, m.getPrice());
			ps.setString(7, m.getContent());
			ps.setInt(8, m.getMg());
			ps.setInt(9, m.getQnt());
			ps.setInt(10, m.getSell());
			
			
			af = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("상품추가에 실패했습니다.");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("접속해제에 실패하였습니다.");
				e.printStackTrace();
			}
		}

		return af;
	}
	
	 public Shop getProduct(String code){  // CART---> SHOP 상품검색   
		                                      //상품 상세보기할때사용> 
	    	
	    	Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Shop s = null;

			String sql = "SELECT * FROM SHOP WHERE CODE=?";
			
			con = getConn();
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, code);
				rs=ps.executeQuery();		
				if(rs.next()){
					s = new Shop();
					s.setImage(rs.getString("image"));
					s.setPrice(rs.getInt("price"));
					s.setName(rs.getString("name"));
					s.setCode(rs.getString("code"));
					s.setSize(rs.getString("size"));
					s.setKind(rs.getString("kind"));
					s.setMg(rs.getInt("mg"));
					s.setContent(rs.getString("content"));
					s.setQnt(rs.getInt("qnt"));
					s.setSell(rs.getInt("sell"));
					s.setRegdate(rs.getString("regdate"));
					
				}

			} catch (SQLException e) {
				System.out.println("회원정보 조회중 오류 발생");
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
					con.close();
				} catch (SQLException e) {
					System.out.println("접속해제 실패");
					e.printStackTrace();
				}
			}
			return s;
		}
	 

	
	
	 
	 public ArrayList<Shop> listShop() { //상품 전체검색 
			
			Connection con;
			Statement st = null;
			ResultSet rs = null;
			ArrayList<Shop> mList = null;
			Shop s = null;
			String sql = "SELECT * FROM SHOP";
			con = getConn();
			try {
				st=con.createStatement();
				rs=st.executeQuery(sql);
				mList = new ArrayList<Shop>();
				while(rs.next()) {
					s=new Shop();
					s.setImage(rs.getString("image"));
					s.setPrice(rs.getInt("price"));
					s.setName(rs.getString("name"));
					s.setCode(rs.getString("code"));
					s.setSize(rs.getString("size"));
					s.setKind(rs.getString("kind"));
					s.setMg(rs.getInt("mg"));
					s.setContent(rs.getString("content"));
					s.setQnt(rs.getInt("qnt"));
					s.setSell(rs.getInt("sell"));
					s.setRegdate(rs.getString("regdate"));
					
					mList.add(s);
				}
			} catch (SQLException e) {
				System.out.println("상품전체조회 중 오류발생");
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					System.out.println("접속해제 실패");
					e.printStackTrace();
				}
			}
			return mList;
		}
	 

	 public ArrayList<Shop> listGoods(String goods) {
			Connection con;
			//Statement st = null;
			PreparedStatement ps =null;
			ResultSet rs = null;
			ArrayList<Shop> sList = null;
			
			String sql = "SELECT * FROM SHOP WHERE NAME LIKE ?";
			con=getConn();
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, "%"+goods+"%");

				rs=ps.executeQuery();
				
				sList = new ArrayList<>();
				while(rs.next()) {
					Shop s  = new Shop();
					s.setImage(rs.getString("image"));
					s.setPrice(rs.getInt("price"));
					s.setName(rs.getString("name"));
					s.setCode(rs.getString("code"));
					s.setSize(rs.getString("size"));
					s.setKind(rs.getString("kind"));
					s.setMg(rs.getInt("mg"));
					s.setContent(rs.getString("content"));
					s.setQnt(rs.getInt("qnt"));
					s.setSell(rs.getInt("sell"));
					s.setRegdate(rs.getString("regdate"));
					
					
					sList.add(s);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("상품 검색 중 오류발생");
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
			return sList;
		}
	 		

	public ArrayList<Shop> listKIND(String k) {
		Connection con;
		PreparedStatement ps =null;
		ResultSet rs = null;
		ArrayList<Shop> sList = null;
		
		String sql = "SELECT * FROM SHOP WHERE KIND=?";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, k);
			rs=ps.executeQuery();
			
			sList = new ArrayList<>();
			while(rs.next()) {
				Shop s  = new Shop();
				s.setImage(rs.getString("image"));
				s.setPrice(rs.getInt("price"));
				s.setName(rs.getString("name"));
				s.setCode(rs.getString("code"));
				s.setSize(rs.getString("size"));
				s.setKind(rs.getString("kind"));
				s.setMg(rs.getInt("mg"));
				s.setContent(rs.getString("content"));
				s.setQnt(rs.getInt("qnt"));
				s.setSell(rs.getInt("sell"));
				s.setRegdate(rs.getString("regdate"));
				
				
				sList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("상품 검색 중 오류발생");
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
		return sList;
	}

}
