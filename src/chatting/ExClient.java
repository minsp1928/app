package chatting;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List; //////////////////////////////util이 아닌 awt
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
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

	public class ExClient extends JFrame
	implements Runnable, ActionListener{//클라이언트
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
	
	private List user;

	
	public ExClient(String server) {		//생성자
		super("채팅프로그램");//타이틀
		host = server;
		listener = new Thread(this);
		listener.start();
		output = new JTextArea();//텍스트에리어 객체 생성
/**/	jp = new JScrollPane(output);//여기서 jp를 선언했기때문에
		output.setEditable(false);//문장입력을 막아준것.(채팅내용이 뜨는곳)
/**/	jb =  jp.getVerticalScrollBar(); //스크롤팬에 근본을 두고 스크롤바를 조정할 수 있따.
/**/	add(jp, "Center");//jp 객체로 유지가 가능하다
		Panel bottom = new Panel(new BorderLayout());//동서남북
		label = new JLabel("사용자 이름");
		bottom.add(label,"West");
		input = new JTextField();
		bottom.add(input, "Center");
		input.addActionListener(this);//엔터 입력이 들어오면 엑션퍼퓸드가 실행?
		add(bottom,"South");
		//--------------------------altus2
		user =new List();
		add(user, "East");
		//-------------------------------
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);//창, 프로세스를 끄는것
		setSize(400, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		
		if(args.length > 0) {
			new ExClient(args[0]);////-----------
		}else {
			new ExClient("locallhost");///-------------------
		}

	}//end of main
	public void run() {
		try {
			Socket s = new Socket("localhost", 7980);
//			Socket s = new Socket("172.30.1.7", 7979);
			InputStream ins = s.getInputStream();
			OutputStream os = s.getOutputStream();
			i = new BufferedReader(new InputStreamReader(ins));
			o = new PrintWriter(new OutputStreamWriter(os),true);//이상한거 임포트되어있었음
			while(true) {
				String line = i.readLine();
				StringTokenizer st= new StringTokenizer(line, "#");//딜리미터를 이용해서 split, stringtokenizer로
				output.append(st.nextToken() + "\n");//첫번째는 무조건 메세지, 나머지는 이름
				user.removeAll();//*서버에서 보내는 이름만 add (나머지는 지운것 꼭 지워줘야한다.)
				while(st.hasMoreElements()){//넘어온 데이터를 가공하고 리스트에 추가한다. user은 리스트*/
					user.add(st.nextToken());

				}
//가공			output.append(line+"\n");//텍스트에리어에 넣어줌
/**/			jb.setValue(jb.getMaximum());//=의도 : 스크롤바를 조정하겠다 (위는 미니멈, 밑은 맥시멈)
			}
		} catch (Exception e) {//언노운호스트익셉셤
		System.out.println(e.getMessage());
		}
	}//end of run
	
	//텍스트 필드에 글 입력, 엔터를 눌렀을때 액션
		//컨테이너, 컴포넌트(컨테이너의 역할이 안되는 버튼(텍스트필드에 없는)
		//ex) window, frame 보통 인터페이스는 하나의 메소드(기능)밖에 없어요?액션 퍼폼드? 
	public void actionPerformed (ActionEvent e) {
		Object c = e.getSource(); //getsourse로 이벤트를 발생 객체를 다 얘가 받아?
		if(c == input) {
			label.setText("메시지");//락으로 바꿔서 ?
			o.println(input.getText()); //텍스트 필드에 입력한 값을 보내라
			input.setText("");
		}
	}//end of actionPerformed


}//end of class
