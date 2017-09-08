package adminLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import adminLogin.AdminLoginGUI.LoginListener;
import adminLogin.AdminLoginGUI.adminIcon;
import adminLogin.AdminLoginGUI.adminSetPwListener;
import adminLogin.AdminLoginGUI.computerIcon;
import adminMain.AdminMainGUI;
import db.AdminDao;

public class AdminSetPwGUI extends JFrame {
	private JPanel panel, keyIcon, adminIcon;
	private JLabel oldLabel, newLabel, compareLabel;
	private JButton updateButton, cancelButton;
	private JPasswordField oldPWf, newPWf, comparePWf;
	BufferedImage adminImg, keyImg = null;

	AdminLoginGUI adminLoginGui;

	public AdminSetPwGUI() {
		setLocation(350, 250);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 300);
		setTitle("Admin Settings");

		try {
			adminImg = ImageIO.read(new File("images//adminsettings.png"));
			keyImg = ImageIO.read(new File("images//setpw.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		panel = new JPanel();
		TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK), "Admin Settings");
		panel.setBorder(tb);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		adminIcon = new adminIcon();
		keyIcon = new keyIcon();

		oldLabel = new JLabel("기존 비밀번호");
		oldLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		newLabel = new JLabel("새로운 비밀번호");
		newLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		compareLabel = new JLabel("비밀번호 확인");
		compareLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

		oldPWf = new JPasswordField();
		newPWf = new JPasswordField();
		comparePWf = new JPasswordField();

		updateButton = new JButton(new ImageIcon("images//updatebtn.png"));
		updateButton.setFocusPainted(false);
		updateButton.addActionListener(new checkListener());

		cancelButton = new JButton(new ImageIcon("images//cancelbtn.png"));
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new checkListener());

		panel.setBounds(0, 0, 600, 300);
		oldPWf.setBounds(290, 120, 250, 30);
		newPWf.setBounds(290, 150, 250, 30);
		comparePWf.setBounds(290, 180, 250, 30);
		oldLabel.setBounds(170, 110, 200, 50);
		newLabel.setBounds(170, 140, 200, 50);
		compareLabel.setBounds(170, 170, 200, 50);

		adminIcon.setBounds(180, 60, 328, 50);
		keyIcon.setBounds(100, 40, 110, 90);
		updateButton.setBounds(340, 230, 75, 25);
		cancelButton.setBounds(430, 230, 75, 25);

		panel.add(updateButton);
		panel.add(cancelButton);
		panel.add(oldPWf);
		panel.add(newPWf);
		panel.add(comparePWf);
		panel.add(oldLabel);
		panel.add(newLabel);
		panel.add(compareLabel);
		panel.add(adminIcon);
		panel.add(keyIcon);
		add(panel);

		setVisible(true);

	}

	class checkListener implements ActionListener {

		boolean checkFlag = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			AdminDao dao = new AdminDao();

			if (e.getSource() == cancelButton) {
				dispose();
			}
			if (e.getSource() == updateButton) {
				String oldPasswd = new String(oldPWf.getPassword(), 0, oldPWf.getPassword().length);
				String newPasswd = new String(newPWf.getPassword(), 0, newPWf.getPassword().length);
				String comparePasswd = new String(comparePWf.getPassword(), 0, comparePWf.getPassword().length);

//				if (oldPasswd.equalsIgnoreCase(adminLoginGui.adminPw)) {
//					if (newPasswd.equalsIgnoreCase(comparePasswd)) {
//						adminLoginGui.adminPw = newPasswd;
//						JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.");
//					} else {
//						JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주십시오.");
//					}
//
//				} else {
//					JOptionPane.showMessageDialog(null, "기존 비밀번호가 틀립니다.");
//				}
				
				//여기

			}
		}
	}

	class adminIcon extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(adminImg, 0, 0, null);
		}
	}

	class keyIcon extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(keyImg, 0, 0, null);
		}

	}

	public static void main(String[] args) {
		new AdminSetPwGUI();

	}

}
