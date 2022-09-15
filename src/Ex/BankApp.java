package Ex;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
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

import Ex.User;
import app.AccountNumber;
import app.BankService;
import app.BankServiceImpl;
import app.initDepositAmountException;

public class BankApp extends JFrame implements ActionListener {
	JButton login, join, adminLogin;
	static BankService bs = new BankServiceImpl();
	//인터페이스는 객체생성불가 , 인터페이스를 임플리먼트로 객체를 선언
	
//	static User[] users; //정적으로 선언 기준점
	
	static List <User> arr = new LinkedList();
	
	
	/*public BankApp() {
		users = new User[2];//저장을 영속}
	}*/
	public BankApp() {
		super("으냉은행");
		login = new JButton("login");
		join = new JButton("join");
		adminLogin = new JButton("adminLogin");
		
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
	public static void main(String[] args) throws initDepositAmountException {
		// main 여기서 실행(spring boot로 치자면 controller)
		AccountNumber anum = new AccountNumber();//계좌정보
		int accountNum; //계좌번호를 넣을 변수? 배열?  
		Scanner sc = new Scanner(System.in);
			


		boolean run1 = true; //질문하는 반복문 (로그인 화면)
		boolean run2 = true; //질문하는 반복문  (로그인 후 메뉴화면)
		
		while(run1) {
			System.out.println("| 1: 로그인 | 2:회원가입 | 3: 관리자로그인");//할 수 있으면 아이디 비밀번호 찾기
				char selectNo;
			try {//if만 쓰면 다 돈다 -> else if로 전환
				 selectNo=sc.next().charAt(0);//입력받은 값 //try에 넣으면 무한 반복
				if((int)selectNo == 49 ) {//로그인
					bs.idpwCheck();
					break;
				}else if((int)selectNo == 50) { //회원가입
					bs.join();
		    
				}else if((int)selectNo == 51) { //관리자로그인
					bs.adminLogin();
					break;
		    
				}else {
					System.out.println("원하시는 메뉴의 숫자를 입력해주세요.");
				//	System.out.println((int)selectNo);
				}
			} catch (InputMismatchException e) { // 작동안함 why
				
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
		}//run2 끝
			

	}//end of main
	public char run1(char selectNo) {
		
		return selectNo;
	}                                                                    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}//end of BankApp
