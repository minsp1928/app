package jdbc;//join
/*
  1. cross join
  2. equi join
  3. left join 
  4. rigt join
  
    1. cross join은 cartesian product(카데시안[카디션] 곱)이라고도 하며
   	조인되는 두 테이블에서 곱집합을 반환한다.
   	크로스 조인은 가장 간단하고 조인의 기본
   	모든 행을 다 가져오기 때문에 정규화된 데이터베이스에는 사용되지 않는다.
   	첫 번째 테이블의 행수를 두 번째 테이블의 행수로 곱한 것만큼의 행을 반환.
   	
   	2. equi join(inner equi join)
   	where절에 조건연산자 "="를 사용하여 중복된(교집합) 값을 결과로 추출함.
   	
    3. left outer join(left join)
    left라는 것을 from 절에서 지정된 왼쪽 또는 첫 번째 테이블을 참조.
    기준테이블의 값 + 테이블과 기준테이블의 중복된 값을 보여줍니다.
    '왼쪽 테이블'을 기준으로 조인, 결과값은 A테이블의 모든 데이터와
    a테이블과 b테이블이 중복되는 값이 검색.
    
    4. right outer join(right join)
  	right라는 것을 from 절에서 지정된 오른쪽 또는 두 번째 테이블을 참조.
  	left outer join의 반대입니다.
  	오른쪽 테이블을 기준으로 join, 결과값은 b테이블의 모든데이터와
  	a테이블과 b테이블이 중복되는 값이 검색.
  	
	[연습 테이블 만들기1] :JNTEST1

	CREATE TABLE JNTEST1(
	ID INT primary key auto_increment, //PK로만 사용가능(시퀀스)
	phone VARCHAR(20),
	NAME VARCHAR(10));
	
	[레코드 추가]
	insert into JNTEST1(PHONE,NAME) values('011-1234-5678', '홍길동');
	insert into JNTEST1(PHONE,NAME) values('011-1234-5678', '이순신');
	insert into JNTEST1(PHONE,NAME) values('010-1234-5678', '만득이');
	insert into JNTEST1(PHONE,NAME) values('010-1234-5678', '개똥이');
	insert into JNTEST1(PHONE,NAME) values('010-1234-5678', '칠득이');
	insert into JNTEST1(PHONE,NAME) values('070-1234-5678', '갑순이');

	[연습 테이블 만들기2] :JNTEST2

	CREATE TABLE JNTEST2(
	ID INT,
	MDATE DATE,
  	NAME VARCHAR(10));
	
	[레코드 추가]
	insert into JNTEST2 values(1, '2020-01-01', '축구선수');
	insert into JNTEST2 values(2, '2020-01-02', '농구선수');
	insert into JNTEST2 values(3, '2020-01-03', '배구선수');
	insert into JNTEST2 values(4, '2020-02-01', '축구선수');
	insert into JNTEST2 values(5, '2020-02-02', '농구선수');
	insert into JNTEST2 values(6, '2020-03-01', '배구선수');

*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex42Join {

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
		
			for(int i = 1; i<=columns; i++) {//컬럼 수(당연히 1부터 시작)
				String columnName = rsmd.getColumnName(i);//컬럼의 이름을 갖고오겠다
				System.out.print(columnName+"\t");
			}
			System.out.println("\n--------------------------------------------");
			while(rs.next()) {//이거 없으면 데이터를 안꺼내옴
				for(int i = 1; i<=columns; i++) {//컬럼 로우별 전체 데이터를 가져옴반복
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
		//ex1)//곱집합( 로우가 로우별로 )
	//	String sql = "SELECT * FROM JNTEST1 CROSS JOIN JNTEST2";
	//	String sql = "SELECT * FROM JNTEST1,JNTEST2"; //CROSS JOIN은 ","로대체가능
		//ex2)조건이 같을때(동등 조인) = 교집합
//		String sql = "SELECT * FROM JNTEST1 T1 INNER JOIN JNTEST2 T2 ON T1.ID =T2.ID";
//		String sql = "SELECT * FROM JNTEST1 T1, JNTEST2 T2 WHERE T1.ID =T2.ID";
		//ex3) 교집합을 포함한 왼쪽만값만(IS NULL 사용시 교집합도 안나오는 초승달모양)
		String sql = "SELECT * FROM JNTEST1 T1 LEFT OUTER JOIN JNTEST2 T2 ON T1.ID =T2.ID";
		//ex4)
		//String sql = "SELECT * FROM JNTEST1 T1 RIGHT OUTER JOIN JNTEST2 T2 ON T1.ID =T2.ID";
	
		return sql;
	}
}
