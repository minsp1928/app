package app;

import java.util.Date;

public class Account {

	private String id;			//유저id
	private String accountNum;	//계좌번호
	private String accountPw;	//계좌비밀번호
	private int balance;		//잔액
	private Date opendate;      //계좌생성일
	
	public Account() {
		super();
		
	}
	public Account(String id, String accountNum, String accountPw, int balance, Date opendate) {
		super();
		this.id = id;
		this.accountNum = accountNum;
		this.accountPw = accountPw;
		this.balance = balance;
		this.opendate = opendate;
	}
	
	
	public Date getopendate() {
		return opendate;
	}
	public String getId() {
		return id;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public String getAccountPw() {
		return accountPw;
	}
	public int getBalance() {
		return balance;
	}
	
	
	public Account setId(String id) {
		this.id = id;
		return this;
	}
	public Account setAccountNum(String accountNum) {
		this.accountNum = accountNum;
		return this;
	}
	public Account setAccountPw(String accountPw) {
		this.accountPw = accountPw;
		return this;
	}
	public Account setBalance(int balance) {
		this.balance = balance;
		return this;
	}
	public Account setopendate(Date opendate) {
		this.opendate = opendate;
		return this;
	}
	
}
