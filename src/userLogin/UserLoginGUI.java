package userLogin;

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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.LongToIntFunction;

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

import db.UserDao;
import db.UserVO;

import userUsingState.UserUsingStateGUI;

import userUsingState.UserUsingStateGUI;

import AdminServer.User;

public class UserLoginGUI extends JFrame {
	private JPanel panel, pcNumPanel, plzLogin, padIcon;
	private JLabel idLabel, pcNumLabel, pwLabel, nonMembersLabel;
	private JButton loginButton, signInButton, idSearchButton, pwSearchButton;
	private JTextField idTf, nonMemberTf;
	private JPasswordField pwTf;
	BufferedImage userLoginImg = null;
	public static User user = null;
	
	InetAddress ip = null;
	
	public UserLoginGUI() {
		setSize(1280, 1024);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("User Login");

		try {
			userLoginImg = ImageIO.read(new File("images//plzlogin.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
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

		pwLabel = new JLabel("Password");
		pwLabel.setFont(new Font("���� ���", Font.BOLD, 22));
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("���� ���", Font.BOLD, 22));
		nonMembersLabel = new JLabel("��ȸ��");
		nonMembersLabel.setFont(new Font("���� ���", Font.BOLD, 22));
		pcNumLabel = new JLabel("1");
		pcNumLabel.setFont(new Font("���� ���", Font.BOLD, 140));
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
		pcNumLabel.setBounds(60, 40, 100, 100);
		plzLogin.setBounds(250, 40, 328, 50);

		loginButton.setBounds(250, 230, 100, 27);
		signInButton.setBounds(360, 230, 100, 27);
		signInButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserJoinGUI join = new UserJoinGUI();
			}
		});
		idSearchButton.setBounds(470, 230, 94, 28);

		idSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ID_SearchGUI search = new ID_SearchGUI();

			}

		});
		pwSearchButton.setBounds(470, 260, 94, 28);

		pwSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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

		setVisible(true);
	}

	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			String name = null;
		
			UserDao dao = new UserDao();
			try {
				InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			if (idTf.getText().length() > 0) {

				int result = dao.UserLoginCheck(idTf.getText(), new String(pwTf.getPassword()));
				if (result == 1) {
					System.out.println("�α��� ����");
					name=dao.UserNameSelect(idTf.getText());
					user = new User(name);
					user.setUserNumber(dao.UserNumberSelect(idTf.getText())+"");
					user.setUserID(idTf.getText());
					user.setBirthYear(dao.UserBirthSelect(idTf.getText()));
					UserClient userclient = new UserClient();
					dispose();
					UserUsingStateGUI uus = new UserUsingStateGUI(userclient);
				} else if (result == 0) {
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.", "�н����� ����", JOptionPane.OK_OPTION);
				} else {
					System.out.println("���̵� �����ϴ�.");
					JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�.", "���̵� ����", JOptionPane.OK_OPTION);
				}
			} else if (nonMemberTf.getText().length() > 0) {
				int result = dao.nonMemberLoginCheck(nonMemberTf.getText());
				if (result == 1) {
					System.out.println("�α��� ����");
					name = dao.nonMemberIdSelect(nonMemberTf.getText());
					user = new User(name);
					user.setUserNumber(dao.NonMemberNumSelect(nonMemberTf.getText()));
					user.setUserID(nonMemberTf.getText());
					UserClient userclient = new UserClient();
					dispose();
					UserUsingStateGUI uus = new UserUsingStateGUI( userclient);
				} else if (result == 0) {
					System.out.println("������� ��ȣ�Դϴ�.");
					JOptionPane.showMessageDialog(null, "������� ��ȣ.", "����", JOptionPane.OK_OPTION);
				} else {
					System.out.println("���̵� �����ϴ�.(��ȸ��)");
					JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�.", "����", JOptionPane.OK_OPTION);
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
