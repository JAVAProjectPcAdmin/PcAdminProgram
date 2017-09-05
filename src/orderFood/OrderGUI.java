package orderFood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private MenuPnlGui ramen[], drink[], snack[];

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

		menuTab.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		menuTab.setTabPlacement(JTabbedPane.LEFT);

		menuPnl.setLayout(new BorderLayout());
		menuPnl.add(menuTab);
		menuPnl.setBackground(Color.WHITE);

		/// ÃÑ »ç¿ë±Ý¾×
		priceLabel = new JLabel("<html><br>ÃÑ ±Ý¾× : ¿ø<br><br></html>");
		priceLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));

		labelPnl.add(priceLabel);
		labelPnl.setBackground(Color.WHITE);

		////////////////////////////////////////////////////////////////////////////////////////////////

		ramenPnl.setLayout(new GridLayout(2, 4));

		ramen = new MenuPnlGui[8];
		for (int i = 0; i < ramen.length; i++) {
			ramen[i] = new MenuPnlGui(
					"C:\\Users\\student\\Documents\\GitHub\\PcAdminProgram\\food\\ramen" + (i + 1) + ".png");
			ramen[i].setSize(200, 250);
			ramenPnl.add(ramen[i]);
		}

		////////////////////////////////////////////////////////////////////////////////////////////////

		drinkPnl.setLayout(new GridLayout(2, 4));

		drink = new MenuPnlGui[8];
		for (int i = 0; i < drink.length; i++) {
			drink[i] = new MenuPnlGui(
					"C:\\Users\\student\\Documents\\GitHub\\PcAdminProgram\\food\\drink" + (i + 1) + ".png");
			drink[i].setSize(200, 250);
			drinkPnl.add(drink[i]);
		}

		////////////////////////////////////////////////////////////////////////////////////////////////

		snackPnl.setLayout(new GridLayout(2, 4));

		snack = new MenuPnlGui[8];
		for (int i = 0; i < snack.length; i++) {
			snack[i] = new MenuPnlGui(
					"C:\\Users\\student\\Documents\\GitHub\\PcAdminProgram\\food\\snack" + (i + 1) + ".png");
			snack[i].setSize(200, 260);
			snackPnl.add(snack[i]);
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// °áÁ¦, Ãë¼Ò
		orderBtn = new JButton("    ÁÖ  ¹®    ");
		cancleBtn = new JButton("    Ãë  ¼Ò    ");
		cancleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		orderBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		cancleBtn.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		orderBtn.setBackground(Color.WHITE);
		cancleBtn.setBackground(Color.WHITE);
		orderBtn.setFocusPainted(false);
		cancleBtn.setFocusPainted(false);

		selectPnl.setBackground(Color.WHITE);
		selectPnl.add(orderBtn);
		selectPnl.add(cancleBtn);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		wholePnl.setLayout(new BoxLayout(wholePnl, BoxLayout.Y_AXIS));

		wholePnl.add(menuPnl);
		wholePnl.add(labelPnl);
		wholePnl.add(selectPnl);
		add(wholePnl);

		setSize(1000, 800);
		setResizable(false);

		setBackground(Color.WHITE);
		setUndecorated(true);
		setLocation(150, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		OrderGUI oderGui = new OrderGUI();
	}
}
