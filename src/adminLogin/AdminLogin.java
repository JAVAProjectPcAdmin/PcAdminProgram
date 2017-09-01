package adminLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AdminLogin extends JFrame {
	private JPanel panel, computerIcon, adminIcon;
	private JLabel idLabel, pwLabel;
	private JButton loginButton, setButton;
	private JTextField idTf;
	private JPasswordField pwTf;
	BufferedImage adminLoginImg, computerImg = null;

	public AdminLogin() {
		setLocation(400,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 300);
		setTitle("Admin Login");

		try {
			adminLoginImg = ImageIO.read(new File("adminlogin.png"));
			computerImg = ImageIO.read(new File("computer.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel = new JPanel();
		TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK), "Admin Login");
		panel.setBorder(tb);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		adminIcon = new adminIcon();
		computerIcon = new computerIcon();

		pwLabel = new JLabel("Password");
		pwLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		idTf = new JTextField();
		pwTf = new JPasswordField();

		loginButton = new JButton(new ImageIcon("loginbt.png"));
		loginButton.setFocusPainted(false);
		setButton = new JButton(new ImageIcon("adminbt.png"));
		setButton.setFocusPainted(false);

		panel.setBounds(0, 0, 600, 300);
		idTf.setBounds(320, 150, 250, 30);gjdfosgjsodfgodj
		pwTf.setBounds(320, 180, 250, 30);
		idLabel.setBounds(200, 140, 50, 50);
		pwLabel.setBounds(200, 170, 200, 50);
		adminIcon.setBounds(180, 60, 328, 50);
		computerIcon.setBounds(70, 50, 97, 70);
		loginButton.setBounds(320, 230, 118, 27);
		setButton.setBounds(450, 230, 123, 27);

		panel.add(loginButton);
		panel.add(setButton);
		panel.add(idTf);
		panel.add(pwTf);
		panel.add(idLabel);
		panel.add(pwLabel);
		panel.add(adminIcon);
		panel.add(computerIcon);
		add(panel);

		setVisible(true);

	}

	class adminIcon extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(adminLoginImg, 0, 0, null);
		}

	}

	class computerIcon extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(computerImg, 0, 0, null);
		}

	}

	public static void main(String[] args) {
		new AdminLogin();

	}
}
