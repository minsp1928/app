package Ex;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ex8 {
	Ex02[] member; //빈 객체
	static List<Ex02> list; //static으로 선언하면 어디서든 가용가능
	public Ex8(){//생성자를 통해 미리 배열의 공간을 설정
		member = new Ex02[3];
		member[0] = new Ex02("홍길동1", "a", "1");
		member[1] = new Ex02("홍길동2", "b", "2");
		member[2] = new Ex02("홍길동3", "c", "3");
		list = Arrays.asList(member); //array를 List처럼 사용할 수 있게 도와주는 클래스
		//Arrays-> 정렬관련 메서드
		//aslist를 쓰면 메모리를 경제적으로 쓸 수 있음
		//sort 함수가 Arrays 에도 있고 Collections 에도 있는데 일반적인 배열은 Arrays
		//제네릭관련도 신경써야,LinkedList 가 데이터 삽입에 더 유리한 면
		//.add()로 값추가/remove()로 제거가 가능하며 검색보단 추가 삭제를 많이 하는 쪽이 유리하다


		for(Ex02 m : list) {//향상된 for문 리스트  	for(Ex02 m : member) {//향상된 for문
			//객체변수 m에다가 list값을 넣겠다는 
			System.out.println(m.getName());
		}
		//컬렉션형이 들어와야하므로 리스트를 넣음
		list = new LinkedList<>(list);//기본생성자를 생성했다면,()를 비웠다면 list.add로 값을 다 넣어야함
	//	Object obj = list.get(0);//빈 객체로 사용하겠다.
		//Ex02 user = (Ex02)obj;//쓰고자하는 자료형으로 캐스팅 근데 오브젝트면 하위클래스의 메서드를 못씀
		Iterator<Ex02> iter = list.iterator();
		//이터레이터:반복자 배열이나 리스트같은 컬렉션의 데이터에 접근, 반복하는 객체
		//인터페이스 
		while(iter.hasNext()) {//존재하는 만큼 실행 / 없으면 false, hasnNext는 불리언타임. 
			System.out.println(iter.next().getName());//get으로 지정해주지않으면 주소값을 찾음
		}
		list.remove(member[1]);//삭제 / 인덱스로도 지울 수도 있고 객체로도 지울 수 있음
		for(Ex02 m : list) {//향상된 for문 리스트  	for(Ex02 m : member) {//향상된 for문
			System.out.println(m.getName());
		}
		
		
	}
	public static void main(String[] args) {
		//컬렉션 중 링크드리스트 예제
			new Ex8();//메인에서 선언을 해줘야 실행할때 나옴.
		
	}
}
