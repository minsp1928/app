package Ex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Ex14 extends Frame implements Runnable{
	//랜덤,공튕기기
	
	//런에이블에서 생성
	
	int x = 0; //시작하는 좌표를 설정
	int y = 20;//사각형 사이즈가20이므로 아래로 내려와야 함.
	boolean xOrient, yOrient; //false로 자동초기화
	public Ex14() { //자바에서는 객체가 생성될때 필드가 자동초기화가된다.
		//=기본값(ex int = 0, boollan = false, double = 0.0) 
		setSize(300, 200);
		setVisible(true); //창안에 있는것을 보이게 해줌
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {//e라는 변수처리를 안하면 오류
				System.exit(0);//그냥 창닫기 X버튼 이벤트를 만들어준거임
			}
		});
	}
	public void paint(Graphics gr) {//자동호출(창을 띄우자마자 호출됨)
		//graphics : 붓역할
		//좌표값을 이용한 화면
		Random ran = new Random();
		Dimension d = this.getSize();
		//Dimension : 컨테이너(frame) 크기(가로,세로)에 대한 정보를 가져온다.
		if (xOrient) {
			x--;
			if (x < 0) { //0을 만나면 다시 그 숫자를 빼는것
				x=0;
				xOrient = false;
			}
		}else {
			x++;
			if(x>= d.width - 20 ) {//사각형의 길이 20을 고려한것
				x = d.width - 20;
				xOrient =true;
			}
		}
		if (yOrient) {
			y--;
			if (y < 0) {
				y=0;
				yOrient = false;
			}
		}else {
			y++;
			if(y>= d.height - 20 ) {
				y = d.height - 20;
				yOrient =true;
			}
		}
		int r = ran.nextInt(255); //(범위)안에서 랜덤값 설정
		int g = ran.nextInt(255);
		int b = ran.nextInt(255);
		gr.setColor(new Color(r, g, b));
		gr.drawRect(x, y, 20, 20);//xy좌표값과 가로세로
	}
	public void update(Graphics g) { //잔상을 만드는 역할을 한다
		//근데 오버라이드가 돼서(재정의) 원래의 update메서드에서 그림을 지우는 역할을
		//없앤거라 잔상이 남는다. update()를 지우면 잔상이 없이 바로 paint를 호출한다
		paint(g);//그림을 제거한 후 그림을 그리는 paint()를 호출한다
		
	}
	//Runnable을 하면 자동생성되는 override
	public void run() {//그림을 그리는것을 수행해~하는 스레드
		while (true) {
			repaint();//paint()로 가기전 update를 자동으로 호출
			try {
				Thread.sleep(10);//
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
	public static void main(String[] args) {
		Thread thread = new Thread(new Ex14()); //스레드객체생성 및 사용
		//나 자신에게 주기위해(런에이블 implements)나의 객체를 생성하면 런에이블을 사용할 수 있어
		//객체 생성할때 스타트를 호출할 수 있다.
		thread.start();
	}

}
