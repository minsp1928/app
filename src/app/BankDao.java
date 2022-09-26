package app;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
//
public interface BankDao {
	void openAccount();//계좌개설
//	boolean deposit(User cust, long input); //입금
	void idpwCheck();//로그인 아이디,비번확인
	int join() throws initDepositAmountException;//회원가입
	void adminLogin(); //관리자 로그인
	int Balance();//잔액
	boolean deposit(); //입금
	boolean withDraw();//출금
	void allAccount(String id);//전체계좌조회
	void allUserAdmin();//관리자 전체사용자정보조회
	User selectId(String id);
	Account selectAccount(String accountNum, String accountPw);
	Account accountCheck(String id); //계좌 유무 조회
	//서비스역할
	//@Serviece 쓰고 싶은데 어노테이션쓰려면 롬복을 써야겠지...?
//기능을 나열해 놓는 문서(상속은 implements(구현)쪽이고..?인터페이스도 상속의 개념이 가능한건 다형성이 존재.)
//은행프로그램 메서드를 설계
//
//로그인 인터페이스 - 조회
//회원가입 인터페이스 -인서트
//마이페이지 인터페이스 -조회 ,인서트, 업데이트, 삭제 가능
//계좌개설 인터페이스 -유저아이디당 계좌 3개 제한을 위해 조회, 인서트(랜덤 숫자)자유적금, 일반계좌 종류 다르게?

	
}
