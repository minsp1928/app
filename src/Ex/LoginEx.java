package Ex;

public class LoginEx {

	public static void main(String[] args) {
		try {
			login("white", "12345");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			login("blue", "54321");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public static void login(String id, String password) 
			throws NotExistIDException, WrongPasswordException{
		//id가 blue가 아니라면 NotExistIDException 발생시킴
		if(!id.equals("blue")) {
			throw new NotExistIDException("아이디가 존재하지않습니다");
		}
		if(!password.equals("12345")) {
			throw new WrongPasswordException("패스워드가 틀립니다.");
		}
	}
	/* 실행결과
	 * 아이디가 존재하지않습니다 첫번째 로그인 조건에선 아이디가 틀림
	   패스워드가 틀립니다.    두번째 로그인 조건에선 비밀번호가 틀림
	 * 
	 * */

}
