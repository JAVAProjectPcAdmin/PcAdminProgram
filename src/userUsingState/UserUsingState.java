package userUsingState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
		setLocation(900, 50); // ½ÃÀÛÀ§Ä¡ ¼³Á¤ ¸Þ¼Òµå
		setSize(310, 280);
		setResizable(false);
		setUndecorated(true); // À©µµ¿ìÃ¢ À­ºÎºÐ Á¦°Å ¸Þ¼Òµå

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
		customerNumberLb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		timeLb = new JLabel("00:00");
		moneyLb = new JLabel("0¿ø");
		talkLb = new JLabel("¹®ÀÇ");
		talkLb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 10));
		orderLb = new JLabel("ÁÖ¹®");
		orderLb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 10));
		informationLb = new JLabel("¿ä±ÝÁ¤º¸");
		informationLb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 10));

		talkBt = new JButton(new ImageIcon("talk.png"));
		orderBt = new JButton(new ImageIcon("order.png"));
		informationBt = new JButton(new ImageIcon("information.png"));

		panel.setBounds(10, 80, 283, 84);
		customerNumberLb.setBounds(40, 0, 100, 100);
		talkBt.setBounds(20, 100, 30, 30);

		panel.add(timeLb);
		panel.add(moneyLb);

		timeLb.setBounds(90, 10, 70, 30);
		moneyLb.setBounds(90, 45, 700, 30);
		add(talkBt);
		add(customerNumberLb);
		add(panel);

		setVisible(true);

	}

	class JPanelWithBackground extends JPanel {

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
		new UserUsingState();
	}

}
