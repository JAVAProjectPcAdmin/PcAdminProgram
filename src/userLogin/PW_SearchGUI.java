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

public class PW_SearchGUI extends JFrame implements ActionListener {
	private JTextField idTf, residentTf;
	private JLabel searchLabel, idLabel, residentLabel, resultLabel;
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
		searchLabel = new JLabel("Password Ã£±â");
		searchLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		residentLabel = new JLabel("ÁÖ¹Îµî·Ï¹øÈ£");
		residentLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));

		idTf = new JTextField();
		residentTf = new JTextField();
		residentPf = new JPasswordField();
		cancleButton = new JButton(new ImageIcon("images//cancle.png"));
		cancleButton.setFocusPainted(false);
		checkButton = new JButton(new ImageIcon("images//ok.png"));
		checkButton.setFocusPainted(false);

		searchIcon.setBounds(70, 20, 64, 64);
		searchLabel.setBounds(140, 30, 250, 50);
		idLabel.setBounds(30, 140, 50, 50);
		residentLabel.setBounds(30, 190, 200, 50);
		idTf.setBounds(170, 150, 200, 30);
		residentTf.setBounds(170, 200, 100, 30);
		cancleButton.setBounds(200, 340, 118, 28);
		checkButton.setBounds(80, 340, 118, 28);
		residentPf.setBounds(272, 200, 100, 30);
		resultLabel.setBounds(80, 240, 300, 50);

		checkButton.addActionListener(listener);
		cancleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		panel.add(searchIcon);
		panel.add(searchLabel);
		panel.add(idLabel);
		panel.add(residentLabel);
		panel.add(idTf);
		panel.add(residentTf);
		panel.add(cancleButton);
		panel.add(checkButton);
		panel.add(residentPf);
		panel.add(resultLabel);
		add(panel);

		setVisible(true);
	}

	class checkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UserDao dao = new UserDao();
			String pw = dao.PwSearch(idTf.getText(), residentTf.getText() + new String(residentPf.getPassword()));

			if (pw == null) {
				resultLabel.setText("ÀÔ·Â°ªÀ» ´Ù½Ã È®ÀÎÇØ ÁÖ¼¼¿ä.");
			} else {
				resultLabel.setText("ºñ¹Ð¹øÈ£´Â *" + pw + "* ÀÔ´Ï´Ù.");
			}
		}

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
