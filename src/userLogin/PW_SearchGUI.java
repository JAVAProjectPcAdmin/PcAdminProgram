package userLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PW_SearchGUI extends JFrame implements ActionListener {
	private JTextField nameTf, p_NumberTf, emailTf;
	private JLabel searchLabel, idLabel, residentLabel, emailLabel;
	private JPanel panel, searchIcon;
	private JButton cancleButton, checkButton;
	private JPasswordField residentPf;
	BufferedImage searchImg = null;

	public PW_SearchGUI() {
		setTitle("PW Search");
		setLayout(null);
		setLocation(400, 300);
		setSize(400, 420);
		setResizable(false);
		setAlwaysOnTop(true);

		try {
			searchImg = ImageIO.read(new File("Search.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setSize(400, 420);

		searchIcon = new SearchIdPw();
		searchLabel = new JLabel("Password ã��");
		searchLabel.setFont(new Font("���� ����", Font.BOLD, 30));
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("���� ����", Font.BOLD, 20));
		residentLabel = new JLabel("�ֹε�Ϲ�ȣ");
		residentLabel.setFont(new Font("���� ����", Font.BOLD, 20));
		emailLabel = new JLabel("�̸��� �ּ�");
		emailLabel.setFont(new Font("���� ����", Font.BOLD, 20));

		nameTf = new JTextField();
		p_NumberTf = new JTextField();
		emailTf = new JTextField();
		
		residentPf = new JPasswordField();

		cancleButton = new JButton(new ImageIcon("cancle.png"));
		cancleButton.setFocusPainted(false);
		checkButton = new JButton(new ImageIcon("ok.png"));
		checkButton.setFocusPainted(false);

		searchIcon.setBounds(70, 20, 64, 64);
		searchLabel.setBounds(140, 30, 250, 50);
		idLabel.setBounds(30, 140, 50, 50);
		residentLabel.setBounds(30, 190, 200, 50);
		emailLabel.setBounds(30, 240, 200, 50);
		nameTf.setBounds(170, 150, 200, 30);
		p_NumberTf.setBounds(170, 200, 200, 30);
		emailTf.setBounds(170, 250, 200, 30);
		cancleButton.setBounds(200, 340, 118, 28);
		checkButton.setBounds(80, 340, 118, 28);

		panel.add(searchIcon);
		panel.add(searchLabel);
		panel.add(idLabel);
		panel.add(residentLabel);
		panel.add(emailLabel);
		panel.add(nameTf);
		panel.add(p_NumberTf);
		panel.add(emailTf);
		panel.add(cancleButton);
		panel.add(checkButton);
		add(panel);

		setVisible(true);
	}

	class SearchIdPw extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(searchImg, 0, 0, null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new PW_SearchGUI();
	}
}
