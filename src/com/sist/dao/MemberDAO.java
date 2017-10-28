package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sist.vo.Members;

public class MemberDAO {
	
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
	
	public int addMember(Members m){ //회원가입
		Connection con=null;
		PreparedStatement ps = null;
		

		String sql = "INSERT INTO MEMBERS(MID, PWD, NAME, BIRTHDAY, LOCATIONS, GENDER, EMAIL, QUESTION, ANSWER, PHONE, REGDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,SYSDATE)";
		con=getConn();

		int af=0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getMid());
			ps.setString(2, m.getPwd());
			ps.setString(3, m.getName());
			ps.setString(4, m.getBirthday());
			ps.setString(5, m.getLocations());
			ps.setString(6, m.getGender());
			ps.setString(7, m.getEmail());
			ps.setString(8, m.getQuestion());
			ps.setString(9, m.getAnswer());
			ps.setString(10, m.getPhone());
			
			af = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("회원가입에 실패했습니다.");
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
	
	public String searchPwd(String mid ,String answer, String question){ //비밀번호 찾기
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pwd = null;

		String sql = "SELECT PWD FROM MEMBERS WHERE MID=? AND ANSWER=? AND QUESTION=?";
		
		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, answer);
			ps.setString(3, question);
			rs=ps.executeQuery();		
						
			if(rs.next()){
				pwd=rs.getString("pwd");
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
		return pwd;
	}
    public String searchMid(String name, String email){  // 아이디 찾기
    	
    	Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mid = null;

		String sql = "SELECT MID FROM MEMBERS WHERE NAME=? AND EMAIL=?";
		
		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			rs=ps.executeQuery();		
						
			if(rs.next()){
				mid=rs.getString("mid");
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
		return mid;
	}
    
 public int searchMid(String MID){  // 아이디 중복체크 
    	
    	Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mid = null;
		int count = 0;

		String sql = "SELECT MID FROM MEMBERS WHERE MID=?";
		
		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, MID);
			rs=ps.executeQuery();		
						
			if(rs.next()){
				mid=rs.getString("mid");
				count++;
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
		return count;
	}
				
	
	public Members getMember(String mid){  // 회원검색
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Members m = null;

		String sql = "SELECT * FROM MEMBERS WHERE MID=?";
		con = getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			
						
			if(rs.next()){
				m = new Members();
				m.setMid(rs.getString("mid"));
				m.setPwd(rs.getString("pwd"));
				m.setName(rs.getString("name"));
				m.setBirthday(rs.getString("birthday"));
				m.setGender(rs.getString("gender"));
				m.setEmail(rs.getString("email"));
				m.setQuestion(rs.getString("question"));
				m.setAnswer(rs.getString("answer"));		
				m.setPhone(rs.getString("phone"));
				m.setMg(rs.getInt("mg"));
				m.setRegdate(rs.getString("regdate"));
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
		return m;
	}
		
	
	public int updateMembers(Members m){ //회원 정보 수정 
		
		Connection con=null;
		PreparedStatement ps=null;
		int af=0;
		
		String sql="UPDATE MEMBERS SET PWD=?, NAME=?, BIRTHDAY=?, LOCATIONS=?, EMAIL=?, GENDER=?, QUESTION=?, ANSWER=?, PHONE=?, WHERE MID=?";
		con=getConn();

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getPwd());
			ps.setString(2, m.getName());
			ps.setString(3, m.getBirthday());
			ps.setString(4, m.getLocations());
			ps.setString(5, m.getEmail());
			ps.setString(6, m.getGender());
			ps.setString(7, m.getQuestion());
			ps.setString(8, m.getAnswer());
			ps.setString(9, m.getPhone());
			ps.setString(10, m.getMid());
			
			af = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("회원정보 수정중 오류 발생");
			e.printStackTrace();
		} finally {
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
	
	public int updateMG(Members m){ //회원 정보 수정 
		
		Connection con=null;
		PreparedStatement ps=null;
		int af=0;
		
		String sql="UPDATE MEMBERS SET MG=? WHERE MID=?";
		con=getConn();

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, m.getMg());
			ps.setString(2, m.getMid());

			
			af = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("마일리지 수정중 오류 발생");
			e.printStackTrace();
		} finally {
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
	
	public int deleteMember(Members m){
		
		Connection con;
		PreparedStatement ps = null;
		int af = 0;
		
		String sql = "DELETE MEMBERS WHERE MID=?";
		
		con = getConn();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, m.getMid());
			af=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("회원탈퇴 중 오류발생");
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
