package chattingMission;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ClientTest extends Frame implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	//디자인 먼저 하기
	private TextArea ta = new TextArea();
	private TextField tf = new TextField();
	
	private Socket socketClient = null;
	//private MulticastSocket ms = null;
	private InetAddress xGroup = null;
	
private String host = "172.30.1.54";
	//private String host = "172.30.1.7";
	private int port = 7777;
	
	public ClientTest() {
		
		ta.setEditable(false);
		
		add(ta, BorderLayout.CENTER);
		add(tf, BorderLayout.SOUTH);
		
		tf.addActionListener(this);
		
		//종료버튼
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
		
		//setTitle("Chatting Client");//채팅 클라이언트
		setTitle("Group Client [" + host + "]");
		setSize(300, 400);
		setVisible(true);
		tf.requestFocus();
	}
	
	@SuppressWarnings("deprecation")
	public void connect() {
		try {
			xGroup = InetAddress.getByName(host);
			socketClient = new Socket(host, port);
			//ms = new MulticastSocket(port);
			//ms.joinGroup(xGroup);
			
			Thread th = new Thread(this);
			th.start();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
//	public void disConnect() {
//		try {
//			ms.leaveGroup(xGroup);
//			ms.close();
//			
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//	}
	
	public static void main(String[] args) {

		new ClientTest().connect();
	}

	@Override
	public void run(){
		String str;
		
		try {
			
			if(socketClient == null) 
				return;
				//clientInput();
				InputStream is = socketClient.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				while((str = br.readLine()) != null) {
					ta.append("\r\n" + str);				
				}
				
		} catch (Exception e) {
			ta.append("\r\n Server 연결 종료!");
			socketClient = null;
		}
		
		try {
			while(true) {
				
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			//disConnect();
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str = tf.getText();
		
		//검사
		if(str.trim().equals("")) {
			
			return;
		}
		
		if(socketClient == null) {
			
			return;
		}
		
		try {
			OutputStream os = socketClient.getOutputStream();
			PrintWriter out = new PrintWriter(os, true);
			
			out.println("나영 ]" + str);//내보내기-> client에게 전송//print()만 쓰면 데이터 안나감
			
			ta.append("\r\n나영 ]" + str);//"\r\n나영]"
			tf.setText("");
			tf.requestFocus();
			
		} catch (Exception e2) {
			ta.append("\r\n Server Connection is end...");//client와 접속이 끊겼습니다
			System.out.println(e.toString());
			socketClient = null;
			//disConnect();
		
		}
	}
}
