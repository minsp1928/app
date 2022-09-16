package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * 제약 조건 (constraint)
제약 조건 (constraint)이란 데이터의 무결성을 지키기 위해.
데이터를 입력 받을 때 실행되는 검사 규칙을 의미합니다.
이러한 제약 조건은 CREATE문으로 테이블을 생성할 때나 ALTER문으로 필드를 추가할 때도 설정할 수 있습니다.
-MYSQL에서 사용할 수 있는 제약 조건은 다음과 같습니다.
-제한 옵션을 주다/ 위반 시 무결성 조건에 위반된다~ 에러
1.NOT NULL    :기본은 널 허용
2.UNIQUE       :값이 중복이 되어지지 않는 속성 (ex id : 유효성 검사)
3.PRIMARY KEY
4.FOREIGN KEY
5.DEFUALT    : 널이 나올수 없는 개념
--질문)유효성 검사에서 id가 중복됐을 때 프론트에서 막는 게 맞는 건지, 백엔드에서 막는 게 맞는 건지..? 어디에 책임을 가져야하나
-->난 AJAX로 처리에서 db에 갔다 왔는디..

—>답은 둘다 중요하다. 비중은 프론트앤드가 더 비중있게 차단시키는게 맞다!

==이유: 서버의 일을 줄여주는게 당연(바람직)하다.프로세스를 처리할 수 있다면 거기서.. 자바스크립트

-NOT NULL 제약 조건을 설정하면, 해당 필드는 NULL 값을 저장할 수 없습니다.

즉, 이 제약 조건이 설정된 필드는 무조건 데이터를 가지고 있어야합니다.

NOT NULL 제약조건은 CREATE문으로 테이블을 생성할 때나,

나중에 ALTER문으로 추가 할 수도 있습니다.
 예문
 ----
EX1) 테이블생성 1.NOT NULL:기본은 널 허용
 CREATE TABLE TEST1(
	ID VARCHAR(10) NOT NULL,
	PW VARCHAR(10),
	NAME VARCHAR(10),
	MDATE DATE,
	AGE INT
);
----
EX2) 테이블생성 2.UNIQUE:값이 중복이 되어지지 않는 속성 (ex id : 유효성 검사)
 CREATE TABLE TEST2(
	ID VARCHAR(10) UNIQUE,
	PW VARCHAR(10),
	NAME VARCHAR(10),
	MDATE DATE,
	AGE INT
);
----
EX3) 테이블생성 3.PRIMARY KEY

 CREATE TABLE TEST3(
	ID VARCHAR(10) PRIMARY KEY,
	PW VARCHAR(10),
	NAME VARCHAR(10),
	MDATE DATE,
	AGE INT
);

test : 인서트할때 중복, 널값을 넣어보면서 확인해보자
----
EX3) 테이블생성 3.FOREIGN KEY   
 
 CREATE TABLE TEST4(     --parent
	CODE INT(10) PRIMARY KEY,
	ITEM VARCHAR(30),
	RDATE DATE
);
 CREATE TABLE TEST5(     --child
	NUM INT PRIMARY KEY,
	CODE INT,
	FOREIGN KEY (CODE)
	REFERENCES TEST4(CODE) ON UPDATE CASCADE

);
실행1)
"INSERT INTO TEST4 (CODE, ITEM, RDATE)VALUES(1, '아이템1', '2020-01-01')" //인서트
실행2)
"INSERT INTO TEST4 (CODE, ITEM, RDATE)VALUES(2, '아이템2', '2020-01-01')" //인서트
실행3)
"INSERT INTO TEST5 (CODE)VALUES(1)" //인서트
실행4)
"INSERT INTO TEST5 (CODE)VALUES(2)" //인서트

옵션 : ON UPDATE CASCADE
-부모키와 자식키로 연결되어있기때문에 데이터를 수정하면 연결되어있는 테이블까지 같이 수정하겠다.
삭제또한 마찬가지/ 자식먼저 삭제해야한다.
----
 */
public class Ex34Constraint {

	public static void main(String[] args) {
		Ex34Constraint ex34 = new Ex34Constraint();
//		ex34.Connection(ex34.alterSql());
//		ex34.Connection(ex34.insertSql());
		ex34.selectConnection(ex34.selectSql());
	//	ex34.Connection(ex34.updateSql());

	}//메인 끝
	
	public static String insertSql() { //실행 테스트용
//		String sql = "INSERT INTO TEST3 (PW) VALUES('123')";
//		String sql = "INSERT INTO TEST3 (ID, PW) VALUES('ABC', '123')";
//		String sql ="INSERT INTO TEST4 (CODE, ITEM, RDATE)VALUES(1, '아이템1', '2020-01-01')"; //인서트
//		String sql ="INSERT INTO TEST4 (CODE, ITEM, RDATE)VALUES(2, '아이템2', '2020-01-01')";//인서트
		String sql ="INSERT INTO TEST5 (CODE)VALUES(1)";//인서트 ->오류가 당연히 남
//		String sql ="INSERT INTO TEST5 (CODE)VALUES(2)";//인서트
		return sql;

	}//insertSql 끝
	
	public static String alterSql() {
		//ex1)
//		String sql = "ALTER TABLE ATTEST ADD COLUMN AGE VARCHAR(10) NOT NULL";//컬럼추가 뒤에 한 칸띄울것..					
//		String sql = "ALTER TABLE ATTEST MODIFY COLUMN AGE INT(10) NOT NULL"; //컬럼 수정
//		String sql = "ALTER TABLE ATTEST RENAME COLUMN PW TO PASSWORD"; //컬럼명 수정PW를 PASSWORD
//		String sql = "ALTER TABLE ATTEST MODIFY COLUMN PASSWORD VARCHAR(30) NOT NULL"; //VARCHAR(30) NOT NULL로 변경
//		String sql = "ALTER TABLE ATTEST CHANGE COLUMN PW PASSWORD VARCHAR(30) NOT NULL"; //VARCHAR(30) NOT NULL로 변경
//		String sql = "ALTER TABLE ATTEST DROP COLUMN AGE"; // AGE 삭제
//		String sql = "CREATE TABLE TEST4(CODE INT(10) PRIMARY KEY, ITEM VARCHAR(30), RDATE DATE"; // AGE 삭제
		String sql = " CREATE TABLE TEST4( "
				+ "	NUM INT RIMARY KEY,"
				+ "	CODE INT,"
				+ "	FOREIGN KEY (CODE)"
				+ "	REFERENCES TEST4(CODE) ON UPDATE CASCADE"; // AGE 삭제

		return sql;	
	}//alterSql 끝
	
	public static String selectSql() {
	//실행
		String sqls = "SELECT * FROM TEST4";
	//	String sql = "SELECT * FROM TEST5";
		
		return sqls;
	}//selectSql끝
	
	public static String updateSql() {
	//실행
		String sql = "UPDATE TEST4 SET CODE=7 WHERE CODE=2";
		//자식 테이블을 수정하면 부모 테이블도 수정되는것을 확인해보자
		
		return sql;
	}//updateSql끝
	
	public void Connection(String sql) {
		Connection conn = Ex30DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt = conn.prepareStatement(sql); ->두개로 나워서 사용하려면 메서드를 각각 정해야함
//			pstmt = conn.prepareStatement(alterSql());
			int result = pstmt.executeUpdate();
			String msg = result > -1 ? "성공" : "실패";
			System.out.println(msg);
		} catch (SQLException e) {
			System.out.println("e.getMessage() -> "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				System.out.println("e2.getMessage() -> "+e2.getMessage());
			}
		}
		
	}//Connection끝
	
	public void selectConnection(String selectSql) {
		Connection conn = Ex30DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for(int i = 1; i<columns; i++) {
				System.out.println(rsmd.getColumnName(i)+"\t");
			}
//			pstmt = conn.prepareStatement(sql); ->두개로 나워서 사용하려면 메서드를 각각 정해야함
//			pstmt = conn.prepareStatement(alterSql());
		} catch (SQLException e) {
			System.out.println("e.getMessage() -> "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				System.out.println("e2.getMessage() -> "+e2.getMessage());
			}
		}
		
	}//selectConnection끝
	
	//ex2) AGE INT 수정
	//ex3) PW를 PASSWORD와 VARCHAR(30) NOT NULL로 변경
	//ex4) AGE 삭제
	
}//클래스 끝
