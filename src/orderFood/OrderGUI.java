package orderFood;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
		labelPnl = new JPanel();

		// ¸Þ´ºÅÇ
		menuTab = new JTabbedPane();
		menuTab.addTab("   ¶ó   ¸é   ·ù   ", ramenPnl);
		menuTab.addTab("   À½   ·á   ¼ö   ", drinkPnl);
		menuTab.addTab("   ½º   ³¼   ·ù   ", snackPnl);
		menuTab.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		 menuTab.setTabPlacement(JTabbedPane.LEFT);

	
		menuPnl.add(menuTab);
<<<<<<< HEAD
//		menuPnl.setBounds(0, 0, 1000, 600);
//		menuPnl.setBackground(Color.WHITE);
//		menuPnl.setLayout(new FlowLayout());
=======
		// menuPnl.setBounds(0, 0, 1000, 600);
		menuPnl.setBackground(Color.WHITE);
		menuPnl.setLayout(new FlowLayout());
>>>>>>> 2142f8e8b69e8925ad4691522f0a22d49b9eceb0

		// menuPnl.setPreferredSize(new Dimension(400, 300));

		/// ÃÑ »ç¿ë±Ý¾×
		priceLabel = new JLabel("ÃÑ ±Ý¾× : ");
		priceLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));

		labelPnl.add(priceLabel);
		labelPnl.setBackground(Color.yellow);
		labelPnl.setPreferredSize(new Dimension(0, -400));
		// labelPnl.setBounds(0, 400, 1000, 100);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// °áÁ¦, Ãë¼Ò
		orderBtn = new JButton(" ÁÖ ¹® ");
		cancleBtn = new JButton(" Ãë ¼Ò ");
		orderBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		cancleBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		orderBtn.setBackground(Color.WHITE);
		cancleBtn.setBackground(Color.WHITE);
		orderBtn.setFocusPainted(false);
		cancleBtn.setFocusPainted(false);

		selectPnl.setPreferredSize(new Dimension(0, -350));
		selectPnl.setBackground(Color.RED);
		// selectPnl.add(priceLabel);
		selectPnl.add(orderBtn);
		selectPnl.add(cancleBtn);
<<<<<<< HEAD
		//selectPnl.setBounds(0, 500, 1000, 400);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/// ÃÑ »ç¿ë±Ý¾×
		labelPnl = new JPanel();
		priceLabel = new JLabel("ÃÑ ±Ý¾× : ");
		
		labelPnl.add(priceLabel);
		//labelPnl.setBounds(0, 400, 1000, 100);
		
=======
		// selectPnl.setBounds(0, 500, 1000, 400);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
>>>>>>> 2142f8e8b69e8925ad4691522f0a22d49b9eceb0

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		add(menuPnl);
		//add(labelPnl);
		//add(selectPnl);

<<<<<<< HEAD
		// setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setLayout(null);
=======
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		// setLayout(null);
>>>>>>> 2142f8e8b69e8925ad4691522f0a22d49b9eceb0
		setSize(1000, 800);
		// setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		// setUndecorated(true);
		setLocation(150, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		OrderGUI oderGui = new OrderGUI();
	}
}
