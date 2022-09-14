package chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

public class MultiServer { //서버
//	벡터는 동기화가 되어있는 컬렉션이다, 객체의 삽입, 삭제, 이동이 편함
	private Vector handlers;//소켓을 객체화해서 관리할 객체
	//(여기에 handler를 넣어서 객체를 관리한 객체!)
	public MultiServer(int port) throws IOException{ //생성자
		try {
			ServerSocket server = new ServerSocket(port);//서버기때문에 서버소켓, ip노 포트번호만
			handlers = new Vector();
			System.out.println("chatServer is ready");
			while(true) {
				MultiHandlerThread handler = new MultiHandlerThread(this, server.accept()); //반환된 소켓들을 객체에 넣는것
				//사용자 접근,요청(너하고 접속할래~)이 들어오면 스레드를 새로 생성 : 웹과 같은 방식.
				//ㄴ통신할 수 있는 소켓을 반환
				handler.start();
				//별도의 스레드가 필요해 왜? 소켓만 반복하니까. multi객체에 extends해줄거임
				//클라이언트의 접근을 기다리겠다 : 데먼스레드역할?->프로그램이 종료되기전까지 실행됨
				}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}//end of 생성자
		public Object getHandler(int index) {
			return handlers.elementAt(index);//컬렉션은 get보다는 요소를 수집한다가 어울려(get으로 바꿔도 됨
		}
		//특정된 인덱스를 통해 소켓을 가져와서 그 객체(multi)를 가져오는것
		public void register(MultiHandlerThread c) {
			handlers.addElement(c);//저장(객체에 저장한 정보를 핸들러스라는 컬렉션에 관리용으로 저장)
		}
		public void unregister(Object o) {
			handlers.removeElement(o);//삭제
		}
	//	public synchronized void broadcast (String message) {///메소드자체에 동기화
		public void broadcast (String message) {
			//메세지가 오면 모든 사용자에게 방송해~ -> 1. 인원파악 2 . 객체에서 인원만큼 소켓을 꺼내옴
			synchronized (handlers){/*동기화처리 혹은 특정된영역에 처리가능 () */
			//ㄴ동기화되는것중에 동시에 일어나는 일중 한쪽을 wating을 해줘서 처리. block처리?
			//ㄴ동시에 일어나는일이 있겠다 싶으면 동기화. 
			int n = handlers.size();
			for(int i=0; i < n; i++) {
				//반복문-> 1. 인원파악 2 . 객체에서 인원만큼 소켓을 꺼내옴
				MultiHandlerThread h  = (MultiHandlerThread)handlers.elementAt(i); //i 인덱스의 요소를 가져오겠다.
				try {					//
					h.println(message); //소켓으로 writing : 모든 사용자에게 메세지보낸다.(출력용 메서드)
				//	h.write(message); //println = write
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
				
		}
				
			
	}//end of broadcast
			
	public static void main(String[] args) throws IOException {
		new MultiServer(7979); //객체생성
	}//end of main

}//end of class
