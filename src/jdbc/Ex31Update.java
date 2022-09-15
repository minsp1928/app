package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Ex31Update { //업데이트 쿼리를 가져오는 메서드
	public String updateSql() {
		System.out.println("비밀번호를 변경할 아이디를 입력하세요");
		Scanner scan = new Scanner(System.in);
		System.out.print("바꿀 아이디 입력 : ");
		String id = scan.next();
		System.out.print("비밀번호 입력 : ");
		String pw = scan.next();
		String sql = "update dbtest set pw='"+ pw +"' where id='" + id +"'   ";
		return sql;								//"'앞에 한 칸 띄우고 where,특정된 사용자에게만(unique) : pk
	}
	public void update(String sql) { //업데이트 쿼리에서 리턴한  sql을 갖고와서 사용
//		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection conn = Ex30DBAction.getInstance().getConnection();//싱글톤으로 만든 인스턴스를 쓰겠다~
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			String msg = result > -1 ? "성공" : "실패";
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("update exception : "+e.getMessage());
		} finally {
			try {
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				System.out.println("update SQLException : "+e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		 Ex31Update ex31 = new Ex31Update();
		 ex31.update(ex31.updateSql());
	}
}
