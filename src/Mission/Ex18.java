package Mission;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JMenuItem;

public class Ex18 extends Frame implements ActionListener{
	public MenuBar mb;
	public Menu m;
	public MenuItem[] mi;
	public FileDialog fd_load, fd_save;
	public TextArea ta; //add
	//) 실습문제)
	/*
	 * 1.Exit 메누아이템 선택시 프로그램 종료.
	 */
	public  Ex18() {
		ta = new TextArea();
		ta.setEditable(false);//처음에 텍스트아리아 사용못하게 선언
		fd_load = new FileDialog(this, "파일 열기", FileDialog.LOAD);
		fd_save = new FileDialog(this, "파일 wjwkd", FileDialog.SAVE);
		mb = new MenuBar();
		m = new Menu("File");
		mi = new MenuItem[5];
		mi[0] = new JMenuItem("New File");
		mi[1] = new JMenuItem("Open File");
		mi[2] = new JMenuItem("Save File");
		mi[3] = new JMenuItem("Save As");
		mi[4] = new JMenuItem("Exit");
		for(int i = 0; i<mi.length; i++) {
			m.add(mi[i]);
			mi[i].addActionListener(this);
			if( i != 2 && i != (mi.length - 1))//마지막 줄 안적을라구
				m.addSeparator();//구분선
		}
		mi[i].setEnabled(false);
		md.add(m);
		setMenuBar(mb);
		add(ta,"Center");
		setSize(500, 500);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(mi[4])) {
			System.exit(0);
		}else if (obj == mi[0]) {
			mi[1].setEnabled(true);
			ta.setEditable(true);
		}else if (obj == mi[1]) {
			//Open File
			fd_load.setVisible(true);
//			fd_open.getDirectory()
//			fd_open.getFile()
			FileReader fr = null;
			BufferedReader br =null;
			ta.setEditable(true);
			ta.setText("");
			try {
				File file = new File(fd_load.getDirectory()
									+fd_load.getFile());//파일경로:파일주소/파일이름
				fr = new FileReader(file);
				br.
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
