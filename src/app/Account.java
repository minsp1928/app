package app;

import java.util.Date;

public class Account {
//
	private static String id;			//유저id
	private static String accountNum;	//계좌번호
	private static String accountPw;	//계좌비밀번호
	private static int balance;		//잔액
	private static Date opendate;      //계좌생성일
	
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
	
	
	public static Date getOpendate() {
		return opendate;
	}
	public static String getId() {
		return id;
	}
	public static String getAccountNum() {
		return accountNum;
	}
	public static String getAccountPw() {
		return accountPw;
	}
	public static int getBalance() {
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
	public Account setOpendate(Date opendate) {
		this.opendate = opendate;
		return this;
	}
	
}
