package userUsingState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserUsingState extends JFrame {
	private JLabel timeLb, moneyLb, talkLb, orderLb, informationLb, customerNumberLb;
	private JButton talkBt, orderBt, informationBt;
	private JPanel panel, grayPanel;
	BufferedImage panelImg = null;

	public UserUsingState() {
		setLayout(null);
		setLocation(900, 50); // ������ġ ���� �޼ҵ�
		setSize(310, 280);
		setResizable(false);
		setUndecorated(true); // ������â ���κ� ���� �޼ҵ�

		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.WHITE);

		try {
			panelImg = ImageIO.read(new File("graypanel.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		grayPanel = new grayPanel();
		grayPanel.setLayout(null);

		customerNumberLb = new JLabel("001");
		timeLb = new JLabel("00:00");
		moneyLb = new JLabel("0��");
		talkLb = new JLabel("����");
		talkLb.setFont(new Font("���� ���", Font.BOLD, 10));
		orderLb = new JLabel("�ֹ�");
		orderLb.setFont(new Font("���� ���", Font.BOLD, 10));
		informationLb = new JLabel("�������");
		informationLb.setFont(new Font("���� ���", Font.BOLD, 10));

		talkBt = new JButton(new ImageIcon("talk.png"));
		orderBt = new JButton(new ImageIcon("order.png"));
		informationBt = new JButton(new ImageIcon("information.png"));

		grayPanel.setBounds(10, 80, 283, 84);
		customerNumberLb.setBounds(50, 10, 50, 50);

		grayPanel.add(timeLb);
		grayPanel.add(moneyLb);

		timeLb.setBounds(40, 20, 30, 30);
		moneyLb.setBounds(40, 50, 30, 30);
		add(grayPanel);

		setVisible(true);

	}

	class grayPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(panelImg, 0, 0, null);
		}
	}

	public static void main(String[] args) {
		new UserUsingState();
	}

}
