package Ex;

public class Ex02 {
	//모델?도메인 예제
	private String name;    //유저 이름
	private String id;		//유저 아이디 ->pk로 가야할듯 길이 유효성검사
	private String pw;	
	
	public Ex02(String name, String id, String pw) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	//생성자 사용목적-> 만들었던 필드를 초기화해줄때 사용
	//생성자의 인자를 가질 수 있다.

	 Ex02(int a) {
			//같은 이름으로 오버로드 가능하지만 같은 형태를 2개이상 사용은 불가
		 //이름과 변수의 타입, 갯수로
		}
	 Ex02(String a) {
		 //같은 이름으로 오버로드 가능하지만 같은 형태를 2개이상 사용은 불가
		 //이름과 변수의 타입, 갯수로
	 }
	 Ex02(int b, int a) {
			//String, int/String, int도 안됨 대신 int Sting은 된다
		}
	 public static void main(String[] args) {

		//new Ex02(20,10);//객체가 생성이 되어지면 호출되면 생성?
	}


}
