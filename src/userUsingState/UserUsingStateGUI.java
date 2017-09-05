package userUsingState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

import orderFood.OrderGUI;

public class UserUsingStateGUI extends JFrame {
	private JLabel timeLb, moneyLb, talkLb, orderLb, informationLb, customerNumberLb;
	private JButton talkBt, orderBt, informationBt;
	private JPanel panel, grayPanel;
	BufferedImage panelImg = null;

	public UserUsingStateGUI() {
		setLayout(null);
		setLocation(950, 50); // ������ġ ���� �޼ҵ�
		setSize(310, 280);
		setResizable(false);
		setUndecorated(true); // ������â ���κ� ���� �޼ҵ�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.WHITE);

		panel = null;
		try {
			panel = new JPanelWithBackground("graypanel.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 400);

		customerNumberLb = new JLabel("001");
		customerNumberLb.setFont(new Font("���� ���", Font.BOLD, 40));
		timeLb = new JLabel("00:00");
		moneyLb = new JLabel("0��");
		talkLb = new JLabel("����");
		talkLb.setFont(new Font("���� ���", Font.BOLD, 13));
		orderLb = new JLabel("�ֹ�");
		orderLb.setFont(new Font("���� ���", Font.BOLD, 13));
		informationLb = new JLabel("�������");
		informationLb.setFont(new Font("���� ���", Font.BOLD, 13));

		talkBt = new JButton(new ImageIcon("talk.png"));
		talkBt.setBorderPainted(false);

		talkBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TalkGUI talk = new TalkGUI();
			}
		});
		orderBt = new JButton(new ImageIcon("order.png"));
		orderBt.setBorderPainted(false);
		informationBt = new JButton(new ImageIcon("information.png"));
		informationBt.setBorderPainted(false);
		informationBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChargeInformationGUI ci = new ChargeInformationGUI();
			}
		});

		panel.setBounds(10, 80, 283, 84);
		customerNumberLb.setBounds(40, 0, 100, 100);
		talkBt.setBounds(50, 180, 44, 32);
		orderBt.setBounds(130, 180, 38, 29);
		orderBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OrderGUI order = new OrderGUI();
			}
		});
		informationBt.setBounds(210, 180, 42, 36);
		talkLb.setBounds(55, 210, 50, 50);
		orderLb.setBounds(137, 210, 50, 50);
		informationLb.setBounds(208, 210, 70, 50);

		panel.add(timeLb);
		panel.add(moneyLb);

		timeLb.setBounds(90, 10, 70, 30);
		moneyLb.setBounds(90, 45, 700, 30);

		add(talkBt);
		add(orderBt);
		add(informationBt);
		add(talkLb);
		add(orderLb);
		add(informationLb);
		add(customerNumberLb);
		add(panel);

		setVisible(true);

	}

	class JPanelWithBackground extends JPanel { // �гο� �̹��� ä��Ⱑ �ƴ� �̹����� ��׶���� �������� Ŭ����

		private Image backgroundImage;

		public JPanelWithBackground(String fileName) throws IOException {
			backgroundImage = ImageIO.read(new File("graypanel.png"));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(backgroundImage, 0, 0, null);
		}
	}

	public static void main(String[] args) {
		new UserUsingStateGUI();
	}

}
