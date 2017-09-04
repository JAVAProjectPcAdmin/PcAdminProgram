package orderFood;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

//size 200, 250

public class MenuPnlGui extends JPanel {
	private JButton upBtn, downBtn;
	private JLabel countLbl, menuImgLbl;
	public int count = 0;
	
	public MenuPnlGui(String menuImg) {
		Icon upBtnImg = new ImageIcon("plus.png");
		Icon downbtnImg = new ImageIcon("minus.png");
		Icon menuLblImg = new ImageIcon(menuImg);
		
		upBtn = new JButton(upBtnImg);
		downBtn = new JButton(downbtnImg);
		
		countLbl = new JLabel(Integer.toString(count));
		countLbl.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		
		menuImgLbl = new JLabel(menuLblImg, SwingConstants.CENTER); 
		
		setLayout(null);
		
		menuImgLbl.setBounds(13, 10, 160, 160);
		downBtn.setBounds(50, 180, 20, 20);
		countLbl.setBounds(85, 175, 30, 30);
		upBtn.setBounds(110, 180, 20, 20);
		
		setBackground(Color.WHITE);
		
		add(menuImgLbl);
		add(downBtn);
		add(countLbl);
		add(upBtn);
	}
}
