package adminMain;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class administer_MainFrame extends JFrame {
	public administer_MainFrame() {
		Left_MainPanel lmp = new Left_MainPanel();

		lmp.setBounds(0, 80, 220, 850);

		add(lmp);
		setResizable(false);

		setLayout(null);
		setSize(1280, 924);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("관리자 화면");

	}

	public static void main(String[] args) {
		administer_MainFrame admin = new administer_MainFrame();

	}

}
