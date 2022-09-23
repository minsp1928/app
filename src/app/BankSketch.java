
package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class BankSketch {
//
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int balance = 20000;//잔고
	int accountNum; //계좌번호를 넣을 변수? 배열?  
	boolean run = true;//질문하는 반복문 
	
	while(run) {
		System.out.println("1: 계좌개설 | 2: 예금 | 3: 출금| 4: 잔액조회 | 5: 종료");//반복문에 넣을것
		System.out.print("선택>  ");
		int choice=sc.nextInt();//입력받은 값
		
		switch (choice) {
		
		case 1://계좌개설
			 System.out.println("계좌를 개설하시겠습니까?");
			 System.out.println("1.네 | 2.아니오");
			 int check = sc.nextInt(); 
			 
			 if(check == 1) {
				 Random rn = new Random();
				 accountNum = rn.nextInt(9)+1;//1~10까지의 랜덤숫자를 계좌번호로 설정
				 System.out.println("계좌번호 : "+accountNum);
			 }else {
				
				System.out.println("메인화면으로 돌아갑니다.");
			}
			 break;
		
		case 2://예금
			/*System.out.println("계좌번호를 입력해주세요");
			int accountNumCh = sc.nextInt();
			System.out.println("계좌번호 : " + accountNumCh);*/
			//db에 연결하지않아서 어떻게 처리해야할지 모르겠음.
			//if (accountNumCh>accountNum) 
			//계좌번호가 존재한다면 예금할 금액입력 else 존재하지않는 계좌입니다, 
			//계좌를 개설하시겠습니까? 1이면 케이스1로 되돌아가기
			//2이면 메인화면으로 돌아갑니다.(반복문 돌리기-> break없애기)
			System.out.print("예금할 금액 입력 : ");
			balance += sc.nextInt();
			break;
		case 3://출금
			System.out.print("출금할 금액 입력 : ");
			int withdraw = sc.nextInt();
			if (withdraw <= balance) {//다시 입력 받아야하나? 아님 변수에 입력받을까?->일단 변수에 받는다.
				balance -= withdraw;
				System.out.println(withdraw+"원이 출금되었습니다. 잔액은 "+balance+"원 입니다.");
				break;
			}else {
				System.out.println("잔고가 부족합니다.");
				break;
			}
			
		case 4 ://잔액조회
			System.out.println("잔액은 "+balance+"원 입니다.");
			break;
			
		case 5:
			System.out.println("시스템을 종료합니다.");
			run = false;
			break;
		default:
		}
		
	}
	
	
	
	
	}
	

}