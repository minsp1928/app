package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex33Select {

	public static void main(String[] args) {
		Ex33Select ex33 = new Ex33Select();
		ex33.select(ex33.selectSql());
	}//end of main
	public static String selectSql() {
//		System.out.println("selectSql");
//		String sql = "SELECT ID, PW FROM DBTEST";
//		String sql = "SELECT ID, PW FROM DBTEST WHERE ID ='유저2'";
		String sql = "SELECT * FROM DBTEST";
		return sql;
	}//end of selectSql
	public void select(String sql) {
	//	System.out.println("select");
		Connection conn = Ex30DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for(int i = 1; i <= cols; i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
				//해당컬럼이 존재하는 만큼 가져오겠다
				//차이는 컬럼에 대한 개수제한을 두지않겠다. = 반복문을 통해 계속 데려온다?
			}
			System.out.println();
//			System.out.println("ID\tPW");
			List<User> data =new ArrayList<>();
			
			while(rs.next()) {//로우의 개념으로 커서를 이동해서 데이터가없을때까지 줄을 데려오겠다 
			User user = new User().setId(rs.getString(1)).setPw(rs.getString(2));
			//영속화된 데이터를 바로 추출된 사용자 데이터를 컬렉션화(모델에 저장)
			//캡슐레이션->컬렉션에 담아서 사용할 수 있게해주는것
			data.add(user);
			/*
				for(int i = 1; i <= cols; i++) {
					System.out.print(rs.getString(i) + "\t");//영속성 데이터가 여기서 나오는것???
					//해당컬럼이 존재하는 만큼 가져오겠다
					//차이는 컬럼에 대한 개수제한을 두지않겠다. = 반복문을 통해 계속 데려온다?
				}
				System.out.println();
				*/
				//컬럼의 필드별~ 1 : 아이디, 1: 비밀번호
//				System.out.println(rs.getString(1) + "\t" + rs.getString(2));
		//		System.out.println(rs.getString("ID") + "\t" + rs.getString(2));
				//오버로드 되어있으므로 id로 가져올 수 있음
			}
			Iterator<User> iterator =data.iterator();
			//iterator로 영속db-> 컬렉션에 담음 ->컬렉션을 편집
			while(iterator.hasNext()) {
				User userinfo = iterator.next();
				System.out.println(userinfo.getId() + "\t" + userinfo.getPw());
			}
		} catch (Exception e) {
			System.out.println("Ex33Select exception : "+e.getMessage());
		}finally {
			try {
				if ( rs != null)rs.close();//닫아준 순서대로 닫기
				if ( pstmt != null)pstmt.close();//닫아준 순서대로 닫기
				if ( conn != null)conn.close();//닫아준 순서대로 닫기
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	class User{//캡슐레이션? 게터세터? 빈객체를 이용해? 영속해서 dto 객체화 하는것
		private String id;
		private String pw;
		public String getId() {
			return id;
		}
		public User setId(String id) {
			this.id = id;
			return this;
		}
		public String getPw() {
			return pw;
		}
		public User setPw(String pw) {
			this.pw = pw;
			return this;
		}
		
	}
}//end of class
