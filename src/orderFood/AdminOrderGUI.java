package orderFood;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import AdminServer.User;

public class AdminOrderGUI extends JFrame {
	private JButton okBtn;
	private JPanel wholePnl;
	private JLabel seatLbl;
	private JLabel orderLbl; 

	public AdminOrderGUI(String order, int seatNum) {
		okBtn = new JButton("Ȯ��");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		seatLbl = new JLabel(seatNum + "�� �ڸ����� �ֹ��� ���Խ��ϴ�.");
		seatLbl.setFont(new Font("��������", Font.BOLD, 15));
		orderLbl = new JLabel(order);
		orderLbl.setFont(new Font("��������", Font.PLAIN, 13));
		wholePnl = new JPanel();
		wholePnl.setLayout(null);
		wholePnl.add(seatLbl);
		seatLbl.setBounds(30, 7, 300, 50);
		wholePnl.add(orderLbl);
		orderLbl.setBounds(95, 30, 300, 100);
		wholePnl.add(okBtn);
		okBtn.setBounds(110, 170, 60, 30);
		wholePnl.setBackground(Color.WHITE);
		add(wholePnl);
		setSize(300, 260);
		//setUndecorated(true);
		setVisible(false);
	}
}
