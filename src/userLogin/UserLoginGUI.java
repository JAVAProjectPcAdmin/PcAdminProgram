package userLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AdminServer.User;
import db.UserDao;
import userUsingState.UserUsingStateGUI;

public class UserLoginGUI extends JFrame {
	private JPanel panel, pcNumPanel, plzLogin;
	private JLabel idLabel, pcNumLabel, pwLabel, nonMembersLabel, gameLabel;
	private JButton loginButton, signInButton, idSearchButton, pwSearchButton;
	private JTextField idTf, nonMemberTf;
	private JPasswordField pwTf;
	private BufferedImage userLoginImg = null;
	public static User user = null;
	private String myIp = null;
	private int seatNumber;
	private UserDao dao = new UserDao();

	public UserLoginGUI() {
		setSize(1280, 1024);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("User Login");

		try {
			userLoginImg = ImageIO.read(new File("images//plzlogin.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginListener listener = new LoginListener();

		panel = new JPanel();
		TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK), "User Login");
		panel.setBorder(tb);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		pcNumPanel = new JPanel();
		pcNumPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		pcNumPanel.setBackground(Color.WHITE);
		pcNumPanel.setLayout(null);
		plzLogin = new PlzLogin();

		// myip
		try {
			myIp = InetAddress.getLocalHost().getHostAddress();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		seatNumber = dao.SeatNumSelect(myIp);

		pwLabel = new JLabel("Password");
		pwLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		nonMembersLabel = new JLabel("비회원");
		nonMembersLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		pcNumLabel = new JLabel((seatNumber + 1) + "");
		pcNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pcNumLabel.setFont(new Font("맑은 고딕", Font.BOLD, 150));

		gameLabel = new JLabel(); //
		gameLabel.setIcon(new ImageIcon("images//game.png"));
		gameLabel.setBounds(280, 600, 400, 400);
		add(gameLabel);

		idTf = new JTextField();
		pwTf = new JPasswordField();
		nonMemberTf = new JTextField(null);
		loginButton = new JButton(new ImageIcon("images//loginbt.png"));
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(listener);

		signInButton = new JButton(new ImageIcon("images//sign.png"));
		signInButton.setFocusPainted(false);

		idSearchButton = new JButton(new ImageIcon("images//idsearch.png"));
		idSearchButton.setFocusPainted(false);
		pwSearchButton = new JButton(new ImageIcon("images//pwsearch.png"));
		pwSearchButton.setFocusPainted(false);

		panel.setBounds(650, 680, 600, 300);
		pcNumPanel.setBounds(20, 30, 200, 200);
		idTf.setBounds(320, 110, 250, 30);
		pwTf.setBounds(320, 142, 250, 30);
		nonMemberTf.setBounds(320, 190, 250, 30);
		idLabel.setBounds(200, 100, 50, 50);
		pwLabel.setBounds(200, 130, 200, 50);
		nonMembersLabel.setBounds(200, 180, 200, 50);
		pcNumLabel.setBounds(10, 5, 180, 180);
		plzLogin.setBounds(250, 40, 328, 50);

		loginButton.setBounds(250, 230, 100, 26);
		signInButton.setBounds(360, 230, 100, 26);
		signInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserJoinGUI join = new UserJoinGUI();
			}
		});
		idSearchButton.setBounds(470, 230, 94, 26);

		idSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ID_SearchGUI search = new ID_SearchGUI();

			}

		});
		pwSearchButton.setBounds(470, 260, 94, 26);

		pwSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PW_SearchGUI search = new PW_SearchGUI();
			}
		});

		panel.add(loginButton);
		panel.add(signInButton);
		panel.add(idSearchButton);
		panel.add(idTf);
		panel.add(pwTf);
		panel.add(nonMemberTf);
		panel.add(idLabel);
		panel.add(pwLabel);
		panel.add(nonMembersLabel);
		panel.add(plzLogin);
		panel.add(pwSearchButton);
		pcNumPanel.add(pcNumLabel);
		add(pcNumPanel);
		add(panel);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image Iconimg = toolkit.getImage("images\\pcIcon.png");
		setIconImage(Iconimg);

		getContentPane().setBackground(Color.WHITE);

		setVisible(true);
	}

	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String name = null;

			if (idTf.getText().length() > 0) {

				int result = dao.UserLoginCheck(idTf.getText(), new String(pwTf.getPassword()));
				if (result == 1) {
					System.out.println("로그인 성공");
					name = dao.UserNameSelect(idTf.getText());
					user = new User(name);
					user.setUserNumber(dao.UserNumberSelect(idTf.getText()) + "");
					user.setUserID(idTf.getText());
					user.setBirthYear(dao.UserBirthSelect(idTf.getText()));

					user.setSeatNumber(seatNumber);

					UserClient userclient = new UserClient();
					dispose();
					UserUsingStateGUI uus = new UserUsingStateGUI(userclient);
				} else if (result == 0) {
					System.out.println("비밀번호가 틀렸습니다.");
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "패스워드 오류", JOptionPane.OK_OPTION);
				} else {
					System.out.println("아이디가 없습니다.");
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.", "아이디 없음", JOptionPane.OK_OPTION);
				}
			} else if (nonMemberTf.getText().length() > 0) { // 비회원
				int result = dao.nonMemberLoginCheck(nonMemberTf.getText());
				if (result == 1) {
					System.out.println("로그인 성공");
					name = dao.nonMemberIdSelect(nonMemberTf.getText());
					user = new User(name);
					user.setUserNumber(dao.NonMemberNumSelect(nonMemberTf.getText()));
					user.setUserID(nonMemberTf.getText());
					user.setSeatNumber(seatNumber);
					UserClient userclient = new UserClient();
					dispose();
					UserUsingStateGUI uus = new UserUsingStateGUI(userclient);
				} else if (result == 0) {
					System.out.println("사용중인 번호입니다.");
					JOptionPane.showMessageDialog(null, "사용중인 번호.", "오류", JOptionPane.OK_OPTION);
				} else {
					System.out.println("아이디가 없습니다.(비회원)");
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.", "오류", JOptionPane.OK_OPTION);
				}
			}
		}
	}

	class PlzLogin extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(userLoginImg, 0, 0, null);
		}
	}

	public static void main(String[] args) {
		UserLoginGUI a = new UserLoginGUI();
	}
}
