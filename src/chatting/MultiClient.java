package chatting;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/*import java.io.ObjectOutputStream; ->이거 뭐야*/
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiClient extends JFrame 
	implements Runnable, ActionListener{//클라이언트
	//JFrame : gui를 쓰겠다
	//Runnable : 스레드를 표현하겠다
	private BufferedReader i;
	private PrintWriter o;
	private JTextArea output;//여러줄
	private JTextField input;//한 줄
	private JLabel label;
	private Thread listener; //인스턴스 생성(멤버변수)_
	private String host; //(서버)
	private JScrollPane jp;//미션1
	private JScrollBar jb;//미션1

	
	public MultiClient(String server) {//생성자
		super("채팅프로그램");//타이틀 : JFrame을 상속받은것
		host = server;
		listener = new Thread(this); 
		listener.start(); //맨밑의 run이 실행됨(스레드의 메서드)
		output = new JTextArea();//텍스트에리어 객체 생성
		
/*미션1*/jp = new JScrollPane(output);//여기서 jp를 선언했기때문에
/*미션1*/jb =  jp.getVerticalScrollBar(); //스크롤팬에 근본을 두고 스크롤바를 조정할 수 있따.
/*미션1*/add (jp, "Center");//jp 객체로 유지가 가능하다

	//	add (new JScrollPane(output), "Center");//패널을 먼저 넣고 텍스트에리어를 붙인것.(원래코드)

		//다이렉트로 붙여도 되지만 따로하면 스크롤바를 인스턴스를 생성해서 내가 핸들링할 수 있다
		//스스로 도움말을 볼줄알고 객체를 만들어 찾아서 핸들링할 수 있눈게 미션 : 객체지향적
		//힌트 : 스크롤바 객체, 객체를 내가 적절한곳에 넣어서 핸들링 
		output.setEditable(false);//문장입력을 막아준것.(채팅내용이 뜨는곳이므로)
		Panel bottom = new Panel(new BorderLayout());//동서남북 입력할 곳을 지정?
		label = new JLabel("사용자 이름");
		bottom.add(label,"West");
		input = new JTextField();//메세지를 입력하는 창
		bottom.add(input, "Center");
		input.addActionListener(this);//엔터 입력이 들어오면 엑션퍼퓸드가 실행!
		add(bottom,"South");
		setDefaultCloseOperation(EXIT_ON_CLOSE);//창, 프로세스를 끄는것(서버종료용)
		setSize(400, 300);
		setVisible(true);
	}
	
	public void run() {
		try {
			Socket s = new Socket("localhost", 7979);//내 주소
//			Socket s = new Socket("172.30.1.7", 7979);
			InputStream ins = s.getInputStream();
			OutputStream os = s.getOutputStream();
			i = new BufferedReader(new InputStreamReader(ins));
			o = new PrintWriter(new OutputStreamWriter(os),true);//이상한거 임포트되어있었음
			while(true) {
				String line = i.readLine();
				output.append(line+"\n");//텍스트에리어에 넣어줌
/*미션1*/		jb.setValue(jb.getMaximum());//=의도 : 스크롤바를 조정하겠다 (위는 미니멈, 밑은 맥시멈)
/*미션1_2*/		output.setCaretPosition(output.getText().length()); 
				//ㄴ위이이이의 코드없이 이 한 줄로도 스크롤바를 맨밑에 유지 가능
			}
		} catch (Exception e) {//언노운호스트익셉셤
		System.out.println(e.getMessage());
		}
	}//end of run
	
	/*60번라인에서 addActionListener를 실행했을때*/ 
	//텍스트 필드에 글 입력, 엔터를 눌렀을때 액션
		//컨테이너, 컴포넌트(컨테이너의 역할이 안되는 버튼(텍스트필드에 없는)
		//ex) window, frame 보통 인터페이스는 하나의 메소드(기능)밖에 없어요?액션 퍼폼드? 
	public void actionPerformed (ActionEvent e) {//이벤트 처리하는곳~
		Object c = e.getSource(); //getsourse로 이벤트를 발생 객체를 다아 얘가 받아?
		if(c == input) { //겟소스로 이벤트 발생객체를 갖고오는데 input과 주소가 같을 때 메시지를 보여라
			//스위치 케이스같은것, case = input일때~ 같은 조건을 걸어준것
			label.setText("메시지");//락으로 바꿔서 ?
			o.println(input.getText()); //텍스트 필드에 입력한 값을 보내라
			input.setText("");//텍스트필드 비우기(메세지를 작성후 엔터를 누르는 순간)
		}
	}//end of actionPerformed
	public static void main(String[] args) {
		/*
		 * JScrollPane jp = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 * jp.getHorizontalScrollBar().setUnitIncrement(10);
		 * jp.getVerticalScrollBar().setUnitIncrement(10);
		 */
		
		
		if(args.length > 0) {
			new MultiClient(args[0]);
		}else {
			new MultiClient("locallhost");
		}

	}//end of main

}//end of class
