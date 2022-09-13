package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	private Socket socket; //서버가 아닌 소켓을 준비
	public EchoClient(String host, int port) throws IOException { //생성자에서 서버에 접속해야하므로 서버 ip와 port가 필요하다
		socket = new Socket(host, port);
	}
	public void echo() throws IOException {
		OutputStream os = socket.getOutputStream();//소켓을 통해서 바이트를 입출력을 받는 인스턴스는 알아야
		InputStream is = socket.getInputStream();//문자데이터를 사용할건데 바이트임
		BufferedReader in = new BufferedReader
				(new InputStreamReader(is));//
		PrintWriter out = new PrintWriter(os, true);
		BufferedReader con = new BufferedReader
				(new InputStreamReader(System.in)); // 클라이언트에서 입력한 값이 필요
		while(true) {//데이터 주고받기 ( 메아리식으로 클라이언트만 말하는것)
			String msg = con.readLine(); //여기서 보냄 서버화면에 뜸
			out.println(msg); //system으로 쳐서 잘못나왔던
			if(msg.equals("bye")) {
				break;//bye를 치면 종료하겠다. = 나가기전까진 통신
			}
			System.out.println(in.readLine());//서버에서 받은것
		}
	}
	public void close() throws IOException {
		socket.close();
	}
	
	public static void main(String[] args) {
			try {//프로그램 아규먼트 (String[] args) - 프로그램을 실행할 때 넘겨주는 값
				EchoClient ec;
				System.out.println("메시지를 입력하세요");
				if(args.length > 0) {
					ec = new EchoClient(args[0], 7777);//입력한 ip가 있을때
				}else {
					ec = new EchoClient("localhost", 7777);
				}
				ec.echo();
				ec.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
