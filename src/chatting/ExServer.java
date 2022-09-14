package chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

public class ExServer {//server

	private Vector handlers;
	public ExServer(int port) throws IOException{
		try {
			ServerSocket server = new ServerSocket(port);
			handlers = new Vector();
			System.out.println("chatServer is ready Ex24");
			while(true) {
				ExHandler handler = new ExHandler(this, server.accept());
				handler.start();//////안넣었었음
				}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}//end of 생성자
		public Object getHandler(int index) {//핸들러객체 안에 해당 소켓을 갖고와서 통신 - 특정 사용자 통신
			//요소를 수집한다 라는 의미의 컬렉션은 element = get
			return handlers.elementAt(index);
		}

		public void register(ExHandler c) {
			handlers.addElement(c);//저장
		}
		public void unregister(Object o) {
			handlers.removeElement(o);//클라이언트 나가면 삭제
		}

		public void broadcast (String message) {
		
			synchronized (handlers){
			int n = handlers.size();
			StringBuffer sb = new StringBuffer();/////////////////////
			//처음에 자른것은 데이터로, 넥스트 토큰은 이름으로 만듦
				for(int i=0; i < n; i++) {
					//반복문-> 1. 인원파악 2 . 객체에서 인원만큼 소켓을 꺼내옴
					ExHandler h  = (ExHandler)handlers.elementAt(i);//object로 나오니까 강제casting
					sb.append("#" + h.getUser());// #/$ 데이터를 가공해서 쓰겠다.
					//핸들러에서 유저라는 이름을 가져온것임
				}
				for(int i=0; i < n; i++) {
					ExHandler h  = (ExHandler)handlers.elementAt(i);
					try {			
						h.println(message + sb.toString()); 
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
			
			}
				
		
	}//end of broadcast
	
	public static void main(String[] args) throws IOException {
		new ExServer(7980);
	}

}
