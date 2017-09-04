package project;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class UserManagedFrame extends JFrame{
	UserPanel panel[] =new UserPanel[25];
	public UserManagedFrame() {
		setLayout(new GridLayout(5, 5));
		
		for(int i=0;i<25;i++) {
			panel[i]=new UserPanel();
			panel[i].setSize(100,100);
				add(panel[i]);
			}
			
			setSize(900,500);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
	}
	public static void main(String[] args) {
		new UserManagedFrame();
	}
}
