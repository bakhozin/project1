package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sist.vo.Cart;
import com.sist.vo.Members;
import com.sist.vo.Shop;

public class CartDAO {

	public Connection getConn() {
		String url = "jdbc:oracle:thin:@211.238.142.148:1521:orcl";
		String user="DD";
		String pwd="123456";
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {

			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {

			System.out.println("���� ����, ������ ��й�ȣ�� Ȯ���ϼ���.");
			e.printStackTrace();
		}
		return con;
	}
	


	public Cart getCart(String mid){  // ��ٱ��ϰ˻�   
		//��ǰ �󼼺����Ҷ����> 

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart c = null;

		String sql = "SELECT * FROM CART WHERE MID=? ";

		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();		

			if(rs.next()){
				c = new Cart();			

//				c.setSeq(rs.getString("seq"));  ����Ʈ�� 0�������� 
				c.setMid(rs.getString("mid"));
				c.setCode(rs.getString("code"));
				c.setName(rs.getString("sname"));
				c.setImage(rs.getString("image"));
				c.setPrice(rs.getInt("price"));
				c.setOrderQnt(rs.getInt("orderQnt"));
				c.setSize(rs.getString("ssize"));
				c.setQnt(rs.getInt("qnt"));

			}

		} catch (SQLException e) {
			System.out.println("ȸ������ ��ȸ�� ���� �߻�");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("�������� ����");
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public Cart getCart(String mid, String code){  // ��ٱ��ϰ˻�   
		//��ǰ �󼼺����Ҷ����> 

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart c = null;

		String sql = "SELECT * FROM CART WHERE MID=? AND CODE=? ";

		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, code);
			rs=ps.executeQuery();		

			if(rs.next()){
				c = new Cart();			

//				c.setSeq(rs.getString("seq"));  ����Ʈ�� 0�������� 
				c.setMid(rs.getString("mid"));
				c.setCode(rs.getString("code"));
				c.setName(rs.getString("sname"));
				c.setImage(rs.getString("image"));
				c.setPrice(rs.getInt("price"));
				c.setOrderQnt(rs.getInt("orderQnt"));
				c.setSize(rs.getString("ssize"));
				c.setQnt(rs.getInt("qnt"));

			}

		} catch (SQLException e) {
			System.out.println("ȸ������ ��ȸ�� ���� �߻�");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("�������� ����");
				e.printStackTrace();
			}
		}
		return c;
	}
	
	
	public int addProduct(Cart c){ //��ٱ��� ���� 
		Connection con=null;
		PreparedStatement ps = null;
		

		String sql = "INSERT INTO CART(SEQ, CODE, SNAME, IMAGE, MID, PRICE, ORDERQNT, SSIZE, QNT) VALUES((SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM CART), ?, ?, ?, ?, ?, ?, ?, ?)";
		
		con=getConn();

		int af=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getCode());
			ps.setString(2, c.getName());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getMid());
			ps.setInt(5, c.getPrice());
			ps.setInt(6, c.getOrderQnt());
			ps.setString(7, c.getSize());
			ps.setInt(8, c.getQnt());
			
			
			af = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("īƮ�߰��� �����߽��ϴ�.");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("���������� �����Ͽ����ϴ�.");
				e.printStackTrace();
			}
		}

		return af;
	}
	

	public ArrayList<Cart> listCart(String mid) { // ���� ��ٱ��� ����Ʈ?  

		PreparedStatement ps = null;		
		Connection con;
		ResultSet rs = null;
		ArrayList<Cart> cList = null;
		Cart c = null;
		String sql = "SELECT * FROM CART WHERE MID=?";
		
		con = getConn();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			
			
			cList = new ArrayList<Cart>();
			while(rs.next()) {
				c=new Cart();
				
				System.out.println("rs:"+rs);
			
				c.setSeq(rs.getString("seq"));
				c.setMid(rs.getString("mid"));
				c.setCode(rs.getString("code"));
				c.setName(rs.getString("sname"));
				c.setImage(rs.getString("image"));
				c.setPrice(rs.getInt("price"));
				c.setOrderQnt(rs.getInt("orderQnt"));
				c.setSize(rs.getString("ssize"));
				c.setQnt(rs.getInt("qnt"));
				
				cList.add(c);
			}
		} catch (SQLException e) {
			System.out.println("��ǰ��ü��ȸ �� �����߻�");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("�������� ����");
				e.printStackTrace();
			}
		}
		return cList;
	}
	
	
		public int deleteCart(String seq){
		
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		
		String sql = "DELETE CART WHERE SEQ=?";
		
		con = getConn();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, seq);
			af=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("īƮ���� �� �����߻�");
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("�������� ����");
				e.printStackTrace();
			}
		}
		return af;	
		
		}
	
		public int updateCart(int orderQnt, String mid, String code){ //CART ���� 
			
			Connection con=null;
			PreparedStatement ps=null;
			int af=0;
			
			String sql="UPDATE CART SET ORDERQNT=? WHERE MID=? and CODE=?";
			con=getConn();
	
			try {
				ps = con.prepareStatement(sql);
				
				ps.setInt(1, orderQnt);
				ps.setString(2, mid);
				ps.setString(3, code);
				
				
				af = ps.executeUpdate();
	
			} catch (SQLException e) {
				System.out.println("īƮ ������ ���� �߻�");
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					System.out.println("�������� ����");
					e.printStackTrace();
				}
			}
	
			return af;
		}

}
