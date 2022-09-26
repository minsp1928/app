package Ex;

import java.util.Date;
//어노테이션을 사용하지 않는것일까?
public class User {
	//private으로 필드를..?
//은행-> 유저정보 어떤게 필요한가
	private int accountNum; //계좌번호 필수로 가야할까? 그럼 회원가입할때 계좌도 생성해야함..계좌생성제한
	private String name;    //유저 이름
	private String id;		//유저 아이디 ->pk로 가야할듯 길이 유효성검사
	private String pw;		//유저 비밀번호->길이, 영문, 숫자, 문자 가능하게?
	private int balance;
	private Date joindate;      //유저의 가입일
//	private int tel;		//유저 전화번호
//	private Date birthday;	//유저 생일 만약 이벤트를 넣는 다면 있는게 좋겠지 그러면 마일리지같은게 들어가야할까
	

	

	public User() {
		//생성자: 객체의 초기화
		//읻스텐즈 오브젝트가 생략되어있다..형변환 가능이란 의미
		super();//부모의 생성자에 접근할때
		//super : 자동생성하다보면 나오는데 상속이 되어있는 구조에서 객체를 자동생성, 생성자를 부른것
		//this : 지역변수와 전역변수의 개념. 이미 선언된 변수의 이름과 값이 같다면 this는 멤버에 있는 변수를 지칭할때 사용
		//멤버필드를 구분할때, 객체를 생성자에서 부를깨 사용이 가능
		//암묵적인 약속으로 변수같은 네임룰,
		//생성자 특징
		this.accountNum = accountNum;
		this.name = name;//보통 임의로 다른 변수이름을 사용하지않는다.(다른 개발자도 읽을 수 있어야하므로)
		this.id = id;
		this.pw = pw;
	//	this.tel = tel;
	//	this.birthday = birthday;
		this.balance = balance;
	}

	public User(int accountNum, String name, String id, String pw, int balance, Date joindate) {
		super();
		this.accountNum = accountNum;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.balance = balance;
		this.joindate = joindate;
	}

	//빈 객체패턴
	public int getAccountNum() {
		return accountNum;
	}


	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


//	public int getTel() {
//		return tel;
//	}
//
//
//	public void setTel(int tel) {
//		this.tel = tel;
//	}
//
//
//	public Date getBirthday() {
//		return birthday;
//	}
//
//
//	public void setBirthday(Date birthday) {
//		this.birthday = birthday;
//	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	
	
	
}
