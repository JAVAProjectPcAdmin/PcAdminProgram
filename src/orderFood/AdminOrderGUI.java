package orderFood;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminOrderGUI extends JFrame {
	private JButton okBtn;
	private JPanel wholePnl;
	private JLabel seatLbl;
	public JLabel orderLbl; 
	
	public AdminOrderGUI(String order) {
		
		okBtn = new JButton("확 인");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		seatLbl = new JLabel("번 자리에서 주문이 들어왔습니다.");
		orderLbl = new JLabel(order);
		wholePnl = new JPanel();
		wholePnl.add(seatLbl);
		wholePnl.add(orderLbl);
		wholePnl.add(okBtn);
		add(wholePnl);
		
		setSize(300, 100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
