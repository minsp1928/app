package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	//소켓:콘센트의 소켓이미지 = 꽂아지는 순간 연결:통신가능
	private ServerSocket server; //소켓을 이용해서 서버 구현
	public EchoServer(int port) throws IOException {//생성자 ip가 필요하지않아
		server = new ServerSocket(port);//이미 상용화된 포트번호 사용X, 1024범위이상으로 만들기권장
		//포트만 임의로 만들어주는데 ip는 필요없다
		//채팅이 왜 네트워크에서 중요한가? 다중채팅*(멀티채팅) 채팅이 네트워크 원리를 잘 설명하고 있다.
		//한 사람이 메세지를 여럿에게 보낼때? → 직접 메세지가 가는게 아니라 서버에 접속
		//접속된 호스트들에게 브로드캐스팅하는것
	}
	//얘만 실핼될 데먼?스레드
	//여러사용자들의 소켓을 관리할 수 있지않을까..?->미션 : 스레드를 이용
	public void service()  throws IOException {//io응용프로그램 입출력장치로 목적지를 정하는 개념
		/*스레드,컬렉션*/	
		Socket client = server.accept();//여기만 따로 정의 해준다면 여러명이 가능하지 않을까/대기상태(블로킹) 스레드의 개념,
		//사용자가 접근할 때(연결할때, 소켓이 연결될때 io 내려감, = 근데 이 예제는 한명만 가능
		//ip와 포트정보를 알아야 서버를 들어갈 수 있다. 
		//서버 소켓에는 accept란 메소드 : 통신할 수 있는 도구를 만드는것.
		//클라이언트가 서버의 아이피를 알고 접근 시도, accept를 통해 통신할 수 있는 소켓을 반환하는것
		//접속된 소켓들을 어딘가에 담아두면 브로드캐스트가 가능하다.
		InputStream is = client.getInputStream();//문자데이터를 사용할건데 바이트임
		OutputStream os = client.getOutputStream();//소켓을 통해서 바이트를 입출력을 받는 인스턴스는 알아야
		InputStreamReader isr = new InputStreamReader(is);//여기서 문자로 받아
		BufferedReader in = new BufferedReader(isr);//필터스트림으로 변환을 시켜주는것?
		PrintWriter out = new PrintWriter(os, true);
	//	BufferedReader con = new BufferedReader(new InputStreamReader(System.in)); 필요없는 이유 : 서버는 그냥 요청만
		//(양쪽으로 하고싶다면 순환 배치가 반대로 되게?출입구/입출구
		while(true) {//데이터 주고받기 ( 메아리식으로 클라이언트만 말하는것)
			String msg = in.readLine(); 
			System.out.println(msg); //서버콘솔에 넣음
			if(msg.equals("bye")) {
				break;//bye를 치면 종료하겠다. = 나가기전까진 통신
			}
			out.println(">>"+msg);//메세지를 보냄/서블릿과 같은 것 원격지에 데이터를 보내는것 서버를 갔다왔다.
		}
	}//end of service
	public void close() throws IOException {
		server.close();//꼭 해줄것
		
	}//end of close
	public static void main(String[] args) {
		try {
			EchoServer es = new EchoServer(1289);
			es.service();
			es.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of main
}//end of class
