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
		setLocation(950, 50); // 시작위치 설정 메소드
		setSize(310, 280);
		setResizable(false);
		setUndecorated(true); // 윈도우창 윗부분 제거 메소드
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
		customerNumberLb.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		timeLb = new JLabel("00:00");
		moneyLb = new JLabel("0원");
		talkLb = new JLabel("문의");
		talkLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		orderLb = new JLabel("주문");
		orderLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		informationLb = new JLabel("요금정보");
		informationLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));

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

	class JPanelWithBackground extends JPanel { // 패널에 이미지 채우기가 아닌 이미지를 백그라운드로 쓰기위한 클래스

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
