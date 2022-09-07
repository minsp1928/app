
package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class BankServiceImpl implements BankService{
		
	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//	int balance = 20000;//잔고
	//	int accountNum; //계좌번호를 넣을 변수? 배열?  	
		//User user = new User(); -> 여기서만 하면 여기서만 사용됨 놉!
	private static int index = 0;

	
	@Override
	public void idpwCheck() {//로그인 체크
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check = true;
		while(check) {
			System.out.println("-----");
			System.out.println("로그인");
			System.out.println("-----");
			System.out.print("아이디를 입력해주세요 : ");
			id = sc.next();
			System.out.print("패스워드를 입력해주세요 : ");
			pw = sc.next();
			//id 혹은 pw가 다르면 반복문이 다시 돌아라
				for(int i=0; i<BankApp.arr.size(); i++) {//아이디, 패스워드 체크
					if(id.equals(BankApp.arr.get(i).getId()) && pw.equals(BankApp.arr.get(i).getPw())){
						System.out.println("id : "+BankApp.arr.get(i).getId()+" pw : "+BankApp.arr.get(i).getPw());
						System.out.println(BankApp.arr.get(i).getName()+"님 환영합니다.");
						check = false;
						break;
					}/*else if(!id.equals(BankApp.arr.get(i).getId()) || !pw.equals(BankApp.arr.get(i).getPw())){
						//이건 그냥 의미가 있나
						// check = true; 의미없음
					}*/
			
				}
			
		// 메서드로 만들어서 (기능)을 호출하는것으로.
	}
		
		
	
	}//로그인 체크 끝-----------------------------
	
	@Override
	public int join() {//회원가입
		Scanner sc = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		String year = Integer.toString(cal.get(Calendar.YEAR)); 
		String mon  = Integer.toString((cal.get(Calendar.MONTH)))+1;
		String date = Integer.toString(cal.get(Calendar.DATE));
		
		int accountNum = 0;
		String name = null;
		String id = null;
		String pw = null;
		int balance = 0;
		
		
		System.out.println("------");
		System.out.println("회원가입");
		System.out.println("------");
//		for(int i = 0; i<users.length; i++) {
			System.out.print("이름 :");
			name = sc.next();
			System.out.print("아이디 :");
			id = sc.next();
			/*
			for(int j = 0; j<users.length; j++) {
				if(users[j].getId().equals(id)) {
					System.out.println("이미 있는 아이디입니다.");
				}
			}
			*/
			System.out.print("비밀번호 :");
			pw = sc.next();
			System.out.print("비밀번호 재확인 :");
			String pwChk = sc.next();
			if(pw.equals(pwChk)) {
				System.out.println("비밀번호 확인.");
			}else {
				System.out.println("비밀번호가 다릅니다.");
			}
		

			System.out.print("초기입금액 : ");
			balance = sc.nextInt();
			if(balance>0) {
				System.out.println(balance+"원이 입금되었습니다.");
			}else{
				System.out.println("0이상의 금액을 입력해 주세요");
			}
	/*accounts[index] = new Account(accountNumber, password, name, balance);
	index++; 배열로 했다면 인덱스를 1씩 늘려주는 시퀀스를 이용해야함*/
			
		accountNum = (int) ((Math.random()*999999)+1);//랜덤으로 돌리지만 리스트 중복체크필요
		String joinadte = year+mon+date; 
		BankApp.arr.add(new User(accountNum,name,id,pw,balance,joinadte));
		System.out.println(name+"님 가입을 환영합니다! | 가입일 : "+joinadte);
		System.out.println(name+"님의 계좌번호는 "+accountNum+"입니다.");

		 return 1;
		
	}//회원가입 끝 -------------
	
	@Override
	public void adminLogin() { //관리자로그인
		Map<String, String> map = new HashMap<>();
		Properties properties = getAdminInfo();
		String adminID = properties.getProperty("adminId");//admin
		String adminPW = properties.getProperty("adminPw");//admin
		map.put(adminID, adminPW);
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("---------");
			System.out.println("관리자로그인");
			System.out.println("---------");
			System.out.print("아이디를 입력해주세요 : ");
			String id = sc.next();
			System.out.print("패스워드를 입력해주세요 : ");
			String pw = sc.next();
			if(map.containsKey(id)) {//containsKey는 boolean형태. key값이 존재하는지 아닌지를 알 수 있다.
				if(map.get(id).equals(pw)) {
					System.out.println("관리자로 로그인이 되었습니다.");
					break;
				}else System.out.println("비밀번호를 확인해주세요");
			}else System.out.println("아이디를 확인해주세요");
		}
		
	}//관리자로그인 끝
	public static Properties getAdminInfo() {//프로퍼티안의 관리자 정보가져오기
		//properties 클래스를 사용하여 설정파일을 제어
		Properties properties =null;
		try {
			properties = new Properties();
			String path = BankServiceImpl.class.getResource("adminDB.properties").getPath();
			//class.getResource를 이용하여 해당 클래스의 절대경로를 getpath를 이용하여 파일 객체의 경로를 반환한다.
			path =URLDecoder.decode(path,"UTF-8");
			//대소문자, 숫자, 밑줄을 제외한 url에 있는 문자를 코드화하는 인코딩 결과를 디코딩 = 텍스트화
			properties.load(new FileReader(path));
			//load라는 메서드를 통해 프로퍼티 파일을 읽어옴
			
		} catch (Exception e) { e.printStackTrace();
			System.out.println("에러"+e.getMessage());
		}
		return properties;
		
	}//관리자정보 가져오기 끝

	@Override
	//깃허브테스트
	public int Balance() {//잔액확인 메서드?
		Scanner sc = new Scanner(System.in);
		User user = new User();
		System.out.println("계좌번호를 입력해 주세요 : ");
		int accountNumber = sc.nextInt();
		
		for(int i=0; i<BankApp.arr.size(); i++) {
			if(accountNumber == BankApp.arr.get(i).getAccountNum()) {
			
				int balance = BankApp.arr.get(i).getBalance();
				System.out.println(BankApp.arr.get(i).getName()+"님의 잔액은 "+BankApp.arr.get(i).getBalance()+"원 입니다.");
				System.out.println(BankApp.arr.get(i)+"님의 잔액은 "+BankApp.arr.get(i).getBalance()+"원 입니다.");
	
				
			}else {
				System.out.println("계좌번호를 정확히 입력해주세요.");
			}
		
		}
		return 0;
	}//잔액확인 메서드 끝-----
	
	@Override
	public boolean deposit() {//예금
		Scanner sc = new Scanner(System.in);
		User user = new User();
		System.out.println("계좌번호를 입력해 주세요 : ");
		int accountNumber = sc.nextInt();
		
		for(int i=0; i<BankApp.arr.size(); i++) {
			if(accountNumber == BankApp.arr.get(i).getAccountNum()) {
				
				
				System.out.print("입금할 금액 입력 : ");
				int balance = BankApp.arr.get(i).getBalance();
				int deposit = sc.nextInt();
				balance += deposit;
				user.setBalance(balance);
				BankApp.arr.add(i,user);
				
				if (deposit>=0) {
							System.out.println(deposit+"원이 입금되었습니다. 잔액은 "+BankApp.arr.get(i).getBalance()+"원 입니다.");
			
							return true;
						}else {
							System.out.println("1원 이상의 금액을 입력해주세요.");

						}
			
			}else {
				System.out.println("계좌번호를 정확히 입력해주세요.");
			}
		
		}
		return true;

	}//예금 끝

	@Override
	public boolean withDraw() {//출금
		Scanner sc = new Scanner(System.in);
		User user = new User();
		System.out.println("계좌번호를 입력해 주세요 : ");
		int accountNumber = sc.nextInt();
		
		for(int i=0; i<BankApp.arr.size(); i++) {
			if(accountNumber == BankApp.arr.get(i).getAccountNum()) {
				//어떻게 해야 리스트에 담긴 계좌번호와 잔고를 맵에 넣지?
				
				System.out.print("출금할 금액 입력 : ");
				int balance = BankApp.arr.get(i).getBalance();
				int withdraw = sc.nextInt();
				balance -= withdraw;
				user.setBalance(balance);
				BankApp.arr.add(i,user);
				
				if (balance>=withdraw) {
							System.out.println(withdraw+"원이 출금되었습니다. 잔액은 "+BankApp.arr.get(i).getBalance()+"원 입니다.");
							System.out.println(balance);
							
							return true;
						}else {
							System.out.println("잔액이 부족합니다.");
							System.out.println(balance);
						}
			
			}else {
				System.out.println("계좌번호를 정확히 입력해주세요.");
			}
		
		}
		return true;

	}//출금 끝

	@Override
	public void allAccount() {//전체계좌목록 조회
		//관리자 아이디일때 조회, 삭제 가능
		//일반 아이디일때 조회 가능
		//if(BankApp.arr.)
		System.out.println("-----------");
		System.out.println("계좌목록");
		System.out.println("-----------");	
		for(int i = 0; i<BankApp.arr.size(); i++) {
			System.out.println("이름 : "+BankApp.arr.get(i).getName()
								+"\t계좌번호 : "+BankApp.arr.get(i).getAccountNum()
								+"\t가입일   :"+BankApp.arr.get(i).getJoinadte());
		}
		
	}

	

	

}