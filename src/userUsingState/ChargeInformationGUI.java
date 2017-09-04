package userUsingState;

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
import javax.swing.JTextField;

public class ChargeInformationGUI extends JFrame implements ActionListener {

	private JLabel titleLabel, nameLabel,rs_nameLabel, startLabel,rs_startLabel, usingTimeLabel,rs_usingTimeLabel, chargeLabel,rs_chargeLabel, orderChargeLabel,rs_orderChargeLabel, sumLabel,rs_sumLabel;
	private JPanel panel, moenyBagIcon;

	BufferedImage moneybagImg = null;

	public ChargeInformationGUI() {
		setTitle("¿ä±ÝÁ¤º¸");
		setLayout(null);
		setLocation(400, 300);
		setSize(310, 400);
		setResizable(false);
		setAlwaysOnTop(true);

		try {
			moneybagImg = ImageIO.read(new File("moneybag.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setSize(400, 420);

		moenyBagIcon = new moenyBagIcon();
		titleLabel = new JLabel("¿ä±ÝÁ¤º¸");
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		nameLabel = new JLabel(" * ÀÌ¸§");
		nameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		startLabel = new JLabel(" * ½ÃÀÛ½Ã°£");
		startLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		usingTimeLabel = new JLabel(" * »ç¿ë ½Ã°£");
		usingTimeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		chargeLabel = new JLabel(" * ½Ã°£ »ç¿ë ¿ä±Ý");
		chargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		orderChargeLabel = new JLabel(" * À½½ÄÁÖ¹®±Ý¾×");
		orderChargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		sumLabel = new JLabel(" * ÇÕ°è");
		sumLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
////////////////////////////////////////////////////////////////////////////////////		
		rs_nameLabel = new JLabel(" * ÀÌ¸§");
		rs_nameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_startLabel = new JLabel(" * ½ÃÀÛ½Ã°£");
		rs_startLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_usingTimeLabel = new JLabel(" * »ç¿ë ½Ã°£");
		rs_usingTimeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_chargeLabel = new JLabel(" * ½Ã°£ »ç¿ë ¿ä±Ý");
		rs_chargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_orderChargeLabel = new JLabel(" * À½½ÄÁÖ¹®±Ý¾×");
		rs_orderChargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_sumLabel = new JLabel(" * ÇÕ°è");
		rs_sumLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
/////////////////////////////////////////////////////////////////////////////////////
		moenyBagIcon.setBounds(30, 10, 64, 64);

		titleLabel.setBounds(120, 13, 200, 50);
		nameLabel.setBounds(20, 110, 50, 50);
		startLabel.setBounds(20, 140, 100, 50);
		usingTimeLabel.setBounds(20, 170, 100, 50);
		chargeLabel.setBounds(20, 200, 200, 50);
		orderChargeLabel.setBounds(20, 230, 200, 50);
		sumLabel.setBounds(20, 280, 80, 50);
///////////////////////////////////////////////////////////////
		titleLabel.setBounds(120, 13, 200, 50);
		rs_nameLabel.setBounds(20, 110, 50, 50);
		rs_startLabel.setBounds(20, 140, 100, 50);
		rs_usingTimeLabel.setBounds(20, 170, 100, 50);
		rs_chargeLabel.setBounds(20, 200, 200, 50);
		rs_orderChargeLabel.setBounds(20, 230, 200, 50);
		rs_sumLabel.setBounds(20, 280, 80, 50);
////////////////////////////////////////////////////////////////		
		panel.add(nameLabel);
		panel.add(startLabel);
		panel.add(moenyBagIcon);
		panel.add(titleLabel);
		panel.add(usingTimeLabel);
		panel.add(chargeLabel);
		panel.add(orderChargeLabel);
		panel.add(sumLabel);

		add(panel);

		setVisible(true);
	}

	class moenyBagIcon extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(moneybagImg, 0, 0, null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new ChargeInformationGUI();
	}
}
