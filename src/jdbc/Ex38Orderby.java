package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex38Orderby {
/*
	 ORDER BY 문 - ORDER BY(ASC[ascending], DESC[descending])
	 정렬
	 [테이블 생성]
	 CREATE TABLE OBTEST(NUM INT, ID VARCHAR(10));
	 
*/	public static String insert() {
		String sql ="insert into obtest values(1, 'abc')";
//		String sql ="insert into obtest values(2, 'def')";
//		String sql ="insert into obtest values(3, 'ghi')";
		return sql;
	}
	public static String orderBy() {
	//	String sql = "select * from obtest order by NUM ASC";
		String sql = "select * from obtest order by ID ASC";
		return sql;
	}
	public static void main(String[] args) {
		Connection conn = Ex30DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = orderBy();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for(int i = 1; i<columns; i++) {
				String colName =rsmd.getColumnName(i);
				System.out.print(colName+"\t");
			}
			System.out.println("\n-------------------------");
			while(rs.next()) {
				for(int i =1; i <=columns; i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("e.getMessage() -> "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				System.out.println("e2.getMessage() -> "+e2.getMessage());
			}
		}

	}//main 끝

}
