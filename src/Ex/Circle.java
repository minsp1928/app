package Ex;

public class Circle extends Shape {//is-a 관계
	protected int r;
	public  Circle() {
		r = 0;
	}
	public  Circle(int r) {
		this.r = r;
	}
	@Override
	public double circumference() {
		return Math.PI*2*r;
	}
	@Override
	public double area() {
		return Math.PI*r*r;
	}
	public int getRadious() {
		return r;
	}
	public void serRadius(int r) {
		this.r = r;
	}

	
}
