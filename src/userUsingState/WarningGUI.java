package userUsingState;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.applet.Main;

public class WarningGUI extends JFrame {
	private JButton okBtn;
	private JLabel warningLbl, imgLbl;
	private JPanel wholePnl;

	public static void main(String[] args) {
		new WarningGUI();
	}
	public WarningGUI() {
		okBtn = new JButton(new ImageIcon("images//ok.png"));
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Icon warningImg = new ImageIcon("images//warning.png");
		imgLbl = new JLabel(warningImg);
		warningLbl = new JLabel("<html>�̼����ڴ� �� 10�� ���� <br>��ǻ�͸� ����Ͻ� �� �����ϴ�!</html>");
		warningLbl.setFont(new Font("���� ���", Font.PLAIN, 15));
		wholePnl = new JPanel();
		wholePnl.setLayout(null);
		wholePnl.add(warningLbl);
		warningLbl.setBounds(80, 10, 300, 100);
		wholePnl.add(okBtn);
		okBtn.setBounds(145, 110, 60, 26);
		wholePnl.add(imgLbl);
		imgLbl.setBounds(20, 40, 41, 41);
		wholePnl.setBackground(Color.WHITE);
		add(wholePnl);
		setTitle("���� �˸�");
		setResizable(false);
		setAlwaysOnTop(true);
		setLocation(500, 350);
		setVisible(true);
		setSize(350, 200);
	}
}
