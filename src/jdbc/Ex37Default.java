package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//무통장입금등 가입자가 아닐때 사용자 정보는 없지만 기본적인 정보를 입력할 수 있는
/*
 default 제약조건은 해당 필드의 기본값을 설정할 수 있게 해줍니다.
 만약레코드를 입력할 때 해당 필드 값을 전달하지 않으면, 
 자동으로 설정된 기본값을 저장합니다.
 [테이블 생성]
 CREATE TABLE DEFAULT_TEST6(
 	ID			VARCHAR(10),
 	PW			VARCHAR(30),
 	NAME		VARCHAR(30) DEFAULT 'Anoymous',
 	MDate 		DATA,
 	AGE			INT
 	);
 */
public class Ex37Default {
	public static void main(String[] args) {
		Ex37Default ex37 = new Ex37Default();
		String sql = insert();
		ex37.Connection(sql);
	}//main 끝
	public void Connection(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Ex30DBAction.getInstance().getConnection();
			stmt = conn.createStatement();
			//데이터 입력
		//	sql = insert();
		//	stmt.executeUpdate(sql);
			
		//	출력 결과확인
		    sql = select();
		    rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			while(rs.next()) {//이거 없으면 데이터를 안꺼내옴
				for(int i = 1; i<columns; i++) {
					System.out.println(rs.getString(i)+"\t");
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
		
	}//selectConnection끝
	public static String select() {
		String sql = "SELECT * FROM DEFAULT_TEST6";
		return sql;
	}
	public static String insert() {
		String sql = "INSERT INTO DEFAULT_TEST6(ID, PW, MDATE, AGE) VALUES('ABC', '123','2021-09-08', 100)";
		return sql;
	}
}
