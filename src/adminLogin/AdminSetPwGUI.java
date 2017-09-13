package adminLogin;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import db.AdminDao;
import db.AdminVO;

public class AdminSetPwGUI extends JFrame {
	private JPanel panel, keyIcon, adminIcon;
	private JLabel oldLabel, newLabel, compareLabel, idLabel;
	private JButton updateButton, cancelButton;
	private JTextField IdTxf;
	private JPasswordField oldPWf, newPWf, comparePWf;
	BufferedImage adminImg, keyImg = null;

	private AdminLoginGUI adminLoginGui;
	private AdminDao dao;

	public AdminSetPwGUI() {
		setLocation(350, 250);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(600, 300);
		setTitle("Admin Settings");
		setUndecorated(true);

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

		idLabel = new JLabel("������ ID");
		idLabel.setFont(new Font("���� ���", Font.BOLD, 14));
		oldLabel = new JLabel("���� ��й�ȣ");
		oldLabel.setFont(new Font("���� ���", Font.BOLD, 14));
		newLabel = new JLabel("���ο� ��й�ȣ");
		newLabel.setFont(new Font("���� ���", Font.BOLD, 14));
		compareLabel = new JLabel("��й�ȣ Ȯ��");
		compareLabel.setFont(new Font("���� ���", Font.BOLD, 14));

		IdTxf = new JTextField();
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
		IdTxf.setBounds(290, 95, 250, 30);
		oldPWf.setBounds(290, 125, 250, 30);
		newPWf.setBounds(290, 155, 250, 30);
		comparePWf.setBounds(290, 185, 250, 30);
		idLabel.setBounds(170, 85, 200, 50);
		oldLabel.setBounds(170, 115, 200, 50);
		newLabel.setBounds(170, 145, 200, 50);
		compareLabel.setBounds(170, 175, 200, 50);

		adminIcon.setBounds(180, 35, 328, 50);
		keyIcon.setBounds(100, 15, 110, 90);
		updateButton.setBounds(340, 230, 75, 25);
		cancelButton.setBounds(430, 230, 75, 25);

		panel.add(updateButton);
		panel.add(cancelButton);
		panel.add(IdTxf);
		panel.add(oldPWf);
		panel.add(newPWf);
		panel.add(comparePWf);
		panel.add(idLabel);
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
			dao = new AdminDao();
			AdminVO vo = new AdminVO();

			if (e.getSource() == cancelButton) {
				dispose();
			}
			if (e.getSource() == updateButton) {
				String adminId = IdTxf.getText();
				String oldPasswd = new String(oldPWf.getPassword(), 0, oldPWf.getPassword().length);
				String newPasswd = new String(newPWf.getPassword(), 0, newPWf.getPassword().length);
				String comparePasswd = new String(comparePWf.getPassword(), 0, comparePWf.getPassword().length);
				vo.setPassword(newPasswd);
				if (adminId.equals(dao.AdminIdSelect(adminId))) {
					if (oldPasswd.equalsIgnoreCase(dao.AdminPWSelect(adminId))) {

						if (newPasswd.equalsIgnoreCase(comparePasswd)) {
							if (newPasswd.equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "���ο� ��й�ȣ�� �Է����ּ���.");
							} else {
								dao.adminUpdate(vo, adminId);
								JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�.");
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� Ȯ�����ּ���.");
						}

					} else {
						JOptionPane.showMessageDialog(null, "���� ��й�ȣ�� Ʋ���ϴ�.");
					}

				} else {
					JOptionPane.showMessageDialog(null, "������ ID�� Ȯ�����ּ���.");
				}

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
