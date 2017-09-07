package adminLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import adminMain.AdminMainGUI;
import db.UserDao;

public class AdminLoginGUI extends JFrame {
	private JPanel panel, computerIcon, adminIcon;
	private JLabel idLabel, pwLabel;
	private JButton loginButton, setButton;
	private JTextField idTf;
	private JPasswordField pwTf;
	BufferedImage adminLoginImg, computerImg = null;

	private String adminId = "admin", adminPw = "sds1501";

	public AdminLoginGUI() {
		setLocation(400, 400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 300);
		setTitle("Admin Login");

		try {
			adminLoginImg = ImageIO.read(new File("images//adminlogin.png"));
			computerImg = ImageIO.read(new File("images//computer.png"));

		} catch (IOException e) {
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
		pwLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		idTf = new JTextField();
		pwTf = new JPasswordField();

		loginButton = new JButton(new ImageIcon("images//loginbt.png"));
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(new LoginListener());
		
		setButton = new JButton(new ImageIcon("images//adminbt.png"));
		setButton.setFocusPainted(false);
		setButton.addActionListener(new adminSetPwListener());

		panel.setBounds(0, 0, 600, 300);
		idTf.setBounds(320, 150, 250, 30);
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
	
	// 관리자 로그인
	class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String updatePasswd = new String(pwTf.getPassword(), 0, pwTf.getPassword().length);
			
			//로그인
			if (idTf.getText().equals(adminId)) {
				if(updatePasswd.equals(adminPw)) {
					System.out.println("로그인 성공");
					dispose();
					AdminMainGUI amg = new AdminMainGUI();
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호를 잘못 입력하셨습니다.");
				
				}
			}else {
				JOptionPane.showMessageDialog(null, "등록되지 않은 아이디이거나, 잘못 입력하셨습니다.");
			}
		
		}
	}
	
	// 관리자 설정
	class adminSetPwListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
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
		new AdminLoginGUI();

	}
}
