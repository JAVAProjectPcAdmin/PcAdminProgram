package adminLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
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

import AdminServer.AdminServer;
import ChattingServer.Server;
import adminMain.AdminMainGUI;
import db.AdminDao;
import db.AdminVO;
import db.UserDao;

public class AdminLoginGUI extends JFrame {
	private JPanel panel, computerIcon, adminIcon;
	private JLabel idLabel, pwLabel;
	private JButton loginButton, setButton, registerButton,outButton;
	private JTextField idTf;
	private JPasswordField pwTf;
	BufferedImage adminLoginImg, computerImg = null;
	
	ImageIcon exitIcon = new ImageIcon("images//exit.png");
	Image exitButton = exitIcon.getImage();
	Image newExitButtonImg = exitButton.getScaledInstance(25, 23, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chExitButton = new ImageIcon(newExitButtonImg);

	public AdminLoginGUI() {
		setLocation(350, 350);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 300);
		setTitle("Admin Login");
		setUndecorated(true);

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

		registerButton = new JButton(new ImageIcon("images//registeradmin.png"));
		registerButton.setFocusPainted(false);
		registerButton.addActionListener(new adminRegisterListener());

		setButton = new JButton(new ImageIcon("images//adminbt.png"));
		setButton.setFocusPainted(false);
		setButton.addActionListener(new adminSetPwListener());
		
		outButton = new JButton(chExitButton);
		outButton.setFocusPainted(false);
		outButton.setBorderPainted(false);
		outButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		panel.setBounds(0, 0, 600, 300);
		idTf.setBounds(320, 140, 250, 30);
		pwTf.setBounds(320, 180, 250, 30);
		idLabel.setBounds(200, 130, 50, 50);
		pwLabel.setBounds(200, 170, 200, 50);
		adminIcon.setBounds(180, 60, 328, 50);
		computerIcon.setBounds(70, 50, 97, 70);
		
		loginButton.setBounds(250, 230, 90, 27);
		registerButton.setBounds(350, 230, 90, 27);
		setButton.setBounds(450, 230, 90, 27);
		outButton.setBounds(565, 15, 23, 21);
		
		panel.add(outButton);
		panel.add(loginButton);
		panel.add(registerButton);
		panel.add(setButton);
		panel.add(idTf);
		panel.add(pwTf);
		panel.add(idLabel);
		panel.add(pwLabel);
		panel.add(adminIcon);
		panel.add(computerIcon);
		add(panel);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image Iconimg = toolkit.getImage("images\\networking.png");
		setIconImage(Iconimg);
		
		setVisible(true);
		
<<<<<<< HEAD
		new AdminServer();
=======
		 new AdminServer();
		 
>>>>>>> b88b7656577328e031891462b1bdd0d7f1e4b84e
	}

	// 관리자 로그인
	class LoginListener implements ActionListener {
		AdminVO adminVo = new AdminVO();
		AdminDao adminDao = new AdminDao();

		@Override
		public void actionPerformed(ActionEvent e) {
			String adminId = idTf.getText();
			String adminPw = new String(pwTf.getPassword(), 0, pwTf.getPassword().length);

			if (e.getSource() == loginButton) {

				if (adminId.equals(adminDao.AdminIdSelect(adminDao.AdminIdSelect(adminId)))) {
					if (adminPw.equals(adminDao.AdminPWSelect(adminId))) {
						dispose();
						new AdminMainGUI();
					
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "관리자ID를 다시 확인해주세요.");
				}

			}

			// 로그인 만들기

		}
	}

	// 관리자 비밀번호 변경
	class adminSetPwListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AdminSetPwGUI adminSetPw = new AdminSetPwGUI();

		}

	}

	// 관리자 등록
	class adminRegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AdminJoinGUI adminRegister = new AdminJoinGUI();

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
