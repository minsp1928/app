package Ex;

public class Ex15Exception { //문제
	String str = "";
	public void foo(int i) {
		try {//예외가 발생할 수 있는 영역을 지정
			if ( i == 1) { // 0일 경우 들어오지않음 
				throw new Exception(); //throw : 일종의 예외처리
			}
			str +=" 1 ";//문자열이므로 연산x결합연산
			//String보단 스트링버퍼,스트링빌더가 더 낫다.(나중에 toString?)
		} catch (Exception e) { //예외발생 하면 들어와
			str += " 2 ";
			return;
		}finally {//예외가 발생하든 발생하지않든 무조건 실행(강제성)
			str += " 3 ";
		}
		str += " 4 "; 
	}
	public static void main(String[] args) {
		Ex15Exception ee = new Ex15Exception();
		ee.foo(0); //str : 1 3 4
		//인스턴스를 통해서 int 0을 들고 메서드를 호출
		// 0이기때문에 if문x = exception에 들어가지않음 = 1넣고 2안함
		//finally는 항상 거치니까 3 넣고 try catch문에서 나와서 4를 포함
		ee.foo(1); //str : 2 3 4? ->왜 4를 안감
		//return과 finally가 같이 존재해서! return으로 2를 반환,(메서드 끝 = 종료)
		//끝내기전에 finally를 거치고 오기때문에 2, 3
		System.out.println(ee.str);
		//답은 1 3 4 2 3
	}

} //
