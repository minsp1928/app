package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex27JDBC {
	public Ex27JDBC() {//모든 데이터베이스 물리적 드라이버가 있음. 자바 환경에서 드라이버가 로딩이 되어야 db에 연결해서 사용할 수 있다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//패키지 안에 있는 class
			//"oracle.jdbd.driver.OralcleDriver"
			//드라이버 로딩하는 과정, 클래스가 없으면 클래스낫파운드익셉션이 발생, 다운받게된다?
			Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "mysql");
			//jdbc:oracle:thin:@localhost:1521:xe:", "hr", "hr"
			//커넥션(인스턴스인) 객체가 생성되는것
			//db하고 연결해서 쓸려면 커넥션 객체가 있어야해
		} catch (ClassNotFoundException |SQLException e) {
			System.out.println("익셉션 : " + e.getMessage());
	
		}
	}//end of 생성자
	public static void main(String[] args) {
		new Ex27JDBC();
		System.out.println("test");

	}//end of main

}//end of class
