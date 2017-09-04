package adminMain;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminMainGUI extends JFrame {
	public AdminMainGUI() {
		LeftMainGUI lmp = new LeftMainGUI();
		RightMainGUI[] rightUserPanel=new RightMainGUI[25];
		JPanel rightPanel= new JPanel();

		lmp.setBounds(0, 80, 220, 850);

		rightPanel.setLayout(new GridLayout(5, 5));

		for (int i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210,170);
			rightPanel.add(rightUserPanel[i]);
		}
		rightPanel.setBounds(230,90,1030,800);
		add(rightPanel);
		
		
		add(lmp);
		setResizable(false);

		setLayout(null);
		setSize(1280, 924);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("관리자 화면");

	}

	public static void main(String[] args) {
		AdminMainGUI admin = new AdminMainGUI();

	}

}
