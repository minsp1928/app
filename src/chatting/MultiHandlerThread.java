package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiHandlerThread extends Thread{ //핸들러
	private Socket s;//소켓을 준비?
	private BufferedReader i;
	private PrintWriter o;
	private MultiServer server;
	
	public MultiHandlerThread(MultiServer server, Socket s) throws Exception {
		this.server = server;
		this.s=s;
		InputStream ins = s.getInputStream();
		OutputStream os = s.getOutputStream();
		
		InputStreamReader isr = new InputStreamReader(ins);
	//	InputStreamReader isr = new InputStreamReader(s.getInputStream());
		OutputStreamWriter osw = new OutputStreamWriter(os);
		o = new PrintWriter(osw, true);
		i = new BufferedReader(isr);
		
	}
	public void run() {//서버의 핸들러.스타트메서드 실행시 런 실행
		String name ="";//사용자 이름
		try {
			name = i.readLine();//여기서 이름을 한 번만 입력할것.
		//	broadcast ( name + "님이 방문하셨습니다.") -> 나빼고 메세지 (레지스터위냐 아래의 차이일뿐)
			server.register(this);//객체에 이름을 저장 -> 브로드캐스트하기전에 실행
			broadcast (name + "님이 방문하셨습니다.");//->나도 보여 메세지
			while(true) {
				String msg = i.readLine(); //메세지를 읽어와서 뿌려주기위한 반복문
				broadcast (name + " - " + msg);
			}
		} catch (Exception e) {
			
			//e.printStackTrace(); -> 이거 주면 빨간 글 나와서 뺌
			server.unregister(this); //try문 바깥에 있었음 //제거용
			broadcast(name+"님이 나가셨습니다.");
			try {//시스템의 안정성을 고려해 클로즈도 순서대로(예외를 막아줄 수 있는 최소한의 태도)
				i.close();
				o.close();
				s.close();
			} catch (IOException e2) {
				
			}
		}
		
	}//end of run
	protected void println(String message) {
		//멤버를 위에서 프린트라이트를 만들어줬으니까?
		o.println(message); // println = printwrite(파일)기능은 같아도 의미적으로 접근용~
	}
	protected void broadcast (String message) {//화면에 메세지를 뿌려주기위한 메서드
		server.broadcast(message);
	}//end of broadcast

}
