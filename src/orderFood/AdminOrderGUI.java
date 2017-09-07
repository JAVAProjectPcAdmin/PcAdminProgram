package orderFood;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import AdminServer.User;

public class AdminOrderGUI extends JFrame {
	private JButton okBtn;
	private JPanel wholePnl;
	private JLabel seatLbl;
	private JLabel orderLbl; 

	public AdminOrderGUI(String order, User user) {
		okBtn = new JButton("Ȯ��");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		seatLbl = new JLabel(user.getSeatNumber() + "�� �ڸ����� �ֹ��� ���Խ��ϴ�.");
		orderLbl = new JLabel(order);
		wholePnl = new JPanel();
		wholePnl.setLayout(null);
		wholePnl.add(seatLbl);
		seatLbl.setBounds(40, 7, 300, 50);
		wholePnl.add(orderLbl);
		orderLbl.setBounds(95, 30, 300, 100);
		wholePnl.add(okBtn);
		okBtn.setBounds(110, 170, 60, 30);
		wholePnl.setBackground(Color.WHITE);
		add(wholePnl);
		setSize(300, 260);
		setVisible(true);
	}
}
