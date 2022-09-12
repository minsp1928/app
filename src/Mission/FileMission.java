package Mission;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

class FileMission extends JFrame implements ActionListener{
	
	
	public void FileMission() throws IOException {
		setVisible(true);//창이 보이게 설정
		setSize(500, 200);//사이즈 설정
		setResizable(false);//사이즈 조절 기능 막음
		setLocationRelativeTo(null);//화면 중앙에 창띄우기
		setDefaultCloseOperation(EXIT_ON_CLOSE);//창을 끌때 프로그램 끝내기
		
		
		File file = new File("D:/work/FileMission.txt");//파일위치,파일명.형태
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw,true);//오토플러시 작업
	
		//save버튼을 눌러야 저장이 된다.
		
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		//데이터 입력받는 코드
		System.out.println("입력 : "); 
		String str ="";
		while(!str.equals("end")) {//읽어오는 과정
			str = br.readLine();//textarea에 들어갈 내용
			pw.println(str);
		} 
		br.close();
		pw.close();
	}//FileMission()끝
	public static void main(String[] args) throws IOException {
		
		new FileMission();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
