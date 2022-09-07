package Ex;

import java.io.FileReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Ex9_2 {//map 키와 벨루(둘다 오브젝트임) 컬렉션

	public static void main(String[] args) {

		//타입을 넣어주는것으로 객체도 넣을 수 있다./객체로 넣어야하는 컬렉션이기에 int가 아닌 integer사용
		//키는 중복되면 안됨(논리적인 목적을 달성할 수 없는 에러발생)
		Map<String, String> map = new Hashtable<>();
		Properties properties = getAdminInfo();
		String adminId = properties.getProperty("username");
		String adminPw = properties.getProperty("password");
		map.put(adminId, adminPw);
		Scanner scanner = new Scanner(System.in);//예제니까 메인에서 하는거지 위의 생성자에 빼는게 좋다
		while(true) {
			System.out.println("아이디와 비밀번호를 입력해주세요");
			System.out.print("ID : ");
			String id = scanner.next();
			System.out.print("PW : ");
			String pw = scanner.next();
			if(map.containsKey(id)) { //컬렉션안에 저장되어있는 객체를 확인할 수 있다.
				if(map.get(id).equals(pw)) {
					System.out.println("로그인 되었습니다");
					break;
				}else System.out.println("비밀번호를 다시 입력해주세요");				
			}else System.out.println("아이디를 다시 입력해주세요");
		}
	}

	public static Properties getAdminInfo() {
		Properties  properties= null;
		try {
			properties = new Properties();//properties 맵처럼 키벨루 형식 환경설정 정보를 담음
			String  path = Ex9_2.class.getResource("database.properties").getPath();
			path = URLDecoder.decode(path,"UTF-8");
			properties.load(new FileReader(path));
			//팩토리 패턴(디자인패턴)으로 만들어진 xml을 사용할때 path를 읽어와서 
			//빈객체를 얻어올때와 비슷하다.
		
			
		} catch (Exception e) { e.printStackTrace();}
			return properties;
		
	}

}
