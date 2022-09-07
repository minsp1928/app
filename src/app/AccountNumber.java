package app;

import java.util.Date;

public class AccountNumber {
//계좌정보 테이블 (계좌번호로 유저와 join)
	private int accountNum; //계좌번호 -> pk
	private String type;//통장 종류 1=일반계좌,2=적금
	private int balance;//잔고
//	private Date creation;//개설날짜
	
	public int getAccountNum() {
		return accountNum;
	}
	
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
