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

import db.UserDao;

public class ID_SearchGUI extends JFrame implements ActionListener {
	private JTextField nameTf, p_NumberTf, emailTf;
	private JLabel searchLabel, nameLabel, p_numberLabel, emailLabel, resultLabel;
	private JPanel panel, searchIcon;
	private JButton cancleButton, checkButton;

	BufferedImage searchImg = null;

	public ID_SearchGUI() {
		setTitle("ID Search");
		setLayout(null);
		setLocation(400, 300);
		setSize(400, 450);
		setResizable(false);
		setAlwaysOnTop(true);

		try {
			searchImg = ImageIO.read(new File("images//Search.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkListener listener = new checkListener();
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setSize(400, 420);

		searchIcon = new SearchIdPw();
		searchLabel = new JLabel("ID Ã£±â");
		searchLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		nameLabel = new JLabel("ÀÌ ¸§");
		nameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		p_numberLabel = new JLabel("ÈÞ´ëÆù ¹øÈ£");
		p_numberLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		emailLabel = new JLabel("ÀÌ¸ÞÀÏ ÁÖ¼Ò");
		emailLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));

		nameTf = new JTextField();
		p_NumberTf = new JTextField();
		emailTf = new JTextField();

		cancleButton = new JButton(new ImageIcon("images//cancle.png"));
		cancleButton.setFocusPainted(false);
		checkButton = new JButton(new ImageIcon("images//ok.png"));
		checkButton.setFocusPainted(false);

		searchIcon.setBounds(80, 40, 64, 64);
		searchLabel.setBounds(170, 40, 200, 50);
		
		
		nameLabel.setBounds(40, 147, 50, 30);
		p_numberLabel.setBounds(40, 192, 200, 30);
		emailLabel.setBounds(40, 237, 200, 30);
		
		nameTf.setBounds(170, 147, 170, 30);
		p_NumberTf.setBounds(170, 192, 170, 30);
		emailTf.setBounds(170, 237, 170, 30);
		
		checkButton.setBounds(90, 300, 100, 26);
		cancleButton.setBounds(200, 300, 100, 26);
		resultLabel.setBounds(70, 340, 300, 50);
		
		checkButton.addActionListener(listener);
		cancleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		panel.add(searchIcon);
		panel.add(searchLabel);
		panel.add(nameLabel);
		panel.add(p_numberLabel);
		panel.add(emailLabel);
		panel.add(nameTf);
		panel.add(p_NumberTf);
		panel.add(emailTf);
		panel.add(cancleButton);
		panel.add(checkButton);
		panel.add(resultLabel);
		add(panel);

		setVisible(true);
	}

	class SearchIdPw extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(searchImg, 0, 0, null);
		}

	}

	class checkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UserDao dao = new UserDao();
			String id = dao.IdSearch(nameTf.getText(), emailTf.getText(), p_NumberTf.getText());

			if (id == null) {
				resultLabel.setText("ÀÔ·Â°ªÀ» ´Ù½Ã È®ÀÎÇØ ÁÖ¼¼¿ä.");
			} else {
				resultLabel.setText("¾ÆÀÌµð´Â *" + id + "* ÀÔ´Ï´Ù.");
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new ID_SearchGUI();
	}

}
