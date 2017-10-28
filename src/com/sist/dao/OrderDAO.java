package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sist.vo.Cart;
import com.sist.vo.Order;

public class OrderDAO {
	
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
	
	
	public Order getOrder(String mid){  // 내 주문내역 검색    

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order o = null;

		String sql = "SELECT * FROM ORDERINFO WHERE MID=? ";

		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();		

			if(rs.next()){			
				o = new Order();			
				
//				c.setSeq(rs.getString("seq"));  디폴트값 0으로주자 
				o.setShipping(rs.getInt("shipping"));
				o.setOrderNumber(rs.getString("orderNumber"));
				o.setTotal(rs.getInt("total"));
				o.setGoods(rs.getString("goods"));
				o.setLocations(rs.getString("locations"));
				o.setMid(rs.getString("mid"));
				o.setName(rs.getString("name"));
				o.setPhone(rs.getString("phone"));
				o.setCode(rs.getString("code"));
				o.setRegdate(rs.getString("regdate"));
				
			}

		} catch (SQLException e) {
			System.out.println("주문조회중 오류 발생");
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
		return o;
	}
	
	
	public int addOrder(Order o){ //주문내역 생성 
		Connection con=null;
		PreparedStatement ps = null;
		

		String sql = "INSERT INTO ORDERINFO(SHIPPING, ORDERNUMBER, TOTAL, GOODS, LOCATIONS, MID, NAME, PHONE, CODE,REGDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		con=getConn();

		int af=0;
		try {
			ps = con.prepareStatement(sql);
						
			
			ps.setInt(1, o.getShipping());
			ps.setString(2, o.getOrderNumber());
			ps.setInt(3, o.getTotal());
			ps.setString(4, o.getGoods());
			ps.setString(5, o.getLocations());
			ps.setString(6, o.getMid());
			ps.setString(7, o.getName());
			ps.setString(8, o.getPhone());
			ps.setString(9, o.getCode());
			
			
			af = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("주문내역 추가에 실패했습니다.");
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
	

	public ArrayList<Order> listOrder(String mid) { // 마이 주문내역 리스트  

		PreparedStatement ps = null;		
		Connection con;
		ResultSet rs = null;
		ArrayList<Order> oList = null;
		Order o = null;
		String sql = "SELECT * FROM CART WHERE MID=?";
		
		con = getConn();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();			
			
			oList = new ArrayList<Order>();
			while(rs.next()) {
				o=new Order();
			
				o.setShipping(rs.getInt("shipping"));
				o.setOrderNumber(rs.getString("orderNumber"));
				o.setTotal(rs.getInt("total"));
				o.setGoods(rs.getString("goods"));
				o.setLocations(rs.getString("locations"));
				o.setMid(rs.getString("mid"));
				o.setName(rs.getString("name"));
				o.setPhone(rs.getString("phone"));
				o.setCode(rs.getString("code"));
				o.setRegdate(rs.getString("regdate"));
				
				oList.add(o);
			}
		} catch (SQLException e) {
			System.out.println("주문내역조회 중 오류발생");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("접속해제 실패");
				e.printStackTrace();
			}
		}
		return oList;
	}
	
	
		public int deleteOrder(String orderNumber){
		
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		
		String sql = "DELETE ORDERINFO WHERE SEQ=?";
		
		con = getConn();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, orderNumber);
			af=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("주문내역 삭제 중 오류발생");
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("접속해제 실패");
				e.printStackTrace();
			}
		}
		return af;	
		
		}
	

}
