package Ex;

public class Rect extends Shape {//is-a 관계
	protected int width, height;
	public  Rect() {
		this(0,0);
	}
	public  Rect(int w, int h) {
		width = w;
		height = h;
		x=getWidth();
		y=getHeight();
		
	}
	public  double area(){
		return width * height;
	}
	public double circumference() {//둘레
		return 2 *(width + height);
		
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public void setSize(int w, int h) {
		width =w;
		height = h;
		
	}public int getSize() {
		return x * y;
		
	}

}
