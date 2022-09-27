package app;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import app.User;

public class BankApp extends JFrame implements ActionListener {
	JButton login, join, adminLogin;
	static BankDao bd = new BankDaoImpl();
	//인터페이스는 객체생성불가 , 인터페이스를 임플리먼트로 객체를 선언
	//dd
//	static User[] users; //정적으로 선언 기준점
	
	static List <User> arr = new LinkedList();
	
	
	/*public BankApp() {
		users = new User[2];//저장을 영속}
	}*/
/*	public BankApp() {
		super("으냉은행");
		login = new JButton("로그인");
		join = new JButton("회원가입");
		adminLogin = new JButton("관리자로그인");
		
		Panel p = new Panel();
		p.add(login);
		p.add(join);
		p.add(adminLogin);
		
		this.add(p, "Center");
		this.setSize(300,100);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
		});
		login.addActionListener(this);
	}
	*/
	
	public void mainMenu () throws initDepositAmountException, IOException {

		boolean mainMenu = true; //질문하는 반복문 (로그인 화면)
		while(mainMenu) {
			
			System.out.println("| 1: 로그인 | 2:회원가입 | 3: 관리자 | 4: 종료 |");
		//	System.out.println("| 1: 로그인 | 2:회원가입 | 3: 관리자 | 4: 아이디/비밀번호찾기 | 5: 종료 |");//할 수 있으면 아이디 비밀번호 찾기
			System.out.print("선택>>");
			
			Scanner sc = new Scanner(System.in);
			char selectNo=sc.next().charAt(0);//입력받은 값 //try에 넣으면 무한 반복
			BankApp bankApp = new BankApp();
			try {//if만 쓰면 다 돈다 -> else if로 전환

				if((int)selectNo == 49 ) {//로그인
					User user = bd.idpwCheck();
					if(user.getId()=="") {
						continue;
					}else if (user.getId()!= null) {
						bankApp.bankMenu();//뱅크 메뉴
					}
//				if((int)selectNo == 49 ) {//로그인
//					User user = bd.idpwCheck();
//					String id = user.getId();
//					if(id!=null) {//로그인 성공
//						System.out.println("idpwCheck id->"+id);
//						bankApp.bankMenu();//뱅크 메뉴
//						break;
//					}else {//로그인 실패, 메인으로 돌아옴
//						bankApp.mainMenu();
//						break;
//					}
//				break;
				}else if((int)selectNo == 50) { //회원가입
					bd.join();
					
				}else if((int)selectNo == 51) { //관리자로그인
					bd.adminLogin();
					bankApp.adminBankMenu();
					break;
				}
			//	else if((int)selectNo == 52) { //아이디,비밀번호찾기
				//	bd.idPwFind(); }
				else if((int)selectNo == 52) { //종료
					System.out.println("|           ++은행시스템을 종료합니다++           |");
					break;
				}
				else {
					System.out.println("원하시는 메뉴의 숫자를 입력해주세요.");
				//	System.out.println((int)selectNo);
				}
			} catch (InputMismatchException e) { // 작동안함 why
				
				System.out.println("원하시는 메뉴의 숫자만 입력해주세요.");
				System.out.println("e.getMessage() -> "+e.getMessage());
				e.printStackTrace();

			}
	
		}//mainMenu 끝
	//	return selectNo;
	}                                                                    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void bankMenu() throws initDepositAmountException, IOException {
		boolean bankMenu = true; //질문하는 반복문  (로그인 후 메뉴화면)
		
		while(bankMenu) {

			Scanner sc = new Scanner(System.in);
			System.out.println("| 1: 계좌개설 | 2: 예금 | 3:출금  | 4:잔액조회 | 5: 계좌목록 | 6: 계좌해지 | 7: 송금 | 8: 로그아웃 | 9: 종료 |" );//반복문에 넣을것
			System.out.print("선택>  ");
			int choice=sc.nextInt();//입력받은 값
		
			switch (choice) {	
				case 1: //계좌개설
					bd.openAccount();
					break;
				case 2: //예금 
					bd.deposit();
					break;
				case 3://출금 		
					bd.withDraw();       			
					break;
				case 4://잔액조회/                  
					bd.Balance();
					break;
				case 5 ://계좌목록
					bd.allAccount();
					break;
				case 6 ://계좌해지
					bd.deleteAccount();
					break;
				case 7 ://송금
					bd.remittance();
					break;
				case 9://종료
					System.out.println("|------이용해주셔서 감사합니다------|");
					bankMenu = false;
					break;
				default:
					System.out.println("화면의 숫자만 입력해주세요.");
				}
		}//bankMenu while 끝
			

	}//bankMenu 끝10-26653-500482
	public void adminBankMenu() throws initDepositAmountException, IOException {
		boolean adminBankMenu = true; //질문하는 반복문  (로그인 후 메뉴화면)
		BankApp bankApp = new BankApp();
		
		while(adminBankMenu) {

			Scanner sc = new Scanner(System.in);
			System.out.println("| 1: 사용자목록 | 2: 회원 탈퇴 | 3: 로그아웃 | 4: 종료 |" );//반복문에 넣을것
			System.out.print("선택>  ");
			int choice=sc.nextInt();//입력받은 값
		
			switch (choice) {		
				case 1://계좌목록
					bd.allUserAdmin();
					//계좌목록을 부를 때 파일에 저장(근데 값 추가가 아니라 업데이트,파일에서 목록읽어오기)
					break;
				case 2:
					System.out.println("------회원 탈퇴-----");
					bd.userDelete();
					break;
				case 3:
					System.out.println("------로그아웃------");
					adminBankMenu = false;
					bankApp.mainMenu();
					break;
				case 4:
					System.out.println("시스템을 종료합니다.");
					adminBankMenu = false;
					break;
				default:
					System.out.println("화면의 숫자만 입력해주세요.");
				}
		}//run2 끝
	}
	public static void main(String[] args) throws initDepositAmountException, IOException {
		// main 여기서 실행(spring boot로 치자면 view와 controller)
		/* System.out.println("main에 들어오나"); */
		
		BankApp bankApp = new BankApp();
		bankApp.mainMenu();//메인 메뉴 스타트?


		
	}//end of main
}//end of BankApp
