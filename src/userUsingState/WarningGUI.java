package userUsingState;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarningGUI extends JFrame {
	private JButton okBtn;
	private JLabel warningLbl, imgLbl;
	private JPanel wholePnl;

	public WarningGUI() {
		okBtn = new JButton("확인");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Icon warningImg = new ImageIcon("images//warning.png");
		imgLbl = new JLabel(warningImg);
		warningLbl = new JLabel("<html>미성년자는 <br>밤 10시에 컴퓨터가 종료됩니다!</html>");
		wholePnl = new JPanel();
		wholePnl.setLayout(null);
		wholePnl.add(warningLbl);
		warningLbl.setBounds(80, 10, 300, 100);
		wholePnl.add(okBtn);
		okBtn.setBounds(120, 100, 60, 30);
		wholePnl.add(imgLbl);
		imgLbl.setBounds(20, 40, 41, 41);
		wholePnl.setBackground(Color.WHITE);
		add(wholePnl);
		setTitle("종료 알림");
		setResizable(false);
		setAlwaysOnTop(true);
		setLocation(500, 350);
		setVisible(true);
		setSize(300, 200);
	}
}
