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
	private JPanel wholePnl;
	private JButton orderBtn, cancleBtn;
	private JTabbedPane menuTab;
	private JLabel priceLabel;

	public OrderGUI() {
		wholePnl = new JPanel();

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

<<<<<<< HEAD
		menuTab.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		menuTab.setTabPlacement(JTabbedPane.LEFT);

		menuPnl.setLayout(new BorderLayout());
		menuPnl.add(menuTab);
		menuPnl.setBackground(Color.WHITE);
=======
	
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
>>>>>>> 43d6c9269062db89bf1db66cc9ff36ab7a43f125

		/// ÃÑ »ç¿ë±Ý¾×
		priceLabel = new JLabel("<html><br>ÃÑ ±Ý¾× : ¿ø<br><br></html>");
		priceLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));

		labelPnl.add(priceLabel);
		labelPnl.setBackground(Color.WHITE);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// °áÁ¦, Ãë¼Ò
		orderBtn = new JButton("    ÁÖ  ¹®    ");
		cancleBtn = new JButton("    Ãë  ¼Ò    ");
		orderBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		cancleBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		orderBtn.setBackground(Color.WHITE);
		cancleBtn.setBackground(Color.WHITE);
		orderBtn.setFocusPainted(false);
		cancleBtn.setFocusPainted(false);

		selectPnl.setBackground(Color.WHITE);
		selectPnl.add(orderBtn);
		selectPnl.add(cancleBtn);
<<<<<<< HEAD

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		wholePnl.setLayout(new BoxLayout(wholePnl, BoxLayout.Y_AXIS));

		wholePnl.add(menuPnl);
		wholePnl.add(labelPnl);
		wholePnl.add(selectPnl);
		add(wholePnl);

=======
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
>>>>>>> 43d6c9269062db89bf1db66cc9ff36ab7a43f125
		// setLayout(null);
>>>>>>> 2142f8e8b69e8925ad4691522f0a22d49b9eceb0
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setUndecorated(true);
		setLocation(150, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		OrderGUI oderGui = new OrderGUI();
	}
}
