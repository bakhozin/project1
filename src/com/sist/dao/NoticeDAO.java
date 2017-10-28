package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sist.vo.Notice;


public class NoticeDAO {
	
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
	
	//집계함수
	public int getSeqCount(String field, String query) {
		Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		String sql = "SELECT COUNT(SEQ) FROM NOTICE WHERE "+field+" LIKE ?";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+query+"%");
			rs=ps.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println("count="+count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public Notice getHit(String seq){ //조회수늘리기 
		Connection con;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		Notice n = null;
		int af = 0;

		String sql = "SELECT SEQ,TITLE,CONTENT,WRITER,REGDATE,HIT+1 FROM NOTICE WHERE SEQ=?";
		String sql2 = "UPDATE NOTICE SET HIT=HIT+1 WHERE SEQ=?";

		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, seq);
			rs = ps.executeQuery();
			if(rs.next()){  
				n= new Notice();
				n.setSeq(rs.getString("seq"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setWriter(rs.getString("writer"));
				n.setRegdate(rs.getString("regdate"));
				n.setHit(rs.getInt("hit+1"));
				n.setFileSrc(rs.getString("fileSrc"));

			}

			ps2 = con.prepareStatement(sql2);
			ps2.setString(1, seq);

			af = ps2.executeUpdate();
			if(af==1){
				System.out.println("조회수 수정 성공");
			}

		} catch (SQLException e) {
			System.out.println(" 조회 중 오류발생");
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
				con.close(); 


			} catch (SQLException e) {
				System.out.println("접속해제 실패");
				e.printStackTrace();
			}
		}

		return n;
	}
	
	
	//리스트 전체 조회-검색
	public ArrayList<Notice> allNotices(int pg, String field, String query) {
		Connection con;
		//Statement st = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		ArrayList<Notice> nList = null;
		int startRN = 1 + (pg-1)*10;
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS RN, N.* FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY TO_NUMBER(SEQ) DESC)N) WHERE RN BETWEEN ? AND ?";
		con=getConn();
		try {
			//st=con.createStatement();
			ps=con.prepareStatement(sql);
			//1. 한페이지에 보일 게시글 수 결정 (ex.10)-> 2. 페이징에 보이는 페이지 갯수 결정(ex.5)
			ps.setString(1, "%"+query+"%");
			ps.setInt(2, startRN);
			ps.setInt(3, startRN+10);
			rs=ps.executeQuery();
			
			nList = new ArrayList<>();
			while(rs.next()) {
				Notice nt = new Notice();
				nt.setSeq(rs.getString("seq"));
				nt.setTitle(rs.getString("title"));
				nt.setWriter(rs.getString("writer"));
				nt.setContent(rs.getString("content"));
				nt.setRegdate(rs.getString("regdate"));
				nt.setHit(rs.getInt("hit"));
				
				nList.add(nt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("게시판 전체조회 중 오류발생");
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
	
	//원하는 게시판 조회
	public Notice getNotices(String seq) {
		Connection con;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Notice n = null;
		String sql = "SELECT * FROM NOTICE WHERE SEQ=? ORDER BY TO_NUMBER(SEQ) DESC";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, seq);
			rs=ps.executeQuery();
			if(rs.next()){
				n=new Notice();
				n.setSeq(rs.getString("seq"));
				n.setTitle(rs.getString("title"));
				n.setWriter(rs.getString("writer"));
				n.setContent(rs.getString("content"));
				n.setRegdate(rs.getString("regdate"));
				n.setHit(rs.getInt("hit"));
				n.setFileSrc(rs.getString("fileSrc"));
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
	public int addNotices(Notice n, String mid) {
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		
		System.out.println(mid);
		
		String sql = "INSERT INTO NOTICE (TITLE, SEQ , WRITER, CONTENT, REGDATE, FILESRC) VALUES (?, (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICE), ?, ?, SYSDATE,?)";
		con=getConn();
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, n.getTitle());
			ps.setString(2, mid);
			ps.setString(3, n.getContent());
			ps.setString(4, n.getFileSrc());
			
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
	public int updateNotices(Notice n) {
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE SEQ=?";
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
	public int deleteNotice(Notice n) {
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		String sql = "DELETE NOTICE WHERE SEQ=?";
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
