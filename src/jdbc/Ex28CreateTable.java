package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Ex28CreateTable {
	Connection conn; 
	public Ex28CreateTable() {//모든 데이터베이스 물리적 드라이버가 있음. 자바 환경에서 드라이버가 로딩이 되어야 db에 연결해서 사용할 수 있다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//패키지 안에 있는 class
			//"oracle.jdbd.driver.OralcleDriver"
			//드라이버 로딩하는 과정, 클래스가 없으면 클래스낫파운드익셉션이 발생, 다운받게된다?
			 conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "mysql");
			//jdbc:oracle:thin:@localhost:1521:xe:", "hr", "hr"
			//커넥션(인스턴스인) 객체가 생성되는것
			//db하고 연결해서 쓸려면 커넥션 객체가 있어야해
		} catch (ClassNotFoundException |SQLException e) {
			System.out.println("익셉션 : " + e.getMessage());
	
		}
	}//end of 생성자
	public void creatTable() { // table을 테스트로 만들어보는중
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate
			("create table DBTEST(ID varchar(50), PW varchar(50))");
			if (result < 0) {
				System.out.println("실행실패");
			}else {
				System.out.println("실행성공");
				
			}
		} catch (SQLException e) {
			System.out.println("createTable try 익셉션 -> "+e.getMessage());
		}finally {
			try {
				if(stmt != null)stmt.close();
				if(stmt != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Ex28CreateTable().creatTable();
		System.out.println("test");

	}//end of main

}//end of class
