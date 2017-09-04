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
	
<<<<<<< HEAD
	public MenuPnlGui(String menuImg) {
		Icon upBtnImg = new ImageIcon("plus.png");
		Icon downbtnImg = new ImageIcon("minus.png");
=======
	public MenuPnlGuI(String menuImg) {
		Icon upBtnImg = new ImageIcon("upbutton.jpg");
		Icon downbtnImg = new ImageIcon("downbutton.jpg");
>>>>>>> f1b39694a8a3c0ed43b689eb9ec5e75ac2b533ba
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
