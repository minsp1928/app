package jdbc;
/*
 - 그룹화하여 데이터 조회 (GROURP BY) ->그룹화 했을때, 그룹으로 인해서 추출된곳에서
 	조건을 주려면 WHERE이 아닌 HAVING을 사용한다
 	: 대표적인 그룹 함수 COUNT(), AVG(), MIN()MAX()SUM() 함수등과
 	HAVING 절(조건절)을 사용함 ->해빙절은 그룹바이외에는 사용할 수 없음
 	
 	[연습 테이블 만들기] : GBTEST
 	CREATE TABLE GBTEST(
	 	TYPE INT,
	 	phone varchar(20),
	 	NAME VARCHAR(10));
 	
 	[레코드 추가]

	insert into GBTEST values(1, '011-1234-5678', '홍길동');
	insert into GBTEST values(1, '011-1234-5678', '이순신');
	insert into GBTEST values(2, '010-1234-5678', '만득이');
	insert into GBTEST values(2, '010-1234-5678', '개똥이');
	insert into GBTEST values(2, '010-1234-5678', '칠득이');
	insert into GBTEST values(2, '010-1234-5678', '갑들이');
	insert into GBTEST values(3, '070-1234-5678', '갑순이');
 	
 	
 	
 	
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex40GroupBy {
	public static void main(String[] args) {
		Connection conn = null;
		conn = Ex30DBAction.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String sql = select();
		System.out.println("sql =>"+sql);
		try {
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
		
			for(int i = 1; i<=columns; i++) {
				String columnName = rsmd.getColumnName(i);
				System.out.print(columnName+"\t");
			}
			System.out.println("\n---------------------------");
			while(rs.next()) {//이거 없으면 데이터를 안꺼내옴
				for(int i = 1; i<=columns; i++) {
					String columnValue = rs.getString(i);
					System.out.print(columnValue+"\t");
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
	}

	
	public static String select() {
//	    ex1) 유형(type)별로 갯수를 가져오고 싶은데, 단순히 count 함수로 데이터를
// 		조회하면 전체 갯수만을 가져옵니다.
//		(7 나옴)
//		String sql = "SELECT COUNT(TYPE) FROM GBTEST";
		
//		ex2) 컬럼 그룹화 : type을 그룹화하여 name 갯수 조회 (컬럼 그룹화) 
//		(1:2, 2:4, 3:1)
//		String sql = "SELECT TYPE, COUNT(NAME) AS CNT FROM GBTEST GROUP BY TYPE";
		
//	 	ex3) type 1 초과인, type을 그룹화하여 name갯수를 조회
//	 	(조건 처리 후에 컬럼 그룹화 한 후에 조건 처리)vip가 3명 초과일때~ 다른 조건을 주는것
//		(2:4, 3:1)
//		String sql = "SELECT TYPE, COUNT(NAME) AS CNT FROM GBTEST WHERE TYPE > 1 GROUP BY TYPE";

//	 	ex4)type 1 초과인, type을 그룹화하여 name갯수를 가져온 후, 그 중에 갯수가 2개 이상인 데이터 조회 	
//		(2:4)
//		String sql = "SELECT TYPE, COUNT(NAME) AS CNT FROM GBTEST WHERE TYPE > 1 GROUP BY TYPE HAVING CNT >=2";
//		having 사용
		
//	 	연습문제) type1 초과인, type을 그룹화하여 name갯수를 가져온 후, 
//	 	그 중에 갯수가 2개 이상인 데이터 조회(내림차순 정렬)
//		(2:4)  --> 뭔가 이상하게 나옴 ㅎㅎ
		String sql = "SELECT name, TYPE, COUNT(NAME) AS CNT FROM GBTEST WHERE TYPE > 1 GROUP BY TYPE HAVING CNT >=2 ORDER BY TYPE DESC";
		return sql;
			
	}
}

