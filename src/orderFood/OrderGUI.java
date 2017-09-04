package orderFood;

import javax.swing.*;

public class OrderGUI extends JFrame {
	private JPanel[] menuPnl;
	private JPanel ramenPnl, drinkPnl;
	private JButton downBtn, upBtn, orderBtn, cancleBtn;
	private JTabbedPane ramenTab, drinkTab;
	
	public OrderGUI() {
		menuPnl = new JPanel[16];
		
		ramenPnl = new JPanel();
		drinkPnl = new JPanel();
		
		downBtn = new JButton("-");
		upBtn = new JButton("+");
		orderBtn = new JButton("주문");
		cancleBtn = new JButton("취소");
		
		ramenTab = new JTabbedPane();
		
		ramenTab.add("라면", ramenPnl);
		
		
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		OrderGUI o = new OrderGUI();
	}
}
