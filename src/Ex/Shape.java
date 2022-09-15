package Ex;

public abstract class Shape { //모호한 개념의 추상클래스
	protected int x, y;
	public abstract double area();
	public abstract double circumference();
	//protected abstract int getSize();//인터페이스가 수정된것임. 말고한다면?

}
