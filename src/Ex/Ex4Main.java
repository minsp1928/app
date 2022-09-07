package Ex;

public class Ex4Main {

	public static void main(String[] args) {
		Ex04 ex = new Ex04();
		
//		boolean result = ex.login("abc","123");
		boolean result = ex.login("abc1","123");
		if(result) {
			System.out.println("관리자로그인이 되었습니다.");
	//		new Ex10().setVisible(true);
		}else {
			
			System.out.println("id또는 password가 올바르지 않습니다.");
		}
		
		if(result) {
			System.out.println("로그인 되었습니다.");
		}else {
			System.out.println("id또는 password가 올바르지 않습니다.");
		}

	}
}
