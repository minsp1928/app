package Ex;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*; // 자동완성이 안돼서 그냥 수기로 쳤따.
import java.awt.event.*;


public class Ex5 extends JFrame implements ActionListener{	
	//ActionListener:이벤트를 정의한 인터페이스 
	//버튼을 눌렀을때 그 창으로 넘어갈 수 있게(동적 페이지를 가져올 수 있는)이벤트를 적용시키기 위해 사용
	//must implement the inherited abstract method ActionListener.actionPerformed
	//-> 추상클래스를 상속받겠다했느데 정의를 안해줬을때 생기는 오류
	JLabel id_la, pw_la;
	//Label id_la, pw_la;
	JTextField id_tf, pw_tf;
	//TextField id_tf, pw_tf;
	JButton login, join;//뭔가 좀더 이쁜갑다 볼륨감, 그라이데이션?
	
	//Button login, join;
	Ex6 ex6 = new Ex6();
	public Ex5() {
		//GUI는 애플리케이션때 많이 사용한다.(웹에서는 덜쓰는편)
		super("GUI");
		id_la = new JLabel("ID");
		pw_la = new JLabel("PW");
		id_tf = new JTextField();
		pw_tf = new JTextField();
		login = new JButton("login"); 
		join = new JButton("join");
	/*	id_la = new Label("ID");
		pw_la = new Label("PW");
		id_tf = new TextField();
		pw_tf = new TextField();
		login = new Button();
		join = new Button();*/
		Panel p = new Panel();
		Panel p2 = new Panel(new GridLayout(2, 2));
		p.setLayout(new GridLayout(1, 2, 10, 10));//마진,패딩처럼..?
		p.add(login);
		p.add(join);
		p2.add(id_la);
		p2.add(id_tf); 
		p2.add(pw_la); 
		p2.add(pw_tf); 
		this.add(p, "South");//프레임은 동서남북 배치
		this.add(p2, "Center");
		//하나의 프레임 창안에 배치할 수 있는 컨테이너의 역할
		this.setSize(300, 100);//(가로,세로)자바x.swing= 자바에서 확장됨 즉, this는 다 찾아내는것.
		this.setResizable(false);//크기조정 막는것
		this.setVisible(true);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		login.addActionListener(this);//이벤트 감청자(사용자가 이벤트를 작동하는지 확인)
		//this는 오버라이딩된actionPerformed를 의미
		
	}
	//추상클래스 인터페이스예제였는데 아닌듯
	
//	public class Ex5 extends JFrame{
	//콘솔버전이 아닌 GUI를 보여줄 수 있다?
/*	public JTextField id, pw;
	public Ex5() {
		super("GUI");
		id = new JTextField("id");
		pw = new JTextField("pw");
		JPanel p = new JPanel();
		p.add(id);
		p.add(pw); 
		this.add(p);
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//서버 끝내는것.
	}*/
	public static void main(String[] args) {
		new Ex5();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("됐나여?" +id_tf.getText());
		//로그인 버튼을 누르면 콘솔에 아이디에 적은 값을 보여주면서
		id_tf.setText("");// 텍스트 창의 값은 지워준다
	//	new Ex6();//조건에 맞으면 ex6실행?//누를때마가 생성됨..
		ex6.setVisible(true);//
		this.setVisible(false);//첫 gui창이 꺼진것
		ex6.dispose();//지정된 프레임을 종료시킨다
		
	}

}
