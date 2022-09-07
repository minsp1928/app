package app;

 class X {  //할머니
	 	protected int i =10;
		protected String msg = " I am a X."; //6번째
		public void print() {
			System.out.println(msg);
		}
		public void play() {
			System.out.println("play..."+msg);
		}
 }
 class Y extends X { //엄마  x클래스와 이름이 같지만 상속관계기 때문에 여기 기준에선 x클래스가 메모리상 은닉화되어 사용이 가능하다.
		 protected int i = 20;
		 protected String msg = " I am a Y.";
		 @Override // 오버라이드를 안하면 에러남(왜냐면 재정의해서 사용한것이기때문이지 이름이 같은 메서드만들 수 없음)
		 //오버라이드 특징 : 재정의한 메서드만 사용가능.
		 public void print() {
			System.out.println(msg);//4번째
	}
}
 public class Z extends Y { //자식 (은닉변수->클래스타입의 고유의 타입, 필드를 활용하기위해 사용하겠다?)
	 protected int i = 30;
	 protected String msg = " I am a Z."; //5번째
	 public void print() {//오버라이드 된 메서드(어노테이션이 없지만!)재정의
		System.out.println(msg); //1번, 2번, 3번
	 }
	 public void play() {//y위에 x가 play메서드를 갖고있으므로 재정의된 오버라이드임
			System.out.println("play..."+msg);
		}
	 public void doZ() {
			System.out.println("do something in Z.");
		}
	 public void test(int i) {
		 Z z =new Z();
		 Y y = z; //z를 넣었음. -> 형변환 모습 상속관계는 is-a관계 z가 y타입으로 바뀐것
		 X x = z; //z를 넣었음.->z가 x타입으로 바뀐것
		 // 문제)
		 z.print(); 	// I am a Z.
		 y.print();		// I am a Z. ->위에서 z를 넣었기때문 
		 x.print(); 	// I am a Z. ->위에서 z를 넣었기때문 
		 super.print(); // z가 y를 상속받고있어서  y의 print메서드 실행 / super는 위의 부모에 접근
		 play(); 		// this가 생략되어있음. z클래스안에서 실행되었기 때문에 z클래스의 play메서드 실행
		 super.play();  //z위의 부모클래스의 play메서지그드를 실행하는데 y에는 play가 없고 그 위의 클래스인 x의 play메서드가 실행??
		 //y.doZ();->오류 나는 부모를 알지만 부모는 나를 모른다.
		 //super.super.print();
		 System.out.println("\ni ="+ i); // 15 -> main
		 //this가 생략되어있을때가 많이 있지만 지역변수에 값이 있다면 i는 지역변수다.
		 System.out.println("this.i ="+ this.i);//30 자식클래스가 나오는것같은데 x>y>z순으로 상속해서 z의 i?
		 //오버라이드의 특징: 한 번 재정의되면 재정의된 최종적인 정의만이 존재
		 //this는 나를 보고 super는 위의 부모에 접근
		 System.out.println("super.i ="+ super.i);//20 부모를 참조한다고 알고있는데 왜 y?-> z가 y를 상속받았으니까 y가 부모..당연
		 System.out.println("y.i ="+ y.i);//20 은닉변수선언 x에도 i가 있지만
		 System.out.println("x.i ="+ x.i);//10
		 System.out.println("((Y)this).i ="+ ((Y)this).i);//20
		 System.out.println("((X)this).i ="+ ((X)this).i);//10
		 //super.super.i = 10;
		 
	 }
	 
		public static void main(String[] args) {
			Z z = new Z();
			z.test(15);//34번라인에 선언한 메서드.
		}
}
 
