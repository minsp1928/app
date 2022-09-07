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

public class Ex9 {//map 키와 벨루(둘다 오브젝트임) 컬렉션

	public static void main(String[] args) {
		//Map <String, String> map = new HashMap<>();//<>제네릭타입으로 정의 할 수 있음
		//Map <String, Ex02> map = new HashMap<>();
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
//		
//		map.put("spring", "1");//put : 맵에서 사용하는 메서드로 키와 벨루의 형식으로 저장하는것
//		map.put("summer", "2");
//		map.put("fall"  , "3");
//		map.put("witer" , "4");
//		String value = (String)map.get("spring");
//		//벨루를 사용하려면 키를 알아야함 컬렉션과 동일하게 get메서드를 통해가져옴
//		System.out.println(value);
//		Set<String> set =  map.keySet();// 셋을 얻어낼 수 있따(전환)<>제네릭타입으로 정의를 해줬기때문에 캐스팅안해도됨
//		//맵에 저장되어있는 키들을 가져와 셋에 넣어줌
//		//컬렉션에서 셋,맵,리스트 ㅁ-> 맵에서 컬렉션으로 받아오는것
//		Iterator<String> iter = set.iterator();
//		while(iter.hasNext()) {
//			String key = iter.next();
//		//	System.out.println("key : "+ iter.next() + "value : "+map.get(iter));//next가 계속반복되므로 변수에 넣음
//			System.out.println("key : "+ key + " value : "+map.get(key));
//		//iterator를 통해 반복적으로 순차적으로 가져오겠다.
//		 		 
//		}
	
		}//end of main
	public static Properties getAdminInfo() {
		Properties  properties= null;
		try {
			properties = new Properties();
			String  path = Ex9.class.getResource("database.properties").getPath();
			path = URLDecoder.decode(path,"UTF-8");
			properties.load(new FileReader(path));
			//팩토리 패턴(디자인패턴)으로 만들어진 xml을 사용할때 path를 읽어와서 
			//빈객체를 얻어올때와 비슷하다.
			/*
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			System.out.println(driver);
			*/
			
		} catch (Exception e) { e.printStackTrace();}
			return properties;
		
	}

}
