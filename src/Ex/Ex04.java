package Ex;

import java.util.Properties;

public class Ex04 {
	//서비스역할 예제
	public Ex02 member;
//	public Ex[] ex2 = new Ex02[2]
	public Ex04() {
		Properties properties = new Ex9().getAdminInfo();//정적인/ 프로퍼티에갖고있는 값으로 로그인	
		member = new Ex02("홍길동", 
				properties.getProperty("username"),
				properties.getProperty("password"));
	//	member = new Ex02("홍길동", "abc", "123"); 
		//ex2[0] = member;
	}
	public boolean login(String id, String pw){
		boolean result = false;//아이디,비번을 db에서 비교하고 맞는지 확인하기우해 boolean사용
//		if(id == member.getId() 
		if(id.equals(member.getId())
				&& pw.equals(member.getPw())){
			System.out.println(member.getId()+"님");
			result = true;
//==은 객체를 (레퍼런스)비교/equals 내용비교, ==를 흉내내서 만든 메서드로 object(최상위)클래스에 정의되어있다.
//오버라이드 문자열의 객체가 다른건지 문자의 내용이 다르넌지 비교?'
				//
	    }
		return result;//리턴이 없어서 계속 오류뜸 void가 아니므로 return필요
	}//end of login
	public void logout(String id) {
		
		System.out.println("로그아웃되었습니다.");
		
	}
}
