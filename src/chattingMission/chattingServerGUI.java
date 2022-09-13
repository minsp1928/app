package chattingMission;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class chattingServerGUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea(40,25);//채팅기록
	private	JTextField jtf = new JTextField(25);//채팅창
	private chattingServer server = new chattingServer();
	
	public chattingServerGUI() throws IOException {
		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200,100,400,600);
		setTitle("서버부분");
		
		server.setGui(this);
		server.setting();
	}
	
	public static void main(String[] args) throws IOException {
		new chattingServerGUI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String msg ="서버 : "+jtf.getText() +"\r\n";
		System.out.print(msg);
		server.sendMessage(msg);
		jtf.setText("");
		jta.append("\n"+msg);
	}

	public void appendMsg(String msg) {
		jta.append("\n"+msg);
	}

}
