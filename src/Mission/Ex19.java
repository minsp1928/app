package Mission;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Ex19 { // 메인에서 실행됨
	List<Ex2> members;
	public Ex19() {//생성자 / 파일을 이용하여 회원정보를 저장, 읽어서 정보의 영속성을 만드는것.
		members = new ArrayList<>();//가장빠른?리스트/중간에 수정하고 그런다면 링크드리스트/맵을 사용
		//사용자 정보를 보관(영속)하기 위한 컬렉션
		Ex2 member = new Ex2("c", "3", "3"); //회원가입했다 치고~ 뱅크에서는 입력받아서 생성하는것
		members.add(member);//arraylist를 이용하여 순서대로 값을 집어넣겠다.
		fileInput( member);//생성하자마자 가입하자마자 파일에 저장하겠다는 의미(fileInput 메서드를 실행)
		fileOutput();//파일을 읽어오겠다
		for(Ex2 m : members) {
			System.out.println(m.getName());
		}
	}
	public void fileInput(Ex2 member) { //사용자 정보를 갖고있는 메서드
		try {
			PrintWriter pw =
					new PrintWriter(//true : append true ->덮어쓰지않고 계속 값을 추가하는것(기본은 false)->회원가입할때마다 파일에 저장하겠다.
					new FileWriter(new File("members.txt"),true));//파일 경로, 필요 하면 buffered 
			// 멤버.txt앞에 절대경로를 주는편이 어디있는지 알기 쉽다. 여기서는 워크스페이스쪽에 저장되었다.
			//-------------------------
			StringBuilder sb = new StringBuilder();//string과 메모리를 효율적으로 쓸 수 있다.
			sb.append(member.getName());
			sb.append("#");
			sb.append(member.getId());
			sb.append("#");
			sb.append(member.getPw());//하나의 문자열로 만드는것
			pw.println(sb.toString()); // 문자열을 파일안에 기록하겠다/버퍼안에서 수정할 수 있게하는것
			pw.close();
			//--------------------------
			pw.println(member.getName() + "#" + member.getId() + "#" + member.getPw());//사용하지않는 문자열(#)을 사용해서 나누는게 좋다.
			//#를 딜리미터(구분문자)로 잘라서 사용하겠다 split(문자열을 자르는
			pw.close();//여기서는 버퍼를 사용하지않았기 때문에 close를 안해주면 기록이 안됨//버퍼는 오토플러시가 있음
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void fileOutput() {
		try {
			BufferedReader br =
					new BufferedReader(
					new FileReader(new File("members.txt")));//파일리더에는 리드라인이 없음
			//바로 파일리더를 쓰려면 가공이 필요(int 아스키코드) 버퍼를 사용하면 문자로 읽을수있음
			while(br.ready()) {//로우넘버
				StringTokenizer st = 
						//new StringTokenizer(br.readLine(),"#", true);//리턴딜림? true(기본은 false)딜리미터도 토큰으로 보여주는것
						new StringTokenizer(br.readLine(),"#");//딜리미터 = 구분문자 
						//new StringTokenizer(br.readLine());//요소(엘리멘트?별로)잘라서 받아옴
				 		//커서의 다음값이 있을때까지 설정이 없음(디폴트는 간격(스페이스 딜리미터)
					while(st.hasMoreTokens()) {//컬럼넘버 / hasMoreElements -> blooean타입
						//넘겨줄 토큰이 있는지 커서를 옮겨 확인하는 함수로 없으면 false 
					//	Object obj = st.nextElement();=>object형으로 반환하지만 String으로도 받을 수 있음
					//	String obj = st.nextToken();
					//	Ex2 member = new Ex2("b", "2", "2");이렇게 하면 매번 값이 생성됨
						Ex2 member = new Ex2(st.nextToken(),//객체를 생성하고 저장의 목적으로 사용한다.
								st.nextToken(),st.nextToken()); // 객체를 생성하고 바로 주기
						members.add(member);//members 리스트에 파일을 읽어와 잘라낸 토큰들을 다시 담아준다. = 객체화
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new Ex19();
	}
}
