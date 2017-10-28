package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sist.vo.Review;

public class ReviewDAO {
	
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
	
	public ArrayList<Review> allNotices(int pg, String field, String query) {
		Connection con;
		//Statement st = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		ArrayList<Review> nList = null;
		int startRN = 1 + (pg-1)*5;
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS RN, N.* FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY TO_NUMBER(SEQ) DESC)N) WHERE RN BETWEEN ? AND ?";
		con=getConn();
		try {
			//st=con.createStatement();
			ps=con.prepareStatement(sql);
			//1. 한페이지에 보일 게시글 수 결정 (ex.10)-> 2. 페이징에 보이는 페이지 갯수 결정(ex.5)
			ps.setString(1, "%"+query+"%");
			ps.setInt(2, startRN);
			ps.setInt(3, startRN+5);
			rs=ps.executeQuery();
			
			nList = new ArrayList<>();
			while(rs.next()) {
				Review nt = new Review();
				nt.setSeq(rs.getString("seq"));
				nt.setTitle(rs.getString("title"));
				nt.setContent(rs.getString("content"));
				nt.setWriter(rs.getString("writer"));
				nt.setRegdate(rs.getString("regdate"));
				nt.setHit(rs.getInt("hit"));
				nt.setFilesrc(rs.getString("filesrc"));
				
				nList.add(nt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("리뷰 전체조회 중 오류발생");
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
		return nList;
	}
	
	//원하는 리뷰게시판 조회
	public Review getNotices(String seq) {
		Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Review n = null;
		String sql = "SELECT * FROM REVIEW WHERE SEQ=? ORDER BY TO_NUMBER(SEQ) DESC";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, seq);
			rs=ps.executeQuery();
			if(rs.next()){
				n=new Review();
				n.setSeq(rs.getString("seq"));
				n.setTitle(rs.getString("title"));
				n.setWriter(rs.getString("writer"));
				n.setContent(rs.getString("content"));
				n.setRegdate(rs.getString("regdate"));
				n.setHit(rs.getInt("hit"));
				n.setFilesrc(rs.getString("filesrc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("게시판 조회 중 오류발생");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("접속해제실패");
				e.printStackTrace();
			}
		}
		return n;
	}
	
	//게시판 추가
	public int addNotices(Review n, String mid) {
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		
		System.out.println(mid);
		
		String sql = "INSERT INTO REVIEW (SEQ, TITLE, WRITER, CONTENT, REGDATE, FILESRC) VALUES ((SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM REVIEW), ?, ?, ?, SYSDATE,?)";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, n.getTitle());
			ps.setString(2, mid);
			ps.setString(3, n.getContent());
			ps.setString(4, n.getFilesrc());
			
			af=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("게시판 추가 중 오류발생");
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("접속해제실패");
				e.printStackTrace();
			}
		}
		return af;
	}
	
	//게시판 수정
	public int updateNotices(Review n) {
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		String sql = "UPDATE Review SET TITLE=?, CONTENT=? WHERE SEQ=?";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, n.getTitle());
			ps.setString(2, n.getContent());
			ps.setString(3, n.getSeq());
			af = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("게시판 수정 중 오류발생");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("접속실패");
				e.printStackTrace();
			}
		}
		return af;
		
	}
	
	//게시글 삭제
	public int deleteNotice(Review n) {
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		String sql = "DELETE NOTICES WHERE SEQ=?";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, n.getSeq());
			af=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("게시글 삭제중 오류발생");
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("접속해제 실패");
				e.printStackTrace();
			}
		}
		
		return af;
	}

	

}
