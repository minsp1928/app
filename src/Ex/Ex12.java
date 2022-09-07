package Ex;

public class Ex12 {

	public static void main(String[] args) {
		Thread thread = new Ex13();//형변환(안해도 되긴함?)
		thread.start();//스레드의 실행단위를 실행해
		//런메서드를 오버라이드하고 생성구현한 메서드를  호출하는게 아닌
		//스레드는 start()메서드로 콜백함수의 원리로 ex13의 run메서드를 호출
		//
		//스레드의 실행단위가 메인과 별개로 실행 = 병렬로 실행 
		for(int i =0; i < 5; i++) {//"띵"이 출력됨과 동시에 Ex13의 run메서드가 실행
			System.out.println("띵");
			try {
				Thread.sleep(500);//0.5초 1000으로하면 1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				

	}

}
