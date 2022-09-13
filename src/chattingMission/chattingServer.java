package chattingMission;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class chattingServer {

	//----
	private ServerSocket server;
	private Socket socket;
	private chattingServerGUI gui;
	private String msg;
	private static Map<String, PrintWriter> membersMap = new HashMap<String,PrintWriter>();
	
	public final void setGui(chattingServerGUI gui) {
		this.gui = gui;
	}
	
	public void setting() throws IOException {
		Collections.synchronizedMap(membersMap);//동기화처리(교통정리?
		server = new ServerSocket(7777);
			while (true) {
				System.out.println("server ready...");	
				socket = server.accept();//서버에 소켓을 통해 접근하면
				System.out.println(socket.getInetAddress()+"에서 접속했습니다.");
				Receiver receiver = new Receiver(socket);
				receiver.start();
				
		}
	}
	public static void main(String[] args)throws IOException {
		chattingServer chattingServer = new chattingServer();
		chattingServer.setting();
	}
	public static void addClient(String nick, PrintWriter out) throws IOException{
		 sendMessage(nick + "님이 접속 하셨습니다.");
		membersMap.put(nick,out);
	}
	public static void removeClient(String nick) {
		sendMessage(nick+"님이 나가셨습니다.");
		membersMap.remove(nick);
	}
	public static void sendMessage(String msg) {
		Iterator<String> it = membersMap.keySet().iterator();
		String key = "";
		while(it.hasNext()) {
			key = it.next();
			try {
				membersMap.get(key).write(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//-----------------thread
	class Receiver extends Thread {
		private String nick;
		private BufferedReader in;
		private PrintWriter out;
		public Receiver(Socket socket) throws IOException {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			InputStreamReader isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			out = new PrintWriter(os,true);
			nick = in.readLine();
			addClient(nick, out);
		}
		public void run() {
			try {
				while(in!=null) {
					msg = in.readLine();
					sendMessage(msg);
					gui.appendMsg(msg);
				}
			} catch (IOException e) {
				removeClient(nick);
			}
		}
	}

}//chattingServer 끝
