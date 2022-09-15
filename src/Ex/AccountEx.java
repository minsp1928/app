package Ex;

import java.util.Scanner;

public class AccountEx {
	public static void main(String[] args) {
		Account account = new Account();
		//예금하기
		
		account.deposit(10000);
		System.out.println("예금액: "+account.getBalance());
		//출금하기
		try {
			System.out.println("출금할 금액 : ");
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			account.withdraw(a);
		} catch (BalanceInsufficientException e) {
			String message = e.getMessage();
			System.out.println(message);
		}
	}

}
