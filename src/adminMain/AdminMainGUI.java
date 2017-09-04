package adminMain;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
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
			rightPanel.addMouseListener(new PopupListener());
		}
		rightPanel.setBounds(230,90,1030,800);
		add(rightPanel);
		
		
		add(lmp);
		setResizable(false);
///////////////////////////////////////////////////////////////////////////////////////////////////////
		 popup = new JPopupMenu();
		JMenuItem menuChat = new JMenuItem("대화걸기");
		JMenuItem menuLogout = new JMenuItem("로그아웃");
		popup.add(menuChat);
		popup.add(menuLogout);
//		menuChat.addActionListener(this);
		
		

		setLayout(null);
		setSize(1280, 924);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("관리자 화면");
		getContentPane().setBackground(Color.WHITE);

	}
	

	public static void main(String[] args) {
		AdminMainGUI admin = new AdminMainGUI();

	}
	
	class PopupListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			showPopup(e);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}
		private void showPopup(MouseEvent e) {
			if(e.isPopupTrigger()) {
				popup.show(e.getComponent(),e.getX(),e.getY());
			}
		}
	}

}
