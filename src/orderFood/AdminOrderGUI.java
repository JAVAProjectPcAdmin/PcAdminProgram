package orderFood;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminOrderGUI extends JFrame {
	private JButton okBtn;
	private JPanel wholePnl;
	private JLabel seatLbl;
	private JLabel orderLbl;

	public AdminOrderGUI(String order, int seatNum) {

		seatLbl = new JLabel((seatNum + 1) + "번 자리에서 주문이 들어왔습니다.");
		seatLbl.setFont(new Font("맑은고딕", Font.BOLD, 15));
		orderLbl = new JLabel(order);
		orderLbl.setFont(new Font("맑은고딕", Font.PLAIN, 18));
		wholePnl = new JPanel();
		wholePnl.setLayout(null);
		wholePnl.add(seatLbl);
		seatLbl.setBounds(20, 15, 300, 50);
		wholePnl.add(orderLbl);
		orderLbl.setBounds(90, 50, 300, 200);
		okBtn = new JButton(new ImageIcon("images//ok.png"));
		wholePnl.add(okBtn);
		okBtn.setBounds(110, 230, 60, 26);
		wholePnl.setBackground(Color.WHITE);
		add(wholePnl);

		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(300, 300);
		setLocation(470, 300);
		setAlwaysOnTop(true);
		setVisible(true);
	}
}
