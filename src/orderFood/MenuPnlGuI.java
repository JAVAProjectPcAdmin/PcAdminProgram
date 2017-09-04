package orderFood;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

//size 200, 250

public class MenuPnlGuI extends JFrame {
	private JButton upBtn, downBtn, ex;
	private JLabel countLbl, menuImgLbl;
	public int count = 0;
	
	public MenuPnlGuI() {
		Icon upBtnImg = new ImageIcon("plus1.png");
		Icon downbtnImg = new ImageIcon("minus.png");

		upBtn = new JButton(upBtnImg);
		downBtn = new JButton(downbtnImg);
		
		ex = new JButton("ggg");
		
		countLbl = new JLabel(Integer.toString(count));
		countLbl.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		
		menuImgLbl = new JLabel("");
		menuImgLbl.setBackground(Color.ORANGE);
		
		
		setLayout(null);
		ex.setBounds(13, 10, 160, 160);
		add(ex);
		menuImgLbl.setBounds(13, 10, 160, 160);
		downBtn.setBounds(50, 180, 20, 20);
		countLbl.setBounds(85, 175, 30, 30);
		upBtn.setBounds(110, 180, 20, 20);
		
		getContentPane().setBackground(Color.WHITE);
		
		add(menuImgLbl);
		add(downBtn);
		add(countLbl);
		add(upBtn);
		setSize(200, 250); //
		setVisible(true); //
		setDefaultCloseOperation(EXIT_ON_CLOSE); //
	}
	
	public static void main(String[] args) {
		MenuPnlGuI m = new MenuPnlGuI();
	}
}
