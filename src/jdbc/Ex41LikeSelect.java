package jdbc; //우편검색 실습? 동만 검색해서 나오게 하면 쉽다?

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 	[형식]				[설명]
 	컬럼명 like '%cd%'	컬럼값 중 cd가 포함된 문자열이 모두 대상
 	컬럼명 like 'cd%'		컬럼값 중 cd로 시작하는 문자열이 대상
 	컬럼명 like 'c%d'		컬럼값 중 c로시작하고 중간 값들은 무엇이든 상관없이
 						(자리수도 상관없이) 끝자리값이 d인 문자열이 대상
 	컬럼명 like '_cd_'	컬럼값 자리수가 4자리이고 1번째, 4번째 자리값은
 						무엇이든 상관없는 문자열이 대상
 	컬럼명 like 'cd_'	컬럼값 자리수가 3자리이고 cd로 시작하고 3번째값은
 						무엇이든 상관없는 문자열이 대상
 	컬럼명 like 'c_d'	컬럼값 자리수가 3자리이고 1번째 값이 c,3번째 값이 d이고
 						중간값 1자리는 무엇이든 상관없는 문자열이 대상
 
 	[연습 테이블 만들기] : LSTEST
 	CREATE TABLE LSTEST(
 		phone VARCHAR(20),
		NAME VARCHAR(10)
		);
	
	[레코드 추가]
	insert into LSTEST values('011-1234-5678', '홍길동');
	insert into LSTEST values('011-1234-5678', '이순신');
	insert into LSTEST values('010-1234-5678', '만득이');
	insert into LSTEST values('010-1234-5678', '개똥이');
	insert into LSTEST values('010-1234-5678', '칠득이');
	insert into LSTEST values('070-1234-5678', '갑순이');
	
	
*/
public class Ex41LikeSelect {

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
		//ex1)011로 끝나는 자리수상관없는 PHONE 문자열 ( 아무것도 안나옴)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE PHONE LIKE '%011'";
		//ex2)011로 시작하는 자리수상관없는 PHONE 문자열 (홍길동, 이순신정보)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE PHONE LIKE '011%'";
		//ex3)070이 들어가는 자리수상관X PHONE 문자열(갑순이 정보)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE PHONE LIKE '%070%'";
		//ex4)0으로시작해 8로 끝나는 자리수상관X PHONE 문자열(모든 정보)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE PHONE LIKE '0%8'";
		//ex5)3자리수의 가운데 순이 들어가는 이름 문자열(이순신, 갑순이)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE NAME LIKE '_순_'";
		//ex6)3자리수의 김으로 시작하는 이름 문자열(김씨없음)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE NAME LIKE '김__'";
		//ex7)3자리수의 이로 끝나는 이름 문자열((만득,개똥,칠득,갑순)+이)
	//	String sql = "SELECT PHONE, NAME FROM LSTEST WHERE NAME LIKE '__이'";
		//ex8)3자리수의 갑으로 시작 이로 끝나는 이름 문자열(갑순이)
		String sql = "SELECT PHONE, NAME FROM LSTEST WHERE NAME LIKE '갑_이'";
	
		return sql;
	}
}
