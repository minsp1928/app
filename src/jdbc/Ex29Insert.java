package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex29Insert {
	Connection conn;
	public Ex29Insert() {//모든 데이터베이스 물리적 드라이버가 있음. 자바 환경에서 드라이버가 로딩이 되어야 db에 연결해서 사용할 수 있다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//패키지 안에 있는 class
			//"oracle.jdbd.driver.OralcleDriver"
			//드라이버 로딩하는 과정, 클래스가 없으면 클래스낫파운드익셉션이 발생, 다운받게된다?
			 conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "mysql");
			//jdbc:oracle:thin:@localhost:1521:xe:", "hr", "hr"
			//커넥션(인스턴스인) 객체가 생성되는것
			//db하고 연결해서 쓸려면 커넥션 객체가 있어야해
		} catch (ClassNotFoundException |SQLException e) {
			System.out.println("익셉션 : " + e.getMessage());
	
		}
	}//end of 생성자
	
	public void insert() {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		try {//동적으로 값 받아서 db에 넣기
			Scanner scan = new Scanner(System.in);
			System.out.print("아이디 입력 : ");
			String id = scan.next();
			System.out.print("비밀번호 입력 : ");
			String pw = scan.next();
			
			stmt = conn.createStatement();
			int result2 = stmt.executeUpdate
//			("INSERT INTO DBTEST values('user1', '123')");     //1.statement에서 그냥 바로 지정해서 값을 넣는 경우
			("INSERT INTO DBTEST values('"+id+"', '"+pw+"')"); //2.statement로 입력받은 값을 경우
/*3.		pstmt = conn.prepareStatement
			("INSERT INTO DBTEST values(?, ?)");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			int result = pstmt.executeUpdate();
			String msg = result > -1 ? "성공" : "실패";
*/
			String msg = result2 > -1 ? "성공" : "실패";
			System.out.println(msg);
			//dnl은 정수? 하여간 음수만 아니면 성공		
		} catch (SQLException e) {
			System.out.println("inset exection : "+e.getMessage());
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(pstmt != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Ex29Insert().insert();

	}

}
