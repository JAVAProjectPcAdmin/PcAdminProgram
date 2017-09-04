package orderFood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import com.sun.prism.Graphics;

public class OrderGUI extends JFrame {
	private JPanel ramenPnl, drinkPnl, snackPnl, selectPnl, menuPnl, labelPnl;
	private JButton orderBtn, cancleBtn;
	private JTabbedPane menuTab;
	private JLabel priceLabel;

	public OrderGUI() {
		ramenPnl = new JPanel();
		drinkPnl = new JPanel();
		snackPnl = new JPanel();
		selectPnl = new JPanel();
		menuPnl = new JPanel();

		orderBtn = new JButton("ÁÖ¹®");
		cancleBtn = new JButton("Ãë¼Ò");

		// ¸Þ´ºÅÇ
		menuTab = new JTabbedPane();
		menuTab.addTab("   ¶ó   ¸é   ·ù   ", ramenPnl);
		menuTab.addTab("   À½   ·á   ¼ö   ", drinkPnl);
		menuTab.addTab("   ½º   ³¼   ·ù   ", snackPnl);
		menuTab.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		// menuTab.setTabPlacement(JTabbedPane.LEFT);

		menuPnl.add(menuTab);
		menuPnl.setBounds(0, 0, 1000, 600);
		menuPnl.setBackground(Color.WHITE);
		menuPnl.setLayout(new FlowLayout());

		// menuPnl.setPreferredSize(new Dimension(400, 300));

		// °áÁ¦, Ãë¼Ò
		orderBtn = new JButton(" ÁÖ ¹® ");
		cancleBtn = new JButton(" Ãë ¼Ò ");
		orderBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		cancleBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		orderBtn.setBackground(Color.WHITE);
		cancleBtn.setBackground(Color.WHITE);
		orderBtn.setFocusPainted(false);
		cancleBtn.setFocusPainted(false);

		// selectPnl.setPreferredSize(new Dimension(0, -300));
		selectPnl.setBackground(Color.RED);
		// selectPnl.add(priceLabel);
		selectPnl.add(orderBtn);
		selectPnl.add(cancleBtn);
		selectPnl.setBounds(0, 500, 1000, 400);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/// ÃÑ »ç¿ë±Ý¾×
		labelPnl = new JPanel();
		priceLabel = new JLabel("ÃÑ ±Ý¾× : ");
		
		labelPnl.add(priceLabel);
		labelPnl.setBounds(0, 400, 1000, 100);
		

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		add(menuPnl);
		add(labelPnl);
		add(selectPnl);

		// setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		// setLayout(null);
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		// setUndecorated(true);
		setLocation(150, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		OrderGUI o = new OrderGUI();
	}
}
