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
	public EchoClient(String host, int port) throws IOException { //생성자에서 서버에 접속해야하므로 서버 ip(host)와 port가 필요하다
		socket = new Socket(host, port); //서버와 다르게 그냥소켓이용
	}
	public void echo() throws IOException {
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		BufferedReader in = new BufferedReader
				(new InputStreamReader(is));//
		PrintWriter out = new PrintWriter(os, true); //true = append(계속 추가하겠다
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
			try {//프로그램 아규먼트 (String[] args) - 프로그램을 실행할 때 넘겨주는 값(배열안에 파라미터값이 있을때)
				EchoClient ec;
				System.out.println("메시지를 입력하세요");
				if(args.length > 0) {
					ec = new EchoClient(args[0], 1289);//입력한 ip가 있을때
				}else {
					ec = new EchoClient("localhost", 1289);
				}
				ec.echo();
				ec.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
