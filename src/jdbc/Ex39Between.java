package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/*
  between (범위):a and b 에서 a와b를 포함한(inclusive)
  			그 사이의 모든 값에 해당하는지 여부를 조건으로 한다.
  			
  [실습테이블 만들기] : BWTEST
  
  CREATE TABLE BWTEST(
  		IDX INT,
  		BIRTH DATE,
  		NAME VARCHAR(10));
  
  [레코드 추가]
 
  */
public class Ex39Between {

	public static void main(String[] args) {
		Connection conn = Ex30DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = insert();
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
	public static String insert() {
		String sql = "insert into bwtest values(1, '2020-01-01', '홍길동')";
//		String sql = "insert into bwtest values(2, '2020-01-02', '민서')";
//		String sql = "insert into bwtest values(3, '2020-01-03', '나영')";
//		String sql = "insert into bwtest values(4, '2020-02-01', '민주')";
//		String sql = "insert into bwtest values(5, '2020-02-02', '성현')";
//		String sql = "insert into bwtest values(6, '2020-03-01', '다희')";
//		String sql = "insert into bwtest values(7, '2020-03-02', '갑순이')";
		return sql;
	}
	public static String select() {
	//	String sql = "SELECT * FROM BWTEST WHERE IDX BETWEEN";
	//	String sql = "SELECT * FROM BWTEST WHERE IDX>=2 AND IDX <=5";
	//	연습문제) 2020-01-02 ~ 2020-01-08 범위의 레코드를 추출하시오
	//	String sql = "SELECT * FROM BWTEST WHERE BIRTH BETWEEN '2020-01-02' AND '2020-01-08'";
		//Limit = 범위 추출
	//	ex1) 1~n  // 서브쿼리로 처리하지않고도 페이징이 가능
	//	String sql = "SELECT * FROM BWTEST 2'";
	//	ex2) 가져올 게시물 N개 ~ N개 이후부터
	//	String sql = "SELECT * FROM BWTEST LIMIT 2 OFFSET 5'";
	//	ex3) 게시물 N 이후부터 ~ 가져올 N개 //페이징으로 가장 쓰기 좋지 않을까
		String sql = "SELECT * FROM BWTEST ORDER BY IDX DESC LIMIT 5, 2'";
	//실습과제>뱅크사용자(회원) 출력화면을 만들고 5명씩 페이지별로 출력하시오
		return sql;
	}//select 끝
}
