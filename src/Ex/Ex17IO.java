package Ex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ex17IO {

	public static void main(String[] args) throws IOException {
		File file = new File("D:/work/IO.txt");
		FileReader fr = new FileReader(file);
		BufferedReader bffr = new BufferedReader(fr);
		while(bffr.ready()) {
		//버퍼레디 : 읽을 준비(파일에 읽을것이 있는지 확인하고 있으면 true,없으면 false
			System.out.println(bffr.readLine());
		}
		bffr.close();
		//연습문제)파일에 기록된 문자데이터를 화면에 출력하시오
		//중간단계 미션) 입력한것을 파일로 저장하는..? ui로 메모장수준의 에디터...
		//활용문제) 뱅크에 사용자(회원) 데이터를 파일로 기록하는 프로그램을 작성하시오(영속성)
		//규칙을 정해야함
		//맵방식을 써야하나
	}

}
