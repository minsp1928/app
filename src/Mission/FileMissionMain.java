package Mission;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class MissionFrame extends JFrame{
	
	public MissionFrame() {
		setTitle("아자아자 파일을 저장하자 미션");
		FileMenubar();
		JPanel panel = new JPanel();
		
		JTextArea txtArea = new JTextArea();//텍스트를 입력받는곳
		txtArea.setEditable(false);//시작할 때, 텍스트아리아 비활성화(입력받을 수 있는 컴포넌트에만 해당)
		//파일다일로그
		
		
		panel.setLayout(new BorderLayout());
		panel.add(txtArea,BorderLayout.CENTER);//패널의 가운데에 텍스트아리아가 배치되었다.
		
		add(panel);//안해주면 패널 보더레이아웃이 먹지 않는다.
		
		setSize(600, 350);//사이즈 설정
		setResizable(false);//사이즈 조절 기능 막음
		setLocationRelativeTo(null);//화면 중앙에 창띄우기
		setDefaultCloseOperation(EXIT_ON_CLOSE);//창을 끌때 프로그램 끝내기
		
		
		
		setVisible(true);//창이 보이게 설정
		
	}//end of missionFrame
	 void FileMenubar() {
		JMenuBar mb = new JMenuBar();//메뉴바 생성
		JMenu fileMenu = new JMenu("File");
		JMenu fileMenuEx = new JMenu("FileEx");
		
		mb.add(fileMenu);
		
		JMenuItem [] mi = new JMenuItem[5];
		mi[0] = new JMenuItem("New File");
		mi[1] = new JMenuItem("Open File");
		mi[2] = new JMenuItem("Save File");
		mi[3] = new JMenuItem("Save As");
		mi[4] = new JMenuItem("Exit");
		
		/*
		 * m for(int i = 0; i< mi.length; i++) { for(int i = 0; i< mi.length; i++) { if(
		 * i!= 2 && i !=(mi.length - 1)) //
		 * 
		 * }
		 */
		mi[1].setEnabled(false);//비활성화(그냥 해본것)
			/////
			
		fileMenu.add(mi[0]);
		fileMenu.addSeparator();//분리선
		fileMenu.add(mi[1]);
		fileMenu.addSeparator();//분리선
		fileMenu.add(mi[2]);
		fileMenu.add(mi[3]);
		fileMenu.addSeparator();//분리선
		fileMenu.add(mi[4]);
		
		MenuActionListner listner = new MenuActionListner();
	
		for(int i = 0; i<mi.length; i++) {
			mi[i].addActionListener(new MenuActionListner());
			
		}
		
		
		/*
		fileMenu.add(new JMenuItem("New File")); //메뉴"File"에 메뉴아이템을 추가
		fileMenu.addSeparator();//분리선
		fileMenu.add(new JMenuItem("Open File"));
		fileMenu.addSeparator();//분리선
		fileMenu.add(new JMenuItem("Save File"));
		fileMenu.add(new JMenuItem("Save As"));
		fileMenu.addSeparator();//분리선
		fileMenu.add(new JMenuItem("Exit"));
		*/


		this.setJMenuBar(mb);//메인 프레임에 메뉴바를 붙임

	}//end of FileMenubar
	 
	 class MenuActionListner implements ActionListener { //이벤트 발생 유무를 검사하는 프로세스
		 public void actionPerformed(ActionEvent e) { //액션리스너(인터페이스)의 리스너 메소드
			 String cmd = e.getActionCommand();//메소드를 사용 발생된 이벤트 소스를 식별
			 // cmd : 파일메뉴에서 선택되는 값
			 switch (cmd) {
			 
			 case "Open File":
				 
				 break;
			 case "Exit":
				System.out.println("exit action working");
				System.exit(0);
				break;
			}
			
		}
	 }
}



public class FileMissionMain {

	public static void main(String[] args) {
		new MissionFrame();

	}

}
