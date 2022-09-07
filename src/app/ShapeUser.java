package app;

public class ShapeUser {
	public static void main(String[] args) {
		Shape[] shape = new Shape[3]; //배열이기때문에 선언되었던 int형만 사용할 수 있다
		shape[0] = new Circle(5);//상속되었기때문에 된다(is a) 
		shape[1] = new Circle(7);//extends된 도형은 다양한 성질로 바뀌어서 사용가능하다.
		shape[2] = new Rect(9, 5);//다형성 =-> 오브젝트 배열을 선언한것
		//shpae 형태가 된것
		//컬렉션인 배열이기때문에 객체를 사용.
		System.out.println("shape[0].area() = "+shape[0].area());//78.53981633974483
		System.out.println("shape[1].area() = "+shape[1].area());//153.93804002589985
		System.out.println("shape[2].area() = "+shape[2].area());//45.0
		//문제 사각형의 사이즈를 출력해보시오
		//배열로하면 부모의타입.(자식의 타입을 모른다)
//		
//		System.out.println(shape[2].x );//이건 뽑을 수 있음.
//		System.out.println(shape[2].y);//이건 뽑을 수 있음.
//		int x = (integer)shape[2].x;
//		int y = shape[2].y;
//		System.out.println("x, y "+x+y);
		Rect rect = (Rect) shape[2];//왜 cast가 떴는가 = 타입변환
		//Rect안에있는 메서드를 사용해야하기때문에 재정의 해준것.
		//System.out.println("shape[2].getSize() = "+ shape[2].getSize());->인터페이스를 수정해서한것.
		System.out.println("rect.getSize() = "+ rect.getSize());
	}

}
