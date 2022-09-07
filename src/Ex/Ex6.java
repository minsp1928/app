package Ex;

import java.awt.Frame;
import java.awt.*; // 자동완성이 안돼서 그냥 수기로 쳤따.
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Ex6 extends Frame{
public  Ex6() {
	super("GUI");
 
	this.setSize(300, 300);
	this.setVisible(false);
	setResizable(false);//크기바꿀 수 없게
	this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
			
		}	
	});

	}
}
