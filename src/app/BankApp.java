package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankApp {
	static BankService bs = new BankServiceImpl();
	//인터페이스는 객체생성불가 , 인터페이스를 임플리먼트로 객체를 선언

//	static User[] users; //정적으로 선언 기준점
	
	static List <User> arr = new ArrayList<>();
	/*public BankApp() {
		users = new User[2];//저장을 영속}
	}*/

	public static void main(String[] args) {
		// main 여기서 실행(spring boot로 치자면 controller)
		AccountNumber anum = new AccountNumber();//계좌정보
		int accountNum; //계좌번호를 넣을 변수? 배열?  
		Scanner sc = new Scanner(System.in);
			

		boolean run1 = true; //질문하는 반복문 (로그인 화면)
		boolean run2 = true; //질문하는 반복문  (로그인 후 메뉴화면)
		
		while(run1) {
			System.out.println("| 1: 로그인 | 2:회원가입 | 3: 관리자로그인");//할 수 있으면 아이디 비밀번호 찾기
			
			int selectNo=sc.nextInt();//입력받은 값 //try에 넣으면 무한 반복
			
			try {
				if(selectNo == 1) {//로그인
					bs.idpwCheck();
					break;
				}if(selectNo ==2) { //회원가입
					bs.join();
		    
				}if(selectNo ==3) { //관리자로그인
					bs.adminLogin();
					break;
		    
				}else {
					System.out.println("원하시는 메뉴의 숫자를 입력해주세요.");
				}
			} catch (InputMismatchException e) { // 작동안함 why
				e.printStackTrace();
				System.out.println("원하시는 메뉴의 숫자만 입력해주세요.");
			}
	
		}//run1 끝
			

		while(run2) {
			System.out.println("| 1: 예금 | 2:출금  | 3:잔액조회 | 4: 계좌목록 | 5: 종료 |" );//반복문에 넣을것
			System.out.print("선택>  ");
			int choice=sc.nextInt();//입력받은 값
			switch (choice) {		
				case 1: //예금 
					bs.deposit();
					break;
				case 2://출금 		
					bs.withDraw();       			
					break;
				case 3://잔액조회/                  
					bs.Balance();
					break;
				case 4 ://계좌목록
					bs.allAccount();
					break;
				case 5:
					System.out.println("시스템을 종료합니다.");
					run2 = false;
					break;
				default:
					System.out.println("화면의 숫자만 입력해주세요.");
				}
	}
				
			
	
	
	}
}//end of main
