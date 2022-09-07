package Ex;

import java.awt.Toolkit;

public class Ex13 extends Thread{
	//스레드
	//스레드의 run 메서드 : 특정된 작업(실행단위)를 수행하는 메서드
	public void run() {
		System.out.println("호출");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//소리와 "띵"을 동시에 출력하는것
		for(int i =0; i < 5; i++) {
			toolkit.beep(); //beep 음 소리 출력
			try {
				Thread.sleep(500);//0.5초 1000으로하면 1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
