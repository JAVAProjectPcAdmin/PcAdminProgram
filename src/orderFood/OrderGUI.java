package orderFood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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

		// �޴���
		menuTab = new JTabbedPane();
		menuTab.addTab("   ��   ��   ��   ", ramenPnl);
		menuTab.addTab("   ��   ��   ��   ", drinkPnl);
		menuTab.addTab("   ��   ��   ��   ", snackPnl);
		

		menuTab.setFont(new Font("���� ���", Font.BOLD, 25));
		menuTab.setTabPlacement(JTabbedPane.LEFT);

		menuPnl.setLayout(new BorderLayout());
		menuPnl.add(menuTab);
		menuPnl.setBackground(Color.WHITE);

		/// �� ���ݾ�
		priceLabel = new JLabel("<html><br>�� �ݾ� : ��<br><br></html>");
		priceLabel.setFont(new Font("���� ���", Font.BOLD, 20));

		labelPnl.add(priceLabel);
		labelPnl.setBackground(Color.WHITE);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// ����, ���
		orderBtn = new JButton("    ��  ��    ");
		cancleBtn = new JButton("    ��  ��    ");
		orderBtn.setFont(new Font("���� ���", Font.BOLD, 25));
		cancleBtn.setFont(new Font("���� ���", Font.BOLD, 25));
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


		
		// setLayout(null);
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
