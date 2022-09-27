package Ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Ex16IO {
	public static void main(String[] args) throws IOException {
			//InputStream, OutputStream
		File file = new File("C:/IO/test.txt");//파일다일로그를 띄우면 폴더 및 파일 선택을 하면 파일을 만들 수 있음
		//출력할때 파일이 없으면 에러/ 입력할때 파일이 있으면 만들어짐
		//출력목적: 파일에다가 시스템을 통해서 문자데이터를 파일에 저장하기
		//*문제가 왔을때 요리라고 생각하자. = 재료들을 준비, 필요한 공정을 순서대로 : 알고리즘
		//출력 file에 문자데이터를 넣을거야(txt파일에 기록) 그러면file writer
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		//버퍼의 특징 : 데이터를 담게 되면 따로 사용해~하는 명령어를 사용해야함 : flush
		PrintWriter pw = new PrintWriter(bw, true);//true : outo flush
		//writer(최상위) 상속 다형?다양성?
		InputStream is =System.in;//system :키보드같은 입력장치 
		//is.read를 이용해서 값을 가져오면 바이트로 가져옴 쓸 수는 있지만 좀 불편해 가공하는게 좋아
		InputStreamReader isr = new InputStreamReader(is);//얘가 필터
		BufferedReader br = new BufferedReader(isr);
		System.out.println("입력 : ");
		String str ="";
		//파일라이트의 어펜드 파일스트림계열중 파일롸이터의 어펜드 : ex 메뉴
		//스트링버퍼
		//루프돌려서 있는 만큼 돌리기
		while(!str.equals("end")) { //end를 치면 반복중단, 파일로 저장d
			str = br.readLine();
			pw.println(str);//여기서 파일이 저장되는것
		}
		br.close();
		pw.close();
	}
}
