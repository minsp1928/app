package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jdbc.Ex30DBAction;
//
public class DBAction {
	private static DBAction instance; 
	private Connection conn;			  
	public DBAction() {				 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bankapp", "root", "mysql");
		} catch (ClassNotFoundException |SQLException e) {
			System.out.println("익셉션 : " + e.getMessage());
		}
	}
	public static DBAction getInstance() {
		if(instance == null) 
			instance = new DBAction();
			return instance; 
	}
	public Connection getConnection() {
		return this.conn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
