package userUsingState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AdminServer.User;
import userLogin.UserLoginGUI;

public class ChargeInformationGUI extends JFrame {

	private JLabel titleLabel, nameLabel, rs_nameLabel, startLabel, rs_startLabel, chargeLabel, rs_chargeLabel,
			orderChargeLabel, rs_orderChargeLabel, sumLabel, rs_sumLabel;
	private JPanel panel, moneyBagIcon;
	private JButton exitButton;
	private JTable chargeTable;

	BufferedImage moneybagImg = null;

	public ChargeInformationGUI() {
		setTitle("¿ä±ÝÁ¤º¸");
		setLayout(null);
		setLocation(950, 330);
		setSize(310, 370);
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);

		try {
			moneybagImg = ImageIO.read(new File("images//moneybag.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setSize(400, 420);

		exitButton = new JButton("È®ÀÎ");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUsingStateGUI.flag2 = false;
				dispose(); // Ã¢ÇÏ³ª¸¸ Á¾·á ÀüÃ¼Á¾·á´Â system.exit
			}
		});

		moneyBagIcon = new moenyBagIcon();
		titleLabel = new JLabel("¿ä±ÝÁ¤º¸");
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		nameLabel = new JLabel(" * ÀÌ¸§");
		nameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		startLabel = new JLabel(" * ½ÃÀÛ½Ã°£");
		startLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		chargeLabel = new JLabel(" * ½Ã°£ »ç¿ë ¿ä±Ý");
		chargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		orderChargeLabel = new JLabel(" * À½½ÄÁÖ¹®±Ý¾×");
		orderChargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		sumLabel = new JLabel(" * ÇÕ°è");
		sumLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		////////////////////////////////////////////////////////////////////////////////////
		rs_nameLabel = new JLabel(UserLoginGUI.user.getName());
		rs_nameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_startLabel = new JLabel(UserLoginGUI.user.getStartTime());
		rs_startLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_chargeLabel = new JLabel(Integer.toString(UserLoginGUI.user.getTotalPrice() - UserLoginGUI.user.getAddPrice()));
		rs_chargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_orderChargeLabel = new JLabel(Integer.toString(UserLoginGUI.user.getAddPrice()));
		rs_orderChargeLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rs_sumLabel = new JLabel(Integer.toString(UserLoginGUI.user.getTotalPrice()));
		rs_sumLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		/////////////////////////////////////////////////////////////////////////////////////
		exitButton.setBounds(190, 330, 100, 20);
		moneyBagIcon.setBounds(30, 10, 64, 64);
		titleLabel.setBounds(120, 13, 200, 50);
		nameLabel.setBounds(20, 110, 50, 50);
		startLabel.setBounds(20, 140, 100, 50);
		chargeLabel.setBounds(20, 170, 200, 50);
		orderChargeLabel.setBounds(20, 200, 200, 50);
		sumLabel.setBounds(20, 250, 80, 50);
		///////////////////////////////////////////////////////////////
		rs_nameLabel.setBounds(250, 110, 50, 50);
		rs_startLabel.setBounds(191, 140, 100, 50);
		rs_chargeLabel.setBounds(240, 170, 200, 50);
		rs_orderChargeLabel.setBounds(240, 200, 200, 50);
		rs_sumLabel.setBounds(220, 250, 80, 50);
		////////////////////////////////////////////////////////////////
		panel.add(nameLabel);
		panel.add(startLabel);
		panel.add(moneyBagIcon);
		panel.add(titleLabel);
		panel.add(chargeLabel);
		panel.add(orderChargeLabel);
		panel.add(sumLabel);
		//////////////////////////////////////////////////////////////
		panel.add(rs_nameLabel);
		panel.add(rs_startLabel);
		panel.add(rs_chargeLabel);
		panel.add(rs_orderChargeLabel);
		panel.add(rs_sumLabel);
		panel.add(exitButton);
		add(panel);

		setVisible(true);
	}

	class moenyBagIcon extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(moneybagImg, 0, 0, null);
		}
	}

}
