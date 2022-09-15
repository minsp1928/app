package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ex30DBAction {
	private static Ex30DBAction instance; //싱글톤 디자인패턴을 사용해서 하나의 객체를 만들어두고 사용하겠다(모듈화)
	private Connection conn;			  //대표적인 싱글톤 : 캘린더
	public Ex30DBAction() {				  //싱글톤에서 쓰는 룰 getInstance메서드 사용한대
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "mysql");
		} catch (ClassNotFoundException |SQLException e) {
			System.out.println("익셉션 : " + e.getMessage());
		}
	}
	public static Ex30DBAction getInstance() {
		if(instance == null) 
			instance = new Ex30DBAction();
			return instance; //instance를 선언할 때 static 안써서 오류		
	}
	public Connection getConnection() {
		return this.conn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
