package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex32Delete {

	public static void main(String[] args) {
		Connection conn = Ex30DBAction.getInstance().getConnection();//싱글톤으로 만든 인스턴스를 쓰겠다~
		PreparedStatement pstmt = null;
		System.out.println("삭제할 아이디를 입력하세요");
		Scanner sc = new Scanner(System.in);
		System.out.print("ID : ");
		String id = sc.next();
		
		try {//prepareStatement메서드로 쿼리를 넣으면 객체가 생성, 
			pstmt = conn.prepareStatement(delete());
//			/설정한 값을 셋팅해줌
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();//()안에 인자 주면안됨(state와 다르게?)
			String msg = result > -1 ? "성공" : "실패";//삼항연산자 -> 0,1,-1만 나오는데 -1만 실패
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("데이터베이스 드라이버 exception : "+e.getMessage());
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				System.out.println("delete SQLException : "+e.getMessage());
			}
		}
	}
	public static String delete() {
		String sql = "delete from dbtest where id=?";
//		String sql = "delete from dbtest where id=user1";
		return sql;
		
	}

}
