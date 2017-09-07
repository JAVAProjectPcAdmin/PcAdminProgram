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

public class AdminSetPwGUI extends JFrame {
	private JPanel panel, keyIcon, adminIcon;
	private JLabel oldLabel, newLabel, compareLabel;
	private JButton updateButton, cancelButton;
	private JPasswordField oldPWf, newPWf, comparePWf;
	BufferedImage adminImg, keyImg = null;

//	ImageIcon findButtonIcon = new ImageIcon("images//findseat.jpg");
//	Image findButton = findButtonIcon.getImage();
//	Image newFindButtonImg = findButton.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH);
//	ImageIcon chFindButton = new ImageIcon(newFindButtonImg);

	public AdminSetPwGUI() {
		setLocation(350, 250);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 300);
		setTitle("Admin Login");

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

		oldLabel = new JLabel("±âÁ¸ ºñ¹Ð¹øÈ£");
		oldLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		newLabel = new JLabel("»õ·Î¿î ºñ¹Ð¹øÈ£");
		newLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		compareLabel = new JLabel("ºñ¹Ð¹øÈ£ È®ÀÎ");
		compareLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
		
		oldPWf = new JPasswordField();
		newPWf = new JPasswordField();
		comparePWf = new JPasswordField();

		updateButton = new JButton(new ImageIcon("images//updatebtn.png"));
		updateButton.setFocusPainted(false);
		
		cancelButton = new JButton(new ImageIcon("images//cancelbtn.png"));
		cancelButton.setFocusPainted(false);

		panel.setBounds(0, 0, 600, 300);
		oldPWf.setBounds(290, 120, 250, 30);
		newPWf.setBounds(290, 150, 250, 30);
		comparePWf.setBounds(290, 180, 250, 30);
		oldLabel.setBounds(170, 110, 200, 50);
		newLabel.setBounds(170, 140, 200, 50);
		compareLabel.setBounds(170, 170, 200, 50);
		
		adminIcon.setBounds(180, 60, 328, 50);
		keyIcon.setBounds(100, 40, 110, 90);
		updateButton.setBounds(340, 230, 77, 27);
		cancelButton.setBounds(430, 230, 77, 27);

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
